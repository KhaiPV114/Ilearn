package com.onlinelearning.Controllers.Manager;

import com.onlinelearning.Models.Course;
import com.onlinelearning.Services.CourseService;
import com.onlinelearning.Services.FileUploadService;
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
import org.apache.commons.lang3.StringUtils;

@WebServlet(name = "ManagerCourseAdd", urlPatterns = {"/manager/course/add"})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 10, // 5 MB
        maxFileSize = 1024 * 1024 * 10, // 10 MB
        maxRequestSize = 1024 * 1024 * 100 // 100 MB
)
public class ManagerCourseAdd extends HttpServlet {

    private static final String FORM_PATH = "/dashboard/manager/course-form.jsp";

    private final FileUploadService fileUploadService = FileUploadServiceImpl.load();

    private final CourseService courseService = new CourseServiceImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher(FORM_PATH).forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(FORM_PATH);

        //Get name
        String name = request.getParameter("name");
        if (StringUtils.isBlank(name)) {
            request.setAttribute("nameError", "Course name must not be empty!");
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
        Course course = Course.builder()
                .owner_id(1)
                .name(name)
                .imageUrl(imageUrl)
                .description(description)
                .build();

        /*
        .id(rs.getInt("course_id"))
                            .categoryId(rs.getInt("categoryID"))
                            .owner_id(rs.getInt("owner_id"))
                            .name(rs.getString("name"))
                            .imageUrl(rs.getString("image_url"))
                            .description(rs.getString("description"))
        
        */
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
