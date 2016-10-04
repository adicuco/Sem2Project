package DBLayer;

import java.sql.SQLException;
import java.util.ArrayList;

import ModelLayer.Customer;

public interface IFDBCustomer {

	public ArrayList<Customer> getAllCustomers();

	public ArrayList<Customer> getAllCustomers(String name);
	
	public ArrayList<Customer> getAllCustomers(String wClause, String var);

	public Customer findCustomer(int customerId);

	public Customer findCustomer(String name);

	public int insertCustomer(Customer cus) throws Exception;

	public int updateCustomer(Customer cus);
	
	public void updateCustomer1(String whatToUpdate, String newValue, int customerId) throws SQLException;
	
	public void resetPassword(int customerId) throws SQLException;
	
	public void insertCustomer(String fName, String lName, String address, String city, String zip, String phone, String email, String cpr, String membership, double amountToPay) throws Exception;

	public Customer findCustomerByCpr(String cpr);
	
	public ArrayList<Customer> getOverdue();
	
}
