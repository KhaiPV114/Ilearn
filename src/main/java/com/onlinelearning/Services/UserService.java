package com.onlinelearning.Services;

import com.onlinelearning.Models.User;
import java.util.List;


public interface UserService {

    public User updateUser(User user);
    
    public User getUser(Integer id);
    
    public User getUserByUsername(String username);
    
    public boolean changePassword(int id, String newPassword);
    
    public List<User> getUsersByRole(String role);
}
