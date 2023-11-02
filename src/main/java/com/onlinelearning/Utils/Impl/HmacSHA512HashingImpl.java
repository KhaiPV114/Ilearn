package com.onlinelearning.Utils.Impl;

import com.onlinelearning.Utils.DotEnv;
import com.onlinelearning.Utils.Hashing;
import java.nio.charset.StandardCharsets;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class HmacSHA512HashingImpl implements Hashing {

    private final String SECRET_KEY = DotEnv.get("VNPAY_SECRET_KEY");

    @Override
    public String hash(String data) {
        try {
            if (data == null) {
                throw new NullPointerException();
            }
            final Mac hmac512 = Mac.getInstance("HmacSHA512");
            byte[] hmacKeyBytes = SECRET_KEY.getBytes();
            final SecretKeySpec secretKey = new SecretKeySpec(hmacKeyBytes, "HmacSHA512");
            hmac512.init(secretKey);
            byte[] dataBytes = data.getBytes(StandardCharsets.UTF_8);
            byte[] result = hmac512.doFinal(dataBytes);
            StringBuilder sb = new StringBuilder(2 * result.length);
            for (byte b : result) {
                sb.append(String.format("%02x", b & 0xff));
            }
            return sb.toString();

        } catch (Exception ex) {
            return "";
        }
    }

}
