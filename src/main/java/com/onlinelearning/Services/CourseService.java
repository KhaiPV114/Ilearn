package com.onlinelearning.Services;

import com.onlinelearning.Models.Course;
import java.util.List;

public interface CourseService {

    Course getCourseById(Integer id);

    List<Course> getAllCourses();

    Course createCourse(Course course) throws Exception;

    Course updateCourse(Course course) throws Exception;

    Course deleteCourse(Course course) throws Exception;
    
}
