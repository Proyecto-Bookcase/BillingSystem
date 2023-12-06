package Dtos;

public class EnterpirseDto {

    private String name;
    private int id;
    private String postalDir;
    private String hrDepBossName;
    private String contDepRespName;
    private String sindCoreSecName;
    private String logo;
    private float refriCharge;
    private float billAmount;
    private String generalBossName;
    private float discount;
    private String phoneNumber;
    private String email;
    private float tariffPerHours;
    private float tariffPerWeight;

    // Constructor
    public EnterpirseDto(String name, int id, String postalDir, String hrDepBossName,
                         String contDepRespName, String sindCoreSecName, String logo,
                         float refriCharge, float billAmount, String generalBossName,
                         float discount, String phoneNumber, String email,
                         float tariffPerHours, float tariffPerWeight) {
        this.name = name;
        this.id = id;
        this.postalDir = postalDir;
        this.hrDepBossName = hrDepBossName;
        this.contDepRespName = contDepRespName;
        this.sindCoreSecName = sindCoreSecName;
        this.logo = logo;
        this.refriCharge = refriCharge;
        this.billAmount = billAmount;
        this.generalBossName = generalBossName;
        this.discount = discount;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.tariffPerHours = tariffPerHours;
        this.tariffPerWeight = tariffPerWeight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPostalDir() {
        return postalDir;
    }

    public void setPostalDir(String postalDir) {
        this.postalDir = postalDir;
    }

    public String getHrDepBossName() {
        return hrDepBossName;
    }

    public void setHrDepBossName(String hrDepBossName) {
        this.hrDepBossName = hrDepBossName;
    }

    public String getContDepRespName() {
        return contDepRespName;
    }

    public void setContDepRespName(String contDepRespName) {
        this.contDepRespName = contDepRespName;
    }

    public String getSindCoreSecName() {
        return sindCoreSecName;
    }

    public void setSindCoreSecName(String sindCoreSecName) {
        this.sindCoreSecName = sindCoreSecName;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public float getRefriCharge() {
        return refriCharge;
    }

    public void setRefriCharge(float refriCharge) {
        this.refriCharge = refriCharge;
    }

    public float getBillAmount() {
        return billAmount;
    }

    public void setBillAmount(float billAmount) {
        this.billAmount = billAmount;
    }

    public String getGeneralBossName() {
        return generalBossName;
    }

    public void setGeneralBossName(String generalBossName) {
        this.generalBossName = generalBossName;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public float getTariffPerHours() {
        return tariffPerHours;
    }

    public void setTariffPerHours(float tariffPerHours) {
        this.tariffPerHours = tariffPerHours;
    }

    public float getTariffPerWeight() {
        return tariffPerWeight;
    }

    public void setTariffPerWeight(float tariffPerWeight) {
        this.tariffPerWeight = tariffPerWeight;
    }
}
