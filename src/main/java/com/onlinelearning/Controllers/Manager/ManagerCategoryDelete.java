package com.onlinelearning.Controllers.Manager;

import com.onlinelearning.Models.Category;
import com.onlinelearning.Services.CategoryService;
import com.onlinelearning.Services.Impl.CategoryServiceImpl;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "ManagerCategoryDelete", urlPatterns = {"/manager/category/delete"})
public class ManagerCategoryDelete extends HttpServlet {

    private static final String VIEW_PATH = "/dashboard/manager/course-category-form.jsp";

    private final CategoryService categoryService = new CategoryServiceImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(VIEW_PATH);
        String id = request.getParameter("id");
        try {
            Category category = categoryService.getCategoryById(Integer.parseInt(id));
            categoryService.deleteCategory(category);
        } catch (Exception e) {
        }
        response.sendRedirect(request.getContextPath() + "/manager/category");
    }

}
