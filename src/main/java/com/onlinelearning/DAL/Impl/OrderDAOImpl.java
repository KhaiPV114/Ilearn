package com.onlinelearning.DAL.Impl;

import com.onlinelearning.DAL.DBContext;
import com.onlinelearning.DAL.OrderDAO;
import com.onlinelearning.Enums.OrderStatus;
import com.onlinelearning.Models.Order;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrderDAOImpl implements OrderDAO {

    private final DBContext dbContext = new DBContextImpl();
    private final String TABLE_NAME = "orders";

    @Override
    public Order createOrder(Order newOrder) {
        String sql = "insert into " + TABLE_NAME
                + "(user_id, created_at, status)"
                + " values (?, ?, ?)";
        try ( Connection cn = dbContext.getConnection();  PreparedStatement ps = cn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, newOrder.getUserId());
            ps.setTimestamp(2, Timestamp.valueOf(newOrder.getCreatedAt()));
            ps.setString(3, OrderStatus.NEW.toString());
            int affectedRow = ps.executeUpdate();
            if (affectedRow > 0) {
                try ( ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        newOrder.setId(rs.getInt(1));
                        return newOrder;
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Order getOrderById(Integer orderId) {
        String sql = "select user_id, created_at, status"
                + " from " + TABLE_NAME
                + " where order_id = ?";
        try ( Connection cn = dbContext.getConnection();  PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, orderId);
            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Order order = Order.builder()
                            .id(orderId)
                            .userId(rs.getInt("user_id"))
                            .createdAt(rs.getTimestamp("created_at").toLocalDateTime())
                            .status(OrderStatus.valueOf(rs.getString("status")))
                            .build();
                    return order;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Order updateOrder(Order newOrder) {
        String sql = "update " + TABLE_NAME
                + " set status = ?"
                + " where order_id = ? and user_id = ?";
        try ( Connection cn = dbContext.getConnection();  PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, newOrder.getStatus().toString());
            ps.setInt(2, newOrder.getId());
            ps.setInt(3, newOrder.getUserId());
            int affectedRow = ps.executeUpdate();
            if (affectedRow > 0) {
                return newOrder;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Order deleteOrder(Order deleteOrder) {
        String sql = "delete from " + TABLE_NAME
                + " where order_id = ?";
        try ( Connection cn = dbContext.getConnection();  PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, deleteOrder.getId());
            int affectedRow = ps.executeUpdate();
            if (affectedRow > 0) {
                return deleteOrder;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
