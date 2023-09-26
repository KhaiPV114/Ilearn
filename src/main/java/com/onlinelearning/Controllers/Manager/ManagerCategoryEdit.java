package com.onlinelearning.Controllers.Manager;

import com.onlinelearning.Models.Category;
import com.onlinelearning.Services.CategoryService;
import com.onlinelearning.Services.FileUploadService;
import com.onlinelearning.Services.Impl.CategoryServiceImpl;
import com.onlinelearning.Services.Impl.FileUploadServiceImpl;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import org.apache.commons.lang3.StringUtils;

@WebServlet(name = "ManagerCategoryEdit", urlPatterns = {"/manager/category/edit"})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 10, // 5 MB
        maxFileSize = 1024 * 1024 * 10, // 10 MB
        maxRequestSize = 1024 * 1024 * 100 // 100 MB
)
public class ManagerCategoryEdit extends HttpServlet {

    private static final String FORM_PATH = "/dashboard/manager/course-category-form.jsp";

    private final FileUploadService fileUploadService = FileUploadServiceImpl.load();

    private final CategoryService categoryService = new CategoryServiceImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(FORM_PATH);
        String id = request.getParameter("id");
        try {
            Category category = categoryService.getCategoryById(Integer.parseInt(id));
            request.setAttribute("id", category.getId());
            request.setAttribute("name", category.getName());
            request.setAttribute("description", category.getDescription());
            request.setAttribute("imageUrl", category.getImageUrl());
            requestDispatcher.forward(request, response);
            return;
        } catch (ServletException | IOException | NumberFormatException e) {
        }
        response.sendRedirect(request.getContextPath() + "/manager/category");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(FORM_PATH);
        
        Integer id = Integer.parseInt(request.getParameter("id"));

        //Get name
        String name = request.getParameter("name");
        if (StringUtils.isBlank(name)) {
            request.setAttribute("nameError", "Category name must not be empty!");
            requestDispatcher.forward(request, response);
            return;
        }
        request.setAttribute("name", name);

        //Get description
        String description = request.getParameter("description");
        if (StringUtils.isBlank(description)) {
            request.setAttribute("descriptionError", "Description must not be empty!");
            requestDispatcher.forward(request, response);
            return;
        }
        request.setAttribute("description", description);

        //Get image
        Part imagePart = request.getPart("image");
        String imageUrl = null;
        if (imagePart != null && imagePart.getSize() > 0) {
            try {
                imageUrl = fileUploadService.uploadImage(imagePart);
                request.setAttribute("imageUrl", imageUrl);
            } catch (Exception e) {
                request.setAttribute("imageError", e.getMessage());
                requestDispatcher.forward(request, response);
                return;
            }
        }

        //Create category
        Category category = Category.builder()
                .id(id)
                .name(name)
                .imageUrl(imageUrl)
                .description(description)
                .build();

        //Save category
        try {
            categoryService.updateCategory(category);
            request.setAttribute("success", "Update category successfully!");
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
        }

        requestDispatcher.forward(request, response);
    }

}
