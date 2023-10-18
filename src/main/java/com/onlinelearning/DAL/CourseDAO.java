package com.onlinelearning.DAL;

import com.onlinelearning.Models.Course;
import java.util.List;

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
    
    Boolean isEnrolled(Integer userId, Integer courseId);
    
    Boolean getUserEnrollCourse(Integer userId, Integer courseId);

    List<Course> getCourseByKeyword(String keyword);
    
    List<Course> getCourseByKeywordOrderByPriceDesc(String keyword);
    
    List<Course> getCourseByKeywordOrderByPriceAsc(String keyword);
    
    List<Course> getCourseByCategory(String category);
    
    List<Course> getCourseByCategoryId(Integer categoryId);
}
