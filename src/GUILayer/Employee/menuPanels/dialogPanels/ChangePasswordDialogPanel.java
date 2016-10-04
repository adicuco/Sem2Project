package GUILayer.Employee.menuPanels.dialogPanels;

import javax.swing.JDialog;

import ModelLayer.Employee;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

import ControlLayer.EmployeeCtr;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class ChangePasswordDialogPanel extends JDialog{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Employee employee;
	private final JLabel lblNewPassword = new JLabel("New Password");
	private final JLabel lblConfirmPassword = new JLabel("Confirm Password");
	private final JPasswordField textNewPassword = new JPasswordField();
	private final JPasswordField textConfirm = new JPasswordField();
	private final JButton btnCancel = new JButton("Cancel");
	private final JButton btnSave = new JButton("Save");
	
	public ChangePasswordDialogPanel(Employee employee) {
		this.employee = employee;	
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
		
		textConfirm.setColumns(10);
		textConfirm.setFont(new Font("SansSerif", Font.BOLD, 15));
		textNewPassword.setColumns(10);
		textNewPassword.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblNewPassword.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblConfirmPassword.setFont(new Font("SansSerif", Font.BOLD, 20));
		
		btnCancel.setFont(new Font("SansSerif", Font.BOLD, 20));
		btnSave.setFont(new Font("SansSerif", Font.BOLD, 20));
		
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(72)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblConfirmPassword)
						.addComponent(lblNewPassword)
						.addComponent(btnSave, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 0, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(30)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(textNewPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(textConfirm, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(98)
							.addComponent(btnCancel)))
					.addGap(113))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(40)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewPassword)
						.addComponent(textNewPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblConfirmPassword)
						.addComponent(textConfirm, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 82, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCancel)
						.addComponent(btnSave))
					.addGap(58))
		);
		getContentPane().setLayout(groupLayout);
	}

	private void createEvents(){
		
		textNewPassword.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				textNewPassword.setFocusable(true);
				textConfirm.setFocusable(false);
				textConfirm.setBackground(null);
				textNewPassword.setBackground(Color.LIGHT_GRAY);
			}
		});

		textConfirm.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				textConfirm.setFocusable(true);
				textNewPassword.setFocusable(false);
				textNewPassword.setBackground(null);
				textConfirm.setBackground(Color.LIGHT_GRAY);
				
			}
		});		
		
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		btnSave.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				EmployeeCtr empCtr = new EmployeeCtr();
				if (textNewPassword.getText().equals(textConfirm.getText())) {
					try {
						empCtr.updateEmployee("password", textNewPassword.getText(), employee.getEmployeeId());
						JOptionPane.showMessageDialog(null, "Password successfully updated");
						dispose();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(null, "The password does not mtach");

				}
			}
		});
			}
		
	}
	
	
	
