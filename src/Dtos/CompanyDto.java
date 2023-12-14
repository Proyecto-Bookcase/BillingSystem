package Dtos;

import java.util.ArrayList;

public class CompanyDto {
    private int id;
    private String name;
    private float fuelTariff;
    private CompanyTypeDto companyType;
    private int enterpriseId;

    private ArrayList<ConditioningCompanyDto> conditionings;
    private ArrayList<HandlingGoodsDto> handlingGoods;
    private ArrayList<PriorityCompanyDto> priorityCompanies;

    public ArrayList<ConditioningCompanyDto> getConditionings() {
        return conditionings;
    }

    public void setConditionings(ArrayList<ConditioningCompanyDto> conditionings) {
        this.conditionings = conditionings;
    }

    public ArrayList<HandlingGoodsDto> getHandlingGoods() {
        return handlingGoods;
    }

    public void setHandlingGoods(ArrayList<HandlingGoodsDto> handlingGoods) {
        this.handlingGoods = handlingGoods;
    }

    public ArrayList<PriorityCompanyDto> getPriorityCompanies() {
        return priorityCompanies;
    }

    public void setPriorityCompanies(ArrayList<PriorityCompanyDto> priorityCompanies) {
        this.priorityCompanies = priorityCompanies;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getFuelTariff() {
        return fuelTariff;
    }

    public void setFuelTariff(float fuelTariff) {
        this.fuelTariff = fuelTariff;
    }

    public int getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(int enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public CompanyTypeDto getCompanyType() {
        return companyType;
    }

    public void setCompanyType(CompanyTypeDto companyType) {
        this.companyType = companyType;
    }
}
