package DBLayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

import ModelLayer.Customer;
import ModelLayer.EquipmentType;
import ModelLayer.Rent;
import ModelLayer.RentType;
import ModelLayer.Rentable;

public class DBRent implements IFDBRent {

	private Connection con;

	public DBRent() {
		con = DBConnection.getInstance().getDBcon();
	}

	public ArrayList<Rent> getAllRents() {
		return miscWhere("");

	}

	public ArrayList<Rent> getAllRents(String wClause, String var) {
		return miscWhere(wClause + " ='" + var + "'");

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

	public int deleteRent(Rent rent, Customer customer) {
		int rc = -1;

		String query = "DELETE FROM Rent WHERE rentId= " + rent.getRentId() + ";\n";
		if (customer.getCustomerId() != 0 || Timestamp.valueOf(LocalDateTime.now()).after(rent.getStartDate())) {
			query += "UPDATE Customer SET amountToPay = amountToPay - " + rent.gettAmount() + " WHERE customerId = "
					+ customer.getCustomerId();
		}
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

	@SuppressWarnings("deprecation")
	public ArrayList<Rent> getAvailableEquipment(Timestamp period1, Timestamp period2, EquipmentType eqType) {
		ResultSet results;
		ArrayList<Rent> list = new ArrayList<Rent>();
		PreparedStatement prepSt;
		Timestamp open = (Timestamp) period1.clone();
		open.setHours(8);
		open.setMinutes(0);
		Timestamp close = (Timestamp) period2.clone();
		close.setHours(23);
		close.setMinutes(50);

		String query = "SELECT rentId, customerId, courtId, roomId, eqId, dateRentMade, startDate, endDate, rentType, tAmount, notes "
				+ "FROM Rent WHERE rentType = 'EQUIPMENT' and (? BETWEEN ? and endDate) and (? BETWEEN startDate and ?)"
				+ " and eqId in (select eqId FROM Equipment WHERE eqTypeId = ?)";
		try {
			prepSt = con.prepareStatement(query);
			prepSt.setTimestamp(1, period1);
			prepSt.setTimestamp(2, open);
			prepSt.setTimestamp(3, period2);
			prepSt.setTimestamp(4, close);
			prepSt.setInt(5, eqType.getEqTypeId());
			results = prepSt.executeQuery();

			while (results.next()) {
				Rent rent = new Rent();
				rent = buildRent(results);
				list.add(rent);
			}
		} catch (Exception e) {
			System.out.println("Query exception - select: " + e);
		}
		return list;
	}

	public ArrayList<Rent> getRoomReservation(Timestamp checkIn, Timestamp checkOut){
		ResultSet results;
		ArrayList<Rent> list = new ArrayList<Rent>();
		PreparedStatement prepSt;
		String query = "SELECT * FROM Rent WHERE rentType = 'ROOM' AND startDate = ? AND endDate = ?";
		try {
			prepSt = con.prepareStatement(query);
			prepSt.setTimestamp(1, checkIn);
			prepSt.setTimestamp(2, checkOut);
			results = prepSt.executeQuery();

			while (results.next()) {
				Rent rent = new Rent();
				rent = buildRent(results);
				list.add(rent);
			}

		} catch (Exception e) {
			System.out.println("Query exception - select: " + e);
		}
		return list;
	}
	
	public ArrayList<Rent> getAvailableSportCourts(Timestamp date, String courtType) {
		ResultSet results;
		ArrayList<Rent> list = new ArrayList<Rent>();
		PreparedStatement prepSt;

		String query = "SELECT rentId, customerId, courtId, roomId, eqId, dateRentMade, startDate, endDate, rentType, tAmount, notes "
				+ "FROM Rent WHERE rentType = 'SPORTCOURT' and (? BETWEEN startDate and endDate)"
				+ " and courtId in (select courtId from SportCourt where sport = ? )";
		try {
			prepSt = con.prepareStatement(query);
			prepSt.setTimestamp(1, date);
			prepSt.setString(2, courtType);
			results = prepSt.executeQuery();

			while (results.next()) {
				Rent rent = new Rent();
				rent = buildRent(results);
				list.add(rent);
			}

		} catch (Exception e) {
			System.out.println("Query exception - select: " + e);
		}
		return list;
	}

	@SuppressWarnings("deprecation")
	public void insertNewRent(Rent rent) throws SQLException {
		int nextID = GetMax.getMaxId("Select max(rentId) from Rent");
		nextID++;
		rent.getStartDate().setMinutes(0);
		rent.getStartDate().setSeconds(0);
		PreparedStatement insertRent = null;
		PreparedStatement updateCustomer = null;

		String insertString = "INSERT INTO RENT";
		switch (rent.getRentType()) {
		case ROOM:
			insertString += "(rentId, customerId, roomId, dateRentMade, startDate, endDate, rentType, tAmount, notes)";
			break;
		case EQUIPMENT:
			insertString += "(rentId, customerId, eqId, dateRentMade, startDate, endDate, rentType, tAmount, notes)";
			break;
		case SPORTCOURT:
			insertString += "(rentId, customerId, courtId, dateRentMade, startDate, endDate, rentType, tAmount, notes)";
			break;
		}
		insertString += " VALUES(?,?,?,?,?,?,?,?,?)";

		String updateStatement = "update Customer set amountToPay = amountToPay + ? where customerId = ?";

		try {
			con.setAutoCommit(false);
			insertRent = con.prepareStatement(insertString);
			updateCustomer = con.prepareStatement(updateStatement);

			insertRent.setInt(1, nextID);
			insertRent.setInt(2, rent.getCustomer().getCustomerId());
			insertRent.setInt(3, rent.getRentable().getId());
			insertRent.setTimestamp(4, rent.getDateRentMade());
			insertRent.setTimestamp(5, rent.getStartDate());
			insertRent.setTimestamp(6, rent.getEndDate());
			insertRent.setString(7, rent.getRentType().toString());
			insertRent.setDouble(8, rent.gettAmount());
			insertRent.setString(9, rent.getNotes());
			insertRent.executeUpdate();

			updateCustomer.setDouble(1, rent.gettAmount());
			updateCustomer.setInt(2, rent.getCustomer().getCustomerId());
			updateCustomer.executeUpdate();

			con.commit();
		} catch (SQLException e) {
			System.out.println(e);
			System.out.println("Rent not inserted.");
			if (con != null) {
				try {
					System.err.print("Transaction is being rolled back");
					con.rollback();
				} catch (SQLException excep) {
					System.out.println(excep);
				}
			}
		} finally {
			if (insertRent != null) {
				insertRent.close();
			}
			if (updateCustomer != null) {
				updateCustomer.close();
			}
			con.setAutoCommit(true);
		}
	}

	@SuppressWarnings("deprecation")
	public void insertSportCourtAndEquipment(Rent sportCourt, ArrayList<Rent> eqs) throws SQLException {
		int nextID = GetMax.getMaxId("Select max(rentId) from Rent");
		nextID++;
		sportCourt.getStartDate().setMinutes(0);
		sportCourt.getStartDate().setSeconds(0);
		PreparedStatement insertSportCourt = null;
		PreparedStatement insertEquipment = null;
		PreparedStatement updateCustomer = null;

		String insertSportCourtString = "INSERT INTO RENT (rentId, customerId, courtId, dateRentMade, startDate, endDate, rentType, tAmount, notes) VALUES(?,?,?,?,?,?,?,?,?)";
		String insertEquipmentString = "INSERT INTO RENT (rentId, customerId, eqId, dateRentMade, startDate, endDate, rentType, tAmount, notes) VALUES(?,?,?,?,?,?,?,?,?)";

		String updateStatement = "update Customer set amountToPay = amountToPay + ? where customerId = ?";

		try {
			con.setAutoCommit(false);
			insertSportCourt = con.prepareStatement(insertSportCourtString);
			insertEquipment = con.prepareStatement(insertEquipmentString);
			updateCustomer = con.prepareStatement(updateStatement);

			insertSportCourt.setInt(1, nextID);
			insertSportCourt.setInt(2, sportCourt.getCustomer().getCustomerId());
			insertSportCourt.setInt(3, sportCourt.getRentable().getId());
			insertSportCourt.setTimestamp(4, sportCourt.getDateRentMade());
			insertSportCourt.setTimestamp(5, sportCourt.getStartDate());
			insertSportCourt.setTimestamp(6, sportCourt.getEndDate());
			insertSportCourt.setString(7, sportCourt.getRentType().toString());
			insertSportCourt.setDouble(8, sportCourt.gettAmount());
			insertSportCourt.setString(9, sportCourt.getNotes());
			insertSportCourt.executeUpdate();

			updateCustomer.setDouble(1, sportCourt.gettAmount());
			updateCustomer.setInt(2, sportCourt.getCustomer().getCustomerId());
			updateCustomer.executeUpdate();
			con.commit();

			for (Rent eq : eqs) {
				nextID++;
				insertEquipment.setInt(1, nextID);
				insertEquipment.setInt(2, eq.getCustomer().getCustomerId());
				insertEquipment.setInt(3, eq.getRentable().getId());
				insertEquipment.setTimestamp(4, eq.getDateRentMade());
				insertEquipment.setTimestamp(5, eq.getStartDate());
				insertEquipment.setTimestamp(6, eq.getEndDate());
				insertEquipment.setString(7, eq.getRentType().toString());
				insertEquipment.setDouble(8, eq.gettAmount());
				insertEquipment.setString(9, eq.getNotes());
				insertEquipment.executeUpdate();

				updateCustomer.setDouble(1, eq.gettAmount());
				updateCustomer.setInt(2, eq.getCustomer().getCustomerId());
				updateCustomer.executeUpdate();
				con.commit();
			}
		} catch (SQLException e) {
			System.out.println(e);
			System.out.println("Rent not inserted.");
			if (con != null) {
				try {
					System.err.print("Transaction is being rolled back");
					con.rollback();
				} catch (SQLException excep) {
					System.out.println(excep);
				}
			}
		} finally {
			if (insertSportCourt != null) {
				insertSportCourt.close();
			}
			if (insertEquipment != null) {
				insertEquipment.close();
			}
			if (updateCustomer != null) {
				updateCustomer.close();
			}
			con.setAutoCommit(true);
		}
	}
}
