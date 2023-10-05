package com.onlinelearning.Services;

import com.onlinelearning.Models.Order;

/**
 * @author duy20
 */
public interface OrderService {
    Order createOrder(Order newOrder);
    
    Order deleteOrder(Integer orderId) throws Exception;
}
