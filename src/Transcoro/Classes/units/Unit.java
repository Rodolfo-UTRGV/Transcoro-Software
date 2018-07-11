/**
 *   _____
 * () | ,_   _,         ,   _   _   ,_   _
 *    |/  | / |  /|/|  / \_/   / \_/  | / \_
 *  (/    |/\/|_/ | |_/ \/ \__/\_/    |/\_/
 *
 *  Transcoro Software 2018 || The power of proyects
 **/
package Transcoro.Classes.units;

import java.sql.Date;

public class Unit {

    /**
     * The units id.
     */
    private int id;

    /**
     * The units brand.
     */
    private String brand;

    /**
     * The units model.
     */
    private int model;

    /**
     * The units plates.
     */
    private String plates;

    /**
     * The units date of purchase.
     */
    private Date purchaseDate;

    /**
     * The units vehicle identification number.
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
