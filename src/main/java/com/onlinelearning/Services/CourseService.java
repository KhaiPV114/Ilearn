package com.onlinelearning.Services;

import com.onlinelearning.Models.Course;
import java.util.List;
import java.util.Map;

public interface CourseService {

    Course getCourseById(Integer id);

    List<Course> getAllCourses();

    List<Course> getCourseByOwnerId(Integer ownerId, Integer size, Integer page);

    Integer countNumberOfCourseByOwnerId(Integer ownerId, Integer size);

    Course createCourse(Course course) throws Exception;

    Course updateCourse(Course course) throws Exception;

    Course deleteCourse(Course course) throws Exception;

    List<Course> getAllCoursesByUserId(Integer userId);

    List<Course> getAllCourseOrderByPriceDesc();

    List<Course> getAllCourseOrderByPriceAsc();
    
    List<Course> get3CourseByNumberOfPurchase();
 
    Course validateCourse(Integer courseId) throws Exception;

    Boolean isEnrolled(Integer userId, Integer courseId);
    
    List<Course> getCourseByCategoryId(Integer categoryId);

    void getUserEnrollCourse(Integer userId, Integer courseId) throws Exception;

    List<Course> getCourseByOwnerId(Integer ownerId);
    
    Integer getTotalLearnerOfAllCourse(Integer ownerId);
    public Map<String, List<Double>> getTotalProfit(Integer ownerId);
    

    List<Course> getCourseByKeyword(String keyword);
    
    
    List<Course> getCourseByCategory(String category);
    
    List<Course> findAll(String sqlQuery);
    
    List<Integer> getAllEnrolledCourseId(int id); 
}
