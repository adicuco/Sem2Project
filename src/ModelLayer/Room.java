package ModelLayer;

public class Room extends Rentable {

	private int roomId;
	private RoomType roomType;
	private double roomPrice;
	private boolean extraBed;
	private String notes;

	public Room(RoomType roomType, boolean extraBed, String notes) {
		this.roomType = roomType;
		this.extraBed = extraBed;
		this.notes = notes;
		roomPrice = roomType.getRoomPrice();
		this.setActive(Active.ACTIVE);
		this.setStatus(Status.AVAILABLE);

	}

	public Room() {
	}

	/**
	 * @return the roomId
	 */
	public int getRoomId() {
		return roomId;
	}

	/**
	 * @return the roomType
	 */
	public RoomType getRoomType() {
		return roomType;
	}

	/**
	 * @return the roomPrice
	 */
	public double getRoomPrice() {
		return roomPrice;
	}

	/**
	 * @return the extraBed
	 */
	public boolean isExtraBed() {
		return extraBed;
	}

	/**
	 * @return the notes
	 */
	public String getNotes() {
		return notes;
	}

	/**
	 * @param roomId
	 *            the roomId to set
	 */
	public void setRoomId(int roomId) {
		this.setId(roomId);
		this.roomId = roomId;
	}

	/**
	 * @param roomType
	 *            the roomType to set
	 */
	public void setRoomType(RoomType roomType) {
		this.roomType = roomType;
	}

	/**
	 * @param roomPrice
	 *            the roomPrice to set
	 */
	public void setRoomPrice(double roomPrice) {
		this.roomPrice = roomPrice;
	}

	/**
	 * @param extraBed
	 *            the extraBed to set
	 */
	public void setExtraBed(boolean extraBed) {
		this.extraBed = extraBed;
	}

	/**
	 * @param notes
	 *            the notes to set
	 */
	public void setNotes(String notes) {
		this.notes = notes;
	}

}
