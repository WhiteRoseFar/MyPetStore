package csu.web.MyPetStore.web.servlet;



import csu.web.MyPetStore.domain.Account;
import csu.web.MyPetStore.service.OrderService;
import csu.web.MyPetStore.domain.Order;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ListOrderFormServlet extends HttpServlet {
    private static final String VIEWLISTORDER = "/WEB-INF/jsp/order/listOrder.jsp";

    private String username;
    private OrderService orderService;
    private List<Order> orderList = new ArrayList<Order>();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        username = request.getParameter("username");
        orderService = new OrderService();
        orderList = orderService.getOrdersByUsername(username);

        HttpSession session = request.getSession();
        session.setAttribute("orderList", orderList);

        //HttpSession session = request.getSession();
        Account account = (Account)session.getAttribute("loginAccount");
        request.getRequestDispatcher(VIEWLISTORDER).forward(request, response);
    }
}
