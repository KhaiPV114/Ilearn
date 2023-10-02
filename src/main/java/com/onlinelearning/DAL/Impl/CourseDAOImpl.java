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

    private Course courseRowMapper(ResultSet rs) throws SQLException {
        Course course = Course.builder()
                .id(rs.getInt("course_id"))
                .categoryId(rs.getInt("category_id"))
                .ownerId(rs.getInt("owner_id"))
                .name(rs.getString("name"))
                .imageUrl(rs.getString("image_url"))
                .description(rs.getString("description"))
                .price(rs.getDouble("price"))
                .build();
        return course;
    }

    @Override
    public Course getCourseById(Integer id) {
        String sql = "select * from courses where course_id = ?";
        try ( Connection cn = dbContext.getConnection();  PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Course course = courseRowMapper(rs);
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
        String sql = "select * from courses where name = ?";
        try ( Connection cn = dbContext.getConnection();  PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, name);
            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Course course = courseRowMapper(rs);
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
        String sql = "select * from courses";
        try ( Connection cn = dbContext.getConnection();  PreparedStatement ps = cn.prepareStatement(sql);  ResultSet rs = ps.executeQuery()) {
            List<Course> courses = new ArrayList<>();
            while (rs.next()) {
                Course course = courseRowMapper(rs);
                courses.add(course);
            }
            return courses;
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Course> getCourseByOwnerIdPaging(Integer ownerId, Integer size, Integer page) {
        String sql = "select * from courses where owner_id = ? order by id offset ? limit ?";
        int offset = size * (page - 1);
        try ( Connection cn = dbContext.getConnection();  PreparedStatement ps = cn.prepareStatement(sql);  ResultSet rs = ps.executeQuery()) {
            ps.setInt(1, ownerId);
            ps.setInt(2, offset);
            ps.setInt(3, size);
            List<Course> courses = new ArrayList<>();
            while (rs.next()) {
                Course course = courseRowMapper(rs);
                courses.add(course);
            }
            return courses;
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Integer countNumberOfCourseByOwnerId(Integer ownerId) {
        String sql = "select count(*) as total where owner_id = ?";
        try ( Connection cn = dbContext.getConnection();  PreparedStatement ps = cn.prepareStatement(sql);) {
            ps.setInt(1, ownerId);
            try ( ResultSet rs = ps.executeQuery();) {
                if (rs.next()) {
                    return rs.getInt("total");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    @Override
    public Course createCourse(Course course) {
        String sql = "insert into courses(category_id, owner_id, name, image_url, description, price) values (?, ?, ?, ?, ?, ?)";
        try ( Connection cn = dbContext.getConnection();  PreparedStatement ps = cn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, course.getCategoryId());
            ps.setInt(2, course.getOwnerId());
            ps.setString(3, course.getName());
            ps.setString(4, course.getImageUrl());
            ps.setString(5, course.getDescription());
            ps.setDouble(6, course.getPrice());
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
        String sql = "update courses"
                + " set category_id = ?, owner_id = ?, name = ?, image_url = ?, description = ? "
                + " where course_id = ?";
        try ( Connection cn = dbContext.getConnection();  PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, course.getCategoryId());
            ps.setInt(2, course.getOwnerId());
            ps.setString(3, course.getName());
            ps.setString(4, course.getImageUrl());
            ps.setString(5, course.getDescription());
            ps.setDouble(6, course.getPrice());
            ps.setInt(7, course.getId());
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
