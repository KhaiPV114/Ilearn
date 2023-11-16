package com.onlinelearning.Services;

import com.onlinelearning.Models.Role;
import com.onlinelearning.Models.User;
import java.util.List;

public interface UserService {

    public User updateUser(User user);

    public User getUser(Integer id);

    public User getUserByUsername(String username);

    public boolean changePassword(int id, String newPassword);

    public List<User> getUsersByRole(String role);

    public List<User> getUserByKeyword(String keyword);

    public List<User> getAllActiveUsers();

    public List<User> getAllBannedUsers();

    public User updateUserStatus(String status, User user);

    public List<User> getLearnerOfAllCourse(Integer ownerId);
    
    public List<User> getLearnerOfAllCourseWithStatus(Integer ownerId, String status);
    
    public List<User> getLearnerOfAllCourseByKeyword(String keyword);
    
    Integer getNumberOfUserAtRole(Role role);
}
