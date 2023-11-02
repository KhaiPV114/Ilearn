package com.onlinelearning.Controllers.Learner;

import com.onlinelearning.Models.Course;
import com.onlinelearning.Models.User;
import com.onlinelearning.Services.AuthService;
import com.onlinelearning.Services.CourseService;
import com.onlinelearning.Services.Impl.AuthServiceImpl;
import com.onlinelearning.Services.Impl.CourseServiceImpl;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet(name = "ViewEnrolledCourses", urlPatterns = {"/learner/courses/enrolled"})
public class LearnerEnrolledCoursesView extends HttpServlet {

    private static final String VIEW_PATH = "/dashboard/learner/course-enrolled-view.jsp";
    private static final String HOME_PATH = "/homepage";

    private final CourseService courseService = new CourseServiceImpl();

    private final AuthService authService = new AuthServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        User user = authService.getUser(request);

        if (user == null) {
            response.sendRedirect(request.getContextPath() + HOME_PATH);
            return;
        }

        List<Course> enrolledCourses = courseService.getAllCoursesByUserId(user.getId());
        request.setAttribute("enrolledCourses", enrolledCourses);
        request.getRequestDispatcher(VIEW_PATH).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
