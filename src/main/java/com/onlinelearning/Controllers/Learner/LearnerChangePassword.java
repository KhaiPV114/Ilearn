package com.onlinelearning.Controllers.Learner;

import com.onlinelearning.Models.User;
import com.onlinelearning.Services.AuthService;
import com.onlinelearning.Services.Impl.AuthServiceImpl;
import com.onlinelearning.Services.Impl.UserServiceImpl;
import com.onlinelearning.Services.UserService;
import com.password4j.BcryptFunction;
import com.password4j.Password;
import com.password4j.types.Bcrypt;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "LearnerChangePassword", urlPatterns = {"/learner/change-password"})
public class LearnerChangePassword extends HttpServlet {

    private static final String VIEW_PATH = "/general/change-password.jsp";
    private static final AuthService authService = new AuthServiceImpl();
    private static final UserService userService = new UserServiceImpl();
    private final BcryptFunction bcrypt = BcryptFunction.getInstance(Bcrypt.A, 10);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher(VIEW_PATH).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = authService.getUser(request);
        String currentPassword = request.getParameter("currentPassword");
        String newPassword = request.getParameter("newPassword");
        String retypePassword = request.getParameter("retypePassword");

        boolean canChange = Password.check(currentPassword, user.getPassword()).with(bcrypt);
        if (!canChange) {
            request.setAttribute("error_message", "Current passwords do not match");
            request.getRequestDispatcher(VIEW_PATH).forward(request, response);
            return;
        }
        canChange = newPassword.equals(retypePassword);
        if (!canChange) {
            request.setAttribute("error_message", "New confirmation password is not correct");
            request.getRequestDispatcher(VIEW_PATH).forward(request, response);
            return;
        }

        if (canChange) {
            String hashedPassword = Password.hash(newPassword).with(bcrypt).getResult();
            user.setPassword(hashedPassword);
            user = userService.updateUser(user);
            if (user != null) {
                request.getSession().setAttribute("user", user);
                request.setAttribute("success_message", "Change password successfully");
            } else {
                request.setAttribute("error_message", "Can not change password");
            }
        }
        request.getRequestDispatcher(VIEW_PATH).forward(request, response);
    }

}
