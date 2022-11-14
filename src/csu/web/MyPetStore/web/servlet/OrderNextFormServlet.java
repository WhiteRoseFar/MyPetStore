package csu.web.MyPetStore.web.servlet;


import csu.web.MyPetStore.domain.Order;
import csu.web.MyPetStore.domain.Account;
import csu.web.MyPetStore.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class OrderNextFormServlet extends HttpServlet {
    private static final String CONFIRM_ORDER_FORM = "/WEB-INF/jsp/order/confirmOrder.jsp";
    private static final String SHIPPINGFORM = "/WEB-INF/jsp/order/shopping.jsp";

    private String shippingAddressRequired;
    private Order order;
    private OrderService orderService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        shippingAddressRequired = request.getParameter("shippingAddressRequired");
        order = new Order();

        HttpSession session = request.getSession();
        order = (Order)session.getAttribute("order");
        Account account = (Account)session.getAttribute("loginAccount");

        if (shippingAddressRequired == null){
            request.getRequestDispatcher(CONFIRM_ORDER_FORM).forward(request, response);
        }
        else{
            shippingAddressRequired = null;
            request.getRequestDispatcher(SHIPPINGFORM).forward(request, response);
        }

    }
}
