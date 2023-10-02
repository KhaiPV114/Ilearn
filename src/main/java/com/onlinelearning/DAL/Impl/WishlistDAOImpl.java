package com.onlinelearning.DAL.Impl;

import com.onlinelearning.DAL.DBContext;
import com.onlinelearning.DAL.WishlistDAO;
import com.onlinelearning.Models.WishlistItem;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WishlistDAOImpl implements WishlistDAO {

    private final DBContext dbContext = new DBContextImpl();
    private final String TABLE_NAME = "wishlists";

    @Override
    public List<WishlistItem> getWishlistByUserId(Integer userId) {
        String sql = "select user_id, course_id"
                + " from " + TABLE_NAME
                + " where user_id = ?";
        try ( Connection cn = dbContext.getConnection();  PreparedStatement ps = cn.prepareStatement(sql);) {
            ps.setInt(1, userId);
            try ( ResultSet rs = ps.executeQuery()) {
                List<WishlistItem> wishlist = new ArrayList<>();
                while (rs.next()) {
                    WishlistItem wishlistItem = WishlistItem.builder()
                            .userId(rs.getInt("user_id"))
                            .courseId(rs.getInt("course_id"))
                            .build();
                    wishlist.add(wishlistItem);
                }
                return wishlist;
            }
        } catch (Exception ex) {
            Logger.getLogger(CategoryDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public WishlistItem getWishlistByUserIdAndCourseId(Integer userId, Integer courseId) {
        String sql = "select user_id, course_id"
                + " from " + TABLE_NAME
                + " where user_id = ? and course_id = ?";
        try ( Connection cn = dbContext.getConnection();  PreparedStatement ps = cn.prepareStatement(sql);) {
            ps.setInt(1, userId);
            ps.setInt(2, courseId);
            try ( ResultSet rs = ps.executeQuery();) {
                if (rs.next()) {
                    return WishlistItem.builder()
                            .userId(rs.getInt("user_id"))
                            .courseId(rs.getInt("course_id"))
                            .build();
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(CategoryDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public WishlistItem createWishlistItem(WishlistItem wishlistItem) {
        String sql = "insert into " + TABLE_NAME
                + "(user_id, course_id)"
                + " values (?, ?)";
        try ( Connection cn = dbContext.getConnection();  PreparedStatement ps = cn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, wishlistItem.getUserId());
            ps.setInt(2, wishlistItem.getCourseId());
            int affectedRow = ps.executeUpdate();
            if (affectedRow > 0) {
                try ( ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        return WishlistItem.builder()
                                .userId(rs.getInt("user_id"))
                                .courseId(rs.getInt("course_id"))
                                .build();
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public WishlistItem deleteWishlistItem(WishlistItem wishlistItem) {
        String sql = "delete from " + TABLE_NAME
                + " where user_id = ? and course_id = ?";
        try ( Connection cn = dbContext.getConnection();  PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, wishlistItem.getUserId());
            ps.setInt(2, wishlistItem.getCourseId());
            int affectedRow = ps.executeUpdate();
            if (affectedRow > 0) {
                return wishlistItem;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
