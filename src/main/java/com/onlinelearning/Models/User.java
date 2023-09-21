package com.onlinelearning.Models;


import java.time.LocalDateTime;
import java.util.Date;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {

    private Integer id;

    private String username;

    private String password;

    private String email;

    private String googleEmail;

    private String fullName;

    private Date dob;

    private String phoneNumber;

    private LocalDateTime createdAt;

    private String status;

}
