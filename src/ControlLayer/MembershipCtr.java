package ControlLayer;

import java.util.ArrayList;

import DBLayer.DBConnection;
import DBLayer.DBMembership;
import DBLayer.IFDBMembership;
import ModelLayer.Membership;

public class MembershipCtr {

	public MembershipCtr(){
	}
	
	public ArrayList<Membership> findAllMemberships() {
		IFDBMembership dbMs = new DBMembership();
		ArrayList<Membership> allMs = new ArrayList<Membership>();
		allMs = dbMs.getAllMemberships();
		return allMs;
	}

	public Membership findMembership(String name) {
		IFDBMembership dbMs = new DBMembership();
		return dbMs.findMembership(name);

	}

	public int updateMembership(String name, int discount, double price) {
		IFDBMembership dbMs = new DBMembership();
		Membership ms = new Membership();
		ms.setName(name);
		ms.setDiscount(discount);
		ms.setPrice(price);
		return dbMs.updateMembership(ms);
	}

	public void insertNew(String name, int discount, double price) throws Exception {
		Membership ms = new Membership(name, discount, price);
		try {
			IFDBMembership dbMs = new DBMembership();
			DBConnection.startTransaction();
			dbMs.insertMembership(ms);
			DBConnection.commitTransaction();
		} catch (Exception e) {
			DBConnection.rollbackTransaction();
			throw new Exception("Membership not inserted");
		}
	}
}
