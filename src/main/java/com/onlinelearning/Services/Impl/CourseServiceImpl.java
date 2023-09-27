package com.onlinelearning.Services.Impl;

import com.onlinelearning.DAL.CourseDAO;
import com.onlinelearning.DAL.Impl.CourseDAOImpl;
import com.onlinelearning.Models.Course;
import com.onlinelearning.Services.CourseService;
import java.util.List;

public class CourseServiceImpl implements CourseService {

    private final CourseDAO courseDAO = new CourseDAOImpl();

    @Override
    public Course getCourseById(Integer id) {
        if (id == null) {
            return null;
        }
        return courseDAO.getCourseById(id);
    }

    @Override
    public List<Course> getAllCourses() {
        return courseDAO.getAllCourses();
    }

    private void validateCourse(Course course) throws Exception {
        if (courseDAO.getCourseByName(course.getName()) != null) {
            throw new Exception("Course name is already existed!");
        }
    }

    @Override
    public Course createCourse(Course course) throws Exception {
        validateCourse(course);
        Course createdCourse = courseDAO.createCourse(course);
        if (createdCourse == null) {
            throw new Exception("Create course failed!");
        }
        return createdCourse;
    }

    @Override
    public Course deleteCourse(Course course) throws Exception {
        if (course.getId() == null) {
            throw new Exception("Course id must not be empty!");
        }
        Course deletedCourse = courseDAO.deleteCourse(course);
        if (deletedCourse == null) {
            throw new Exception("Delete course failed!");
        }
        return deletedCourse;
    }

    @Override
    public Course updateCourse(Course course) throws Exception {
        validateCourse(course);
        Course updatedCourse = courseDAO.updateCourse(course);
        if (updatedCourse == null) {
            throw new Exception("Update course failed!");
        }
        return updatedCourse;
    }

}
