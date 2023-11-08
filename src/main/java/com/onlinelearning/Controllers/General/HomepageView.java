package com.onlinelearning.Controllers.General;

import com.onlinelearning.Models.Category;
import com.onlinelearning.Models.Course;
import com.onlinelearning.Services.CategoryService;
import com.onlinelearning.Services.CourseService;
import com.onlinelearning.Services.Impl.CategoryServiceImpl;
import com.onlinelearning.Services.Impl.CourseServiceImpl;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet(name = "HomepageView", urlPatterns = {""})
public class HomepageView extends HttpServlet {

    private static final String VIEW_PATH = "homepage.jsp";

    private final CategoryService categoryService = new CategoryServiceImpl();
    
    private final CourseService courseService = new CourseServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Course> courses = courseService.get3CourseByNumberOfPurchase();
        request.setAttribute("courses", courses);

        List<Category> allCategories = categoryService.getAllCategories();
        request.setAttribute("allCategories", allCategories);
        for (Category category : allCategories) {

            // Lấy danh sách khóa học theo từng category id
            List<Course> getCourseByCategoryId = courseService.getCourseByCategoryId(category.getId());

            // Gán danh sách khóa học vào attribute của category đó
            category.setCourses(getCourseByCategoryId);

        }

        request.getRequestDispatcher(VIEW_PATH).forward(request, response);
    }

}
