package csu.web.MyPetStore.web.servlet;



import csu.web.MyPetStore.domain.Account;
import csu.web.MyPetStore.domain.Product;
import csu.web.MyPetStore.service.CatelogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class SearchProductServlet extends HttpServlet {
    private static final String SEARCH_PRODUCTS = "/WEB-INF/jsp/catalog/searchProduct.jsp";

    private String keyword;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        keyword = request.getParameter("keyword");
        System.out.println(keyword);
        //request.setAttribute("keyword", keyword);
        CatelogService service = new CatelogService();
        List<Product> productList = service.searchProductList(keyword);

        //保存数据
        HttpSession session = request.getSession();
        session.setAttribute("keyword", keyword);
        session.setAttribute("productList", productList);

        Account account = (Account)session.getAttribute("loginAccount");

        //跳转页面
        request.getRequestDispatcher(SEARCH_PRODUCTS).forward(request, response);
    }

}
