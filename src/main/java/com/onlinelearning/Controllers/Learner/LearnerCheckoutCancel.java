package com.onlinelearning.Controllers.Learner;

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

@WebServlet(name="LearnerCheckoutCancel", urlPatterns={"/cart/checkout/cancel"})
public class LearnerCheckoutCancel extends HttpServlet {
    
    private final OrderService orderService = new OrderServiceImpl();
    private final String HOME_PATH = "/homepage";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String orderId = request.getParameter("order-id");
        
        try {
            orderService.deleteOrder(Integer.parseInt(orderId));
        } catch (Exception e) {
        }
        
        response.sendRedirect(request.getContextPath() + HOME_PATH);
    }
}
