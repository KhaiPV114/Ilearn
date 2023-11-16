package com.onlinelearning.Controllers.Learner;

import com.onlinelearning.Models.User;
import com.onlinelearning.Models.WishlistItem;
import com.onlinelearning.Services.AuthService;
import com.onlinelearning.Services.Impl.AuthServiceImpl;
import com.onlinelearning.Services.Impl.WishlistServiceImpl;
import com.onlinelearning.Services.WishlistService;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "LearnerWishlistDelete", urlPatterns = {"/learner/wishlist/delete"})
public class LearnerWishlistDelete extends HttpServlet {

    private static final String VIEW_PATH = "/dashboard/learner/wishlist.jsp";

    private final WishlistService wishlistService = new WishlistServiceImpl();

    private final AuthService authService = new AuthServiceImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = authService.getUser(request);
        if (user != null) {
            int courseId = Integer.parseInt(request.getParameter("course-id"));
            WishlistItem wishlistItem = WishlistItem.builder()
                    .userId(user.getId())
                    .courseId(courseId)
                    .build();
            try {
                wishlistService.deleteWishlistItem(wishlistItem);
            } catch (Exception ex) {
                Logger.getLogger(LearnerWishlistDelete.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            String referringPage = request.getHeader("referer");
            if (referringPage != null) {
                response.sendRedirect(referringPage);
            } else {
                response.sendRedirect(request.getContextPath() + VIEW_PATH);
            }
        }
    }
}

