package csu.web.MyPetStore.web.servlet;

import csu.web.MyPetStore.domain.Account;
import csu.web.MyPetStore.log.LogServlet;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SignOutServlet extends HttpServlet {
    private static final String MAIN = "/WEB-INF/jsp/catalog/main.jsp";

    private Account account;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        account = null;
        //HttpSession session = request.getSession();
        session.setAttribute("loginAccount", account);

        LogServlet.setLogout();
        LogServlet.updateDBFromSession();

        request.getRequestDispatcher(MAIN).forward(request, response);

    }
}