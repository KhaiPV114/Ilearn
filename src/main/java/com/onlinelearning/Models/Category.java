package com.onlinelearning.Models;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Category {

    private Integer id;

    private String name;

    private String imageUrl;

    private String description;
    
    private List<Course> courses;

    public void setCourses(List<Course> courses) {
    this.courses = courses;
  }

}
