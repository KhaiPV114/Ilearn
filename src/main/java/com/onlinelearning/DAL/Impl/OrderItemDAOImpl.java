package com.onlinelearning.DAL.Impl;

import com.onlinelearning.DAL.DBContext;
import com.onlinelearning.DAL.OrderItemDAO;
import com.onlinelearning.Models.OrderItem;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrderItemDAOImpl implements OrderItemDAO {

    private final DBContext dbContext = new DBContextImpl();
    private final String TABLE_NAME = "order_item";

    @Override
    public OrderItem createOrderItem(OrderItem newOrderItem) {
        String sql = "insert into " + TABLE_NAME
                + "(order_id, course_id, coupon_id, original_price, price)"
                + " values (?, ?, ?, ?, ?)";
        try ( Connection cn = dbContext.getConnection();  PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, newOrderItem.getOrderId());
            ps.setInt(2, newOrderItem.getCourseId());
            if (newOrderItem.getCouponId() == null) {
                ps.setNull(3, Types.NULL);
            } else {
                ps.setInt(3, newOrderItem.getCouponId());
            }
            ps.setDouble(4, newOrderItem.getOriginalPrice());
            ps.setDouble(5, newOrderItem.getPrice());
            int affectedRow = ps.executeUpdate();
            if (affectedRow > 0) {
                return newOrderItem;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public boolean deleteOrderItem(Integer orderId) {
        String sql = "delete from " + TABLE_NAME
                + " where order_id = ?";
        try ( Connection cn = dbContext.getConnection();  PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, orderId);
            int affectedRow = ps.executeUpdate();
            if (affectedRow > 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
}
