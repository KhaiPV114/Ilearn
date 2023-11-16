package com.onlinelearning.Services;

import com.onlinelearning.Models.Role;
import com.onlinelearning.Models.User;
import jakarta.servlet.http.HttpServletRequest;

public interface AuthService {

    User login(HttpServletRequest request) throws Exception;

    User googleLogin(HttpServletRequest request) throws Exception;

    User register(User user) throws Exception;

    User getUser(HttpServletRequest request);

    User updateUser(User user) throws Exception;

    Integer getUserId(HttpServletRequest request);

    boolean checkRole(HttpServletRequest request, Role role);

    boolean isGuest(HttpServletRequest request);

    boolean isLearner(HttpServletRequest request);

    boolean isInstructor(HttpServletRequest request);

    boolean isManager(HttpServletRequest request);

}
