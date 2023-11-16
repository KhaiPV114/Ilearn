package com.onlinelearning.Controllers.Learner;

import com.onlinelearning.Models.Course;
import com.onlinelearning.Models.User;
import com.onlinelearning.Services.AuthService;
import com.onlinelearning.Services.CourseService;
import com.onlinelearning.Services.Impl.AuthServiceImpl;
import com.onlinelearning.Services.Impl.CourseServiceImpl;
import com.onlinelearning.Utils.Constants;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet(name = "LearnerStatisticView", urlPatterns = {"/learner/dashboard"})
public class LearnerStatisticView extends HttpServlet {

    private final CourseService courseService = new CourseServiceImpl();
    
    private final AuthService authService = new AuthServiceImpl();
     
    private static final String HOME_PATH = Constants.HOME_PATH;
    
    private final String VIEW_PATH = "/dashboard/learner/dashboard.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Get the user ID from the session or wherever it is stored
        User user = authService.getUser(request);
        if (user == null) {
            response.sendRedirect(request.getContextPath() + HOME_PATH);
            return;
        } 

        // Fetch the enrolled courses for the user
        List<Course> enrolledCourses = courseService.getEnrolledCourseOfUserId(user.getId());

        // Set the enrolledCourses as an attribute in the request scope
        request.setAttribute("enrolledCourses", enrolledCourses);
        request.getRequestDispatcher(VIEW_PATH).forward(request, response);       
    }   

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
