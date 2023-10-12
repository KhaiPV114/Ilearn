package com.onlinelearning.Controllers;

import com.onlinelearning.Services.CartService;
import com.onlinelearning.Services.Impl.CartServiceImpl;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "HomepageView", urlPatterns = {"/homepage"})
public class HomepageView extends HttpServlet {
    
    private static final String HOME_PATH = "index.jsp";
    private static final CartService CartService = new CartServiceImpl();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CartService.updateCartInSession(request.getSession(), request, response);
        request.getRequestDispatcher(HOME_PATH).forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }
}
