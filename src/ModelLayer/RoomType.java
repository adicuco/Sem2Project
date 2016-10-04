package ModelLayer;

public class RoomType {

	private String roomTypeName;
	private double roomPrice;

	public RoomType(String roomTypeName, double roomPrice) {
		this.roomTypeName = roomTypeName;
		this.roomPrice = roomPrice;
	}
	
	public RoomType() {
	}

	/**
	 * @return the roomPrice
	 */
	public double getRoomPrice() {
		return roomPrice;
	}

	/**
	 * @param roomPrice
	 *            the roomPrice to set
	 */
	public void setRoomPrice(double roomPrice) {
		this.roomPrice = roomPrice;
	}

	/**
	 * @return the roomTypeName
	 */
	public String getRoomTypeName() {
		return roomTypeName;
	}

	/**
	 * @param roomTypeName
	 *            the roomTypeName to set
	 */
	public void setRoomTypeName(String roomTypeName) {
		this.roomTypeName = roomTypeName;
	}

}
