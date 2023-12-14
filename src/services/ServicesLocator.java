package services;

import java.sql.Connection;
import java.sql.DriverManager;

public class ServicesLocator {
    //db contection
    private static String userName = "postgres";
    private static String password = "postgres";
    private  static Connection connection;
    private  static ServicesLocator manager;

    private static CargoServices cargoServices;
    private static ClientServices clientServices;
    private static CompanyServices companyServices;
    private static CompanyTypeServices companyTypeServices;
    private static ConditioningCompanyServices conditioningCompanyServices;
    private static EnterpriseServices enterpriseServices;
    private static HandlingGoodsServices handlingGoodsServices;
    private static LocationServices locationServices;
    private static PackedTypeServices packedTypeServices;
    private static PriorityCompanyServices priorityCompanyServices;
    private static ProductTypeServices productTypeServices;
    private static RoleServices roleServices;
    private static UserServices userServices;
    private static WarehoseSevices warehoseSevices;

    private ServicesLocator()
    {
        try {

            Class.forName("org.postgresql.Driver");

            connection =  DriverManager.getConnection("jdbc:postgresql://localhost:5432/facturation_system_2", userName, password);
            System.out.println("conectado con exito");

            cargoServices = new CargoServices(connection);
            clientServices = new ClientServices(connection);
            companyServices = new CompanyServices(connection);
            companyTypeServices = new CompanyTypeServices(connection);
            enterpriseServices = new EnterpriseServices(connection);
            handlingGoodsServices = new HandlingGoodsServices(connection);
            locationServices = new LocationServices(connection);
            packedTypeServices = new PackedTypeServices(connection);
            priorityCompanyServices = new PriorityCompanyServices(connection);
            productTypeServices = new ProductTypeServices(connection);
            roleServices = new RoleServices(connection);
            userServices = new UserServices(connection);
            warehoseSevices = new WarehoseSevices(connection);
            conditioningCompanyServices = new ConditioningCompanyServices(connection);

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

    public static CargoServices getCargoServices() {
        return cargoServices;
    }

    public static ClientServices getClientServices() {
        return clientServices;
    }

    public static CompanyServices getCompanyServices() {
        return companyServices;
    }

    public static CompanyTypeServices getCompanyTypeServices() {
        return companyTypeServices;
    }

    public static EnterpriseServices getEnterpriseServices() {
        return enterpriseServices;
    }

    public static HandlingGoodsServices getHandlingGoodsServices() {
        return handlingGoodsServices;
    }

    public static LocationServices getLocationServices() {
        return locationServices;
    }

    public static PackedTypeServices getPackedTypeServices() {
        return packedTypeServices;
    }

    public static PriorityCompanyServices getPriorityCompanyServices() {
        return priorityCompanyServices;
    }

    public static ProductTypeServices getProductTypeServices() {
        return productTypeServices;
    }

    public static RoleServices getRoleServices() {
        return roleServices;
    }

    public static UserServices getUserServices() {
        return userServices;
    }

    public static WarehoseSevices getWarehoseSevices() {
        return warehoseSevices;
    }

    public static ConditioningCompanyServices getConditioningCompanyServices() {
        return conditioningCompanyServices;
    }
}
