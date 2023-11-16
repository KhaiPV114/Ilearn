package com.onlinelearning.Controllers.General;

import com.onlinelearning.Services.Impl.FileUploadServiceImpl;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

@WebServlet(name = "ImageController", urlPatterns = {"/images/*"})
public class ImageController extends HttpServlet {

    private static final String IMAGE_DIRECTORY = FileUploadServiceImpl.load().getImageUploadDirectoryPath();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get the requested image file name from the URL
        String requestedImage = request.getPathInfo();

        // Construct the actual file path
        String imagePath = IMAGE_DIRECTORY + requestedImage;
        File imageFile = new File(imagePath);

        if (!imageFile.exists() || imageFile.isDirectory()) {
            // Handle when the image file does not exist or is a directory
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // Set the content type based on the image type
        String contentType;
        if (requestedImage.endsWith(".png")) {
            contentType = "image/png";
        } else if (requestedImage.endsWith(".jpg") || requestedImage.endsWith(".jpeg")) {
            contentType = "image/jpeg";
        } else {
            contentType = "image/*";  // Fallback to a generic image type
        }
        response.setContentType(contentType);

        // Set content length to prevent browsers from buffering large images
        response.setContentLength((int) imageFile.length());

        // Open input stream and output stream
        try ( FileInputStream in = new FileInputStream(imageFile);  OutputStream out = response.getOutputStream()) {
            // Copy the image content to the response output stream
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
        }
    }

}
