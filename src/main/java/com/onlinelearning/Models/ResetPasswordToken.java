package com.onlinelearning.Models;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResetPasswordToken {

    private Integer id;

    private Integer userId;

    private String token;

    private LocalDateTime createdAt;

    private LocalDateTime expiredAt;

    private Boolean used;

}
