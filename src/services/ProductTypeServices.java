package services;

import Dtos.ProductTypeDto;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductTypeServices {
    private Connection connection;
    public ProductTypeServices(Connection connection){
        this.connection = connection;
    }

    public void insertProductTypeConnection(String desciption) throws Exception {

        try {
            CallableStatement cstmt = connection.prepareCall(
                    "{ call createproducttype(?)}");
            cstmt.setString(1, desciption);
            cstmt.executeQuery();

        } catch (SQLException e) {
            throw new Exception(e);
        }
    }
    public void deleteProductType(int id) throws Exception {

        try {
            CallableStatement cstmt = connection.prepareCall(
                    "{ call deleteproducttype(?)}");
            cstmt.setInt(1, id);
            cstmt.executeQuery();

        } catch (SQLException e) {
            throw new Exception(e);
        }
    }
    public ArrayList<ProductTypeDto> getAllProductType() throws Exception {
        ArrayList<ProductTypeDto> companyTypes = new ArrayList<ProductTypeDto>();
        try {
            CallableStatement cstmt = connection.prepareCall(
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
            throw new Exception(e);
        }
        return  companyTypes;
    }
    public ProductTypeDto getProductTypeByCargo(int cargoId) throws Exception {
        ProductTypeDto productType = null;
        try {
            CallableStatement cstmt = connection.prepareCall(
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
            throw new Exception(e);
        }
        return  productType;
    }

    public ProductTypeDto getProductTypeById(int id) throws Exception {
        ProductTypeDto conditioningCompany = null;
        try {
            CallableStatement cstmt = connection.prepareCall(
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
            throw new Exception(e);
        }
        return  conditioningCompany;
    }

    public void updateProductType(int id, String description) throws Exception {
        try {
            CallableStatement cstmt = connection.prepareCall(
                    "{ call updateproducttype(?,?)}");
            cstmt.setInt(1, id);
            cstmt.setString(2, description);

            cstmt.executeQuery();

        } catch (SQLException e) {
            throw new Exception(e);
        }
    }
}
