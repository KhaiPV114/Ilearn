package com.onlinelearning.Models;

import com.onlinelearning.Enums.TrackingStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Tracking {

    private Integer lessonId;

    private Integer userId;

    private TrackingStatus status;

}
