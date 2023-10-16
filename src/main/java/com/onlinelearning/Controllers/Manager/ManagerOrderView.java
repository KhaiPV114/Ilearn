/*
 * DuyDuc94
 */
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

/**
 * @author duy20
 */
@WebServlet(name = "ManagerOrderView", urlPatterns = {"/dashboard/manager/order"})
public class ManagerOrderView extends HttpServlet {

    private final String VIEW_PATH = "/dashboard/manager/order.jsp";
    private final OrderService OrderService = new OrderServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Order> allOrders = OrderService.getAllOrders();
        List<Order> pendingOrders = new ArrayList<>();
        List<Order> rejectedOrders = new ArrayList<>();
        List<Order> failedOrders = new ArrayList<>();
        List<Order> successfulOrders = new ArrayList<>();

        for (Order order : allOrders) {
            if (order.getStatus().equals(OrderStatus.PENDING)) {
                pendingOrders.add(order);
            }
            if (order.getStatus().equals(OrderStatus.REJECTED)) {
                rejectedOrders.add(order);
            }
            if (order.getStatus().equals(OrderStatus.FAILED)) {
                failedOrders.add(order);
            }
            if (order.getStatus().equals(OrderStatus.SUCCESSFUL)) {
                successfulOrders.add(order);
            }
        }
        System.out.println("Pending orders: " + pendingOrders);
        System.out.println("Failed orders: " + failedOrders);
        System.out.println("Successful orders: " + successfulOrders);
        System.out.println("Rejected orders: " + rejectedOrders);
        System.out.println("All orders: " + allOrders);
        request.setAttribute("pendingOrders", pendingOrders);
        request.setAttribute("rejectedOrders", rejectedOrders);
        request.setAttribute("failedOrders", failedOrders);
        request.setAttribute("successfulOrders", successfulOrders);
        request.setAttribute("allOrders", allOrders);
        request.getRequestDispatcher(VIEW_PATH).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
