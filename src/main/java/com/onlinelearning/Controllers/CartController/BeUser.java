/*
 * DuyDuc94
 */
package com.onlinelearning.Controllers.CartController;

import com.onlinelearning.Models.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * @author duy20
 */
@WebServlet(name = "BeUser", urlPatterns = {"/BeUser"})
public class BeUser extends HttpServlet {
    
    private final String TEST_PATH = "testing/cart.jsp";

    //response.setContentType("text/html;charset=UTF-8");
    //PrintWriter out = response.getWriter();
    //request.setCharacterEncoding("UTF-8");
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        String status = request.getParameter("status");
        String message;
        if (status.compareTo("Login") == 0) {
            User user = User.builder().id(1).username("DuyDuc").fullName("Duc Duy").status("admin").build();
            request.getSession().setAttribute("user", user);
            message = "Login success!";
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
        //processRequest(request, response);

    }
}
