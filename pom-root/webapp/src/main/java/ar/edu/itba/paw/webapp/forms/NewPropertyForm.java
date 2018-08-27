package ar.edu.itba.paw.webapp.forms;

import ar.edu.itba.paw.models.Property;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


public class NewPropertyForm {
    
    //TODO: IMAGES

    @Size(min = 1, max = 30)
    @Pattern(regexp = "[a-zA-Z]+")
    private String street;
    
    @Size(min = 1, max = 10)
    @Pattern(regexp = "[0-9]+")
    private String number;

    @Size(min = 1, max = 3)
    @Pattern(regexp = "[0-9]+")
    private String floor;

    @Size(min = 1, max = 5)
    @Pattern(regexp = "[a-zA-Z0-9]+")
    private String apartment;
    
    
    private Property.Type type;

    @Size(min = 1, max = 20)
    @Pattern(regexp = "[0-9]+")
    private String userId;
    
    @Size(min = 1, max = 20)
    @Pattern(regexp = "[0-9]+")
    private String price;

    @Size(min = 1, max = 5)
    @Pattern(regexp = "[0-9]+")
    private String coveredArea;    //In quarter meters

    @Size(min = 1, max = 5)
    @Pattern(regexp = "[0-9]+")
    private String totalArea;    //In quarter meters

    @Size(min = 1, max = 3)
    @Pattern(regexp = "[0-9]+")
    private String rooms;

    @Size(min = 1, max = 3)
    @Pattern(regexp = "[0-9]+")
    private String baths;
    
    private String garage;

    @Size(min = 1, max = 15)
    @Pattern(regexp = "[0-9]+")
    private String taxPrice;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getApartment() {
        return apartment;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }

    public Property.Type getType() {
        return type;
    }

    public void setType(Property.Type type) {
        this.type = type;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCoveredArea() {
        return coveredArea;
    }

    public void setCoveredArea(String coveredArea) {
        this.coveredArea = coveredArea;
    }

    public String getTotalArea() {
        return totalArea;
    }

    public void setTotalArea(String totalArea) {
        this.totalArea = totalArea;
    }

    public String getRooms() {
        return rooms;
    }

    public void setRooms(String rooms) {
        this.rooms = rooms;
    }

    public String getBaths() {
        return baths;
    }

    public void setBaths(String baths) {
        this.baths = baths;
    }

    public String getGarage() {
        return garage;
    }

    public void setGarage(String garage) {
        this.garage = garage;
    }

    public String getTaxPrice() {
        return taxPrice;
    }

    public void setTaxPrice(String taxPrice) {
        this.taxPrice = taxPrice;
    }
}
