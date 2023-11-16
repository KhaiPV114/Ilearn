package com.onlinelearning.Filters;

import com.onlinelearning.Models.User;
import com.onlinelearning.Services.AuthService;
import com.onlinelearning.Services.Impl.AuthServiceImpl;
import java.io.IOException;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebFilter(
        filterName = "InstructorRoleFilter",
        urlPatterns = {
            "/instructor/*"
        }
)
public class InstructorRoleFilter implements Filter {

    private final AuthService authService = new AuthServiceImpl();

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        if (!(authService.isInstructor(request) || authService.isManager(request))) {
            response.sendRedirect(request.getContextPath() + "/error/403.jsp");
            return;
        }
        chain.doFilter(request, response);
    }

}
