package com.onlinelearning.Models;

import com.onlinelearning.Enums.CouponStatus;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Builder
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Coupon {

    private Integer id;
    
    private Integer courseId;
    
    private String code;
    
    private Double percent;
    
    private Integer quantity;
    
    private Integer remainQuantity;
    
    private LocalDateTime createdAt;
    
    private LocalDateTime startTime;
    
    private LocalDateTime endTime;
    
    private CouponStatus status;
    
}
