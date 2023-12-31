package com.onlinelearning.DAL.Impl;

import com.onlinelearning.DAL.CourseDAO;
import com.onlinelearning.DAL.DBContext;
import com.onlinelearning.Enums.CourseStatus;
import com.onlinelearning.Models.Course;
import com.onlinelearning.Models.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CourseDAOImpl implements CourseDAO {

    private final DBContext dbContext = new DBContextImpl();
    private final String COURSE_TABLE = "courses";

    private Course courseResultSetMapper(ResultSet rs) throws SQLException {
        CourseStatus status = null;
        if (rs.getString("status") != null) {
            status = CourseStatus.valueOf(rs.getString("status"));
        }
        Course course = Course.builder()
                .id(rs.getInt("course_id"))
                .categoryId(rs.getInt("category_id"))
                .ownerId(rs.getInt("owner_id"))
                .name(rs.getString("name"))
                .imageUrl(rs.getString("image_url"))
                .description(rs.getString("description"))
                .price(rs.getDouble("price"))
                .status(status)
                .build();
        return course;
    }

    @Override
    public Course getCourseById(Integer id) {
        String sql = "select *"
                + " from " + COURSE_TABLE
                + " where course_id = ?";
        try ( Connection cn = dbContext.getConnection();  PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return courseResultSetMapper(rs);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Course getCourseByName(String name) {
        String sql = "select *"
                + " from " + COURSE_TABLE
                + " where name = ?";
        try ( Connection cn = dbContext.getConnection();  PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, name);
            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return courseResultSetMapper(rs);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Course> getAllCourses() {
        String sql = "select * from " + COURSE_TABLE;
        try ( Connection cn = dbContext.getConnection();  PreparedStatement ps = cn.prepareStatement(sql);  ResultSet rs = ps.executeQuery()) {
            List<Course> courses = new ArrayList<>();
            while (rs.next()) {
                courses.add(courseResultSetMapper(rs));
            }
            return courses;
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Course> getCourseByOwnerIdPaging(Integer ownerId, Integer size, Integer page) {
        String sql = "select *"
                + " from " + COURSE_TABLE
                + " where owner_id = ?"
                + " order by course_id"
                + " limit ? offset ?";
        int offset = size * (page - 1);
        try ( Connection cn = dbContext.getConnection();  PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, ownerId);
            ps.setInt(2, size);
            ps.setInt(3, offset);
            List<Course> courses = new ArrayList<>();
            try ( ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    courses.add(courseResultSetMapper(rs));
                }
            }
            return courses;
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Integer countNumberOfCourseByOwnerId(Integer ownerId) {
        String sql = "select count(*) as total"
                + " from " + COURSE_TABLE
                + " where owner_id = ?";
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
        String sql = "insert into " + COURSE_TABLE
                + "(category_id, owner_id, name, image_url, description, price)"
                + " values (?, ?, ?, ?, ?, ?)";
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
        String sql = "update " + COURSE_TABLE
                + " set name = ?, image_url = ?, description = ?, price = ?"
                + " where course_id = ?";
        try ( Connection cn = dbContext.getConnection();  PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, course.getName());
            ps.setString(2, course.getImageUrl());
            ps.setString(3, course.getDescription());
            ps.setDouble(4, course.getPrice());
            ps.setInt(5, course.getId());
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
        String sql = "delete from " + COURSE_TABLE
                + " where course_id = ?";
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

    @Override
    public List<Course> getEnrolledCourseOfUserId(Integer userId) {
        String sql = "select c.* from " + COURSE_TABLE + " c"
                + " JOIN users_courses uc ON c.course_id = uc.course_id "
                + " where uc.user_id = ?";
        try ( Connection cn = dbContext.getConnection();  PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            try ( ResultSet rs = ps.executeQuery()) {
                List<Course> courses = new ArrayList<>();
                while (rs.next()) {
                    courses.add(courseResultSetMapper(rs));
                }
                return courses;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Course> getCourseByKeyword(String keyword) {
        String sql = "select *"
                + " from " + COURSE_TABLE
                + " where name LIKE CONCAT('%', ?, '%')";
        try ( Connection cn = dbContext.getConnection();  PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, keyword);
            try ( ResultSet rs = ps.executeQuery()) {
                List<Course> courses = new ArrayList<>();
                while (rs.next()) {
                    courses.add(courseResultSetMapper(rs));
                }
                return courses;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }


    @Override
    public Boolean isEnrolled(Integer userId, Integer courseId) {
        String sql = "select * from users_courses"
                + " where user_id = ? and course_id = ?";
        try ( Connection cn = dbContext.getConnection();  PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.setInt(2, courseId);
            try ( ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Boolean getUserEnrollCourse(Integer userId, Integer courseId) {
        String sql = "insert into users_courses"
                + "(user_id, course_id, joined_time)"
                + " values(?, ?, ?)";
        try ( Connection cn = dbContext.getConnection();  PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.setInt(2, courseId);
            ps.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
            int affectedRow = ps.executeUpdate();
            if (affectedRow > 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }


    @Override
    public List<Course> getAllCourseOrderByPriceDesc() {
        String sql = "select *"
                + " from " + COURSE_TABLE
                + " order by price desc";
        try ( Connection cn = dbContext.getConnection();  PreparedStatement ps = cn.prepareStatement(sql)) {
            try ( ResultSet rs = ps.executeQuery()) {
                List<Course> courses = new ArrayList<>();
                while (rs.next()) {
                    courses.add(courseResultSetMapper(rs));
                }
                return courses;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Course> getAllCourseOrderByPriceAsc() {
        String sql = "select *"
                + " from " + COURSE_TABLE
                + " order by price asc";
        try ( Connection cn = dbContext.getConnection();  PreparedStatement ps = cn.prepareStatement(sql)) {
            try ( ResultSet rs = ps.executeQuery()) {
                List<Course> courses = new ArrayList<>();
                while (rs.next()) {
                    courses.add(courseResultSetMapper(rs));
                }
                return courses;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Course> getCourseByCategory(String category) {
        String sql = "select *"
                + " from " + COURSE_TABLE + " c"
                + " join categories ca on c.category_id = ca.category_id"
                + " where ca.name = ?";
        try ( Connection cn = dbContext.getConnection();  PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, category);
            try ( ResultSet rs = ps.executeQuery()) {
                List<Course> courses = new ArrayList<>();
                while (rs.next()) {
                    courses.add(courseResultSetMapper(rs));
                }
                return courses;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Course> getCourseByCategoryId(Integer categoryId) {
        String sql = "select *"
                + " from " + COURSE_TABLE
                + " where category_id = ?";
        try ( Connection cn = dbContext.getConnection();  PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, categoryId);
            try ( ResultSet rs = ps.executeQuery()) {
                List<Course> courses = new ArrayList<>();
                while (rs.next()) {
                    courses.add(courseResultSetMapper(rs));
                }
                return courses;
            }
        } catch (SQLException ex) {
            return null;
        }
    }

    @Override
    public List<Course> get3CourseByNumberOfPurchase() {
        String sql = "SELECT course_id, category_id, owner_id, name, image_url, description, price, status FROM courses JOIN (SELECT course_id AS most_common_value, COUNT(*) AS count FROM order_item GROUP BY course_id ORDER BY count DESC LIMIT 3 ) AS subquery ON course_id = subquery.most_common_value;";
        try ( Connection cn = dbContext.getConnection();  PreparedStatement ps = cn.prepareStatement(sql);  ResultSet rs = ps.executeQuery()) {
            List<Course> courses = new ArrayList<>();
            while (rs.next()) {
                courses.add(courseResultSetMapper(rs));
            }
            return courses;
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Course> getCourseByOwnerId(Integer ownerId) {
        String sql = "select * from courses where owner_id = ? ";
        try ( Connection cn = dbContext.getConnection();  PreparedStatement ps = cn.prepareStatement(sql);) {
            ps.setInt(1, ownerId);
            ResultSet rs = ps.executeQuery();
            List<Course> courses = new ArrayList<>();
            while (rs.next()) {
                Course course = courseResultSetMapper(rs);
                courses.add(course);
            }
            return courses;
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Integer getTotalLearnerOfAllCourse(Integer ownerId) {
        String sql = "select count( distinct uc.user_id ) as 'totalLearner' from courses c join users_courses uc on c.course_id = uc.course_id where c.owner_id = ?";
        try ( Connection cn = dbContext.getConnection();  PreparedStatement ps = cn.prepareStatement(sql);) {
            ps.setInt(1, ownerId);
            try ( ResultSet rs = ps.executeQuery();) {
                if (rs.next()) {
                    return rs.getInt("totalLearner");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    public List<Course> findAll(String sqlQuery) {
        List<Course> courses = new ArrayList<>();
        
        String sql = sqlQuery;
        try ( Connection cn = dbContext.getConnection();  PreparedStatement ps = cn.prepareStatement(sql)) {
            try ( ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                Course course = courseResultSetMapper(rs);
                courses.add(course);
            }
            }
            return courses;
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
    
    
    
    public static void main(String[] args) {
        CourseDAO courseDAO = new CourseDAOImpl();
        List<Course> course = courseDAO.getCourseByCategory("James");
        System.out.println(course);
    }

    @Override
    public Map<String, List<Double>> getTotalProfit(Integer ownerId) {
        Map<String, List<Double>> map = new HashMap<>();
        List<Double> totalCourse = new ArrayList<>();
        List<Double> totalPrice = new ArrayList<>();
        String sql = "select c.course_id , count(c.course_id) as 'totalCourse'  ,  count(c.course_id) * c.price as 'totalPrice' from courses c join users_courses uc on c.course_id = uc.course_id where owner_id = ? \n"
                + "group by c.course_id";
        try ( Connection cn = dbContext.getConnection();  PreparedStatement ps = cn.prepareStatement(sql);) {
            ps.setInt(1, ownerId);
            try ( ResultSet rs = ps.executeQuery();) {
                while (rs.next()) {
                    totalCourse.add(Double.valueOf(rs.getInt("totalCourse")));
                    totalPrice.add(rs.getDouble("totalPrice"));
                }
                map.put("totalCourse", totalCourse);
                map.put("totalPrice", totalPrice);
                return map;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Integer> getAllEnrolledCourseId(int id) {
        List<Integer> coursesId = new ArrayList<>();
        
        String sql = "select uc.course_id as 'id' from users_courses uc "
                + "join users u on u.user_id = uc.user_id "
                + "where u.user_id = ?";
        try ( Connection cn = dbContext.getConnection();  PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try ( ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                coursesId.add(rs.getInt("id"));
            }
            }
            return coursesId;
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    
}
