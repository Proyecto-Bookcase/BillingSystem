package services;

import Dtos.PriorityCompanyDto;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PriorityCompanyServices {
    private Connection connection;
    public PriorityCompanyServices(Connection connection){
        this.connection = connection;
    }

    public void insertPriorityCompany(String desciption){

        try {
            CallableStatement cstmt = connection.prepareCall(
                    "{ call createpriority(?)}");
            cstmt.setString(1, desciption);
            cstmt.executeQuery();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void deletePriorityCompany(int id){

        try {
            CallableStatement cstmt = connection.prepareCall(
                    "{ call deletepriority(?)}");
            cstmt.setInt(1, id);
            cstmt.executeQuery();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public ArrayList<PriorityCompanyDto> getAllPriorityCompany(){
        ArrayList<PriorityCompanyDto> priorityCompanyArrayList = new ArrayList<PriorityCompanyDto>();
        try {
            CallableStatement cstmt = connection.prepareCall(
                    "{ call getallpriority()}");

            ResultSet rs = cstmt.executeQuery();
            while (rs.next()) {
                PriorityCompanyDto priorityCompany = new PriorityCompanyDto(
                        rs.getInt("id"),
                        rs.getString("description")
                );
                priorityCompanyArrayList.add(priorityCompany);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  priorityCompanyArrayList;
    }
    public ArrayList<PriorityCompanyDto> getAllPriorityCompanyByCompany(int companyId){
        ArrayList<PriorityCompanyDto> priorityCompanyArrayList = new ArrayList<PriorityCompanyDto>();
        try {
            CallableStatement cstmt = connection.prepareCall(
                    "{ call getallprioritybycompany(?)}");
            cstmt.setInt(1, companyId);

            ResultSet rs = cstmt.executeQuery();
            while (rs.next()) {
                PriorityCompanyDto priorityCompany = new PriorityCompanyDto(
                        rs.getInt("id"),
                        rs.getString("description")
                );
                priorityCompanyArrayList.add(priorityCompany);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  priorityCompanyArrayList;
    }
    public PriorityCompanyDto getPriorityCompanyById(int id){
        PriorityCompanyDto priorityCompany = null;
        try {
            CallableStatement cstmt = connection.prepareCall(
                    "{ call getpriority(?)}");
            cstmt.setInt(1, id);

            ResultSet rs = cstmt.executeQuery();
            if (rs.next()) {
                 priorityCompany = new PriorityCompanyDto(
                        rs.getInt("id"),
                        rs.getString("description")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  priorityCompany;
    }

    public void updatePriorityCompany(int id, String description){
        try {
            CallableStatement cstmt = connection.prepareCall(
                    "{ call updatepriority(?,?)}");
            cstmt.setInt(1, id);
            cstmt.setString(2, description);

            cstmt.executeQuery();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
