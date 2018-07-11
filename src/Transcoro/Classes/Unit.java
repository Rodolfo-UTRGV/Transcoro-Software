package Transcoro.Classes;

import java.sql.Date;

public class Unit {

    /**
     * The unit id.
     */
    private int id;

    /**
     * The unit brand.
     */
    private String brand;

    /**
     * The unit model.
     */
    private int model;

    /**
     * The unit plates.
     */
    private String plates;

    /**
     * The unit date of purchase.
     */
    private Date purchaseDate;

    /**
     * The unit vehicle identification number.
     */
    private String vin;

    /**
     * @description
     * Constructor
     *
     * @param id
     * @param brand
     * @param model
     * @param plates
     * @param purchaseDate
     * @param vin
     */
    public Unit(int id, String brand, int model, String plates, Date purchaseDate, String vin) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.plates = plates;
        this.purchaseDate = purchaseDate;
        this.vin = vin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getModel() {
        return model;
    }

    public void setModel(int model) {
        this.model = model;
    }

    public String getVIN() {
        return vin;
    }

    public void setVIN(String vin) {
        this.vin = vin;
    }

    public String getPlates() {
        return plates;
    }

    public void setPlates(String plates) {
        this.plates = plates;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }
}
