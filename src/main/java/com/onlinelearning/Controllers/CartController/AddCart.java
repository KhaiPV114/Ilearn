package com.onlinelearning.Controllers.CartController;

import com.onlinelearning.Models.Cart;
import com.onlinelearning.Models.User;
import com.onlinelearning.Services.CartService;
import com.onlinelearning.Services.Impl.CartServiceImpl;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet(name = "AddCart", urlPatterns = {"/testing/add-to-cart"})
public class AddCart extends HttpServlet {

    private static final String VIEW_PATH = "/testing/cart.jsp";
    private static final String HOME_PATH = "/homepage";

    private final CartService cartService = new CartServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + HOME_PATH);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        Integer courseId = Integer.parseInt(request.getParameter("course-id"));
        String message = "";

        //If is guest
        if (user == null) {
            List<Cart> carts = cartService.getCartsFromCookie(request);
            Cart newCart = Cart.builder().courseId(courseId).build();
            if (carts.contains(newCart)) {
                message = "This already in your cart";
            } else {
                carts.add(newCart);
                cartService.addCartsToCookie(response, carts);
            }
            request.setAttribute("messageGuest", message);
            request.getRequestDispatcher(VIEW_PATH).forward(request, response);
        } else {
            Cart newCart = Cart.builder().id(null).userId(user.getId()).courseId(courseId).build();
            Cart addedCart = cartService.createCart(newCart);
            if (addedCart == null) {
                message = "Add cart unsuccessful!";
            } else {
                message = "Add cart successful!";
            }
            request.setAttribute("messageUser", message);
            request.setAttribute("addedCart", addedCart);
            request.getRequestDispatcher(VIEW_PATH).forward(request, response);
        }
    }
}
