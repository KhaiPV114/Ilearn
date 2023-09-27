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
        
        //If is guest, delete course id of carts in Cookie
        if (user == null) {
            List<Cart> carts = cartService.getCartsFromCookie(request);
            Cart deleteCart = Cart.builder().courseId(courseId).build();
            carts.remove(deleteCart);
            cartService.addCartsToCookie(response, carts);
            response.sendRedirect(request.getContextPath() + "/cart");
        } else {
            int userId = user.getId();
            cartService.deleteCart(cartService.getCartsByUserIdAndCourseId(userId, courseId));
            response.sendRedirect(request.getContextPath() + "/cart");
        }

    }
}
