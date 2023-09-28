package com.onlinelearning.Services.Impl;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.apache.v2.ApacheHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.onlinelearning.DAL.Impl.RoleDAOImpl;
import com.onlinelearning.DAL.Impl.UserDAOImpl;
import com.onlinelearning.DAL.RoleDAO;
import com.onlinelearning.DAL.UserDAO;
import com.onlinelearning.Models.GoogleUser;
import com.onlinelearning.Models.Role;
import com.onlinelearning.Models.User;
import com.onlinelearning.Services.AuthService;
import com.onlinelearning.Utils.Constants;
import com.onlinelearning.Utils.CookieUtils;
import com.onlinelearning.Utils.DotEnv;
import com.password4j.BcryptFunction;
import com.password4j.Password;
import com.password4j.types.Bcrypt;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AuthServiceImpl implements AuthService {

    private final UserDAO userDAO = new UserDAOImpl();

    private final RoleDAO roleDAO = new RoleDAOImpl();

    private final BcryptFunction bcrypt;

    public AuthServiceImpl() {
        bcrypt = BcryptFunction.getInstance(Bcrypt.A, 10);
    }

    @Override
    public User login(HttpServletRequest request) throws Exception {
        //Get username and password from request
        String username = request.getParameter("l_username");
        String password = request.getParameter("l_password");

        //Add back username to show in form if login failed
        request.setAttribute("l_username", username);

        //Get user with the corresponding username from database
        User user = userDAO.getUserByUsername(username);
        if (user == null) { //If user does not exist, return null to exit
            throw new Exception("Username does not exist!");
        }

        //Once the user exists, verify the encrypted password
        boolean verified = Password.check(password, user.getPassword()).with(bcrypt);
        if (!verified) { //If password does not match, return null to exit
            throw new Exception("Username or password is not match!");
        }

        //Once the password match, get user role
        HashSet<Role> roles = getUserRoles(user);
        if (roles == null) { //If get role failed, means login failed, return null to exit
            throw new Exception("User does not have permission to access!");
        }

        //Get session
        HttpSession session = request.getSession(true);
        session.setAttribute("user", user);     //Add user to session
        session.setAttribute("roles", roles);   //Add role to session

        //Everything is done, return logged in user
        return user;
    }

    @Override
    public User register(User user) throws Exception {
        String isValidUser = validateUserInfo(user);
        if (!isValidUser.equals("OK")) {
            throw new IllegalArgumentException(isValidUser);
        }
        if (userDAO.isUsernameExisted(user.getUsername())) {
            throw new IllegalArgumentException("Username is already existed!");
        }
        if (userDAO.isEmailExisted(user.getEmail()) || userDAO.isEmailExisted(user.getGoogleEmail())) {
            throw new IllegalArgumentException("Email is already existed!");
        }
        String hashedPassword = Password.hash(user.getPassword()).with(bcrypt).getResult();
        user.setPassword(hashedPassword);
        User addedUser = userDAO.addUser(user);
        if (addedUser == null) {
            throw new Exception("Register failed. Please try again!");
        }
        for (Role role : user.getRoles()) {
            userDAO.addUserRole(addedUser, role);
        }
        return addedUser;
    }

    private String validateUserInfo(User user) {
        if (user.getUsername() == null || !user.getUsername().matches(Constants.REGEX_USERNAME_CHECK)) {
            return "USERNAME_FORMAT_INVALID";
        }
        if (!user.getPassword().matches(Constants.REGEX_PASSWORD_CHECK)) {
            return "PASSWORD_FORMAT_INVALID";
        }
        if (!user.getEmail().matches(Constants.REGEX_EMAIL_CHECK)) {
            return "EMAIL_FORMAT_INVALID";
        }
        if (!user.getFullName().matches(Constants.REGEX_FULLNAME_CHECK)) {
            return "LASTNAME_IS_EMPTY";
        }
        return "OK";
    }

    @Override
    public User getUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session == null || session.getAttribute("user") == null) {
            return null;
        }
        User user = (User) session.getAttribute("user");
        return user;
    }

    @Override
    public Integer getUserId(HttpServletRequest request) {
        User user = getUser(request);
        if (user == null) {
            return null;
        }
        return user.getId();
    }

    public HashSet<Role> getUserRoles(User user) throws IllegalAccessError {
        //Try to get all roles
        HashSet<Role> roles = userDAO.getRoles(user);
        if (roles == null || roles.isEmpty()) { //If doesn't have role yet, add role LEARNER
            try {
                userDAO.addUserRole(user, Role.LEARNER);
                roles = userDAO.getRoles(user);
            } catch (Exception ex) {
                return null;
            }
        }
        return roles;
    }

    @Override
    public boolean checkRole(HttpServletRequest request, Role role) {
        HttpSession session = request.getSession();
        if (session == null) {
            return false;
        }
        HashSet<Role> roles = (HashSet<Role>) session.getAttribute("roles");
        return roles.contains(role);
    }

    @Override
    public boolean isGuest(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return !(session != null && session.getAttribute("user") != null);
    }

    @Override
    public boolean isUser(HttpServletRequest request) {
        return checkRole(request, Role.LEARNER);
    }

    @Override
    public boolean isManager(HttpServletRequest request) {
        return checkRole(request, Role.MANAGER);
    }

    public GoogleUser getGoogleUserFromGoogleLoginRequest(HttpServletRequest request) {
        //Validate csrf
        String requestCsrfToken = request.getParameter("g_csrf_token");
        if (requestCsrfToken == null) {
            return null;
        }
        if (!CookieUtils.getCookieValue(request, "g_csrf_token").equals(requestCsrfToken)) {
            return null;
        }

        //Get token from HTTP POST request from google
        String idTokenString = request.getParameter("credential");

        //Validate token and get information
        HttpTransport transport = new ApacheHttpTransport();
        JsonFactory jsonFactory = new GsonFactory();
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
                .setAudience(Collections.singletonList(DotEnv.get("GOOGLE_CLIENT_ID")))
                .build();

        GoogleIdToken idToken = null;
        try {
            idToken = verifier.verify(idTokenString);
        } catch (GeneralSecurityException | IOException ex) {
            Logger.getLogger(AuthServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (idToken == null) {
            return null;
        }

        Payload payload = idToken.getPayload();

        // Get profile information from payload
        // String userId = payload.getSubject();
        String email = payload.getEmail();
        boolean emailVerified = payload.getEmailVerified();
        String name = (String) payload.get("name");
        String pictureUrl = (String) payload.get("picture");
        String locale = (String) payload.get("locale");
        String familyName = (String) payload.get("family_name");
        String givenName = (String) payload.get("given_name");

        //Create google user based on getted info
        GoogleUser googleUser = GoogleUser.builder()
                .email(email)
                .emailVerifired(emailVerified)
                .name(name)
                .pictureUrl(pictureUrl)
                .locale(locale)
                .familyName(familyName)
                .givenName(givenName)
                .build();

        return googleUser;
    }

    @Override
    public User googleLogin(HttpServletRequest request) throws Exception {
        //Get google user after logged in with google
        GoogleUser loggedInGoogleUser = getGoogleUserFromGoogleLoginRequest(request);
        if (loggedInGoogleUser == null) { //If logged in fail, return null to exit
            return null;
        }

        //Get user by google email from logged in google user
        User user = userDAO.getUserByGoogleEmail(loggedInGoogleUser.getEmail());

        if (user == null) { //If user not found, register a new account
            //Check if is mail is existed
            if (userDAO.isEmailExisted(loggedInGoogleUser.getEmail())) {
                throw new IllegalArgumentException("User with this email is already existed!");
            }

            //If everything is ok, crete a new user based on google user info
            User newUser = User.builder()
                    .email(loggedInGoogleUser.getEmail())
                    .googleEmail(loggedInGoogleUser.getEmail())
                    .fullName(loggedInGoogleUser.getName())
                    .build();

            //Add new user to database
            User registedUser = userDAO.addUser(newUser);
            if (registedUser == null) { //If add fail, throw exception
                throw new IllegalArgumentException("Register with Google failed. Please try again!");
            }

            //Add role for new user
            User addedUserWithRole = userDAO.addUserRole(registedUser, Role.LEARNER);
            if (addedUserWithRole == null) { //If add fail, throw exception
                throw new IllegalArgumentException("Register with Google failed. Please try again!");
            }

            user = registedUser; //set user to registed user to continue process
        }

        //If user existed, get user role
        HashSet<Role> roles = getUserRoles(user);
        if (roles == null) { //If get role failed, means login failed, return null to exit
            throw new Exception("User does not have permission to access!");
        }

        //Get session
        HttpSession session = request.getSession(true);
        session.setAttribute("user", user);     //Add user to session
        session.setAttribute("roles", roles);   //Add role to session

        return user;
    }

}
