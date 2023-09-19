package com.onlinelearning.Utils;

import io.github.cdimascio.dotenv.Dotenv;

public class DotEnv {
    private final Dotenv dotenv;

    public DotEnv() {
        dotenv = Dotenv.configure().directory(".").load();
    }
    
    public String get(String key) {
        return dotenv.get(key);
    }
    
    public String get(String key, String defaultValue) {
        return dotenv.get(key, defaultValue);
    }
}
