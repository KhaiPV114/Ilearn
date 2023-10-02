package com.onlinelearning.Models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WishlistItem {

    private Integer userId;

    private Integer courseId;

}
