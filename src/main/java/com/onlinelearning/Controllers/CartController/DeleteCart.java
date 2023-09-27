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
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "DeleteCart", urlPatterns = {"/remove-cart"})
public class DeleteCart extends HttpServlet {

    private static final String VIEW_PATH = "/common/cart.jsp";
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
        int courseId = Integer.parseInt(request.getParameter("course-id"));

        if (user == null) {
            List<Cart> carts = cartService.getCartsFromCookie(request);
            Cart deleteCart = Cart.builder().courseId(courseId).build();
            carts.remove(deleteCart);
            cartService.addCartsToCookie(response, carts);
            response.sendRedirect(request.getContextPath() + "/cart");
        } else {
            try {
                Cart deleteCart = Cart.builder().userId(user.getId()).courseId(courseId).build();
                cartService.deleteCart(deleteCart);
            } catch (Exception ex) {
                Logger.getLogger(DeleteCart.class.getName()).log(Level.SEVERE, null, ex);
            }
            response.sendRedirect(request.getContextPath() + "/cart");
        }

    }
}
