package com.onlinelearning.Controllers.Manager;

import com.onlinelearning.Models.Coupon;
import com.onlinelearning.Models.Course;
import com.onlinelearning.Services.CouponService;
import com.onlinelearning.Services.CourseService;
import com.onlinelearning.Services.Impl.CouponServiceImpl;
import com.onlinelearning.Services.Impl.CourseServiceImpl;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

@WebServlet(name = "ManagerCouponEdit", urlPatterns = {"/manager/coupon/edit"})
public class ManagerCouponEdit extends HttpServlet {

    private static final String FORM_PATH = "/dashboard/manager/coupon-edit-form.jsp";

    private static final CouponService couponService = new CouponServiceImpl();
    
    private static final CourseService courseService = new CourseServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String couponId = request.getParameter("couponId");
        
        
        int editCouponId = Integer.valueOf(couponId);
        Coupon editCoupon = couponService.getCouponById(editCouponId);
        request.setAttribute("couponId", couponId);
        request.setAttribute("couponEdit", editCoupon);
        
        List<Course> courses = courseService.getAllCourses();
        System.out.println(courses);
        request.setAttribute("courses", courses);
        request.getRequestDispatcher(FORM_PATH).forward(request, response);
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(FORM_PATH);

        List<Course> courses = courseService.getAllCourses();
        request.setAttribute("courses", courses);
        
        String couponId = request.getParameter("id");
        if (StringUtils.isBlank(couponId)) {
            request.setAttribute("couponIdError", "Coupon ID must not be empty!");
            requestDispatcher.forward(request, response);
            return;
        }
        
        String code = request.getParameter("code");
        if (StringUtils.isBlank(code)) {
            request.setAttribute("codeError", "Coupon code must not be empty!");
            requestDispatcher.forward(request, response);
            return;
        }
        String courseId = request.getParameter("courseId");
        System.out.println(courseId);
        if (StringUtils.isBlank(courseId)) {
            request.setAttribute("coureIdError", "Course ID must not be empty!");
            requestDispatcher.forward(request, response);
            return;
        }

        String percent = request.getParameter("percent");
        if (StringUtils.isBlank(percent)) {
            request.setAttribute("percentError", "Percent code must not be empty!");
            requestDispatcher.forward(request, response);
            return;
        }

        String quantity = request.getParameter("quantity");
        if (StringUtils.isBlank(quantity)) {
            request.setAttribute("quantityError", "Quantity code must not be empty!");
            requestDispatcher.forward(request, response);
            return;
        }

        String startAt = request.getParameter("started");
        if (StringUtils.isBlank(startAt)) {
            request.setAttribute("startedError", "Start time must not be empty!");
            requestDispatcher.forward(request, response);
            return;
        }

        String endedAt = request.getParameter("ended");
        if (StringUtils.isBlank(endedAt)) {
            request.setAttribute("endedError", "End time must not be empty!");
            requestDispatcher.forward(request, response);
            return;
        }
        
        String status = request.getParameter("status");
        if (StringUtils.isBlank(status)) {
            request.setAttribute("statuserror", "Status must not be empty!");
            requestDispatcher.forward(request, response);
            return;
        }

        LocalDateTime createdAt = LocalDateTime.now();

        Coupon coupon = Coupon.builder()
                .id(Integer.valueOf(couponId))
                .code(code)
                .courseId(Integer.valueOf(courseId))
                .createdAt(createdAt)
                .endTime(LocalDateTime.parse(endedAt))
                .percent(Double.valueOf(percent))
                .quantity(Integer.parseInt(quantity))
                .startTime(LocalDateTime.parse(startAt))
                .build();
        System.out.println("EDit coupon : " + coupon);

        try {
            Coupon editCoupon = couponService.updateCoupon(coupon);
            System.out.println(editCoupon);
            request.setAttribute("success", "Edit coupon successfully!");
        } catch (Exception ex) {
            request.setAttribute("fail", ex.getMessage());
        }
        requestDispatcher.forward(request, response);
    }

}
