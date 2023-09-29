package com.onlinelearning.Filters;

import com.onlinelearning.Controllers.Authentication.GoogleRegisterController;
import com.onlinelearning.Controllers.Authentication.LoginController;
import com.onlinelearning.Models.User;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "GoogleLoginFilter", urlPatterns = {"/*"})
public class GoogleLoginFilter implements Filter {

    private final GoogleRegisterController googleRegisterController = new GoogleRegisterController();

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession(false);
        if (session == null) {
            chain.doFilter(req, res);
            return;
        }
        if (session.getAttribute("user") != null
                && ((User) session.getAttribute("user")).getGoogleEmail() != null
                && session.getAttribute("roles") == null) {
            String googleRegisterPath = request.getContextPath() + "/authentication/register/google";
            String logoutPath = request.getContextPath() + "/authentication/logout";
            System.out.println(googleRegisterPath);
            System.out.println(request.getRequestURI());
            //If request url is not equal to logout or google register url, continue to register form
            if (request.getRequestURI().equals(googleRegisterPath)
                    || request.getRequestURI().equals(logoutPath)) {
                chain.doFilter(req, res);
                return;
            }
            googleRegisterController.doGet(request, response);
        }
        chain.doFilter(req, res);
    }

}
