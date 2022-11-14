package csu.web.MyPetStore.web.servlet;



import csu.web.MyPetStore.domain.Order;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ShoppingAddressServlet extends HttpServlet {
    private static final String CONFIRM_ORDER_FORM = "/WEB-INF/jsp/order/confirmOrder.jsp";

    private Order order;
    private String shipToFirstName;
    private String shipToLastName;
    private String shipAddress1;
    private String shipAddress2;
    private String shipCity;
    private String shipState;
    private String shipZip;
    private String shipCountry;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        shipToFirstName = request.getParameter("shipToFirstName");
        shipToLastName = request.getParameter("shipToLastName");
        shipAddress1 = request.getParameter("shipAddress1");
        shipAddress2 = request.getParameter("shipAddress2");
        shipCity = request.getParameter("shipCity");
        shipState = request.getParameter("shipState");
        shipZip = request.getParameter("shipZip");
        shipCountry = request.getParameter("shipCountry");

        HttpSession session = request.getSession();
        order = (Order)session.getAttribute("order");

        order.setShipToFirstName(shipToFirstName);
        order.setShipToLastName(shipToLastName);
        order.setShipAddress1(shipAddress1);
        order.setShipAddress2(shipAddress2);
        order.setShipCity(shipCity);
        order.setShipState(shipState);
        order.setShipZip(shipZip);
        order.setCourier(shipCountry);

        session.setAttribute("order", order);

        request.getRequestDispatcher(CONFIRM_ORDER_FORM).forward(request, response);
    }
}
