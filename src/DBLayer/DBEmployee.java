package DBLayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import ModelLayer.Active;
import ModelLayer.Employee;
import ModelLayer.Person.AccessLvl;

/**
 * 
 * @author Eugen Bobolea, Dalibor Branny, Adrian Cucolas, Catalin Rat
 *
 */

public class DBEmployee implements IFDBEmployee {

	private Connection con;

	public DBEmployee() {
		con = DBConnection.getInstance().getDBcon();
	}

	public ArrayList<Employee> getAllEmployees() {
		return miscWhere("");

	}

	public ArrayList<Employee> getAllEmployees(String name) {
		return miscWhere("fName like '%" + name + "%' or lName like'%" + name + "%'");

	}

	public ArrayList<Employee> getAllEmployees(String wClause, String var) {
		return miscWhere(wClause + " ='" + var + "'");

	}

	public Employee findEmployee(int employeeId) {
		String wClause = " employeeId = '" + employeeId + "'";
		return singleWhere(wClause);
	}

	public Employee findEmployee(String name) {
		String wClause = "fName ='" + name + "' or lName ='" + name + "'";
		if (name.contains(" ")) {
			String[] str = name.split(" ");
			wClause = " fName = '" + str[0] + "' and lName ='" + str[1] + "'";
		}
		return singleWhere(wClause);
	}

	public int insertEmployee(Employee emp) throws Exception {
		int nextID = GetMax.getMaxId("Select max(employeeId) from Employee");
		nextID++;

		int re = -1;
		String query = "INSERT INTO Employee(employeeId, fName, lName, address, city, zip, phone, email, cpr, accessLvl, active, password, loggedIn) VALUES('"
				+ nextID + "','" + emp.getfName() + "','" + emp.getlName() + "','" + emp.getAddress() + "','"
				+ emp.getCity() + "','" + emp.getZip() + "','" + emp.getPhone() + "','" + emp.getEmail() + "','"
				+ emp.getCpr() + "','" + emp.getAccessLvl().toString() + "','" + emp.getActive().toString() + "','"
				+ emp.getPassword() + "','" + emp.isLoggedIn() + "')";
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			re = stmt.executeUpdate(query);
			stmt.close();
		} catch (SQLException e) {
			System.out.println(e);
			System.out.println("Employee not inserted.");
			throw new Exception("Employee is not inserted correct");
		}
		return re;
	}

	public int updateEmployee(Employee employee) {
		Employee emp = employee;
		int rc = -1;

		String query = "UPDATE Employee SET " + "fName ='" + emp.getfName() + "', " + "lName ='" + emp.getlName()
				+ "', " + "address ='" + emp.getAddress() + "', " + "city ='" + emp.getCity() + "', " + "zip ='"
				+ emp.getZip() + "', " + "phone ='" + emp.getPhone() + "', " + "email ='" + emp.getEmail() + "', "
				+ "cpr ='" + emp.getCpr() + "', " + "accessLvl ='" + emp.getAccessLvl().toString() + "', " + "active ='"
				+ emp.getActive().toString() + "', " + "password ='" + emp.getPassword() + "', " + "loggedIn ='"
				+ emp.isLoggedIn() + "' WHERE employeeId = " + emp.getEmployeeId();

		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);

			stmt.close();
		} catch (Exception e) {
			System.out.println("Update exception in Employee db: " + e);
		}
		return (rc);
	}

	private String buildQuery(String wClause) {
		String query = "SELECT employeeId, fName, lName, address, city, zip, phone, email, cpr, accessLvl, active, password, loggedIn FROM Employee";

		if (wClause.length() > 0)
			query = query + " WHERE " + wClause;

		return query;
	}

	private ArrayList<Employee> miscWhere(String wClause) {
		ResultSet results;
		ArrayList<Employee> list = new ArrayList<Employee>();
		String query = buildQuery(wClause);

		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);

			while (results.next()) {
				Employee emp = new Employee();
				emp = buildEmployee(results);
				list.add(emp);
			}
			stmt.close();
		} catch (Exception e) {
			System.out.println("Query exception - select: " + e);
		}
		return list;
	}

	private Employee singleWhere(String wClause) {
		ResultSet results;
		Employee emp = new Employee();
		String query = buildQuery(wClause);

		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);

			if (results.next()) {
				emp = buildEmployee(results);
				stmt.close();
			} else {
				emp = null;
			}
		} catch (Exception e) {
			System.out.println("Query exception: " + e);
		}
		return emp;
	}

	private Employee buildEmployee(ResultSet results) {
		Employee emp = new Employee();
		try {
			emp.setfName(results.getString("fName"));
			emp.setlName(results.getString("lName"));
			emp.setAddress(results.getString("address"));
			emp.setCity(results.getString("city"));
			emp.setZip(results.getString("zip"));
			emp.setPhone(results.getString("phone"));
			emp.setEmail(results.getString("email"));
			emp.setCpr(results.getString("cpr"));
			emp.setEmployeeId(results.getInt("employeeId"));
			emp.setAccessLvl(AccessLvl.valueOf(results.getString("accessLvl")));
			emp.setActive(Active.valueOf(results.getString("active")));
			emp.setPassword(results.getString("password"));
			emp.setLoggedIn(results.getBoolean("loggedIn"));

		} catch (Exception e) {
			System.out.println("Error in building the Employee object");
		}
		return emp;
	}
	
	public void updateEmployee1(String whatToUpdate, String newValue, int employeeId) throws SQLException {
		PreparedStatement prepSt = null;
		String query = "update Employee set " + whatToUpdate + " = ? where employeeId = ?";
		try {
			con.setAutoCommit(false);
			prepSt = con.prepareStatement(query);
			prepSt.setString(1, newValue);
			prepSt.setInt(2, employeeId);
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
	
	public void resetPassword(int employeeId) throws SQLException{
		PreparedStatement prepSt = null;
		String query = "UPDATE Employee SET password = RIGHT((SELECT cpr FROM Employee WHERE employeeId = ?),4) WHERE employeeId = ?";
		try {
			prepSt = con.prepareStatement(query);
			prepSt.setInt(1, employeeId);
			prepSt.setInt(2, employeeId);
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
