package com.onlinelearning.DAL.Impl;

import com.onlinelearning.DAL.WishlistDAO;
import com.onlinelearning.DAL.DBContext;
import com.onlinelearning.Models.Wishlist;

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

    @Override
    public Wishlist getWishlistByUserId(Integer userId) {
        String sql = "SELECT wishlist_id, user_id, course_id FROM wishlists WHERE user_id = ?";
        try (Connection cn = dbContext.getConnection(); PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, userId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Wishlist wishlist = Wishlist.builder()
                    .id(rs.getInt("wishlist_id"))
                    .id(rs.getInt("user_id"))
                    .id(rs.getInt("course_id"))
                    .build();
                    return wishlist;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(WishlistDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Wishlist> getAllWishlists() {
        String sql = "SELECT wishlist_id, user_id, course_id FROM wishlists";
        try (Connection cn = dbContext.getConnection(); PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            List<Wishlist> wishlists = new ArrayList<>();
            while (rs.next()) {
                Wishlist wishlist = Wishlist.builder()
                .id(rs.getInt("wishlist_id"))
                .id(rs.getInt("user_id"))
                .id(rs.getInt("course_id"))
                .build();
                wishlists.add(wishlist);
            }
            return wishlists;
        } catch (SQLException ex) {
            Logger.getLogger(WishlistDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Wishlist addWishlist(Wishlist wishlist) {
        String sql = "INSERT INTO wishlists(user_id, course_id) VALUES (?, ?) WHERE wishlist_id = ?";
        try (Connection cn = dbContext.getConnection(); PreparedStatement ps = cn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, wishlist.getUserId());
            ps.setInt(2, wishlist.getCourseId());
            int affectedRows = ps.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        wishlist.setId(rs.getInt(1));
                        return wishlist;
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(WishlistDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Wishlist deleteWishlist(Wishlist wishlist) {
        String sql = "DELETE FROM wishlists WHERE wishlist_id = ?";
        try (Connection cn = dbContext.getConnection(); PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, wishlist.getId());

            int affectedRows = ps.executeUpdate();
            if (affectedRows > 0) {
                return wishlist;
            }
        } catch (SQLException ex) {
            Logger.getLogger(WishlistDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
