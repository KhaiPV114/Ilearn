package com.onlinelearning.Controllers.Learner;

import com.onlinelearning.Models.CartItem;
import com.onlinelearning.Models.Course;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "LearnerCheckoutView", urlPatterns = {"/cart/checkout"})
public class LearnerCheckoutView extends HttpServlet {

    private final String VIEW_PATH = "/dashboard/learner/checkout.jsp";
    private final String HOME_PATH = "/homepage";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        request.getRequestDispatcher(VIEW_PATH).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<String> couponCodes = new ArrayList<>();
        List<Course> coursesInCart = (List<Course>) request.getSession().getAttribute("coursesInCart");
        if(coursesInCart.isEmpty()){
            response.sendRedirect(HOME_PATH);
            return;
        }
        for (int i = 0; true; i++) {
            String couponCode = request.getParameter("cId" + i);
            if (couponCode == null) {
                break;
            } else {
                couponCodes.add(couponCode);
            }
        }
        request.getRequestDispatcher(VIEW_PATH).forward(request, response);
    }
}
