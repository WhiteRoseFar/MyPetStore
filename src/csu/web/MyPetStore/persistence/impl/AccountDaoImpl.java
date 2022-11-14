package csu.web.MyPetStore.persistence.impl;

import csu.web.MyPetStore.domain.Account;
import csu.web.MyPetStore.persistence.AccountDao;
import csu.web.MyPetStore.persistence.DBUtil;
import csu.web.MyPetStore.web.servlet.RemoveCartServlet;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;



public class AccountDaoImpl implements AccountDao {
    private static String getAccountByUsernameString = "SELECT SIGNON.USERNAME,ACCOUNT.EMAIL,ACCOUNT.FIRSTNAME," +
            "ACCOUNT.LASTNAME,ACCOUNT.STATUS,ACCOUNT.ADDR1 AS address1,ACCOUNT.ADDR2 AS address2,ACCOUNT.CITY," +
            "ACCOUNT.STATE,ACCOUNT.ZIP,ACCOUNT.COUNTRY,ACCOUNT.PHONE,PROFILE.LANGPREF AS languagePreference," +
            "PROFILE.FAVCATEGORY AS favouriteCategoryId,PROFILE.MYLISTOPT AS listOption,PROFILE.BANNEROPT AS bannerOption," +
            "BANNERDATA.BANNERNAME " +
            "FROM ACCOUNT, PROFILE, SIGNON, BANNERDATA " +
            "WHERE ACCOUNT.USERID = ? AND SIGNON.USERNAME = ACCOUNT.USERID AND PROFILE.USERID = ACCOUNT.USERID " +
            "AND PROFILE.FAVCATEGORY = BANNERDATA.FAVCATEGORY";

    private static String getAccountByUsernameAndPasswordString = "SELECT SIGNON.USERNAME,ACCOUNT.EMAIL,ACCOUNT.FIRSTNAME," +
            "ACCOUNT.LASTNAME,ACCOUNT.STATUS,ACCOUNT.ADDR1 AS address1,ACCOUNT.ADDR2 AS address2,ACCOUNT.CITY," +
            "ACCOUNT.STATE,ACCOUNT.ZIP,ACCOUNT.COUNTRY,ACCOUNT.PHONE,PROFILE.LANGPREF AS languagePreference," +
            "PROFILE.FAVCATEGORY AS favouriteCategoryId,PROFILE.MYLISTOPT AS listOption,PROFILE.BANNEROPT AS bannerOption," +
            "BANNERDATA.BANNERNAME " +
            "FROM ACCOUNT, PROFILE, SIGNON, BANNERDATA " +
            "WHERE ACCOUNT.USERID = ? AND SIGNON.PASSWORD = ?" +
            "AND SIGNON.USERNAME = ACCOUNT.USERID AND PROFILE.USERID = ACCOUNT.USERID " +
            "AND PROFILE.FAVCATEGORY = BANNERDATA.FAVCATEGORY";

    private static String updateAccountString = "UPDATE ACCOUNT SET EMAIL = ?, FIRSTNAME = ?, LASTNAME = ?,STATUS = ?, ADDR1 = ?," +
            "ADDR2 = ?, CITY = ?, STATE = ?,ZIP = ?, COUNTRY = ?, PHONE = ? " +
            "WHERE USERID = ?";

    private static String updateProfileString = "UPDATE PROFILE SET LANGPREF = ?, FAVCATEGORY = ?, MYLISTOPT = ?,BANNEROPT = ? WHERE USERID = ?";

    private static String updateSignonString = "UPDATE SIGNON SET PASSWORD = ? WHERE USERNAME = ?";


    private static String insertAccountString = "INSERT INTO ACCOUNT (EMAIL, FIRSTNAME, LASTNAME, STATUS, ADDR1, ADDR2,CITY, STATE, ZIP, COUNTRY, PHONE, USERID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static String insertProfileString = "INSERT INTO PROFILE (LANGPREF, FAVCATEGORY, MYLISTOPT, BANNEROPT, USERID) VALUES (?, ?, ?, ?, ?)";

    private static String insertSignonString = "INSERT INTO SIGNON (PASSWORD,USERNAME) VALUES (?, ?)";

