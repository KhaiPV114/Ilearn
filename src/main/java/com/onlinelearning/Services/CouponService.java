package com.onlinelearning.Services;

import com.onlinelearning.Models.Coupon;
import java.util.List;

public interface CouponService {
    
    Coupon getCouponById(Integer id);
    
    List<Coupon> getAllCoupon();
    
    Coupon getCouponByCode(String code);
    
    Coupon createCoupon(Coupon coupon) throws Exception;
    
    Coupon updateCoupon(Coupon coupon) throws Exception;
    
    Coupon deleteCoupon(Coupon coupon) throws Exception;
    
    boolean canApplyCoupon(Coupon coupon) throws Exception;
    
    Coupon minusCouponRemain(Coupon coupon);
}
