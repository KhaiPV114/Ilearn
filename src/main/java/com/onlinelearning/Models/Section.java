package com.onlinelearning.Models;

import com.onlinelearning.Enums.SectionStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Section {

    private Integer id;

    private Integer courseId;

    private String name;

    private SectionStatus status;

    private Float orderNumber;

}
