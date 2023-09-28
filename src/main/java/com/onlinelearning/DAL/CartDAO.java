package com.onlinelearning.DAL;

import com.onlinelearning.Models.CartItem;
import java.util.List;

public interface CartDAO {

    List<CartItem> getCartByUserId(Integer userId);
    
    CartItem getCartByUserIdAndCourseId(Integer userId, Integer courseId);

    CartItem createCart(Integer userId, Integer courseId);

    CartItem deleteCart(CartItem cartItem);
}
