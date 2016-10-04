package ModelLayer;

public class Employee extends Person {

	private int employeeId;

	public Employee(String fName, String lName, String address, String city, String zip, String phone, String email,
			String cpr) {
		super(fName, lName, address, city, zip, phone, email, cpr);
		this.setAccessLvl(AccessLvl.EMPLOYEE);
		this.setActive(Active.ACTIVE);
		this.setLoggedIn(false);
	}

	public Employee() {
	}

	/**
	 * @return the employeeId
	 */
	public int getEmployeeId() {
		return employeeId;
	}

	/**
	 * @param employeeId
	 *            the employeeId to set
	 */
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

}
