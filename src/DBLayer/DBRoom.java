package DBLayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import ModelLayer.Active;
import ModelLayer.Room;
import ModelLayer.RoomType;
import ModelLayer.Status;

public class DBRoom implements IFDBRoom {
	private Connection con;

	public DBRoom() {
		con = DBConnection.getInstance().getDBcon();
	}

	public ArrayList<Room> getAllRooms() {
		return miscWhere("");

	}

	public ArrayList<Room> getAllRooms(String wClause, String var) {
		return miscWhere(wClause + " ='" + var + "'");

	}

	public ArrayList<Room> getAllAvailable(Timestamp checkIn, Timestamp checkOut) {
		System.out.println(checkIn.toString() + "    " + checkOut.toString());
		ArrayList<Room> list = new ArrayList<Room>();
		String query = "SELECT * FROM Room WHERE active = 'ACTIVE' AND roomId NOT IN ( SELECT roomId FROM Rent WHERE rentType ='ROOM' AND startDate BETWEEN ? AND ? OR endDate BETWEEN ? AND ?)";
		PreparedStatement prepSt = null;
		ResultSet results;
		try {
			prepSt = con.prepareStatement(query);
			prepSt.setTimestamp(1, checkIn);
			prepSt.setTimestamp(2, checkOut);
			prepSt.setTimestamp(3, checkIn);
			prepSt.setTimestamp(4, checkOut);
			results = prepSt.executeQuery();
			while (results.next()) {
				Room room = new Room();
				room = buildRoom(results);
				list.add(room);
			}
		} catch (Exception e) {
			System.out.println("Query exception - select: " + e);
		}

		return list;
	}

	public Room findRoom(int roomId) {
		String wClause = " roomId = '" + roomId + "'";
		return singleWhere(wClause);
	}

	public int insertRoom(Room room) throws Exception {
		int nextID = GetMax.getMaxId("Select max(roomId) from Room");
		nextID++;

		int rc = -1;
		String query = "INSERT INTO Room(roomId, roomTypeName, extraBed, status, active, notes) VALUES('" + nextID
				+ "','" + room.getRoomType().getRoomTypeName() + "','" + room.isExtraBed() + "','"
				+ room.getStatus().toString() + "','" + room.getActive().toString() + "','" + room.getNotes() + " ')";

		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);
			stmt.close();
		} catch (SQLException e) {
			rc = -2;
			System.out.println(e);
			System.out.println("Room not inserted.");
			throw new Exception("Room is not inserted correct");
		}
		return rc;
	}

	public int updateRoom(Room room) {
		int rc = -1;

		String query = "UPDATE Room SET " + "roomTypeName ='" + room.getRoomType().getRoomTypeName() + "', "
				+ "extraBed ='" + room.isExtraBed() + "', " + "status ='" + room.getStatus().toString() + "', "
				+ "active = '" + room.getActive().toString() + "', " + "notes = '" + room.getNotes()
				+ "' WHERE roomId = " + room.getRoomId();

		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);

			stmt.close();
		} catch (Exception e) {
			System.out.println("Update exception in Room db: " + e);
			rc = -2;
		}
		return (rc);
	}

	private String buildQuery(String wClause) {
		String query = "SELECT roomId, roomTypeName, extraBed, status, active, notes FROM Room";

		if (wClause.length() > 0)
			query = query + " WHERE " + wClause;

		return query;
	}

	private ArrayList<Room> miscWhere(String wClause) {
		ResultSet results;
		ArrayList<Room> list = new ArrayList<Room>();
		String query = buildQuery(wClause);

		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);

			while (results.next()) {
				Room room = new Room();
				room = buildRoom(results);
				list.add(room);
			}
			stmt.close();
		} catch (Exception e) {
			System.out.println("Query exception - select: " + e);
		}
		return list;
	}

	private Room singleWhere(String wClause) {
		ResultSet results;
		Room room = new Room();
		String query = buildQuery(wClause);

		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);

			if (results.next()) {
				room = buildRoom(results);
				stmt.close();
			} else {
				room = null;
			}
		} catch (Exception e) {
			System.out.println("Query exception: " + e);
		}
		return room;
	}

	private Room buildRoom(ResultSet results) {
		Room room = new Room();
		try {
			room.setRoomId(results.getInt("roomId"));
			room.setExtraBed(results.getBoolean("extraBed"));
			room.setNotes(results.getString("notes"));
			room.setActive(Active.valueOf(results.getString("active")));
			room.setStatus(Status.valueOf(results.getString("status")));
			IFDBRoomType dbRt = new DBRoomType();
			RoomType rT = dbRt.findRoomType(results.getString("roomTypeName"));
			room.setRoomType(rT);
			room.setRoomPrice(rT.getRoomPrice());

		} catch (Exception e) {
			System.out.println("Error in building the Room object");
		}
		return room;
	}

}
