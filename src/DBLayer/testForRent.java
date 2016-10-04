package DBLayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import ModelLayer.Customer;
import ModelLayer.EquipmentType;
import ModelLayer.Rent;
import ModelLayer.RentType;
import ModelLayer.Rentable;

public class testForRent implements IFDBRent {

	private Connection con;

	public testForRent() {
		con = DBConnection.getInstance().getDBcon();
	}

	public ArrayList<Rent> getAllRents() {
		return miscWhere("");

	}

	public ArrayList<Rent> getAllRents(String wClause, String var) {
		return miscWhere(wClause + " ='" + var + "'");

	}

	public ArrayList<Rent> getRentals(Timestamp date, String sport) throws SQLException{
		ResultSet results;
		ArrayList<Rent> list = new ArrayList<Rent>();
		PreparedStatement prepSt;
		String query = "select rentId, customerId, courtId, roomId, eqId, dateRentMade, startDate, endDate, rentType, tAmount, notes "
				+ "from Rent where renTtype = 'SPORTCOURT' and startDate = ? and courtId in (select courtId from SportCourt where sport = ?)";
		try {
		prepSt = con.prepareStatement(query);
		prepSt.setTimestamp(1, date);
		prepSt.setString(2, sport);
		results = prepSt.executeQuery();
		
		while (results.next()) {
			Rent rent = new Rent();
			rent = buildRent(results);
			list.add(rent);
		}
	}catch (Exception e) {
		System.out.println("Query exception - select: " + e);
	}
		return list;
	}
	
	public ArrayList<Rent> getRentals(String type, String startDate, String endDate, String sport) throws SQLException{
		ResultSet results;
		ArrayList<Rent> list = new ArrayList<Rent>();
		PreparedStatement prepSt;
		String query = "select rentId, customerId, courtId, roomId, eqId, dateRentMade, startDate, endDate, rentType, tAmount, notes"
				+ "from Rent where renTtype = '?' and  (startDate >= '?' and endDate <= '?') and courtId in (select courtId from SportCourt where sport = '?')";
		try {
		prepSt = con.prepareStatement(query);
		prepSt.setString(1, type);
		prepSt.setString(3, startDate);
		prepSt.setString(3, endDate);
		prepSt.setString(3, sport);
		results = prepSt.executeQuery();
		
		while (results.next()) {
			Rent rent = new Rent();
			rent = buildRent(results);
			list.add(rent);
		}
	}catch (Exception e) {
		System.out.println("Query exception - select: " + e);
	}
		return list;
	}
	
	
	public Rent findRent(int rentId) {
		String wClause = " rentId = '" + rentId + "'";
		return singleWhere(wClause);
	}

	public int insertRent(Rent rent) throws Exception {
		int nextID = GetMax.getMaxId("Select max(rentId) from Rent");
		nextID++;
		String query = "INSERT INTO RENT";
		switch (rent.getRentType()) {
		case ROOM:
			query += "(rentId, customerId, roomId, dateRentMade, startDate, endDate, rentType, tAmount, notes)";
			break;
		case EQUIPMENT:
			query += "(rentId, customerId, eqId, dateRentMade, startDate, endDate, rentType, tAmount, notes)";
			break;
		case SPORTCOURT:
			query += "(rentId, customerId, courtId, dateRentMade, startDate, endDate, rentType, tAmount, notes)";
			break;
		}
		int rc = -1;
		query += " VALUES('" + nextID + "','" + rent.getCustomer().getCustomerId() + "','" + rent.getRentable().getId()
				+ "','" + rent.getDateRentMade() + "','" + rent.getStartDate() + "','" + rent.getEndDate() + "','"
				+ rent.getRentType().toString() + "'," + rent.gettAmount() + ",'" + rent.getNotes() + "')";
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);
			stmt.close();
		} catch (SQLException e) {
			rc = -2;
			System.out.println(e);
			System.out.println("Rent not inserted.");
			throw new Exception("Rent is not inserted correct");
		}
		return rc;
	}

	private String buildQuery(String wClause) {
		String query = "SELECT rentId, customerId, courtId, roomId, eqId, dateRentMade, startDate, endDate, rentType, tAmount, notes FROM Rent";

		if (wClause.length() > 0)
			query = query + " WHERE " + wClause;

		return query;
	}

	private ArrayList<Rent> miscWhere(String wClause) {
		ResultSet results;
		ArrayList<Rent> list = new ArrayList<Rent>();
		String query = buildQuery(wClause);

		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);

			while (results.next()) {
				Rent rent = new Rent();
				rent = buildRent(results);
				list.add(rent);
			}
			stmt.close();
		} catch (Exception e) {
			System.out.println("Query exception - select: " + e);
		}
		return list;
	}

	private Rent singleWhere(String wClause) {
		ResultSet results;
		Rent rent = new Rent();
		String query = buildQuery(wClause);

		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);

			if (results.next()) {
				rent = buildRent(results);
				stmt.close();
			} else {
				rent = null;
			}
		} catch (Exception e) {
			System.out.println("Query exception: " + e);
		}
		return rent;
	}

	private Rent buildRent(ResultSet results) {
		Rent rent = new Rent();
		try {
			rent.setRentId(results.getInt("rentId"));
			rent.setDateRentMade(results.getTimestamp("dateRentMade"));
			rent.setStartDate(results.getTimestamp("startDate"));
			rent.setEndDate(results.getTimestamp("endDate"));
			rent.setRentType(RentType.valueOf(results.getString("rentType")));
			rent.settAmount(results.getDouble("tAmount"));
			IFDBCustomer dbCus = new DBCustomer();
			rent.setCustomer(dbCus.findCustomer(results.getInt("customerId")));
			rent.setNotes(results.getString("notes"));

			Rentable rt = null;
			switch (rent.getRentType()) {
			case ROOM:
				IFDBRoom dbRoom = new DBRoom();
				rt = dbRoom.findRoom(results.getInt("roomId"));
				break;
			case EQUIPMENT:
				IFDBEquipment dbEq = new DBEquipment();
				rt = dbEq.findEquipment(results.getInt("eqId"));
				break;
			case SPORTCOURT:
				IFDBSportCourt dbSport = new DBSportCourt();
				rt = dbSport.findSportCourt(results.getInt("courtId"));
				break;
			}
			rent.setRentable(rt);

		} catch (Exception e) {
			System.out.println("Error in building the Rent object");
		}
		return rent;
	}
	
	public int deleteRent(int rentId) {
		int rc = -1;
		
		String query = "DELETE FROM Rent WHERE rentId= " + rentId;
		
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);

			stmt.close();
		} catch (Exception e) {
			System.out.println("Update exception in Rent db (Delete rent): " + e);
			rc = -2;
		}
		return (rc);
	}

	@Override
	public ArrayList<Rent> getAvailableEquipment(Timestamp period1, Timestamp period2, EquipmentType eqType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertNewRent(Rent rent) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int deleteRent(Rent rent, Customer customer) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<Rent> getAvailableSportCourts(Timestamp date, String courtType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertSportCourtAndEquipment(Rent sportCourt, ArrayList<Rent> eqs) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Rent> getRoomReservation(Timestamp checkIn, Timestamp checkOut) {
		// TODO Auto-generated method stub
		return null;
	}

}
