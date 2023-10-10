package com.onlinelearning.Controllers.Instructor;

import com.onlinelearning.Models.Category;
import com.onlinelearning.Models.Course;
import com.onlinelearning.Models.User;
import com.onlinelearning.Services.AuthService;
import com.onlinelearning.Services.CategoryService;
import com.onlinelearning.Services.CourseService;
import com.onlinelearning.Services.FileUploadService;
import com.onlinelearning.Services.Impl.AuthServiceImpl;
import com.onlinelearning.Services.Impl.CategoryServiceImpl;
import com.onlinelearning.Services.Impl.CourseServiceImpl;
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
import java.util.List;
import org.apache.commons.lang3.StringUtils;

@WebServlet(name = "InstructorCourseEdit", urlPatterns = {"/instructor/course/edit"})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 10, // 5 MB
        maxFileSize = 1024 * 1024 * 10, // 10 MB
        maxRequestSize = 1024 * 1024 * 100 // 100 MB
)
public class InstructorCourseEdit extends HttpServlet {

    private static final String FORM_PATH = "/dashboard/instructor/course-form.jsp";

    private final FileUploadService fileUploadService = FileUploadServiceImpl.load();

    private final CategoryService categoryService = new CategoryServiceImpl();

    private final CourseService courseService = new CourseServiceImpl();
    
    private final AuthService authService = new AuthServiceImpl();


    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Category> categories = categoryService.getAllCategories();
        request.setAttribute("categories", categories);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(FORM_PATH);
        String id = request.getParameter("id");
        try {
            Course course = courseService.getCourseById(Integer.parseInt(id));
            request.setAttribute("name", course.getName());
            request.setAttribute("category", course.getCategoryId());
            request.setAttribute("price", course.getPrice());
            request.setAttribute("description", course.getDescription());
            request.setAttribute("imageUrl", course.getImageUrl());
            requestDispatcher.forward(request, response);
            return;
        } catch (ServletException | IOException | NumberFormatException e) {
        }
        response.sendRedirect(request.getContextPath() + "/instructor/course");
    }
    
    

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(FORM_PATH);
        
        Integer id = Integer.parseInt(request.getParameter("id"));

        //Get name
        String name = request.getParameter("name");
        if (StringUtils.isBlank(name)) {
            request.setAttribute("nameError", "Course name must not be empty!");
            requestDispatcher.forward(request, response);
            return;
        }
        request.setAttribute("name", name);
        
        String categoryIdString = request.getParameter("category");
        if (StringUtils.isBlank(categoryIdString)) {
            request.setAttribute("categoryError", "Category must bot be empty!");
            requestDispatcher.forward(request, response);
            return;
        }
        Integer categoryId = Integer.valueOf(categoryIdString);
        request.setAttribute("categoryID", categoryIdString);

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
        
        String priceString = request.getParameter("price");
        if (StringUtils.isBlank(priceString)) {
            request.setAttribute("priceError", "Price must not be empty!");
            requestDispatcher.forward(request, response);
        }
        Double price = Double.valueOf(priceString);
        request.setAttribute("price", priceString);
        
        User currentUser = authService.getUser(request);

        
        
        //Create category
        Course course = Course.builder()
                .id(id)
                .categoryId(categoryId)
                .ownerId(currentUser.getId())
                .name(name)
                .imageUrl(imageUrl)
                .description(description)
                .price(price)
                .build();


        try {
            courseService.updateCourse(course);
            request.setAttribute("success", "Update course successfully!");
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
        }

        requestDispatcher.forward(request, response);
    }

}
