package services;

import Dtos.CompanyTypeDto;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CompanyTypeServices {
    private Connection connection;
    public CompanyTypeServices(Connection connection){
        this.connection = connection;
    }

    public void insertCompanyTypeConnection(String desciption){

        try {
            CallableStatement cstmt = connection.prepareCall(
                    "{ call createcompanytype(?)}");
            cstmt.setString(1, desciption);
            cstmt.executeQuery();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void deleteCompanyType(int id){

        try {
            CallableStatement cstmt = connection.prepareCall(
                    "{ call deletecompanytype(?)}");
            cstmt.setInt(1, id);
            cstmt.executeQuery();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public ArrayList<CompanyTypeDto> getAllCompanyType(){
        ArrayList<CompanyTypeDto> companyTypes = new ArrayList<CompanyTypeDto>();
        try {
            CallableStatement cstmt = connection.prepareCall(
                    "{ call getallcompanytype()}");//implementar esto

            ResultSet rs = cstmt.executeQuery();
            while (rs.next()) {
                CompanyTypeDto companyType = new CompanyTypeDto(
                        rs.getInt("id"),
                        rs.getString("description")
                );
                companyTypes.add(companyType);
            }
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            System.out.println(e.getMessage());
        }
        return  companyTypes;
    }
    public CompanyTypeDto getCompanyTypeByCompany(int companyId){
        CompanyTypeDto companyType = null;
        try {
            CallableStatement cstmt = connection.prepareCall(
                    "{ call getcompanytypebycompany(?)}");
            cstmt.setInt(1, companyId);

            ResultSet rs = cstmt.executeQuery();
            if (rs.next()) {
                companyType = new CompanyTypeDto(
                        rs.getInt("id"),
                        rs.getString("description")
                );

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  companyType;
    }

    public CompanyTypeDto getCompanyTypeCompanyById(int id){
        CompanyTypeDto conditioningCompany = null;
        try {
            CallableStatement cstmt = connection.prepareCall(
                    "{ call getCompanyType (?)}");
            cstmt.setInt(1, id);

            ResultSet rs = cstmt.executeQuery();
            if (rs.next()) {
                 conditioningCompany= new CompanyTypeDto(
                        rs.getInt("id"),
                        rs.getString("description")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  conditioningCompany;
    }

    public void updategetCompanyType(int id, String description){
        try {
            CallableStatement cstmt = connection.prepareCall(
                    "{ call updatecompanytype(?,?)}");
            cstmt.setInt(1, id);
            cstmt.setString(2, description);

            cstmt.executeQuery();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
