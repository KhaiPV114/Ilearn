/*
 * DuyDuc94
 */

package com.onlinelearning.Controllers.Learner;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * @author duy20
 */

@WebServlet(name="LearnerCheckoutPayment", urlPatterns={"/cart/checkout/payment"})
public class LearnerCheckoutPayment extends HttpServlet {
    
    private final String VIEW_PATH = "/dashboard/learner/payment.jsp";
    private final String HOME_PATH = "/homepage";
    private final String BANK_ID = "970418";    //BIDV
    private final String ACCOUNT_NO = "2112359999"; //LUONG HUU DUC DUY
    private final String DESCRIPTION = "ilearn order id ";
    private final double oneDollarVND = 24385;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String orderId = request.getParameter("order-id");
        if(orderId==null){
            response.sendRedirect(request.getContextPath() + HOME_PATH);
            return;
        }
        
        double price = Double.parseDouble(request.getParameter("price"));
        if(price == 0){
            //...
        }else{
            price = price * oneDollarVND;
        }
        
        request.setAttribute("BANK_ID", BANK_ID);
        request.setAttribute("ACCOUNT_NO", ACCOUNT_NO);
        request.setAttribute("AMOUNT", price);
        request.getRequestDispatcher(VIEW_PATH).forward(request, response);
        
    }
}
