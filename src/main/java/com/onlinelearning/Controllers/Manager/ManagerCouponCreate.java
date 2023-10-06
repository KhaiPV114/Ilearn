package com.onlinelearning.Controllers.Manager;

import com.onlinelearning.Models.Coupon;
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
import org.apache.commons.lang3.StringUtils;

@WebServlet(name = "ManagerCouponCreate", urlPatterns = {"/dashboard/manager/coupon-create"})
public class ManagerCouponCreate extends HttpServlet {

    private static final CouponService couponService = new CouponServiceImpl();

    private static final CourseService courseService = new CourseServiceImpl();

    private static final String FORM_PATH = "/dashboard/manager/coupon-create-form.jsp";

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher(FORM_PATH).forward(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(FORM_PATH);

        String code = request.getParameter("code");
        if (StringUtils.isBlank(code)) {
            request.setAttribute("codeError", "Coupon code must not be empty!");
            requestDispatcher.forward(request, response);
            return;
        }
        String courseId = request.getParameter("courseId");
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

        LocalDateTime createdAt = LocalDateTime.now();

//        Course course = courseService.getCourseById(courseId);
        Coupon coupon = Coupon.builder()
                .code(code)
                .courseId(Integer.parseInt(courseId))
                .createdAt(createdAt)
                .endTime(LocalDateTime.parse(endedAt))
                .percent(Float.parseFloat(percent))
                .quantity(Integer.parseInt(quantity))
                .startTime(LocalDateTime.parse(startAt))
                .build();

        try {
            Coupon newCoupon = couponService.createCoupon(coupon);
            request.setAttribute("success", "C  reate coupon successfully!");
        } catch (Exception ex) {
            request.setAttribute("fail", ex.getMessage());
        }
        requestDispatcher.forward(request, response);
    }

}
