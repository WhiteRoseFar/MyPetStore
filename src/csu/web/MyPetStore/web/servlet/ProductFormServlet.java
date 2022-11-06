package csu.web.MyPetStore.web.servlet;

import csu.web.MyPetStore.domain.Category;
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

public class ProductFormServlet extends HttpServlet {
    private CatelogService catelogService;
    private static final  String PRODUCT_FORM = "/WEB-INF/jsp/catalog/product.jsp";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        catelogService = new CatelogService();
        String productId = req.getParameter("productId");

        Product product = catelogService.getProduct(productId);
        List<Item> itemList = catelogService.getItemListByProduct(productId);

        HttpSession session = req.getSession();
        session.setAttribute("product", product);
        session.setAttribute("itemList", itemList);

        req.getRequestDispatcher(PRODUCT_FORM).forward(req, resp);
    }
}
