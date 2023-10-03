package com.onlinelearning.Models;

import com.onlinelearning.Enums.OrderStatus;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Order {

    private Integer id;
    
    private Integer userId;
    
    private LocalDateTime createdAt;
    
    private OrderStatus status;
    
}
