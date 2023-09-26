package com.onlinelearning.Services;

import com.onlinelearning.Models.Cart;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

public interface CartService {

    List<Cart> getCartsByUserId(Integer userId);
    
    Cart createCart(Cart cart) throws Exception;
    
    Cart deleteCart(Cart cart) throws Exception;
    
    List<Cart> getCartsFromCookie(HttpServletRequest request);
    
    void addCartsToCookie(HttpServletResponse response, List<Cart> carts);
    
}
