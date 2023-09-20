package com.onlinelearning.Models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Lesson {

    private Integer id;
    
    private Integer courseId;
    
    private String name;
    
    private String content;
    
}
