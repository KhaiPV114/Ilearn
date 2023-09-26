package com.onlinelearning.Models;

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
    
}
