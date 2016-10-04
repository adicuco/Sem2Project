package ControlLayer;

import java.sql.SQLException;
import java.util.ArrayList;

import DBLayer.DBConnection;
import DBLayer.DBEmployee;
import DBLayer.IFDBEmployee;
import ModelLayer.Active;
import ModelLayer.Employee;
import ModelLayer.LoginErrors;
import ModelLayer.Person.AccessLvl;

public class EmployeeCtr {

	public EmployeeCtr() {
	}

	public ArrayList<Employee> findAllEmployees() {
		IFDBEmployee dbEmp = new DBEmployee();
		ArrayList<Employee> allEmp = new ArrayList<Employee>();
		allEmp = dbEmp.getAllEmployees();
		return allEmp;
	}

	public ArrayList<Employee> findAllEmployees(String name) {
		IFDBEmployee dbEmp = new DBEmployee();
		ArrayList<Employee> allEmp = new ArrayList<Employee>();
		allEmp = dbEmp.getAllEmployees(name);
		return allEmp;
	}

	public ArrayList<Employee> findAllEmployees(String wClause, String var) {
		IFDBEmployee dbEmp = new DBEmployee();
		ArrayList<Employee> allEmp = new ArrayList<Employee>();
		allEmp = dbEmp.getAllEmployees(wClause, var);
		return allEmp;
	}

	public Employee findEmployee(String name) {
		IFDBEmployee dbEmp = new DBEmployee();
		return dbEmp.findEmployee(name);

	}

	public Employee findEmployee(int employeeId) {
		IFDBEmployee dbEmp = new DBEmployee();
		return dbEmp.findEmployee(employeeId);
	}

	public int updateEmployee(int employeeId, String fName, String lName, String address, String city, String zip,
			String phone, String email, String cpr, String accessLvl, String active, String password) {
		IFDBEmployee dbEmp = new DBEmployee();
		Employee emp = new Employee();
		emp.setEmployeeId(employeeId);
		emp.setfName(fName);
		emp.setlName(lName);
		emp.setAddress(address);
		emp.setCity(city);
		emp.setZip(zip);
		emp.setPhone(phone);
		emp.setEmail(email);
		emp.setCpr(cpr);
		emp.setAccessLvl(AccessLvl.valueOf(accessLvl));
		emp.setActive(Active.valueOf(active));
		emp.setPassword(password);

		return dbEmp.updateEmployee(emp);
	}

	public void insertNew(String fName, String lName, String address, String city, String zip, String phone,
			String email, String cpr, AccessLvl accessLvl) throws Exception {
		Employee emp = new Employee(fName, lName, address, city, zip, phone, email, cpr);
		emp.setAccessLvl(accessLvl);
		try {
			IFDBEmployee dbEmp = new DBEmployee();
			DBConnection.startTransaction();
			dbEmp.insertEmployee(emp);
			DBConnection.commitTransaction();
		} catch (Exception e) {
			DBConnection.rollbackTransaction();
			throw new Exception("Employee not inserted");
		}
	}

	public LoginErrors login(int employeeId, String password) {
		IFDBEmployee dbEmp = new DBEmployee();
		Employee emp = dbEmp.findEmployee(employeeId);
		if (emp == null) {
			return LoginErrors.PASSWORD;
		} else {
			if (emp.getPassword().equals(password)) {
				if (!emp.isLoggedIn()) {
					emp.setLoggedIn(true);
					dbEmp.updateEmployee(emp);
					return LoginErrors.SUCCESS;
				} else {
					return LoginErrors.LOGGEDIN;
				}
			} else {
				return LoginErrors.PASSWORD;
			}
		}
	}
	
	public void updateEmployee(String whatToUpdate, String newValue, int employeeId) throws SQLException{
		IFDBEmployee dbEmp = new DBEmployee();
		dbEmp.updateEmployee1(whatToUpdate, newValue, employeeId);
		
	}

	public void logout(int employeeId) {
		IFDBEmployee dbEmp = new DBEmployee();
		Employee cus = dbEmp.findEmployee(employeeId);
		cus.setLoggedIn(false);
		dbEmp.updateEmployee(cus);
	}
}
