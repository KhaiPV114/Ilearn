package com.onlinelearning.Utils;

import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;

/**
 * @author duy20
 */
public class JsonUtils {

    public static HashMap<String, String> convertJsonToHashMap(String Json) {
        // Using Gson to parse the JSON array to 2d array
        Gson gson = new Gson();
        Object[][] array = gson.fromJson(Json, Object[][].class);

        HashMap<String, String> hashMap = new HashMap<>();

        // Iterating through the array and adding entries to the HashMap
        for (Object[] entry : array) {
            hashMap.put((String) entry[0], (String) entry[1]);
        }
        return hashMap;
    }

    //Convert request data from JSON to String
    public static String getJsonDataFromRequest(HttpServletRequest request) {
        try ( BufferedReader reader = request.getReader()) {
            StringBuilder jsonBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonBuilder.append(line);
            }
            return jsonBuilder.toString();
        } catch (IOException ex) {
            return null;
        }
    }
}
