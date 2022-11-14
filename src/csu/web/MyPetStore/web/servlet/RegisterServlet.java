package csu.web.MyPetStore.web.servlet;

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

public class RegisterServlet extends HttpServlet {

    private static final String Register_FORM = "/WEB-INF/jsp/account/register.jsp";
    private static final String MAIN_FORM = "/WEB-INF/jsp/catalog/main.jsp";

    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String status;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String zip;
    private String country;
    private String phone;
    private String favouriteCategoryId;
    private String languagePreference;
    private boolean listOption;
    private boolean bannerOption;

    Account registeraccount=new Account();

    private String msg=null;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        registeraccount.setUsername(req.getParameter("username"));
        registeraccount.setPassword(req.getParameter("password"));
        registeraccount.setFirstName(req.getParameter("firstname"));
        registeraccount.setLastName(req.getParameter("lastname"));
        registeraccount.setStatus(req.getParameter("status"));
        registeraccount.setAddress1(req.getParameter("address1"));
        registeraccount.setAddress2(req.getParameter("address2"));
        registeraccount.setEmail(req.getParameter("email"));

        registeraccount.setCity(req.getParameter("city"));

        registeraccount.setZip(req.getParameter("zip"));

        registeraccount.setState(req.getParameter("state"));
        registeraccount.setCountry(req.getParameter("country"));

        registeraccount.setPhone(req.getParameter("phone"));
        registeraccount.setFavouriteCategoryId(req.getParameter("favouritecategoryid"));
        registeraccount.setLanguagePreference(req.getParameter("languagepreference"));

        LogServlet.setLogin(req.getSession(),req.getParameter("username"),true);
        LogServlet.updateDBFromSession();

        if(req.getParameter("listoption").equals("YES")){
            registeraccount.setListOption(true);
        }else {
            registeraccount.setListOption(false);
        }

        if(req.getParameter("banneroption").equals("YES")){
            registeraccount.setBannerOption(true);


        }else {
            registeraccount.setBannerOption(false);
        }




        //校验用户输入的正确性
        if(!validate()){
            req.setAttribute("registerMsg", this.msg);
            req.getRequestDispatcher(Register_FORM).forward(req,resp);
        }else{
            AccountService accountService = new AccountService();

            if(accountService.insertAccount(registeraccount)) {
                System.out.println("chenggong");
                this.msg="注册成功";
                req.getRequestDispatcher(MAIN_FORM).forward(req,resp);

            }else {
                System.out.println("shibai");
                req.getRequestDispatcher(Register_FORM).forward(req,resp);

            }



        }
    }


    private boolean validate(){
//去数据库里面找，看用户名是否重复
        AccountService accountService = new AccountService();
        Account loginAccount = accountService.getAccount(registeraccount.getUsername());


        if(loginAccount!=null){
            this.msg=" this name has been used";
            return false;
        }

        if(registeraccount.getUsername() == null || registeraccount.getUsername().equals("")){
            this.msg = "username can not be empty";
            return false;
        }
        if(registeraccount.getPassword()== null ||registeraccount.getPassword().equals("")){
            this.msg = "password can not be empty";
            return false;
        }

        return true;
    }
}
