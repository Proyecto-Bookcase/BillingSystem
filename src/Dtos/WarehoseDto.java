package Dtos;

public class WarehoseDto {
    private int number;
    private boolean cooled;

    public WarehoseDto(int number, boolean cooled) {
        this.number = number;
        this.cooled = cooled;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isCooled() {
        return cooled;
    }

    public void setCooled(boolean cooled) {
        this.cooled = cooled;
    }
}
