package services;

import Dtos.ActualUser;
import Dtos.Dto;
import Dtos.RoleDto;
import Dtos.UserDto;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserServices {

    private Connection connection;
    public UserServices(Connection connection){
        this.connection = connection;
    }

    public void inset_User(String username, String password, String email, int id_Enterprise, int id_role){

        try{
            CallableStatement cstmt = connection.prepareCall(
                    "{call insert_user(?,?,?,?,?)}");
            cstmt.setString(1,username);
            cstmt.setString(2,password);
            cstmt.setString(3,email);
            cstmt.setInt(4,id_Enterprise);
            cstmt.setInt(5,id_role);

            cstmt.executeQuery();

        }catch(SQLException e){
            throw new RuntimeException();
        }
    }
    public void delete_User(String username){

        try {
            CallableStatement cstmt = connection.prepareCall(
                    "{call delete_user(?)}");
            cstmt.setString(1,username);

            cstmt.executeQuery();
        }catch(SQLException e){
            throw new RuntimeException();
        }
    }
    public void update_User(String username, String password, String email, int id_Enterprise, int id_role){

        try{

            CallableStatement cstmt = connection.prepareCall(
                    "{call update_user(?,?,?,?,?)}");
            cstmt.setString(1,username);
            cstmt.setString(2,password);
            cstmt.setString(3,email);
            cstmt.setInt(4,id_Enterprise);
            cstmt.setInt(5,id_role);

            cstmt.executeQuery();

        }catch(SQLException e){
            throw new RuntimeException();
        }
    }
    public ArrayList<UserDto> getAll_Users(){

        ArrayList<UserDto> list_users = new ArrayList<UserDto>();
        try{
            CallableStatement cstmt = connection.prepareCall(
                    "{call get_all_userr()}");
            ResultSet rs = cstmt.executeQuery();
            while (rs.next()){
                UserDto user = new UserDto(
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("email")
                );
                list_users.add(user);
            }
        }catch(SQLException e){
            throw new RuntimeException();
        }
        return list_users;
    }
    public UserDto getUser(String username){
        UserDto user = null;
        try {
            CallableStatement cstmt = connection.prepareCall(
                    "{call get_user_info(?)}");
            cstmt.setString(1,username);

            ResultSet rs = cstmt.executeQuery();
            System.out.println("1");
            if (rs.next()){
                System.out.println("2");
                user = new UserDto(
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("email")
                );
                System.out.println("3");
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    public boolean checkUser(String username, String password){
        boolean esc = true;
        ActualUser actualUser = ActualUser.getActualUser();
        try {
            CallableStatement cstmt = connection.prepareCall(
                    "{call get_user_info2(?,?)}");
            cstmt.setString(1,username);
            cstmt.setString(2,password);

            ResultSet rs = cstmt.executeQuery();
            System.out.println("1");
            if (rs.next()){
                System.out.println("2");
                actualUser.setPassword(rs.getString("password"));
                actualUser.setEmail(rs.getString("email"));
                actualUser.setUsername(rs.getString("username"));
                actualUser.setRole(new RoleDto(rs.getInt("role_id"), rs.getString("role")));

                CallableStatement cstmt2 = connection.prepareCall(
                        "{call get_permissions_for_role(?)}");
                cstmt2.setInt(1, actualUser.getRole().getId());

                ResultSet rs2 = cstmt2.executeQuery();
                ArrayList<String> permissions = new ArrayList<String>();
                while (rs2.next()){
                    permissions.add(rs2.getString("permission_identifier"));
                }
                actualUser.setPermissions(permissions);
            }
            else {
                esc = false;
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return esc;
    }

    public boolean checkPermission(String permission)
    {
        boolean esc = false;
        ActualUser actualUser = ActualUser.getActualUser();
        ArrayList<String> permissions = actualUser.getPermissions();
        int index = 0;
        while (index < permissions.size() && !esc) {
            String perm = permissions.get(index);
            if (perm.equals(permission)) {
                esc = true;
            }
            index++;
        }

        return esc;
    }
}
