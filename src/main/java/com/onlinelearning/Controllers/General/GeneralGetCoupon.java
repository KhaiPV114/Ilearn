package com.onlinelearning.Controllers.General;

import com.google.gson.Gson;
import com.onlinelearning.Models.Coupon;
import com.onlinelearning.Models.Course;
import com.onlinelearning.Services.CouponService;
import com.onlinelearning.Services.Impl.CouponServiceImpl;
import com.onlinelearning.Utils.Constants;
import com.onlinelearning.Utils.JsonUtils;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@WebServlet(name = "GeneralGetCoupon", urlPatterns = {"/coupon/get"})
public class GeneralGetCoupon extends HttpServlet {

    private static final String HOME_PATH = Constants.HOME_PATH;

    private final CouponService couponService = new CouponServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + HOME_PATH);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try ( PrintWriter pw = response.getWriter()) {

            List<Course> coursesInCart = (List<Course>) request.getSession().getAttribute("coursesInCart");

            //Create HashMap<courseId, couponCode> use to track coupon applied to which course
            String requestData = JsonUtils.getJsonDataFromRequest(request);
            HashMap<String, String> courseCoupons = JsonUtils.convertJsonToHashMap(requestData);

            try {
                //Get coupon code insert from user
                Coupon insertedCoupon = couponService.getCouponByCode(courseCoupons.get("insertCoupon"));
                //Check if coupon can apply
                if (couponService.canApplyCoupon(insertedCoupon)) {
                    Double discount = 0d;
                    boolean isApplied = false;
                    boolean isSuitable = true;
                    List<String> appliedCoupons = new ArrayList<>();

                    //Iterate through all course in cart to calculate discount and apply coupon
                    for (Course course : coursesInCart) {
                        //Get current coupon have applied to this course, if haven't
                        //applied, set percent discount to 0
                        Coupon currentCoupon;
                        try {
                            currentCoupon = couponService.getCouponByCode(
                                    courseCoupons.get(course.getId().toString())
                            );
                        } catch (Exception e) {
                            currentCoupon = Coupon.builder()
                                    .percent(0d)
                                    .build();
                        }

                        if (insertedCoupon.getCourseId().equals(course.getId()) || insertedCoupon.getCourseId() == 0) {     //0: coupon for all course
                            if (insertedCoupon.getPercent() > currentCoupon.getPercent()) {
                                courseCoupons.replace(course.getId().toString(), insertedCoupon.getCode());
                                discount += course.getPrice() * (insertedCoupon.getPercent() / 100);
                                isApplied = true;
                            } else {
                                isSuitable = false;
                                discount += course.getPrice() * (currentCoupon.getPercent() / 100);
                            }
                        } else {
                            discount += course.getPrice() * (currentCoupon.getPercent() / 100);
                        }

                        //Add applied coupon to list
                        if (!courseCoupons.get(course.getId().toString()).isEmpty() && !appliedCoupons.contains(courseCoupons.get(course.getId().toString()))) {
                            appliedCoupons.add(courseCoupons.get(course.getId().toString()));
                        }
                    }

                    //Add appiled coupons and discount of all coupon to tracker
                    courseCoupons.put("appliedCoupons", appliedCoupons.toString());
                    courseCoupons.put("discount", discount.toString());

                    if (isApplied) {    //If coupon have applied
                        courseCoupons.remove("failedMsg");
                        courseCoupons.put("appliedMsg", "Applied <strong>" + insertedCoupon.getCode() + "</strong> to your cart!");
                    } else if (!isSuitable) {   //If coupon available, but can't apply
                        courseCoupons.remove("appliedMsg");
                        courseCoupons.put("failedMsg", "You have applied a more valuable coupon code!");
                    } else {    //If coupon code can't apply for any course in cart
                        courseCoupons.remove("appliedMsg");
                        courseCoupons.put("failedMsg", "Coupon <strong>" + insertedCoupon.getCode() + "</strong> is not available for these courses!");
                    }
                }
            } catch (Exception couponException) {
                courseCoupons.remove("appliedMsg");
                courseCoupons.put("failedMsg", couponException.getMessage());
            }

            //Transfer tracker to Json and send response
            Gson gson = new Gson();
            String responseData = gson.toJson(courseCoupons);
            response.setStatus(HttpServletResponse.SC_OK);
            pw.print(responseData);
        }
    }
}
