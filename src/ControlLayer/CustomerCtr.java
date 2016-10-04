package ControlLayer;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import DBLayer.DBConnection;
import DBLayer.DBCustomer;
import DBLayer.DBMembership;
import DBLayer.IFDBCustomer;
import DBLayer.IFDBMembership;
import ModelLayer.Active;
import ModelLayer.Customer;
import ModelLayer.LoginErrors;
import ModelLayer.Membership;
import ModelLayer.Person.AccessLvl;

public class CustomerCtr {

	public CustomerCtr() {
	}

	public ArrayList<Customer> findAllCustomers() {
		IFDBCustomer dbCus = new DBCustomer();
		ArrayList<Customer> allCus = new ArrayList<Customer>();
		allCus = dbCus.getAllCustomers();
		return allCus;
	}

	public ArrayList<Customer> findAllCustomers(String name) {
		IFDBCustomer dbCus = new DBCustomer();
		ArrayList<Customer> allCus = new ArrayList<Customer>();
		allCus = dbCus.getAllCustomers(name);
		return allCus;
	}

	public ArrayList<Customer> findAllCustomers(String wClause, String var) {
		IFDBCustomer dbCus = new DBCustomer();
		ArrayList<Customer> allCus = new ArrayList<Customer>();
		allCus = dbCus.getAllCustomers(wClause, var);
		return allCus;
	}
	
	public ArrayList<Customer> getOverDue(){
		IFDBCustomer dbcus = new DBCustomer();
		return dbcus.getOverdue();
	}

	public Customer findCustomer(String name) {
		IFDBCustomer dbCus = new DBCustomer();
		return dbCus.findCustomer(name);

	}

	public Customer findCustomer(int id) {
		IFDBCustomer dbCus = new DBCustomer();
		return dbCus.findCustomer(id);
	}

	public int updateCustomer(Customer customer){
		IFDBCustomer dbCus = new DBCustomer();
		return dbCus.updateCustomer(customer);
	}
	
	public int updateCustomer(int customerId, String fName, String lName, String address, String city, String zip,
			String phone, String email, String cpr, boolean payCheck, Timestamp payDay, String accessLvl, String active,
			String membership, String password, double amountToPay) {
		IFDBCustomer dbCus = new DBCustomer();
		Customer cus = new Customer();
		cus.setCustomerId(customerId);
		cus.setfName(fName);
		cus.setlName(lName);
		cus.setAddress(address);
		cus.setCity(city);
		cus.setZip(zip);
		cus.setPhone(phone);
		cus.setEmail(email);
		cus.setCpr(cpr);
		cus.setPayCheck(payCheck);
		cus.setPayDay(payDay);
		cus.setAccessLvl(AccessLvl.valueOf(accessLvl));
		cus.setActive(Active.valueOf(active));
		cus.setPassword(password);
		IFDBMembership dbMs = new DBMembership();
		cus.setMembership(dbMs.findMembership(membership));
		cus.setAmountToPay(amountToPay);
		return dbCus.updateCustomer(cus);
	}

	public int removeCustomer(int custmerId) {
		IFDBCustomer dbCus = new DBCustomer();
		Customer cus = dbCus.findCustomer(custmerId);
		cus.setActive(Active.INACTIVE);
		return dbCus.updateCustomer(cus);

	}

	public void insertNew(String fName, String lName, String address, String city, String zip, String phone,
			String email, String cpr) throws Exception {
		Customer cus = new Customer(fName, lName, address, city, zip, phone, email, cpr);
		IFDBMembership dbMs = new DBMembership();
		cus.setMembership(dbMs.findMembership("Guest"));
		try {
			IFDBCustomer dbCus = new DBCustomer();
			DBConnection.startTransaction();
			dbCus.insertCustomer(cus);
			DBConnection.commitTransaction();
		} catch (Exception e) {
			DBConnection.rollbackTransaction();
			throw new Exception("Customer not inserted");
		}
	}
	
	public void inserNew(String fName, String lName, String address, String city, String zip, String phone, String email, String cpr, String membership) throws Exception{
		IFDBMembership dbMs = new DBMembership();
		IFDBCustomer dbCus = new DBCustomer();
		Membership mem = dbMs.findMembership(membership);
		dbCus.insertCustomer(fName, lName, address, city, zip, phone, email, cpr, membership, mem.getPrice());	
	}

	public LoginErrors login(int customerId, String password) {
		IFDBCustomer dbCus = new DBCustomer();
		Customer cus = dbCus.findCustomer(customerId);
		if (cus == null) {
			return LoginErrors.PASSWORD;
		} else {
			if (cus.getPassword().equals(password)) {
				if (cus.getActive() == Active.ACTIVE) {
					if (!cus.isLoggedIn()) {
						cus.setLoggedIn(true);
						dbCus.updateCustomer(cus);
						return LoginErrors.SUCCESS;
					} else {
						return LoginErrors.LOGGEDIN;
					}
				} else {
					return LoginErrors.INACTIVE;
				}
			} else {
				return LoginErrors.PASSWORD;
			}
		}
	}
	
	public void updateCustomer(String whatToUpdate, String newValue, int customerId) throws SQLException{
		IFDBCustomer dbCus = new DBCustomer();
		dbCus.updateCustomer1(whatToUpdate, newValue, customerId);
	}
	
	public void resetPassword(int customerId ) throws SQLException{
		IFDBCustomer dbcus = new DBCustomer();
		dbcus.resetPassword(customerId);
		
	}


	public void logout(int customerId) {
		IFDBCustomer dbCus = new DBCustomer();
		Customer cus = dbCus.findCustomer(customerId);
		cus.setLoggedIn(false);
		dbCus.updateCustomer(cus);
	}
}
