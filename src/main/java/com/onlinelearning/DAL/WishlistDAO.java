package com.onlinelearning.DAL;

import com.onlinelearning.Models.Wishlist;
import java.util.List;

public interface WishlistDAO{
    
    List<Wishlist> getWishlistByUserId(Integer userId);
    
    Wishlist addWishlist(Wishlist wishlist);
    
    Wishlist deleteWishlist(Wishlist wishlist);

}