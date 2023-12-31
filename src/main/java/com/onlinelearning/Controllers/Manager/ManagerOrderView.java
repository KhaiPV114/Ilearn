package com.onlinelearning.Controllers.Manager;

import com.onlinelearning.Enums.OrderStatus;
import com.onlinelearning.Models.Order;
import com.onlinelearning.Services.Impl.OrderServiceImpl;
import com.onlinelearning.Services.OrderService;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ManagerOrderView", urlPatterns = {"/manager/order"})
public class ManagerOrderView extends HttpServlet {

    private static final String VIEW_PATH = "/dashboard/manager/order.jsp";

    private final OrderService orderService = new OrderServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Order> allOrders = orderService.getAllOrders();
        List<Order> pendingOrders = new ArrayList<>();
        List<Order> failedOrders = new ArrayList<>();
        List<Order> successfulOrders = new ArrayList<>();

        if (allOrders != null) {
            for (Order order : allOrders) {
                if (order.getStatus() == OrderStatus.UNPAID) {
                    pendingOrders.add(order);
                }
                if (order.getStatus() == OrderStatus.FAILED) {
                    failedOrders.add(order);
                }
                if (order.getStatus() == OrderStatus.SUCCESSFUL) {
                    successfulOrders.add(order);
                }
            }
        }

        request.setAttribute("pendingOrders", pendingOrders);
        request.setAttribute("failedOrders", failedOrders);
        request.setAttribute("successfulOrders", successfulOrders);
        request.setAttribute("allOrders", allOrders);
        request.getRequestDispatcher(VIEW_PATH).forward(request, response);
    }

}
