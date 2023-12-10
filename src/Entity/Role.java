package Entity;

public class Role {
    private int id;
    private String description;

    public Role(int id, String description) {
        this.id = id;
        this.description = description;
    }
    public Role() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
