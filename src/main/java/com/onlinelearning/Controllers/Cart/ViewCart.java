package com.onlinelearning.Controllers.Cart;

import com.onlinelearning.Services.CartService;
import com.onlinelearning.Services.CourseService;
import com.onlinelearning.Services.Impl.CartServiceImpl;
import com.onlinelearning.Services.Impl.CourseServiceImpl;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "CartController", urlPatterns = {"/cart"})
public class ViewCart extends HttpServlet {

    private static final String VIEW_PATH = "/common/cart.jsp";

    private final CartService cartService = new CartServiceImpl();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        cartService.updateCartInSession(request.getSession(false), request, response);
        request.getRequestDispatcher(VIEW_PATH).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
