package com.onlinelearning.Services.Impl;

import com.onlinelearning.Services.PaymentService;
import com.onlinelearning.Utils.DotEnv;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import com.onlinelearning.Utils.Hashing;
import com.onlinelearning.Utils.Impl.HmacSHA512HashingImpl;
import jakarta.servlet.http.HttpServletRequest;

public class VNPaymentServiceImpl implements PaymentService {

    private static final Hashing HmacSHA512 = new HmacSHA512HashingImpl();

    private final String vnp_PayUrl = DotEnv.get("VNPAY_URL");
    private final String vnp_Version = DotEnv.get("VNPAY_VERSION");
    private final String vnp_TmnCode = DotEnv.get("VNPAY_TMN_CODE");
    private final String vnp_OrderInfo = DotEnv.get("VNPAY_MESSAGE_PAYMENT");

    @Override
    public String getPaymentURL(Map<String, String> params) {
        try {
            params.put("vnp_Version", vnp_Version);
            params.put("vnp_TmnCode", vnp_TmnCode);
            params.put("vnp_OrderInfo", vnp_OrderInfo + " " + params.get("vnp_TxnRef"));

            List fieldNames = new ArrayList(params.keySet());
            Collections.sort(fieldNames);
            StringBuilder hashData = new StringBuilder();
            StringBuilder query = new StringBuilder();
            Iterator itr = fieldNames.iterator();
            while (itr.hasNext()) {
                String fieldName = (String) itr.next();
                String fieldValue = (String) params.get(fieldName);
                if ((fieldValue != null) && (fieldValue.length() > 0)) {
                    //Build hash data
                    hashData.append(fieldName);
                    hashData.append('=');
                    hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                    //Build query
                    query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                    query.append('=');
                    query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                    if (itr.hasNext()) {
                        query.append('&');
                        hashData.append('&');
                    }
                }
            }
            String queryUrl = query.toString();
            String vnp_SecureHash = HmacSHA512.hash(hashData.toString());
            queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
            String paymentUrl = vnp_PayUrl + "?" + queryUrl;
            return paymentUrl;
        } catch (Exception e) {
            return "";
        }
    }

    @Override
    public boolean compareChecksum(Map<String, String> params, String Checksum) {
        try {
            List fieldNames = new ArrayList(params.keySet());
            Collections.sort(fieldNames);
            StringBuilder hashData = new StringBuilder();
            StringBuilder query = new StringBuilder();
            Iterator itr = fieldNames.iterator();
            while (itr.hasNext()) {
                String fieldName = (String) itr.next();
                String fieldValue = (String) params.get(fieldName);
                if ((fieldValue != null) && (fieldValue.length() > 0)) {
                    //Build hash data
                    hashData.append(fieldName);
                    hashData.append('=');
                    hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                    //Build query
                    query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                    query.append('=');
                    query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                    if (itr.hasNext()) {
                        query.append('&');
                        hashData.append('&');
                    }
                }
            }
            String vnp_SecureHash = HmacSHA512.hash(hashData.toString());
            return Checksum.compareTo(vnp_SecureHash) == 0;
        } catch (Exception e) {
            return false;
        }
    }

    public static String getIpAddress(HttpServletRequest request) {
        String ipAdress;
        try {
            ipAdress = request.getHeader("X-FORWARDED-FOR");
            if (ipAdress == null) {
                ipAdress = request.getRemoteAddr();
            }
        } catch (Exception e) {
            ipAdress = "Invalid IP:" + e.getMessage();
        }
        return ipAdress;
    }
}
