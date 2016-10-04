package ModelLayer;

import java.sql.Timestamp;
import java.util.ArrayList;

public class Event {

	private int eventId;
	private String name;
	private String description;
	private int maxParticipants;
	private int minParticipants;
	private Timestamp regStartDate;
	private Timestamp regEndDate;
	private Timestamp startDate;
	private Timestamp endDate;
	private int participantsNr;
	private Status status;
	private double price;
	private Employee employee;
	private ArrayList<Customer> participants;
	private Active active;

	public Event(String name, String description, int maxParticipants, int minParticipants, Timestamp regStartDate,
			Timestamp regEndDate, Timestamp startDate, Timestamp endDate, double price, Employee employee) {
		this.name = name;
		this.description = description;
		this.maxParticipants = maxParticipants;
		this.minParticipants = minParticipants;
		this.regStartDate = regStartDate;
		this.regEndDate = regEndDate;
		this.startDate = startDate;
		this.endDate = endDate;
		this.participantsNr = 0;
		this.price = price;
		this.employee = employee;
		status = Status.AVAILABLE;
		active = Active.ACTIVE;
	}

	public Event() {
	}

	/**
	 * @return the eventId
	 */
	public int getEventId() {
		return eventId;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @return the maxParticipants
	 */
	public int getMaxParticipants() {
		return maxParticipants;
	}

	/**
	 * @return the minParticipants
	 */
	public int getMinParticipants() {
		return minParticipants;
	}

	/**
	 * @return the regStartDate
	 */
	public Timestamp getRegStartDate() {
		return regStartDate;
	}

	/**
	 * @return the regEndDate
	 */
	public Timestamp getRegEndDate() {
		return regEndDate;
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
	 * @return the participantsNr
	 */
	public int getParticipantsNr() {
		return participantsNr;
	}

	/**
	 * @return the status
	 */
	public Status getStatus() {
		return status;
	}

	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @return the employee
	 */
	public Employee getEmployee() {
		return employee;
	}

	/**
	 * @return the participants
	 */
	public ArrayList<Customer> getParticipants() {
		return participants;
	}

	/**
	 * @return the active
	 */
	public Active getActive() {
		return active;
	}

	/**
	 * @param eventId
	 *            the eventId to set
	 */
	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @param maxParticipants
	 *            the maxParticipants to set
	 */
	public void setMaxParticipants(int maxParticipants) {
		this.maxParticipants = maxParticipants;
	}

	/**
	 * @param minParticipants
	 *            the minParticipants to set
	 */
	public void setMinParticipants(int minParticipants) {
		this.minParticipants = minParticipants;
	}

	/**
	 * @param regStartDate
	 *            the regStartDate to set
	 */
	public void setRegStartDate(Timestamp regStartDate) {
		this.regStartDate = regStartDate;
	}

	/**
	 * @param regEndDate
	 *            the regEndDate to set
	 */
	public void setRegEndDate(Timestamp regEndDate) {
		this.regEndDate = regEndDate;
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
	 * @param participantsNr
	 *            the participantsNr to set
	 */
	public void setParticipantsNr(int participantsNr) {
		this.participantsNr = participantsNr;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(Status status) {
		this.status = status;
	}

	/**
	 * @param price
	 *            the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * @param employee
	 *            the employee to set
	 */
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	/**
	 * @param participants
	 *            the participants to set
	 */
	public void setParticipants(ArrayList<Customer> participants) {
		this.participants = participants;
	}

	/**
	 * @param active
	 *            the active to set
	 */
	public void setActive(Active active) {
		this.active = active;
	}

}
