package services;

import Dtos.PackedTypeDto;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PackedTypeServices {
    private Connection connection;
    public PackedTypeServices(Connection connection){
        this.connection = connection;
    }

    public void insertPackedType(String desciption) throws Exception {

        try {
            CallableStatement cstmt = connection.prepareCall(
                    "{ call createpackedtype(?)}");
            cstmt.setString(1, desciption);
            cstmt.executeQuery();

        } catch (SQLException e) {
            throw new Exception(e);
        }
    }
    public void deletePackedType(int id) throws Exception {

        try {
            CallableStatement cstmt = connection.prepareCall(
                    "{ call deletepackedtype(?)}");
            cstmt.setInt(1, id);
            cstmt.executeQuery();

        } catch (SQLException e) {
            throw new Exception(e);
        }
    }
    public ArrayList<PackedTypeDto> getAllPackagedType(){
        ArrayList<PackedTypeDto> packageTypes = new ArrayList<PackedTypeDto>();
        try {
            CallableStatement cstmt = connection.prepareCall(
                    "{ call getAllpackedtype()}");//implementar esto

            ResultSet rs = cstmt.executeQuery();
            while (rs.next()) {
                PackedTypeDto pt = new PackedTypeDto(
                        rs.getInt("id"),
                        rs.getString("description")
                );
                packageTypes.add(pt);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  packageTypes;
    }
    public PackedTypeDto getPackedTypeByCargo(int cargoId) throws Exception {
        PackedTypeDto companyType = null;
        try {
            CallableStatement cstmt = connection.prepareCall(
                    "{ call getpackedtypeByCargo(?)}");
            cstmt.setInt(1, cargoId);

            ResultSet rs = cstmt.executeQuery();
            if (rs.next()) {
                companyType = new PackedTypeDto(
                        rs.getInt("id"),
                        rs.getString("description")
                );

            }
        } catch (SQLException e) {
            throw new Exception(e);
        }
        return  companyType;
    }

    public PackedTypeDto getPackedTypeById(int id) throws Exception {
        PackedTypeDto conditioningCompany = null;
        try {
            CallableStatement cstmt = connection.prepareCall(
                    "{ call getPackedType(?)}");
            cstmt.setInt(1, id);

            ResultSet rs = cstmt.executeQuery();
            if (rs.next()) {
                conditioningCompany= new PackedTypeDto(
                        rs.getInt("id"),
                        rs.getString("description")
                );
            }
        } catch (SQLException e) {
            throw new Exception(e);
        }
        return  conditioningCompany;
    }

    public void updatePackedType(int id, String description) throws Exception {
        try {
            CallableStatement cstmt = connection.prepareCall(
                    "{ call updatepackedtype(?,?)}");
            cstmt.setInt(1, id);
            cstmt.setString(2, description);

            cstmt.executeQuery();

        } catch (SQLException e) {
            throw new Exception(e);
        }
    }
}
