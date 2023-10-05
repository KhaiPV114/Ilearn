/*
 * DuyDuc94
 */
package com.onlinelearning.Controllers.Cart;

import com.onlinelearning.Models.Course;
import com.onlinelearning.Services.CourseService;
import com.onlinelearning.Services.Impl.CourseServiceImpl;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author duy20
 */
@WebServlet(name = "TestingCart", urlPatterns = {"/cart/testing"})
public class TestingCart extends HttpServlet {

    private final String VIEW_PATH = "/testing/cart.jsp";

    private final CourseService CourseService = new CourseServiceImpl();
    
    private final GeneralCartAdd GeneralCartAdd = new GeneralCartAdd();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Course> courses = CourseService.getAllCourses();
        request.setAttribute("courses", courses);
        request.getRequestDispatcher(VIEW_PATH).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
