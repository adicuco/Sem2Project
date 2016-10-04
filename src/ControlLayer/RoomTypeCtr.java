package ControlLayer;

import java.util.ArrayList;

import DBLayer.DBConnection;
import DBLayer.DBRoomType;
import DBLayer.IFDBRoomType;
import ModelLayer.RoomType;

public class RoomTypeCtr {

	public RoomTypeCtr() {
	}

	public ArrayList<RoomType> findAllRoomTypes() {
		IFDBRoomType dbRt = new DBRoomType();
		ArrayList<RoomType> allRt = new ArrayList<RoomType>();
		allRt = dbRt.getAllRoomTypes();
		return allRt;
	}

	public RoomType findRoomType(String name) {
		IFDBRoomType dbRt = new DBRoomType();
		return dbRt.findRoomType(name);

	}

	public int updateRoomType(String roomTypeName, double roomPrice) {
		IFDBRoomType dbRt = new DBRoomType();
		RoomType rT = new RoomType();
		rT.setRoomTypeName(roomTypeName);
		rT.setRoomPrice(roomPrice);
		return dbRt.updateRoomType(rT);
	}

	public void insertNew(String roomTypeName, double roomPrice) throws Exception {
		RoomType rT = new RoomType(roomTypeName, roomPrice);
		try {
			IFDBRoomType dbRt = new DBRoomType();
			DBConnection.startTransaction();
			dbRt.insertRoomType(rT);
			DBConnection.commitTransaction();
		} catch (Exception e) {
			DBConnection.rollbackTransaction();
			throw new Exception("RoomType not inserted");
		}
	}
	
	public int checkIfAssigned(String typeName)
	{
		IFDBRoomType rt = new DBRoomType();
		int check = 0;
		return  check = rt.checkIfAssigned(typeName);
	}

}
