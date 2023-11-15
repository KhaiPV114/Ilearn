package com.onlinelearning.Controllers.Learner;

import com.onlinelearning.Enums.OrderStatus;
import com.onlinelearning.Models.Order;
import com.onlinelearning.Models.User;
import com.onlinelearning.Services.AuthService;
import com.onlinelearning.Services.CartService;
import com.onlinelearning.Services.Impl.AuthServiceImpl;
import com.onlinelearning.Services.Impl.CartServiceImpl;
import com.onlinelearning.Services.Impl.OrderServiceImpl;
import com.onlinelearning.Services.Impl.VNPaymentServiceImpl;
import com.onlinelearning.Services.OrderService;
import com.onlinelearning.Services.PaymentService;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "LearnerCheckoutProcess", urlPatterns = {"/cart/checkout/process"})
public class LearnerCheckoutProcess extends HttpServlet {

    private static final String VIEW_PATH = "/dashboard/learner/order-process.jsp";

    private static final String ERROR_403_PATH = "/error/403.jsp";

    private final AuthService authService = new AuthServiceImpl();

    private final PaymentService VNPAY = new VNPaymentServiceImpl();

    private final OrderService orderService = new OrderServiceImpl();

    private final CartService cartService = new CartServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Nhận request return từ VNPay, Gửi sang POST
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //Get all fields of request from VNPay
        Enumeration params = request.getParameterNames();
        Map<String, String> vnp_Params = new HashMap<>();
        while (params.hasMoreElements()) {
            String fieldName = (String) params.nextElement();
            String fieldValue = URLDecoder.decode(request.getParameter(fieldName), "UTF-8");
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                vnp_Params.put(fieldName, fieldValue);
            }
        }
        String vnp_SecureHash = request.getParameter("vnp_SecureHash");
        if (vnp_Params.containsKey("vnp_SecureHash")) {
            vnp_Params.remove("vnp_SecureHash");
        }

        //Checksum to validate data
        if (VNPAY.compareChecksum(vnp_Params, vnp_SecureHash)) {

            //Process order based on transaction status from vnpay
            Order paymentedOrder = orderService.getOrderById(Integer.parseInt(vnp_Params.get("vnp_TxnRef")));
            String message = "";
            if (paymentedOrder != null) {
                if (paymentedOrder.getStatus().equals(OrderStatus.UNPAID)) {    //Avoid user reload page
                    switch (vnp_Params.get("vnp_ResponseCode")) {
                        case "00":  //Thanh toán thành công
                            paymentedOrder.setStatus(OrderStatus.SUCCESSFUL);
                            paymentedOrder = orderService.updateOrder(paymentedOrder);
                            cartService.deleteCartOfUserId(paymentedOrder.getUserId());
                            try {
                                orderService.getUserEnrollCourseByOrderId(paymentedOrder.getId());
                            } catch (Exception e) {
                            }
                            message += "<p>Order payment successful!<p>";
                            break;
                        case "24":  //Khách hàng huỷ giao dịch
                            try {
                                orderService.deleteOrder(paymentedOrder.getId());
                            } catch (Exception ex) {
                                Logger.getLogger(LearnerCheckoutProcess.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            break;
                        default:    //Giao dịch bị lỗi
                            paymentedOrder.setStatus(OrderStatus.FAILED);
                            paymentedOrder = orderService.updateOrder(paymentedOrder);
                            cartService.deleteCartOfUserId(paymentedOrder.getUserId());
                            break;
                    }
                } else {
//                    pw.print("This order has been processed: " + paymentedOrder.getStatus());
//                    return;
                }
            }

            //Check user
            User user = authService.getUser(request);
            if (user != null) {
                //Trả về trang checkout.jsp với status và order
                if (paymentedOrder.getUserId().equals(user.getId())) {
                    request.setAttribute("orderId", paymentedOrder.getId());
                    request.setAttribute("responseCode", vnp_Params.get("vnp_ResponseCode"));
                    request.setAttribute("transactionCode", vnp_Params.get("vnp_TransactionStatus"));
                    request.getRequestDispatcher(VIEW_PATH).forward(request, response);
                    return;
                }
            }
            request.getRequestDispatcher(ERROR_403_PATH).forward(request, response);
        }

    }
}
