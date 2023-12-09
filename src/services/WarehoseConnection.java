package services;

import Entity.PriorityCompany;
import Entity.Warehose;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class WarehoseConnection {

    public static DbManager manager;
    public WarehoseConnection(){
        manager = DbManager.getDbManager();
    }


    public void insertWarehose(int enterpriseId, boolean cooled){

        try {
            CallableStatement cstmt = manager.getConnection().prepareCall(
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
            CallableStatement cstmt = manager.getConnection().prepareCall(
                    "{ call delete_warehose(?)}");
            cstmt.setInt(1, number);
            cstmt.executeQuery();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public ArrayList<Warehose> getAllWarehose(){
        ArrayList<Warehose> priorityCompanyArrayList = new ArrayList<Warehose>();
        try {
            CallableStatement cstmt = manager.getConnection().prepareCall(
                    "{ call getallwarehouse()}");

            ResultSet rs = cstmt.executeQuery();
            while (rs.next()) {
                Warehose priorityCompany = new Warehose(
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

    public Warehose getWarehose(int number){
        Warehose warehose = null;
        try {
            CallableStatement cstmt = manager.getConnection().prepareCall(
                    "{ call getwarehouse(?)}");
            cstmt.setInt(1, number);

            ResultSet rs = cstmt.executeQuery();
            if (rs.next()) {
                warehose = new Warehose(
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
            CallableStatement cstmt = manager.getConnection().prepareCall(
                    "{ call update_warehose(?,?)}");
            cstmt.setInt(1, numer);
            cstmt.setBoolean(2, cooled);

            cstmt.executeQuery();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
