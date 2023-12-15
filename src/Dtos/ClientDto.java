package Dtos;

import java.sql.Date;

public class ClientDto {
    private String name;
    private String type;
    private String country;
    private String phoneNumber;
    private String fax;
    private String email;
    private char attentionType;
    private int antique;
    private int id;
    private Date preferredRate;

    // Constructor
    public ClientDto(String name, String type, String country, String phoneNumber,
                     String fax, String email, char attentionType, int antique,
                     int id, Date preferredRate) {
        this.name = name;
        this.type = type;
        this.country = country;
        this.phoneNumber = phoneNumber;
        this.fax = fax;
        this.email = email;
        this.attentionType = attentionType;
        this.antique = antique;
        this.id = id;
        this.preferredRate = preferredRate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public char getAttentionType() {
        return attentionType;
    }

    public void setAttentionType(char attentionType) {
        this.attentionType = attentionType;
    }

    public int getAntique() {
        return antique;
    }

    public void setAntique(int antique) {
        this.antique = antique;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getPreferredRate() {
        return preferredRate;
    }

    public void setPreferredRate(Date preferredRate) {
        this.preferredRate = preferredRate;
    }
}
