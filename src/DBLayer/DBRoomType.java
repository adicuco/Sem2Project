package DBLayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import ModelLayer.RoomType;

public class DBRoomType implements IFDBRoomType {

	private Connection con;

	public DBRoomType() {
		con = DBConnection.getInstance().getDBcon();
	}

	public ArrayList<RoomType> getAllRoomTypes() {
		return miscWhere("");

	}

	public RoomType findRoomType(String name) {
		String wClause = "roomTypeName ='" + name + "'";
		return singleWhere(wClause);
	}

	public int insertRoomType(RoomType rT) throws Exception {
		int rc = -1;
		String query = "INSERT INTO RoomType(roomTypeName, roomPrice) VALUES('" + rT.getRoomTypeName() + "','"
				+ rT.getRoomPrice() + " ')";

		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);
			stmt.close();
		} catch (SQLException e) {
			rc = -2;
			System.out.println(e);
			System.out.println("RoomType not inserted.");
			throw new Exception("RoomType is not inserted correct");
		}
		return rc;
	}

	public int updateRoomType(RoomType rTtomer) {
		RoomType rT = rTtomer;
		int rc = -1;

		String query = "UPDATE RoomType SET " + "roomPrice =" + rT.getRoomPrice()  + " WHERE roomTypeName = '"
				+ rT.getRoomTypeName() + "'";

		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);

			stmt.close();
		} catch (Exception e) {
			System.out.println("Update exception in RoomType db: " + e);
			rc = -2;
		}
		return (rc);
	}

	private String buildQuery(String wClause) {
		String query = "SELECT roomTypeName, roomPrice FROM RoomType";

		if (wClause.length() > 0)
			query = query + " WHERE " + wClause;

		return query;
	}

	private ArrayList<RoomType> miscWhere(String wClause) {
		ResultSet results;
		ArrayList<RoomType> list = new ArrayList<RoomType>();
		String query = buildQuery(wClause);

		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);

			while (results.next()) {
				RoomType rT = new RoomType();
				rT = buildRoomType(results);
				list.add(rT);
			}
			stmt.close();
		} catch (Exception e) {
			System.out.println("Query exception - select: " + e);
		}
		return list;
	}

	private RoomType singleWhere(String wClause) {
		ResultSet results;
		RoomType rT = new RoomType();
		String query = buildQuery(wClause);

		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);

			if (results.next()) {
				rT = buildRoomType(results);
				stmt.close();
			} else {
				rT = null;
			}
		} catch (Exception e) {
			System.out.println("Query exception: " + e);
		}
		return rT;
	}

	private RoomType buildRoomType(ResultSet results) {
		RoomType rT = new RoomType();
		try {
			rT.setRoomTypeName(results.getString("roomTypeName"));
			rT.setRoomPrice(results.getDouble("roomPrice"));
		} catch (Exception e) {
			System.out.println("Error in building the RoomType object");
		}
		return rT;
	}
	
	public int checkIfAssigned(String typeName){
		String query = "SELECT (CASE WHEN EXISTS (SELECT 1 FROM Room WHERE roomTypeName = ?) THEN 1 ELSE 0 END) AS [Assigned]";
		ResultSet results = null;
		PreparedStatement prepSt = null;
		int check = 0;
		try{
			prepSt = con.prepareStatement(query);
			prepSt.setString(1, typeName);
			results = prepSt.executeQuery();
			if (results.next()) {
				check = results.getInt(1);
			}
		}catch (Exception e) {
			System.out.println("Query exception - select: " + e);
		}
		return check;
	}

}
