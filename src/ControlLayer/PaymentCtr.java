package ControlLayer;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

import DBLayer.DBConnection;
import DBLayer.DBCustomer;
import DBLayer.DBMembership;
import DBLayer.DBPayment;
import DBLayer.IFDBCustomer;
import DBLayer.IFDBMembership;
import DBLayer.IFDBPayment;
import ModelLayer.Customer;
import ModelLayer.Membership;
import ModelLayer.Payment;

public class PaymentCtr {

	public PaymentCtr() {
	}

	public ArrayList<Payment> findAllPayments() {
		IFDBPayment dbPay = new DBPayment();
		ArrayList<Payment> allPays = new ArrayList<Payment>();
		allPays = dbPay.getAllPayments();
		return allPays;
	}

	public ArrayList<Payment> findAllPayments(String wClause, String var) {
		IFDBPayment dbPay = new DBPayment();
		ArrayList<Payment> allPays = new ArrayList<Payment>();
		allPays = dbPay.getAllPayments(wClause, var);
		return allPays;
	}
	
	public ArrayList<Payment> getBetween(Timestamp from, Timestamp till){
		IFDBPayment dbPay = new DBPayment();
		ArrayList<Payment> pays = new ArrayList<Payment>();
		pays = dbPay.getBetween(from, till);
		return pays;
	}

	public Payment findPayment(int id) {
		IFDBPayment dbPay = new DBPayment();
		return dbPay.findPayment(id);
	}
	
	public void insertNew(Customer customer) throws SQLException{
		IFDBPayment pay = new DBPayment();
		pay.insertPayment(customer);
	}
	
	public int getMaxId(int customerId) throws SQLException{
		IFDBPayment pay = new DBPayment();
		return pay.maxPaymentId(customerId);
	}

	public void insertNew(int customerId, String membershipName) throws Exception {
		IFDBCustomer dbCus = new DBCustomer();
		Customer customer = dbCus.findCustomer(customerId);
		IFDBMembership dbMs = new DBMembership();
		Membership membership = dbMs.findMembership(membershipName);
		Timestamp ts = Timestamp.valueOf(LocalDateTime.now());
		Payment pay = new Payment(customer, ts, membership);
		try {
			IFDBPayment dbPay = new DBPayment();
			DBConnection.startTransaction();
			dbPay.insertPayment(pay);
			DBConnection.commitTransaction();
		} catch (Exception e) {
			DBConnection.rollbackTransaction();
			throw new Exception("Payment not inserted");
		}
	}
}
