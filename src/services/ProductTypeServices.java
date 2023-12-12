package services;

import Dtos.ProductTypeDto;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductTypeServices {
    public static ServicesLocator manager;
    public ProductTypeServices(){
        manager = ServicesLocator.getDbManager();
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
    public ArrayList<ProductTypeDto> getAllProductType(){
        ArrayList<ProductTypeDto> companyTypes = new ArrayList<ProductTypeDto>();
        try {
            CallableStatement cstmt = manager.getConnection().prepareCall(
                    "{ call getAllProductType()}");//implementar esto

            ResultSet rs = cstmt.executeQuery();
            while (rs.next()) {
                ProductTypeDto companyType = new ProductTypeDto(
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
    public ProductTypeDto getProductTypeByCargo(int cargoId){
        ProductTypeDto productType = null;
        try {
            CallableStatement cstmt = manager.getConnection().prepareCall(
                    "{ call getProductTypeByCargo(?)}");
            cstmt.setInt(1, cargoId);

            ResultSet rs = cstmt.executeQuery();
            if (rs.next()) {
                productType = new ProductTypeDto(
                        rs.getInt("id"),
                        rs.getString("description")
                );

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  productType;
    }

    public ProductTypeDto getProductTypeById(int id){
        ProductTypeDto conditioningCompany = null;
        try {
            CallableStatement cstmt = manager.getConnection().prepareCall(
                    "{ call getProductType (?)}");
            cstmt.setInt(1, id);

            ResultSet rs = cstmt.executeQuery();
            if (rs.next()) {
                conditioningCompany= new ProductTypeDto(
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
