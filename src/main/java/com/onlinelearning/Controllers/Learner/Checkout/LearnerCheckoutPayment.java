package com.onlinelearning.Controllers.Learner.Checkout;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.onlinelearning.Models.User;
import com.onlinelearning.Services.AuthService;
import com.onlinelearning.Services.Impl.AuthServiceImpl;
import com.onlinelearning.Services.Impl.VNPaymentServiceImpl;
import com.onlinelearning.Services.PaymentService;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

@WebServlet(name = "LearnerCheckoutPayment", urlPatterns = {"/cart/checkout/payment"})
public class LearnerCheckoutPayment extends HttpServlet {

    private final String ERROR_404_PATH = "/error/404.jsp";
    private final String ERROR_403_PATH = "/error/403.jsp";

    private final PaymentService VNPay = new VNPaymentServiceImpl();
    private final String ReturnUrl = "http://localhost:8686/iLearn/cart/checkout/process";

    private final AuthService AuthService = new AuthServiceImpl();
//    private final String VIETQR_BASE_LINK = "https://api.vietqr.io/image/";
//    private final String BANK_ID = "970418";    //BIDV
//    private final String ACCOUNT_NO = "2112359999"; //LUONG HUU DUC DUY
//    private final String TEMPLATE_LINK = "KpkOM2t.jpg";
//    private final String DESCRIPTION = "ilearn+order+";
    private final double VNDExchangeRate = 24385;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher(ERROR_404_PATH).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = AuthService.getUser(request);
        if (user != null) {
            try ( PrintWriter pw = response.getWriter()) {
//            String orderId = request.getParameter("order-id");
//            String grandTotal = request.getParameter("grandTotal");
//            if (orderId != null && grandTotal != null) {
//                Double price = Double.parseDouble(grandTotal);
//                price = price * VNDExchangeRate;   //Transfer price to VND
//
//                //Generate VietQR API image link
//                String qrLink = VIETQR_BASE_LINK 
//                        + BANK_ID 
//                        + "-" + ACCOUNT_NO 
//                        + "-" + TEMPLATE_LINK 
//                        + "?amount=" + price.intValue() 
//                        + "&addInfo=" + DESCRIPTION + orderId;
//                response.setStatus(HttpServletResponse.SC_OK);
//                pw.print(qrLink);
//            }else{
//                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//                pw.print("Unknown errors have occurred!");
//            }

                long amount = Math.round((Double.parseDouble(request.getParameter("amount")) * VNDExchangeRate) * 100);
                String vnp_TxnRef = request.getParameter("order-id");

                //Require fleids
                Map<String, String> vnp_Params = new HashMap<>();
                vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
                vnp_Params.put("vnp_Command", "pay");
                vnp_Params.put("vnp_BankCode", request.getParameter("payment-method"));
                vnp_Params.put("vnp_Locale", "en");
                vnp_Params.put("vnp_CurrCode", "VND");
                vnp_Params.put("vnp_OrderType", "other");
                vnp_Params.put("vnp_Amount", String.valueOf(amount));
                vnp_Params.put("vnp_ReturnUrl", ReturnUrl);
                vnp_Params.put("vnp_IpAddr", VNPaymentServiceImpl.getIpAddress(request));

                Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
                SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
                String vnp_CreateDate = formatter.format(cld.getTime());
                vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

//                cld.add(Calendar.MINUTE, 10);
                cld.add(Calendar.SECOND, 30);
                String vnp_ExpireDate = formatter.format(cld.getTime());
                vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);

                //Optional fleids
                vnp_Params.put("vnp_Bill_FirstName", user.getFullName());
                vnp_Params.put("vnp_Bill_Mobile", user.getPhoneNumber());
                String userEmail = user.getEmail().isEmpty() ? user.getGoogleEmail() : user.getEmail();
                vnp_Params.put("vnp_Bill_Email", userEmail);

                String paymentUrl = VNPay.getPaymentURL(vnp_Params);

                if (!paymentUrl.isEmpty()) {
                    JsonObject job = new JsonObject();
                    job.addProperty("code", "00");
                    job.addProperty("message", "success");
                    job.addProperty("data", paymentUrl);
                    Gson gson = new Gson();
                    pw.write(gson.toJson(job));
                } else {

                }
            }
        } else {
            request.getRequestDispatcher(ERROR_403_PATH).forward(request, response);
        }
    }
}
