package com.onlinelearning.DAL;

import com.onlinelearning.Models.Order;
import java.util.List;

/**
 * @author duy20
 */
public interface OrderDAO {

    Order getOrderById(Integer orderId);

    List<Order> getAllOrdersOfUserId(Integer userId);

    Order getUnpaidOrderOfUserId(Integer userId);

    List<Order> getAllOrders();

    Order createOrder(Order newOrder);

    Order updateOrder(Order newOrder);

    Order deleteOrder(Order deleteOrder);

    List<Order> getPaidOrdersByMonth(int month, int year);
}
