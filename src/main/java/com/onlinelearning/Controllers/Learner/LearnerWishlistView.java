package com.onlinelearning.Controllers.Learner;

import com.onlinelearning.Models.Course;
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
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "LearnerWishlistView", urlPatterns = {"/learner/wishlist"})
public class LearnerWishlistView extends HttpServlet {

    private final String VIEW_PATH = "/dashboard/learner/wishlist.jsp";
    
    private final String HOME_PATH = "/index.jsp";

    private final WishlistService wishlistService = new WishlistServiceImpl();

    private final CourseService courseService = new CourseServiceImpl();

    private final AuthService authService = new AuthServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = authService.getUser(request);
        if (user == null) {
            response.sendRedirect(request.getContextPath() + HOME_PATH);
        } else {
            List<WishlistItem> wishlists = wishlistService.getWishlistByUserId(user.getId());
            List<Course> courses = new ArrayList<>();
            for (WishlistItem wishlist : wishlists) {
                courses.add(courseService.getCourseById(wishlist.getCourseId()));
            }
            request.setAttribute("coursesInWishlist", courses);
            request.getRequestDispatcher(VIEW_PATH).forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
