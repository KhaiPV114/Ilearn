package com.onlinelearning.DAL.Impl;

import com.onlinelearning.DAL.CouponDAO;
import com.onlinelearning.DAL.DBContext;
import com.onlinelearning.Enums.CouponStatus;
import com.onlinelearning.Models.Coupon;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CouponDAOImpl implements CouponDAO {

    private final DBContext dbContext = new DBContextImpl();
    private final String COUPON_TABLE = "coupons";

    private Coupon couponResultSetMapper(ResultSet rs) throws SQLException {
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

    @Override
    public Coupon getCouponById(Integer id) {
        String sql = "select *"
                + " from " + COUPON_TABLE
                + " where coupon_id = ?";
        try ( Connection cn = dbContext.getConnection();  PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return couponResultSetMapper(rs);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    @Override
    public Coupon getCouponByCode(String code) {
        String sql = "select *"
                + " from " + COUPON_TABLE
                + " where code = ?";
        try ( Connection cn = dbContext.getConnection();  PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, code);
            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return couponResultSetMapper(rs);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Coupon> getAllCoupons() {
        String sql = "select *"
                + " from " + COUPON_TABLE;
        try ( Connection cn = dbContext.getConnection();  PreparedStatement ps = cn.prepareStatement(sql);  ResultSet rs = ps.executeQuery()) {
            List<Coupon> coupons = new ArrayList<>();
            while (rs.next()) {
                coupons.add(couponResultSetMapper(rs));
            }
            return coupons;
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Coupon createCoupon(Coupon coupon) {
        String sql = "insert into " + COUPON_TABLE
                + "(code, percent, quantity, created_at, start_time, end_time, status, course_id)"
                + " values ( ?, ?, ?, ? ,? ,? ,? , ?)";
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
            ps.setInt(8, coupon.getCourseId());
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
    public Coupon updateCoupon(Coupon coupon) {
        String sql = "update " + COUPON_TABLE
                + " set code = ?, percent = ?, quantity = ?, remain_quantity = ?, created_at = ?, start_time = ?, end_time = ?, status = ?, course_id = ? "
                + " where coupon_id = ?";
        try ( Connection cn = dbContext.getConnection();  PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, coupon.getCode());
            ps.setDouble(2, coupon.getPercent());
            ps.setInt(3, coupon.getQuantity());
            if (coupon.getRemainQuantity() == null) {
                ps.setNull(4, Types.INTEGER);
            } else {
                ps.setInt(4, coupon.getRemainQuantity());
            }
            ps.setTimestamp(5, Timestamp.valueOf(coupon.getCreatedAt()));
            ps.setTimestamp(6, Timestamp.valueOf(coupon.getStartTime()));
            ps.setTimestamp(7, Timestamp.valueOf(coupon.getEndTime()));
            if (coupon.getStatus() == null) {
                ps.setNull(8, Types.VARCHAR);
            } else {
                ps.setString(8, coupon.getStatus().toString());
            }
            ps.setInt(9, coupon.getCourseId());
            ps.setInt(10, coupon.getId());
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
    public Coupon deleteCoupon(Coupon coupon) {
        String sql = "delete from " + COUPON_TABLE
                + " where coupon_id = ?";
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

    public static void main(String[] args) {
        CouponDAOImpl couponDAOImpl = new CouponDAOImpl();
        Coupon coupon = new Coupon();
        coupon.setId(1);
        coupon.setCourseId(2);
        coupon.setCode("DISCOUNT123");
        coupon.setPercent(10.0);
        coupon.setQuantity(100);
        coupon.setRemainQuantity(50);
        coupon.setCreatedAt(LocalDateTime.now());
        coupon.setStartTime(LocalDateTime.of(2023, 1, 1, 0, 0)); // Set start time, adjust as needed
        coupon.setEndTime(LocalDateTime.of(2023, 12, 31, 23, 59));
        coupon.setStatus(CouponStatus.ACTIVE);
        couponDAOImpl.updateCoupon(coupon);
        System.out.println(coupon);
    }

}
