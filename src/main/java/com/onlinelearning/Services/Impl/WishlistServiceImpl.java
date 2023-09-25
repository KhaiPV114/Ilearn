package com.onlinelearning.Services.Impl;

import com.onlinelearning.DAL.WishlistDAO;
import com.onlinelearning.DAL.Impl.WishlistDAOImpl;
import com.onlinelearning.Models.Course;
import com.onlinelearning.Services.WishlistService;

import java.util.List;

public class WishlistServiceImpl implements WishlistService {

    private final WishlistDAO wishlistDAO = new WishlistDAOImpl();

    @Override
    public Course getCourseById(Integer id) {
        if (id == null) {
            return null;
        }
        return wishlistDAO.getCourseById(id);
    }

    @Override
    public List<Course> getWishlistByUserId(Integer userId) {
        if (userId == null) {
            return null;
        }
        return wishlistDAO.getWishlistByUserId(userId);
    }

    @Override
    public boolean addToWishlist(Integer userId, Course course) throws Exception {
        if (userId == null || course == null) {
            throw new Exception("Add to wishlist failed!");
        }
        
        return wishlistDAO.addToWishlist(userId, course);
    }

    @Override
    public boolean removeFromWishlist(Integer userId, Integer courseId) throws Exception {
        if (userId == null || courseId == null) {
            throw new Exception("Remove from wishlist failed!");
        }
        
        return wishlistDAO.removeFromWishlist(userId, courseId);
    }
}
