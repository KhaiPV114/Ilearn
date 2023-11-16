package com.onlinelearning.Controllers.Learner;

import com.onlinelearning.Enums.OrderStatus;
import com.onlinelearning.Models.Order;
import com.onlinelearning.Models.OrderItem;
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
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "LearnerCheckoutContinue", urlPatterns = {"/cart/checkout/continue"})
public class LearnerCheckoutContinue extends HttpServlet {

    private final String VIEW_PATH = "/dashboard/learner/checkout.jsp";
    
    private final String ERROR_404_PATH = "/error/404.jsp";
    
    private final String ERROR_403_PATH = "/error/403.jsp";

    private final OrderService orderService = new OrderServiceImpl();
    
    private final AuthService authService = new AuthServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher(ERROR_404_PATH).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        User user = authService.getUser(request);
        String orderId = request.getParameter("order-id");
        if (user == null) {
            request.getRequestDispatcher(ERROR_403_PATH).forward(request, response);
        }

        Order createdOrder = (Order) request.getAttribute("order");     //Continue unpaid order
        if (orderId != null) {
            createdOrder = orderService.getOrderById(Integer.parseInt(orderId));
        }

        if (createdOrder != null && createdOrder.getStatus().equals(OrderStatus.UNPAID)) {
            double subTotal = 0;
            double grandTotal = 0;

            List<OrderItem> orderItems = orderService.getOrderItemsByOrderId(createdOrder.getId());
            for (OrderItem orderItem : orderItems) {
                subTotal += orderItem.getOriginalPrice();
                grandTotal += orderItem.getPrice();
            }

            List<String> messageError = new ArrayList<>();
            messageError.add("You have unpaid order, please continue or cancel!");

            request.setAttribute("order", createdOrder);
            request.setAttribute("orderItems", orderItems);
            request.setAttribute("subTotal", subTotal);
            request.setAttribute("discount", subTotal - grandTotal);
            request.setAttribute("grandTotal", grandTotal);
            request.setAttribute("noNeedPayment", grandTotal == 0);
            request.setAttribute("messageError", messageError);

            request.getRequestDispatcher(VIEW_PATH).forward(request, response);
        } else {
            request.getRequestDispatcher(ERROR_404_PATH).forward(request, response);
        }

    }

}
