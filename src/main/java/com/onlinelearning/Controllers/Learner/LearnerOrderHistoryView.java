package com.onlinelearning.Controllers.Learner;

import com.onlinelearning.Models.Order;
import com.onlinelearning.Models.User;
import com.onlinelearning.Services.AuthService;
import com.onlinelearning.Services.Impl.AuthServiceImpl;
import com.onlinelearning.Services.Impl.OrderServiceImpl;
import com.onlinelearning.Services.OrderService;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet(name = "LearnerOrderHistoryView", urlPatterns = {"/learner/order/history"})
public class LearnerOrderHistoryView extends HttpServlet {

    private static final String VIEW_PATH = "/dashboard/learner/order-history.jsp";
    private static final String ERROR_403_PATH = "/error/403.jsp";

    private final AuthService authService = new AuthServiceImpl();

    private final OrderService orderService = new OrderServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = authService.getUser(request);
        if (user == null) {
            request.getRequestDispatcher(ERROR_403_PATH).forward(request, response);
        } else {
            Order unpaidOrder = orderService.getUnpaidOrderByUserId(user.getId());
            List<Order> orders = orderService.getAllOrdersByUserId(user.getId());
            request.setAttribute("unpaidOrder", unpaidOrder);
            request.setAttribute("orders", orders);
            request.getRequestDispatcher(VIEW_PATH).forward(request, response);
        }
    }

}
