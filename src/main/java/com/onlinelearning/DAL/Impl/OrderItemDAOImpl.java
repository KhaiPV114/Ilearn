package com.onlinelearning.DAL.Impl;

import com.onlinelearning.DAL.DBContext;
import com.onlinelearning.DAL.OrderItemDAO;
import com.onlinelearning.Models.OrderItem;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrderItemDAOImpl implements OrderItemDAO {

    private final DBContext dbContext = new DBContextImpl();
    private final String TABLE_NAME = "order_item";

    @Override
    public OrderItem createOrderItem(OrderItem newOrderItem) {
        String sql = "insert into " + TABLE_NAME
                + "(course_id, coupon_id, original_price, price)"
                + " values (?, ?, ?, ?)";
        try ( Connection cn = dbContext.getConnection();  PreparedStatement ps = cn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, newOrderItem.getCourseId());
            ps.setInt(2, newOrderItem.getCouponId());
            ps.setDouble(3, newOrderItem.getOriginalPrice());
            ps.setDouble(4, newOrderItem.getPrice());
            int affectedRow = ps.executeUpdate();
            if (affectedRow > 0) {
                try ( ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        newOrderItem.setId(rs.getInt(1));
                        return newOrderItem;
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
