package com.onlinelearning.DAL;

import com.onlinelearning.Models.Course;
import java.util.List;

public interface CourseDAO {

    Course getCourseById(Integer id);

    Course getCourseByName(String name);

    List<Course> getAllCourses();

    Course createCourse(Course course);

    Course updateCourse(Course course);

    Course deleteCourse(Course course);

}
