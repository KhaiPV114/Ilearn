package com.onlinelearning.Services;

import com.onlinelearning.Models.Cart;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

public interface CartService {

    List<Cart> getCartsByUserId(Integer userId);
    
    Cart getCartsByUserIdAndCourseId(Integer userId, Integer courseId);
    
    Cart createCart(Cart cart);
    
    Cart deleteCart(Cart cart);
    
    List<Cart> getCartsFromCookie(HttpServletRequest request);
    
    void addCartsToCookie(HttpServletResponse response, List<Cart> carts);
    
}