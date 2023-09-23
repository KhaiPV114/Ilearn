package com.onlinelearning.Services.Impl;

import com.onlinelearning.Utils.Constants;
import jakarta.servlet.http.Part;
import java.io.File;
import java.util.UUID;
import com.onlinelearning.Services.FileUploadService;

public class FileUploadServiceImpl implements FileUploadService {

    private static FileUploadServiceImpl fileUploadServiceInstance;

    private static String uploadDirectory = Constants.UPLOAD_DIRECTORY + File.separator + Constants.UPLOAD_IMAGE_DIRECTORY;

    //Singleton pattern: do not allow to instantiating class public
    private FileUploadServiceImpl() {
        if (Constants.PROFILE.equals("dev")) {
            uploadDirectory = Constants.UPLOAD_DIRECTORY_DEV + File.separator + Constants.UPLOAD_IMAGE_DIRECTORY;
        }
        File uploadDirectoryFile = new File(uploadDirectory);
        if (!uploadDirectoryFile.exists()) {
            uploadDirectoryFile.mkdirs();
        }
    }

    public static FileUploadService load() {
        if (fileUploadServiceInstance == null) {
            fileUploadServiceInstance = new FileUploadServiceImpl();
        }
        return fileUploadServiceInstance;
    }

    @Override
    public String getImageUploadDirectoryPath() {
        return uploadDirectory;
    }

    @Override
    public String uploadImage(Part filePart) throws Exception {
        //Get file name
        String fileName = filePart.getSubmittedFileName();

        //Get file extension from file name
        String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1);

        //Check if file extension is in allowed list, if not then throw Exception
        if (!Constants.ALLOWED_IMAGE_EXTENSIONS.contains(fileExtension)) {
            throw new Exception("Image extension is not allowed!");
        }

        //Generate new file name as an UUID
        String savingFileName = UUID.randomUUID().toString() + "." + fileExtension;
        //Create saving path
        String savingFilePath = uploadDirectory + File.separator + savingFileName;

        //Save file
        filePart.write(savingFilePath);

        //File url to access from internet
        String savedFileUrl = "/images/" + savingFileName;

        return savedFileUrl;
    }

}
