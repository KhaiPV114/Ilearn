package com.onlinelearning.Controllers.General.Authentication;

import com.onlinelearning.Models.User;
import com.onlinelearning.Services.AuthService;
import com.onlinelearning.Services.Impl.AuthServiceImpl;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "GoogleLoginController", urlPatterns = {"/authentication/login-with-google"})
public class GoogleLoginController extends HttpServlet {

    private static final String VIEW_PATH = "/general/authentication.jsp";
    private static final String HOME_PATH = "/homepage";

    private final AuthService authService = new AuthServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/authentication");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher viewDispatcher = request.getRequestDispatcher(VIEW_PATH);
        User user;
        try {
            user = authService.googleLogin(request);
        } catch (Exception ex) {
            request.setAttribute("g_error", ex.getMessage());
            viewDispatcher.forward(request, response);
            return;
        }
        response.sendRedirect(request.getContextPath() + HOME_PATH);
    }

}
