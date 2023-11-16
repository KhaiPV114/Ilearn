package com.onlinelearning.DAL;

import com.onlinelearning.Models.OrderItem;
import java.util.List;

/**
 * @author duy20
 */
public interface OrderItemDAO {

    List<OrderItem> getAllOrderItemsByOrderId(Integer orderId);

    OrderItem createOrderItem(OrderItem newOrderItem);

    boolean deleteOrderItem(Integer orderId);
}
