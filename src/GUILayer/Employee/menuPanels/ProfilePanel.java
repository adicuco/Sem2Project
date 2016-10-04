package GUILayer.Employee.menuPanels;

import javax.swing.JPanel;

import ModelLayer.Employee;

import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import GUILayer.Customer.menuPanels.TimeClass;
import GUILayer.Employee.menuPanels.dialogPanels.ChangePasswordDialogPanel;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;


import ControlLayer.EmployeeCtr;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ProfilePanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Employee employee;
	private final JLabel lblDate = new JLabel(TimeClass.getDate());
	private final JLabel lblFirstName = new JLabel("First Name");
	private final JLabel lblLastName = new JLabel("Last Name");
	private final JTextField textFirstName = new JTextField();
	private final JTextField textLastName = new JTextField();
	private final JLabel lblAddress = new JLabel("Address");
	private final JLabel lblCity = new JLabel("City");
	private final JLabel lblZip = new JLabel("Zip");
	private final JLabel lblEmail = new JLabel("Email");
	private final JLabel lblPhone = new JLabel("Phone");
	private final JLabel lblCpr = new JLabel("CPR");
	private final JTextField textCPR = new JTextField();
	private final JTextField textAddress = new JTextField();
	private final JTextField textCity = new JTextField();
	private final JTextField textZip = new JTextField();
	private final JTextField textEmail = new JTextField();
	private final JTextField textPhone = new JTextField();
	private final JButton btnUpdateProfile = new JButton("Update Profile");
	private final JButton btnChangePassword = new JButton("Change Password");
	private final JPanel panel = new JPanel();
	private final JButton btnSave = new JButton("Save");
	private final JButton btnCancel = new JButton("Cancel");
	private final JLabel lblProfileMenu = new JLabel("PROFILE    MENU");
	
	
	
	public ProfilePanel(Employee employee){
	this.employee = employee;
		initialize();
		createEvents();
	}
	
	
	
	
	private void initialize() {
		setVisible(true);
		BorderFactory.createEmptyBorder(1, 1, 1, 1);
		//setSize(new Dimension(950, 750));
		lblDate.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblProfileMenu.setFont(new Font("SansSerif", Font.BOLD, 30));
		lblFirstName.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblLastName.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblAddress.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblCity.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblZip.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblCpr.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblEmail.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblPhone.setFont(new Font("SansSerif", Font.BOLD, 20));
		textPhone.setEnabled(false);
		textPhone.setEditable(false);
		textPhone.setFont(new Font("SansSerif", Font.BOLD, 20));
		textPhone.setColumns(10);
		textEmail.setEnabled(false);
		textEmail.setEditable(false);
		textEmail.setFont(new Font("SansSerif", Font.BOLD, 20));
		textEmail.setColumns(10);
		textZip.setEnabled(false);
		textZip.setEditable(false);
		textZip.setFont(new Font("SansSerif", Font.BOLD, 20));
		textZip.setColumns(10);
		textCity.setEnabled(false);
		textCity.setEditable(false);
		textCity.setFont(new Font("SansSerif", Font.BOLD, 20));
		textCity.setColumns(10);
		textAddress.setEnabled(false);
		textAddress.setEditable(false);
		textAddress.setFont(new Font("SansSerif", Font.BOLD, 20));
		textAddress.setColumns(10);
		textCPR.setEnabled(false);
		textCPR.setEditable(false);
		textCPR.setFont(new Font("SansSerif", Font.BOLD, 20));
		textCPR.setColumns(10);
		textLastName.setEnabled(false);
		textLastName.setEditable(false);
		textLastName.setFont(new Font("SansSerif", Font.BOLD, 20));
		textLastName.setColumns(10);
		textFirstName.setEnabled(false);
		textFirstName.setEditable(false);
		textFirstName.setFont(new Font("SansSerif", Font.BOLD, 20));
		textFirstName.setColumns(10);
		
		btnUpdateProfile.setFont(new Font("SansSerif", Font.BOLD, 20));
		btnChangePassword.setFont(new Font("SansSerif", Font.BOLD, 20));
		btnCancel.setFont(new Font("SansSerif", Font.BOLD, 20));
		btnCancel.setVisible(false);
		btnSave.setFont(new Font("SansSerif", Font.BOLD, 20));
		btnSave.setVisible(false);
		
		textFirstName.setText(employee.getfName());
		textLastName.setText(employee.getlName());
		textAddress.setText(employee.getAddress());
		textCity.setText(employee.getCity());
		textZip.setText(employee.getZip());
		textCPR.setText(employee.getCpr());
		textEmail.setText(employee.getEmail());
		textPhone.setText(employee.getPhone());
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 1050, Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(383)
					.addComponent(lblProfileMenu, GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
					.addGap(174)
					.addComponent(lblDate, GroupLayout.PREFERRED_SIZE, 232, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(43)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblFirstName)
								.addComponent(lblCpr))
							.addGap(27)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(textFirstName)
								.addComponent(textCPR, GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE))
							.addGap(77)
							.addComponent(lblLastName)
							.addGap(58)
							.addComponent(textLastName, GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
							.addGap(148))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblAddress)
								.addComponent(lblEmail))
							.addGap(51)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(textEmail, GroupLayout.DEFAULT_SIZE, 385, Short.MAX_VALUE)
									.addGap(48)
									.addComponent(lblPhone)
									.addGap(18)
									.addComponent(textPhone, 357, 357, 357))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(textAddress, GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE)
									.addGap(54)
									.addComponent(lblCity)
									.addGap(40)
									.addComponent(textCity, GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
									.addGap(47)
									.addComponent(lblZip)
									.addGap(18)
									.addComponent(textZip, GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)))
							.addContainerGap())))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblDate))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(24)
							.addComponent(lblProfileMenu)))
					.addGap(132)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblFirstName)
						.addComponent(textFirstName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblLastName)
						.addComponent(textLastName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(56)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCpr)
						.addComponent(textCPR, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(61)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAddress)
						.addComponent(textAddress, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textCity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCity)
						.addComponent(lblZip)
						.addComponent(textZip, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(87)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEmail)
						.addComponent(textEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPhone)
						.addComponent(textPhone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 134, Short.MAX_VALUE)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(64)
					.addComponent(btnCancel, GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
					.addGap(88)
					.addComponent(btnUpdateProfile, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(94)
					.addComponent(btnChangePassword, GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
					.addGap(70)
					.addComponent(btnSave, GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
					.addGap(64))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(19)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSave)
						.addComponent(btnCancel)
						.addComponent(btnUpdateProfile)
						.addComponent(btnChangePassword))
					.addContainerGap(24, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		setLayout(groupLayout);
		
	}
	
	private void createEvents(){
		btnChangePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ChangePasswordDialogPanel(employee);
			}
		});
		
		btnUpdateProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFirstName.setEnabled(true);
				textFirstName.setEditable(true);
				textLastName.setEnabled(true);
				textLastName.setEditable(true);
				textAddress.setEnabled(true);
				textAddress.setEditable(true);
				textCity.setEnabled(true);
				textCity.setEditable(true);
				textZip.setEnabled(true);
				textZip.setEditable(true);
				textEmail.setEnabled(true);
				textEmail.setEditable(true);
				textPhone.setEnabled(true);
				textPhone.setEditable(true);
				btnUpdateProfile.setVisible(false);
				btnChangePassword.setVisible(false);
				btnCancel.setVisible(true);
				btnSave.setVisible(true);
			}
		});
		
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFirstName.setEnabled(false);
				textFirstName.setEditable(false);
				textLastName.setEnabled(false);
				textLastName.setEditable(false);
				textAddress.setEnabled(false);
				textAddress.setEditable(false);
				textCity.setEnabled(false);
				textCity.setEditable(false);
				textZip.setEnabled(false);
				textZip.setEditable(false);
				textEmail.setEnabled(false);
				textEmail.setEditable(false);
				textPhone.setEnabled(false);
				textPhone.setEditable(false);
				btnUpdateProfile.setVisible(true);
				btnChangePassword.setVisible(true);
				btnCancel.setVisible(false);
				btnSave.setVisible(false);
			}
		});
		
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EmployeeCtr empCtr = new EmployeeCtr();
				employee.setfName(textFirstName.getText());
				employee.setlName(textLastName.getText());
				employee.setAddress(textAddress.getText());
				employee.setCity(textCity.getText());
				employee.setZip(textZip.getText());
				employee.setEmail(textEmail.getText());
				employee.setPhone(textPhone.getText());
				empCtr.updateEmployee(employee.getEmployeeId(), textFirstName.getText(), textLastName.getText(), textAddress.getText(), textCity.getText(),
						textZip.getText(), textPhone.getText(), textEmail.getText(), employee.getCpr(), employee.getAccessLvl().toString(), employee.getActive().toString(), employee.getPassword());
			
				JOptionPane.showMessageDialog(null, "Profile updated!");
				btnCancel.doClick();
			}
		});
	}
}
