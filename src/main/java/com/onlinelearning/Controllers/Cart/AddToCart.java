package com.onlinelearning.Controllers.Cart;

import com.onlinelearning.Models.CartItem;
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

@WebServlet(name = "AddToCart", urlPatterns = {"/add-to-cart"})
public class AddToCart extends HttpServlet {

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
        String message;

        if (user == null) {
            List<CartItem> cart = cartService.getCartFromCookie(request);
            CartItem newCartItem = CartItem.builder().courseId(courseId).build();
            if (cart.contains(newCartItem)) {
                message = "This already in your cart";
            } else {
                cart.add(newCartItem);
                cartService.addCartToCookie(response, cart);
                message = "Add cart to cookies successful!";
            }
        } else {
            try {
                cartService.createCartItem(user.getId(), courseId);
                message = "Add cart to database successful!";
            } catch (Exception ex) {
                message = ex.getMessage();
            }
        }
        
        cartService.updateCartInSession(request.getSession(false), request, response);
        request.setAttribute("messageAddToCart", message);
        request.getRequestDispatcher(VIEW_PATH).forward(request, response);
    }
}
