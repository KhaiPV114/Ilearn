package com.onlinelearning.DAL;

import com.onlinelearning.Models.WishlistItem;
import java.util.List;

public interface WishlistDAO{
    
    List<WishlistItem> getWishlistByUserId(Integer userId);
    
    WishlistItem getWishlistByUserIdAndCourseId(Integer userId, Integer courseId);
    
    WishlistItem createWishlistItem(WishlistItem wishlistItem);
    
    WishlistItem deleteWishlistItem(WishlistItem wishlistItem);

}