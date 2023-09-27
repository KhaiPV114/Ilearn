package com.onlinelearning.Controllers.Manager;

import com.onlinelearning.DAL.CouponDAO;
import com.onlinelearning.Models.Coupon;
import com.onlinelearning.Services.CouponService;
import com.onlinelearning.Services.Impl.CouponServiceImpl;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

@WebServlet(name = "ManagerCouponCreate", urlPatterns = {"/dashboard/manager/coupon-search"})
public class ManagerCouponSearch extends HttpServlet {

    private static final String FORM_PATH = "/dashboard/manager/coupon-form.jsp";

    private static final CouponService couponService = new CouponServiceImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher(FORM_PATH).forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(FORM_PATH);

        String id = request.getParameter("id");
        String code = request.getParameter("code");

        List<Coupon> coupons = new ArrayList<>();
        
        if (StringUtils.isBlank(id) && StringUtils.isBlank(code)) {
            coupons = couponService.getAllCoupon();
            request.setAttribute("coupons", coupons);
            requestDispatcher.forward(request, response);
        }
    }

}
