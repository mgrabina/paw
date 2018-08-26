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
    private Integer number;

    @Size(min = 1, max = 3)
    @Pattern(regexp = "[0-9]+")
    private Integer floor;

    @Size(min = 1, max = 5)
    @Pattern(regexp = "[a-zA-Z0-9]+")
    private String apartment;


    private Property.Type type;

    @Size(min = 1, max = 20)
    @Pattern(regexp = "[0-9]+")
    private Integer userId;

    @Size(min = 1, max = 20)
    @Pattern(regexp = "[0-9]+")
    private Long price;

    @Size(min = 1, max = 5)
    @Pattern(regexp = "[0-9]+")
    private Integer coveredArea;    //In quarter meters

    @Size(min = 1, max = 5)
    @Pattern(regexp = "[0-9]+")
    private Integer totalArea;    //In quarter meters

    @Size(min = 1, max = 3)
    @Pattern(regexp = "[0-9]+")
    private Integer rooms;

    @Size(min = 1, max = 3)
    @Pattern(regexp = "[0-9]+")
    private Integer baths;

    private Boolean garage;

    @Size(min = 1, max = 15)
    @Pattern(regexp = "[0-9]+")
    private Integer taxPrice;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Integer getCoveredArea() {
        return coveredArea;
    }

    public void setCoveredArea(Integer coveredArea) {
        this.coveredArea = coveredArea;
    }

    public Integer getTotalArea() {
        return totalArea;
    }

    public void setTotalArea(Integer totalArea) {
        this.totalArea = totalArea;
    }

    public Integer getRooms() {
        return rooms;
    }

    public void setRooms(Integer rooms) {
        this.rooms = rooms;
    }

    public Integer getBaths() {
        return baths;
    }

    public void setBaths(Integer baths) {
        this.baths = baths;
    }

    public Boolean getGarage() {
        return garage;
    }

    public void setGarage(Boolean garage) {
        this.garage = garage;
    }

    public Integer getTaxPrice() {
        return taxPrice;
    }

    public void setTaxPrice(Integer taxPrice) {
        this.taxPrice = taxPrice;
    }
}
