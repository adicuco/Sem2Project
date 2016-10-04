package ModelLayer;

/**
 * 
 * @author Eugen Bobolea, Dalibor Branny, Adrian Cucolas, Catalin Rat
 *
 */

public class Equipment extends Rentable {

	private int eqId;
	private EquipmentType eqType;
	private String name;
	private String description;
	private int quantity;
	private double price;

public Equipment (EquipmentType eqType, String name, String description, int quantity, double price){
	this.eqType = eqType;
	this.name = name;
	this.description = description;
	this.quantity = quantity;
	this.price = price;
	this.setActive(Active.ACTIVE);
	this.setStatus(Status.AVAILABLE);
}

public Equipment(){
	
}

public int getEqId() {
	return eqId;
}

public void setEqId(int eqId) {
	this.eqId = eqId;
	this.setId(eqId);
}

public EquipmentType getEqType() {
	return eqType;
}

public void setEqType(EquipmentType eqType) {
	this.eqType = eqType;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getDescription() {
	return description;
}

public void setDescription(String description) {
	this.description = description;
}

public int getQuantity() {
	return quantity;
}

public void setQuantity(int quantity) {
	this.quantity = quantity;
}

public double getPrice() {
	return price;
}

public void setPrice(double price) {
	this.price = price;
}
	
	
	
}
