package com.onlinelearning.Controllers.Instructor;

import com.onlinelearning.Models.User;
import com.onlinelearning.Services.Impl.UserServiceImpl;
import com.onlinelearning.Services.UserService;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;

@WebServlet(name="InstructorLearnerStatusChange", urlPatterns={"/instructor/learner/status/change"})
public class InstructorLearnerStatusChange extends HttpServlet {
   
    private static final String VIEW_PATH = "/dashboard/instructor/learner-manage.jsp";

    private final UserService userService = new UserServiceImpl();
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String status = request.getParameter("status");
        String id = request.getParameter("id");
        String username = request.getParameter("userName");
        System.out.println("Status: " + status);
        System.out.println("ID: " + id);

        if (!StringUtils.isBlank(status)) {
            if (status.equals("BANNED")) {
                User user = userService.getUser(Integer.parseInt(id));
                User userUpdateStatus = userService.updateUserStatus("ACTIVE", user);
                response.sendRedirect(request.getContextPath() + "/instructor/learner/search");
                return;
            }
            if (status.equals("ACTIVE")) {
                User user = userService.getUser(Integer.parseInt(id));
                User userUpdateStatus = userService.updateUserStatus("BANNED", user);
                response.sendRedirect(request.getContextPath() + "/instructor/learner/search");
                return;
            }
        }
    } 

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
    }

}