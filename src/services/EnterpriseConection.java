package services;

import Dtos.EnterpirseDto;


import java.sql.*;

public class EnterpriseConection {
    public static DbManager manager;
    public EnterpriseConection(){
        manager = DbManager.getDbManager();
    }

    public EnterpirseDto getEnterpirse(){


        try {
            Statement stmt = manager.getConnection().createStatement();
            // Ejecutar una consulta estática

            ResultSet rs = stmt.executeQuery("SELECT * FROM enterpirse;");

            // Procesar los resultados
            while (rs.next()) {
                // Obtener los valores de las columnas
                int id = rs.getInt("id");
                String name = rs.getString("name");
                System.out.println("ID: " + id + ", Name: " + name);
                // ...
            }

            // Cerrar la conexión y los recursos
            rs.close();
            stmt.close();
            //conn.close();

        }catch (Exception e){

        }


        return null;
    }

    public EnterpirseDto getEnterpirseStaticaFunction(int id){

    try {


        // Crear un objeto PreparedStatement
        //PreparedStatement pstmt = manager.getConnection().prepareStatement("SELECT * FROM enterprise WHERE id = ?");
        PreparedStatement pstmt = manager.getConnection().prepareStatement("SELECT getenterprise(?)");

        // Establecer los parámetros de la consulta
        pstmt.setInt(1, id);

        // Ejecutar la consulta
        ResultSet rs = pstmt.executeQuery();

        // Procesar los resultados
            while (rs.next()) {
            // Obtener los valores de las columnas
            int id2 = rs.getInt("id");
            String name = rs.getString("name");
            System.out.println("llegó aqui");
            System.out.println("ID: " + id2 + ", Name: " + name);
            // ...
        }

            // Cerrar la conexión y los recursos
            rs.close();
            pstmt.close();
            //conn.close();
    }catch (Exception e){
        System.out.println(e.getMessage());
    }


        return null;
    }
    public EnterpirseDto getEnterpirseDbFunction(int id){
        EnterpirseDto enterpriseDTO = null;
        try {


            CallableStatement cstmt = manager.getConnection().prepareCall("{ call getenterprise(?)}");

            // Establecer los parámetros de la función almacenada
            cstmt.setInt(1, id);

            ResultSet rs = cstmt.executeQuery();

            // Procesar los resultados
            while (rs.next()) {
                 enterpriseDTO = new EnterpirseDto(
                        rs.getString("NAME"),
                        rs.getInt("ID"),
                        rs.getString("POSTAL_DIR"),
                        rs.getString("HR_DEP_BOSS_NAME"),
                        rs.getString("CONT_DEP_RESP_NAME"),
                        rs.getString("SIND_CORE_SEC_NAME"),
                        rs.getString("LOGO"),
                        rs.getFloat("REFRI_CHARGE"),
                        rs.getFloat("BILL_AMOUNT"),
                        rs.getString("GENERAL_BOSS_NAME"),
                        rs.getFloat("DISCOUNT"),
                        rs.getString("PHONE_NUMBER"),
                        rs.getString("EMAIL"),
                        rs.getFloat("TARIFF_PER_HOURS"),
                        rs.getFloat("TARIFF_PER_WEIGHT")
                );

            }

            // Cerrar la conexión y los recursos
            rs.close();
            cstmt.close();
            //conn.close();

        }catch (Exception e) {
            System.out.println(e.getMessage());
        }


        return enterpriseDTO;
    }
    public void updateEnterprise(int p_id, String p_name, String p_postal_dir,
                                 String p_hr_dep_boss_name, String p_cont_dep_resp_name,
                                 String p_sind_core_sec_name, String p_logo,
                                 float p_refri_charge, float p_bill_amount,
                                 String p_general_boss_name, float p_discount,
                                 String p_phone_number, String p_email,
                                 float p_tariff_per_hours, float p_tariff_per_weight)
    {
        try {


            //CallableStatement cstmt = manager.getConnection().prepareCall("{ call update_enterprise(?)}");
            CallableStatement cstmt = manager.getConnection().prepareCall(
                    "{ call update_enterprise(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");

            // Establecer los parámetros de la función almacenada
            cstmt.setInt(1, p_id);
            cstmt.setString(2, p_name);
            cstmt.setString(3, p_postal_dir);
            cstmt.setString(4, p_hr_dep_boss_name);
            cstmt.setString(5, p_cont_dep_resp_name);
            cstmt.setString(6, p_sind_core_sec_name);
            cstmt.setString(7, p_logo);
            cstmt.setFloat(8, p_refri_charge);
            cstmt.setFloat(9, p_bill_amount);
            cstmt.setString(10, p_general_boss_name);
            cstmt.setFloat(11, p_discount);
            cstmt.setString(12, p_phone_number);
            cstmt.setString(13, p_email);
            cstmt.setFloat(14, p_tariff_per_hours);
            cstmt.setFloat(15, p_tariff_per_weight);


            ResultSet rs = cstmt.executeQuery();
            // Cerrar la conexión y los recursos
            rs.close();
            cstmt.close();
            //conn.close();

        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
