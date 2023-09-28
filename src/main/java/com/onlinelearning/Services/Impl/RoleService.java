package com.onlinelearning.Services.Impl;

import com.onlinelearning.Models.Role;
import com.onlinelearning.Models.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.util.HashSet;

public class RoleService {

    public static boolean checkRole(HttpServletRequest request, Role role) {
        HttpSession session = request.getSession();
        if (session == null || session.getAttribute("roles") == null) {
            return false;
        }
        HashSet<Role> roles = (HashSet<Role>) session.getAttribute("roles");
        return roles.contains(role);
    }

    public static boolean isGuest(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return !(session != null && session.getAttribute("user") != null);
    }

    public static boolean isLearner(HttpServletRequest request) {
        return checkRole(request, Role.LEARNER);
    }
    
    public static boolean isInstructor(HttpServletRequest request) {
        return checkRole(request, Role.INSTRUCTOR);
    }

    public static boolean isManager(HttpServletRequest request) {
        return checkRole(request, Role.MANAGER);
    }

    public static String getUserFullname(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            return null;
        }
        User user = (User) session.getAttribute("user");
        return user.getFullName();
    }
}
