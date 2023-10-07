package com.onlinelearning.Controllers.Learner;

import com.onlinelearning.Enums.OrderStatus;
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
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "LearnerCheckoutProcess", urlPatterns = {"/cart/checkout/process"})
public class LearnerCheckoutProcess extends HttpServlet {

    private final AuthService AuthService = new AuthServiceImpl();
    private final OrderService OrderService = new OrderServiceImpl();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        User user = AuthService.getUser(request);
        if (user != null) {
            String orderId = request.getParameter("order-id");
            if (orderId != null) {
                Order createdOrder = OrderService.getOrderById(Integer.parseInt(orderId));
                if(createdOrder.getUserId().equals(user.getId()) ){
                    createdOrder.setStatus(OrderStatus.PENDING);
                    createdOrder = OrderService.updateOrder(createdOrder);
                    PrintWriter out = response.getWriter();
                    List<Order> orders = OrderService.getAllOrdersByUserId(user.getId());
                    for (Order order : orders) {
                        out.print(order.toString() + "<br/>");
                    }
                    out.print("New added:" + createdOrder.toString());
                }
            }

        }
    }
}
