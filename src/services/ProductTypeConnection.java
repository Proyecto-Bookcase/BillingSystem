package services;

import Entity.CompanyType;
import Entity.ProductType;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductTypeConnection {
    public static DbManager manager;
    public ProductTypeConnection(){
        manager = DbManager.getDbManager();
    }

    public void insertProductTypeConnection(String desciption){

        try {
            CallableStatement cstmt = manager.getConnection().prepareCall(
                    "{ call createproducttype(?)}");
            cstmt.setString(1, desciption);
            cstmt.executeQuery();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void deleteProductType(int id){

        try {
            CallableStatement cstmt = manager.getConnection().prepareCall(
                    "{ call deleteproducttype(?)}");
            cstmt.setInt(1, id);
            cstmt.executeQuery();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public ArrayList<ProductType> getAllProductType(){
        ArrayList<ProductType> companyTypes = new ArrayList<ProductType>();
        try {
            CallableStatement cstmt = manager.getConnection().prepareCall(
                    "{ call getAllProductType()}");//implementar esto

            ResultSet rs = cstmt.executeQuery();
            while (rs.next()) {
                ProductType companyType = new ProductType(
                        rs.getInt("id"),
                        rs.getString("description")
                );
                companyTypes.add(companyType);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  companyTypes;
    }
    public ProductType getProductTypeByCargo(int cargoId){
        ProductType productType = null;
        try {
            CallableStatement cstmt = manager.getConnection().prepareCall(
                    "{ call getProductTypeByCargo(?)}");
            cstmt.setInt(1, cargoId);

            ResultSet rs = cstmt.executeQuery();
            if (rs.next()) {
                productType = new ProductType(
                        rs.getInt("id"),
                        rs.getString("description")
                );

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  productType;
    }

    public ProductType getProductTypeById(int id){
        ProductType conditioningCompany = null;
        try {
            CallableStatement cstmt = manager.getConnection().prepareCall(
                    "{ call getProductType (?)}");
            cstmt.setInt(1, id);

            ResultSet rs = cstmt.executeQuery();
            if (rs.next()) {
                conditioningCompany= new ProductType(
                        rs.getInt("id"),
                        rs.getString("description")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  conditioningCompany;
    }

    public void updateProductType(int id, String description){
        try {
            CallableStatement cstmt = manager.getConnection().prepareCall(
                    "{ call updateproducttype(?,?)}");
            cstmt.setInt(1, id);
            cstmt.setString(2, description);

            cstmt.executeQuery();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
