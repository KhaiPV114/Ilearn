package com.onlinelearning.Controllers.Learner;

import com.onlinelearning.Services.Impl.OrderServiceImpl;
import com.onlinelearning.Services.OrderService;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "LearnerCheckoutCancel", urlPatterns = {"/cart/checkout/cancel"})
public class LearnerCheckoutCancel extends HttpServlet {

    private static final String VIEW_PATH = "/learner/order/history";
    
    private static final String ERROR_404_PATH = "/error/404.jsp";
    
    private static final String HOME_PATH = "/homepage";

    private final OrderService orderService = new OrderServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher(ERROR_404_PATH).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String orderId = request.getParameter("order-id");
        try {
            orderService.deleteOrder(Integer.parseInt(orderId));
            if (request.getParameter("view-order-history") != null) {
                response.sendRedirect(request.getContextPath() + VIEW_PATH);
            } else {
                response.sendRedirect(request.getContextPath() + HOME_PATH);
            }
        } catch (Exception ex) {
            Logger.getLogger(LearnerCheckoutCancel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
