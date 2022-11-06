package csu.web.MyPetStore.persistence.impl;

import csu.web.MyPetStore.domain.Category;
import csu.web.MyPetStore.domain.Product;
import csu.web.MyPetStore.persistence.DBUtil;
import csu.web.MyPetStore.persistence.ProductDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao {

    private static final String getProductListByCategoryString =
            "SELECT PRODUCTID,NAME,DESCN AS description,CATEGORY AS categoryId FROM PRODUCT WHERE CATEGORY=?";
    private static final String getProductString =
            "SELECT PRODUCTID,NAME,DESCN AS description,CATEGORY AS categoryId FROM PRODUCT WHERE PRODUCTID=?";

    private static final String searchProductListString =
            "SELECT PRODUCTID,NAME,DESCN AS description,CATEGORY AS categoryId FROM PRODUCT WHERE lower(name) like ?";
    @Override
    public List<Product> getProductListByCategory(String categoryId) {
        List<Product> productList = new ArrayList<Product>();
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedstatement = connection.prepareStatement(getProductListByCategoryString);
            preparedstatement.setString(1, categoryId);
            ResultSet resultSet  = preparedstatement.executeQuery();
            while(resultSet.next()) {
                Product product  = new Product();
                product.setProductId(resultSet.getString(1));
                product.setName(resultSet.getString(3));
                product.setDescription(resultSet.getString(4));
                product.setCategoryId(resultSet.getString(2));
                productList.add(product);
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedstatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return productList;
    }

    @Override
    public Product getProduct(String productId) {
        Product product = new Product();
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedstatement = connection.prepareStatement(getProductString);
            preparedstatement.setString(1, productId);
            ResultSet resultSet  = preparedstatement.executeQuery();
            while(resultSet.next()) {
                product  = new Product();
                product.setProductId(resultSet.getString(1));
                product.setName(resultSet.getString(3));
                product.setDescription(resultSet.getString(4));
                product.setCategoryId(resultSet.getString(2));
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedstatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return product;
    }

    @Override
    public List<Product> searchProductList(String keywords) {
        List<Product> productList = new ArrayList<Product>();
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedstatement = connection.prepareStatement(searchProductListString);
            preparedstatement.setString(1, keywords);
            ResultSet resultSet  = preparedstatement.executeQuery();
            while(resultSet.next()) {
                Product product  = new Product();
                product.setProductId(resultSet.getString(1));
                product.setName(resultSet.getString(3));
                product.setDescription(resultSet.getString(4));
                product.setCategoryId(resultSet.getString(2));
                productList.add(product);
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedstatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return productList;
    }
}
