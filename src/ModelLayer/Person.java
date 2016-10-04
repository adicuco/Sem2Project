package ModelLayer;

public abstract class Person {

	private String fName;
	private String lName;
	private String address;
	private String city;
	private String zip;
	private String phone;
	private String email;
	private String cpr;
	private String password;
	private Active active;
	private AccessLvl accessLvl;
	private boolean loggedIn;

	/**
	 * @return the accessLvl
	 */
	public AccessLvl getAccessLvl() {
		return accessLvl;
	}

	/**
	 * @param accessLvl
	 *            the accessLvl to set
	 */
	public void setAccessLvl(AccessLvl accessLvl) {
		this.accessLvl = accessLvl;
	}

	public enum AccessLvl {
		CUSTOMER, EMPLOYEE, MANAGER
	}

	public Person() {
	}

	public Person(String fName, String lName, String address, String city, String zip, String phone, String email,
			String cpr) {
		this.fName = fName;
		this.lName = lName;
		this.address = address;
		this.city = city;
		this.zip = zip;
		this.phone = phone;
		this.email = email;
		this.cpr = cpr;
		password = cpr.substring(7);
		active = Active.ACTIVE;
	}

	/**
	 * @return the fName
	 */
	public String getfName() {
		return fName;
	}

	/**
	 * @return the lName
	 */
	public String getlName() {
		return lName;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @return the zip
	 */
	public String getZip() {
		return zip;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @return the cpr
	 */
	public String getCpr() {
		return cpr;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @return the active
	 */
	public Active getActive() {
		return active;
	}

	/**
	 * @param fName
	 *            the fName to set
	 */
	public void setfName(String fName) {
		this.fName = fName;
	}

	/**
	 * @param lName
	 *            the lName to set
	 */
	public void setlName(String lName) {
		this.lName = lName;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @param city
	 *            the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @param zip
	 *            the zip to set
	 */
	public void setZip(String zip) {
		this.zip = zip;
	}

	/**
	 * @param phone
	 *            the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @param cpr
	 *            the cpr to set
	 */
	public void setCpr(String cpr) {
		this.cpr = cpr;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @param active
	 *            the active to set
	 */
	public void setActive(Active active) {
		this.active = active;
	}

	/**
	 * @return the loggedIn
	 */
	public boolean isLoggedIn() {
		return loggedIn;
	}

	/**
	 * @param loggedIn the loggedIn to set
	 */
	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

}
