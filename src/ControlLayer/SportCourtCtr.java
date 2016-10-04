package ControlLayer;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import DBLayer.DBConnection;
import DBLayer.DBCustomer;
import DBLayer.DBRent;
import DBLayer.DBSportCourt;
import DBLayer.IFDBCustomer;
import DBLayer.IFDBRent;
import DBLayer.IFDBSportCourt;
import ModelLayer.Active;
import ModelLayer.EquipmentType;
import ModelLayer.Rent;
import ModelLayer.SportCourt;
import ModelLayer.Status;

public class SportCourtCtr {

	public SportCourtCtr() {
	}

	public ArrayList<SportCourt> findAllSportCourts() {
		IFDBSportCourt dbSc = new DBSportCourt();
		ArrayList<SportCourt> allSc = new ArrayList<SportCourt>();
		allSc = dbSc.getAllSportCourts();
		return allSc;
	}

	public SportCourt findSportCourt(int courtId) {
		IFDBSportCourt dbSc = new DBSportCourt();
		return dbSc.findSportCourt(courtId);

	}

	public int updateSportCourt(int courtId, double price) {
		IFDBSportCourt dbSc = new DBSportCourt();
		SportCourt scR = dbSc.findSportCourt(courtId);
		scR.setPrice(price);
		return dbSc.updateSportCourt(scR);
	}

	public int closeSportCourt(int courtId) {
		IFDBSportCourt dbSc = new DBSportCourt();
		SportCourt sc = dbSc.findSportCourt(courtId);
		sc.setActive(Active.INACTIVE);
		return dbSc.updateSportCourt(sc);

	}

	public int openSportCourt(int courtId) {
		IFDBSportCourt dbSc = new DBSportCourt();
		SportCourt sc = dbSc.findSportCourt(courtId);
		sc.setActive(Active.ACTIVE);
		return dbSc.updateSportCourt(sc);

	}

	public void insertNew(EquipmentType eqType, String sport, double price) throws Exception {
		SportCourt sc = new SportCourt(eqType, sport, price);
		try {
			IFDBSportCourt dbSc = new DBSportCourt();
			DBConnection.startTransaction();
			dbSc.insertSportCourt(sc);
			DBConnection.commitTransaction();
		} catch (Exception e) {
			DBConnection.rollbackTransaction();
			throw new Exception("SportCourt not inserted");
		}
	}

	public List<String> getSportTypes() {
		IFDBSportCourt dbSport = new DBSportCourt();
		return dbSport.getCourtTypes();
	}

	public ArrayList<SportCourt> getAvailableSportCourts(Timestamp date, String courtType, int customerId) {
		IFDBRent dbRent = new DBRent();
		IFDBSportCourt dbSp = new DBSportCourt();
		IFDBCustomer dbCus = new DBCustomer();
		double discount = (double) dbCus.findCustomer(customerId).getMembership().getDiscount() / 100;
		ArrayList<SportCourt> courts = dbSp.getAllSportCourts("sport", courtType);
		ArrayList<Rent> rents = dbRent.getAvailableSportCourts(date, courtType);

		for (Rent rent : rents) {
			if (!courts.isEmpty()) {
				for (SportCourt sportCourt : courts) {
					if (rent.getRentable().getId() == sportCourt.getCourtId()) {
						sportCourt.setStatus(Status.UNAVAILABLE);
						break;
					}
				}
			}
		}

		for (int i = 0; i <= courts.size() - 1; i++) {
			SportCourt spq = courts.get(i);
			double price = spq.getPrice();
			price -= price * discount;
			spq.setPrice(price);
			if (spq.getActive().equals(Active.INACTIVE)) {
				spq.setStatus(Status.UNAVAILABLE);
			}
		}

		return courts;
	}

	@SuppressWarnings("deprecation")
	public ArrayList<SportCourt> getAvailableSportCourtsDay(Timestamp startDate, Timestamp endDate, String courtType) {
		IFDBSportCourt dbSp = new DBSportCourt();
		ArrayList<SportCourt> courts = dbSp.getAllSportCourts("sport", courtType);

		Timestamp day = (Timestamp) startDate.clone();
		while (day.getHours() <= endDate.getHours()) {
			ArrayList<SportCourt> sps = getAvailableSportCourts(day, courtType, 0);
			for (int i = 0; i <= courts.size() - 1; i++) {
				if (courts.get(i).getStatus().equals(Status.AVAILABLE)) {
					for (int j = 0; j <= sps.size() - 1; j++) {
						if (courts.get(i).getCourtId() == sps.get(j).getCourtId()) {
							if (sps.get(j).getStatus().equals(Status.UNAVAILABLE)) {
								courts.get(i).setStatus(Status.UNAVAILABLE);
							}
						}
					}
				}
			}
			day.setHours(day.getHours() + 1);
		}
		return courts;
	}
}