package com.onlinelearning.Controllers.Learner;

import com.onlinelearning.Services.Impl.OrderServiceImpl;
import com.onlinelearning.Services.OrderService;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "LearnerCheckoutCancel", urlPatterns = {"/cart/checkout/cancel"})
public class LearnerCheckoutCancel extends HttpServlet {

    private final OrderService orderService = new OrderServiceImpl();
    private final String HOME_PATH = "/homepage";
    private final String VIEW_PATH = "/dashboard/learner/order/history";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("view-order-history", true);
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String orderId = request.getParameter("order-id");
        Boolean viewOrderHistory = (Boolean) request.getAttribute("view-order-history");
        try {
            orderService.deleteOrder(Integer.parseInt(orderId));
        } catch (Exception e) {
            
        }

        if (viewOrderHistory) {
            response.sendRedirect(request.getContextPath() + VIEW_PATH);
        } else {
            response.sendRedirect(request.getContextPath() + HOME_PATH);
        }
    }
}
