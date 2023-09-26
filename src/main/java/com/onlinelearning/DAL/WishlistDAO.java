package com.onlinelearning.DAL;

import com.onlinelearning.Models.Wishlist;
import java.util.List;

public interface WishlistDAO{
    Wishlist getWishlistById(Integer id);
    
    List<Wishlist> getAllWishlists();
    
    Wishlist addWishlist(Wishlist wishlist);
    
    Wishlist deleteWishlist(Wishlist wishlist);

}