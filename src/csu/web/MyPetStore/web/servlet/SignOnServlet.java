package csu.web.MyPetStore.web.servlet;

import com.mysql.cj.util.StringUtils;

import csu.web.MyPetStore.domain.Account;
import csu.web.MyPetStore.log.LogServlet;
import csu.web.MyPetStore.service.AccountService;



import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class SignOnServlet extends HttpServlet {
    private static final String SIGN_ON_FORM = "/WEB-INF/jsp/account/signon.jsp";
    private static final String MAIN_FORM = "/WEB-INF/jsp/catalog/main.jsp";
    private String username;
    private String password;
    private String verifyCode;
    private String msg=null;
    private AccountService accountService;
    private Account account;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        verifyCode = req.getParameter("verifyCode");
        String cacheCode = (String) req.getSession().getAttribute("verifyCode");
        this.username = username;
        this.password = password;
        accountService = new AccountService();
        account = accountService.getAccount(username, password);

        HttpSession session = req.getSession();
        session.setAttribute("loginAccount", account);

        LogServlet.setLogin(req.getSession(),req.getParameter("username"),false);
        LogServlet.updateDBFromSession();

        if (!validate()) {
            req.setAttribute("signOnMsg", this.msg);
            req.getRequestDispatcher(SIGN_ON_FORM).forward(req, resp);
        } else
        {
            if (account == null || !verifyCode.equalsIgnoreCase(cacheCode)) {
                   if(!verifyCode.equalsIgnoreCase(cacheCode))
                   {
                       this.msg = "验证码错误!";
                       req.setAttribute("signOnMsg", this.msg);
                   }else {
                       this.msg = "用户名或密码错误!";
                       req.setAttribute("signOnMsg", this.msg);
                   }
                session.setAttribute("loginAccount", null);
                req.getRequestDispatcher(SIGN_ON_FORM).forward(req, resp);
            } else {
                account.setPassword(null);
                req.getRequestDispatcher(MAIN_FORM).forward(req, resp);
            }
        }
        req.getSession().removeAttribute("verifyCode");
    }


    private boolean validate(){
        if(this.username == null || this.username.equals("")){
            this.msg = "用户名不能为空!";
            return false;
        }
        if(this.password == null || this.password.equals("")){
            this.msg = "密码不能为空!";
            return false;
        }
        if(verifyCode =="")
        {
            this.msg = "验证码不能为空!";
            return false;
        }
        return true;
    }
}
