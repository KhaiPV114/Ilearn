package com.onlinelearning.DAL;

import com.onlinelearning.Models.Coupon;
import java.util.List;

public interface CouponDAO {
    
    Coupon getCouponById(Integer id);
    
    Coupon getCouponByCode(String code);
    
    List<Coupon> getAllCoupon();
    
    Coupon createCoupon(Coupon coupon);
    
    Coupon updateCoupon(Coupon coupon);
    
    Coupon deleteCoupon(Coupon coupon);

}
