package com.onlinelearning.Controllers.Learner;

import com.onlinelearning.Models.User;
import com.onlinelearning.Services.AuthService;
import com.onlinelearning.Services.Impl.AuthServiceImpl;
import com.onlinelearning.Services.Impl.UserServiceImpl;
import com.onlinelearning.Services.UserService;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;

@WebServlet(name = "LearnerUpdateProfile", urlPatterns = {"/learner/profile"})
public class LearnerUpdateProfile extends HttpServlet {

    private static final String VIEW_PATH = "/dashboard/learner/profile.jsp";
    private static final String ERROR_403_PATH = "/error/403.jsp";
    private static final AuthService authService = new AuthServiceImpl();
    private static final UserService userService = new UserServiceImpl();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Filter
        User user = authService.getUser(request);
        if (user == null) {
            request.getRequestDispatcher(ERROR_403_PATH).forward(request, response);
        } else {
            request.setAttribute("user", user);
            request.getRequestDispatcher(VIEW_PATH).forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = authService.getUser(request);
        String fullname = request.getParameter("fullname");
        String phoneNumber = request.getParameter("phoneNumber");
        String dob = request.getParameter("dob");

        user.setFullName(fullname);
        user.setPhoneNumber(phoneNumber);
        user.setDob(Date.valueOf(dob));
        
        user = userService.updateUser(user);
        
        if(user != null){
            request.setAttribute("message", "Update successfully");
        }else{
            request.setAttribute("message", "Update failed");
        }
        request.setAttribute("user", user);
        request.getRequestDispatcher(VIEW_PATH).forward(request, response);
    }
}
