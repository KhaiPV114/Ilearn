package com.onlinelearning.Models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Wishlist {

    private Integer id;

    private Integer userId;

    private Integer courseId;

}
