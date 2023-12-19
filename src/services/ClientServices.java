package services;
import Dtos.ClientDto;

import java.sql.*;
import java.util.ArrayList;

public class ClientServices {
    private Connection connection;
    public ClientServices(Connection connection){
        this.connection = connection;
    }

    public  void insertClient(String p_name, String p_type, String p_country,
                         String p_phone_number, String p_fax, String p_email,
                         char p_attention_type, int p_antique, Date p_preferred_rate) throws Exception {
        try {
            // Llama al procedimiento almacenado "insertclient" con los parámetros
            CallableStatement cstmt1 = connection.prepareCall(
                    "{ call insert_client(?, ?, ?, ?, ?, ?, ?, ?, ?)}");



            // Configura los parámetros
            cstmt1.setString(1, p_name);
            cstmt1.setString(2, p_type);
            cstmt1.setString(3, p_country);
            cstmt1.setString(4, p_phone_number);
            cstmt1.setString(5, p_fax);
            cstmt1.setString(6, p_email);
            cstmt1.setString(7, String.valueOf(p_attention_type)); // Convertir char a String
            cstmt1.setInt(8, p_antique);
            cstmt1.setDate(9, p_preferred_rate);

            ResultSet rs = cstmt1.executeQuery();
        }catch (Exception e) {
            throw new Exception(e);
        }


    }



    public ClientDto getClientDbFunction(int id) throws Exception {
        ClientDto clientDTO = null;
        try {
            CallableStatement cstmt = connection.prepareCall("{ call getclient(?) }");

            cstmt.setInt(1, id);
            ResultSet rs = cstmt.executeQuery();
            if (rs.next()) {
                 clientDTO = new ClientDto(
                        rs.getString("NAME"),
                        rs.getString("TYPE"),
                        rs.getString("COUNTRY"),
                        rs.getString("PHONE_NUMBER"),
                        rs.getString("FAX"),
                        rs.getString("EMAIL"),
                        rs.getString("ATTENTION_TYPE").charAt(0),
                        rs.getInt("ANTIQUE"),
                        rs.getInt("ID"),
                        rs.getDate("PREFERRED_RATE")
                );
            }

            // Cerrar el ResultSet y el CallableStatement
            rs.close();
            cstmt.close();

        } catch (SQLException e) {
            throw  new Exception(e);
        }
        return clientDTO;
    }

    public ArrayList<ClientDto> getClientAllClientFunction() throws Exception {
        ArrayList<ClientDto> clients = new ArrayList<ClientDto>();
        try {
            CallableStatement cstmt = connection.prepareCall("{ call getallclients() }");

            // Ejecutar la consulta
            ResultSet rs = cstmt.executeQuery();
            while (rs.next()) {
                // Crear una instancia del DTO con los valores recuperados
                ClientDto clientDto= new ClientDto(
                        rs.getString("NAME"),
                        rs.getString("TYPE"),
                        rs.getString("COUNTRY"),
                        rs.getString("PHONE_NUMBER"),
                        rs.getString("FAX"),
                        rs.getString("EMAIL"),
                        rs.getString("ATTENTION_TYPE").charAt(0),
                        rs.getInt("ANTIQUE"),
                        rs.getInt("ID"),
                        rs.getDate("PREFERRED_RATE")
                );
                clients.add(clientDto);

            }


        } catch (SQLException e) {
            throw new Exception(e);
        }
        return clients;
    }

    public void updateClient(ClientDto client){
        try {
            CallableStatement cstmt = connection.prepareCall("{ call update_client(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
            // Configura los parámetros
            cstmt.setInt(1, client.getId());
            cstmt.setString(2, client.getName());
            cstmt.setString(3, client.getType());
            cstmt.setString(4, client.getCountry());
            cstmt.setString(5, client.getPhoneNumber());
            cstmt.setString(6, client.getFax());
            cstmt.setString(7, client.getEmail());
            cstmt.setString(8, String.valueOf(client.getAttentionType())); // Convertir char a String
            cstmt.setInt(9, client.getAntique());
            cstmt.setDate(10, client.getPreferredRate());

            cstmt.executeQuery();

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void deleteClient(int id) throws Exception {
        try {
            CallableStatement cstmt = connection.prepareCall("{ call delete_client(?)}");
            // Configura los parámetros
            cstmt.setInt(1, id);
            cstmt.executeQuery();
        }catch (Exception e){
            throw new Exception(e);
        }
    }

}
