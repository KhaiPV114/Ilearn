package com.onlinelearning.DAL.Impl;

import com.onlinelearning.DAL.CartDAO;
import com.onlinelearning.DAL.DBContext;
import com.onlinelearning.Models.Cart;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CartDAOImpl implements CartDAO {

    private final DBContext dbContext = new DBContextImpl();
    private final String TABLE_NAME = "carts";

    @Override
    public List<Cart> getCartsByUserId(Integer userId) {
        String sql = "select cart_id, user_id, course_id"
                + " from " + TABLE_NAME
                + " where user_id = ?";
        try ( Connection cn = dbContext.getConnection();  PreparedStatement ps = cn.prepareStatement(sql);) {
            ps.setInt(1, userId);
            try ( ResultSet rs = ps.executeQuery()) {
                List<Cart> carts = new ArrayList<>();
                while (rs.next()) {
                    Cart cart = Cart.builder()
                            .id(rs.getInt("cart_id"))
                            .userId(rs.getInt("user_id"))
                            .courseId(rs.getInt("course_id"))
                            .build();
                    carts.add(cart);
                }
                return carts;
            }
        } catch (Exception ex) {
            Logger.getLogger(CategoryDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Cart getCartByUserIdAndCourseId(Integer userId, Integer courseId) {
        String sql = "select cart_id, user_id, course_id"
                + " from " + TABLE_NAME
                + " where user_id = ? and course_id = ?";
        try ( Connection cn = dbContext.getConnection();  PreparedStatement ps = cn.prepareStatement(sql);) {
            ps.setInt(1, userId);
            ps.setInt(2, courseId);
            try ( ResultSet rs = ps.executeQuery();) {
                if (rs.next()) {
                    Cart cart = Cart.builder()
                            .id(rs.getInt("cart_id"))
                            .userId(rs.getInt("user_id"))
                            .courseId(rs.getInt("course_id"))
                            .build();
                    return cart;
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(CategoryDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Cart createCart(Cart cart) {
        String sql = "insert into " + TABLE_NAME
                + "(user_id, course_id)"
                + " values (?, ?)";
        try ( Connection cn = dbContext.getConnection();  PreparedStatement ps = cn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, cart.getUserId());
            ps.setInt(2, cart.getCourseId());
            int affectedRow = ps.executeUpdate();
            if (affectedRow > 0) {
                try ( ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        cart.setId(rs.getInt(1));
                        return cart;
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Cart deleteCart(Cart cart) {
        String sql = "delete from " + TABLE_NAME
                + " where cart_id = ?";
        try ( Connection cn = dbContext.getConnection();  PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, cart.getId());
            int affectedRow = ps.executeUpdate();
            if (affectedRow > 0) {
                return cart;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
