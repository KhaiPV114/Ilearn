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
import java.util.List;
import org.apache.commons.lang3.StringUtils;

@WebServlet(name = "InstructorCourseAdd", urlPatterns = {"/instructor/course/add"})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 10, // 5 MB
        maxFileSize = 1024 * 1024 * 10, // 10 MB
        maxRequestSize = 1024 * 1024 * 100 // 100 MB
)
public class InstructorCourseAdd extends HttpServlet {

    private static final String FORM_PATH = "/dashboard/instructor/course-form.jsp";

    private final FileUploadService fileUploadService = S3FileUploadServiceImpl.load();

    private final CategoryService categoryService = new CategoryServiceImpl();

    private final CourseService courseService = new CourseServiceImpl();

    private final AuthService authService = new AuthServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Category> categories = categoryService.getAllCategories();
        request.setAttribute("categories", categories);
        request.getRequestDispatcher(FORM_PATH).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(FORM_PATH);

        boolean check = true;

        //Get name
        String name = request.getParameter("name");
        if (StringUtils.isBlank(name)) {
            request.setAttribute("nameError", "Course name must not be empty!");
            check = false;
        }

        //Get category id
        String categoryIdString = request.getParameter("category");
        if (StringUtils.isBlank(categoryIdString)) {
            request.setAttribute("categoryError", "Category must bot be empty!");
            check = false;
        }
        Integer categoryId = Integer.valueOf(categoryIdString);

        //Get price 
        String priceString = request.getParameter("price");
        if (StringUtils.isBlank(priceString)) {
            request.setAttribute("priceError", "Price must not be empty!");
            check = false;
        }
        Double price = Double.valueOf(priceString);

        //Get description
        String description = request.getParameter("description");
        if (StringUtils.isBlank(description)) {
            request.setAttribute("descriptionError", "Description must not be empty!");
            check = false;
        }

        //Get image
        Part imagePart = request.getPart("image");
        String imageUrl = null;
        if (imagePart != null && imagePart.getSize() > 0) {
            try {
                imageUrl = fileUploadService.uploadImage(imagePart);
                request.setAttribute("imageUrl", imageUrl);
            } catch (Exception e) {
                request.setAttribute("imageError", e.getMessage());
                check = false;
            }
        }

        if (!check) {
            List<Category> categories = categoryService.getAllCategories();
            request.setAttribute("categories", categories);
            request.setAttribute("name", name);
            request.setAttribute("category", categoryId);
            request.setAttribute("price", priceString);
            request.setAttribute("description", description);
            requestDispatcher.forward(request, response);
            return;
        }

        User currentUser = authService.getUser(request);

        //Create course
        Course course = Course.builder()
                .categoryId(categoryId)
                .ownerId(currentUser.getId())
                .name(name)
                .imageUrl(imageUrl)
                .description(description)
                .price(price)
                .build();

        //Save category
        try {
            courseService.createCourse(course);
            request.setAttribute("success", "Add course successfully!");
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
        }

        requestDispatcher.forward(request, response);
    }

}
