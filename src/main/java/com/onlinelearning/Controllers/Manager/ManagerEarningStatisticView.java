/*
 * DuyDuc94
 */
package com.onlinelearning.Controllers.Manager;

import com.onlinelearning.Enums.OrderStatus;
import com.onlinelearning.Models.Order;
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
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author duy20
 */
@WebServlet(name = "ManagerEarningStatisticView", urlPatterns = {"/manager/earning"})
public class ManagerEarningStatisticView extends HttpServlet {

    private static final String VIEW_PATH = "/dashboard/manager/earning.jsp";
    private static final OrderService orderService = new OrderServiceImpl();
    private static final CategoryService categoryService = new CategoryServiceImpl();
    private static final CourseService courseService = new CourseServiceImpl();
    private static final UserService userService = new UserServiceImpl();
    private static final OrderItemService orderItemService = new OrderItemServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String year = request.getParameter("year");
        String month = request.getParameter("month");

        int chooseYear = LocalDate.now().getYear(), chooseMonth = LocalDate.now().getMonthValue();
        List<Order> allOrders = orderService.getAllOrders();
        Set<Integer> years = new HashSet<>();
        for (Order order : allOrders) {
            if (order.getStatus().equals(OrderStatus.SUCCESSFUL)) {
                years.add(order.getCreatedAt().getYear());
            }
        }

        if (month != null && year != null) {
            try {
                //Can't access future time
                if ((chooseYear == Integer.parseInt(year) && chooseMonth > Integer.parseInt(month)) || chooseYear > Integer.parseInt(year)) {
                    chooseYear = Integer.parseInt(year);
                    chooseMonth = Integer.parseInt(month);
                }
            } catch (Exception e) {
            }
        }

        try {
            List<Order> orders = orderService.getPaidOrdersByMonth(chooseMonth, chooseYear);
            int dayOfMonth = 0;
            double totalEarningOfMonth = 0;
            Double[] earningOfMonth = new Double[dayOfMonth];
            if (!orders.isEmpty()) {
                //if is current month (may don't have full date)
                if (chooseMonth == LocalDate.now().getMonthValue() && chooseYear == LocalDate.now().getYear()) {
                    dayOfMonth = LocalDate.now().getDayOfMonth();
                    earningOfMonth = new Double[dayOfMonth];
                    Arrays.fill(earningOfMonth, 0d);
                    for (Order order : orders) {

                        double earn = orderService.getTotalOfOrder(order);
                        earningOfMonth[order.getCreatedAt().getDayOfMonth() - 1] = earn;
                        totalEarningOfMonth += earn;
                    }
                } else {
                    YearMonth yearMonth = YearMonth.from(orders.get(0).getCreatedAt().toLocalDate());
                    dayOfMonth = yearMonth.lengthOfMonth();
                    earningOfMonth = new Double[dayOfMonth];
                    Arrays.fill(earningOfMonth, 0d);
                    for (Order order : orders) {
                        double earn = orderService.getTotalOfOrder(order);
                        earningOfMonth[order.getCreatedAt().getDayOfMonth() - 1] = earn;
                        totalEarningOfMonth += earn;
                    }
                }
            }
            request.setAttribute("earningOfMonth", earningOfMonth);
            request.setAttribute("choosedMonth", chooseMonth);
            request.setAttribute("choosedYear", chooseYear);
            request.setAttribute("years", years);
            request.setAttribute("daysOfMonth", dayOfMonth);
            request.setAttribute("totalEarningOfMonth", totalEarningOfMonth);
            request.getRequestDispatcher(VIEW_PATH).forward(request, response);
        } catch (Exception ex) {

        }

    }
}
