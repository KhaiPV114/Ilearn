package com.onlinelearning.Listeners;

import com.onlinelearning.DAL.Impl.RoleDAOImpl;
import com.onlinelearning.DAL.RoleDAO;
import com.onlinelearning.Models.Role;
import com.onlinelearning.Utils.DotEnv;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class ConfigurationListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        insertRoleToDatabase();
        addContextInitParam(sce);
    }

    private void insertRoleToDatabase() {
        RoleDAO roleDAO = new RoleDAOImpl();
        for (Role role : Role.values()) {
            Integer roleId = roleDAO.getRoleIdByRoleName(role);
            if (roleId == null) {
                roleDAO.addRole(role);
            }
        }
    }

    private void addContextInitParam(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        servletContext.setInitParameter("GOOGLE_CLIENT_ID", DotEnv.get("GOOGLE_CLIENT_ID"));
    }
}
