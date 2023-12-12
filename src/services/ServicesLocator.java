package services;

import java.sql.Connection;
import java.sql.DriverManager;

public class ServicesLocator {
    //db contection
    private static String userName = "postgres";
    private static String password = "postgres";
    private  static Connection connection;
    private  static ServicesLocator manager;

    private ServicesLocator()
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
    public  static ServicesLocator getDbManager()
    {
        if(manager == null) {
            manager = new ServicesLocator();
        }
        return manager;
    }

    public Connection getConnection() {

        return connection;
    }
}
