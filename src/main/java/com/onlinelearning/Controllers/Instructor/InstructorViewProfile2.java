package com.onlinelearning.Controllers.Instructor;

import com.onlinelearning.Models.Category;
import com.onlinelearning.Models.Course;
import com.onlinelearning.Models.User;
import com.onlinelearning.Services.AuthService;
import com.onlinelearning.Services.CourseService;
import com.onlinelearning.Services.Impl.AuthServiceImpl;
import com.onlinelearning.Services.Impl.CourseServiceImpl;
import com.onlinelearning.Services.Impl.UserServiceImpl;
import com.onlinelearning.Services.UserService;
import com.onlinelearning.Utils.Constants;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

@WebServlet(name = "InstructorViewProfile2", urlPatterns = {"/instructor/profile2"})
public class InstructorViewProfile2 extends HttpServlet {

    private static final String FORM_PATH = "/dashboard/instructor/profile2.jsp";
    
    private static final String HOME_PATH = "/homepage";

    private final AuthService authService = new AuthServiceImpl();

    private final UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(FORM_PATH);

        Integer userId = authService.getUserId(request);

        //String id = request.getParameter("id");
        try {
            User user = userService.getUser(userId);
            request.setAttribute("CreatedAt", user.getCreatedAt());
            request.setAttribute("id", user.getId());
            request.setAttribute("name", user.getFullName());
            request.setAttribute("username", user.getUsername());
            request.setAttribute("email", user.getEmail());
            request.setAttribute("gmail", user.getGoogleEmail());
            request.setAttribute("phone", user.getPhoneNumber());
            request.setAttribute("imageUrl", user.getImageUrl());
            request.setAttribute("description", user.getDescription());
            requestDispatcher.forward(request, response);
            return;
        } catch (ServletException | IOException | NumberFormatException e) {

        }
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(FORM_PATH);

        Integer userId = authService.getUserId(request);

        //Get name
        String name = request.getParameter("name");
        if (StringUtils.isBlank(name)) {
            request.setAttribute("nameError", "Full Name name must not be empty!");
            requestDispatcher.forward(request, response);
            return;
        }
        request.setAttribute("name", name);

        //Get description
        String description = request.getParameter("description");
        if (StringUtils.isBlank(description)) {
            request.setAttribute("descriptionError", "Description must not be empty!");
            requestDispatcher.forward(request, response);
            return;
        }
        request.setAttribute("description", description);

        User currentUser = userService.getUser(userId);

        currentUser.setFullName(name);
        currentUser.setDescription(description);
        


        //Save category
        try {
            userService.updateUser(currentUser);
            request.setAttribute("success", "Update category successfully!");
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
        }

        requestDispatcher.forward(request, response);

    }
}
