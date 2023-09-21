package com.onlinelearning.Models;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserCourse {
    
    private Integer courseId;
    
    private Integer userId;
    
    private Boolean archived;
    
    private LocalDateTime joinedTime;
    
    private Integer ratedStarts;
    
    private Integer ratedContent;
    
}
