package com.onlinelearning.Services;

import jakarta.servlet.http.Part;

public interface FileUploadService {
    
    String getImageUploadDirectoryPath();

    String uploadImage(Part filePart) throws Exception;

}
