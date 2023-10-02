package com.onlinelearning.Controllers;

import com.onlinelearning.Models.Coupon;
import com.onlinelearning.Models.Course;
import com.onlinelearning.Services.AuthService;
import com.onlinelearning.Services.CouponService;
import com.onlinelearning.Services.Impl.AuthServiceImpl;
import com.onlinelearning.Services.Impl.CouponServiceImpl;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.List;

@WebServlet(name = "GeneralGetCoupon", urlPatterns = {"/get-coupon-code"})
public class GeneralGetCoupon extends HttpServlet {

    private final CouponService couponService = new CouponServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        String couponCode = request.getParameter("coupon-code");
        Coupon coupon = couponService.getCouponByCode(couponCode);

        List<Course> coursesInCart = (List<Course>) request.getSession().getAttribute("coursesInCart");

        //Check if coupon found or not
        if (coupon == null) {
            response.setStatus(response.SC_NOT_FOUND);
            pw.print("Coupon code is invalid!");
            pw.close();
            return;
        }

        //Check if coupon have remain quantity or out-of-date or disable
        if (coupon.getRemainQuantity() <= 0 || coupon.getEndTime().isBefore(LocalDateTime.now()) || coupon.getStatus().compareTo("disable") == 0) {
            response.setStatus(response.SC_NOT_FOUND);
            pw.print("Too bad, the coupon code has expired!");
            pw.close();
            return;
        }

        //Check if coupon code is for course in cart
        for (Course course : coursesInCart) {
            if (coupon.getCourseId().equals(course.getId())) {
                response.setStatus(response.SC_OK);
                double discount = course.getPrice() * coupon.getPercent() / 100;
                pw.print(discount);
                pw.close();
            }
        }

        response.setStatus(response.SC_NOT_ACCEPTABLE);
        pw.print("The coupon code is not valid for these courses!");
        pw.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
