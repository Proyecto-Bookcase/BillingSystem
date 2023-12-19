package services;

import Dtos.*;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

public class CompanyServices {
    private Connection connection;

    public CompanyServices(Connection connection) {
        this.connection = connection;
    }

    /*
    public void get_company_info()
    {
        try {



            // Llama al procedimiento almacenado "insertclient" con los parámetros
            CallableStatement cstmt1 = manager.getConnection().prepareCall(
                    "{ call insert_client(?)}");

            ResultSet rs = cstmt1.executeQuery();
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void getCompanyInfo() {
        try {
            //CallableStatement cstmt = manager.getConnection().prepareCall("{ call get_company_info(?) }");
            CallableStatement cstmt = manager.getConnection().prepareCall("{ call get_company_info(?) }");
            //cstmt.setObject(1, Types.REF_CURSOR);
            cstmt.registerOutParameter(1, Types.REF);
            cstmt.execute();

            //ResultSet rs = (ResultSet) cstmt.getObject(1);
            ResultSet rs = cstmt.executeQuery();
            while (rs.next()) {
                // Retrieve the values from the result set
                String name = rs.getString(1);
                String description = rs.getString(2);
                // Retrieve the array values
                Array securityLevelsArray = rs.getArray(3);
                Array conditioningCriteriaArray = rs.getArray(4);
                Array priorityAlternativesArray = rs.getArray(5);
                // Convert the array values to Java arrays
                String[] securityLevels = (String[]) securityLevelsArray.getArray();
                String[] conditioningCriteria = (String[]) conditioningCriteriaArray.getArray();
                String[] priorityAlternatives = (String[]) priorityAlternativesArray.getArray();
                System.out.println("Name: " + name);
                System.out.println("Description: " + description);
                System.out.println("Security Levels: " + Arrays.toString(securityLevels));
                System.out.println("Conditioning Criteria: " + Arrays.toString(conditioningCriteria));
                System.out.println("Priority Alternatives: " + Arrays.toString(priorityAlternatives));
                // Do something with the retrieved values
            }

            rs.close();
            cstmt.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }*/
    public void insertCompany(String name, float fuelTariff, int companyTypeId, int enterpriseId,
                              Integer[] conditionings, Integer[] handlingGoods, Integer[] prioritys) {
        try {
            CallableStatement cstmt = connection.prepareCall("{call insert_company(?, ?, ?, ?, ?, ?, ?)}");
            // Configurar los parámetros
            cstmt.setString(1, name);
            cstmt.setFloat(2, fuelTariff);
            cstmt.setInt(3, companyTypeId);
            cstmt.setInt(4, enterpriseId);
            cstmt.setArray(5, connection.createArrayOf("integer", conditionings));
            cstmt.setArray(6, connection.createArrayOf("integer", handlingGoods));
            cstmt.setArray(7, connection.createArrayOf("integer", prioritys));

            // Ejecutar la llamada a la función
            cstmt.executeQuery();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteCompany(int id) {
        try {
            CallableStatement cstmt = connection.prepareCall("{call delete_company(?)}");
            cstmt.setInt(1, id);
            cstmt.executeQuery();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public CompanyDto getCompany(int companyId) {
        CompanyDto companyDto = new CompanyDto();
        try {
            CallableStatement cstmt = connection.prepareCall("{  call getCompany(?) }");
            cstmt.setInt(1, companyId);


            // Obtener el conjunto de resultados
            ResultSet resultSet = cstmt.executeQuery();

            if (resultSet.next()) {
                companyDto.setId(resultSet.getInt("ID"));
                companyDto.setName(resultSet.getString("NAME"));
                companyDto.setFuelTariff(resultSet.getFloat("FUEL_TARIFF"));
                companyDto.setCompanyType(new CompanyTypeDto(resultSet.getInt("COMPANYTYPE__ID"), resultSet.getString("COMPANYTYPE_description")));
                companyDto.setEnterpriseId(resultSet.getInt("ENTERPRISE__ID"));
                //ConditioningCompanyServices conditioningCompanyConnection = new ConditioningCompanyServices();
                ConditioningCompanyServices conditioningCompanyConnection = ServicesLocator.getConditioningCompanyServices();
                ArrayList<ConditioningCompanyDto> conditioningCompanies = conditioningCompanyConnection.getAllConditioningCompanyByCompany(companyDto.getId());
                //HandlingGoodsServices handlingGoodsConnection = new HandlingGoodsServices();
                HandlingGoodsServices handlingGoodsConnection = ServicesLocator.getHandlingGoodsServices();
                ArrayList<HandlingGoodsDto> handlingGoods = handlingGoodsConnection.getAllHandlingGoodsCompany(companyDto.getId());
                //PriorityCompanyServices priorityCompanyConnection = new PriorityCompanyServices();
                PriorityCompanyServices priorityCompanyConnection = ServicesLocator.getPriorityCompanyServices();
                ArrayList<PriorityCompanyDto> priorityCompanies = priorityCompanyConnection.getAllPriorityCompanyByCompany(companyDto.getId());
                companyDto.setConditionings(conditioningCompanies);
                companyDto.setHandlingGoods(handlingGoods);
                companyDto.setPriorityCompanies(priorityCompanies);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return companyDto;
    }

    public ArrayList<CompanyDto> getAllCompany() {
        ArrayList<CompanyDto> companyDtoList = new ArrayList<CompanyDto>();
        try {
            CallableStatement cstmt = connection.prepareCall("{  call get_all_companies() }");
            // Obtener el conjunto de resultados
            ResultSet resultSet = cstmt.executeQuery();

            while (resultSet.next()) {
                CompanyDto companyDto = new CompanyDto();
                companyDto.setId(resultSet.getInt("ID"));
                companyDto.setName(resultSet.getString("NAME"));
                companyDto.setFuelTariff(resultSet.getFloat("FUEL_TARIFF"));
                companyDto.setCompanyType(new CompanyTypeDto(resultSet.getInt("COMPANYTYPE__ID"), resultSet.getString("COMPANYTYPE_description")));
                companyDto.setEnterpriseId(resultSet.getInt("ENTERPRISE__ID"));
                ConditioningCompanyServices conditioningCompanyConnection = ServicesLocator.getConditioningCompanyServices();
                ArrayList<ConditioningCompanyDto> conditioningCompanies = conditioningCompanyConnection.getAllConditioningCompanyByCompany(companyDto.getId());
                HandlingGoodsServices handlingGoodsConnection = ServicesLocator.getHandlingGoodsServices();
                ArrayList<HandlingGoodsDto> handlingGoods = handlingGoodsConnection.getAllHandlingGoodsCompany(companyDto.getId());
                PriorityCompanyServices priorityCompanyConnection = ServicesLocator.getPriorityCompanyServices();
                ArrayList<PriorityCompanyDto> priorityCompanies = priorityCompanyConnection.getAllPriorityCompanyByCompany(companyDto.getId());
                companyDto.setConditionings(conditioningCompanies);
                companyDto.setHandlingGoods(handlingGoods);
                companyDto.setPriorityCompanies(priorityCompanies);
                companyDtoList.add(companyDto);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return companyDtoList;
    }


    public void updateCompany(CompanyDto companyDto) {
        // Declarar un arreglo de Integer de tamaño 5
        Integer[] arrayConditionings = new Integer[companyDto.getConditionings().size()];
        // Llenar el arreglo con un bucle for
        for (int i = 0; i < arrayConditionings.length; i++) {
            arrayConditionings[i] = (i + 1) * 10;  // Asignar valores a cada posición del arreglo
        }
        Integer[] arrayHandlingGoods = new Integer[companyDto.getConditionings().size()];
        // Llenar el arreglo con un bucle for
        for (int i = 0; i < arrayHandlingGoods.length; i++) {
            arrayHandlingGoods[i] = (i + 1) * 10;  // Asignar valores a cada posición del arreglo
        }
        Integer[] arrayPriorityCompanies = new Integer[companyDto.getConditionings().size()];
        // Llenar el arreglo con un bucle for
        for (int i = 0; i < arrayPriorityCompanies.length; i++) {
            arrayPriorityCompanies[i] = (i + 1) * 10;  // Asignar valores a cada posición del arreglo
        }

        try {
            CallableStatement cstmt = connection.prepareCall("{  call update_company(?,?,?,?,?,?,?,?)}");
            cstmt.setInt(1, companyDto.getId());
            cstmt.setString(2, companyDto.getName());
            cstmt.setFloat(3, companyDto.getFuelTariff());
            cstmt.setInt(4, companyDto.getCompanyType().getId());
            cstmt.setInt(5, companyDto.getEnterpriseId());
            cstmt.setArray(6, connection.createArrayOf("integer", arrayConditionings));
            cstmt.setArray(7, connection.createArrayOf("integer", arrayHandlingGoods));
            cstmt.setArray(8, connection.createArrayOf("integer", arrayPriorityCompanies));
        } catch (Exception e) {
            //throw new RuntimeException(e);
            System.out.println(e.getMessage());
        }
    }
}
