package com.onlinelearning.Controllers.Manager;

import com.onlinelearning.Models.User;
import com.onlinelearning.Services.AuthService;
import com.onlinelearning.Services.Impl.AuthServiceImpl;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "ManagerViewProfile", urlPatterns = {"/manager/profile"})
public class ManagerViewProfile extends HttpServlet {

    private static final String VIEW_PATH = "/dashboard/learner/profile.jsp";
    private static final String ERROR_403_PATH = "/error/403.jsp";
    private static final AuthService authService = new AuthServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Filter
        User user = authService.getUser(request);
        if(user == null){
            request.getRequestDispatcher(ERROR_403_PATH).forward(request, response);
        }else{
            request.setAttribute("user", user);
            request.getRequestDispatcher(VIEW_PATH).forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);

    }
}
