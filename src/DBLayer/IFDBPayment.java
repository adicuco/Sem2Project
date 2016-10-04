package DBLayer;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import ModelLayer.Customer;
import ModelLayer.Payment;

public interface IFDBPayment {
	
	public ArrayList<Payment> getAllPayments();
	
	public ArrayList<Payment> getAllPayments(String wClause, String var);

	public Payment findPayment(int paymentId);

	public int insertPayment(Payment pay) throws Exception;
	
	public ArrayList<Payment> getBetween(Timestamp from, Timestamp till);
	
	public void insertPayment(Customer customer) throws SQLException;
	
	public int maxPaymentId(int customerId) throws SQLException;

}
