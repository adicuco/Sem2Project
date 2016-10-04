package ModelLayer;

import java.sql.Timestamp;

public class Rent {

	private int rentId;
	private Timestamp dateRentMade;
	private Timestamp startDate;
	private Timestamp endDate;
	private RentType rentType;
	private double tAmount;
	private String notes;
	private Customer customer;
	private Rentable rentable;

	public Rent(Timestamp dateRentMade, Timestamp startDate, Timestamp endDate, RentType rentType, double tAmount, String notes,
			Customer customer, Rentable rentable) {
		this.dateRentMade = dateRentMade;
		this.startDate = startDate;
		this.endDate = endDate;
		this.rentType = rentType;
		this.tAmount = tAmount;
		this.notes = notes;
		this.customer = customer;
		this.rentable = rentable;
	}
	
	public Rent() {
	}

	/**
	 * @return the rentId
	 */
	public int getRentId() {
		return rentId;
	}

	/**
	 * @return the dateRentMade
	 */
	public Timestamp getDateRentMade() {
		return dateRentMade;
	}

	/**
	 * @return the startDate
	 */
	public Timestamp getStartDate() {
		return startDate;
	}

	/**
	 * @return the endDate
	 */
	public Timestamp getEndDate() {
		return endDate;
	}

	/**
	 * @return the rentType
	 */
	public RentType getRentType() {
		return rentType;
	}

	/**
	 * @return the tAmount
	 */
	public double gettAmount() {
		return tAmount;
	}

	/**
	 * @return the notes
	 */
	public String getNotes() {
		return notes;
	}

	/**
	 * @return the customer
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * @return the rentable
	 */
	public Rentable getRentable() {
		return rentable;
	}

	/**
	 * @param rentId
	 *            the rentId to set
	 */
	public void setRentId(int rentId) {
		this.rentId = rentId;
	}

	/**
	 * @param dateRentMade
	 *            the dateRentMade to set
	 */
	public void setDateRentMade(Timestamp dateRentMade) {
		this.dateRentMade = dateRentMade;
	}

	/**
	 * @param startDate
	 *            the startDate to set
	 */
	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
	}

	/**
	 * @param endDate
	 *            the endDate to set
	 */
	public void setEndDate(Timestamp endDate) {
		this.endDate = endDate;
	}

	/**
	 * @param rentType
	 *            the rentType to set
	 */
	public void setRentType(RentType rentType) {
		this.rentType = rentType;
	}

	/**
	 * @param tAmount
	 *            the tAmount to set
	 */
	public void settAmount(double tAmount) {
		this.tAmount = tAmount;
	}

	/**
	 * @param notes
	 *            the notes to set
	 */
	public void setNotes(String notes) {
		this.notes = notes;
	}

	/**
	 * @param customer
	 *            the customer to set
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	/**
	 * @param rentable
	 *            the rentable to set
	 */
	public void setRentable(Rentable rentable) {
		this.rentable = rentable;
	}

}
