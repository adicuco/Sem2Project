package ControlLayer;

import java.sql.Timestamp;
import java.util.ArrayList;

import DBLayer.DBConnection;
import DBLayer.DBCustomer;
import DBLayer.DBEquipment;
import DBLayer.DBEquipmentType;
import DBLayer.DBRent;
import DBLayer.IFDBCustomer;
import DBLayer.IFDBEquipment;
import DBLayer.IFDBEquipmentType;
import DBLayer.IFDBRent;
import ModelLayer.Active;
import ModelLayer.Equipment;
import ModelLayer.EquipmentType;
import ModelLayer.Rent;

/**
 * 
 * @author Eugen Bobolea, Dalibor Branny, Adrian Cucolas, Catalin Rat
 *
 */

public class EquipmentCtr {

	public EquipmentCtr() {
	}

	public ArrayList<Equipment> findAllEquipment() {
		IFDBEquipment dbEq = new DBEquipment();
		ArrayList<Equipment> allEquipment = new ArrayList<Equipment>();
		allEquipment = dbEq.getAllEquipment();
		return allEquipment;
	}

	public ArrayList<Equipment> findAllEquipment(String wClause, String var) {
		IFDBEquipment dbEq = new DBEquipment();
		ArrayList<Equipment> allEquipment = new ArrayList<Equipment>();
		allEquipment = dbEq.getAllEquipment(wClause, var);
		return allEquipment;
	}

	public Equipment findEquipment(int eqId) {
		IFDBEquipment dbEq = new DBEquipment();
		return dbEq.findEquipment(eqId);
	}

	public int updateEquipment(int eqId, int eqTypeId, String name, String description, int quantity, double price,
			String active) {
		IFDBEquipment dbEq = new DBEquipment();
		Equipment equipment = new Equipment();
		equipment.setEqId(eqId);
		IFDBEquipmentType dbEt = new DBEquipmentType();
		equipment.setEqType(dbEt.findEquipmentType(eqTypeId));
		equipment.setName(name);
		equipment.setDescription(description);
		equipment.setQuantity(quantity);
		equipment.setActive(Active.valueOf(active));
		equipment.setPrice(price);
		return dbEq.updateEquipment(equipment);
	}

	public int removeEquipment(int eqId) {
		IFDBEquipment dbEq = new DBEquipment();
		Equipment eq = dbEq.findEquipment(eqId);
		eq.setActive(Active.INACTIVE);
		return dbEq.updateEquipment(eq);
	}

	public void insertNew(int eqTypeId, String name, String description, int quantity, double price) throws Exception {
		IFDBEquipmentType eT = new DBEquipmentType();
		EquipmentType eqType = eT.findEquipmentType(eqTypeId);
		Equipment equipment = new Equipment(eqType, name, description, quantity, price);
		try {
			IFDBEquipment dbEq = new DBEquipment();
			DBConnection.startTransaction();
			dbEq.insertEquipment(equipment);
			DBConnection.commitTransaction();
		} catch (Exception e) {
			DBConnection.rollbackTransaction();
			throw new Exception("Equipment not inserted");
		}

	}

	public ArrayList<Equipment> getAvailableEquipment(Timestamp period1, Timestamp period2, int eqTypeId,
			int customerId) {
		IFDBRent dbRent = new DBRent();
		IFDBEquipment dbEq = new DBEquipment();
		IFDBCustomer dbCus = new DBCustomer();
		IFDBEquipmentType dbEqType = new DBEquipmentType();
		double discount = (double) dbCus.findCustomer(customerId).getMembership().getDiscount() / 100;
		EquipmentType eqType = dbEqType.findEquipmentType(eqTypeId);
		ArrayList<Equipment> eqs = dbEq.getAllEquipment("eqTypeId", String.valueOf(eqTypeId));
		ArrayList<Rent> rents = dbRent.getAvailableEquipment(period1, period2, eqType);

		for (Rent rent : rents) {
			if (!eqs.isEmpty()) {
				for (Equipment equipment : eqs) {
					if (rent.getRentable().getId() == equipment.getEqId()) {
						int quantity = 0;
						if (rent.getNotes().contains("|")) {
							String[] str = rent.getNotes().split("|");
							quantity = Integer.valueOf(str[0]);
						} else {
							quantity = Integer.valueOf(rent.getNotes());
						}
						equipment.setQuantity(equipment.getQuantity() - quantity);
						break;
					}
				}
			}

		}

		for (int i = 0; i <= eqs.size() - 1; i++) {
			Equipment eqq = eqs.get(i);
			double price = eqq.getPrice();
			price -= price * discount;
			eqq.setPrice(price);
			if (eqq.getQuantity() <= 0 || eqq.getActive().equals(Active.INACTIVE)) {
				eqs.remove(eqq);
				i--;
			}
		}
		return eqs;
	}

}
