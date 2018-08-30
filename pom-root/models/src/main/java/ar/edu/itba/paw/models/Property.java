package ar.edu.itba.paw.models;

import java.util.List;

public class Property {
	    
    private Integer id;
    private String street;
    private Integer number;
    private Integer floor;
    private String apartment;
    private OperationType operationType;
    private PropertyType type;
    private User publisherUser;
    private Long price;
    private Integer coveredArea;    //In quarter meters
    private Integer totalArea;    //In quarter meters
    private Integer rooms;
    private Integer baths;
    private Boolean garage;
    private Integer taxPrice;
    private List<Property> wishes;
    private List<String> images;

    public Property(Integer id, String street, Integer number, Integer floor, String apartment, PropertyType type, OperationType operationType, User userId, Long price, Integer coveredArea, Integer totalArea, Integer rooms, Integer baths, Boolean garage, Integer taxPrice) {
        this.id = id;
        this.street = street;
        this.number = number;
        this.floor = floor;
        this.apartment = apartment;
        this.type = type;
        this.operationType = operationType;
        this.publisherUser = userId;
        this.price = price;
        this.coveredArea = coveredArea;
        this.totalArea = totalArea;
        this.rooms = rooms;
        this.baths = baths;
        this.garage = garage;
        this.taxPrice = taxPrice;
    }

    public void setPrice(Long price) {
        this.price = price;
    }
    
    public void setPublisherUser(User u) {
    	this.publisherUser = u;
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
    
    public void addImage(String image) {
        this.images.add(image);
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

    public User getPublisherUser() {
        return publisherUser;
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

    public PropertyType getType() {
        return type;
    }

    public List<Property> getWishes() {
        return wishes;
    }
    
    public List<String> getImages() {
        return images;
    }
    
    public static class PropertyBuilder{
    	
    	private Integer nestedId;
        private String nestedStreet;
        private Integer nestedNumber;
        private Integer nestedFloor;
        private String nestedApartment;
        private PropertyType nestedType;
        private OperationType nestedOperationType;
        private User nestedPublisherUser;
        private Long nestedPrice;
        private Integer nestedCoveredArea;    //In quarter meters
        private Integer nestedTotalArea;    //In quarter meters
        private Integer nestedRooms;
        private Integer nestedBaths;
        private Boolean nestedGarage;
        private Integer nestedTaxPrice;
        private List<Property> nestedWishes;
        private List<String> nestedImages;
        
        public PropertyBuilder(final User newUser, final String newStreet, final Integer newNumber, final Long newPrice, final OperationType newOperationType) {
        	this.nestedPublisherUser = newUser;
        	this.nestedStreet = newStreet;
            this.nestedNumber = newNumber;
            this.nestedPrice = newPrice;
            this.nestedOperationType = newOperationType;
        }
        
        public PropertyBuilder id(Integer newId){
           this.nestedId = newId;
           return this;
        }
        
        public PropertyBuilder floor(Integer newFloor){
            this.nestedFloor = newFloor;
            return this;
         }
        
        public PropertyBuilder apartment(String newApartment){
            this.nestedApartment = newApartment;
            return this;
         }
        
        public PropertyBuilder type(PropertyType newType){
            this.nestedType = newType;
            return this;
         }
        
        public PropertyBuilder coveredArea(Integer newCA){
            this.nestedCoveredArea = newCA;
            return this;
         }
        
        public PropertyBuilder totalArea(Integer newTA){
            this.nestedTotalArea = newTA;
            return this;
         }
        
        public PropertyBuilder rooms(Integer newRooms){
            this.nestedRooms = newRooms;
            return this;
         }
        
        public PropertyBuilder baths(Integer newBaths){
            this.nestedBaths = newBaths;
            return this;
         }
        
        public PropertyBuilder garage(Boolean newGarage){
            this.nestedGarage = newGarage;
            return this;
         }
        
        public PropertyBuilder taxPrice(Integer newTaxPrice){
            this.nestedTaxPrice = newTaxPrice;
            return this;
         }
        
        public PropertyBuilder wishes(List<Property> newWishes){
            this.nestedWishes = newWishes;
            return this;
         }
        
        public Property build(){
        	
           return new Property(
              nestedId, nestedStreet, nestedNumber,
              nestedFloor, nestedApartment,
              nestedType, nestedOperationType, nestedPublisherUser, nestedPrice,
              nestedCoveredArea, nestedTotalArea, nestedRooms,
              nestedBaths, nestedGarage, nestedTaxPrice);
        }
    	
    }

}
