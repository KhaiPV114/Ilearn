package com.onlinelearning.Models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderItem {

    private Integer orderId;

    private Integer courseId;

    private Integer couponId;

    private Double originalPrice;

    private Double price;

}
