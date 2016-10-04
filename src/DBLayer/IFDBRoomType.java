package DBLayer;

import java.util.ArrayList;

import ModelLayer.RoomType;

public interface IFDBRoomType {

	public ArrayList<RoomType> getAllRoomTypes();

	public RoomType findRoomType(String roomTypeName);

	public int insertRoomType(RoomType rT) throws Exception;

	public int updateRoomType(RoomType rT);
	
	public int checkIfAssigned(String typeName);
}
