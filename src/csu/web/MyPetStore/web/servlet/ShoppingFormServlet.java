package csu.web.MyPetStore.web.servlet;

import csu.web.MyPetStore.domain.Account;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ShoppingFormServlet extends HttpServlet {
    private static final String SHIPPINGFORM = "/WEB-INF/jsp/order/shopping.jsp";
    private static final String SIGNONFORM = "/WEB-INF/jsp/account/signon.jsp";

    private Account account;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        account = (Account)session.getAttribute("loginAccount");

        if (account == null){
            request.getRequestDispatcher(SIGNONFORM).forward(request, response);
        } else{
            //HttpSession session = request.getSession();
            Account account = (Account)session.getAttribute("loginAccount");

            request.getRequestDispatcher(SHIPPINGFORM).forward(request, response);
        }
    }
}
