package ModelLayer;

import java.sql.Timestamp;
import java.util.ArrayList;

public class PreRentEquipment {

	private ArrayList<Equipment> equipments;
	private Timestamp startDate;
	private Timestamp endDate;

	public PreRentEquipment(ArrayList<Equipment> equipments, Timestamp startDate, Timestamp endDate) {
		this.equipments = equipments;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public ArrayList<Equipment> getEquipments() {
		return equipments;
	}

	public Timestamp getStartDate() {
		return startDate;
	}

	public Timestamp getEndDate() {
		return endDate;
	}

	public void setEquipments(ArrayList<Equipment> equipments) {
		this.equipments = equipments;
	}

	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(Timestamp endDate) {
		this.endDate = endDate;
	}

}
