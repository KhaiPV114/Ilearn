package com.onlinelearning.DAL.Impl;

import com.onlinelearning.DAL.DBContext;
import com.onlinelearning.DAL.SectionDAO;
import com.onlinelearning.Enums.SectionStatus;
import com.onlinelearning.Models.Section;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SectionDAOImpl implements SectionDAO {
    
    private final DBContext dbContext = new DBContextImpl();
    
    private Section sectionResultSetMapper(ResultSet rs) throws SQLException {
        String statusString = rs.getString("status");
        SectionStatus status = null;
        if (statusString != null) {
            status = SectionStatus.valueOf(statusString);
        }
        Section section = Section.builder()
                .id(rs.getInt("section_id"))
                .courseId(rs.getInt("course_id"))
                .name(rs.getString("name"))
                .status(status)
                .orderNumber(rs.getFloat("order_number"))
                .build();
        return section;
    }
    
    @Override
    public Float getNewOrderNumberByCourseId(Integer courseId) {
        String sql = "select max(order_number) as max_order_number from sections where course_id = ?";
        try ( Connection cn = dbContext.getConnection();  PreparedStatement ps = cn.prepareStatement(sql);) {
            ps.setInt(1, courseId);
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
    public Section createSection(Section section) {
        String sql = "insert into sections(course_id, name, status, order_number) VALUES (?, ?, ?, ?)";
        try ( Connection cn = dbContext.getConnection();  PreparedStatement ps = cn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, section.getCourseId());
            ps.setString(2, section.getName());
            if (section.getStatus() == null) {
                ps.setNull(3, Types.VARCHAR);
            } else {
                ps.setString(3, section.getStatus().toString());
            }
            Float orderNumber = getNewOrderNumberByCourseId(section.getCourseId());
            ps.setFloat(4, orderNumber);
            int affectedRow = ps.executeUpdate();
            if (affectedRow > 0) {
                try ( ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        section.setId(rs.getInt(1));
                        return section;
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @Override
    public Section getSection(Integer id) {
        String sql = "select * from sections where section_id = ?";
        try ( Connection cn = dbContext.getConnection();  PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return sectionResultSetMapper(rs);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @Override
    public List<Section> getSectionsByCourseId(Integer courseId) {
        String sql = "select * from sections where course_id = ? order by order_number";
        try ( Connection cn = dbContext.getConnection();  PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, courseId);
            try ( ResultSet rs = ps.executeQuery()) {
                List<Section> sections = new ArrayList<>();
                while (rs.next()) {
                    sections.add(sectionResultSetMapper(rs));
                }
                return sections;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @Override
    public List<Section> getSectionsByCourseIdAndStatus(Integer courseId, SectionStatus status) {
        String sql = "select * from sections where course_id = ? and status = ? order by order_number";
        if (status == SectionStatus.ACTIVE) {
            sql = "select * from sections where course_id = ? and (status = ? or status is null) order by order_number";
        }
        try ( Connection cn = dbContext.getConnection();  PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, courseId);
            ps.setString(2, status.toString());
            try ( ResultSet rs = ps.executeQuery()) {
                List<Section> sections = new ArrayList<>();
                while (rs.next()) {
                    sections.add(sectionResultSetMapper(rs));
                }
                return sections;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @Override
    public Section updateSection(Section section) {
        String sql = "update sections set course_id = ?, name = ?, status = ?, order_number = ? where section_id = ?";
        try ( Connection cn = dbContext.getConnection();  PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, section.getCourseId());
            ps.setString(2, section.getName());
            if (section.getStatus() == null) {
                ps.setNull(3, Types.VARCHAR);
            } else {
                ps.setString(3, section.getStatus().toString());
            }
            ps.setFloat(4, section.getOrderNumber());
            ps.setInt(5, section.getId());
            int affectedRow = ps.executeUpdate();
            if (affectedRow > 0) {
                return section;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @Override
    public Section deleteSection(Section section) {
        String sql = "delete from sections where section_id = ?";
        try ( Connection cn = dbContext.getConnection();  PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, section.getId());
            int affectedRow = ps.executeUpdate();
            if (affectedRow > 0) {
                return section;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
