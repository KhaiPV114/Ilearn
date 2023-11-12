package com.onlinelearning.Controllers.Instructor;

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
import java.util.ArrayList;
import java.util.List;

@WebServlet(name="InstructorViewProfile", urlPatterns={"/instructor/profile"})
public class InstructorViewProfile extends HttpServlet {
    private static final String VIEW_PATH = "/dashboard/instructor/profile.jsp";

    private static final String HOME_PATH = "/homepage";
    private final AuthService authService = new AuthServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        User user = authService.getUser(request);
        if (user == null){
            response.sendRedirect(request.getContextPath() + HOME_PATH);
        } else {
            request.getRequestDispatcher(VIEW_PATH).forward(request, response);
        }
        
    }

}