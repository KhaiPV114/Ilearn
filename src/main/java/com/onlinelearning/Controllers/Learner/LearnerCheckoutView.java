package com.onlinelearning.Controllers.Learner;

import com.onlinelearning.Enums.OrderStatus;
import com.onlinelearning.Models.Coupon;
import com.onlinelearning.Models.Course;
import com.onlinelearning.Models.Order;
import com.onlinelearning.Models.OrderItem;
import com.onlinelearning.Models.User;
import com.onlinelearning.Services.AuthService;
import com.onlinelearning.Services.CouponService;
import com.onlinelearning.Services.Impl.AuthServiceImpl;
import com.onlinelearning.Services.Impl.CouponServiceImpl;
import com.onlinelearning.Services.Impl.OrderItemServiceImpl;
import com.onlinelearning.Services.Impl.OrderServiceImpl;
import com.onlinelearning.Services.OrderItemService;
import com.onlinelearning.Services.OrderService;
import com.onlinelearning.Utils.JsonUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@WebServlet(name = "LearnerCheckoutView", urlPatterns = {"/cart/checkout"})
public class LearnerCheckoutView extends HttpServlet {

    private final String VIEW_PATH = "/dashboard/learner/checkout.jsp";
    private final String ERROR_404_PATH = "/error/404.jsp";
    private final String HOME_PATH = "/homepage";
    private final OrderService orderService = new OrderServiceImpl();
    private final OrderItemService orderItemService = new OrderItemServiceImpl();
    private final CouponService couponService = new CouponServiceImpl();
    private final AuthService authService = new AuthServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher(ERROR_404_PATH).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        User user = authService.getUser(request);
        List<Course> coursesInCart = (List<Course>) request.getSession().getAttribute("coursesInCart");
        if (user == null || coursesInCart.isEmpty()) {
            response.sendRedirect(request.getContextPath() + HOME_PATH);
            return;
        }

        HashMap<String, String> courseCouponMap = JsonUtils.convertJsonToHashMap(request.getParameter("data"));

        //Create new order
        Order newOrder = orderService.createOrder(Order.builder()
                .userId(user.getId())
                .createdAt(LocalDateTime.now())
                .status(OrderStatus.NEW)
                .build());

        double subTotal = 0;
        double grandTotal = 0;

        //Create order item base on cart and coupon it applied
        List<OrderItem> orderItems = new ArrayList<>();
        List<String> messageError = new ArrayList<>();
        Coupon mightyCoupon = null;     //If this order have applied 1 mighty coupon: Mã giảm giá toàn sàn, then only minus this coupon remanin in database by 1.
        for (Course course : coursesInCart) {
            //Create order item
            OrderItem newOrderItem = OrderItem.builder()
                    .orderId(newOrder.getId())
                    .courseId(course.getId())
                    .originalPrice(course.getPrice())
                    .build();

            //Validate coupon and applied it to get new price
            if (!courseCouponMap.get(course.getId().toString()).isEmpty()) {
                Coupon currentCoupon = couponService.getCouponByCode(courseCouponMap.get(course.getId().toString()));
                if (currentCoupon.getCourseId() == 0) {
                    mightyCoupon = currentCoupon;
                }
                try {
                    //Validated and applied success
                    if (couponService.canApplyCoupon(currentCoupon)) {
                        newOrderItem.setCouponId(currentCoupon.getId());
                        newOrderItem.setPrice(
                                course.getPrice() - (course.getPrice() * (currentCoupon.getPercent() / 100))
                        );
                        if (!currentCoupon.equals(mightyCoupon)) {
                            couponService.minusCouponRemain(currentCoupon);
                        }
                    }
                } catch (Exception couponException) {
                    newOrderItem.setPrice(course.getPrice());
                    messageError.add(couponException.getMessage());
                }
            } else {
                newOrderItem.setPrice(course.getPrice());
            }

            orderItems.add(orderItemService.createOrderITem(newOrderItem));
            subTotal += newOrderItem.getOriginalPrice();
            grandTotal += newOrderItem.getPrice();
        }

        if (mightyCoupon != null) {
            couponService.minusCouponRemain(mightyCoupon);
        }

        request.setAttribute("order", newOrder);
        request.setAttribute("messageError", messageError);
        request.setAttribute("orderItems", orderItems);
        request.setAttribute("subTotal", subTotal);
        request.setAttribute("discount", subTotal - grandTotal);
        request.setAttribute("grandTotal", grandTotal);

        if (grandTotal == 0) {
            request.setAttribute("noNeedPayment", true);
        }

        request.getRequestDispatcher(VIEW_PATH).forward(request, response);
    }
}
