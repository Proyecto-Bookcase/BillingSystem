package services;

import Dtos.CargoDto;
import Dtos.PackedTypeDto;
import Dtos.ProductTypeDto;

import java.sql.*;
import java.util.ArrayList;

public class    CargoServices {
    private Connection connection;

    public CargoServices(Connection connection) {
        this.connection = connection;
    }

    public void insertCargo(

            String p_name, boolean p_refrigeration, Timestamp p_expiration_date, float p_packed_unit_weight,
            int p_pack_parts, float p_weight, int p_producttype_id, int p_packedtype_id, int p_client_id,
            Timestamp p_arrival_date, Timestamp p_departure_date, Timestamp p_actual_departure_date,
            float p_needed_fuel,
            int p_compartment, int p_floor, int p_shelf, int p_warehouse_number
    ) {

        try {
            // Llama al procedimiento almacenado "insert_cargo" con los parámetros
            CallableStatement cstmt = connection.prepareCall(
                    "{ call insert_cargo(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) }");

            // Configura los parámetros
            cstmt.setString(1, p_name);
            cstmt.setBoolean(2, p_refrigeration);
            cstmt.setTimestamp(3, p_expiration_date);
            cstmt.setFloat(4, p_packed_unit_weight);
            cstmt.setInt(5, p_pack_parts);
            cstmt.setFloat(6, p_weight);
            cstmt.setInt(7, p_producttype_id);
            cstmt.setInt(8, p_packedtype_id);
            cstmt.setInt(9, p_client_id);
            cstmt.setTimestamp(10, p_arrival_date);
            cstmt.setTimestamp(11, p_departure_date);
            cstmt.setTimestamp(12, p_actual_departure_date);
            cstmt.setFloat(13, p_needed_fuel);


            // Parámetros de localización
            cstmt.setInt(14, p_compartment);
            cstmt.setInt(15, p_floor);
            cstmt.setInt(16, p_shelf);
            cstmt.setInt(17, p_warehouse_number);


            // Ejecutar la llamada al procedimiento almacenado
            cstmt.execute();

            // Cerrar el CallableStatement
            cstmt.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteCargo(int cargoId) {
        try {
            CallableStatement cstmt = connection.prepareCall("{ call delete_cargo(?)}");
            // Configura los parámetros
            cstmt.setInt(1, cargoId);
            cstmt.executeQuery();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public CargoDto getCargoDbFunction(int cargoId) {
        CargoDto cargoDTO = null;
        try (CallableStatement cstmt = connection.prepareCall("{call getCargo(?) }")) {
            cstmt.setInt(1, cargoId);

            cstmt.executeQuery();

            // Obtener el conjunto de resultados
            ResultSet rs = cstmt.executeQuery();
            if (rs.next()) {
                cargoDTO.setId(rs.getInt("ID"));
                cargoDTO.setName(rs.getString("NAME"));
                cargoDTO.setRefrigeration(rs.getBoolean("REFRIGERATION"));
                cargoDTO.setExpirationDate(rs.getTimestamp("EXPIRATION_DATE"));
                cargoDTO.setPackedUnitWeight(rs.getFloat("PACKED_UNIT_WEIGHT"));
                cargoDTO.setPackParts(rs.getInt("PACK_PARTS"));
                cargoDTO.setWeight(rs.getFloat("WEIGHT"));
                cargoDTO.setProductType(new ProductTypeDto(rs.getInt("PRODUCTTYPE__ID"), rs.getString("PRODUCTTYPE_DESCRIPTION")));
                cargoDTO.setPackedType(new PackedTypeDto(rs.getInt("PACKEDTYPE__ID"), rs.getString("PACKEDTYPE_DESCRIPTION")));
                cargoDTO.setClientId(rs.getInt("CLIENT__ID"));
                cargoDTO.setArrivalDate(rs.getTimestamp("ARRIVAL_DATE"));
                cargoDTO.setDepartureDate(rs.getTimestamp("DEPARTURE_DATE"));
                cargoDTO.setActualDepartureDate(rs.getTimestamp("ACTUAL_DEPARTURE_DATE"));
                //todo change
                cargoDTO.setNeededFuel(rs.getFloat("needed_fuel"));


                cargoDTO.setCompartment(rs.getInt("COMPARTMENT"));
                cargoDTO.setFloor(rs.getInt("FLOOR"));
                cargoDTO.setShelf(rs.getInt("SHELF"));
                cargoDTO.setWarehouseNumber(rs.getInt("WAREHOUSE_NUMBER"));

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return cargoDTO;

    }

    public ArrayList<CargoDto> getAllCargoDbFunction() {
        ArrayList<CargoDto> cargoDTOs = new ArrayList<CargoDto>();
        try (CallableStatement cstmt = connection.prepareCall("{call getCargo() }")) {

            cstmt.executeQuery();

            ResultSet rs = cstmt.executeQuery();
            while (rs.next()) {
                CargoDto cargoDTO = new CargoDto();
                cargoDTO.setId(rs.getInt("ID"));
                cargoDTO.setName(rs.getString("NAME"));
                cargoDTO.setRefrigeration(rs.getBoolean("REFRIGERATION"));
                cargoDTO.setExpirationDate(rs.getTimestamp("EXPIRATION_DATE"));
                cargoDTO.setPackedUnitWeight(rs.getFloat("PACKED_UNIT_WEIGHT"));
                cargoDTO.setPackParts(rs.getInt("PACK_PARTS"));
                cargoDTO.setWeight(rs.getFloat("WEIGHT"));
                cargoDTO.setProductType(new ProductTypeDto(rs.getInt("PRODUCTTYPE__ID"), rs.getString("PRODUCTTYPE_DESCRIPTION")));
                cargoDTO.setPackedType(new PackedTypeDto(rs.getInt("PACKEDTYPE__ID"), rs.getString("PACKEDTYPE_DESCRIPTION")));
                cargoDTO.setClientId(rs.getInt("CLIENT__ID"));
                cargoDTO.setArrivalDate(rs.getTimestamp("ARRIVAL_DATE"));
                cargoDTO.setDepartureDate(rs.getTimestamp("DEPARTURE_DATE"));
                cargoDTO.setActualDepartureDate(rs.getTimestamp("ACTUAL_DEPARTURE_DATE"));
                cargoDTO.setNeededFuel(rs.getFloat("needed_fuel"));

                cargoDTO.setCompartment(rs.getInt("COMPARTMENT"));
                cargoDTO.setFloor(rs.getInt("FLOOR"));
                cargoDTO.setShelf(rs.getInt("SHELF"));
                cargoDTO.setWarehouseNumber(rs.getInt("WAREHOUSE_NUMBER"));
                //add cargo to list
                cargoDTOs.add(cargoDTO);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return cargoDTOs;

    }

    public void colletCargoBySunad(CargoDto cargoDto) {
        CargoDto cargoDTO = null;
        try (CallableStatement cstmt = connection.prepareCall("{call getCargo(?) }")) {
            cstmt.executeQuery();

            // Obtener el conjunto de resultados
            ResultSet rs = cstmt.executeQuery();
        } catch (SQLException e) {

        }

    }

    //TODO: arreglar xq no recive el refcursor
    public void colletCargoBySunadtest() {
        try (CallableStatement cstmt = connection.prepareCall("{? = call colletcargobysunadtest() }")) {

            cstmt.registerOutParameter(1, Types.REF_CURSOR);
            cstmt.execute();
            ResultSet rset = (ResultSet) cstmt.getObject(1);

            // Dump the cursor
            while (rset.next()) {
                int compartment = rset.getInt("compartment");
                int floor = rset.getInt("floor");
                int shelf = rset.getInt("shelf");
                int warehouseNumber = rset.getInt("warehouseNumber");
            }
            /*ResultSet rs = cstmt.executeQuery();

            while ( rs.next())
            {
                int compartment = rs.getInt("compartment");
                int floor = rs.getInt("floor");
                int shelf = rs.getInt("shelf");
                int warehouseNumber = rs.getInt("warehose_number");

            }*/
            //cstmt.executeQuery();
            // Find out all the SALES person
            // Obtener el conjunto de resultados
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public float getOutCargoById(int cargoId) {
        float totalAmount = 0;
        try {
            CallableStatement cstmt = connection.prepareCall("{ call getOutCargoById(?)}");
            cstmt.setInt(1, cargoId);
            ResultSet rs = cstmt.executeQuery();
            totalAmount = rs.getFloat("total_amountt");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return  totalAmount;
    }


    public ArrayList<CargoDto> getAbandonedCargoesOnLocation() {
        ArrayList<CargoDto> cargoes = new ArrayList<>();
        try {

            CallableStatement cstmt = connection.prepareCall("{ call get_abandoned_cargoes_on_LOCATION()}");
                ResultSet resultSet = cstmt.executeQuery();
                    while (resultSet.next()) {
                        CargoDto cargoDto = new CargoDto();
                        cargoDto.setId(resultSet.getInt("cargo_id"));
                        cargoDto.setName(resultSet.getString("cargo_code"));
                        cargoDto.setRefrigeration(resultSet.getBoolean("refrigeration"));
                        cargoDto.setExpirationDate(resultSet.getTimestamp("expiration_date"));
                        cargoDto.setPackedUnitWeight(resultSet.getFloat("packed_unit_weight"));
                        cargoDto.setPackParts(resultSet.getInt("pack_parts"));
                        cargoDto.setWeight(resultSet.getFloat("weight"));
                        cargoDto.setCompartment(resultSet.getInt("COMPARTMENT"));
                        cargoDto.setFloor(resultSet.getInt("FLOOR"));
                        cargoDto.setShelf(resultSet.getInt("SHELF"));
                        cargoDto.setWarehouseNumber(resultSet.getInt("WAREHOUSE_NUMBER"));

                        cargoes.add(cargoDto);
                    }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cargoes;
    }
    public ArrayList<CargoDto> get_active_cargoes_for_client(int client_id) {
        ArrayList<CargoDto> cargoes = new ArrayList<>();
        try {

            CallableStatement cstmt = connection.prepareCall("{ call get_active_cargoes_for_client(?)}");
            cstmt.setInt(1, client_id);
            ResultSet resultSet = cstmt.executeQuery();
            while (resultSet.next()) {
                CargoDto cargoDto = new CargoDto();
                cargoDto.setId(resultSet.getInt("cargo_id"));
                cargoDto.setName(resultSet.getString("cargo_code"));
                cargoDto.setRefrigeration(resultSet.getBoolean("refrigeration"));
                cargoDto.setExpirationDate(resultSet.getTimestamp("expiration_date"));
                cargoDto.setPackedUnitWeight(resultSet.getFloat("packed_unit_weight"));
                cargoDto.setPackParts(resultSet.getInt("pack_parts"));
                cargoDto.setWeight(resultSet.getFloat("weight"));
                cargoDto.setTotalAmount(resultSet.getFloat("total_amount"));
                cargoDto.setCompartment(resultSet.getInt("COMPARTMENT"));
                cargoDto.setFloor(resultSet.getInt("FLOOR"));
                cargoDto.setShelf(resultSet.getInt("SHELF"));
                cargoDto.setWarehouseNumber(resultSet.getInt("WAREHOUSE_NUMBER"));

                cargoes.add(cargoDto);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cargoes;
    }
}