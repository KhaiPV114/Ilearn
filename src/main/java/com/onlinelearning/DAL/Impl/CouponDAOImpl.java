package com.onlinelearning.DAL.Impl;

import com.onlinelearning.DAL.CouponDAO;
import com.onlinelearning.DAL.DBContext;
import com.onlinelearning.Enums.CouponStatus;
import com.onlinelearning.Models.Coupon;
import com.onlinelearning.Models.Course;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CouponDAOImpl implements CouponDAO {

    private final DBContext dbContext = new DBContextImpl();

    public Coupon getCouponByCourseName(Course course) {
        String sql = "select coupon_id, course_id, code, percent, quantity, remain_quantity, created_at, start_time, end_time, status"
                + " from coupons c "
                + "join courses co"
                + " on c.course_id = co.course_id "
                + "where co.name = ?";
        try ( Connection cn = dbContext.getConnection();  PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, course.getName());
            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    CouponStatus status = null;
                    if (rs.getString("status") != null) {
                        status = CouponStatus.valueOf(rs.getString("status"));
                    }
                    Coupon coupon = Coupon.builder()
                            .id(rs.getInt("coupon_id"))
                            .courseId(rs.getInt("course_id"))
                            .code(rs.getString("code"))
                            .percent(rs.getDouble("percent"))
                            .quantity(rs.getInt("quantity"))
                            .remainQuantity(rs.getInt("remain_quantity"))
                            .createdAt(rs.getTimestamp("created_at").toLocalDateTime())
                            .startTime(rs.getTimestamp("start_time").toLocalDateTime())
                            .endTime(rs.getTimestamp("end_time").toLocalDateTime())
                            .status(status)
                            .build();
                    return coupon;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAOImpl.class.getName()).log(Level.SEVERE, null, ex);

        }
        return null;
    }

    @Override
    public Coupon getCouponById(Integer id
    ) {
        String sql = "select coupon_id, course_id, code, percent, quantity, remain_quantity, created_at, start_time, end_time, status"
                + " from coupons where coupon_id = ?";
        try ( Connection cn = dbContext.getConnection();  PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    CouponStatus status = null;
                    if (rs.getString("status") != null) {
                        status = CouponStatus.valueOf(rs.getString("status"));
                    }
                    Coupon coupon = Coupon.builder()
                            .id(rs.getInt("coupon_id"))
                            .courseId(rs.getInt("course_id"))
                            .code(rs.getString("code"))
                            .percent(rs.getDouble("percent"))
                            .quantity(rs.getInt("quantity"))
                            .remainQuantity(rs.getInt("remain_quantity"))
                            .createdAt(rs.getTimestamp("created_at").toLocalDateTime())
                            .startTime(rs.getTimestamp("start_time").toLocalDateTime())
                            .endTime(rs.getTimestamp("end_time").toLocalDateTime())
                            .status(status)
                            .build();
                    return coupon;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    @Override
    public Coupon getCouponByCode(String code) {
        String sql = "select coupon_id, course_id, code, percent, quantity, remain_quantity, created_at, start_time, end_time, status "
                + "from coupons where code = ?";
        try ( Connection cn = dbContext.getConnection();  PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, code);
            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    CouponStatus status = null;
                    if (rs.getString("status") != null) {
                        status = CouponStatus.valueOf(rs.getString("status"));
                    }
                    Coupon coupon = Coupon.builder()
                            .id(rs.getInt("coupon_id"))
                            .courseId(rs.getInt("course_id"))
                            .code(rs.getString("code"))
                            .percent(rs.getDouble("percent"))
                            .quantity(rs.getInt("quantity"))
                            .remainQuantity(rs.getInt("remain_quantity"))
                            .createdAt(rs.getTimestamp("created_at").toLocalDateTime())
                            .startTime(rs.getTimestamp("start_time").toLocalDateTime())
                            .endTime(rs.getTimestamp("end_time").toLocalDateTime())
                            .status(status)
                            .build();
                    return coupon;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Coupon> getAllCoupon() {
        String sql = "select coupon_id, course_id, code, percent, quantity, remain_quantity, created_at, start_time, end_time, status"
                + " from coupons";
        try ( Connection cn = dbContext.getConnection();  PreparedStatement ps = cn.prepareStatement(sql);  ResultSet rs = ps.executeQuery()) {
            List<Coupon> coupons = new ArrayList<>();
            while (rs.next()) {
                CouponStatus status = null;
                if (rs.getString("status") != null) {
                    status = CouponStatus.valueOf(rs.getString("status"));
                }
                Coupon coupon = Coupon.builder()
                        .id(rs.getInt("coupon_id"))
                        .courseId(rs.getInt("course_id"))
                        .code(rs.getString("code"))
                        .percent(rs.getDouble("percent"))
                        .quantity(rs.getInt("quantity"))
                        .remainQuantity(rs.getInt("remain_quantity"))
                        .createdAt(rs.getTimestamp("created_at").toLocalDateTime())
                        .startTime(rs.getTimestamp("start_time").toLocalDateTime())
                        .endTime(rs.getTimestamp("end_time").toLocalDateTime())
                        .status(status)
                        .build();
                coupons.add(coupon);
            }
            return coupons;
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Coupon createCoupon(Coupon coupon
    ) {
        String sql = "insert into coupons(code, percent, quantity, created_at, start_time, end_time, status)"
                + " values ( ?, ?, ?, ? ,? ,? ,?)";
        try ( Connection cn = dbContext.getConnection();  PreparedStatement ps = cn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, coupon.getCode());
            ps.setDouble(2, coupon.getPercent());
            ps.setInt(3, coupon.getQuantity());
            ps.setTimestamp(4, Timestamp.valueOf(coupon.getCreatedAt()));
            ps.setTimestamp(5, Timestamp.valueOf(coupon.getStartTime()));
            ps.setTimestamp(6, Timestamp.valueOf(coupon.getEndTime()));
            if (coupon.getStatus() == null) {
                ps.setNull(7, Types.VARCHAR);
            } else {
                ps.setString(7, coupon.getStatus().toString());
            }
            int affectedRow = ps.executeUpdate();
            if (affectedRow > 0) {
                try ( ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        coupon.setId(rs.getInt(1));
                        return coupon;
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Coupon updateCoupon(Coupon coupon
    ) {
        String sql = "update coupons set "
                + "code = ?, percent = ?, quantity = ?, remain_quantity = ?, created_at = ?, start_time = ?, end_time = ?, status = ?"
                + " where coupon_id = ?";
        try ( Connection cn = dbContext.getConnection();  PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, coupon.getCode());
            ps.setDouble(2, coupon.getPercent());
            ps.setInt(3, coupon.getQuantity());
            ps.setInt(4, coupon.getRemainQuantity());
            ps.setTimestamp(5, Timestamp.valueOf(coupon.getCreatedAt()));
            ps.setTimestamp(6, Timestamp.valueOf(coupon.getStartTime()));
            ps.setTimestamp(7, Timestamp.valueOf(coupon.getEndTime()));
            ps.setString(8, coupon.getStatus().toString());
            ps.setInt(9, coupon.getId());
            int affectedRow = ps.executeUpdate();
            if (affectedRow > 0) {
                return coupon;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Coupon deleteCoupon(Coupon coupon
    ) {
        String sql = "delete from coupons where coupon_id = ?";
        try ( Connection cn = dbContext.getConnection();  PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, coupon.getId());
            int affectedRow = ps.executeUpdate();
            if (affectedRow > 0) {
                return coupon;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
}