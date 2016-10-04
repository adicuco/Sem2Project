package DBLayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import ModelLayer.Active;
import ModelLayer.Customer;
import ModelLayer.Event;
import ModelLayer.Rent;
import ModelLayer.Status;

public class DBEvent implements IFDBEvent {

	private Connection con;

	public DBEvent() {
		con = DBConnection.getInstance().getDBcon();
	}

	public ArrayList<Event> getAllEvents() {
		return miscWhere("");

	}

	public ArrayList<Event> getAllEvents(String wClause, String var) {
		return miscWhere(wClause + " ='" + var + "'");

	}

	public Event findEvent(int eventId) {
		String wClause = " eventId = '" + eventId + "'";
		return singleWhere(wClause);
	}

	public int insertEvent(Event event) throws Exception {
		int nextID = GetMax.getMaxId("Select max(eventId) from Event");
		nextID++;

		int rc = -1;
		String query = "INSERT INTO Event(eventId, name, description, minParticipants, maxParticipants, regStartDate, regEndDate, startDate, endDate, participantsNr,employeeId, active, price, status) VALUES('"
				+ nextID + "','" + event.getName() + "','" + event.getDescription() + "','" + event.getMinParticipants()
				+ "','" + event.getMaxParticipants() + "','" + event.getRegStartDate() + "','" + event.getRegEndDate()
				+ "','" + event.getStartDate() + "','" + event.getEndDate() + "','" + event.getParticipantsNr() + "','"
				+ event.getEmployee().getEmployeeId() + "','" + event.getActive().toString() + "','" + event.getPrice()
				+ "','" + event.getStatus().toString() + "')";

		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);
			stmt.close();
		} catch (SQLException e) {
			rc = -2;
			System.out.println(e);
			System.out.println("Event not inserted.");
			throw new Exception("Event is not inserted correct");
		}
		return rc;
	}

	public int updateEvent(Event event) {
		int rc = -1;

		String query = "UPDATE Event SET " + "name ='" + event.getName() + "', " + "description ='"
				+ event.getDescription() + "', " + "minParticipants ='" + event.getMinParticipants() + "', "
				+ "maxParticipants ='" + event.getMaxParticipants() + "', " + "regStartDate ='"
				+ event.getRegStartDate().toString() + "', " + "regEndDate ='" + event.getRegEndDate().toString()
				+ "', " + "startDate ='" + event.getStartDate().toString() + "', " + "endDate ='"
				+ event.getEndDate().toString() + "', " + "employeeId ='" + event.getEmployee().getEmployeeId() + "', "
				+ "active ='" + event.getActive().toString() + "', " + "price ='" + event.getPrice() + "', "
				+ "status ='" + event.getStatus().toString() + "'" + " WHERE eventId = " + event.getEventId();

		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);

			stmt.close();
		} catch (Exception e) {
			System.out.println("Update exception in Event db: " + e);
			rc = -2;
		}
		return (rc);
	}

	private String buildQuery(String wClause) {
		String query = "SELECT eventId, name, description, minParticipants, maxParticipants, regStartDate, regEndDate, startDate, endDate, participantsNr, employeeId, active, price, status FROM Event";

		if (wClause.length() > 0)
			query = query + " WHERE " + wClause;

		return query;
	}

	private ArrayList<Event> miscWhere(String wClause) {
		ResultSet results;
		ArrayList<Event> list = new ArrayList<Event>();
		String query = buildQuery(wClause);

		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);

			while (results.next()) {
				Event event = new Event();
				event = buildEvent(results);
				list.add(event);
			}
			stmt.close();
		} catch (Exception e) {
			System.out.println("Query exception - select: " + e);
		}
		return list;
	}

	private Event singleWhere(String wClause) {
		ResultSet results;
		Event event = new Event();
		String query = buildQuery(wClause);

		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);

			if (results.next()) {
				event = buildEvent(results);
				stmt.close();
			} else {
				event = null;
			}
		} catch (Exception e) {
			System.out.println("Query exception: " + e);
		}
		return event;
	}

	private Event buildEvent(ResultSet results) {
		Event event = new Event();
		try {
			event.setEventId(results.getInt("eventId"));
			event.setName(results.getString("name"));
			event.setDescription(results.getString("description"));
			event.setMinParticipants(results.getInt("minParticipants"));
			event.setMaxParticipants(results.getInt("maxParticipants"));
			event.setRegStartDate(results.getTimestamp("regStartDate"));
			event.setRegEndDate(results.getTimestamp("regEndDate"));
			event.setStartDate(results.getTimestamp("startDate"));
			event.setEndDate(results.getTimestamp("endDate"));
			event.setParticipantsNr(results.getInt("participantsNr"));
			IFDBEmployee dbEmp = new DBEmployee();
			event.setEmployee(dbEmp.findEmployee(results.getInt("employeeId")));
			event.setActive(Active.valueOf(results.getString("active")));
			event.setPrice(results.getDouble("price"));
			event.setStatus(Status.valueOf(results.getString("status")));
			event.setParticipants(getParticipants(event.getEventId()));

		} catch (Exception e) {
			System.out.println("Error in building the Event object");
		}
		return event;
	}

	public int addParticipant(Event event, Customer customer, int participantsNr) {
		int rc = -1;

		String query = "INSERT INTO EventParticipants (eventId, customerId) VALUES (" + event.getEventId() + ", "
				+ customer.getCustomerId() + ");\n";
		query += "UPDATE Event SET participantsNr= " + participantsNr + " WHERE eventId = " + event.getEventId()
				+ ";\n";
		query += "UPDATE Customer SET amountToPay = amountToPay + " + event.getPrice() + " WHERE customerId = "
				+ customer.getCustomerId();

		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);

			stmt.close();
		} catch (Exception e) {
			System.out.println("Update exception in Event db (Add participant): " + e);
			rc = -2;
		}
		return (rc);
	}

	public int deleteParticipant(Event event, Customer customer, int participantsNr) {
		int rc = -1;

		String query = "DELETE FROM EventParticipants WHERE eventId= " + event.getEventId() + " and customerId= "
				+ customer.getCustomerId() + ";\n";
		query += "UPDATE Event SET participantsNr= " + participantsNr + " WHERE eventId = " + event.getEventId()
				+ ";\n";
		query += "UPDATE Customer SET amountToPay = amountToPay - " + event.getPrice() + " WHERE customerId = "
				+ customer.getCustomerId();

		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);

			stmt.close();
		} catch (Exception e) {
			System.out.println("Update exception in Event db (Delete participant): " + e);
			rc = -2;
		}
		return (rc);
	}

	private ArrayList<Customer> getParticipants(int eventId) {
		ResultSet results;
		ArrayList<Customer> list = new ArrayList<Customer>();
		String query = "SELECT eventId, customerId FROM EventParticipants WHERE eventId= " + eventId;

		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);

			IFDBCustomer dbCus = new DBCustomer();
			Customer cus = new Customer();

			while (results.next()) {
				cus = dbCus.findCustomer(results.getInt("customerId"));
				list.add(cus);
			}
			stmt.close();
		} catch (Exception e) {
			System.out.println("Query exception - select: " + e);
		}
		return list;
	}

	public ArrayList<Event> getAllEvents(int customerId) {
		ResultSet results;
		ArrayList<Event> list = new ArrayList<Event>();
		String query = "SELECT eventId, customerId FROM EventParticipants WHERE customerId= " + customerId;

		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);

			while (results.next()) {
				Event event = new Event();
				event = findEvent(results.getInt("eventId"));
				list.add(event);
			}
			stmt.close();
		} catch (Exception e) {
			System.out.println("Query exception - select: " + e);
		}
		return list;
	}

	public ArrayList<Event> getAvailableEvents(Timestamp now) {
		ResultSet results;
		ArrayList<Event> list = new ArrayList<Event>();
		PreparedStatement prepSt;

		String query = "select * from Event where (? BETWEEN regStartDate and regEndDate)";
		try {
			prepSt = con.prepareStatement(query);
			prepSt.setTimestamp(1, now);
			results = prepSt.executeQuery();

			while (results.next()) {
				Event event = new Event();
				event = buildEvent(results);
				list.add(event);
			}
		} catch (Exception e) {
			System.out.println("Query exception - select: " + e);
		}
		return list;
	}

	public void insertEvent(Event event, ArrayList<Rent> sportCourts, ArrayList<Rent> eqs) throws SQLException {
		int nextID = GetMax.getMaxId("Select max(rentId) from Rent");
		nextID++;
		int eventID = GetMax.getMaxId("Select max(eventId) from Event");
		eventID++;

		PreparedStatement insertEvent = null;
		PreparedStatement insertSportCourt = null;
		PreparedStatement insertEquipment = null;

		String insertEventString = "INSERT INTO Event(eventId, name, description, minParticipants, maxParticipants, regStartDate,"
				+ " regEndDate, startDate, endDate, participantsNr,employeeId, active, price, status) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		String insertSportCourtString = "INSERT INTO RENT (rentId, customerId, courtId, dateRentMade, startDate, endDate, rentType, tAmount, notes)"
				+ " VALUES(?,?,?,?,?,?,?,?,?)";
		String insertEquipmentString = "INSERT INTO RENT (rentId, customerId, eqId, dateRentMade, startDate, endDate, rentType, tAmount, notes)"
				+ " VALUES(?,?,?,?,?,?,?,?,?)";

		try {
			con.setAutoCommit(false);
			insertEvent = con.prepareStatement(insertEventString);
			insertSportCourt = con.prepareStatement(insertSportCourtString);
			insertEquipment = con.prepareStatement(insertEquipmentString);

			insertEvent.setInt(1, eventID);
			insertEvent.setString(2, event.getName());
			insertEvent.setString(3, event.getDescription());
			insertEvent.setInt(4, event.getMinParticipants());
			insertEvent.setInt(5, event.getMaxParticipants());
			insertEvent.setTimestamp(6, event.getRegStartDate());
			insertEvent.setTimestamp(7, event.getRegEndDate());
			insertEvent.setTimestamp(8, event.getStartDate());
			insertEvent.setTimestamp(9, event.getEndDate());
			insertEvent.setInt(10, event.getParticipantsNr());
			insertEvent.setInt(11, event.getEmployee().getEmployeeId());
			insertEvent.setString(12, event.getActive().toString());
			insertEvent.setDouble(13, event.getPrice());
			insertEvent.setString(14, event.getStatus().toString());
			insertEvent.executeUpdate();
			con.commit();

			for (Rent sportCourt : sportCourts) {
				nextID++;
				insertSportCourt.setInt(1, nextID);
				insertSportCourt.setInt(2, sportCourt.getCustomer().getCustomerId());
				insertSportCourt.setInt(3, sportCourt.getRentable().getId());
				insertSportCourt.setTimestamp(4, sportCourt.getDateRentMade());
				insertSportCourt.setTimestamp(5, sportCourt.getStartDate());
				insertSportCourt.setTimestamp(6, sportCourt.getEndDate());
				insertSportCourt.setString(7, sportCourt.getRentType().toString());
				insertSportCourt.setDouble(8, sportCourt.gettAmount());
				insertSportCourt.setString(9, sportCourt.getNotes() + eventID);
				insertSportCourt.executeUpdate();
				con.commit();
			}

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
				insertEquipment.setString(9, eq.getNotes() + "|" + eventID);
				insertEquipment.executeUpdate();
				con.commit();
			}
		} catch (SQLException e) {
			System.out.println(e);
			System.out.println("Event not inserted.");
			if (con != null) {
				try {
					System.err.print("Transaction is being rolled back");
					con.rollback();
				} catch (SQLException excep) {
					System.out.println(excep);
				}
			}
		} finally {
			if (insertEvent != null) {
				insertEvent.close();
			}
			if (insertSportCourt != null) {
				insertSportCourt.close();
			}
			if (insertEquipment != null) {
				insertEquipment.close();
			}
			con.setAutoCommit(true);
		}
	}
}
