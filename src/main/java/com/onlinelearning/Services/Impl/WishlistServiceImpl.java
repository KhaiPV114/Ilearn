package com.onlinelearning.Services.Impl;

import com.onlinelearning.DAL.WishlistDAO;
import com.onlinelearning.DAL.Impl.WishlistDAOImpl;
import com.onlinelearning.Models.WishlistItem;
import com.onlinelearning.Services.WishlistService;
import java.util.List;

public class WishlistServiceImpl implements WishlistService {

    private final WishlistDAO wishlistDAO = new WishlistDAOImpl();

     @Override
    public List<WishlistItem> getWishlistByUserId(Integer userId) {
        if (userId == null) {
            return null;
        }
        return wishlistDAO.getWishlistByUserId(userId);
    }

    @Override
    public WishlistItem getWishlistByUserIdAndCourseId(Integer userId, Integer courseId) {
        if (userId == null || courseId == null) {
            return null;
        }
        return wishlistDAO.getWishlistByUserIdAndCourseId(userId, courseId);
    }

    private void validateWishlist(Integer userId, Integer courseId) throws Exception {
        if (wishlistDAO.getWishlistByUserIdAndCourseId(userId, courseId) != null) {
            throw new Exception("Wishlist: (" + userId + ", " + courseId + ") is already exist!");
        }
    }

    @Override
    public WishlistItem createWishlistItem(WishlistItem cartItem) throws Exception {
        validateWishlist(cartItem.getUserId(), cartItem.getCourseId());
        return wishlistDAO.createWishlistItem(cartItem);
    }

    @Override
    public WishlistItem deleteWishlistItem(WishlistItem cartItem) throws Exception {
        try {
            validateWishlist(cartItem.getUserId(), cartItem.getCourseId());
        } catch (Exception cartItemIsExistException) {
            return wishlistDAO.deleteWishlistItem(cartItem);
        }
        throw new Exception("Wishlist: " + cartItem + "doesn't exist");
    }
}
