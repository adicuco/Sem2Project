/**
 * 
 */
package DBLayer;

import java.sql.SQLException;
import java.util.ArrayList;

import ModelLayer.Employee;

/**
 * @author Eugen Bobolea, Dalibor Branny, Adrian Cucolas, Catalin Rat
 *
 */
public interface IFDBEmployee {

	public ArrayList<Employee> getAllEmployees();

	public ArrayList<Employee> getAllEmployees(String name);

	public ArrayList<Employee> getAllEmployees(String wClause, String var);

	public Employee findEmployee(int employeeId);

	public Employee findEmployee(String name);

	public int insertEmployee(Employee emp) throws Exception;
	
	public int updateEmployee(Employee emp);
	
	public void updateEmployee1(String whatToUpdate, String newValue, int employeeId) throws SQLException ;
	
	public void resetPassword(int employeeId) throws SQLException;

}
