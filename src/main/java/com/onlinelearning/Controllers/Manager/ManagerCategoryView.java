package com.onlinelearning.Controllers.Manager;

import com.onlinelearning.Models.Category;
import com.onlinelearning.Services.CategoryService;
import com.onlinelearning.Services.Impl.CategoryServiceImpl;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet(name="ManagerCategoryController", urlPatterns={"/manager/category"})
public class ManagerCategoryView extends HttpServlet {
    
    private static final String VIEW_PATH = "/dashboard/manager/course-category-view.jsp";
    
    private final CategoryService categoryService = new CategoryServiceImpl();
   
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        List<Category> categories = categoryService.getAllCategories();
        request.setAttribute("categories", categories);
        request.getRequestDispatcher(VIEW_PATH).forward(request, response);
    } 

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
    }

}
