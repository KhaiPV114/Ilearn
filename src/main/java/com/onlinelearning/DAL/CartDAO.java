package com.onlinelearning.DAL;

import com.onlinelearning.Models.CartItem;
import java.util.List;

public interface CartDAO {

    List<CartItem> getCartByUserId(Integer userId);
    
    CartItem getCartByUserIdAndCourseId(Integer userId, Integer courseId);

    CartItem createCartItem(CartItem cartItem);

    CartItem deleteCartItem(CartItem cartItem);
}
