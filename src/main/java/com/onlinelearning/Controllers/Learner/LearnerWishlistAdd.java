package com.onlinelearning.Controllers.Learner;

import com.onlinelearning.Models.User;
import com.onlinelearning.Models.WishlistItem;
import com.onlinelearning.Services.AuthService;
import com.onlinelearning.Services.CourseService;
import com.onlinelearning.Services.Impl.AuthServiceImpl;
import com.onlinelearning.Services.Impl.CourseServiceImpl;
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

@WebServlet(name = "LearnerWishlistAdd", urlPatterns = {"/add-to-wishlist"})
public class LearnerWishlistAdd extends HttpServlet {

    private final WishlistService wishlistService = new WishlistServiceImpl();

    private final AuthService authService = new AuthServiceImpl();

    private final String VIEW_PATH = "/dashboard/learner/wishlist";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

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
                wishlistService.createWishlistItem(wishlistItem);
            } catch (Exception ex) {
                Logger.getLogger(LearnerWishlistAdd.class.getName()).log(Level.SEVERE, null, ex);
            }
            response.sendRedirect(request.getContextPath() + VIEW_PATH);
        }
    }
}
