package csu.web.MyPetStore.web.servlet;



import csu.web.MyPetStore.domain.Account;
import csu.web.MyPetStore.domain.Cart;
import csu.web.MyPetStore.service.OrderService;
import csu.web.MyPetStore.domain.Order;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class OrderFormServelt extends HttpServlet {
    private static final String VIEWORDER = "/WEB-INF/jsp/order/viewOrder.jsp";
    private static final String ERROR = "/WEB-INF/jsp/common/error.jsp";

    private Order order;
    private OrderService orderService;
    private Cart cart;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        order = (Order) session.getAttribute("order");
        cart = (Cart) session.getAttribute("cart");

        if (order != null) {
            orderService = new OrderService();
            orderService.insertOrder(order);
            session.setAttribute("order", order);
            //清空购物车
            cart = null;
            session.setAttribute("cart", cart);

            session.setAttribute("message", "Thank you, your order has been submitted.");

            //HttpSession session = request.getSession();
            Account account = (Account)session.getAttribute("loginAccount");

            request.getRequestDispatcher(VIEWORDER).forward(request, response);
        } else {
            session.setAttribute("message", "An error occurred processing your order (order was null).");
            request.getRequestDispatcher(ERROR).forward(request, response);
        }
    }
}
