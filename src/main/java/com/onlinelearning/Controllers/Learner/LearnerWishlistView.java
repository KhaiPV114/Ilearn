package com.onlinelearning.Controllers.Learner;

import com.onlinelearning.Models.Course;
import com.onlinelearning.Models.WishlistItem;
import com.onlinelearning.Services.CourseService;
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

@WebServlet(name = "LearnerWishlistView", urlPatterns = {"/dashboard/learner/wishlist"})
public class LearnerWishlistView extends HttpServlet {

    private static final String VIEW_PATH = "/dashboard/learner/wishlist.jsp";

    private final WishlistService wishlistService = new WishlistServiceImpl();

    private final CourseService courseService = new CourseServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        User user = (User) request.getSession(false).getAttribute("user");
//        if(user == null){
//            
//        }
        List<WishlistItem> wishlists = wishlistService.getWishlistByUserId(3);
        List<Course> courses = new ArrayList<>();
        for (WishlistItem wishlist : wishlists) {
            courses.add(courseService.getCourseById(wishlist.getCourseId()));
        }
        request.setAttribute("coursesInWishlist", courses);
        request.getRequestDispatcher(VIEW_PATH).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
