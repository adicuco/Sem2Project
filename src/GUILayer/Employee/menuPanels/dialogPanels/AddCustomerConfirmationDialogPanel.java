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

import ControlLayer.CustomerCtr;

import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class AddCustomerConfirmationDialogPanel extends JDialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JButton btnOk = new JButton("OK");
	private final JLabel lblCustomer = new JLabel("Customer successfully added");
	private final JLabel lblCustomerId = new JLabel("Customer ID");
	private final JLabel lblCustomerPassword = new JLabel("Customer Password");
	private final JTextField txtId = new JTextField();
	private final JTextField txtPassword = new JTextField();
	private String cpr;
	
	
	public AddCustomerConfirmationDialogPanel (String cpr){
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
		CustomerCtr cusCtr = new CustomerCtr();
		
		txtPassword.setEnabled(false);
		txtPassword.setEditable(false);
		txtPassword.setColumns(10);
		txtId.setEnabled(false);
		txtId.setEditable(false);
		txtId.setColumns(10);
		lblCustomer.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblCustomerId.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblCustomerPassword.setFont(new Font("SansSerif", Font.BOLD, 20));
		txtId.setFont(new Font("SansSerif", Font.BOLD, 15));
		txtPassword.setFont(new Font("SansSerif", Font.BOLD, 15));
		
		
		btnOk.setFont(new Font("SansSerif", Font.BOLD, 20));
		
		txtId.setText(String.valueOf(cusCtr.findAllCustomers("cpr", cpr).get(0).getCustomerId()));
		txtPassword.setText(cusCtr.findAllCustomers("cpr", cpr).get(0).getPassword());
		
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(52)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblCustomerPassword)
						.addComponent(lblCustomerId))
					.addPreferredGap(ComponentPlacement.RELATED, 6, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(txtPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(59))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(78, Short.MAX_VALUE)
					.addComponent(lblCustomer, GroupLayout.PREFERRED_SIZE, 289, GroupLayout.PREFERRED_SIZE)
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
					.addComponent(lblCustomer)
					.addPreferredGap(ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCustomerId)
						.addComponent(txtId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(28)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCustomerPassword)
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
