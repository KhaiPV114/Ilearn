package com.onlinelearning.Services.Impl;

import com.onlinelearning.Services.FileUploadService;
import com.onlinelearning.Utils.Constants;
import com.onlinelearning.Utils.DotEnv;
import jakarta.servlet.http.Part;
import java.io.File;
import java.nio.file.Files;
import java.util.UUID;
import org.apache.commons.lang3.StringUtils;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3AsyncClient;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.transfer.s3.S3TransferManager;
import static software.amazon.awssdk.transfer.s3.SizeConstant.MB;
import software.amazon.awssdk.transfer.s3.model.FileUpload;
import software.amazon.awssdk.transfer.s3.model.UploadFileRequest;

public class S3FileUploadServiceImpl implements FileUploadService {

    private static S3FileUploadServiceImpl s3FileUploadServiceInstance;

    private static S3TransferManager transferManager;

    private static final String UPLOAD_ROOT_BUCKET = DotEnv.get("AWS_S3_ROOT_BUCKET");

    private static final String UPLOAD_ROOT_BUCKET_URL = DotEnv.get("AWS_S3_ROOT_BUCKER_URL");

    private static final String DEFAULT_IMAGE_UPLOAD_DIRECTORY = DotEnv.get("AWS_S3_DEFAULT_IMAGE_UPLOAD_DIRECTORY");

    private static final String UPLOAD_TEMP_DIRECTORY_ON_DISK = DotEnv.get("AWS_S3_UPLOAD_TEMP_DIRECTORY");

    private S3FileUploadServiceImpl() {
        AwsBasicCredentials credentials = AwsBasicCredentials.create(
                DotEnv.get("AWS_ACCESS_KEY_ID"),
                DotEnv.get("AWS_SECRET_ACCESS_KEY")
        );
        AwsCredentialsProvider credentialsProvider
                = StaticCredentialsProvider.create(credentials);
        S3AsyncClient s3AsyncClient = S3AsyncClient
                .crtBuilder()
                .credentialsProvider(credentialsProvider)
                .region(Region.AP_SOUTHEAST_1)
                .targetThroughputInGbps(1.0)
                .minimumPartSizeInBytes(8 * MB)
                .build();
        transferManager = S3TransferManager.builder()
                .s3Client(s3AsyncClient)
                .build();
        File uploadTempDirectoryOnDisk = new File(UPLOAD_TEMP_DIRECTORY_ON_DISK);
        if (!uploadTempDirectoryOnDisk.exists()) {
            uploadTempDirectoryOnDisk.mkdirs();
        }
    }

    public synchronized static S3FileUploadServiceImpl load() {
        if (s3FileUploadServiceInstance == null) {
            s3FileUploadServiceInstance = new S3FileUploadServiceImpl();
        }
        return s3FileUploadServiceInstance;
    }

    @Override
    public String uploadFile(String uploadDirectory, String uploadFileName, File file) throws Exception {
        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(UPLOAD_ROOT_BUCKET)
                .key(uploadFileName)
                .build();
        UploadFileRequest uploadFileRequest = UploadFileRequest.builder()
                .putObjectRequest(putObjectRequest)
                .source(file)
                .build();
        FileUpload fileUpload = transferManager.uploadFile(uploadFileRequest);
        return fileUpload.completionFuture().join().response().eTag();
    }

    @Override
    public String uploadImage(String uploadDirectory, Part filePart) throws Exception {
        if (StringUtils.isBlank(uploadDirectory)) {
            throw new Exception("Upload directory must not be empty!");
        }

        if (filePart == null || filePart.getSize() <= 0) {
            throw new Exception("File part must not be empty");
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

        //Create saving file path
        String savingFilePath = uploadDirectory + "/" + savingFileName;

        //Create saving path
        String tempFilePath = UPLOAD_TEMP_DIRECTORY_ON_DISK + savingFileName;

        //Save file temporary
        filePart.write(tempFilePath);

        File uploadedFileOnDisk = new File(tempFilePath);

        try {
            String result = uploadFile(
                    UPLOAD_ROOT_BUCKET,
                    savingFilePath,
                    uploadedFileOnDisk
            );
            if (result != null) {
                Files.deleteIfExists(uploadedFileOnDisk.toPath());
            }
        } catch (Exception ex) {
            return ex.getMessage();
        }

        //File url to access from internet
        String savedFileUrl = UPLOAD_ROOT_BUCKET_URL + savingFilePath;

        return savedFileUrl;
    }

    @Override
    public String uploadImage(Part filePart) throws Exception {
        return uploadImage(DEFAULT_IMAGE_UPLOAD_DIRECTORY, filePart);
    }

}
