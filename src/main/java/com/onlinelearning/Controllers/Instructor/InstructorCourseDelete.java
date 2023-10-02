package com.onlinelearning.Controllers.Instructor;

import com.onlinelearning.Models.Course;
import com.onlinelearning.Services.CourseService;
import com.onlinelearning.Services.Impl.CourseServiceImpl;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name="InstructorCourseDelete", urlPatterns={"/instructor/course/delete"})
public class InstructorCourseDelete extends HttpServlet {

    private static final String VIEW_PATH = "/dashboard/instructor/course-form.jsp";

    private final CourseService courseService = new CourseServiceImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(VIEW_PATH);
        String id = request.getParameter("id");
        try {
            Course course = courseService.getCourseById(Integer.parseInt(id));
            courseService.deleteCourse(course);
        } catch (Exception e) {
        }
        response.sendRedirect(request.getContextPath() + "/instructor/course");
    }

}