package ar.edu.itba.paw.models;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Property {
	    
    private Long id;
    private String street;
    private int floor;
    private String apartment;
    private String neighborhood;
    private OperationType operationType;
    private PropertyType type;
    private User publisherUser;
    private long price;
    private int coveredArea;    //In quarter meters
    private int totalArea;    //In quarter meters
    private int rooms;
    private int baths;
    private Boolean garage;
    private int taxPrice;
    private List<String> images;
    private String adMessage;
    private String adDescription;
    private int adDate;
    private Boolean inmediateDelivery;

    public Property(Long id, String street, int floor, String apartment, String neighborhood, OperationType operationType, PropertyType type, User publisherUser, long price, int coveredArea, int totalArea, int rooms, int baths, Boolean garage, int taxPrice, String adMessage, String adDescription, int adDate, Boolean inmediateDelivery) {
        this.id = id;
        this.street = street;
        this.floor = floor;
        this.apartment = apartment;
        this.neighborhood = neighborhood;
        this.operationType = operationType;
        this.type = type;
        this.publisherUser = publisherUser;
        this.price = price;
        this.coveredArea = coveredArea;
        this.totalArea = totalArea;
        this.rooms = rooms;
        this.baths = baths;
        this.garage = garage;
        this.taxPrice = taxPrice;
        this.adMessage = adMessage;
        this.adDescription = adDescription;
        this.adDate = adDate;
        this.inmediateDelivery = inmediateDelivery;
        this.images = new ArrayList<String>();
    }

    public Property(Long id, String street, int floor, String apartment,
                    PropertyType type, OperationType operationType, User userId, long price,
                    int coveredArea, int totalArea, int rooms, int baths,
                    Boolean garage, int taxPrice) {
        this.id = id;
        this.street = street;
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

    public Property(String street, int floor, String apartment, String neighborhood,
                    OperationType operationType, PropertyType type, User publisherUser, long price,
                    int coveredArea, int totalArea, int rooms, int baths, Boolean garage,
                    int taxPrice, String adMessage, String adDescription, int adDate, Boolean inmediateDelivery) {
        this.street = street;
        this.floor = floor;
        this.apartment = apartment;
        this.neighborhood = neighborhood;
        this.operationType = operationType;
        this.type = type;
        this.publisherUser = publisherUser;
        this.price = price;
        this.coveredArea = coveredArea;
        this.totalArea = totalArea;
        this.rooms = rooms;
        this.baths = baths;
        this.garage = garage;
        this.taxPrice = taxPrice;
        this.adMessage = adMessage;
        this.adDescription = adDescription;
        this.adDate = adDate;
        this.images = new LinkedList<String>();
        this.inmediateDelivery = inmediateDelivery;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getAdMessage() {
        return adMessage;
    }

    public void setAdMessage(String adMessage) {
        this.adMessage = adMessage;
    }

    public String getAdDescription() {
        return adDescription;
    }

    public void setAdDescription(String adDescription) {
        this.adDescription = adDescription;
    }

    public int getAdDate() {
        return adDate;
    }

    public void setAdDate(int adDate) {
        this.adDate = adDate;
    }

    public Boolean getInmediateDelivery() {
        return inmediateDelivery;
    }

    public void setInmediateDelivery(Boolean inmediateDelivery) {
        this.inmediateDelivery = inmediateDelivery;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public void setOperationType(OperationType operationType) {
        this.operationType = operationType;
    }

    public void setPrice(long price) {
        this.price = price;
    }
    
    public void setPublisherUser(User u) {
    	this.publisherUser = u;
    }

    public void setCoveredArea(int coveredArea) {
        this.coveredArea = coveredArea;
    }

    public void setTotalArea(int totalArea) {
        this.totalArea = totalArea;
    }

    public void setRooms(int rooms) {
        this.rooms = rooms;
    }

    public void setBaths(int baths) {
        this.baths = baths;
    }

    public void setGarage(Boolean garage) {
        this.garage = garage;
    }

    public void setTaxPrice(int taxPrice) {
        this.taxPrice = taxPrice;
    }
    
    public void addImage(String image) {
        this.images.add(image);
    }

    public Long getId() {

        return id;
    }

    public String getStreet() {
        return street;
    }

    public int getFloor() {
        return floor;
    }

    public String getApartment() {
        return apartment;
    }

    public User getPublisherUser() {
        return publisherUser;
    }

    public long getPrice() {
        return price;
    }

    public int getCoveredArea() {
        return coveredArea;
    }

    public int getTotalArea() {
        return totalArea;
    }

    public int getRooms() {
        return rooms;
    }

    public int getBaths() {
        return baths;
    }

    public Boolean getGarage() {
        return garage;
    }

    public int getTaxPrice() {
        return taxPrice;
    }

    public PropertyType getType() {
        return type;
    }

    public List<String> getImages() {
        return images;
    }
    
    public static class PropertyBuilder{
    	
    	private Long nestedId;
        private String nestedStreet;
        private int nestedNumber;
        private int nestedFloor;
        private String nestedApartment;
        private String nestedNeighborhood;
        private PropertyType nestedType;
        private OperationType nestedOperationType;
        private User nestedPublisherUser;
        private long nestedPrice;
        private int nestedCoveredArea;    //In quarter meters
        private int nestedTotalArea;    //In quarter meters
        private int nestedRooms;
        private int nestedBaths;
        private Boolean nestedGarage;
        private int nestedTaxPrice;
        private String nestedAdMessage;
        private String nestedAdDescription;
        private int nestedAdDate;
        private Boolean nestedInmediateDelivery;
        private List<Property> nestedWishes;
        private List<String> nestedImages;
        
        public PropertyBuilder(final User newUser, final String newStreet, final long newPrice, final OperationType newOperationType) {
        	this.nestedPublisherUser = newUser;
        	this.nestedStreet = newStreet;
            this.nestedPrice = newPrice;
            this.nestedOperationType = newOperationType;
        }
        
        public PropertyBuilder id(Long newId){
           this.nestedId = newId;
           return this;
        }
        
        public PropertyBuilder floor(int newFloor){
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
        
        public PropertyBuilder coveredArea(int newCA){
            this.nestedCoveredArea = newCA;
            return this;
         }
        
        public PropertyBuilder totalArea(int newTA){
            this.nestedTotalArea = newTA;
            return this;
         }
        
        public PropertyBuilder rooms(int newRooms){
            this.nestedRooms = newRooms;
            return this;
         }
        
        public PropertyBuilder baths(int newBaths){
            this.nestedBaths = newBaths;
            return this;
         }
        
        public PropertyBuilder garage(Boolean newGarage){
            this.nestedGarage = newGarage;
            return this;
         }
        
        public PropertyBuilder taxPrice(int newTaxPrice){
            this.nestedTaxPrice = newTaxPrice;
            return this;
         }
        
        public PropertyBuilder wishes(List<Property> newWishes){
            this.nestedWishes = newWishes;
            return this;
         }

        public PropertyBuilder neighborhood(String newNeighborhood){
            this.nestedNeighborhood = newNeighborhood;
            return this;
        }

        public PropertyBuilder adMessage(String newAdMessage){
            this.nestedAdMessage = newAdMessage;
            return this;
        }

        public PropertyBuilder adDescription(String newAdDescription){
            this.nestedAdDescription = newAdDescription;
            return this;
        }

        public PropertyBuilder adDate(int newAdDate){
            this.nestedAdDate = newAdDate;
            return this;
        }

        public PropertyBuilder immediateDelivery(Boolean newInmediateDelivery){
            this.nestedInmediateDelivery = newInmediateDelivery;
            return this;
        }

        public Property build(){
        	
           return new Property(
              nestedId, nestedStreet,
              nestedFloor, nestedApartment, nestedNeighborhood,
                   nestedOperationType, nestedType, nestedPublisherUser, nestedPrice,
              nestedCoveredArea, nestedTotalArea, nestedRooms,
              nestedBaths, nestedGarage, nestedTaxPrice, nestedAdMessage, nestedAdDescription, nestedAdDate,
                   nestedInmediateDelivery);
        }
    	
    }

	@Override
	public String toString() {
		return "Property [id=" + id + ", street=" + street + ", floor=" + floor + ", apartment="
				+ apartment + ", neighborhood=" + neighborhood + ", operationType=" + operationType + ", type=" + type
				+ ", publisherUser=" + publisherUser + ", price=" + price + ", coveredArea=" + coveredArea
				+ ", totalArea=" + totalArea + ", rooms=" + rooms + ", baths=" + baths + ", garage=" + garage
				+ ", taxPrice=" + taxPrice + ", images=" + images + ", adMessage=" + adMessage + ", adDescription="
				+ adDescription + ", adDate=" + adDate + ", inmediateDelivery=" + inmediateDelivery + "]";
	}
    
    

}
