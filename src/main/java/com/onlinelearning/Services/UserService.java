package com.onlinelearning.Services;

import com.onlinelearning.Models.User;

public interface UserService {

    User updateUser(User user);
    
    User getUser(Integer id);
    
    User getUserByUsername(String username);
    
    boolean changePassword(int id, String newPassword);

}
