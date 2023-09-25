package com.onlinelearning.DAL.Impl;

import com.onlinelearning.DAL.WishlistDAO;
import com.onlinelearning.DAL.DBContext;
import com.onlinelearning.Models.Course;
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
   public Course getCourseById(Integer id) {
       String sql = "select course_id, name, image_url, description from courses where course_id = ?";
        try ( Connection cn = dbContext.getConnection();  PreparedStatement ps = cn.prepareStatement(sql)) {
           ps.setInt(1, id);
            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Course course = Course.builder()
                           .id(rs.getInt("course_id"))
                           .name(rs.getString("name"))
                           .imageUrl(rs.getString("image_url"))
                           .description(rs.getString("description"))
                           .build();
                    return course;
               }
           }
        } catch (SQLException ex) {
           Logger.getLogger(CategoryDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
       }
       return null;
    }

    @Override
    public List<Course> getWishlistByUserId(Integer userId) {
        String sql = "SELECT course_id, course_name FROM wishlist WHERE user_id = ?";
        try (Connection cn = dbContext.getConnection(); PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                List<Course> wishlist = new ArrayList<>();
                while (rs.next()) {
                    Course course = Course.builder()
                        .id(rs.getInt("course_id"))
                        .name(rs.getString("course_name"))
                        .build();
                        wishlist.add(course);
                }
                return wishlist;
            }
        } catch (SQLException ex) {
            Logger.getLogger(WishlistDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public boolean addToWishlist(Integer userId, Course course) {
        String sql = "INSERT INTO wishlist (user_id, course_id, course_name) VALUES (?, ?, ?)";
        try (Connection cn = dbContext.getConnection(); PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.setInt(2, course.getId());
            ps.setString(3, course.getName());
            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException ex) {
            Logger.getLogger(WishlistDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public boolean removeFromWishlist(Integer userId, Integer courseId) {
        String sql = "DELETE FROM wishlist WHERE user_id = ? AND course_id = ?";
        try (Connection cn = dbContext.getConnection(); PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.setInt(2, courseId);
            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException ex) {
            Logger.getLogger(WishlistDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

}

