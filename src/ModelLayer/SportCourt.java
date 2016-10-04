package ModelLayer;

/**
 * 
 * @author Eugen Bobolea, Dalibor Branny, Adrian Cucolas, Catalin Rat
 *
 */
public class SportCourt extends Rentable {

	int courtId;
	EquipmentType eqType;
	String sport;
	double price;

	/**
	 * @param courtID
	 * @param eqType
	 * @param sport
	 * @param price
	 */

	public SportCourt(EquipmentType eqType, String sport, double price) {
		this.eqType = eqType;
		this.sport = sport;
		this.price = price;
		this.setActive(Active.ACTIVE);
		this.setStatus(Status.AVAILABLE);

	}

	public SportCourt() {
	}

	/**
	 * @return the courtId
	 */

	public int getCourtId() {
		return courtId;
	}

	/**
	 * @return the eqType
	 */

	public EquipmentType getEqType() {
		return eqType;
	}

	/**
	 * @return the sport
	 */

	public String getSport() {
		return sport;
	}

	/**
	 * @return the price
	 */

	public double getPrice() {
		return price;
	}

	/**
	 * @param courtId
	 *            the courtId to set
	 */

	public void setCourtId(int courtId) {
		this.setId(courtId);
		this.courtId = courtId;
	}

	/**
	 * @param eqType
	 *            the eqType to set
	 */

	public void setEqType(EquipmentType eqType) {
		this.eqType = eqType;
	}

	/**
	 * @param sport
	 *            the sport to set
	 */

	public void setSport(String sport) {
		this.sport = sport;
	}

	/**
	 * @param price
	 *            the price to set
	 */

	public void setPrice(double price) {
		this.price = price;
	}
	@Override
	public String toString(){
		return this.sport;
	}

}
