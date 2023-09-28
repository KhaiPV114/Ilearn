package com.onlinelearning.Services.Impl;

import com.onlinelearning.DAL.CouponDAO;
import com.onlinelearning.DAL.Impl.CouponDAOImpl;
import com.onlinelearning.Models.Coupon;
import com.onlinelearning.Services.CouponService;
import java.util.List;

public class CouponServiceImpl implements CouponService{
    
    private final CouponDAO couponDAO = new CouponDAOImpl();

    @Override
    public Coupon getCouponById(Integer id) {
        if(id == null){
            return null;
        }
        return couponDAO.getCouponById(id);
    }

    @Override
    public List<Coupon> getAllCoupon() {
        return couponDAO.getAllCoupon();
    }

    @Override
    public Coupon getCouponByCode(String code){
        if(code.isEmpty()){
            return null;
        }
        return couponDAO.getCouponByCode(code);
    }
    
    public void validateCoupon(Coupon coupon) throws Exception{
        if(couponDAO.getCouponByCode(coupon.getCode()) != null){
            throw new Exception("Coupon Code is already exist!");
        }
    }

    @Override
    public Coupon createCoupon(Coupon coupon) throws Exception {
        validateCoupon(coupon);
        Coupon newCoupon = couponDAO.createCoupon(coupon);
        if(newCoupon == null){
            throw new Exception("Create coupon failed!");
        }
        return coupon;
    }

    @Override
    public Coupon updateCoupon(Coupon coupon) throws Exception {
        validateCoupon(coupon);
        Coupon updateCoupon = couponDAO.updateCoupon(coupon);
        if(updateCoupon == null){
            throw new Exception("Update coupon failed!");
        }
        return updateCoupon;
    }

    @Override
    public Coupon deleteCoupon(Coupon coupon) throws Exception {
        if(coupon.getId() == null){
            throw new Exception("Coupon is must not be empty!");
        }
        Coupon deleteCoupon = couponDAO.deleteCoupon(coupon);
        if(deleteCoupon == null){
            throw new Exception("Delete coupon failed!");
        }
        return coupon;
    }
    
}
