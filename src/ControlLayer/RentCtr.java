package ControlLayer;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

import DBLayer.DBConnection;
import DBLayer.DBCustomer;
import DBLayer.DBEquipment;
import DBLayer.DBRent;
import DBLayer.DBRoom;
import DBLayer.DBSportCourt;
import DBLayer.IFDBCustomer;
import DBLayer.IFDBEquipment;
import DBLayer.IFDBRent;
import DBLayer.IFDBRoom;
import DBLayer.IFDBSportCourt;
import ModelLayer.Customer;
import ModelLayer.Equipment;
import ModelLayer.Rent;
import ModelLayer.RentType;
import ModelLayer.Rentable;

public class RentCtr {

	public RentCtr() {
	}

	public ArrayList<Rent> findAllRents() {
		IFDBRent dbRent = new DBRent();
		ArrayList<Rent> allRents = new ArrayList<Rent>();
		allRents = dbRent.getAllRents();
		return allRents;
	}

	public ArrayList<Rent> findAllRents(String wClause, String var) {
		IFDBRent dbRent = new DBRent();
		ArrayList<Rent> allRents = new ArrayList<Rent>();
		allRents = dbRent.getAllRents(wClause, var);
		return allRents;
	}

	public Rent findRent(int id) {
		IFDBRent dbRent = new DBRent();
		return dbRent.findRent(id);
	}
	
	public ArrayList<Rent> getRoomReservation(Timestamp checkIn, Timestamp checkOut){
		IFDBRent dbRent = new DBRent();
		return dbRent.getRoomReservation(checkIn, checkOut);
	}

	public void insertNew(Timestamp startDate, Timestamp endDate, String rentType, double tAmount, String notes,
			int customerId, int rentableId) throws Exception {
		IFDBCustomer dbCus = new DBCustomer();
		Customer customer = dbCus.findCustomer(customerId);
		Timestamp dateRentMade = Timestamp.valueOf(LocalDateTime.now());

		Rentable rentable = null;
		switch (RentType.valueOf(rentType)) {
		case ROOM:
			IFDBRoom dbRoom = new DBRoom();
			rentable = dbRoom.findRoom(rentableId);
			break;
		case EQUIPMENT:
			IFDBEquipment dbEq = new DBEquipment();
			rentable = dbEq.findEquipment(rentableId);
			break;
		case SPORTCOURT:
			IFDBSportCourt dbSport = new DBSportCourt();
			rentable = dbSport.findSportCourt(rentableId);
			break;
		}

		Rent rent = new Rent(dateRentMade, startDate, endDate, RentType.valueOf(rentType), tAmount, notes, customer,
				rentable);
		try {
			IFDBRent dbRent = new DBRent();
			DBConnection.startTransaction();
			dbRent.insertRent(rent);
			DBConnection.commitTransaction();
		} catch (Exception e) {
			DBConnection.rollbackTransaction();
			throw new Exception("Rent not inserted");
		}
	}

	public ArrayList<Rent> findOngoingRents(String wClause, String var) {
		ArrayList<Rent> rents = findAllRents(wClause, var);
		Rent rent = null;
		for (int i = 0; i <= rents.size() - 1; i++) {
			rent = rents.get(i);
			if (Timestamp.valueOf(LocalDateTime.now()).after(rent.getEndDate())) {
				rents.remove(rent);
				i--;
			}
		}
		return rents;
	}

	public int deleteRent(int rentId, int customerId) {
		IFDBRent dbRent = new DBRent();
		IFDBCustomer dbCus = new DBCustomer();
		Rent rent = dbRent.findRent(rentId);
		Customer customer = dbCus.findCustomer(customerId);
		return dbRent.deleteRent(rent, customer);
	}

	public void insertNewRent(Timestamp startDate, Timestamp endDate, String rentType, double tAmount, String notes,
			int customerId, int rentableId) throws Exception {
		IFDBCustomer dbCus = new DBCustomer();
		Customer customer = dbCus.findCustomer(customerId);
		Timestamp dateRentMade = Timestamp.valueOf(LocalDateTime.now());

		Rentable rentable = null;
		switch (RentType.valueOf(rentType)) {
		case ROOM:
			IFDBRoom dbRoom = new DBRoom();
			rentable = dbRoom.findRoom(rentableId);
			break;
		case EQUIPMENT:
			IFDBEquipment dbEq = new DBEquipment();
			rentable = dbEq.findEquipment(rentableId);
			break;
		case SPORTCOURT:
			IFDBSportCourt dbSport = new DBSportCourt();
			rentable = dbSport.findSportCourt(rentableId);
			break;
		}

		Rent rent = new Rent(dateRentMade, startDate, endDate, RentType.valueOf(rentType), tAmount, notes, customer,
				rentable);
		try {
			IFDBRent dbRent = new DBRent();
			DBConnection.startTransaction();
			dbRent.insertNewRent(rent);
			DBConnection.commitTransaction();
		} catch (Exception e) {
			DBConnection.rollbackTransaction();
			throw new Exception("Rent not inserted");
		}
	}

	public void insertSportCourtWithEquipment(Timestamp startDate, Timestamp endDate, double tAmount, String notes,
			int customerId, int rentableId, ArrayList<Equipment> eqs) throws Exception {
		IFDBCustomer dbCus = new DBCustomer();
		Customer customer = dbCus.findCustomer(customerId);
		Timestamp dateRentMade = Timestamp.valueOf(LocalDateTime.now());
		IFDBEquipment dbEq = new DBEquipment();
		IFDBSportCourt dbSport = new DBSportCourt();
		Rentable rentable = null;

		rentable = dbSport.findSportCourt(rentableId);
		Rent rent = new Rent(dateRentMade, startDate, endDate, RentType.valueOf("SPORTCOURT"), tAmount, notes, customer,
				rentable);

		ArrayList<Rent> rents = new ArrayList<Rent>();
		for (Equipment eq : eqs) {
			rentable = dbEq.findEquipment(eq.getEqId());
			Rent rentEq = new Rent(dateRentMade, startDate, endDate, RentType.valueOf("EQUIPMENT"), eq.getPrice(),
					String.valueOf(eq.getQuantity()), customer, rentable);
			rents.add(rentEq);
		}
		try {
			IFDBRent dbRent = new DBRent();
			DBConnection.startTransaction();
			dbRent.insertSportCourtAndEquipment(rent, rents);
			DBConnection.commitTransaction();
		} catch (Exception e) {
			DBConnection.rollbackTransaction();
			throw new Exception("Rent not inserted");
		}
	}
}
