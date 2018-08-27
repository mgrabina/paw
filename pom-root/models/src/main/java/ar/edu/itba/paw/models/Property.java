package ar.edu.itba.paw.models;

public class Property {
    public enum Type{
        house, apartment, duplex, office, land
    };

    private Integer id;
    private String street;
    private Integer number;
    private Integer floor;
    private String apartment;
    private Type type;
    private Long userId;
    private Long price;
    //Images in Imagesxproperty relation
    private Integer coveredArea;    //In quarter meters
    private Integer totalArea;    //In quarter meters
    private Integer rooms;
    private Integer baths;
    private Boolean garage;
    private Integer taxPrice;

    public Property(Integer id, String street, Integer number, Integer floor, String apartment, Type type, Long userId, Long price, Integer coveredArea, Integer totalArea, Integer rooms, Integer baths, Boolean garage, Integer taxPrice) {
        this.id = id;
        this.street = street;
        this.number = number;
        this.floor = floor;
        this.apartment = apartment;
        this.type = type;
        this.userId = userId;
        this.price = price;
        this.coveredArea = coveredArea;
        this.totalArea = totalArea;
        this.rooms = rooms;
        this.baths = baths;
        this.garage = garage;
        this.taxPrice = taxPrice;
    }

    public Property(String street, Integer number, Integer floor, String apartment, Type type, Long userId, Long price, Integer coveredArea, Integer totalArea, Integer rooms, Integer baths, Boolean garage, Integer taxPrice) {
        this.street = street;
        this.number = number;
        this.floor = floor;
        this.apartment = apartment;
        this.type = type;
        this.userId = userId;
        this.price = price;
        this.coveredArea = coveredArea;
        this.totalArea = totalArea;
        this.rooms = rooms;
        this.baths = baths;
        this.garage = garage;
        this.taxPrice = taxPrice;
    }

    public Property(String street, Integer number, Integer floor, String apartment, Long userId, Long price) {
        this.street = street;
        this.number = number;
        this.floor = floor;
        this.apartment = apartment;
        this.userId = userId;
        this.price = price;
    }

    public Property(String street, Integer number, Long userId, Long price) {
        this.street = street;
        this.number = number;
        this.userId = userId;
        this.price = price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public void setCoveredArea(Integer coveredArea) {
        this.coveredArea = coveredArea;
    }

    public void setTotalArea(Integer totalArea) {
        this.totalArea = totalArea;
    }

    public void setRooms(Integer rooms) {
        this.rooms = rooms;
    }

    public void setBaths(Integer baths) {
        this.baths = baths;
    }

    public void setGarage(Boolean garage) {
        this.garage = garage;
    }

    public void setTaxPrice(Integer taxPrice) {
        this.taxPrice = taxPrice;
    }

    public Integer getId() {

        return id;
    }

    public String getStreet() {
        return street;
    }

    public Integer getNumber() {
        return number;
    }

    public Integer getFloor() {
        return floor;
    }

    public String getApartment() {
        return apartment;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getPrice() {
        return price;
    }

    public Integer getCoveredArea() {
        return coveredArea;
    }

    public Integer getTotalArea() {
        return totalArea;
    }

    public Integer getRooms() {
        return rooms;
    }

    public Integer getBaths() {
        return baths;
    }

    public Boolean getGarage() {
        return garage;
    }

    public Integer getTaxPrice() {
        return taxPrice;
    }
}
