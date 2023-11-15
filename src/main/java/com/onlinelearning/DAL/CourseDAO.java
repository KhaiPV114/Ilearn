package com.onlinelearning.DAL;

import com.onlinelearning.Models.Course;
import com.onlinelearning.Models.User;
import java.util.List;
import java.util.Map;

public interface CourseDAO {

    Course getCourseById(Integer courseId);

    Course getCourseByName(String courseName);

    List<Course> getAllCourses();

    List<Course> getCourseByOwnerIdPaging(Integer ownerId, Integer size, Integer page);

    Integer countNumberOfCourseByOwnerId(Integer ownerId);

    Course createCourse(Course course);

    Course updateCourse(Course course);

    Course deleteCourse(Course course);
    
    List<Course> getEnrolledCourseOfUserId(Integer userId);
    
    List<Course> getAllCourseOrderByPriceDesc();
    
    List<Course> getAllCourseOrderByPriceAsc();
    
    List<Course> get3CourseByNumberOfPurchase();
    
    Boolean isEnrolled(Integer userId, Integer courseId);
    
    Boolean getUserEnrollCourse(Integer userId, Integer courseId);

    List<Course> getCourseByKeyword(String keyword);
    
    List<Course> getCourseByCategory(String category);
    
    List<Course> getCourseByCategoryId(Integer categoryId);
    
    List<Course> getCourseByOwnerId(Integer ownerId);
    
    Integer getTotalLearnerOfAllCourse(Integer ownerId);
    
    Map<String,List<Double>> getTotalProfit(Integer ownerId);
    
    List<Course> findAll(String sqlQuery);
}
