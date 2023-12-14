package Dtos;

public class LocationDto {
    private int compartment;
    private int floor;
    private int shelf;
    private boolean maintenance;
    private int warehouseNumber;
    private int cargoId;

    public LocationDto(int compartment, int floor, int shelf, boolean maintenance, int warehouseNumber, int cargoId) {
        this.compartment = compartment;
        this.floor = floor;
        this.shelf = shelf;
        this.maintenance = maintenance;
        this.warehouseNumber = warehouseNumber;
        this.cargoId = cargoId;
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

    public boolean isMaintenance() {
        return maintenance;
    }

    public void setMaintenance(boolean maintenance) {
        this.maintenance = maintenance;
    }

    public int getWarehouseNumber() {
        return warehouseNumber;
    }

    public void setWarehouseNumber(int warehouseNumber) {
        this.warehouseNumber = warehouseNumber;
    }

    public int getCargoId() {
        return cargoId;
    }

    public void setCargoId(int cargoId) {
        this.cargoId = cargoId;
    }

    @Override
    public String toString() {
        return "Location{" +
                "compartment=" + compartment +
                ", floor=" + floor +
                ", shelf=" + shelf +
                ", maintenance=" + maintenance +
                ", warehouseNumber=" + warehouseNumber +
                ", cargoId=" + cargoId +
                '}';
    }
}

