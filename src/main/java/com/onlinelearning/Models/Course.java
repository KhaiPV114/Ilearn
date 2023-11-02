package com.onlinelearning.Models;

import com.onlinelearning.Enums.CourseStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Course {

    private Integer id;
    
    private Integer categoryId;
    
    private Integer ownerId;
    
    private String name;
    
    private String imageUrl;
    
    private String description;
    
    private Double price;
    
    private CourseStatus status;
    
}
