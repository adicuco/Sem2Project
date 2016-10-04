package ModelLayer;

import java.sql.Timestamp;

public class Customer extends Person {

	private int customerId;
	private boolean payCheck;
	private Timestamp payDay;
	private Membership membership;
	private double amountToPay;

	public Customer(String fName, String lName, String address, String city, String zip, String phone, String email,
			String cpr) {
		super(fName, lName, address, city, zip, phone, email, cpr);
		amountToPay = 0;
		payCheck = false;
		payDay = null;
		this.setAccessLvl(AccessLvl.CUSTOMER);
		this.setActive(Active.ACTIVE);
		this.setLoggedIn(false);
	}

	public Customer() {
	}

	public Customer(int customerId) {
		this.customerId = customerId;
	}

	/**
	 * @return the customerId
	 */
	public int getCustomerId() {
		return customerId;
	}

	/**
	 * @return the payCheck
	 */
	public boolean isPayCheck() {
		return payCheck;
	}

	/**
	 * @return the payDay
	 */
	public Timestamp getPayDay() {
		return payDay;
	}

	/**
	 * @return the membership
	 */
	public Membership getMembership() {
		return membership;
	}

	/**
	 * @param customerId
	 *            the customerId to set
	 */
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	/**
	 * @param payCheck
	 *            the payCheck to set
	 */
	public void setPayCheck(boolean payCheck) {
		this.payCheck = payCheck;
	}

	/**
	 * @param payDay
	 *            the payDay to set
	 */
	public void setPayDay(Timestamp payDay) {
		this.payDay = payDay;
	}

	/**
	 * @param membership
	 *            the membership to set
	 */
	public void setMembership(Membership membership) {
		this.membership = membership;
	}

	/**
	 * @return the amountToPay
	 */
	public double getAmountToPay() {
		return amountToPay;
	}

	/**
	 * @param amountToPay
	 *            the amountToPay to set
	 */
	public void setAmountToPay(double amountToPay) {
		this.amountToPay = amountToPay;
	}

}
