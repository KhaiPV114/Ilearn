package com.onlinelearning.Services;

import com.onlinelearning.Models.CartItem;
import com.onlinelearning.Models.Course;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

public interface CartService {

    void addNewCartItemToCookie(CartItem newCartItem, HttpServletRequest request, HttpServletResponse response) throws Exception;

    void removeCartItemFromCookie(CartItem cartItem, HttpServletRequest request, HttpServletResponse response) throws Exception;

    List<Course> getCourseInCart(HttpServletRequest request, HttpServletResponse response);

    List<CartItem> getCartByUserId(Integer userId);

    boolean deleteCartOfUserId(Integer userId);

    CartItem getCartByUserIdAndCourseId(Integer userId, Integer courseId);

    CartItem createCartItem(CartItem cartItem) throws Exception;

    CartItem deleteCartItem(CartItem cartItem) throws Exception;

}
