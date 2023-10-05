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
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "GeneralCartDelete", urlPatterns = {"/cart/remove"})
public class GeneralCartDelete extends HttpServlet {

    private final String ERROR_404_PATH = "/error/404.jsp";

    private final CartService cartService = new CartServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher(ERROR_404_PATH).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter pw = response.getWriter();
        //Validate request
        User user = (User) request.getSession().getAttribute("user");
        String courseIdParam = request.getParameter("course-id");
        if (courseIdParam == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        int courseId = Integer.parseInt(courseIdParam);

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
