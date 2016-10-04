package DBLayer;

import java.sql.Timestamp;
import java.util.ArrayList;

import ModelLayer.Room;

public interface IFDBRoom {

	public ArrayList<Room> getAllRooms();
	
	public ArrayList<Room> getAllRooms(String wClause, String var);

	public Room findRoom(int roomId);

	public int insertRoom(Room room) throws Exception;

	public int updateRoom(Room room);
	
	public ArrayList<Room> getAllAvailable(Timestamp checkIn, Timestamp checkOut);
}
