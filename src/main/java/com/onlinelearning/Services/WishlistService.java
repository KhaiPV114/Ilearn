package com.onlinelearning.Services;

import com.onlinelearning.Models.Course;
import java.util.List;

public interface WishlistService {

    Course getCourseById(Integer id);

    List<Course> getWishlistByUserId(Integer userId);

    boolean addToWishlist(Integer userId, Course course) throws Exception;

    boolean removeFromWishlist(Integer userId, Integer courseId) throws Exception;
}
