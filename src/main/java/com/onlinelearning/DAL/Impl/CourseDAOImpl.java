package com.onlinelearning.DAL.Impl;

import com.onlinelearning.DAL.CourseDAO;
import com.onlinelearning.DAL.DBContext;
import com.onlinelearning.Models.Course;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CourseDAOImpl implements CourseDAO {

    private final DBContext dbContext = new DBContextImpl();

    @Override
    public Course getCourseById(Integer id) {
        String sql = "select course_id, category_id, owner_id,name,image_url, description from courses where course_id = ?";
        try ( Connection cn = dbContext.getConnection();  PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Course course = Course.builder()
                            .id(rs.getInt("course_id"))
                            .categoryId(rs.getInt("category_id"))
                            .ownerId(rs.getInt("owner_id"))
                            .name(rs.getString("name"))
                            .imageUrl(rs.getString("image_url"))
                            .description(rs.getString("description"))
                            .build();
                    return course;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Course getCourseByName(String name) {
        String sql = "select course_id, category_id, owner_id, name, image_url, description from courses where name = ?";
        try ( Connection cn = dbContext.getConnection();  PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, name);
            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Course course = Course.builder()
                            .id(rs.getInt("course_id"))
                            .categoryId(rs.getInt("category_id"))
                            .ownerId(rs.getInt("owner_id"))
                            .name(rs.getString("name"))
                            .imageUrl(rs.getString("image_url"))
                            .description(rs.getString("description"))
                            .build();
                    return course;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Course> getAllCourses() {
        String sql = "select course_id, category_id, owner_id, name, image_url, description from courses";
        try ( Connection cn = dbContext.getConnection();  PreparedStatement ps = cn.prepareStatement(sql);  ResultSet rs = ps.executeQuery()) {
            List<Course> courses = new ArrayList<>();
            while (rs.next()) {
                Course course = Course.builder()
                        .id(rs.getInt("course_id"))
                        .categoryId(rs.getInt("category_id"))
                        .ownerId(rs.getInt("owner_id"))
                        .name(rs.getString("name"))
                        .imageUrl(rs.getString("image_url"))
                        .description(rs.getString("description"))
                        .build();
                courses.add(course);
            }
            return courses;
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Course createCourse(Course course) {
        String sql = "insert into courses(name, image_url, description) values (?, ?, ?)";
        try ( Connection cn = dbContext.getConnection();  PreparedStatement ps = cn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, course.getName());
            ps.setString(2, course.getImageUrl());
            ps.setString(3, course.getDescription());
            int affectedRow = ps.executeUpdate();
            if (affectedRow > 0) {
                try ( ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        course.setId(rs.getInt(1));
                        return course;
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Course updateCourse(Course course) {
        String sql = "insert into courses(name, image_url, description) values (?, ?, ?)";
        try ( Connection cn = dbContext.getConnection();  PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, course.getName());
            ps.setString(2, course.getImageUrl());
            ps.setString(3, course.getDescription());
            int affectedRow = ps.executeUpdate();
            if (affectedRow > 0) {
                return course;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Course deleteCourse(Course course) {
        String sql = "delete from courses where course_id = ?";
        try ( Connection cn = dbContext.getConnection();  PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, course.getId());
            int affectedRow = ps.executeUpdate();
            if (affectedRow > 0) {
                return course;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
