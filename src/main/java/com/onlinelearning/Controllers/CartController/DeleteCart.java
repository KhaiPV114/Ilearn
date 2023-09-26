package com.onlinelearning.Controllers.CartController;

import com.onlinelearning.Models.User;
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
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "DeleteCart", urlPatterns = {"/remove-cart"})
public class DeleteCart extends HttpServlet {

    private static final String VIEW_PATH = "/common/cart.jsp";

    private final CartService cartService = new CartServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
//            response.sendRedirect(request.getContextPath());
//            return;
        }
        int courseId = Integer.parseInt(request.getParameter("course-id"));
//        int userId = user.getId();
        int userId = 1;
        cartService.deleteCart(cartService.getCartsByUserIdAndCourseId(userId, courseId));
        response.sendRedirect(request.getContextPath() + "/cart");
    }
}
