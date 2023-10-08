package com.onlinelearning.Services.Impl;

import com.onlinelearning.DAL.Impl.OrderDAOImpl;
import com.onlinelearning.DAL.Impl.OrderItemDAOImpl;
import com.onlinelearning.DAL.OrderDAO;
import com.onlinelearning.DAL.OrderItemDAO;
import com.onlinelearning.Models.Order;
import com.onlinelearning.Models.OrderItem;
import com.onlinelearning.Services.OrderService;
import java.util.List;

public class OrderServiceImpl implements OrderService {

    private final OrderDAO orderDAO = new OrderDAOImpl();
    private final OrderItemDAO orderItemDAO = new OrderItemDAOImpl();

    @Override
    public Order getOrderById(Integer orderId) {
        return orderDAO.getOrderById(orderId);
    }

    @Override
    public List<Order> getAllOrdersByUserId(Integer userId) {
        if (userId == null) {
            return null;
        }
        return orderDAO.getAllOrdersByUserId(userId);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderDAO.getAllOrders();
    }

    @Override
    public List<OrderItem> getCoursesOfOrderByOrderId(Integer orderId) {
        return orderItemDAO.getAllOrderItemsByOrderId(orderId);
    }

    @Override
    public Order createOrder(Order newOrder) {
        if (newOrder == null) {
            return null;
        }
        return orderDAO.createOrder(newOrder);
    }

    @Override
    public Order updateOrder(Order newOrder) {
        return orderDAO.updateOrder(newOrder);
    }

    @Override
    public Order deleteOrder(Integer orderId) throws Exception {
        Order deleteOrder = orderDAO.getOrderById(orderId);
        if (deleteOrder != null) {
            if (orderItemDAO.deleteOrderItem(deleteOrder.getId())) {
                return orderDAO.deleteOrder(deleteOrder);
            }
            throw new Exception("Error at deleting order item");
        }
        throw new Exception("Not found order!");
    }

}
