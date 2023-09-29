package com.onlinelearning.Controllers.Learner;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name="LearnerWishlistAdd", urlPatterns={"/LearnerWishlistAdd1"})
public class LearnerWishlistAdd extends HttpServlet {

    //response.setContentType("text/html;charset=UTF-8");
    //PrintWriter out = response.getWriter();
    //request.setCharacterEncoding("UTF-8");

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        //processRequest(request, response);
        
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        //processRequest(request, response);
        
    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LearnerWishlistAdd1</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LearnerWishlistAdd1 at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }   
  
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
