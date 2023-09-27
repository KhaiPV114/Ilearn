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
import java.util.List;


@WebServlet(name = "WishlistView", urlPatterns = {"/dashboard/learner/wishlist"})
public class WishlistView extends HttpServlet {

    private static final String VIEW_PATH = "/dashboard/learner/wishlist.jsp";

    private final WishlistService wishlistService = new WishlistServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {

            List<Wishlist> wishlists = wishlistService.getAllWishlists();
            request.setAttribute("wishlists", wishlists);
            request.getRequestDispatcher(VIEW_PATH).forward(request, response);

        } catch (ServletException | IOException e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher(VIEW_PATH).forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
