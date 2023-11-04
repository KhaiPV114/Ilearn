package com.onlinelearning.Controllers.Manager;

import com.onlinelearning.Models.Coupon;
import com.onlinelearning.Services.CouponService;
import com.onlinelearning.Services.Impl.CouponServiceImpl;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.StringUtils;

@WebServlet(name = "ManagerCouponSearch", urlPatterns = {"/manager/coupon/search"})
public class ManagerCouponSearch extends HttpServlet {

    private static final String FORM_PATH = "/dashboard/manager/coupon-search-form.jsp";

    private static final CouponService couponService = new CouponServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher(FORM_PATH).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(FORM_PATH);

        String courseName = request.getParameter("courseName");
        String code = request.getParameter("code");

        List<Coupon> coupons = new ArrayList<>();
        Coupon coupon;

        if (StringUtils.isBlank(courseName) && StringUtils.isBlank(code)) {

            coupons = couponService.getAllCoupon();
            request.setAttribute("coupons", coupons);
            requestDispatcher.forward(request, response);
        }

        if (StringUtils.isBlank(courseName) && !StringUtils.isBlank(code)) {
            try {
                coupon = couponService.getCouponByCode(code);
                coupons.add(coupon);
                request.setAttribute("coupons", coupons);
                requestDispatcher.forward(request, response);
            } catch (Exception ex) {
                Logger.getLogger(ManagerCouponSearch.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (!StringUtils.isBlank(courseName) && StringUtils.isBlank(code)) {

        }

    }
}
