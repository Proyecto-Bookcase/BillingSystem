package services;

import Entity.CompanyType;
import Entity.ConditioningCompany;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CompanyTypeConnection {
    public static DbManager manager;
    public CompanyTypeConnection(){
        manager = DbManager.getDbManager();
    }

    public void insertCompanyTypeConnection(String desciption){

        try {
            CallableStatement cstmt = manager.getConnection().prepareCall(
                    "{ call createcompanytype(?)}");
            cstmt.setString(1, desciption);
            cstmt.executeQuery();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void deleteCompanyType(int id){

        try {
            CallableStatement cstmt = manager.getConnection().prepareCall(
                    "{ call deletecompanytype(?)}");
            cstmt.setInt(1, id);
            cstmt.executeQuery();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public ArrayList<CompanyType> getAllCompanyType(){
        ArrayList<CompanyType> companyTypes = new ArrayList<CompanyType>();
        try {
            CallableStatement cstmt = manager.getConnection().prepareCall(
                    "{ call getallcompanytype()}");//implementar esto

            ResultSet rs = cstmt.executeQuery();
            while (rs.next()) {
                CompanyType companyType = new CompanyType(
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
    public CompanyType getCompanyTypeByCompany(int companyId){
        CompanyType companyType = null;
        try {
            CallableStatement cstmt = manager.getConnection().prepareCall(
                    "{ call getcompanytypebycompany(?)}");
            cstmt.setInt(1, companyId);

            ResultSet rs = cstmt.executeQuery();
            if (rs.next()) {
                companyType = new CompanyType(
                        rs.getInt("id"),
                        rs.getString("description")
                );

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  companyType;
    }

    public CompanyType getCompanyTypeCompanyById(int id){
        CompanyType conditioningCompany = null;
        try {
            CallableStatement cstmt = manager.getConnection().prepareCall(
                    "{ call getCompanyType (?)}");
            cstmt.setInt(1, id);

            ResultSet rs = cstmt.executeQuery();
            if (rs.next()) {
                 conditioningCompany= new CompanyType(
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
            CallableStatement cstmt = manager.getConnection().prepareCall(
                    "{ call updatecompanytype(?,?)}");
            cstmt.setInt(1, id);
            cstmt.setString(2, description);

            cstmt.executeQuery();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
