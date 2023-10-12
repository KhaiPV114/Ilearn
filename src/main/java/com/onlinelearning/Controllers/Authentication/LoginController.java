package com.onlinelearning.Controllers.Authentication;

import com.onlinelearning.Models.User;
import com.onlinelearning.Services.AuthService;
import com.onlinelearning.Services.CartService;
import com.onlinelearning.Services.Impl.AuthServiceImpl;
import com.onlinelearning.Services.Impl.CartServiceImpl;
import com.onlinelearning.Utils.CookieUtils;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "LoginController", urlPatterns = {"/authentication/login"})
public class LoginController extends HttpServlet {

    private static final String VIEW_PATH = "/common/authentication.jsp";
    
    private static final String HOME_PATH = "/homepage";

    private static final int REMEMBER_ME_COOKIE_MAX_AGE = 60 * 60 * 24 * 7;
    
    private final CartService cartService = new CartServiceImpl();
    private final AuthService authService = new AuthServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            response.sendRedirect(request.getContextPath() + "/");
            return;
        }
        String username = CookieUtils.getCookieValue(request, "username");
        if (username != null) {
            request.setAttribute("l_username", username);
            String password = CookieUtils.getCookieValue(request, "password");
            request.setAttribute("l_password", password);
        }
        request.getRequestDispatcher(VIEW_PATH).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher viewDispatcher = request.getRequestDispatcher(VIEW_PATH);
        User user;
        try {
            user = authService.login(request);
            cartService.updateCartInSession(request.getSession(false), request, response);
        } catch (Exception ex) {
            request.setAttribute("l_error", ex.getMessage());
            viewDispatcher.forward(request, response);
            return;
        }

        Boolean rememberMe = Boolean.valueOf(request.getParameter("l_rememberme"));
        if (rememberMe) {
            String username = request.getParameter("l_username");
            String password = request.getParameter("l_password");
            Cookie usernameCookie = new Cookie("username", username);
            Cookie passwordCookie = new Cookie("password", password);
            usernameCookie.setMaxAge(REMEMBER_ME_COOKIE_MAX_AGE);
            passwordCookie.setMaxAge(REMEMBER_ME_COOKIE_MAX_AGE);
            response.addCookie(usernameCookie);
            response.addCookie(passwordCookie);
        } else {
            CookieUtils.deleteCookieByName(request, response, "username");
            CookieUtils.deleteCookieByName(request, response, "password");
        }
        response.sendRedirect(request.getContextPath() + HOME_PATH);
    }
}