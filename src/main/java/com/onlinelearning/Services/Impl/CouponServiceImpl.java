package com.onlinelearning.Services.Impl;

import com.onlinelearning.DAL.CouponDAO;
import com.onlinelearning.DAL.Impl.CouponDAOImpl;
import com.onlinelearning.Enums.CouponStatus;
import com.onlinelearning.Models.Coupon;
import com.onlinelearning.Services.CouponService;
import java.time.LocalDateTime;
import java.util.List;

public class CouponServiceImpl implements CouponService {

    private final CouponDAO couponDAO = new CouponDAOImpl();

    @Override
    public Coupon getCouponById(Integer id) {
        if (id == null) {
            return null;
        }
        return couponDAO.getCouponById(id);
    }

    @Override
    public List<Coupon> getAllCoupon() {
        return couponDAO.getAllCoupons();
    }

    @Override
    public Coupon getCouponByCode(String code) throws Exception {
        if (!code.isEmpty()) {
            Coupon coupon = couponDAO.getCouponByCode(code);
            if (coupon != null) {
                return coupon;
            }
        }
        throw new Exception("Coupon not found.");
    }

    public void validateCoupon(Coupon coupon) throws Exception {
        if (couponDAO.getCouponByCode(coupon.getCode()) != null) {
            throw new Exception("Coupon Code is already exist!");
        }
    }

    @Override
    public Coupon createCoupon(Coupon coupon) throws Exception {
        validateCoupon(coupon);
        Coupon newCoupon = couponDAO.createCoupon(coupon);
        if (newCoupon == null) {
            throw new Exception("Create coupon failed!");
        }
        return coupon;
    }

    @Override
    public Coupon updateCoupon(Coupon coupon) throws Exception {
        validateCoupon(coupon);
        Coupon updateCoupon = couponDAO.updateCoupon(coupon);
        if (updateCoupon == null) {
            throw new Exception("Update coupon failed!");
        }
        return updateCoupon;
    }

    @Override
    public Coupon deleteCoupon(Coupon coupon) throws Exception {
        if (coupon.getId() == null) {
            throw new Exception("Coupon is must not be empty!");
        }
        Coupon deleteCoupon = couponDAO.deleteCoupon(coupon);
        if (deleteCoupon == null) {
            throw new Exception("Delete coupon failed!");
        }
        return coupon;
    }

    @Override
    public boolean canApplyCoupon(Coupon coupon) throws Exception {
        if (coupon == null) {
            throw new Exception("Coupon code is invalid!");
        }

        //Check if coupon have remain quantity
        if (coupon.getRemainQuantity() <= 0) {
            throw new Exception("Coupon <strong>" + coupon.getCode() + "</strong> is no longer usable!");
        }

        //check if coupon code is expired or disable
        if (coupon.getEndTime().isBefore(LocalDateTime.now()) || coupon.getStatus().compareTo(CouponStatus.DISABLED) == 0) {
            throw new Exception("Too bad, coupon <strong>" + coupon.getCode() + "</strong> has expired!");
        }

        //Check if coupon code is publish
        if (coupon.getStartTime().isAfter(LocalDateTime.now())) {
            throw new Exception("Coupon <strong>" + coupon.getCode() + "</strong> is not available at this time!");
        }

        return true;
    }

    @Override
    public Coupon minusCouponRemain(Coupon coupon) {
        if (coupon == null) {
            return null;
        }

        if (coupon.getRemainQuantity() > 0) {
            coupon.setRemainQuantity(coupon.getRemainQuantity() - 1);
            return couponDAO.updateCoupon(coupon);
        }
        return coupon;
    }
}
