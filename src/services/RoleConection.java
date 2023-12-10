package services;

import Entity.User;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RoleConection {

    public static  DbManager manager;

    public RoleConection(){
        manager = DbManager.getDbManager();
    }



}
