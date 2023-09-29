package com.onlinelearning.Controllers.WishlistController;

import com.onlinelearning.Models.Wishlist;
import com.onlinelearning.Models.User;
import com.onlinelearning.Services.WishlistService;
import com.onlinelearning.Services.Impl.WishlistServiceImpl;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "WishlistDelete", urlPatterns = {"/dashboard/learner/wishlist/delete"})
public class WishlistDelete extends HttpServlet {

    private static final String WISHLIST_PATH = "/dashboard/learner/wishlist.jsp";

    private final WishlistService wishlistService = new WishlistServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect(request.getContextPath()+ WISHLIST_PATH);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        int courseId = Integer.parseInt(request.getParameter("course-id"));

        if (user != null) {
            try {
                Wishlist deleteWishlist = Wishlist.builder().userId(user.getId()).courseId(courseId).build();
                wishlistService.deleteWishlist(deleteWishlist);
            } catch (Exception ex) {
                Logger.getLogger(WishlistDelete.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        response.sendRedirect(request.getContextPath() + WISHLIST_PATH);
    }
}
