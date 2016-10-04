package DBLayer;

/**
 * 
 * @author Eugen Bobolea, Dalibor Branny, Adrian Cucolas, Catalin Rat
 *
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import ModelLayer.Active;
import ModelLayer.Customer;
import ModelLayer.Person.AccessLvl;

public class DBCustomer implements IFDBCustomer {

	private Connection con;

	public DBCustomer() {
		con = DBConnection.getInstance().getDBcon();
	}

	public ArrayList<Customer> getAllCustomers() {
		return miscWhere("");

	}

	public ArrayList<Customer> getAllCustomers(String name) {
		return miscWhere("fName like '%" + name + "%' or lName like'%" + name + "%'");

	}

	public ArrayList<Customer> getAllCustomers(String wClause, String var) {
		return miscWhere(wClause + " ='" + var + "'");

	}

	public Customer findCustomer(int customerId) {
		String wClause = " customerId = '" + customerId + "'";
		return singleWhere(wClause);
	}
	
	public Customer findCustomerByCpr(String cpr) {
		String wClause = " cpr = '" + cpr + "'";
		return singleWhere(wClause);
	}
	
	public ArrayList<Customer> getOverdue(){
		ArrayList<Customer> list = new ArrayList<Customer>();
		ResultSet results;
		String query = "SELECT * FROM Customer WHERE payDay < GETDATE()";

		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);

			while (results.next()) {
				Customer cus = new Customer();
				cus = buildCustomer(results);
				list.add(cus);
			}
			stmt.close();
		} catch (Exception e) {
			System.out.println("Query exception - select: " + e);
		}
		return list;
	}
	
	public Customer findCustomer(String name) {
		String wClause = "fName ='" + name + "' or lName ='" + name + "'";
		if (name.contains(" ")) {
			String[] str = name.split(" ");
			wClause = " fName = '" + str[0] + "' and lName ='" + str[1] + "'";
		}
		return singleWhere(wClause);
	}

	public int insertCustomer(Customer cus) throws Exception {
		int nextID = GetMax.getMaxId("Select max(customerId) from Customer");
		nextID++;

		int rc = -1;
		String query = "INSERT INTO Customer(customerId, fName, lName, address, city, zip, phone, email, cpr, payCheck, payDay, accessLvl, active, password, msName, amountToPay, loggedIn) VALUES('"
				+ nextID + "','" + cus.getfName() + "','" + cus.getlName() + "','" + cus.getAddress() + "','"
				+ cus.getCity() + "','" + cus.getZip() + "','" + cus.getPhone() + "','" + cus.getEmail() + "','"
				+ cus.getCpr() + "','" + cus.isPayCheck() + "'," + cus.getPayDay() + ",'"
				+ cus.getAccessLvl().toString() + "','" + cus.getActive().toString() + "','" + cus.getPassword() + "','"
				+ cus.getMembership().getName() + "'," + cus.getAmountToPay() + ",'" + cus.isLoggedIn() + "')";

		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);
			stmt.close();
		} catch (SQLException e) {
			rc = -2;
			System.out.println(e);
			System.out.println("Customer not inserted.");
			throw new Exception("Customer is not inserted correct");
		}
		return rc;
	}
	
	public void insertCustomer(String fName, String lName, String address, String city, String zip, String phone, String email, String cpr, String membership, double amountToPay) throws Exception {
		int nextID = GetMax.getMaxId("Select max(customerId) from Customer");
		nextID++;
		PreparedStatement prepSt = null;

		String query = "INSERT INTO Customer(customerId, fName, lName, address, city, zip, phone, email, cpr, payCheck, payDay, accessLvl, active, password, msName, amountToPay, loggedIn) VALUES"
				+ "(?,?,?,?,?,?,?,?,?, 'true', ?, 'CUSTOMER', 'ACTIVE', ?, ?, ?, 'false')";	
		try {
			con.setAutoCommit(false);
			prepSt = con.prepareStatement(query);
			prepSt.setInt(1, nextID);
			prepSt.setString(2, fName);
			prepSt.setString(3, lName);
			prepSt.setString(4, address);
			prepSt.setString(5, city);
			prepSt.setString(6, zip);
			prepSt.setString(7, phone);
			prepSt.setString(8, email);
			prepSt.setString(9, cpr);
			prepSt.setString(10, LocalDate.now().plusMonths(1).toString());
			prepSt.setString(11, cpr.substring(7));
			prepSt.setString(12, membership);
			prepSt.setDouble(13, amountToPay);
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

	public int updateCustomer(Customer customer) {
		Customer cus = customer;
		int rc = -1;

		String payDay = "payDay =" + cus.getPayDay() + ", ";
		if (cus.getPayDay() != null) {
			payDay = "payDay ='" + cus.getPayDay().toString() + "', ";
		}

		String query = "UPDATE Customer SET " + "fName ='" + cus.getfName() + "', " + "lName ='" + cus.getlName()
				+ "', " + "address ='" + cus.getAddress() + "', " + "city ='" + cus.getCity() + "', " + "zip ='"
				+ cus.getZip() + "', " + "phone ='" + cus.getPhone() + "', " + "email ='" + cus.getEmail() + "', "
				+ "cpr ='" + cus.getCpr() + "', " + "payCheck ='" + cus.isPayCheck() + "', " + payDay + "accessLvl ='"
				+ cus.getAccessLvl().toString() + "', " + "active ='" + cus.getActive().toString() + "', "
				+ "password ='" + cus.getPassword() + "', " + "msName ='" + cus.getMembership().getName() + "', "
				+ "amountToPay =" + cus.getAmountToPay() + ", " + "loggedIn ='" + cus.isLoggedIn() + "'"
				+ " WHERE customerId = " + cus.getCustomerId();

		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);

			stmt.close();
		} catch (Exception e) {
			System.out.println("Update exception in Customer db: " + e);
			rc = -2;
		}
		return (rc);
	}

	private String buildQuery(String wClause) {
		String query = "SELECT customerId, fName, lName, address, city, zip, phone, email, cpr, payCheck, payDay, accessLvl, active, password, msName, amountToPay, loggedIn FROM Customer";

		if (wClause.length() > 0)
			query = query + " WHERE " + wClause;

		return query;
	}

	private ArrayList<Customer> miscWhere(String wClause) {
		ResultSet results;
		ArrayList<Customer> list = new ArrayList<Customer>();
		String query = buildQuery(wClause);

		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);

			while (results.next()) {
				Customer cus = new Customer();
				cus = buildCustomer(results);
				list.add(cus);
			}
			stmt.close();
		} catch (Exception e) {
			System.out.println("Query exception - select: " + e);
		}
		return list;
	}

	private Customer singleWhere(String wClause) {
		ResultSet results;
		Customer cus = new Customer();
		String query = buildQuery(wClause);

		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);

			if (results.next()) {
				cus = buildCustomer(results);
				stmt.close();
			} else {
				cus = null;
			}
		} catch (Exception e) {
			System.out.println("Query exception: " + e);
		}
		return cus;
	}

	private Customer buildCustomer(ResultSet results) {
		Customer cus = new Customer();
		try {
			cus.setfName(results.getString("fName"));
			cus.setlName(results.getString("lName"));
			cus.setAddress(results.getString("address"));
			cus.setCity(results.getString("city"));
			cus.setZip(results.getString("zip"));
			cus.setPhone(results.getString("phone"));
			cus.setEmail(results.getString("email"));
			cus.setCpr(results.getString("cpr"));
			cus.setCustomerId(results.getInt("customerId"));
			cus.setPayCheck(results.getBoolean("payCheck"));
			cus.setPayDay(results.getTimestamp("payDay"));
			cus.setAccessLvl(AccessLvl.valueOf(results.getString("accessLvl")));
			cus.setActive(Active.valueOf(results.getString("active")));
			cus.setPassword(results.getString("password"));
			IFDBMembership dbMs = new DBMembership();
			cus.setMembership(dbMs.findMembership(results.getString("msName")));
			cus.setAmountToPay(results.getDouble("amountToPay"));
			cus.setLoggedIn(results.getBoolean("loggedIn"));

		} catch (Exception e) {
			System.out.println("Error in building the Customer object");
		}
		return cus;
	}

	public void updateCustomer1(String whatToUpdate, String newValue, int customerId) throws SQLException {
		PreparedStatement prepSt = null;
		String query = "update Customer set " + whatToUpdate + " = ? where customerId = ?";
		try {
			con.setAutoCommit(false);
			prepSt = con.prepareStatement(query);
			prepSt.setString(1, newValue);
			prepSt.setInt(2, customerId);
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
	
	public void resetPassword(int customerId) throws SQLException{
		PreparedStatement prepSt = null;
		String query = "UPDATE Customer SET password = RIGHT((SELECT cpr FROM Customer WHERE customerId = ?),4) WHERE customerId = ?";
		try {
			prepSt = con.prepareStatement(query);
			prepSt.setInt(1, customerId);
			prepSt.setInt(2, customerId);
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
		}
	}


}
