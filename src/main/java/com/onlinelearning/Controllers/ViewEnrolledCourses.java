package com.onlinelearning.Controllers;

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

@WebServlet(name = "ViewEnrolledCourses", urlPatterns = {"/dashboard/learner/course-enrolled-view"})
public class ViewEnrolledCourses extends HttpServlet {

    private static final String VIEW_PATH = "/dashboard/learner/course-enrolled-view.jsp";

    private final CourseService courseService = new CourseServiceImpl();
    
    private final AuthService authService = new AuthServiceImpl();

    
   @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        User user = authService.getUser(request);
        
        if(user == null){
            response.sendRedirect(request.getContextPath() + "/dashboard/learner/course.jsp");
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