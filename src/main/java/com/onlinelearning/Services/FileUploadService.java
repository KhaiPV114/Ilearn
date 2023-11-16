package com.onlinelearning.Services;

import jakarta.servlet.http.Part;
import java.io.File;

public interface FileUploadService {

    default String getImageUploadDirectoryPath() {
        return null;
    }

    String uploadFile(String uploadDirectory, String uploadFileName, File file) throws Exception;

    String uploadImage(String uploadDirectory, Part filePart) throws Exception;

    String uploadImage(Part filePart) throws Exception;

}
