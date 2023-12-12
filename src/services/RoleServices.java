package services;

public class RoleServices {

    public static ServicesLocator manager;

    public RoleServices(){
        manager = ServicesLocator.getDbManager();
    }



}
