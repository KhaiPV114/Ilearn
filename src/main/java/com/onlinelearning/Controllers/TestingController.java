package com.onlinelearning.Controllers;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;

@WebServlet(name = "TestingController", urlPatterns = {"/test"})
public class TestingController extends HttpServlet {

    private static final String VIEW_PATH = "test-page.jsp";

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        if (StringUtils.isBlank(name)) {
            name = "World";
        }
        String message = "Hello, " + name + "!";
        request.setAttribute("message", message);
        request.getRequestDispatcher(VIEW_PATH).forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
