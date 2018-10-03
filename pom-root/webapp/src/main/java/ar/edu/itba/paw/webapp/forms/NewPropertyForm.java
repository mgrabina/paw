package ar.edu.itba.paw.webapp.forms;

import ar.edu.itba.paw.models.OperationType;
import ar.edu.itba.paw.models.Property;
import ar.edu.itba.paw.models.PropertyType;

import javax.validation.constraints.*;

import org.springframework.web.multipart.MultipartFile;


public class NewPropertyForm {

    @Size(min = 3, max = 999)
    @NotNull
    private String street;

	@Min(0)
	@Max(200)
	@NotNull
	private Integer floor=0;

    @Size(min = 0, max = 10)
	@NotNull
    private String apartment=" ";

	@Size(min = 5, max = 30)
	@NotNull
    private String neighborhood;

	@NotNull
    private OperationType operationType;

	@NotNull
    private PropertyType type;

	@Min(1)
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

	@Size(min = 6, max = 80)
	@NotNull
	private String adMessage;

	@Size(min = 6, max = 300)
	@NotNull
	private String adDescription;

	@NotNull
	private Boolean inmediateDelivery;
	
	private MultipartFile[] images;

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
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
	
	public MultipartFile[] getImages() {
		return images;
	}

	public void setImages(MultipartFile[] images) {
		this.images = images;
	}

	@Override
	public String toString() {
		return "NewPropertyForm [street=" + street + ", floor=" + floor + ", apartment="
				+ apartment + ", neighborhood=" + neighborhood + ", operationType=" + operationType + ", type=" + type
				+ ", price=" + price + ", coveredArea=" + coveredArea + ", totalArea=" + totalArea + ", rooms=" + rooms
				+ ", baths=" + baths + ", garage=" + garage + ", taxPrice=" + taxPrice + ", adMessage=" + adMessage
				+ ", adDescription=" + adDescription + ", inmediateDelivery=" + inmediateDelivery + "]";
	}
	
	
}
