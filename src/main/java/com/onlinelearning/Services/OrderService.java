package com.onlinelearning.Services;

import com.onlinelearning.Models.Order;
import java.util.List;

public interface OrderService {
    Order getOrderById(Integer orderId);
    
    List<Order> getAllOrdersByUserId(Integer userId);
    
    List<Order> getAllOrders();
    
    Order createOrder(Order newOrder);
    
    Order updateOrder(Order newOrder);
    
    Order deleteOrder(Integer orderId) throws Exception;
}
