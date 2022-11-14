package csu.web.MyPetStore.persistence;

import csu.web.MyPetStore.domain.Account;

public interface AccountDao {

    Account getAccountByUsername(String username);

    Account getAccountByUsernameAndPassword(Account account);

    boolean insertAccount(Account account);

    void insertProfile(Account account);

    void insertSignon(Account account);

//    boolean updateAccount(Account account);
    void updateAccount(Account account);
    void updateProfile(Account account);

    void updateSignon(Account account);
    boolean findAccountByUsername(String username);
}
