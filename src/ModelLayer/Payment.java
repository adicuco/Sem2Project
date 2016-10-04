package ModelLayer;

import java.sql.Timestamp;

public class Payment {

	private Customer customer;
	private int paymentId;
	private double amount;
	private Timestamp date;
	private Membership membership;

	public Payment(Customer customer, Timestamp date, Membership membership) {
		this.customer = customer;
		amount = membership.getPrice();
		this.date = date;
		this.membership = membership;
	}
	
	public Payment() {
	}

	/**
	 * @return the customer
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * @return the paymentId
	 */
	public int getPaymentId() {
		return paymentId;
	}

	/**
	 * @return the amount
	 */
	public double getAmount() {
		return amount;
	}

	/**
	 * @return the date
	 */
	public Timestamp getDate() {
		return date;
	}

	/**
	 * @return the membership
	 */
	public Membership getMembership() {
		return membership;
	}

	/**
	 * @param customer
	 *            the customer to set
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	/**
	 * @param paymentId
	 *            the paymentId to set
	 */
	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}

	/**
	 * @param amount
	 *            the amount to set
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(Timestamp date) {
		this.date = date;
	}

	/**
	 * @param membership
	 *            the membership to set
	 */
	public void setMembership(Membership membership) {
		this.membership = membership;
	}

}
