package services;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbManager {
    //db contection
    private static String userName = "postgres";
    private static String password = "postgres";
    private  static Connection connection;
    private  static  DbManager manager;

    private DbManager()
    {
        try {


            Class.forName("org.postgresql.Driver");

            connection =  DriverManager.getConnection("jdbc:postgresql://localhost:5432/facturation_system_2", userName, password);
            System.out.println("conectado con exito");

        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("error al conectar");


        }

    }
    public  static DbManager getDbManager()
    {
        if(manager == null) {
            manager = new DbManager();
        }
        return manager;
    }

    public Connection getConnection() {

        return connection;
    }
}
