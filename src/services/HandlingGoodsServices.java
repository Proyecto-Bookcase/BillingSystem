package services;

import Dtos.HandlingGoodsDto;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class HandlingGoodsServices {
    private Connection connection;
    public HandlingGoodsServices(Connection connection){
        this.connection = connection;
    }

    public void insertHandlingGoodsCompany(String desciption) throws Exception {

        try {
            CallableStatement cstmt = connection.prepareCall(
                    "{ call createhandlinggoods(?)}");
            cstmt.setString(1, desciption);
            cstmt.executeQuery();

        } catch (SQLException e) {
            throw new Exception(e);
        }
    }
    public void deleteHandlingGoodsCompany(int id) throws Exception {

        try {
            CallableStatement cstmt = connection.prepareCall(
                    "{ call deletehandlinggoods(?)}");
            cstmt.setInt(1, id);
            cstmt.executeQuery();

        } catch (SQLException e) {
            throw new Exception(e);
        }
    }
    public ArrayList<HandlingGoodsDto> getAllHandlingGoodsCompany() throws Exception {
        ArrayList<HandlingGoodsDto> priorityCompanyArrayList = new ArrayList<HandlingGoodsDto>();
        try {
            CallableStatement cstmt = connection.prepareCall(
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
            throw new Exception(e);
        }
        return  priorityCompanyArrayList;
    }
    public ArrayList<HandlingGoodsDto> getAllHandlingGoodsCompany(int companyId) throws Exception {
        ArrayList<HandlingGoodsDto> priorityCompanyArrayList = new ArrayList<HandlingGoodsDto>();
        try {
            CallableStatement cstmt = connection.prepareCall(
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
            throw new Exception(e);
        }
        return  priorityCompanyArrayList;
    }
    public HandlingGoodsDto getHandlingGoodsCompanyById(int id) throws Exception {
        HandlingGoodsDto handlingGoods = null;
        try {
            CallableStatement cstmt = connection.prepareCall(
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
            throw new Exception(e);
        }
        return  handlingGoods;
    }

    public void updateHandlingGoodsCompany(int id, String description) throws Exception {
        try {
            CallableStatement cstmt = connection.prepareCall(
                    "{ call updatehandlinggoods(?,?)}");
            cstmt.setInt(1, id);
            cstmt.setString(2, description);

            cstmt.executeQuery();

        } catch (SQLException e) {
            throw new Exception(e);
        }
    }
}
