package com.onlinelearning.Services;

import com.onlinelearning.Models.CartItem;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

public interface CartService {

    List<CartItem> getCartByUserId(Integer userId);
    
    CartItem getCartByUserIdAndCourseId(Integer userId, Integer courseId);
    
    CartItem createCartItem(Integer userId, Integer courseId) throws Exception;
    
    CartItem deleteCartItem(CartItem cartItem) throws Exception;
    
    List<CartItem> getCartFromCookie(HttpServletRequest request);
    
    void addCartToCookie(HttpServletResponse response, List<CartItem> carts);
    
    void removeCartFromCookie(HttpServletRequest request, HttpServletResponse response);
    
    void updateCartInSession(HttpSession session, HttpServletRequest request, HttpServletResponse response);
    
}
