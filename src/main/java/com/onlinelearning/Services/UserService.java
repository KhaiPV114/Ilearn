package com.onlinelearning.Services;

import com.onlinelearning.Models.User;


public interface UserService {

    public User updateUser(User user);
    
    public User getUser(Integer id);
    
    public User getUserByUsername(String username);
    
    public boolean changePassword(int id, String newPassword);
}
