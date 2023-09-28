package com.onlinelearning.Services;

import com.onlinelearning.Models.Wishlist;
import java.util.List;

public interface WishlistService {
    
    List<Wishlist> getWishlistByUserId(Integer userId);

    Wishlist addWishlist(Wishlist wishlist) throws Exception;

    Wishlist deleteWishlist(Wishlist wishlist) throws Exception;
}

