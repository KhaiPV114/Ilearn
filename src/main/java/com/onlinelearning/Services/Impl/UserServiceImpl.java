package com.onlinelearning.Services.Impl;

import com.onlinelearning.DAL.Impl.UserDAOImpl;
import com.onlinelearning.DAL.UserDAO;
import com.onlinelearning.Models.User;
import com.onlinelearning.Services.UserService;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

public class UserServiceImpl implements UserService{

    private UserDAO userDao = new UserDAOImpl();
    
    private void validateUser(User user) throws Exception{
        if(userDao.getUserByUsername(user.getUsername()) != null){
            throw new Exception("Username is existed!");
        }
    }
    
    @Override
    public User updateUser(User user) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody

    }

    @Override
    public User getUser(Integer id) {
        if(id == null){
            return null;
        }
        return userDao.getUserById(id);
    }

    @Override
    public User getUserByUsername(String username) {
        if(StringUtils.isBlank(username)){
            return null;
        }
        return userDao.getUserByUsername(username);
    }

    @Override
    public boolean changePassword(int id, String newPassword) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<User> getUsersByRole(String role) {
        if(StringUtils.isBlank(role)){
            return null;
        }
        return userDao.getUsersByRole(role);
    }
}