    @Override
    public Account getAccountByUsername(String username) {

        Account accountResult = null;
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(getAccountByUsernameString);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {

                accountResult = this.resultSetToAccount(resultSet);
            }

            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return accountResult;
    }

    @Override
    public Account getAccountByUsernameAndPassword(Account account) {
        Account account1 = null;
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(getAccountByUsernameAndPasswordString);
            preparedStatement.setString(1, account.getUsername());
            preparedStatement.setString(2, account.getPassword());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                account1 = new Account();
                account1.setUsername(resultSet.getString(1));
                account1.setEmail(resultSet.getString(2));
                account1.setFirstName(resultSet.getString(3));
                account1.setLastName(resultSet.getString(4));
                account1.setStatus(resultSet.getString(5));
                account1.setAddress1(resultSet.getString(6));
                account1.setAddress2(resultSet.getString(7));
                account1.setCity(resultSet.getString(8));
                account1.setState(resultSet.getString(9));
                account1.setZip(resultSet.getString(10));
                account1.setCountry(resultSet.getString(11));
                account1.setPhone(resultSet.getString(12));
                account1.setLanguagePreference(resultSet.getString(13));
                account1.setFavouriteCategoryId(resultSet.getString(14));
                account1.setListOption(resultSet.getBoolean(15));
                account1.setBannerOption(resultSet.getBoolean(16));
                account1.setBannerName(resultSet.getString(17));
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return account1;
    }

    private Account resultSetToAccount(ResultSet resultSet) throws Exception {
        Account account = new Account();
        account.setUsername(resultSet.getString("username"));
//        account.setPassword(resultSet.getString("password"));
        account.setEmail(resultSet.getString("email"));
        account.setFirstName(resultSet.getString("firstName"));
        account.setLastName(resultSet.getString("lastName"));
        account.setStatus(resultSet.getString("status"));
        account.setAddress1(resultSet.getString("address1"));
        account.setAddress2(resultSet.getString("address2"));
        account.setCity(resultSet.getString("city"));
        account.setState(resultSet.getString("state"));
        account.setZip(resultSet.getString("zip"));
        account.setCountry(resultSet.getString("country"));
        account.setPhone(resultSet.getString("phone"));
        account.setFavouriteCategoryId(resultSet.getString("favouriteCategoryId"));
        account.setLanguagePreference(resultSet.getString("languagePreference"));
        account.setListOption(resultSet.getInt("listOption") == 1);
        account.setBannerOption(resultSet.getInt("bannerOption") == 1);
        account.setBannerName(resultSet.getString("bannerName"));
        return account;
    }

