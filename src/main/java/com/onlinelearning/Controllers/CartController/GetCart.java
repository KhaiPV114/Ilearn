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
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "GetCart", urlPatterns = {"/GetCart"})
public class GetCart extends HttpServlet {
    
    private final CartService cartService = new CartServiceImpl();

    private final CourseService courseService = new CourseServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        List<Cart> carts;
        List<Course> coursesInCart = new ArrayList<>();

        if (user == null) {
            carts = cartService.getCartsFromCookie(request);
        } else {
            carts = cartService.getCartsByUserId(user.getId());
            //If in database, user haven't have cart's info
            if (carts.isEmpty()) {
                //Get cart from cookie, and add to database
                carts = cartService.getCartsFromCookie(request);
                for (Cart cart : carts) {
                    cart.setUserId(user.getId());
                    try {
                        cart = cartService.createCart(cart);
                    } catch (Exception ex) {
                    }
                }
            }
            cartService.removeCartsFromCookie(request, response);
        }

        //Get list course in cart to display
        if (!carts.isEmpty()) {
            for (Cart cart : carts) {
                coursesInCart.add(courseService.getCourseById(cart.getCourseId()));
            }
        }
        
        session.setAttribute("coursesInCart", coursesInCart);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
