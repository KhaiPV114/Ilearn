package com.onlinelearning.Controllers.CartController;

import com.onlinelearning.Models.Cart;
import com.onlinelearning.Models.Course;
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
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "CartController", urlPatterns = {"/cart"})
public class ViewCart extends HttpServlet {

    private static final String VIEW_PATH = "/common/cart.jsp";

    private final CartService cartService = new CartServiceImpl();

    private final CourseService courseService = new CourseServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        List<Cart> carts;
        List<Course> coursesInCart = new ArrayList<>();

        if (user == null) {
            carts = cartService.getCartsFromCookie(request);
        } else {
            carts = cartService.getCartsByUserId(user.getId());
            //If in database, user haven't have cart's info, will add cart from 
            //cookie to database, and delete old cookie on broswer
            if (carts.isEmpty()) {
                List<Cart> cartsFromCookie = cartService.getCartsFromCookie(request);
                for (Cart cart : cartsFromCookie) {
                    cart.setUserId(user.getId());
                    try {
                        cartService.createCart(cart);
                    } catch (Exception ex) {}
                }
                carts = cartService.getCartsByUserId(user.getId());
            }
            cartService.removeCartsFromCookie(request, response);
        }

        //Get list course in cart to display
        if (!carts.isEmpty()) {
            for (Cart cart : carts) {
                coursesInCart.add(courseService.getCourseById(cart.getCourseId()));
            }
        }
        request.setAttribute("coursesInCart", coursesInCart);
        request.getRequestDispatcher(VIEW_PATH).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
