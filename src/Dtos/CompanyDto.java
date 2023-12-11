package Dtos;

import Entity.CompanyType;
import Entity.ConditioningCompany;
import Entity.HandlingGoods;
import Entity.PriorityCompany;

import java.util.ArrayList;

public class CompanyDto {
    private int id;
    private String name;
    private float fuelTariff;
    private CompanyType companyType;
    private int enterpriseId;

    private ArrayList<ConditioningCompany> conditionings;
    private ArrayList<HandlingGoods> handlingGoods;
    private ArrayList<PriorityCompany> priorityCompanies;

    public ArrayList<ConditioningCompany> getConditionings() {
        return conditionings;
    }

    public void setConditionings(ArrayList<ConditioningCompany> conditionings) {
        this.conditionings = conditionings;
    }

    public ArrayList<HandlingGoods> getHandlingGoods() {
        return handlingGoods;
    }

    public void setHandlingGoods(ArrayList<HandlingGoods> handlingGoods) {
        this.handlingGoods = handlingGoods;
    }

    public ArrayList<PriorityCompany> getPriorityCompanies() {
        return priorityCompanies;
    }

    public void setPriorityCompanies(ArrayList<PriorityCompany> priorityCompanies) {
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

    public CompanyType getCompanyType() {
        return companyType;
    }

    public void setCompanyType(CompanyType companyType) {
        this.companyType = companyType;
    }
}
