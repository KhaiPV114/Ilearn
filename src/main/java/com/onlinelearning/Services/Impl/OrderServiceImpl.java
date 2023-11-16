package com.onlinelearning.Services.Impl;

import com.onlinelearning.DAL.Impl.OrderDAOImpl;
import com.onlinelearning.DAL.Impl.OrderItemDAOImpl;
import com.onlinelearning.DAL.OrderDAO;
import com.onlinelearning.DAL.OrderItemDAO;
import com.onlinelearning.Enums.OrderStatus;
import com.onlinelearning.Models.Order;
import com.onlinelearning.Models.OrderItem;
import com.onlinelearning.Services.CourseService;
import com.onlinelearning.Services.OrderService;
import java.util.List;

public class OrderServiceImpl implements OrderService {

    private final OrderDAO orderDAO = new OrderDAOImpl();
    private final CourseService CourseService = new CourseServiceImpl();
    private final OrderItemDAO orderItemDAO = new OrderItemDAOImpl();

    @Override
    public Order getOrderById(Integer orderId) {
        if (orderId == null) {
            return null;
        }
        return orderDAO.getOrderById(orderId);
    }

    @Override
    public List<Order> getAllOrdersByUserId(Integer userId) {
        if (userId == null) {
            return null;
        }
        return orderDAO.getAllOrdersOfUserId(userId);
    }

    @Override
    public Order getUnpaidOrderByUserId(Integer userId) {
        if (userId == null) {
            return null;
        }
        return orderDAO.getUnpaidOrderOfUserId(userId);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderDAO.getAllOrders();
    }
    
    @Override
    public List<OrderItem> getOrderItemsByOrderId(Integer orderId) {
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
            throw new Exception("Delete Order Item get error.");
        }
        throw new Exception("Order not found to delete.");
    }

    @Override
    public void getUserEnrollCourseByOrderId(Integer orderId) throws Exception {
        Order order = getOrderById(orderId);
        if (order == null) {
            throw new NullPointerException();
        }
        List<OrderItem> orderItems = getOrderItemsByOrderId(orderId);
        for (OrderItem orderItem : orderItems) {
            try {
                CourseService.getUserEnrollCourse(order.getUserId(), orderItem.getCourseId());
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
    
    @Override
    public List<Order> getPaidOrdersByMonth(Integer month, Integer year) throws Exception{
        if(month == null || year == null){
            throw new Exception("Invalid month and year");
        }
        return orderDAO.getPaidOrdersByMonth(month, year);
    }

    @Override
    public Double getTotalOfOrder(Order order) {
        if(order == null){
            return 0d;
        }
        List<OrderItem> orderItems = orderItemDAO.getAllOrderItemsByOrderId(order.getId());
        Double total = 0d;
        for (OrderItem orderItem : orderItems) {
            total += orderItem.getPrice();
        }
        return total;
    }
}
