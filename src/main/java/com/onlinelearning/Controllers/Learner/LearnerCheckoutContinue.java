/*
 * DuyDuc94
 */

package com.onlinelearning.Controllers.Learner;

import com.onlinelearning.Models.Order;
import com.onlinelearning.Models.OrderItem;
import com.onlinelearning.Models.User;
import com.onlinelearning.Services.AuthService;
import com.onlinelearning.Services.CouponService;
import com.onlinelearning.Services.Impl.AuthServiceImpl;
import com.onlinelearning.Services.Impl.CouponServiceImpl;
import com.onlinelearning.Services.Impl.OrderItemServiceImpl;
import com.onlinelearning.Services.Impl.OrderServiceImpl;
import com.onlinelearning.Services.OrderItemService;
import com.onlinelearning.Services.OrderService;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author duy20
 */
@WebServlet(name="LearnerCheckoutContinue", urlPatterns={"/cart/checkout/continue"})
public class LearnerCheckoutContinue extends HttpServlet {
    
    private final String VIEW_PATH = "/dashboard/learner/checkout.jsp";
    private final String ERROR_404_PATH = "/error/404.jsp";
    private final String HOME_PATH = "/homepage";
    private final OrderService OrderService = new OrderServiceImpl();
    private final OrderItemService OrderItemService = new OrderItemServiceImpl();
    private final CouponService CouponService = new CouponServiceImpl();
    private final AuthService AuthService = new AuthServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        doPost(request, response);
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        User user = AuthService.getUser(request);
        String orderId = request.getParameter("order-id");
        if (orderId == null || user == null) {
            return;
        }
        
        Order createdOrder = OrderService.getOrderById(Integer.parseInt(orderId));
        
        double subTotal = 0;
        double grandTotal = 0;

        List<OrderItem> orderItems = OrderService.getOrderItemsByOrderId(createdOrder.getId());
        for (OrderItem orderItem : orderItems) {
            subTotal += orderItem.getOriginalPrice();
            grandTotal += orderItem.getPrice();
        }
        
        request.setAttribute("order", createdOrder);
        request.setAttribute("orderItems", orderItems);
        request.setAttribute("subTotal", subTotal);
        request.setAttribute("discount", subTotal - grandTotal);
        request.setAttribute("grandTotal", grandTotal);
        
        if (grandTotal == 0) {
            request.setAttribute("noNeedPayment", true);
        }

        request.getRequestDispatcher(VIEW_PATH).forward(request, response);
    }

}
