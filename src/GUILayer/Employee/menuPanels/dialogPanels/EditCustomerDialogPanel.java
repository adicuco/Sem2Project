package GUILayer.Employee.menuPanels.dialogPanels;

import javax.swing.JDialog;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;

import ControlLayer.CustomerCtr;
import ControlLayer.MembershipCtr;
import ModelLayer.Active;
import ModelLayer.Customer;
import ModelLayer.Membership;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;

public class EditCustomerDialogPanel extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel pnlButtons = new JPanel();
	private final JButton btnCancel = new JButton("Cancel");
	private final JButton btnSave = new JButton("Save");
	private final JLabel lblFirstName = new JLabel("First Name");
	private final JLabel lblLastName = new JLabel("Last Name");
	private final JLabel lblCpr = new JLabel("CPR");
	private final JLabel lblAddress = new JLabel("Address");
	private final JLabel lblCity = new JLabel("City");
	private final JLabel lblZip = new JLabel("Zip");
	private final JLabel lblEmail = new JLabel("Email");
	private final JLabel lblPhone = new JLabel("Phone");
	private final JLabel lblMembership = new JLabel("Membership");
	private final JTextField txtFName = new JTextField();
	private final JTextField txtCPR = new JTextField();
	private final JTextField txtAddress = new JTextField();
	private final JTextField txtEmail = new JTextField();
	private final JTextField txtLName = new JTextField();
	private final JTextField txtCity = new JTextField();
	private final JTextField txtZip = new JTextField();
	private final JTextField txtPhone = new JTextField();
	private final JComboBox<String> comboMembership = new JComboBox<String>();
	private ArrayList<Membership> ms;
	private final JButton btnEdit = new JButton("Edit");
	private int customerId;
	private final JLabel lblActive = new JLabel("Active State");
	private final JLabel lblPayCheck = new JLabel("Paid Status");
	private final JTextField txtPayCheck = new JTextField();
	private final JComboBox<Active> comboActive = new JComboBox<Active>();
	private final JLabel lblAmountPay = new JLabel("Amount to pay");
	private final JTextField txtAmountPay = new JTextField();

	public EditCustomerDialogPanel(int customerId) {
		txtAmountPay.setText((String) null);
		txtAmountPay.setFont(new Font("SansSerif", Font.BOLD, 15));
		txtAmountPay.setEnabled(false);
		txtAmountPay.setEditable(false);
		txtAmountPay.setColumns(10);
		lblAmountPay.setFont(new Font("SansSerif", Font.BOLD, 20));
		this.customerId = customerId;
		initialize();
		createEvents();
		fillCombo();
		getRootPane().setBorder(BorderFactory.createEtchedBorder(Color.BLACK, Color.GRAY));
		// setUndecorated(true);
		pack();
		setLocationRelativeTo(null);
		setModal(true);
		setVisible(true);
	}

	private void initialize() {
		Customer cus = new Customer();
		CustomerCtr cusCtr = new CustomerCtr();
		cus = cusCtr.findCustomer(customerId);
		txtPhone.setEnabled(false);
		txtPhone.setEditable(false);
		txtPhone.setColumns(10);
		txtZip.setEnabled(false);
		txtZip.setEditable(false);
		txtZip.setColumns(10);
		txtCity.setEnabled(false);
		txtCity.setEditable(false);
		txtCity.setColumns(10);
		txtLName.setEnabled(false);
		txtLName.setEditable(false);
		txtLName.setColumns(10);
		txtEmail.setEnabled(false);
		txtEmail.setEditable(false);
		txtEmail.setColumns(10);
		txtAddress.setEnabled(false);
		txtAddress.setEditable(false);
		txtAddress.setColumns(10);
		txtCPR.setEnabled(false);
		txtCPR.setEditable(false);
		txtCPR.setColumns(10);
		txtFName.setEnabled(false);
		txtFName.setEditable(false);
		txtFName.setColumns(10);
		txtFName.setFont(new Font("SansSerif", Font.BOLD, 15));
		txtLName.setFont(new Font("SansSerif", Font.BOLD, 15));
		txtAddress.setFont(new Font("SansSerif", Font.BOLD, 15));
		txtCity.setFont(new Font("SansSerif", Font.BOLD, 15));
		txtZip.setFont(new Font("SansSerif", Font.BOLD, 15));
		txtCPR.setFont(new Font("SansSerif", Font.BOLD, 15));
		txtPhone.setFont(new Font("SansSerif", Font.BOLD, 15));
		txtEmail.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblFirstName.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblLastName.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblAddress.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblCity.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblZip.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblCpr.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblEmail.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblPhone.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblMembership.setFont(new Font("SansSerif", Font.BOLD, 20));
		comboMembership.setEnabled(false);
		comboMembership.setFont(new Font("SansSerif", Font.BOLD, 15));
		comboActive.setEnabled(false);
		comboActive.setFont(new Font("SansSerif", Font.BOLD, 15));
		txtPayCheck.setEnabled(false);
		txtPayCheck.setEditable(false);
		txtPayCheck.setFont(new Font("SansSerif", Font.BOLD, 15));
		txtPayCheck.setColumns(10);
		lblPayCheck.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblActive.setFont(new Font("SansSerif", Font.BOLD, 20));
		btnSave.setFont(new Font("SansSerif", Font.BOLD, 20));
		btnSave.setVisible(false);
		btnCancel.setFont(new Font("SansSerif", Font.BOLD, 20));
		btnEdit.setFont(new Font("SansSerif", Font.BOLD, 20));

		txtFName.setText(cus.getfName());
		txtLName.setText(cus.getlName());
		txtCPR.setText(cus.getCpr());
		txtAddress.setText(cus.getAddress());
		txtCity.setText(cus.getCity());
		txtZip.setText(cus.getZip());
		txtEmail.setText(cus.getEmail());
		txtPhone.setText(cus.getPhone());
		txtAmountPay.setText(String.valueOf(cus.getAmountToPay()));
		if (cus.isPayCheck()) {
			txtPayCheck.setText("Paid");
		} else {
			txtPayCheck.setText("Overdue");
		}
		;

		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addGap(27)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
						.createSequentialGroup()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
										.addComponent(lblAmountPay, GroupLayout.PREFERRED_SIZE, 147,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(txtAmountPay, GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE))
								.addGroup(groupLayout.createSequentialGroup()
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(lblAddress).addComponent(lblEmail).addComponent(lblCpr))
										.addGap(18)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(txtAddress, GroupLayout.DEFAULT_SIZE, 231,
														Short.MAX_VALUE)
												.addGroup(groupLayout.createSequentialGroup().addGap(56)
														.addComponent(comboMembership, 0, 175, Short.MAX_VALUE))
												.addComponent(txtCPR, GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
												.addComponent(txtEmail, GroupLayout.DEFAULT_SIZE, 231,
														Short.MAX_VALUE)))
								.addGroup(groupLayout.createSequentialGroup().addComponent(lblFirstName)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(txtFName,
												GroupLayout.PREFERRED_SIZE, 221, GroupLayout.PREFERRED_SIZE)))
						.addGap(91)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false).addGroup(groupLayout
								.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addGroup(groupLayout.createSequentialGroup().addComponent(lblLastName)
												.addPreferredGap(ComponentPlacement.UNRELATED))
										.addComponent(lblPhone).addComponent(lblActive).addComponent(lblPayCheck))
								.addGap(50))
								.addGroup(groupLayout.createSequentialGroup()
										.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
												.addComponent(lblCity).addComponent(lblZip))
										.addGap(67))))
						.addGroup(groupLayout.createSequentialGroup().addComponent(lblMembership).addGap(465)))
				.addGroup(
						groupLayout.createParallelGroup(Alignment.LEADING, false).addComponent(txtPhone)
								.addComponent(txtCity, GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
								.addComponent(txtLName, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 186,
										Short.MAX_VALUE)
								.addComponent(txtZip, Alignment.TRAILING)
								.addComponent(txtPayCheck, GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
								.addComponent(comboActive, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGap(121)).addComponent(pnlButtons, GroupLayout.DEFAULT_SIZE, 915, Short.MAX_VALUE));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup().addGap(30)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblFirstName)
								.addComponent(txtFName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
						.addComponent(txtLName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE).addComponent(lblLastName))
				.addGap(38)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblCpr)
						.addComponent(txtCPR, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(txtCity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCity))
				.addGap(34)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblAddress)
						.addComponent(txtZip, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(txtAddress, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(lblZip))
				.addPreferredGap(ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblEmail)
						.addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(txtPhone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPhone))
				.addGap(28)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblMembership)
						.addComponent(comboMembership, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(lblActive, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboActive, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(txtPayCheck, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPayCheck, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblAmountPay, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtAmountPay, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
				.addGap(21).addComponent(pnlButtons, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)));
		GroupLayout gl_pnlButtons = new GroupLayout(pnlButtons);
		gl_pnlButtons
				.setHorizontalGroup(
						gl_pnlButtons.createParallelGroup(Alignment.TRAILING)
								.addGroup(
										gl_pnlButtons.createSequentialGroup().addGap(48)
												.addComponent(btnSave, GroupLayout.PREFERRED_SIZE, 143,
														GroupLayout.PREFERRED_SIZE)
										.addGap(140)
										.addComponent(btnEdit, GroupLayout.PREFERRED_SIZE, 196,
												GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED, 165, Short.MAX_VALUE)
				.addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE).addGap(49)));
		gl_pnlButtons
				.setVerticalGroup(
						gl_pnlButtons.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_pnlButtons.createSequentialGroup().addContainerGap(24, Short.MAX_VALUE)
										.addGroup(gl_pnlButtons.createParallelGroup(Alignment.BASELINE)
												.addComponent(btnSave).addComponent(btnCancel).addComponent(btnEdit))
						.addGap(32)));
		pnlButtons.setLayout(gl_pnlButtons);
		getContentPane().setLayout(groupLayout);
	}

	private void createEvents() {

		txtZip.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if ((!Character.isDigit(c)) && !(c == KeyEvent.VK_BACK_SPACE)) {
					e.consume();
				}
			}
		});

		txtPhone.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if ((!Character.isDigit(c)) && !(c == KeyEvent.VK_BACK_SPACE)) {
					e.consume();
				}
			}
		});

		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtFName.setEnabled(true);
				txtFName.setEditable(true);
				txtLName.setEnabled(true);
				txtLName.setEditable(true);
				txtAddress.setEnabled(true);
				txtAddress.setEditable(true);
				txtCity.setEnabled(true);
				txtCity.setEditable(true);
				txtZip.setEnabled(true);
				txtZip.setEditable(true);
				txtEmail.setEnabled(true);
				txtEmail.setEditable(true);
				txtPhone.setEnabled(true);
				txtPhone.setEditable(true);
				comboMembership.setEnabled(true);
				comboActive.setEnabled(true);
				btnSave.setVisible(true);
				btnEdit.setVisible(false);

			}
		});

		btnSave.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (validateEmail(txtEmail.getText())) {
					CustomerCtr cusCtr = new CustomerCtr();
					try {
						cusCtr.updateCustomer(customerId, txtFName.getText(), txtLName.getText(), txtAddress.getText(),
								txtCity.getText(), txtZip.getText(), txtPhone.getText(), txtEmail.getText(),
								txtCPR.getText(), cusCtr.findCustomer(customerId).isPayCheck(),
								cusCtr.findCustomer(customerId).getPayDay(),
								cusCtr.findCustomer(customerId).getAccessLvl().toString(),
								comboActive.getSelectedItem().toString(), comboMembership.getSelectedItem().toString(),
								cusCtr.findCustomer(customerId).getPassword(),
								cusCtr.findCustomer(customerId).getAmountToPay());
					} catch (Exception e1) {
						System.out.println(e1);
						e1.printStackTrace();
					}

					JOptionPane.showMessageDialog(null, "Customer Updated");
					btnEdit.doClick();
				} else {
					JOptionPane.showMessageDialog(null, "Invalid email format \nPlease correct the email address");
				}
			}
		});
	}

	private void fillCombo() {

		comboMembership.setModel(new DefaultComboBoxModel<String>());
		CustomerCtr cusCtr = new CustomerCtr();
		MembershipCtr ctr = new MembershipCtr();
		ms = ctr.findAllMemberships();
		for (Membership m : ms) {
			comboMembership.addItem(m.getName());
		}
		comboMembership.setSelectedItem(cusCtr.findCustomer(customerId).getMembership().toString());

		comboActive.setModel(new DefaultComboBoxModel<>(Active.values()));
	}

	public boolean validateEmail(String email) {
		boolean valid = false;
		String emailPatern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		Pattern pattern = Pattern.compile(emailPatern);
		Matcher matcher = pattern.matcher(email);
		if (matcher.matches()) {
			valid = true;
		} else {
			valid = false;
		}
		return valid;
	}
}
