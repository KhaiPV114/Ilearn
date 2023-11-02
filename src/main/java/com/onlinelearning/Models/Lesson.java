package com.onlinelearning.Models;

import com.onlinelearning.Enums.LessonStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Lesson {

    private Integer id;

    private Integer sectionId;

    private String name;

    private String content;

    private LessonStatus status;

    private Float orderNumber;

}
