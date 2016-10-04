package ControlLayer;

import java.sql.Timestamp;
import java.util.ArrayList;

import DBLayer.DBConnection;
import DBLayer.DBRoom;
import DBLayer.DBRoomType;
import DBLayer.IFDBRoom;
import DBLayer.IFDBRoomType;
import ModelLayer.Active;
import ModelLayer.Room;
import ModelLayer.Status;

public class RoomCtr {

	public RoomCtr() {
	}

	public ArrayList<Room> findAllRooms() {
		IFDBRoom dbRoom = new DBRoom();
		ArrayList<Room> allRooms = new ArrayList<Room>();
		allRooms = dbRoom.getAllRooms();
		return allRooms;
	}

	public ArrayList<Room> findAllRooms(String wClause, String var) {
		IFDBRoom dbRoom = new DBRoom();
		ArrayList<Room> allRooms = new ArrayList<Room>();
		allRooms = dbRoom.getAllRooms(wClause, var);
		return allRooms;
	}
	
	public ArrayList<Room> getAllAvailable(Timestamp checkIn, Timestamp checkOut){
		IFDBRoom dbRoom = new DBRoom();
		ArrayList<Room> allAvaillable = new ArrayList<Room>();
		allAvaillable = dbRoom.getAllAvailable(checkIn, checkOut);
		return allAvaillable;
	}

	public Room findRoom(int roomId) {
		IFDBRoom dbRoom = new DBRoom();
		return dbRoom.findRoom(roomId);
	}

	public int updateRoom(int roomId, String roomTypeName, boolean extraBed, String status, String active,
			String notes) {
		IFDBRoom dbRoom = new DBRoom();
		Room room = new Room();
		room.setRoomId(roomId);
		room.setExtraBed(extraBed);
		room.setStatus(Status.valueOf(status));
		room.setActive(Active.valueOf(active));
		room.setNotes(notes);
		IFDBRoomType dbRt = new DBRoomType();
		room.setRoomType(dbRt.findRoomType(roomTypeName));
		return dbRoom.updateRoom(room);
	}

	public int removeRoom(int roomId) {
		IFDBRoom dbRoom = new DBRoom();
		Room room = dbRoom.findRoom(roomId);
		room.setActive(Active.INACTIVE);
		return dbRoom.updateRoom(room);

	}

	public void insertNew(String roomTypeName, boolean extraBed, String notes) throws Exception {
		IFDBRoomType rT = new DBRoomType();
		Room room = new Room(rT.findRoomType(roomTypeName),extraBed,notes);
		try {
			IFDBRoom dbRoom = new DBRoom();
			DBConnection.startTransaction();
			dbRoom.insertRoom(room);
			DBConnection.commitTransaction();
		} catch (Exception e) {
			DBConnection.rollbackTransaction();
			throw new Exception("Room not inserted");
		}
	}
	
	public int checkIfAssigned(String typeName)
	{
		IFDBRoomType rt = new DBRoomType();
		int check = 0;
		return  check = rt.checkIfAssigned(typeName);
	}
}
