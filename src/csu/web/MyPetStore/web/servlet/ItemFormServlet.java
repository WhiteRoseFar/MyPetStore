package csu.web.MyPetStore.web.servlet;

import csu.web.MyPetStore.domain.Item;
import csu.web.MyPetStore.domain.Product;
import csu.web.MyPetStore.service.CatelogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class ItemFormServlet extends HttpServlet {
    private CatelogService catelogService;
    private static final  String ITEM_FORM = "/WEB-INF/jsp/catalog/item.jsp";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        catelogService = new CatelogService();
        String itemId = req.getParameter("itemId");

        Item item = catelogService.getItem(itemId);
        Product product = item.getProduct();

        HttpSession session = req.getSession();
        session.setAttribute("item", item);
        session.setAttribute("product", product);

        req.getRequestDispatcher(ITEM_FORM).forward(req, resp);
    }
}
