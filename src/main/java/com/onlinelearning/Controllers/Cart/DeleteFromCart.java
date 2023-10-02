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

@WebServlet(name = "DeleteCart", urlPatterns = {"/remove-cart"})
public class DeleteFromCart extends HttpServlet {
    
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
        User user = (User) request.getSession(false).getAttribute("user");
        int courseId = Integer.parseInt(request.getParameter("course-id"));

        if (user == null) {
            List<CartItem> cart = cartService.getCartFromCookie(request);
            cart.remove(CartItem.builder().courseId(courseId).build());
            cartService.addCartToCookie(response, cart);
        } else {
            try {
                cartService.deleteCartItem(
                        CartItem.builder()
                                .userId(user.getId())
                                .courseId(courseId)
                                .build()
                );
            } catch (Exception ex) {
            }
        }
        cartService.updateCartInSession(request.getSession(false), request, response);
        response.sendRedirect(request.getContextPath() + "/cart");
    }
}
