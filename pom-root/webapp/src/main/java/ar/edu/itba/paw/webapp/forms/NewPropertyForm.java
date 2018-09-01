package ar.edu.itba.paw.webapp.forms;

import ar.edu.itba.paw.models.OperationType;
import ar.edu.itba.paw.models.Property;
import ar.edu.itba.paw.models.PropertyType;

import javax.validation.constraints.*;


public class NewPropertyForm {
    
    //TODO: IMAGES

    @Size(min = 1, max = 30)
    @Pattern(regexp = "[a-zA-Z]+")
    @NotNull
    private String street;
    
	@Min(1)
	@Max(999999)
    @NotNull
    private Integer number;

	@Min(1)
	@Max(200)
	@NotNull
    private Integer floor;

    @Size(min = 1, max = 5)
    @Pattern(regexp = "[a-zA-Z0-9]+")
	@NotNull
    private String apartment;

	@Size(min = 1, max = 30)
	@Pattern(regexp = "[a-zA-Z0-9]+")
	@NotNull
    private String neighborhood;

	@NotNull
    private OperationType operationType;

	@NotNull
    private PropertyType type;

	@Min(0)
    @NotNull
    private Long price;

	@Min(1)
	@Max(9999999)
	@NotNull
    private Integer coveredArea;    //In quarter meters

	@Min(1)
	@Max(9999999)
	@NotNull
    private Integer totalArea;    //In quarter meters

	@Min(1)
	@Max(99)
	@NotNull
    private Integer rooms;

	@Min(1)
	@Max(99)
	@NotNull
    private Integer baths;

	@NotNull
    private Boolean garage;

	@Min(1)
	@NotNull
    private Integer taxPrice;

	@Size(min = 1, max = 30)
	@Pattern(regexp = "[a-zA-Z0-9]+")
	@NotNull
	private String adMessage;

	@Size(min = 1, max = 150)
	@Pattern(regexp = "[a-zA-Z0-9]+")
	@NotNull
	private String adDescription;

	@NotNull
	private Boolean inmediateDelivery;

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

	public String getNeighborhood() {
		return neighborhood;
	}

	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}

	public OperationType getOperationType() {
		return operationType;
	}

	public void setOperationType(OperationType operationType) {
		this.operationType = operationType;
	}

	public PropertyType getType() {
		return type;
	}

	public void setType(PropertyType type) {
		this.type = type;
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

	public Boolean getInmediateDelivery() {
		return inmediateDelivery;
	}

	public void setInmediateDelivery(Boolean inmediateDelivery) {
		this.inmediateDelivery = inmediateDelivery;
	}
}