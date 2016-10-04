package GUILayer.Employee.menuPanels.dialogPanels;

import javax.swing.JDialog;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;

import ControlLayer.EmployeeCtr;

import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class AddEmployeeConfirmationDialogPanel extends JDialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JButton btnOk = new JButton("OK");
	private final JLabel lblEmployee = new JLabel("Employee successfully added");
	private final JLabel lblEmployeeId = new JLabel("Employee ID");
	private final JLabel lblEmployeePassword = new JLabel("Employee Password");
	private final JTextField txtId = new JTextField();
	private final JTextField txtPassword = new JTextField();
	private String cpr;
	
	
	public AddEmployeeConfirmationDialogPanel (String cpr){
		this.cpr = cpr;		
		initialize();
		createEvents();
		getRootPane().setBorder(BorderFactory.createEtchedBorder(Color.BLACK, Color.GRAY));
		setUndecorated(true);
		pack();
		setLocationRelativeTo(null);
		setModal(true);
		setVisible(true);
	}
	private void initialize() {
		EmployeeCtr empCtr = new EmployeeCtr();
		
		txtPassword.setEnabled(false);
		txtPassword.setEditable(false);
		txtPassword.setColumns(10);
		txtId.setEnabled(false);
		txtId.setEditable(false);
		txtId.setColumns(10);
		lblEmployee.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblEmployeeId.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblEmployeePassword.setFont(new Font("SansSerif", Font.BOLD, 20));
		txtId.setFont(new Font("SansSerif", Font.BOLD, 15));
		txtPassword.setFont(new Font("SansSerif", Font.BOLD, 15));
		
		
		btnOk.setFont(new Font("SansSerif", Font.BOLD, 20));
		
		txtId.setText(String.valueOf(empCtr.findAllEmployees("cpr", cpr).get(0).getEmployeeId()));
		txtPassword.setText(empCtr.findAllEmployees("cpr", cpr).get(0).getPassword());
		
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(52)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblEmployeePassword)
						.addComponent(lblEmployeeId))
					.addPreferredGap(ComponentPlacement.RELATED, 6, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(txtPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(59))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(78, Short.MAX_VALUE)
					.addComponent(lblEmployee, GroupLayout.PREFERRED_SIZE, 289, GroupLayout.PREFERRED_SIZE)
					.addGap(67))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(174, Short.MAX_VALUE)
					.addComponent(btnOk, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
					.addGap(171))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblEmployee)
					.addPreferredGap(ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEmployeeId)
						.addComponent(txtId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(28)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEmployeePassword)
						.addComponent(txtPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(35)
					.addComponent(btnOk, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
					.addGap(24))
		);
		getContentPane().setLayout(groupLayout);
	}
	
	private void createEvents(){
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}
}
