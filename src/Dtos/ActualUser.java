package Dtos;

import java.util.ArrayList;

public class ActualUser {
    private String username;
    private String password ;
    private String email;
    private RoleDto role;
    private ArrayList<String> permissions;

    private static ActualUser instance;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public RoleDto getRole() {
        return role;
    }

    public void setRole(RoleDto role) {
        this.role = role;
    }

    public ArrayList<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(ArrayList<String> permissions) {
        this.permissions = permissions;
    }

    private ActualUser(){
        permissions = new ArrayList<String>();
        role = new RoleDto();
    }

    public static ActualUser getActualUser()
    {

        if(instance == null) {
            instance = new ActualUser();
        }
        return instance;
    }


    @Override
    public String toString() {
        String stringElement = "UserDto{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' ;
        stringElement += ", roleid=" + role.getId()+ '\'';
        stringElement += ", roledescription=" + role.getDescription()+ '\'';
        for (String permission : permissions) {
            stringElement += ", permission=" + permission + '\'';
        }

        return stringElement;
    }
}
