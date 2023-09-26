package com.onlinelearning.Models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Email {

    private String from;
    
    private String to;
    
    private String subject;
    
    private String htmlContent;
    
}
