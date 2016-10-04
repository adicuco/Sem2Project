package GUILayer.Employee.menuPanels.dialogPanels;

import javax.swing.JDialog;

import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;

import ControlLayer.CustomerCtr;
import ControlLayer.PaymentCtr;
import ModelLayer.Customer;

import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.awt.event.ActionEvent;

public class RegisterPaymentDilogPanel extends JDialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int customerId;
	private final JLabel lblConfirmPaymentRegistration = new JLabel("Confirm Payment Registration");
	private final JLabel lblCustomerName = new JLabel("Customer Name");
	private final JLabel lblCustomerId = new JLabel("Customer ID");
	private final JLabel lblAmount = new JLabel("Amount");
	private final JLabel lblCsutomerCpr = new JLabel("Customer CPR");
	private final JButton btnRegisterPayment = new JButton("Register Payment");
	private final JButton btnCancel = new JButton("Cancel");
	private final JTextField txtId = new JTextField();
	private final JTextField txtName = new JTextField();
	private final JTextField txtCpr = new JTextField();
	private final JTextField txtAmount = new JTextField();
	
	public RegisterPaymentDilogPanel(int customerId){
		
		this.customerId = customerId;
		initialize();
		createEvents();	
		getRootPane().setBorder(BorderFactory.createEtchedBorder(Color.BLACK, Color.GRAY));
	//	setUndecorated(true);
		pack();
		setLocationRelativeTo(null);
		setModal(true);
		setVisible(true);
	}
	private void initialize() {
		CustomerCtr cusCtr = new CustomerCtr();
		Customer cus = cusCtr.findCustomer(customerId);
		
		
		txtAmount.setColumns(10);
		txtCpr.setColumns(10);
		txtName.setColumns(10);
		txtId.setColumns(10);
		lblConfirmPaymentRegistration.setFont(new Font("SansSerif", Font.BOLD, 30));
		lblCustomerId.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblCsutomerCpr.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblCustomerName.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblAmount.setFont(new Font("SansSerif", Font.BOLD, 20));
		txtAmount.setFont(new Font("SansSerif", Font.BOLD, 20));
		txtCpr.setFont(new Font("SansSerif", Font.BOLD, 20));
		txtId.setFont(new Font("SansSerif", Font.BOLD, 20));
		txtName.setFont(new Font("SansSerif", Font.BOLD, 20));
		
		btnCancel.setFont(new Font("SansSerif", Font.BOLD, 20));
		
		btnRegisterPayment.setFont(new Font("SansSerif", Font.BOLD, 20));
		txtAmount.setEditable(false);
		txtAmount.setEnabled(false);
		txtCpr.setEditable(false);
		txtCpr.setEnabled(false);
		txtId.setEditable(false);
		txtId.setEnabled(false);
		txtName.setEditable(false);
		txtName.setEnabled(false);
		
		txtId.setText(String.valueOf(customerId));
		txtName.setText(cus.getfName() + " " + cus.getlName());
		txtCpr.setText(cus.getCpr());
		txtAmount.setText(String.valueOf(cus.getAmountToPay()));
		
		
		
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(38)
					.addComponent(btnRegisterPayment)
					.addPreferredGap(ComponentPlacement.RELATED, 108, Short.MAX_VALUE)
					.addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 199, GroupLayout.PREFERRED_SIZE)
					.addGap(32))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(84)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblAmount)
						.addComponent(lblCsutomerCpr)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblCustomerId)
								.addComponent(lblCustomerName))
							.addGap(52)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(txtId, GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
								.addComponent(txtName)
								.addComponent(txtCpr)
								.addComponent(txtAmount))))
					.addContainerGap(113, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(70)
					.addComponent(lblConfirmPaymentRegistration)
					.addContainerGap(77, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(22)
					.addComponent(lblConfirmPaymentRegistration)
					.addPreferredGap(ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCustomerId)
						.addComponent(txtId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCustomerName)
						.addComponent(txtName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblCsutomerCpr)
						.addComponent(txtCpr, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(12)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAmount)
						.addComponent(txtAmount, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnRegisterPayment)
						.addComponent(btnCancel))
					.addGap(21))
		);
		getContentPane().setLayout(groupLayout);
	}
	
	private void createEvents(){
		
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		btnRegisterPayment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CustomerCtr cusCtr = new CustomerCtr();
				Customer cus = cusCtr.findCustomer(customerId);
				PaymentCtr payCtr = new PaymentCtr();
				Calendar date = Calendar.getInstance();
				date.setTimeInMillis(cus.getPayDay().getTime());
				date.add(Calendar.MONTH, 1);
				Timestamp newPayDay = new Timestamp(date.getTimeInMillis());
				cus.setAmountToPay(0);
				cus.setPayDay(newPayDay);
				cusCtr.updateCustomer(cus);
				
				try {
					payCtr.insertNew(cus);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					
				}
				dispose();
				try {
					JOptionPane.showMessageDialog(null, "Payment Registred" + "\n" + "Payment id: " + payCtr.getMaxId(customerId));
				} catch (HeadlessException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		
	}
	
}
