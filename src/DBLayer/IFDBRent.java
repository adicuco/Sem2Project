package DBLayer;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import ModelLayer.Customer;
import ModelLayer.EquipmentType;
import ModelLayer.Rent;

public interface IFDBRent {

	public ArrayList<Rent> getAllRents();

	public ArrayList<Rent> getAllRents(String wClause, String var);

	public Rent findRent(int rentId);

	public int insertRent(Rent rent) throws Exception;

	public void insertNewRent(Rent rent) throws SQLException;

	public int deleteRent(Rent rent, Customer customer);

	public ArrayList<Rent> getAvailableEquipment(Timestamp period1, Timestamp period2, EquipmentType eqType);

	public ArrayList<Rent> getAvailableSportCourts(Timestamp date, String courtType);

	public void insertSportCourtAndEquipment(Rent sportCourt, ArrayList<Rent> eqs) throws SQLException;
	
	public ArrayList<Rent> getRoomReservation(Timestamp checkIn, Timestamp checkOut);

}
