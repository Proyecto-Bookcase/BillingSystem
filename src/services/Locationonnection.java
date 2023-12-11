package services;

import Entity.Location;
import Entity.Warehose;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Locationonnection {
    public static DbManager manager;
    public Locationonnection(){
        manager = DbManager.getDbManager();
    }

    //comment to commit 2
    public void insertLocation(int p_compartment, int p_floor, int p_shelf,
                               boolean p_maintenance, int p_warehouse_number,
                               int p_cargo_id) {

        try {
            CallableStatement cstmt = manager.getConnection().prepareCall(
                    "{ call insert_location(?,?,?,?,?,?)}");

            cstmt.setInt(1, p_compartment);
            cstmt.setInt(2, p_floor);
            cstmt.setInt(3, p_shelf);
            cstmt.setBoolean(4, p_maintenance);
            cstmt.setInt(5, p_warehouse_number);
            cstmt.setInt(6, p_cargo_id);

            cstmt.executeQuery();

       } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void deleteLocation(int p_compartment, int p_floor, int p_shelf, int p_warehouse_number) {

        try {
            CallableStatement cstmt = manager.getConnection().prepareCall(
                    "{ call delete_location(?,?,?,?)}");

            cstmt.setInt(1, p_compartment);
            cstmt.setInt(2, p_floor);
            cstmt.setInt(3, p_shelf);
            cstmt.setInt(4, p_warehouse_number);

            cstmt.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public ArrayList<Location> getAllLocationByFloor(int p_floor, int p_shelf, int p_warehouse_number) {
        ArrayList<Location> locationArrayList = new ArrayList<Location>();

        try {
            CallableStatement cstmt = manager.getConnection().prepareCall(
                    "{ call get_all_location_by_floor(?,?,?)}");
            cstmt.setInt(1, p_floor);
            cstmt.setInt(2, p_shelf);
            cstmt.setInt(3, p_warehouse_number);

            ResultSet rs = cstmt.executeQuery();
            while (rs.next())
            {
                Location location = new Location(
                        rs.getInt("compartment"),
                        rs.getInt("floor"),
                        rs.getInt("shelf"),
                        rs.getBoolean("maintenance"),
                        rs.getInt("WAREHOSE_NUMBER"),
                        rs.getInt("cargo__id")

                );
                locationArrayList.add(location);
            }
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            System.out.println(e);
        }
        return locationArrayList;
    }
    public ArrayList<Location> getAllLocationByShelf(int p_shelf, int p_warehouse_number) {
        ArrayList<Location> locationArrayList = new ArrayList<Location>();

        try {
            CallableStatement cstmt = manager.getConnection().prepareCall(
                    "{ call get_all_location_by_SHELF(?,?)}");

            cstmt.setInt(1, p_shelf);
            cstmt.setInt(2, p_warehouse_number);

            ResultSet rs = cstmt.executeQuery();
            while (rs.next())
            {
                Location location = new Location(
                        rs.getInt("compartment"),
                        rs.getInt("floor"),
                        rs.getInt("shelf"),
                        rs.getBoolean("maintenance"),
                        rs.getInt("WAREHOSE_NUMBER"),
                        rs.getInt("cargo__id")

                );
                locationArrayList.add(location);
            }
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            System.out.println(e);
        }
        return locationArrayList;
    }
    public ArrayList<Location> getAllLocationByWarehouse(int p_warehouse_number) {
        ArrayList<Location> locationArrayList = new ArrayList<Location>();

        try {
            CallableStatement cstmt = manager.getConnection().prepareCall(
                    "{ call get_all_location_by_WAREHOSE(?)}");
            cstmt.setInt(1, p_warehouse_number);

            ResultSet rs = cstmt.executeQuery();
            while (rs.next())
            {
                Location location = new Location(
                        rs.getInt("compartment"),
                        rs.getInt("floor"),
                        rs.getInt("shelf"),
                        rs.getBoolean("maintenance"),
                        rs.getInt("WAREHOSE_NUMBER"),
                        rs.getInt("cargo__id")

                );
                locationArrayList.add(location);
            }
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            System.out.println(e);
        }
        return locationArrayList;
    }
    public ArrayList<Location> getAllEmptyLocationByCooled(boolean cooledValue) {
        ArrayList<Location> locationArrayList = new ArrayList<Location>();

        try {
            CallableStatement cstmt = manager.getConnection().prepareCall(
                    "{ call get_all_location_by_WAREHOSE(?)}");
            cstmt.setBoolean(1, cooledValue);

            ResultSet rs = cstmt.executeQuery();
            while (rs.next())
            {
                Location location = new Location(
                        rs.getInt("compartment"),
                        rs.getInt("floor"),
                        rs.getInt("shelf"),
                        rs.getBoolean("maintenance"),
                        rs.getInt("WAREHOSE_NUMBER"),
                        rs.getInt("cargo__id")

                );
                locationArrayList.add(location);
            }
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            System.out.println(e);
        }
        return locationArrayList;
    }
    public ArrayList<Location> getAllEmptyLocation() {
        ArrayList<Location> locationArrayList = new ArrayList<Location>();

        try {
            CallableStatement cstmt = manager.getConnection().prepareCall(
                    "{ call get_all_location_by_WAREHOSE()}");

            ResultSet rs = cstmt.executeQuery();
            while (rs.next())
            {
                Location location = new Location(
                        rs.getInt("compartment"),
                        rs.getInt("floor"),
                        rs.getInt("shelf"),
                        rs.getBoolean("maintenance"),
                        rs.getInt("WAREHOSE_NUMBER"),
                        rs.getInt("cargo__id")

                );
                locationArrayList.add(location);
            }
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            System.out.println(e);
        }
        return locationArrayList;
    }



    public void updateLocationMaintenance(int p_compartment, int p_floor, int p_shelf,int warehouse_number,
                                          boolean p_maintenance, int p_cargo_id) {

        try {
            CallableStatement cstmt = manager.getConnection().prepareCall(
                    "{ call update_location_maintenance(?,?,?,?,?,?)}");

            cstmt.setInt(1, p_compartment);
            cstmt.setInt(2, p_floor);
            cstmt.setInt(3, p_shelf);
            cstmt.setBoolean(4, p_maintenance);
            cstmt.setInt(5, p_cargo_id);
            cstmt.setInt(6, warehouse_number);

            cstmt.execute();

        } catch (SQLException e) {
            //throw new RuntimeException(e);
            System.out.println(e);
        }
    }

}
