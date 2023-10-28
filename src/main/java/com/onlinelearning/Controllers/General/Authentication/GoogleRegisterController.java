package com.onlinelearning.Controllers.General.Authentication;

import com.onlinelearning.DAL.Impl.UserDAOImpl;
import com.onlinelearning.DAL.UserDAO;
import com.onlinelearning.Models.Role;
import com.onlinelearning.Models.User;
import com.onlinelearning.Services.AuthService;
import com.onlinelearning.Services.Impl.AuthServiceImpl;
import com.onlinelearning.Utils.Constants;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Date;
import java.util.HashSet;
import org.apache.commons.lang3.StringUtils;

@WebServlet(name = "GoogleRegisterController", urlPatterns = {"/authentication/register/google"})
public class GoogleRegisterController extends HttpServlet {
    
    private static final String FORM_PATH = "/general/google-register.jsp";
    
    private final AuthService authService = new AuthServiceImpl();
    
    private final UserDAO userDAO = new UserDAOImpl();
    
    public User getUserFromSession(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/");
            return null;
        }
        User user = (User) session.getAttribute("user");
        return user;
    }
    
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = getUserFromSession(request, response);
        if (user == null) {
            return;
        }
        String email = user.getEmail();
        String fullname = user.getFullName();
        request.setAttribute("g_email", email);
        request.setAttribute("g_fullname", fullname);
        request.getRequestDispatcher(FORM_PATH).forward(request, response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = getUserFromSession(request, response);
        if (user == null) {
            return;
        }
        
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(FORM_PATH);
        
        String fullname = request.getParameter("g_fullname");
        String dobString = request.getParameter("g_dob");
        Date dob = null;
        String phoneNumber = request.getParameter("g_phoneNumber");
        String roleString = request.getParameter("g_role");
        
        boolean formCheck = true;

        //Check fullname
        if (StringUtils.isBlank(fullname) || !fullname.matches(Constants.REGEX_FULLNAME_CHECK)) {
            request.setAttribute("g_fullname_error", "Fullname format is invalid!");
            formCheck = false;
        }

        //Check phone number
        if (StringUtils.isBlank(phoneNumber) || !phoneNumber.matches(Constants.REGEX_PHONE_NUMBER_CHECK)) {
            request.setAttribute("g_phoneNumber_error", "Phone number format is invalid!");
            formCheck = false;
        }

        //Check dob
        if (StringUtils.isBlank(dobString)) {
            request.setAttribute("g_dob_error", "Date of birth can not be empty!");
            formCheck = false;
        } else {
            dob = Date.valueOf(dobString);
        }

        //Check role
        Role role = Role.LEARNER;
        try {
            role = Role.valueOf(roleString);
        } catch (Exception ex) {
            request.setAttribute("g_role_error", "Role is invalid!");
            formCheck = false;
        }

        //If have some conditions failed
        if (!formCheck) {
            request.setAttribute("g_fullname", fullname);
            request.setAttribute("g_dob", dobString);
            request.setAttribute("g_phoneNumber", phoneNumber);
            request.setAttribute("g_role", roleString);
            requestDispatcher.forward(request, response);
            return;
        }

        //Add role
        userDAO.addUserRole(user, role);

        //Update user
        user.setFullName(fullname);
        user.setDob(dob);
        user.setPhoneNumber(phoneNumber);
        
        User updatedUser;
        try {
            updatedUser = authService.updateUser(user);
        } catch (Exception ex) {
            request.setAttribute("g_fullname", fullname);
            request.setAttribute("g_dob", dobString);
            request.setAttribute("g_phoneNumber", phoneNumber);
            request.setAttribute("g_role", roleString);
            request.setAttribute("g_error", ex.getMessage());
            requestDispatcher.forward(request, response);
            return;
        }
        
        HashSet<Role> roles = userDAO.getRoles(user);
        HttpSession session = request.getSession(true);
        session.setAttribute("user", updatedUser);
        session.setAttribute("roles", roles);
        
        request.setAttribute("g_success", "Register successfully!");
        requestDispatcher.forward(request, response);
    }
    
}
