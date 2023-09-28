package com.onlinelearning.Utils;

public class Constants {

    public static final String PROFILE = DotEnv.get("PROFILE");

    public static final String UPLOAD_DIRECTORY = DotEnv.get("UPLOAD_DIRECTORY");

    public static final String UPLOAD_DIRECTORY_DEV = DotEnv.get("UPLOAD_DIRECTORY_DEV");

    public static final String UPLOAD_IMAGE_DIRECTORY = DotEnv.get("UPLOAD_IMAGE_DIRECTORY");

    public static final String PUBLIC_IMAGE_PATH = DotEnv.get("PUBLIC_IMAGE_PATH");

    public static final String ALLOWED_IMAGE_EXTENSIONS = DotEnv.get("ALLOWED_IMAGE_EXTENSIONS");

    public static final String REGEX_USERNAME_CHECK = ".*";

    public static final String REGEX_PASSWORD_CHECK = ".*";

    public static final String REGEX_EMAIL_CHECK = ".*";

    public static final String REGEX_FULLNAME_CHECK = ".*";

    public static final String REGEX_PHONE_NUMBER_CHECK = ".*";

}
