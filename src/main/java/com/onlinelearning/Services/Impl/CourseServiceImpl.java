package com.onlinelearning.Services.Impl;

import com.onlinelearning.DAL.CourseDAO;
import com.onlinelearning.DAL.Impl.CourseDAOImpl;
import com.onlinelearning.Enums.CourseStatus;
import com.onlinelearning.Models.Course;
import com.onlinelearning.Services.CourseService;
import com.onlinelearning.Utils.Constants;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        Course updatedCourse = courseDAO.updateCourse(course);
        if (updatedCourse == null) {
            throw new Exception("Update course failed!");
        }
        return updatedCourse;
    }

    @Override
    public List<Course> getAllCoursesByUserId(Integer userId) {
        if (userId == null) {
            return null;
        }

        // return
        return courseDAO.getEnrolledCourseOfUserId(userId);
    }

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

    @Override
    public List<Course> getCourseByCategory(String category) {
        List<Course> courses = courseDAO.getCourseByCategory(category);
        return courses;
    }

    @Override
    public Course validateCourse(Integer courseId) throws Exception {
        if (courseId != null) {
            Course course = courseDAO.getCourseById(courseId);
            if (course != null) {
                if (course.getStatus().equals(CourseStatus.PUBLISHED)) {
                    return course;
                } else {
                    throw new Exception("Course not available");
                }
            }
        }
        throw new Exception("Invalid course");
    }

    @Override
    public Boolean isEnrolled(Integer userId, Integer courseId) {
        if (userId == null || courseId == null) {
            return false;
        }
        return courseDAO.isEnrolled(userId, courseId);
    }

    @Override
    public List<Course> getCourseByCategoryId(Integer categoryId) {

        if (categoryId == null) {
            return null;
        }

        List<Course> courses = courseDAO.getCourseByCategoryId(categoryId);

        if (courses == null) {
            return null;
        }

        return courses;
    }

    @Override
    public void getUserEnrollCourse(Integer userId, Integer courseId) throws Exception {
        if (userId == null || courseId == null) {
            throw new Exception("User or Course is invalid");
        }
        if (isEnrolled(userId, courseId)) {
            throw new Exception("User " + userId + " have enrolled this course" + courseId);
        }
        if (courseDAO.getUserEnrollCourse(userId, courseId)) {
            //Success
        } else {
            throw new Exception("Get error while get user " + userId + " enrolled course " + courseId);
        }
    }

    @Override
    public List<Course> get3CourseByNumberOfPurchase() {
        List<Course> courses = courseDAO.get3CourseByNumberOfPurchase();
        return courses;
    }

    @Override
    public List<Course> getCourseByOwnerId(Integer ownerId) {
        return courseDAO.getCourseByOwnerId(ownerId);
    }

    @Override
    public Integer getTotalLearnerOfAllCourse(Integer ownerId) {
        return courseDAO.getTotalLearnerOfAllCourse(ownerId);
    }

    @Override
    public Map<String, List<Double>> getTotalProfit(Integer ownerId) {
        return courseDAO.getTotalProfit(ownerId);
    }

}
