package com.onlinelearning.Controllers.Authentication;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name="Logout", urlPatterns={"/authentication/logout"})
public class Logout extends HttpServlet {
    
    private static final String HOME_PATH = "/homepage";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session != null)
            session.invalidate();
        response.sendRedirect(request.getContextPath() + HOME_PATH);
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        doGet(request, response);
    }

}
