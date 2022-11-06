package csu.web.MyPetStore.web.servlet;

import csu.web.MyPetStore.domain.Category;
import csu.web.MyPetStore.domain.Product;
import csu.web.MyPetStore.persistence.DBUtil;
import csu.web.MyPetStore.service.CatelogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

public class CategoryFormServlet extends HttpServlet {

    private CatelogService catelogService;
    private static final  String CATEGORY_FORM = "/WEB-INF/jsp/catalog/category.jsp";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        catelogService = new CatelogService();
        String categoryId = req.getParameter("categoryId");
        Category category = catelogService.getCategory(categoryId);
        if(category == null) req.getRequestDispatcher("/WEB-INF/jsp/catalog/main.jsp").forward(req, resp);
        List<Product> productList = catelogService.getProductListByCategory(categoryId);
        HttpSession session = req.getSession();
        session.setAttribute("category", category);
        session.setAttribute("productList", productList);
        req.getRequestDispatcher(CATEGORY_FORM).forward(req, resp);
    }
}
