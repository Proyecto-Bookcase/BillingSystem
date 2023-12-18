package services;

import conf.Secret;

import java.sql.Connection;
import java.sql.DriverManager;

public class ServicesLocator {
    //db contection

    private static ServicesLocator manager;

    private static String userName = Secret.username;
    private static String password = Secret.password;
    private Connection connection;
    private CargoServices cargoServices;
    private ClientServices clientServices;
    private CompanyServices companyServices;
    private CompanyTypeServices companyTypeServices;
    private ConditioningCompanyServices conditioningCompanyServices;
    private EnterpriseServices enterpriseServices;
    private HandlingGoodsServices handlingGoodsServices;
    private LocationServices locationServices;
    private PackedTypeServices packedTypeServices;
    private PriorityCompanyServices priorityCompanyServices;
    private ProductTypeServices productTypeServices;
    private RoleServices roleServices;
    private UserServices userServices;
    private WarehoseSevices warehoseSevices;

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
        return getDbManager().cargoServices;
    }

    public static ClientServices getClientServices() {
        return getDbManager().clientServices;
    }

    public static CompanyServices getCompanyServices() {
        return getDbManager().companyServices;
    }

    public static CompanyTypeServices getCompanyTypeServices() {
        return getDbManager().companyTypeServices;
    }

    public static EnterpriseServices getEnterpriseServices() {
        return getDbManager().enterpriseServices;
    }

    public static HandlingGoodsServices getHandlingGoodsServices() {
        return getDbManager().handlingGoodsServices;
    }

    public static LocationServices getLocationServices() {
        return getDbManager().locationServices;
    }

    public static PackedTypeServices getPackedTypeServices() {
        return getDbManager().packedTypeServices;
    }

    public static PriorityCompanyServices getPriorityCompanyServices() {
        return getDbManager().priorityCompanyServices;
    }

    public static ProductTypeServices getProductTypeServices() {
        return getDbManager().productTypeServices;
    }

    public static RoleServices getRoleServices() {
        return getDbManager().roleServices;
    }

    public static UserServices getUserServices() {
        return getDbManager().userServices;
    }

    public static WarehoseSevices getWarehoseSevices() {
        return getDbManager().warehoseSevices;
    }

    public static ConditioningCompanyServices getConditioningCompanyServices() {
        return getDbManager().conditioningCompanyServices;
    }
}
