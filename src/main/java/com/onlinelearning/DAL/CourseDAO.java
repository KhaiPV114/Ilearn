package com.onlinelearning.DAL;

import com.onlinelearning.Models.Course;
import java.util.List;

public interface CourseDAO {

    Course getCourseById(Integer id);

    Course getCourseByName(String name);

    List<Course> getAllCourses();

    List<Course> getCourseByOwnerIdPaging(Integer ownerId, Integer size, Integer page);

    Integer countNumberOfCourseByOwnerId(Integer ownerId);

    Course createCourse(Course course);

    Course updateCourse(Course course);

    Course deleteCourse(Course course);

}
