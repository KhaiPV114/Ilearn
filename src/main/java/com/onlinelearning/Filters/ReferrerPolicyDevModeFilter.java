package com.onlinelearning.Filters;

import java.io.IOException;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;

@WebFilter(filterName = "ReferrerPolicyDevModeFilter", urlPatterns = {"/authentication/*"})
public class ReferrerPolicyDevModeFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        chain.doFilter(request, response);

        HttpServletResponse httpServletResponse = ((HttpServletResponse) response);
        httpServletResponse.addHeader("Referrer-Policy", "no-referrer");
    }

}
