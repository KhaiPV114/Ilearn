package com.onlinelearning.Controllers;

import com.google.gson.JsonObject;
import com.onlinelearning.Services.FileUploadService;
import com.onlinelearning.Services.Impl.S3FileUploadServiceImpl;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.PrintWriter;

@WebServlet(name = "ImageUploadController", urlPatterns = {"/upload/image"})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 5, // 5 MB
        maxFileSize = 1024 * 1024 * 10, // 10 MB
        maxRequestSize = 1024 * 1024 * 100 // 100 MB
)
public class ImageUploadController extends HttpServlet {

    private FileUploadService s3FileUploadService = S3FileUploadServiceImpl.load();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        JsonObject result = new JsonObject();
        try {
            Part imagePart = request.getPart("upload");
            String uploadedUrl = s3FileUploadService.uploadImage(imagePart);
            result.addProperty("url", uploadedUrl);
        } catch (Exception e) {
            JsonObject error = new JsonObject();
            error.addProperty("message", e.getMessage());
            result.add("error", error);
        }
        out.print(result.toString());
        out.flush();
    }

}
