package com.onlinelearning.DAL.Impl;

import com.onlinelearning.DAL.DBContext;
import com.onlinelearning.DAL.TrackingDAO;
import com.onlinelearning.Enums.TrackingStatus;
import com.onlinelearning.Models.Tracking;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TrackingDAOImpl implements TrackingDAO {

    private final DBContext dbContext = new DBContextImpl();
    private final String LESSON_TABLE = "tracking";

    private Tracking trackingResultSetMapper(ResultSet rs) throws SQLException {
        TrackingStatus status = null;
        String statusString = rs.getString("status");
        if (statusString != null) {
            status = TrackingStatus.valueOf(statusString);
        }
        Tracking tracking = Tracking.builder()
                .userId(rs.getInt("user_id"))
                .lessonId(rs.getInt("lesson_id"))
                .status(status)
                .build();
        return tracking;
    }

    @Override
    public Tracking createTracking(Tracking tracking) {
        String sql = "insert into " + LESSON_TABLE
                + "(user_id, lesson_id, status)"
                + " values (?, ?, ?)";
        try ( Connection cn = dbContext.getConnection();  PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, tracking.getUserId());
            ps.setInt(2, tracking.getLessonId());
            if (tracking.getStatus() == null) {
                ps.setNull(3, Types.VARCHAR);
            } else {
                ps.setString(3, tracking.getStatus().toString());
            }
            int affectedRow = ps.executeUpdate();
            if (affectedRow > 0) {
                return tracking;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Tracking getTracking(Integer userId, Integer lessonId) {
        String sql = "select *"
                + " from " + LESSON_TABLE
                + " where user_id = ? and lesson_id = ?";
        try ( Connection cn = dbContext.getConnection();  PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.setInt(2, lessonId);
            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return trackingResultSetMapper(rs);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Tracking updateTracking(Tracking tracking) {
        String sql = "update " + LESSON_TABLE
                + " set status = ?"
                + " where user_id = ? and lesson_id = ?";
        try ( Connection cn = dbContext.getConnection();  PreparedStatement ps = cn.prepareStatement(sql)) {
            if (tracking.getStatus() == null) {
                ps.setNull(1, Types.VARCHAR);
            } else {
                ps.setString(1, tracking.getStatus().toString());
            }
            ps.setInt(2, tracking.getUserId());
            ps.setInt(3, tracking.getLessonId());
            int affectedRow = ps.executeUpdate();
            if (affectedRow > 0) {
                return tracking;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Tracking deleteTracking(Tracking tracking) {
        String sql = "delete from " + LESSON_TABLE
                + " where user_id = ? and lesson_id = ?";
        try ( Connection cn = dbContext.getConnection();  PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, tracking.getUserId());
            ps.setInt(2, tracking.getLessonId());
            int affectedRow = ps.executeUpdate();
            if (affectedRow > 0) {
                return tracking;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Tracking> getTrackingsByUserIdAndCourseId(Integer userId, Integer courseId) {
        String sql = "select tracking.user_id as user_id, tracking.lesson_id as lesson_id, tracking.status as status \n"
                + "from courses \n"
                + "join sections on courses.course_id = sections.course_id and courses.course_id = ? and (sections.status is null or sections.status = 'ACTIVE') \n"
                + "join lessons on sections.section_id = lessons.section_id and (lessons.status is null or lessons.status = 'ACTIVE') \n"
                + "join tracking on lessons.lesson_id = tracking.lesson_id and tracking.user_id = ?";
        try ( Connection cn = dbContext.getConnection();  PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, courseId);
            ps.setInt(2, userId);
            List<Tracking> trackings = new ArrayList<>();
            try ( ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Tracking tracking = trackingResultSetMapper(rs);
                    trackings.add(tracking);
                }
                return trackings;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Tracking> getTrackingsByUserIdAndCourseIdAndStatus(Integer userId, Integer courseId, TrackingStatus status) {
        String sql = "select tracking.user_id as user_id, tracking.lesson_id as lesson_id, tracking.status as status \n"
                + "from courses \n"
                + "join sections on courses.course_id = sections.course_id and courses.course_id = ? \n"
                + "join lessons on sections.section_id = lessons.section_id \n"
                + "join tracking on lessons.lesson_id = tracking.lesson_id and tracking.user_id = ? and tracking.status = ?";
        try ( Connection cn = dbContext.getConnection();  PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, courseId);
            ps.setInt(2, userId);
            ps.setString(3, status.toString());
            List<Tracking> trackings = new ArrayList<>();
            try ( ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Tracking tracking = trackingResultSetMapper(rs);
                    trackings.add(tracking);
                }
                return trackings;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
