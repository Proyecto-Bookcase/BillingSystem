package services;

import Dtos.HandlingGoodsDto;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class HandlingGoodsServices {
    public static ServicesLocator manager;
    public HandlingGoodsServices(){
        manager = ServicesLocator.getDbManager();
    }

    public void insertHandlingGoodsCompany(String desciption){

        try {
            CallableStatement cstmt = manager.getConnection().prepareCall(
                    "{ call createhandlinggoods(?)}");
            cstmt.setString(1, desciption);
            cstmt.executeQuery();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void deleteHandlingGoodsCompany(int id){

        try {
            CallableStatement cstmt = manager.getConnection().prepareCall(
                    "{ call deletehandlinggoods(?)}");
            cstmt.setInt(1, id);
            cstmt.executeQuery();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public ArrayList<HandlingGoodsDto> getAllHandlingGoodsCompany(){
        ArrayList<HandlingGoodsDto> priorityCompanyArrayList = new ArrayList<HandlingGoodsDto>();
        try {
            CallableStatement cstmt = manager.getConnection().prepareCall(
                    "{ call getAllHandlingGoods()}");

            ResultSet rs = cstmt.executeQuery();
            while (rs.next()) {
                HandlingGoodsDto priorityCompany = new HandlingGoodsDto(
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
    public ArrayList<HandlingGoodsDto> getAllHandlingGoodsCompany(int companyId){
        ArrayList<HandlingGoodsDto> priorityCompanyArrayList = new ArrayList<HandlingGoodsDto>();
        try {
            CallableStatement cstmt = manager.getConnection().prepareCall(
                    "{ call getAllHandlingGoodsByCompany(?)}");
            cstmt.setInt(1, companyId);

            ResultSet rs = cstmt.executeQuery();
            while (rs.next()) {
                HandlingGoodsDto priorityCompany = new HandlingGoodsDto(
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
    public HandlingGoodsDto getHandlingGoodsCompanyById(int id){
        HandlingGoodsDto handlingGoods = null;
        try {
            CallableStatement cstmt = manager.getConnection().prepareCall(
                    "{ call gethandlingGoods(?)}");
            cstmt.setInt(1, id);

            ResultSet rs = cstmt.executeQuery();
            if (rs.next()) {
                handlingGoods = new HandlingGoodsDto(
                        rs.getInt("id"),
                        rs.getString("description")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  handlingGoods;
    }

    public void updateHandlingGoodsCompany(int id, String description){
        try {
            CallableStatement cstmt = manager.getConnection().prepareCall(
                    "{ call updatehandlinggoods(?,?)}");
            cstmt.setInt(1, id);
            cstmt.setString(2, description);

            cstmt.executeQuery();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
