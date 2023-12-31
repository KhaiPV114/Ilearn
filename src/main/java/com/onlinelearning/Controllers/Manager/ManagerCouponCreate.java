package com.onlinelearning.Controllers.Manager;

import com.onlinelearning.Enums.CouponStatus;
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

@WebServlet(name = "ManagerCouponCreate", urlPatterns = {"/manager/coupon/create"})
public class ManagerCouponCreate extends HttpServlet {

    private static final String FORM_PATH = "/dashboard/manager/coupon-create-form.jsp";

    private static final CouponService couponService = new CouponServiceImpl();
    
    private static final CourseService courseService = new CourseServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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

        LocalDateTime createdAt = LocalDateTime.now();

//        Course course = courseService.getCourseById(courseId);
        Coupon coupon = Coupon.builder()
                .code(code)
                .courseId(Integer.parseInt(courseId))
                .createdAt(createdAt)
                .endTime(LocalDateTime.parse(endedAt))
                .percent(Double.parseDouble(percent))
                .quantity(Integer.parseInt(quantity))
                .startTime(LocalDateTime.parse(startAt))
                .status(CouponStatus.ACTIVE)
                .build();

        System.out.println("Coupon:" + coupon);
        try {
            Coupon newCoupon = couponService.createCoupon(coupon);
            request.setAttribute("success", "Create coupon successfully!");
        } catch (Exception ex) {
            request.setAttribute("fail", ex.getMessage());
        }
        requestDispatcher.forward(request, response);
    }

}
