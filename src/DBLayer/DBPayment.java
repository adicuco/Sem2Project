package DBLayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;

import ModelLayer.Customer;
import ModelLayer.Payment;

public class DBPayment implements IFDBPayment {

	private Connection con;

	public DBPayment() {
		con = DBConnection.getInstance().getDBcon();
	}

	public ArrayList<Payment> getAllPayments() {
		return miscWhere("");

	}

	public ArrayList<Payment> getAllPayments(String wClause, String var) {
		return miscWhere(wClause + " ='" + var + "'");

	}
	
	public ArrayList<Payment> getBetween(Timestamp from, Timestamp till){
		ArrayList<Payment> list = new ArrayList<Payment>();
		String query = "SELECT * FROM Payment WHERE date BETWEEN ? and ?";
		PreparedStatement prepSt = null;
		ResultSet results;
		try {
			prepSt = con.prepareStatement(query);
			prepSt.setTimestamp(1, from);
			prepSt.setTimestamp(2, till);
			results = prepSt.executeQuery();
			while (results.next()) {
				Payment pay = new Payment();
				pay = buildPayment(results);
				list.add(pay);}
		
		} catch (Exception e) {
			System.out.println("Query exception - select: " + e);	
		}
		return list;
	}

	public Payment findPayment(int paymentId) {
		String wClause = " paymentId = '" + paymentId + "'";
		return singleWhere(wClause);
	}

	public int insertPayment(Payment pay) throws Exception {
		int nextID = GetMax.getMaxId("Select max(paymentId) from Payment");
		nextID++;

		int rc = -1;
		String query = "INSERT INTO Payment(paymentId, customerId, amount, date, msName) VALUES('" + nextID + "','"
				+ pay.getCustomer().getCustomerId() + "','" + pay.getAmount() + "','" + pay.getDate() + "', '" + pay.getMembership().getName()
				+ " ')";

		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);
			stmt.close();
		} catch (SQLException e) {
			System.out.println(e);
			System.out.println("Payment not inserted.");
			throw new Exception("Payment is not inserted correct");
		}
		return rc;
	}
	
	public int maxPaymentId(int customerId) throws SQLException{
		ResultSet results = null;
		int maxId = -1;
		String query = "SELECT MAX(paymentId) as Id FROM Payment WHERE customerId = " + customerId + " AND date < GETDATE()";
		try{
			Statement stmt = con.createStatement();
			results = stmt.executeQuery(query);
			if (results.next()) {
				maxId = results.getInt(1);
			}
		}catch (Exception e) {
			System.out.println("Query exception - select: " + e);
		}
		return maxId;
	}
	
	public void insertPayment(Customer customer) throws SQLException{
		int nextID = GetMax.getMaxId("Select max(paymentId) from Payment");
		nextID++;
		
		String query = "INSERT INTO Payment (paymentId, customerId, amount, date, msName) VALUES (?, ?, ?, ?, ?)";
	
		PreparedStatement prepSt = null;
		try {
			con.setAutoCommit(false);
			prepSt = con.prepareStatement(query);
			prepSt.setInt(1, nextID);
			prepSt.setInt(2, customer.getCustomerId());
			prepSt.setDouble(3, customer.getAmountToPay());
			prepSt.setString(4, LocalDate.now().toString());
			prepSt.setString(5, customer.getMembership().getName());
			prepSt.executeUpdate();
			
			con.commit();
		} catch (Exception e) {
			System.out.println("Query exception - select: " + e);
			if (con != null) {
				try {
					System.err.print("Transaction is being rolled back");
					con.rollback();
				} catch (SQLException excep) {
					System.out.println(excep);
				}
			}
		} finally {
			if (prepSt != null) {
				prepSt.close();
			}
			con.setAutoCommit(true);
		}
	}

	private String buildQuery(String wClause) {
		String query = "SELECT paymentId, customerId, amount, date, msName FROM Payment";

		if (wClause.length() > 0)
			query = query + " WHERE " + wClause;
		return query;
	}

	private ArrayList<Payment> miscWhere(String wClause) {
		ResultSet results;
		ArrayList<Payment> list = new ArrayList<Payment>();
		String query = buildQuery(wClause);

		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);

			while (results.next()) {
				Payment pay = new Payment();
				pay = buildPayment(results);
				list.add(pay);
			}
			stmt.close();
		} catch (Exception e) {
			System.out.println("Query exception - select: " + e);
		}
		return list;
	}

	private Payment singleWhere(String wClause) {
		ResultSet results;
		Payment pay = new Payment();
		String query = buildQuery(wClause);

		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);

			if (results.next()) {
				pay = buildPayment(results);
				stmt.close();
			} else {
				pay = null;
			}
		} catch (Exception e) {
			System.out.println("Query exception: " + e);
		}
		return pay;
	}

	private Payment buildPayment(ResultSet results) {
		Payment pay = new Payment();
		try {
			pay.setPaymentId(results.getInt("paymentId"));
			pay.setAmount(results.getDouble("amount"));
			pay.setDate(results.getTimestamp("date"));
			IFDBCustomer dbCus = new DBCustomer();
			pay.setCustomer(dbCus.findCustomer(results.getInt("customerId")));
			IFDBMembership dbMs = new DBMembership();
			pay.setMembership(dbMs.findMembership(results.getString("msName")));

		} catch (Exception e) {
			System.out.println("Error in building the Payment object");
		}
		return pay;
	}
	
	
	
}
