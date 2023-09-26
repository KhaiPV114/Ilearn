package com.onlinelearning.DAL;

import com.onlinelearning.Models.Cart;
import java.util.List;

public interface CartDAO {

    List<Cart> getCartsByUserId(Integer userId);
    
    Cart getCartByUserIdAndCourseId(Integer userId, Integer courseId);

    Cart createCart(Cart cart);

    Cart deleteCart(Cart cart);
}
