package com.onlinelearning.Controllers.Learner;

import com.onlinelearning.DAL.CourseDAO;
import com.onlinelearning.Services.CourseService;
import com.onlinelearning.Services.Impl.CourseServiceImpl;
import com.onlinelearning.Services.Impl.UserServiceImpl;
import com.onlinelearning.Services.UserService;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name="LearnerFilterCourse", urlPatterns={"/course/filter"})
public class LearnerFilterCourse extends HttpServlet {
   
    private final CourseService courseService = new CourseServiceImpl();
    
    private final UserService userService = new UserServiceImpl();
    
//    private final String VIEW_PATH = ""
    
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
    } 

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
    }

}