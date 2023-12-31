package com.onlinelearning.DAL.Impl;

import com.onlinelearning.DAL.DBContext;
import com.onlinelearning.DAL.LessonDAO;
import com.onlinelearning.Enums.LessonStatus;
import com.onlinelearning.Models.Lesson;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LessonDAOImpl implements LessonDAO {

    private final DBContext dbContext = new DBContextImpl();
    private final String LESSON_TABLE = "lessons";

    private Lesson lessonResultSetMapper(ResultSet rs) throws SQLException {
        LessonStatus status = null;
        String statusString = rs.getString("status");
        if (statusString != null) {
            status = LessonStatus.valueOf(statusString);
        }
        Lesson lesson = Lesson.builder()
                .id(rs.getInt("lesson_id"))
                .sectionId(rs.getInt("section_id"))
                .name(rs.getString("name"))
                .status(status)
                .orderNumber(rs.getFloat("order_number"))
                .content(rs.getString("content"))
                .build();
        return lesson;
    }

    @Override
    public Float getNewOrderNumberBySectionId(Integer sectionId) {
        String sql = "select max(order_number) as max_order_number"
                + " from " + LESSON_TABLE
                + " where section_id = ?";
        try ( Connection cn = dbContext.getConnection();  PreparedStatement ps = cn.prepareStatement(sql);) {
            ps.setInt(1, sectionId);
            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Float newOrderNumber = rs.getFloat("max_order_number") + 10.0f;
                    return newOrderNumber;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 10f;
    }

    @Override
    public Lesson createLesson(Lesson lesson) {
        String sql = "insert into " + LESSON_TABLE
                + "(section_id, name, status, order_number, content)"
                + " values (?, ?, ?, ?, ?)";
        try ( Connection cn = dbContext.getConnection();  PreparedStatement ps = cn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, lesson.getSectionId());
            ps.setString(2, lesson.getName());
            if (lesson.getStatus() == null) {
                ps.setNull(3, Types.VARCHAR);
            } else {
                ps.setString(3, lesson.getStatus().toString());
            }
            Float orderNumber = getNewOrderNumberBySectionId(lesson.getSectionId());
            ps.setFloat(4, orderNumber);
            if (lesson.getContent() == null) {
                lesson.setContent("");
            }
            ps.setString(5, lesson.getContent());
            int affectedRow = ps.executeUpdate();
            if (affectedRow > 0) {
                try ( ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        lesson.setId(rs.getInt(1));
                        return lesson;
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Lesson getLesson(Integer id) {
        String sql = "select *"
                + " from " + LESSON_TABLE
                + " where lesson_id = ?";
        try ( Connection cn = dbContext.getConnection();  PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return lessonResultSetMapper(rs);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Lesson> getLessonsBySectionId(Integer sectionId) {
        String sql = "select *"
                + " from " + LESSON_TABLE
                + " where section_id = ? and (status <> 'DELETED' or status is null)"
                + " order by order_number";
        try ( Connection cn = dbContext.getConnection();  PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, sectionId);
            try ( ResultSet rs = ps.executeQuery()) {
                List<Lesson> lessons = new ArrayList<>();
                while (rs.next()) {
                    lessons.add(lessonResultSetMapper(rs));
                }
                return lessons;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Lesson> getLessonsBySectionIdAndStatus(Integer sectionId, LessonStatus status) {
        String sql = "select * from " + LESSON_TABLE + " where section_id = ? and status = ? order by order_number";
        if (status == LessonStatus.ACTIVE) {
            sql = "select * from " + LESSON_TABLE + " where section_id = ? and (status = ? or status is null) order by order_number";
        }
        try ( Connection cn = dbContext.getConnection();  PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, sectionId);
            ps.setString(2, status.toString());
            try ( ResultSet rs = ps.executeQuery()) {
                List<Lesson> lessons = new ArrayList<>();
                while (rs.next()) {
                    lessons.add(lessonResultSetMapper(rs));
                }
                return lessons;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Lesson updateLesson(Lesson lesson) {
        String sql = "update " + LESSON_TABLE
                + " set section_id = ?, name = ?, status = ?, order_number = ?, content = ?"
                + " where lesson_id = ?";
        try ( Connection cn = dbContext.getConnection();  PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, lesson.getSectionId());
            ps.setString(2, lesson.getName());
            if (lesson.getStatus() == null) {
                ps.setNull(3, Types.VARCHAR);
            } else {
                ps.setString(3, lesson.getStatus().toString());
            }
            ps.setFloat(4, lesson.getOrderNumber());
            if (lesson.getContent() == null) {
                lesson.setContent("");
            }
            ps.setString(5, lesson.getContent());
            ps.setInt(6, lesson.getId());
            int affectedRow = ps.executeUpdate();
            if (affectedRow > 0) {
                return lesson;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Lesson deleteLesson(Lesson lesson) {
        String sql = "delete from " + LESSON_TABLE
                + " where lesson_id = ?";
        try ( Connection cn = dbContext.getConnection();  PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, lesson.getId());
            int affectedRow = ps.executeUpdate();
            if (affectedRow > 0) {
                return lesson;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
