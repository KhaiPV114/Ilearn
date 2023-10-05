package com.onlinelearning.Controllers.Learner;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@WebServlet(name = "LearnerCheckoutPayment", urlPatterns = {"/cart/checkout/payment"})
public class LearnerCheckoutPayment extends HttpServlet {

    private final String ERROR_404_PATH = "/error/404.jsp";
    private final String VIETQR_BASE_LINK = "https://api.vietqr.io/image/";
    private final String BANK_ID = "970418";    //BIDV
    private final String ACCOUNT_NO = "2112359999"; //LUONG HUU DUC DUY
    private final String TEMPLATE_LINK = "KpkOM2t.jpg";
    private final String DESCRIPTION = "ilearn+order+";
    private final double VNDExchangeRate = 24385;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher(ERROR_404_PATH).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try ( PrintWriter pw = response.getWriter()) {
            String orderId = request.getParameter("order-id");
            String grandTotal = request.getParameter("grandTotal");
            if (orderId != null && grandTotal != null) {
                Double price = Double.parseDouble(grandTotal);
                price = price * VNDExchangeRate;   //Transfer price to VND

                //Generate VietQR API image link
                String qrLink = VIETQR_BASE_LINK 
                        + BANK_ID 
                        + "-" + ACCOUNT_NO 
                        + "-" + TEMPLATE_LINK 
                        + "?amount=" + price.intValue() 
                        + "&addInfo=" + DESCRIPTION + orderId;
                response.setStatus(HttpServletResponse.SC_OK);
                pw.print(qrLink);
            }else{
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                pw.print("Unknown errors have occurred!");
            }
        }
    }
}
