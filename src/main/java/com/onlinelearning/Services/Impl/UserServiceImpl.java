package com.onlinelearning.Services.Impl;

import com.onlinelearning.DAL.Impl.UserDAOImpl;
import com.onlinelearning.DAL.UserDAO;
import com.onlinelearning.Services.UserService;


public class UserServiceImpl implements UserService {
    
    private final UserDAO userDAO = new UserDAOImpl();

    @Override
    public String getUserFullNameById(Integer userId) {
        return userDAO.getUserById(userId).getFullName();
    }

}
