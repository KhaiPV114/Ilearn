package com.onlinelearning.DAL;

import com.onlinelearning.Models.Course; 

import java.util.List;

public interface WishlistDAO {

    Course getCourseById(Integer id);

    List<Course> getWishlistByUserId(Integer userId);

    boolean addToWishlist(Integer userId, Course course);

    boolean removeFromWishlist(Integer userId, Integer courseId);
}
