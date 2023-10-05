package com.onlinelearning.DAL;

import com.onlinelearning.Models.Order;

/**
 * @author duy20
 */
public interface OrderDAO {
    
    Order getOrderById(Integer orderId);

    Order createOrder(Order newOrder);
    
    Order updateOrder(Order newOrder);
    
    Order deleteOrder(Order deleteOrder);
}
