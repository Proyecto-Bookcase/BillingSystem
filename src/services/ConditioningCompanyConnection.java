package services;

import Entity.ConditioningCompany;
import Entity.PriorityCompany;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ConditioningCompanyConnection {
    public static DbManager manager;
    public ConditioningCompanyConnection(){
        manager = DbManager.getDbManager();
    }

    public void insertConditioningCompany(String desciption){

        try {
            CallableStatement cstmt = manager.getConnection().prepareCall(
                    "{ call createconditioning(?)}");
            cstmt.setString(1, desciption);
            cstmt.executeQuery();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void deleteConditioningCompany(int id){

        try {
            CallableStatement cstmt = manager.getConnection().prepareCall(
                    "{ call deletepriority(?)}");
            cstmt.setInt(1, id);
            cstmt.executeQuery();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public ArrayList<ConditioningCompany> getAllConditioningCompany(){
        ArrayList<ConditioningCompany> ConditioningCompanyArrayList = new ArrayList<ConditioningCompany>();
        try {
            CallableStatement cstmt = manager.getConnection().prepareCall(
                    "{ call getallconditioning()}");

            ResultSet rs = cstmt.executeQuery();
            while (rs.next()) {
                ConditioningCompany ConditioningCompany = new ConditioningCompany(
                        rs.getInt("id"),
                        rs.getString("description")
                );
                ConditioningCompanyArrayList.add(ConditioningCompany);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  ConditioningCompanyArrayList;
    }
    public ArrayList<ConditioningCompany> getAllConditioningCompanyByCompany(int companyId){
        ArrayList<ConditioningCompany> ConditioningCompanyArrayList = new ArrayList<ConditioningCompany>();
        try {
            CallableStatement cstmt = manager.getConnection().prepareCall(
                    "{ call getAllConditioningByCompany(?)}");
            cstmt.setInt(1, companyId);

            ResultSet rs = cstmt.executeQuery();
            while (rs.next()) {
                ConditioningCompany ConditioningCompany = new ConditioningCompany(
                        rs.getInt("id"),
                        rs.getString("description")
                );
                ConditioningCompanyArrayList.add(ConditioningCompany);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  ConditioningCompanyArrayList;
    }

    public ConditioningCompany getConditioningCompanyById(int id){
        ConditioningCompany conditioningCompany = null;
        try {
            CallableStatement cstmt = manager.getConnection().prepareCall(
                    "{ call getconditionings (?)}");
            cstmt.setInt(1, id);

            ResultSet rs = cstmt.executeQuery();
            if (rs.next()) {
                conditioningCompany = new ConditioningCompany(
                        rs.getInt("id"),
                        rs.getString("description")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  conditioningCompany;
    }

    public void updateConditioningCompany(int id, String description){
        try {
            CallableStatement cstmt = manager.getConnection().prepareCall(
                    "{ call updateconditioning(?,?)}");
            cstmt.setInt(1, id);
            cstmt.setString(2, description);

            cstmt.executeQuery();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
