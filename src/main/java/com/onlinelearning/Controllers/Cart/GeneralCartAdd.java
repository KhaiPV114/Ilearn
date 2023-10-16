package com.onlinelearning.Controllers.Cart;

import com.onlinelearning.Enums.CourseStatus;
import com.onlinelearning.Models.CartItem;
import com.onlinelearning.Models.Course;
import com.onlinelearning.Models.User;
import com.onlinelearning.Services.AuthService;
import com.onlinelearning.Services.CartService;
import com.onlinelearning.Services.CourseService;
import com.onlinelearning.Services.Impl.AuthServiceImpl;
import com.onlinelearning.Services.Impl.CartServiceImpl;
import com.onlinelearning.Services.Impl.CourseServiceImpl;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@WebServlet(name = "GeneralCartAdd", urlPatterns = {"/cart/add"})
public class GeneralCartAdd extends HttpServlet {

    private final String ERROR_404_PATH = "/error/404.jsp";

    private final AuthService AuthService = new AuthServiceImpl();
    private final CartService CartService = new CartServiceImpl();
    private final CourseService CourseService = new CourseServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher(ERROR_404_PATH).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter pw = response.getWriter();

        //Get and validate course need add to cart from request
        String courseId = request.getParameter("course-id");
        Course course;
        try {
            course = CourseService.getCourseById(Integer.parseInt(courseId));
            if (course != null) {
                if(course.getStatus().equals(CourseStatus.ARCHIVED) || course.getStatus().equals(CourseStatus.NEW)){
                    throw new Exception("Course not available: This course have been archived or unpublished");
                }
            } else {
                throw new Exception("Invalid course: Cannot add to your cart");
            }
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            pw.print(e.getMessage());
            return;
        }

        CartItem cartItemNeedAdd = CartItem.builder()
                .courseId(course.getId())
                .build();

        //Add from cart
        User user = AuthService.getUser(request);
        boolean addedToCart = true;
        if (user == null) {
            try {
                CartService.addNewCartItemToCookie(cartItemNeedAdd, request, response);
            } catch (Exception cartException) {
                pw.print(cartException.getMessage());
                addedToCart = false;
            }
        } else {
            cartItemNeedAdd.setUserId(user.getId());
            if (CourseService.isEnrolled(user.getId(), cartItemNeedAdd.getCourseId())) {
                addedToCart = false;
            } else {
                try {
                    CartService.createCartItem(cartItemNeedAdd);
                } catch (Exception cartException) {
                    pw.print(cartException.getMessage());
                    addedToCart = false;
                }
            }
        }

        //Response to client 
        if (addedToCart) {
            CartService.updateCartInSession(request.getSession(), request, response);
            response.setStatus(HttpServletResponse.SC_OK);
            pw.print("Add to cart successful!");
        } else {
            response.setStatus(HttpServletResponse.SC_CONFLICT);
        }

    }
}
