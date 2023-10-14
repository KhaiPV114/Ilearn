package com.onlinelearning.Services.Impl;

import com.onlinelearning.DAL.CourseDAO;
import com.onlinelearning.DAL.Impl.CourseDAOImpl;
import com.onlinelearning.Models.Course;
import com.onlinelearning.Services.CourseService;
import com.onlinelearning.Utils.Constants;
import java.util.ArrayList;
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

    @Override
    public List<Course> getCourseByOwnerId(Integer ownerId, Integer size, Integer page) {
        if (ownerId == null) {
            return null;
        }
        if (size == null || size <= 0 || size > Constants.PAGINATION_MAX_PAGE_SIZE) {
            size = Constants.PAGINATION_DEFAULT_PAGE_SIZE;
        }
        if (page == null || page < 0) {
            page = 1;
        }
        return courseDAO.getCourseByOwnerIdPaging(ownerId, size, page);
    }

    @Override
    public Integer countNumberOfCourseByOwnerId(Integer ownerId, Integer size) {
        if (ownerId == null) {
            return 0;
        }
        if (size == null || size <= 0 || size > Constants.PAGINATION_MAX_PAGE_SIZE) {
            size = Constants.PAGINATION_DEFAULT_PAGE_SIZE;
        }
        int rowNumber = courseDAO.countNumberOfCourseByOwnerId(ownerId);
        if (rowNumber == 0) {
            return 0;
        }
        Double totalDouble = Math.ceil(rowNumber * 1.0f / size);
        return totalDouble.intValue();
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

    @Override
    public List<Course> getCourseByKeyword(String keyword) {
        List<Course> courses = courseDAO.getCourseByKeyword(keyword);
        return courses;
    }

    @Override
    public List<Course> getCourseByKeywordOrderByPriceDesc(String keyword) {
        List<Course> courses = courseDAO.getCourseByKeywordOrderByPriceDesc(keyword);
        return courses;
    }

    public List<Course> getCourseByKeywordOrderByPriceAsc(String keyword) {
        List<Course> courses = courseDAO.getCourseByKeywordOrderByPriceAsc(keyword);
        return courses;
    }
    
    public List<Course> getAllCourseOrderByPriceDesc() {
        List<Course> courses = courseDAO.getAllCourseOrderByPriceDesc();
        return courses;
    }

    @Override
    public List<Course> getAllCourseOrderByPriceAsc() {
         List<Course> courses = courseDAO.getAllCourseOrderByPriceAsc();
        return courses;
    }
    
    
    
}
