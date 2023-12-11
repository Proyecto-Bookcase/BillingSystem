package Dtos;

import Entity.PackedType;
import Entity.ProductType;

import java.sql.Timestamp;

public class CargoDto {
    private int id;
    private String name;
    private boolean refrigeration;
    private Timestamp expirationDate;
    private float packedUnitWeight;
    private int packParts;
    private float weight;
    //product type
    private ProductType productType;

    //PACKED TYPE
    private PackedType packedType;

    private int clientId;
    private Timestamp arrivalDate;
    private Timestamp departureDate;
    private Timestamp actualDepartureDate;
    private float loadingCost;
    private float unloadingCost;
    private int compartment;
    private int floor;
    private int shelf;
    private int warehouseNumber;

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

    public boolean isRefrigeration() {
        return refrigeration;
    }

    public void setRefrigeration(boolean refrigeration) {
        this.refrigeration = refrigeration;
    }

    public Timestamp getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Timestamp expirationDate) {
        this.expirationDate = expirationDate;
    }

    public float getPackedUnitWeight() {
        return packedUnitWeight;
    }

    public void setPackedUnitWeight(float packedUnitWeight) {
        this.packedUnitWeight = packedUnitWeight;
    }

    public int getPackParts() {
        return packParts;
    }

    public void setPackParts(int packParts) {
        this.packParts = packParts;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public PackedType getPackedType() {
        return packedType;
    }

    public void setPackedType(PackedType packedType) {
        this.packedType = packedType;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public Timestamp getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Timestamp arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public Timestamp getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Timestamp departureDate) {
        this.departureDate = departureDate;
    }

    public Timestamp getActualDepartureDate() {
        return actualDepartureDate;
    }

    public void setActualDepartureDate(Timestamp actualDepartureDate) {
        this.actualDepartureDate = actualDepartureDate;
    }

    public float getLoadingCost() {
        return loadingCost;
    }

    public void setLoadingCost(float loadingCost) {
        this.loadingCost = loadingCost;
    }

    public float getUnloadingCost() {
        return unloadingCost;
    }

    public void setUnloadingCost(float unloadingCost) {
        this.unloadingCost = unloadingCost;
    }

    public int getCompartment() {
        return compartment;
    }

    public void setCompartment(int compartment) {
        this.compartment = compartment;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public int getShelf() {
        return shelf;
    }

    public void setShelf(int shelf) {
        this.shelf = shelf;
    }

    public int getWarehouseNumber() {
        return warehouseNumber;
    }

    public void setWarehouseNumber(int warehouseNumber) {
        this.warehouseNumber = warehouseNumber;
    }

    @Override
    public String toString() {
        return "CargoDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", refrigeration=" + refrigeration +
                ", expirationDate=" + expirationDate +
                ", packedUnitWeight=" + packedUnitWeight +
                ", packParts=" + packParts +
                ", weight=" + weight +
                ", productType=" + productType +
                ", packedType=" + packedType +
                ", clientId=" + clientId +
                ", arrivalDate=" + arrivalDate +
                ", departureDate=" + departureDate +
                ", actualDepartureDate=" + actualDepartureDate +
                ", loadingCost=" + loadingCost +
                ", unloadingCost=" + unloadingCost +
                ", compartment=" + compartment +
                ", floor=" + floor +
                ", shelf=" + shelf +
                ", warehouseNumber=" + warehouseNumber +
                '}';
    }
}
