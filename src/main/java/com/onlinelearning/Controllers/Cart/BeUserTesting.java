/*
 * DuyDuc94
 */
package com.onlinelearning.Controllers.Cart;

import com.onlinelearning.DAL.Impl.UserDAOImpl;
import com.onlinelearning.DAL.UserDAO;
import com.onlinelearning.Models.User;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * @author duy20
 */
@WebServlet(name = "BeUser", urlPatterns = {"/BeUser"})
public class BeUserTesting extends HttpServlet {

    private final String TEST_PATH = "testing/cart.jsp";

    private final UserDAO userDAO = new UserDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String status = request.getParameter("status");
        int userId = Integer.parseInt(request.getParameter("user-id"));
        String message;
        if (status.compareTo("Login") == 0) {
            User user = userDAO.getUserById(userId);
            if (user != null) {
                request.getSession().setAttribute("user", user);
                message = "Login success!";
            } else {
                message = "User not found!";
            }
        } else {
            request.getSession().removeAttribute("user");
            message = "Logout success!";
        }
        request.setAttribute("message", message);
        request.getRequestDispatcher(TEST_PATH).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
