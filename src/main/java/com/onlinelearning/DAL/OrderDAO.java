package com.onlinelearning.DAL;

import com.onlinelearning.Models.Order;

/**
 * @author duy20
 */
public interface OrderDAO {

    Order createOrder(Order newOrder);

    Order getOrderById(Integer orderId);
    
    Order deleteOrder(Order deleteOrder);
}
