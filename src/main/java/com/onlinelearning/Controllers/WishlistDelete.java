package com.onlinelearning.Controllers;

import com.onlinelearning.Models.Wishlist;
import com.onlinelearning.Services.Impl.WishlistServiceImpl;
import com.onlinelearning.Services.WishlistService;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "WishlistDelete", urlPatterns = {"/dashboard/learner/wishlist/delete"})
public class WishlistDelete extends HttpServlet {

    private static final String VIEW_PATH = "/dashboard/learner/wishlist.jsp";
    private final WishlistService wishlistService = new WishlistServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {

            String id = request.getPathInfo();
            int wishlistId = Integer.parseInt(id.substring(id.lastIndexOf('/') + 1));

            Wishlist wishlist = wishlistService.getWishlistByUserId(wishlistId);

            if (wishlist == null) {
                throw new Exception("Wishlist not found");
            }

            wishlistService.deleteWishlist(wishlist);

            request.getRequestDispatcher(VIEW_PATH).forward(request, response);

        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher(VIEW_PATH).forward(request, response);
        }

    }

    
}
