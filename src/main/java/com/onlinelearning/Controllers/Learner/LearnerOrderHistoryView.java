/*
 * DuyDuc94
 */
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

@WebServlet(name = "LearnerOrderHistoryView", urlPatterns = {"/dashboard/learner/order/history"})
public class LearnerOrderHistoryView extends HttpServlet {

    private final String VIEW_PATH = "/dashboard/learner/order-history.jsp";
    private final AuthService AuthService = new AuthServiceImpl();
    private final OrderService OrderService = new OrderServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = AuthService.getUser(request);
        if (user == null) {

        } else {
            List<Order> orders = OrderService.getAllOrdersByUserId(user.getId());

            request.setAttribute("orders", orders);
        }
        request.getRequestDispatcher(VIEW_PATH).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);

    }
}
