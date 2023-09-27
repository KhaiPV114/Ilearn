package com.onlinelearning.Listeners;

import com.onlinelearning.DAL.Impl.RoleDAOImpl;
import com.onlinelearning.DAL.RoleDAO;
import com.onlinelearning.Models.Role;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class ConfigurationListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        RoleDAO roleDAO = new RoleDAOImpl();
        for (Role role : Role.values()) {
            Integer roleId = roleDAO.getRoleIdByRoleName(role);
            if (roleId == null) {
                roleDAO.addRole(role);
            }
        }
    }

}
