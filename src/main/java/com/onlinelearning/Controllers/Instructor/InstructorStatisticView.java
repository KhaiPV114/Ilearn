package com.onlinelearning.Controllers.Instructor;

import com.mysql.cj.x.protobuf.MysqlxCrud;
import com.onlinelearning.DAL.CourseDAO;
import com.onlinelearning.DAL.Impl.CourseDAOImpl;
import com.onlinelearning.Enums.CourseStatus;
import com.onlinelearning.Models.Course;
import com.onlinelearning.Models.User;
import com.onlinelearning.Services.CourseService;
import com.onlinelearning.Services.Impl.CourseServiceImpl;
import com.onlinelearning.Services.Impl.UserServiceImpl;
import com.onlinelearning.Services.UserService;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;


@WebServlet(name = "InstructorStatisticView", urlPatterns = {"/instructor/statistic/view"})
public class InstructorStatisticView extends HttpServlet {

    private final UserService userService = new UserServiceImpl();

    private final CourseService courseService = new CourseServiceImpl();

    private final String VIEW_PATH = "/dashboard/instructor/dashboard.jsp";

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getRequestDispatcher(VIEW_PATH);

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        List<Course> courses = courseService.getCourseByOwnerId(user.getId());
        int coursesNew = courses.stream()
                .filter(x -> x.getStatus() == CourseStatus.NEW).toArray().length;
        int coursesP = courses.stream()
                .filter(x -> x.getStatus() == CourseStatus.PUBLISHED).toArray().length;

        int totalLearner = courseService.getTotalLearnerOfAllCourse(user.getId());
        Map<String, List<Double>> map = courseService.getTotalProfit(user.getId());
        List<Double> totalCourses = map.get("totalCourse");
        double totalCourse = 0;
        for (Double t : totalCourses) {
            totalCourse+=t;
        }
        List<Double> totalPrices = map.get("totalPrice");
        double totalPrice = 0;
        for (Double t : totalPrices) {
            totalPrice+=t;
        }
        List<User> users = userService.getLearnerOfAllCourse(user.getId());
        System.out.println(totalCourse);
        System.out.println(totalPrice);
        System.out.println("Total learner: " + totalLearner);

        request.setAttribute("coursesNew", coursesNew);
        request.setAttribute("coursesP", coursesP);
        request.setAttribute("totalPrice", totalPrice);
        request.setAttribute("totalCourse", totalCourse);
        request.setAttribute("totalLearner", totalLearner);
        request.setAttribute("users", users);
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
