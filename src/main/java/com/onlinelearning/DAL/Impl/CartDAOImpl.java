package com.onlinelearning.DAL.Impl;

import com.onlinelearning.DAL.CartDAO;
import com.onlinelearning.DAL.DBContext;
import com.onlinelearning.Models.CartItem;
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
    private final String CART_TABLE = "carts";

    private CartItem cartResultSetMapper(ResultSet rs) throws SQLException {
        CartItem cartItem = CartItem.builder()
                .userId(rs.getInt("user_id"))
                .courseId(rs.getInt("course_id"))
                .build();
        return cartItem;
    }

    @Override
    public List<CartItem> getCartByUserId(Integer userId) {
        String sql = "select *"
                + " from " + CART_TABLE
                + " where user_id = ?";
        try ( Connection cn = dbContext.getConnection();  PreparedStatement ps = cn.prepareStatement(sql);) {
            ps.setInt(1, userId);
            try ( ResultSet rs = ps.executeQuery()) {
                List<CartItem> cart = new ArrayList<>();
                while (rs.next()) {
                    cart.add(cartResultSetMapper(rs));
                }
                return cart;
            }
        } catch (Exception ex) {
            Logger.getLogger(CategoryDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public boolean deleteCartOfUserId(Integer userId) {
        String sql = "delete from " + CART_TABLE
                + " where user_id = ?";
        try ( Connection cn = dbContext.getConnection();  PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            int affectedRow = ps.executeUpdate();
            if (affectedRow > 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public CartItem getCartItem(Integer userId, Integer courseId) {
        String sql = "select *"
                + " from " + CART_TABLE
                + " where user_id = ? and course_id = ?";
        try ( Connection cn = dbContext.getConnection();  PreparedStatement ps = cn.prepareStatement(sql);) {
            ps.setInt(1, userId);
            ps.setInt(2, courseId);
            try ( ResultSet rs = ps.executeQuery();) {
                if (rs.next()) {
                    return cartResultSetMapper(rs);
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(CategoryDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public CartItem createCartItem(CartItem cartItem) {
        String sql = "insert into " + CART_TABLE
                + "(user_id, course_id)"
                + " values (?, ?)";
        try ( Connection cn = dbContext.getConnection();  PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, cartItem.getUserId());
            ps.setInt(2, cartItem.getCourseId());
            int affectedRow = ps.executeUpdate();
            if (affectedRow > 0) {
                return cartItem;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public CartItem deleteCartItem(CartItem cartItem) {
        String sql = "delete from " + CART_TABLE
                + " where user_id = ? and course_id = ?";
        try ( Connection cn = dbContext.getConnection();  PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, cartItem.getUserId());
            ps.setInt(2, cartItem.getCourseId());
            int affectedRow = ps.executeUpdate();
            if (affectedRow > 0) {
                return cartItem;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
