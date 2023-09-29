package com.onlinelearning.Controllers.Wishlist;

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

    }
}
