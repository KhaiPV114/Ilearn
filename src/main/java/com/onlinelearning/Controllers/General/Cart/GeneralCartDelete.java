package com.onlinelearning.Controllers.General.Cart;

import com.onlinelearning.Models.CartItem;
import com.onlinelearning.Models.User;
import com.onlinelearning.Services.AuthService;
import com.onlinelearning.Services.CartService;
import com.onlinelearning.Services.Impl.AuthServiceImpl;
import com.onlinelearning.Services.Impl.CartServiceImpl;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@WebServlet(name = "GeneralCartDelete", urlPatterns = {"/cart/remove"})
public class GeneralCartDelete extends HttpServlet {

    private final String ERROR_404_PATH = "/error/404.jsp";

    private final AuthService AuthService = new AuthServiceImpl();
    private final CartService CartService = new CartServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher(ERROR_404_PATH).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter pw = response.getWriter();

        //Get Cart item need delete from request
        Integer courseId;
        try {
            courseId = Integer.parseInt(request.getParameter("course-id"));
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        CartItem cartItemNeedDelete = CartItem.builder().courseId(courseId).build();

        //Delete from cart
        User user = AuthService.getUser(request);
        boolean removedFromCart = true;
        if (user == null) {
            try {
                CartService.removeCartItemFromCookie(cartItemNeedDelete, request, response);
            } catch (Exception ex) {
                removedFromCart = false;
                pw.print(ex.getMessage());
            }
        } else {
            cartItemNeedDelete.setUserId(user.getId());
            try {
                CartService.deleteCartItem(cartItemNeedDelete);
            } catch (Exception ex) {
                removedFromCart = false;
                pw.print(ex.getMessage());
            }
        }

        //Response to client 
        if (removedFromCart) {
            response.setStatus(HttpServletResponse.SC_OK);
            pw.print("Remove from cart successful!");
        } else {
            response.setStatus(HttpServletResponse.SC_CONFLICT);
        }
    }
}
