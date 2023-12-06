package services;

import Entity.CompanyType;
import Entity.PackedType;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PackedTypeConnection {
    public static DbManager manager;
    public PackedTypeConnection(){
        manager = DbManager.getDbManager();
    }

    public void insertPackedType(String desciption){

        try {
            CallableStatement cstmt = manager.getConnection().prepareCall(
                    "{ call createpackedtype(?)}");
            cstmt.setString(1, desciption);
            cstmt.executeQuery();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void deletePackedType(int id){

        try {
            CallableStatement cstmt = manager.getConnection().prepareCall(
                    "{ call deletepackedtype(?)}");
            cstmt.setInt(1, id);
            cstmt.executeQuery();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public ArrayList<PackedType> getAllPackagedType(){
        ArrayList<PackedType> packageTypes = new ArrayList<PackedType>();
        try {
            CallableStatement cstmt = manager.getConnection().prepareCall(
                    "{ call getAllpackedtype()}");//implementar esto

            ResultSet rs = cstmt.executeQuery();
            while (rs.next()) {
                PackedType pt = new PackedType(
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
    public PackedType getPackedTypeByCargo(int cargoId){
        PackedType companyType = null;
        try {
            CallableStatement cstmt = manager.getConnection().prepareCall(
                    "{ call getpackedtypeByCargo(?)}");
            cstmt.setInt(1, cargoId);

            ResultSet rs = cstmt.executeQuery();
            if (rs.next()) {
                companyType = new PackedType(
                        rs.getInt("id"),
                        rs.getString("description")
                );

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  companyType;
    }

    public PackedType getPackedTypeById(int id){
        PackedType conditioningCompany = null;
        try {
            CallableStatement cstmt = manager.getConnection().prepareCall(
                    "{ call getPackedType(?)}");
            cstmt.setInt(1, id);

            ResultSet rs = cstmt.executeQuery();
            if (rs.next()) {
                conditioningCompany= new PackedType(
                        rs.getInt("id"),
                        rs.getString("description")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  conditioningCompany;
    }

    public void updatePackedType(int id, String description){
        try {
            CallableStatement cstmt = manager.getConnection().prepareCall(
                    "{ call updatepackedtype(?,?)}");
            cstmt.setInt(1, id);
            cstmt.setString(2, description);

            cstmt.executeQuery();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
