package csu.web.MyPetStore.web.servlet;

import csu.web.MyPetStore.domain.Cart;
import csu.web.MyPetStore.domain.Item;
import csu.web.MyPetStore.service.CatelogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AddItemToCartServlet extends HttpServlet {
    private CatelogService catelogService;
    private static final String CART_FORM = "/WEB-INF/jsp/cart/cart.jsp";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String workingItemId = req.getParameter("workingItemId");

        HttpSession session = req.getSession();
        Cart cart = (Cart) session.getAttribute("cart");

        if(cart == null)
            cart = new Cart();


        // 每次刷新页面重新向add发出请求, 使得
        if (cart.containsItemId(workingItemId)) {
            cart.incrementQuantityByItemId(workingItemId);
        } else {
            catelogService = new CatelogService();
            // isInStock is a "real-time" property that must be updated
            // every time an item is added to the cart, even if other
            // item details are cached.
            boolean isInStock = catelogService.isItemInStock(workingItemId);
            Item item = catelogService.getItem(workingItemId);
            cart.addItem(item, isInStock);
        }
        session.setAttribute("cart", cart);
        // req.getRequestDispatcher(CART_FORM).forward(req,resp);
        resp.sendRedirect("http://localhost:8080/MyPetStore_war_exploded/updateCart");
    }
}
