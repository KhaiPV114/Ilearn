package com.onlinelearning.Services;

import com.onlinelearning.Models.Wishlist;
import java.util.List;

public interface WishlistService {
    
    Wishlist getWishlistByUserId(Integer userId);

    List<Wishlist> getAllWishlists();

    Wishlist addWishlist(Wishlist wishlist) throws Exception;

    Wishlist deleteWishlist(Wishlist wishlist) throws Exception;
}