    public void updateAccount(Account account) {
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(updateAccountString);
            preparedStatement.setString(1, account.getEmail());
            preparedStatement.setString(2, account.getFirstName());
            preparedStatement.setString(3, account.getLastName());
            preparedStatement.setString(4, account.getStatus());
            preparedStatement.setString(5, account.getAddress1());
            preparedStatement.setString(6, account.getAddress2());
            preparedStatement.setString(7, account.getCity());
            preparedStatement.setString(8, account.getState());
            preparedStatement.setString(9, account.getZip());
            preparedStatement.setString(10, account.getCountry());
            preparedStatement.setString(11, account.getPhone());
            preparedStatement.setString(12, account.getUsername());
            preparedStatement.executeUpdate();

            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public boolean insertAccount(Account account) {
        boolean result = false;
        Connection connection = null;
        PreparedStatement preparedStatement;
        try {

            connection = DBUtil.getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(insertAccountString);
            preparedStatement.setString(12,account.getUsername());
            preparedStatement.setString(1,account.getEmail());
            preparedStatement.setString(2,account.getFirstName());
            preparedStatement.setString(3,account.getLastName());
            preparedStatement.setString(4,account.getStatus());
            preparedStatement.setString(5,account.getAddress1());
            preparedStatement.setString(6,account.getAddress2());
            preparedStatement.setString(10,account.getCountry());
            preparedStatement.setString(8,account.getState());
            preparedStatement.setString(9,account.getZip());
            preparedStatement.setString(7,account.getCity());
            preparedStatement.setString(11,account.getPhone());
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement(insertProfileString);
            preparedStatement.setString(1, account.getLanguagePreference());
            preparedStatement.setString(2, account.getFavouriteCategoryId());
            preparedStatement.setInt(3,account.isListOption()?1:0);
            preparedStatement.setInt(4,account.isBannerOption()?1:0);
            preparedStatement.setString(5,account.getUsername());
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement(insertSignonString);
            preparedStatement.setString(1, account.getPassword());
            preparedStatement.setString(2,account.getUsername());
            preparedStatement.executeUpdate();


            connection.commit();
            result = true;
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e) {
            result = false;
            e.printStackTrace();
        }
        return result;
    }




//    @Override
//    public boolean updateAccount(Account account) {
//        boolean result = false;
//        Connection connection = null;
//        PreparedStatement preparedStatement_1;
//        PreparedStatement preparedStatement_2;
//        PreparedStatement preparedStatement_3;
//        try {
//            connection = DBUtil.getConnection();
//            connection.setAutoCommit(false);
//            preparedStatement_1 = connection.prepareStatement(updateAccountString);
//            preparedStatement_1.setString(1,account.getEmail());
//            preparedStatement_1.setString(2,account.getFirstName());
//            preparedStatement_1.setString(3,account.getLastName());
//            preparedStatement_1.setString(4,account.getStatus());
//            preparedStatement_1.setString(5,account.getAddress1());
//            preparedStatement_1.setString(6,account.getAddress2());
//            preparedStatement_1.setString(7,account.getCity());
//            preparedStatement_1.setString(8,account.getState());
//            preparedStatement_1.setString(9,account.getZip());
//            preparedStatement_1.setString(10,account.getCountry());
//            preparedStatement_1.setString(11,account.getPhone());
//            preparedStatement_1.setString(12,account.getUsername());
//            preparedStatement_1.executeUpdate();
//
//            preparedStatement_2 = connection.prepareStatement(updateProfileString);
//            preparedStatement_2.setString(1, account.getLanguagePreference());
//            preparedStatement_2.setString(2, account.getFavouriteCategoryId());
//            preparedStatement_2.setInt(3,account.isListOption()?1:0);
//            preparedStatement_2.setInt(4,account.isBannerOption()?1:0);
//            preparedStatement_2.setString(5,account.getUsername());
//            preparedStatement_2.executeUpdate();
//
//            preparedStatement_3 = connection.prepareStatement(updateSignonString);
//            preparedStatement_3.setString(1, account.getPassword());
//            preparedStatement_3.setString(2,account.getUsername());
//            preparedStatement_3.executeUpdate();
//
//
//            result = true;
//            connection.commit();
//            DBUtil.closePreparedStatement(preparedStatement_1);
//            DBUtil.closePreparedStatement(preparedStatement_2);
//            DBUtil.closePreparedStatement(preparedStatement_3);
//            DBUtil.closeConnection(connection);
//        }catch (Exception e) {
//            result = false;
//            e.printStackTrace();
//        }
//        return result;
//    }

    @Override
    public void insertProfile(Account account) {
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(insertProfileString);
            preparedStatement.setString(1, account.getLanguagePreference());
            preparedStatement.setString(2, account.getFavouriteCategoryId());
            preparedStatement.setString(3, account.getUsername());
            preparedStatement.executeUpdate();

            DBUtil.closeStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void insertSignon(Account account) {
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(insertSignonString);
            preparedStatement.setString(1, account.getPassword());
            preparedStatement.setString(2, account.getUsername());
            preparedStatement.executeUpdate();

            DBUtil.closeStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void updateSignon(Account account) {
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(updateSignonString);
            preparedStatement.setString(1,account.getPassword());
            preparedStatement.setString(2, account.getUsername());
            preparedStatement.executeUpdate();

            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void updateProfile(Account account) {
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(updateProfileString);
            preparedStatement.setString(1, account.getLanguagePreference());
            preparedStatement.setString(2, account.getFavouriteCategoryId());
            preparedStatement.setString(3, account.getUsername());
            preparedStatement.executeUpdate();

            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public boolean findAccountByUsername(String username) {

        boolean result = false;
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(getAccountByUsernameString);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                result=true;
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
