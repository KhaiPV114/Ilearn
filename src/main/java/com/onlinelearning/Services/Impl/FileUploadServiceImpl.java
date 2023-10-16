package com.onlinelearning.Services.Impl;

import com.onlinelearning.Utils.Constants;
import jakarta.servlet.http.Part;
import java.io.File;
import java.util.UUID;
import com.onlinelearning.Services.FileUploadService;
import com.onlinelearning.Utils.DotEnv;
import org.apache.commons.lang3.StringUtils;

public class FileUploadServiceImpl implements FileUploadService {

    private static FileUploadServiceImpl fileUploadServiceInstance;

    private static final String UPLOAD_ROOT_DIRECTORY = DotEnv.get("UPLOAD_DIRECTORY");

    private static final String DEFAULT_IMAGE_UPLOAD_DIRECTORY = DotEnv.get("DEFAULT_IMAGE_UPLOAD_DIRECTORY");

    private static final String DEFAULT_IMAGE_UPLOAD_PATH
            = UPLOAD_ROOT_DIRECTORY
            + File.separator
            + DotEnv.get("DEFAULT_IMAGE_UPLOAD_DIRECTORY");

    //Singleton pattern: do not allow to instantiating class public
    private FileUploadServiceImpl() {
        File defaultImageUploadDirectory = new File(DEFAULT_IMAGE_UPLOAD_DIRECTORY);
        if (!defaultImageUploadDirectory.exists()) {
            defaultImageUploadDirectory.mkdirs();
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
        return DEFAULT_IMAGE_UPLOAD_PATH;
    }

    @Override
    public String uploadFile(String uploadDirectory, String uploadFileName, File file) throws Exception {
        return null;
    }

    @Override
    public String uploadImage(Part filePart) throws Exception {
        return uploadImage(DEFAULT_IMAGE_UPLOAD_DIRECTORY, filePart);
    }

    @Override
    public String uploadImage(String uploadDirectory, Part filePart) throws Exception {
        if (StringUtils.isBlank(uploadDirectory)) {
            throw new Exception("Upload directory must not be empty!");
        }

        if (filePart == null) {
            throw new Exception("File part must not be null");
        }
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
        String savingFilePath = DEFAULT_IMAGE_UPLOAD_PATH + File.separator + savingFileName;

        //Save file
        filePart.write(savingFilePath);

        //File url to access from internet
        String savedFileUrl = DEFAULT_IMAGE_UPLOAD_DIRECTORY.replaceAll("\\\\", "/") + "/" + savingFileName;

        return savedFileUrl;
    }

}
