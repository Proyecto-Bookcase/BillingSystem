package services;

import Dtos.WarehoseDto;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class WarehoseSevices {

    private Connection connection;
    public WarehoseSevices(Connection connection){
        this.connection = connection;
    }


    public void insertWarehose(int enterpriseId, boolean cooled){

        try {
            CallableStatement cstmt = connection.prepareCall(
                    "{ call insert_warehose(?,?)}");
            cstmt.setBoolean(1, cooled);
            cstmt.setInt(2, enterpriseId);

            cstmt.executeQuery();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void deleteWarehose(int number){

        try {
            CallableStatement cstmt = connection.prepareCall(
                    "{ call delete_warehose(?)}");
            cstmt.setInt(1, number);
            cstmt.executeQuery();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public ArrayList<WarehoseDto> getAllWarehose(){
        ArrayList<WarehoseDto> priorityCompanyArrayList = new ArrayList<WarehoseDto>();
        try {
            CallableStatement cstmt = connection.prepareCall(
                    "{ call getallwarehouse()}");

            ResultSet rs = cstmt.executeQuery();
            while (rs.next()) {
                WarehoseDto priorityCompany = new WarehoseDto(
                        rs.getInt("number"),
                        rs.getBoolean("cooled")
                );
                priorityCompanyArrayList.add(priorityCompany);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  priorityCompanyArrayList;
    }

    public WarehoseDto getWarehose(int number){
        WarehoseDto warehose = null;
        try {
            CallableStatement cstmt = connection.prepareCall(
                    "{ call getwarehouse(?)}");
            cstmt.setInt(1, number);

            ResultSet rs = cstmt.executeQuery();
            if (rs.next()) {
                warehose = new WarehoseDto(
                        rs.getInt("number"),
                        rs.getBoolean("cooled")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  warehose;
    }

    public void updateWarehose(int numer, boolean cooled){
        try {
            CallableStatement cstmt = connection.prepareCall(
                    "{ call update_warehose(?,?)}");
            cstmt.setInt(1, numer);
            cstmt.setBoolean(2, cooled);

            cstmt.executeQuery();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
