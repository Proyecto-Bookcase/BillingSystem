package services;

import Dtos.CargoDto;
import Dtos.PackedTypeDto;
import Dtos.ProductTypeDto;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CargoServices {
    private Connection connection;
    public CargoServices(Connection connection){
        this.connection = connection;
    }

    public void insertCargo(
            String p_name, boolean p_refrigeration, Date p_expiration_date, float p_packed_unit_weight,
            int p_pack_parts, float p_weight, int p_producttype_id, int p_packedtype_id, int p_client_id,
            Date p_arrival_date, Date p_departure_date, Date p_actual_departure_date,
            float p_loading_cost, float p_unloading_cost,
            int p_compartment, int p_floor, int p_shelf, int p_warehouse_number
    ) {

        try {
            // Llama al procedimiento almacenado "insert_cargo" con los parámetros
            CallableStatement cstmt = connection.prepareCall(
                    "{ call insert_cargo(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) }");

            // Configura los parámetros
            cstmt.setString(1, p_name);
            cstmt.setBoolean(2, p_refrigeration);
            cstmt.setDate(3, p_expiration_date);
            cstmt.setFloat(4, p_packed_unit_weight);
            cstmt.setInt(5, p_pack_parts);
            cstmt.setFloat(6, p_weight);
            cstmt.setInt(7, p_producttype_id);
            cstmt.setInt(8, p_packedtype_id);
            cstmt.setInt(9, p_client_id);
            cstmt.setDate(10, p_arrival_date);
            cstmt.setDate(11, p_departure_date);
            cstmt.setDate(12, p_actual_departure_date);
            cstmt.setFloat(13, p_loading_cost);
            cstmt.setFloat(14, p_unloading_cost);

            // Parámetros de localización
            cstmt.setInt(15, p_compartment);
            cstmt.setInt(16, p_floor);
            cstmt.setInt(17, p_shelf);
            cstmt.setInt(18, p_warehouse_number);

            // Ejecutar la llamada al procedimiento almacenado
            cstmt.execute();

            // Cerrar el CallableStatement
            cstmt.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteCargo(int cargoId){
        try {
            CallableStatement cstmt = connection.prepareCall("{ call delete_cargo(?)}");
            // Configura los parámetros
            cstmt.setInt(1, cargoId);
            cstmt.executeQuery();
        }catch (Exception e){
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
                cargoDTO.setLoadingCost(rs.getFloat("LOADING_COST"));
                cargoDTO.setUnloadingCost(rs.getFloat("UNLOADING_COST"));
                cargoDTO.setCompartment(rs.getInt("COMPARTMENT"));
                cargoDTO.setFloor(rs.getInt("FLOOR"));
                cargoDTO.setShelf(rs.getInt("SHELF"));
                cargoDTO.setWarehouseNumber(rs.getInt("WAREHOUSE_NUMBER"));

            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return cargoDTO;

    }
    public ArrayList<CargoDto>  getAllCargoDbFunction() {
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
                cargoDTO.setLoadingCost(rs.getFloat("LOADING_COST"));
                cargoDTO.setUnloadingCost(rs.getFloat("UNLOADING_COST"));
                cargoDTO.setCompartment(rs.getInt("COMPARTMENT"));
                cargoDTO.setFloor(rs.getInt("FLOOR"));
                cargoDTO.setShelf(rs.getInt("SHELF"));
                cargoDTO.setWarehouseNumber(rs.getInt("WAREHOUSE_NUMBER"));
                //add cargo to list
                cargoDTOs.add(cargoDTO);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return cargoDTOs;

    }
}
