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

@WebServlet(name = "GeneralCartAdd", urlPatterns = {"/cart/add"})
public class GeneralCartAdd extends HttpServlet {

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

        boolean successAdding = false;
        int quantity = 0;
        
        if (user == null) { //If is guest
            //Get cart stored in cookie
            List<CartItem> cart = cartService.getCartFromCookie(request);
            
            CartItem newCartItem = CartItem.builder().courseId(courseId).build();
            
            //If cart exist new cart item, do nothing
            if (cart.contains(newCartItem)) {
                //Nothing
            } else {    //Else add cart item to cookie, and send back new quantity of cart
                cart.add(newCartItem);
                cartService.addCartToCookie(response, cart);
                quantity = cart.size();
                successAdding = true;
            }
        } else {    //If is user
            try {
                CartItem cartItem = CartItem.builder()
                        .userId(user.getId())
                        .courseId(courseId)
                        .build();
                cartService.createCartItem(cartItem);
                successAdding = true;
            } catch (Exception ex) {
                //Failed, do nothing
            }
        }
        
        cartService.updateCartInSession(request.getSession(false), request, response);
    }
}
