package com.onlinelearning.Controllers.Testing;

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
import com.onlinelearning.Services.FileUploadService;

@WebServlet(name = "ImageUploadTestingController", urlPatterns = {"/testing/upload-image"})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 5, // 5 MB
        maxFileSize = 1024 * 1024 * 10, // 10 MB
        maxRequestSize = 1024 * 1024 * 100 // 100 MB
)
public class UploadFileTestingController extends HttpServlet {

    private static final String FORM_PATH = "/testing/upload-image.jsp";

    private final FileUploadService fileUploadService = FileUploadServiceImpl.load();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher(FORM_PATH).forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(FORM_PATH);
        Part imagePart = request.getPart("image");
        try {
            String savedImageUrl = fileUploadService.uploadImage(imagePart);
            request.setAttribute("link", savedImageUrl);
        } catch (Exception exception) {
            request.setAttribute("message", exception.toString());
        }
        requestDispatcher.forward(request, response);
    }

}
