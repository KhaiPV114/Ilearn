package com.onlinelearning.Models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderItem {

    private Integer id;

    private Integer courseId;

    private Integer couponId;

    private Float originalPrice;

    private Float price;

}
