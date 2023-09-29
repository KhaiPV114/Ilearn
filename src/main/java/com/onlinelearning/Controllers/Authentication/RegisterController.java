package com.onlinelearning.Controllers.Authentication;

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
import java.sql.Date;
import java.util.HashSet;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author nguyenson
 */
@WebServlet(name = "Register", urlPatterns = {"/authentication/register"})
public class RegisterController extends HttpServlet {
    
    private static final String VIEW_PATH = "/common/authentication.jsp";
    
    private final AuthService authService = new AuthServiceImpl();
    
    private final LoginController loginController = new LoginController();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        loginController.doGet(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(VIEW_PATH);
        
        String username = request.getParameter("r_username");
        String password = request.getParameter("r_password");
        String email = request.getParameter("r_email");
        String fullname = request.getParameter("r_fullname");
        String dobString = request.getParameter("r_dob");
        Date dob = null;
        String phoneNumber = request.getParameter("r_phoneNumber");
        String roleString = request.getParameter("r_role");
        
        boolean formCheck = true;
        //Check username
        if (StringUtils.isBlank(username) || !username.matches(Constants.REGEX_USERNAME_CHECK)) {
            request.setAttribute("r_username_error", "Username format is invalid!");
            formCheck = false;
        }

        //Check password
        if (StringUtils.isBlank(password) || !password.matches(Constants.REGEX_PASSWORD_CHECK)) {
            request.setAttribute("r_password_error", "Password format is invalid!");
            formCheck = false;
        }

        //Check email
        if (StringUtils.isBlank(email) || !email.matches(Constants.REGEX_EMAIL_CHECK)) {
            request.setAttribute("r_email_error", "Email format is invalid!");
            formCheck = false;
        }

        //Check fullname
        if (StringUtils.isBlank(fullname) || !fullname.matches(Constants.REGEX_FULLNAME_CHECK)) {
            request.setAttribute("r_fullname_error", "Fullname format is invalid!");
            formCheck = false;
        }

        //Check phone number
        if (StringUtils.isBlank(phoneNumber) || !phoneNumber.matches(Constants.REGEX_PHONE_NUMBER_CHECK)) {
            request.setAttribute("r_phoneNumber_error", "Phone number format is invalid!");
            formCheck = false;
        }

        //Check dob
        if (StringUtils.isBlank(dobString)) {
            request.setAttribute("r_dob_error", "Date of birth can not be empty!");
            formCheck = false;
        } else {
            dob = Date.valueOf(dobString);
        }

        //Check role
        Role role = Role.LEARNER;
        try {
            role = Role.valueOf(roleString);
        } catch (Exception ex) {
            request.setAttribute("r_role_error", "Role is invalid!");
            formCheck = false;
        }

        //If have some conditions failed
        if (!formCheck) {
            request.setAttribute("r_username", username);
            request.setAttribute("r_email", email);
            request.setAttribute("r_fullname", fullname);
            request.setAttribute("r_dob", dobString);
            request.setAttribute("r_phoneNumber", phoneNumber);
            request.setAttribute("r_role", roleString);
            requestDispatcher.forward(request, response);
            return;
        }

        //Create role set
        HashSet<Role> roles = new HashSet<>();
        roles.add(role);

        //Build user
        User user = User.builder()
                .username(username)
                .password(password)
                .email(email)
                .fullName(fullname)
                .dob(dob)
                .phoneNumber(phoneNumber)
                .roles(roles)
                .build();
        
        User registedUser;
        try {
            registedUser = authService.register(user);
        } catch (Exception ex) {
            request.setAttribute("r_username", username);
            request.setAttribute("r_email", email);
            request.setAttribute("r_fullname", fullname);
            request.setAttribute("r_phoneNumber", phoneNumber);
            request.setAttribute("r_dob", dobString);
            request.setAttribute("r_role", roleString);
            request.setAttribute("r_error", ex.getMessage());
            requestDispatcher.forward(request, response);
            return;
        }
        
        request.setAttribute("r_success", "Register successfully! Please login to continue!");
        
        request.getRequestDispatcher(VIEW_PATH).forward(request, response);
    }
}
