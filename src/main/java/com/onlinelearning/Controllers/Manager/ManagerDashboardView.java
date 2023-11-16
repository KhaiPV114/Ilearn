/*
 * DuyDuc94
 */
package com.onlinelearning.Controllers.Manager;

import com.onlinelearning.Enums.CourseStatus;
import com.onlinelearning.Enums.OrderStatus;
import com.onlinelearning.Models.Course;
import com.onlinelearning.Models.Order;
import com.onlinelearning.Models.Role;
import com.onlinelearning.Services.CategoryService;
import com.onlinelearning.Services.CourseService;
import com.onlinelearning.Services.Impl.CategoryServiceImpl;
import com.onlinelearning.Services.Impl.CourseServiceImpl;
import com.onlinelearning.Services.Impl.OrderItemServiceImpl;
import com.onlinelearning.Services.Impl.OrderServiceImpl;
import com.onlinelearning.Services.Impl.UserServiceImpl;
import com.onlinelearning.Services.OrderItemService;
import com.onlinelearning.Services.OrderService;
import com.onlinelearning.Services.UserService;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ManagerDashboardView", urlPatterns = {"/manager/dashboard"})
public class ManagerDashboardView extends HttpServlet {

    private static final String VIEW_PATH = "/dashboard/manager/dashboard.jsp";
    private static final OrderService orderService = new OrderServiceImpl();
    private static final CategoryService categoryService = new CategoryServiceImpl();
    private static final CourseService courseService = new CourseServiceImpl();
    private static final UserService userService = new UserServiceImpl();
    private static final OrderItemService orderItemService = new OrderItemServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int totalCategories = categoryService.getAllCategories().size();
        List<Course> allCourses = courseService.getAllCourses();
        int totalCourses = allCourses.size();
        List<Course> tempCourses = new ArrayList<>();
        for (Course course : allCourses) {
            if (course.getStatus() != CourseStatus.PUBLISHED) {
                tempCourses.add(course);
            }
        }
        allCourses.removeAll(tempCourses);
        int publishCourses = allCourses.size();
        int totalInstructor = userService.getNumberOfUserAtRole(Role.INSTRUCTOR);
        int totalLearner = userService.getNumberOfUserAtRole(Role.LEARNER);

        List<Order> orders = orderService.getAllOrders();
        Double totalEarnings = 0d;

        if (orders != null) {
            for (Order order : orders) {
                if (order.getStatus().equals(OrderStatus.SUCCESSFUL)) {
                    totalEarnings += orderService.getTotalOfOrder(order);
                }
            }
        }

        request.setAttribute("totalCategories", totalCategories);
        request.setAttribute("totalCourses", totalCourses);
        request.setAttribute("publishCourses", publishCourses);
        request.setAttribute("totalInstructor", totalInstructor);
        request.setAttribute("totalLearner", totalLearner);
        request.setAttribute("totalEarnings", Math.floor(totalEarnings));
        request.getRequestDispatcher(VIEW_PATH).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
