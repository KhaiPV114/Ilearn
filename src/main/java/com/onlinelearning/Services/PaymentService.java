package com.onlinelearning.Services;

import java.util.Map;

public interface PaymentService {

    String getPaymentURL(Map<String, String> params);
    
    boolean compareChecksum(Map<String, String> params, String Checksum);
}
