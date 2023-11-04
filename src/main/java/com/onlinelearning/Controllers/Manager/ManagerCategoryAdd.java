package com.onlinelearning.Controllers.Manager;

import com.onlinelearning.Models.Category;
import com.onlinelearning.Services.CategoryService;
import com.onlinelearning.Services.FileUploadService;
import com.onlinelearning.Services.Impl.CategoryServiceImpl;
import com.onlinelearning.Services.Impl.S3FileUploadServiceImpl;
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

@WebServlet(name = "ManagerCategoryAdd", urlPatterns = {"/manager/category/add"})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 10, // 5 MB
        maxFileSize = 1024 * 1024 * 10, // 10 MB
        maxRequestSize = 1024 * 1024 * 100 // 100 MB
)
public class ManagerCategoryAdd extends HttpServlet {

    private static final String FORM_PATH = "/dashboard/manager/course-category-form.jsp";

    private final FileUploadService fileUploadService = S3FileUploadServiceImpl.load();

    private final CategoryService categoryService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher(FORM_PATH).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(FORM_PATH);

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
                .name(name)
                .imageUrl(imageUrl)
                .description(description)
                .build();

        //Save category
        try {
            categoryService.createCategory(category);
            request.setAttribute("success", "Add category successfully!");
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
        }

        requestDispatcher.forward(request, response);
    }

}
