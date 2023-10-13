package com.onlinelearning.Controllers;

import com.onlinelearning.Models.Category;
import com.onlinelearning.Models.Course;
import com.onlinelearning.Services.CartService;
import com.onlinelearning.Services.CategoryService;
import com.onlinelearning.Services.CourseService;
import com.onlinelearning.Services.Impl.CartServiceImpl;
import com.onlinelearning.Services.Impl.CategoryServiceImpl;
import com.onlinelearning.Services.Impl.CourseServiceImpl;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet(name = "HomepageView", urlPatterns = {"/homepage"})
public class HomepageView extends HttpServlet {
    
    private static final String HOME_PATH = "index.jsp";
    private static final CartService CartService = new CartServiceImpl();
    private static final CategoryService CategoryService = new CategoryServiceImpl();
    private static final CourseService CourseService = new CourseServiceImpl();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CartService.updateCartInSession(request.getSession(), request, response);
        
        List<Category> allCategories = CategoryService.getAllCategories();
        request.setAttribute("allCategories", allCategories);
        
        //List<Course> getCourseByCategoryId = CourseService.getCourseByCategoryId();
        //request.setAttribute("allCourses", allCourses);

        // Duyệt qua từng category
        for (Category category : allCategories) {

        // Lấy danh sách khóa học theo từng category id
        List<Course> getCourseByCategoryId = CourseService.getCourseByCategoryId(category.getId());

          // Gán danh sách khóa học vào attribute của category đó
        category.setCourses( getCourseByCategoryId); 

        }     
        
        request.getRequestDispatcher(HOME_PATH).forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }
}
