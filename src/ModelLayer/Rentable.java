package ModelLayer;

public abstract class Rentable {

	private Status status;
	private Active active;
	private int id;

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * @return the status
	 */
	public Status getStatus() {
		return status;
	}

	/**
	 * @return the active
	 */
	public Active getActive() {
		return active;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(Status status) {
		this.status = status;
	}

	/**
	 * @param active
	 *            the active to set
	 */
	public void setActive(Active active) {
		this.active = active;
	}

}
