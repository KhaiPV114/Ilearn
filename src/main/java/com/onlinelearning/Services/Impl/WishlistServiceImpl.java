package com.onlinelearning.Services.Impl;

import com.onlinelearning.DAL.WishlistDAO;
import com.onlinelearning.DAL.Impl.WishlistDAOImpl;
import com.onlinelearning.Models.Wishlist;
import com.onlinelearning.Services.WishlistService;
import java.util.List;

public class WishlistServiceImpl implements WishlistService {

    private final WishlistDAO wishlistDAO = new WishlistDAOImpl();

    @Override
    public List<Wishlist> getWishlistByUserId(Integer userId) {
        if (userId == null) {
            return null;
        }
        return wishlistDAO.getWishlistByUserId(userId);
    }

    @Override
    public Wishlist addWishlist(Wishlist wishlist) throws Exception {
        Wishlist addedWishlist = wishlistDAO.addWishlist(wishlist);
        if (addedWishlist == null) {
            throw new Exception("Add wishlist failed!");
        }
        return addedWishlist;
    }

    @Override
    public Wishlist deleteWishlist(Wishlist wishlist) throws Exception {
        if (wishlist.getId() == null) {
            throw new Exception("Wishlist id must not be empty!");
        }
        Wishlist deletedWishlist = wishlistDAO.deleteWishlist(wishlist);
        if (deletedWishlist == null) {
            throw new Exception("Delete wishlist failed!");
        }
        return deletedWishlist;
    }
}
