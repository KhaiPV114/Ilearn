package com.onlinelearning.Controllers.Instructor;

import com.onlinelearning.Enums.UserStatus;
import com.onlinelearning.Models.User;
import com.onlinelearning.Services.Impl.UserServiceImpl;
import com.onlinelearning.Services.UserService;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

@WebServlet(name = "InstructorSearchLearner", urlPatterns = {"/instructor/learner/search"})
public class InstructorSearchLearner extends HttpServlet {

    private static final String VIEW_PATH = "/dashboard/instructor/learner-manage.jsp";

    private final UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        
        RequestDispatcher dispatcher = request.getRequestDispatcher(VIEW_PATH);

        System.out.println("_______");
        System.out.println("Start process:");

        String userName = request.getParameter("userName");
        List<User> learnerListActive = new ArrayList<>();
        List<User> learnerListBan = new ArrayList<>();
        List<User> list = new ArrayList<>();

        if (StringUtils.isBlank(userName)) {
            learnerListActive = userService.getLearnerOfAllCourseWithStatus(user.getId(), "ACTIVE");
            System.out.println("Active: " + learnerListActive);
            learnerListBan = userService.getLearnerOfAllCourseWithStatus(user.getId(), "BANNED");
            System.out.println("Banned: " + learnerListBan);
            request.setAttribute("learnerListActive", learnerListActive);
            request.setAttribute("learnerListBan", learnerListBan);
            dispatcher.forward(request, response);
            return;
        }

        list = userService.getUserByKeyword(userName);

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getStatus() == null || list.get(i).getStatus().equals(UserStatus.ACTIVE)) {
                learnerListActive.add(list.get(i));
            } else {
                learnerListBan.add(list.get(i));
            }
        }

        request.setAttribute("learnerListActive", learnerListActive);
        request.setAttribute("learnerListBan", learnerListBan);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
    }

}
