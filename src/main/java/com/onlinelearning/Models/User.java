package com.onlinelearning.Models;


import com.onlinelearning.Enums.UserStatus;
import java.time.LocalDateTime;
import java.sql.Date;
import java.util.HashSet;
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

    private UserStatus status;
    
    private HashSet<Role> roles;
    

}
