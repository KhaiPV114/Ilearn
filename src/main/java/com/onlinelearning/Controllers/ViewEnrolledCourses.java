package com.onlinelearning.Controllers;

import com.onlinelearning.Models.Course;
import com.onlinelearning.Services.CourseService;
import com.onlinelearning.Services.Impl.CourseServiceImpl;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ViewEnrolledCourses", urlPatterns = {"/dashboard/instructor/course-view"})
public class ViewEnrolledCourses extends HttpServlet {

    private static final String VIEW_PATH = "/dashboard/instructor/course-view.jsp";

    private final CourseService courseService = new CourseServiceImpl();
    
   @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        List<Integer> enrolledCourseIds = courseService.getCourseByOwnerId(Integer ownerId);

        List<Course> enrolledCourses = new ArrayList<>();
        for (Integer courseId : enrolledCourseIds) {
            enrolledCourses.add(courseService.getCourseById(courseId));
        }

        request.setAttribute("enrolledCourses", enrolledCourses);
        request.getRequestDispatcher(VIEW_PATH).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}