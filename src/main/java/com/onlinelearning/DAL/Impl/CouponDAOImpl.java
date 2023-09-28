package com.onlinelearning.DAL.Impl;

import com.onlinelearning.DAL.CouponDAO;
import com.onlinelearning.DAL.DBContext;
import com.onlinelearning.Models.Coupon;
import com.onlinelearning.Models.Course;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
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
                    Coupon coupon = Coupon.builder()
                            .id(rs.getInt("coupon_id"))
                            .courseId(rs.getInt("course_id"))
                            .code(rs.getString("code"))
                            .percent(rs.getFloat("percent"))
                            .quantity(rs.getInt("quantity"))
                            .remainQuantity(rs.getInt("remain_quantity"))
                            .createdAt(rs.getTimestamp("created_at").toLocalDateTime())
                            .startTime(rs.getTimestamp("start_time").toLocalDateTime())
                            .endTime(rs.getTimestamp("end_time").toLocalDateTime())
                            .status(rs.getString("status"))
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
                    Coupon coupon = Coupon.builder()
                            .id(rs.getInt("coupon_id"))
                            .courseId(rs.getInt("course_id"))
                            .code(rs.getString("code"))
                            .percent(rs.getFloat("percent"))
                            .quantity(rs.getInt("quantity"))
                            .remainQuantity(rs.getInt("remain_quantity"))
                            .createdAt(rs.getTimestamp("created_at").toLocalDateTime())
                            .startTime(rs.getTimestamp("start_time").toLocalDateTime())
                            .endTime(rs.getTimestamp("end_time").toLocalDateTime())
                            .status(rs.getString("status"))
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
    public Coupon getCouponByCode(String code
    ) {
        String sql = "select coupon_id, course_id, code, percent, quantity, remain_quantity, created_at, start_time, end_time, status "
                + "from coupons where code = ?";
        try ( Connection cn = dbContext.getConnection();  PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, code);
            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Coupon coupon = Coupon.builder()
                            .id(rs.getInt("coupon_id"))
                            .courseId(rs.getInt("course_id"))
                            .code(rs.getString("code"))
                            .percent(rs.getFloat("percent"))
                            .quantity(rs.getInt("quantity"))
                            .remainQuantity(rs.getInt("remain_quantity"))
                            .createdAt(rs.getTimestamp("created_at").toLocalDateTime())
                            .startTime(rs.getTimestamp("start_time").toLocalDateTime())
                            .endTime(rs.getTimestamp("end_time").toLocalDateTime())
                            .status(rs.getString("status"))
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
                Coupon coupon = Coupon.builder()
                        .id(rs.getInt("coupon_id"))
                        .courseId(rs.getInt("course_id"))
                        .code(rs.getString("code"))
                        .percent(rs.getFloat("percent"))
                        .quantity(rs.getInt("quantity"))
                        .remainQuantity(rs.getInt("remain_quantity"))
                        .createdAt(rs.getTimestamp("created_at").toLocalDateTime())
                        .startTime(rs.getTimestamp("start_time").toLocalDateTime())
                        .endTime(rs.getTimestamp("end_time").toLocalDateTime())
                        .status(rs.getString("status"))
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
        String sql = "insert into coupons(code, percent, quantity, remain_quantity, created_at, start_time, end_time, status)"
                + " values ( ?, ?, ?, ?, ? ,? ,? ,?)";
        try ( Connection cn = dbContext.getConnection();  PreparedStatement ps = cn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, coupon.getCode());
            ps.setFloat(2, coupon.getPercent());
            ps.setInt(3, coupon.getQuantity());
            ps.setInt(4, coupon.getRemainQuantity());
            ps.setTimestamp(5, Timestamp.valueOf(coupon.getCreatedAt()));
            ps.setTimestamp(6, Timestamp.valueOf(coupon.getStartTime()));
            ps.setTimestamp(7, Timestamp.valueOf(coupon.getEndTime()));
            ps.setString(8, coupon.getStatus());
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
            ps.setFloat(2, coupon.getPercent());
            ps.setInt(3, coupon.getQuantity());
            ps.setInt(4, coupon.getRemainQuantity());
            ps.setTimestamp(5, Timestamp.valueOf(coupon.getCreatedAt()));
            ps.setTimestamp(6, Timestamp.valueOf(coupon.getStartTime()));
            ps.setTimestamp(7, Timestamp.valueOf(coupon.getEndTime()));
            ps.setString(8, coupon.getStatus());
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

    public static void main(String[] args) {
        CouponDAOImpl couponDAOImpl = new CouponDAOImpl();
//        get
//        List<Coupon> coupons = couponDAOImpl.getAllCoupon();
//        System.out.println(coupons);

//        get by course name 

//      get by id
//        Coupon coupon = couponDAOImpl.getCouponById(2);
//        System.out.println(coupon);
//       get coupon by code
//        Coupon coupon = couponDAOImpl.getCouponByCode("abcxyz");
//        System.out.println(coupon);
//        create
//          Coupon coupon = Coupon.builder().code("abcxyz").createdAt(LocalDateTime.of(2015, 1, 2, 1, 1)).startTime(LocalDateTime.now()).endTime(LocalDateTime.of(2018, 1, 4, 1, 1)).percent(0.10F).quantity(100).remainQuantity(110).status("done").build();
//          couponDAOImpl.createCoupon(coupon);
//          System.out.println(coupon);
//        update
//            Coupon coupon = Coupon.builder().id(1).code("haha").createdAt(LocalDateTime.of(2011, 1, 1, 1, 1)).startTime(LocalDateTime.now()).endTime(LocalDateTime.of(2012, 1, 1, 1, 1)).percent(0.10F).quantity(100).remainQuantity(110).status("done").build();
//            couponDAOImpl.updateCoupon(coupon);
//            System.out.println(coupon);
//        delete
//          Coupon coupon = Coupon.builder().id(1).build();
//          couponDAOImpl.deleteCoupon(coupon);
//          System.out.println(coupon);
    }
}
