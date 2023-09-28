package com.onlinelearning.Controllers.Instructor;

import com.onlinelearning.Models.Course;
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
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "InstructorCourseController", urlPatterns = {"/instructor/course"})
public class InstructorCourseView extends HttpServlet {

    private static final String VIEW_PATH = "/dashboard/instructor/course-view.jsp";

    private final CourseService courseService = new CourseServiceImpl();

    private final AuthService authService = new AuthServiceImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String sizeString = request.getParameter("size");
        String pageString = request.getParameter("page");
        Integer size = null, page = null;
        if (sizeString != null) {
            size = Integer.valueOf(sizeString);
        } else {
            size = 10;
        }
        if (pageString != null) {
            page = Integer.valueOf(pageString);
        } else {
            page = 1;
        }
        Integer userId = authService.getUserId(request);
        List<Course> courses = courseService.getCourseByOwnerId(userId, size, page);
        int total = courseService.countNumberOfCourseByOwnerId(userId, size);
        int start = Math.max(1, page - 2);
        int end = Math.min(total, page + 2);
        if (page < 3) {
            end = total < 5 ? total : 5;
        } else if (page > end - 2) {
            int tempStart = total - 4;
            start = tempStart > 0 ? tempStart : 1;
        }
        List<Integer> pages = new ArrayList<>();
        request.setAttribute("courses", courses);
        request.setAttribute("size", size);
        request.setAttribute("start", start);
        request.setAttribute("end", end);
        request.setAttribute("total", total);
        request.getRequestDispatcher(VIEW_PATH).forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
