package com.onlinelearning.Services;

import com.onlinelearning.Models.CartItem;
import com.onlinelearning.Models.Course;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

public interface CartService {

    List<CartItem> getCartByUserId(Integer userId);
    
    boolean deleteCartOfUserId(Integer userId);

    CartItem getCartByUserIdAndCourseId(Integer userId, Integer courseId);

    CartItem createCartItem(CartItem cartItem) throws Exception;

    CartItem deleteCartItem(CartItem cartItem) throws Exception;

    void addNewCartItemToCookie(CartItem newCartItem, HttpServletRequest request, HttpServletResponse response) throws Exception;
    
    void removeCartItemFromCookie(CartItem cartItem, HttpServletRequest request, HttpServletResponse response) throws Exception;

    List<CartItem> getCartFromCookie(HttpServletRequest request);

    void addCartToCookie(HttpServletRequest request, HttpServletResponse response, List<CartItem> cart);

    void removeCartFromCookie(HttpServletRequest request, HttpServletResponse response);

    List<Course> getCourseInCart(HttpServletRequest request, HttpServletResponse response);

}
