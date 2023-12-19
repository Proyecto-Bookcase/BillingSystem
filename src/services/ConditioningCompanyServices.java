package services;

import Dtos.ConditioningCompanyDto;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ConditioningCompanyServices {
    private Connection connection;
    public ConditioningCompanyServices(Connection connection){
        this.connection = connection;
    }

    public void insertConditioningCompany(String desciption) throws Exception {

        try {
            CallableStatement cstmt = connection.prepareCall(
                    "{ call createconditioning(?)}");
            cstmt.setString(1, desciption);
            cstmt.executeQuery();

        } catch (SQLException e) {
            throw new Exception(e);
        }
    }
    public void deleteConditioningCompany(int id) throws Exception {

        try {
            CallableStatement cstmt = connection.prepareCall(
                    "{ call deletepriority(?)}");
            cstmt.setInt(1, id);
            cstmt.executeQuery();

        } catch (SQLException e) {
            throw new Exception(e);
        }
    }
    public ArrayList<ConditioningCompanyDto> getAllConditioningCompany() throws Exception {
        ArrayList<ConditioningCompanyDto> ConditioningCompanyArrayList = new ArrayList<ConditioningCompanyDto>();
        try {
            CallableStatement cstmt = connection.prepareCall(
                    "{ call getallconditioning()}");

            ResultSet rs = cstmt.executeQuery();
            while (rs.next()) {
                ConditioningCompanyDto ConditioningCompany = new ConditioningCompanyDto(
                        rs.getInt("id"),
                        rs.getString("description")
                );
                ConditioningCompanyArrayList.add(ConditioningCompany);
            }
        } catch (SQLException e) {
            throw new Exception(e);
        }
        return  ConditioningCompanyArrayList;
    }
    public ArrayList<ConditioningCompanyDto> getAllConditioningCompanyByCompany(int companyId) throws Exception {
        ArrayList<ConditioningCompanyDto> ConditioningCompanyArrayList = new ArrayList<ConditioningCompanyDto>();
        try {
            CallableStatement cstmt = connection.prepareCall(
                    "{ call getAllConditioningByCompany(?)}");
            cstmt.setInt(1, companyId);

            ResultSet rs = cstmt.executeQuery();
            while (rs.next()) {
                ConditioningCompanyDto ConditioningCompany = new ConditioningCompanyDto(
                        rs.getInt("id"),
                        rs.getString("description")
                );
                ConditioningCompanyArrayList.add(ConditioningCompany);
            }
        } catch (SQLException e) {
            throw new Exception(e);
        }
        return  ConditioningCompanyArrayList;
    }

    public ConditioningCompanyDto getConditioningCompanyById(int id) throws RuntimeException {
        ConditioningCompanyDto conditioningCompany = null;
        try {
            CallableStatement cstmt = connection.prepareCall(
                    "{ call getconditionings (?)}");
            cstmt.setInt(1, id);

            ResultSet rs = cstmt.executeQuery();
            if (rs.next()) {
                conditioningCompany = new ConditioningCompanyDto(
                        rs.getInt("id"),
                        rs.getString("description")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  conditioningCompany;
    }

    public void updateConditioningCompany(int id, String description) throws Exception {
        try {
            CallableStatement cstmt = connection.prepareCall(
                    "{ call updateconditioning(?,?)}");
            cstmt.setInt(1, id);
            cstmt.setString(2, description);

            cstmt.executeQuery();

        } catch (SQLException e) {
            throw new Exception(e);
        }
    }
}
