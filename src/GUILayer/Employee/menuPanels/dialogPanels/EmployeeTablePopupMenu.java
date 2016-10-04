package GUILayer.Employee.menuPanels.dialogPanels;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JSeparator;
import javax.swing.JTable;

import ControlLayer.EmployeeCtr;

public class EmployeeTablePopupMenu extends JPopupMenu{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JTable table;
	private JMenuItem details = new JMenuItem("Details");
	private JMenuItem resetPass = new JMenuItem("Reset password");
	private JMenuItem signOut = new JMenuItem("Sign out employee");
		
	
	public EmployeeTablePopupMenu (JTable table){
		
		this.table = table;
		initialize();
		createEvents();
		
	}
	
	private void initialize(){

		details.setFont(new Font("SansSerif", Font.BOLD, 15));
		resetPass.setFont(new Font("SansSerif", Font.BOLD, 15));
		signOut.setFont(new Font("SansSerif", Font.BOLD, 15));
		add(details);
		add(new JSeparator());
		add(resetPass);
		add(new JSeparator());
		add(signOut);
		setVisible(true);
	}
	
	private void createEvents(){
			
		details.addActionListener(new ActionListener() {	
			public void actionPerformed(ActionEvent e) {
				int employeeId = 0;
				employeeId = (int) table.getValueAt(table.getSelectedRow(), 0);
				new EditEmployeeDialogPanel(employeeId);
			}
		});
		
		
		resetPass.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				EmployeeCtr empCtr = new EmployeeCtr();
				int employeeId = 0;
				employeeId = (int) table.getValueAt(table.getSelectedRow(), 0);
				try {
					empCtr.updateEmployee("password", empCtr.findEmployee(employeeId).getCpr().substring(6), employeeId);
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
			}
		});
		
		signOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EmployeeCtr empCtr = new EmployeeCtr();
				int employeeId = 0;
				employeeId = (int) table.getValueAt(table.getSelectedRow(), 0);
				try {
					empCtr.updateEmployee("loggedIn", "false", employeeId);
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
			}
		});
		
	}
	
}
