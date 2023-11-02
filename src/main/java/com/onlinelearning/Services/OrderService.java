package com.onlinelearning.Services;

import com.onlinelearning.Models.Order;
import com.onlinelearning.Models.OrderItem;
import java.util.List;

public interface OrderService {
    Order getOrderById(Integer orderId);
    
    List<Order> getAllOrdersByUserId(Integer userId);
    
    List<OrderItem> getOrderItemsByOrderId(Integer orderId);
    
    Order getUnpaidOrderByUserId(Integer userId);
    
    List<Order> getAllOrders();
    
    Order createOrder(Order newOrder);
    
    Order updateOrder(Order newOrder);
    
    Order deleteOrder(Integer orderId) throws Exception;
    
    void getUserEnrollCourseByOrderId(Integer courseId) throws Exception;
}
