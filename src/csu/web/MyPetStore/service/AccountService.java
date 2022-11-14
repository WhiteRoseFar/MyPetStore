package csu.web.MyPetStore.service;


import csu.web.MyPetStore.domain.Account;
import csu.web.MyPetStore.persistence.AccountDao;
import csu.web.MyPetStore.persistence.impl.AccountDaoImpl;

public class AccountService {
    private AccountDao accountDao;
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public AccountService(){
        this.accountDao=new AccountDaoImpl();
    }

    public Account getAccount(String username, String password) {
        Account account = new Account();
        account.setUsername(username);
        account.setPassword(password);
        return accountDao.getAccountByUsernameAndPassword(account);
    }
    public Account getAccount(String username) {
        return accountDao.getAccountByUsername(username);
    }
    public boolean insertAccount(Account account){
        return accountDao.insertAccount(account);
    }

    public void updateAccount(Account account){

        accountDao.updateAccount(account);
        accountDao.updateProfile(account);
        if (account.getPassword() != null && account.getPassword().length() > 0) {
            accountDao.updateSignon(account);
        }
    }

    public boolean isAccountExist(Account registerAccount){
        String username=registerAccount.getUsername();
        if(accountDao.findAccountByUsername(username)){
            this.msg="用户名已存在";
            return  false;
        }
        return accountDao.insertAccount(registerAccount);
    }
}
