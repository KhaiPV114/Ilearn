package com.onlinelearning.Services;

import com.onlinelearning.Models.Course;
import java.util.List;

public interface CourseService {

    Course getCourseById(Integer id);

    List<Course> getAllCourses();

    List<Course> getCourseByOwnerId(Integer ownerId, Integer size, Integer page);

    Integer countNumberOfCourseByOwnerId(Integer ownerId, Integer size);

    Course createCourse(Course course) throws Exception;

    Course updateCourse(Course course) throws Exception;

    Course deleteCourse(Course course) throws Exception;
    
    List<Course> getAllCoursesByUserId(Integer userId);
    
    List<Course> getCourseByCategoryId(Integer categoryId);

}
