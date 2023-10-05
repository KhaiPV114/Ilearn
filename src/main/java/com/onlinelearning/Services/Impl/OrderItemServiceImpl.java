package com.onlinelearning.Services.Impl;

import com.onlinelearning.DAL.Impl.OrderItemDAOImpl;
import com.onlinelearning.DAL.OrderItemDAO;
import com.onlinelearning.Models.OrderItem;
import com.onlinelearning.Services.OrderItemService;


public class OrderItemServiceImpl implements OrderItemService {
    
    private final OrderItemDAO orderItemDAO = new OrderItemDAOImpl();

    @Override
    public OrderItem createOrderITem(OrderItem orderItem) {
        if (orderItem.getOrderId() == null) {
            return null;
        }
        return orderItemDAO.createOrderItem(orderItem);
    }

}
