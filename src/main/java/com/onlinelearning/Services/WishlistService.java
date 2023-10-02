package com.onlinelearning.Services;

import com.onlinelearning.Models.WishlistItem;
import java.util.List;

public interface WishlistService {
    
    List<WishlistItem> getWishlistByUserId(Integer userId);
    
    WishlistItem getWishlistByUserIdAndCourseId(Integer userId, Integer courseId);
    
    WishlistItem createWishlistItem(WishlistItem cartItem) throws Exception;
    
    WishlistItem deleteWishlistItem(WishlistItem cartItem) throws Exception;
}

