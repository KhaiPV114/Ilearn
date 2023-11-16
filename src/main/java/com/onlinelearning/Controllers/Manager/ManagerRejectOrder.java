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
import java.io.PrintWriter;

@WebServlet(name = "ManagerRejectOrder", urlPatterns = {"/manager/order/reject"})
public class ManagerRejectOrder extends HttpServlet {

    private final OrderService orderService = new OrderServiceImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter pw = response.getWriter();
        String orderId = request.getParameter("order-id");
        try {
            Order order = orderService.getOrderById(Integer.parseInt(orderId));
            if (order != null) {
                order.setStatus(OrderStatus.FAILED);
                orderService.updateOrder(order);
                response.setStatus(HttpServletResponse.SC_OK);
            } else {
                throw new Exception("Order not found with id: " + orderId);
            }
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            pw.print(e.getMessage());
        }
    }
}
