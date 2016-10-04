package GUILayer.Customer.menuPanels;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Locale;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import GUILayer.Customer.menuPanels.dialogPanels.ChangeMembershipDialogPanel;
import GUILayer.Customer.menuPanels.dialogPanels.ChangePasswordDialogPanel;
import ModelLayer.Customer;
import javax.swing.JTextArea;

public class ProfilePanel extends JPanel {
	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;
	private JLabel lblDate = new JLabel(TimeClass.getDate());
	private JLabel lblFirstName = new JLabel("First Name");
	private JLabel lblLastName = new JLabel("Last Name");
	private JLabel lblCpr = new JLabel("CPR");
	private JLabel lblAddress = new JLabel("Address");
	private JLabel lblCity = new JLabel("City");
	private JLabel lblZip = new JLabel("Zip");
	private JLabel lblPhone = new JLabel("Phone");
	private JLabel lblEmail = new JLabel("Email");
	private JLabel lblMambership = new JLabel("Membership");
	private JButton changeMembership = new JButton("Change Membership");
	private Customer customer;
	private JButton ChangePassword = new JButton("Change Password");
	private final JLabel lblAmountToPay = new JLabel("Amount to Pay");
	private final JLabel lblPayDay = new JLabel("Pay Day");
	private final JTextField customerAmount = new JTextField((String) null);
	private final JTextField customerPayDay = new JTextField((String) null);
	private final JPanel pnlBtns = new JPanel();
	private final JPanel pnlName = new JPanel();
	private final JPanel pnlContact = new JPanel();
	private final JPanel pnlMembership = new JPanel();
	private final JPanel pnlAddress = new JPanel();
	private JTextField customerMembership;
	private final JTextArea txtPayCheck = new JTextArea();

	/**
	 * Create the panel.
	 */
	public ProfilePanel(Customer customer) {
		this.customer = customer;
		initialize();
		initializeText();
		createEvents();

	}

	@SuppressWarnings("deprecation")
	private void membershipDay() {
		if (customer.getPayDay() == null) {
			changeMembership.setVisible(true);
		} else {
			Calendar calendar = Calendar.getInstance();
			int today = calendar.get(Calendar.DATE);
			int payDay = customer.getPayDay().getDate();

			int x = payDay - today;
			if ((x > 0) && (x <= 4)) {
				changeMembership.setVisible(true);
			} else
				changeMembership.setVisible(false);
		}
	}

	private void initialize() {

		setSize(new Dimension(1117, 765));
		membershipDay();
		lblDate.setFont(new Font("SansSerif", Font.BOLD, 20));
		ChangePassword.setFont(new Font("SansSerif", Font.BOLD, 25));
		changeMembership.setFont(new Font("SansSerif", Font.BOLD, 25));
		GroupLayout gl_pnlBtns = new GroupLayout(pnlBtns);
		gl_pnlBtns
				.setHorizontalGroup(gl_pnlBtns.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_pnlBtns.createSequentialGroup().addContainerGap()
								.addComponent(ChangePassword, GroupLayout.PREFERRED_SIZE, 258,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED, 563, Short.MAX_VALUE)
								.addComponent(changeMembership).addContainerGap()));
		gl_pnlBtns.setVerticalGroup(gl_pnlBtns.createParallelGroup(Alignment.LEADING).addGroup(gl_pnlBtns
				.createSequentialGroup()
				.addGroup(gl_pnlBtns.createParallelGroup(Alignment.BASELINE)
						.addComponent(changeMembership, GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
						.addComponent(ChangePassword, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE))
				.addGap(9)));
		pnlBtns.setLayout(gl_pnlBtns);
		JTextField customerLName = new JTextField(customer.getlName());
		customerLName.setFont(new Font("SansSerif", Font.BOLD, 22));
		customerLName.setEnabled(false);
		customerLName.setEditable(false);
		lblLastName.setFont(new Font("SansSerif", Font.BOLD, 25));
		JTextField customerFName = new JTextField(customer.getfName());
		customerFName.setFont(new Font("SansSerif", Font.BOLD, 22));
		customerFName.setEnabled(false);
		customerFName.setEditable(false);
		lblFirstName.setFont(new Font("SansSerif", Font.BOLD, 25));
		lblCpr.setFont(new Font("SansSerif", Font.BOLD, 25));
		JTextField cutomerCpr = new JTextField(customer.getCpr());
		cutomerCpr.setFont(new Font("SansSerif", Font.BOLD, 22));
		cutomerCpr.setEnabled(false);
		cutomerCpr.setEditable(false);
		GroupLayout gl_pnlName = new GroupLayout(pnlName);
		gl_pnlName.setHorizontalGroup(gl_pnlName.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlName.createSequentialGroup()
						.addGroup(gl_pnlName.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lblCpr, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addComponent(lblFirstName).addComponent(lblLastName))
				.addGap(110)
				.addGroup(gl_pnlName.createParallelGroup(Alignment.TRAILING)
						.addComponent(customerFName, GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)
						.addComponent(cutomerCpr, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)
						.addComponent(customerLName, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE))
				.addContainerGap()));
		gl_pnlName.setVerticalGroup(gl_pnlName.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlName.createSequentialGroup()
						.addGroup(gl_pnlName.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_pnlName.createSequentialGroup().addGap(15).addComponent(lblCpr).addGap(24)
										.addComponent(lblFirstName).addGap(12).addComponent(lblLastName))
						.addGroup(gl_pnlName.createSequentialGroup()
								.addGroup(gl_pnlName.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_pnlName.createSequentialGroup().addGap(63).addComponent(
												customerFName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))
										.addComponent(cutomerCpr, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED).addComponent(customerLName,
										GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)))
						.addContainerGap(7, Short.MAX_VALUE)));
		pnlName.setLayout(gl_pnlName);
		lblEmail.setFont(new Font("SansSerif", Font.BOLD, 25));
		lblPhone.setFont(new Font("SansSerif", Font.BOLD, 25));
		JTextField customerEmail = new JTextField(customer.getEmail());
		customerEmail.setFont(new Font("SansSerif", Font.BOLD, 22));
		customerEmail.setEnabled(false);
		customerEmail.setEditable(false);
		JTextField customerPhone = new JTextField(customer.getPhone());
		customerPhone.setFont(new Font("SansSerif", Font.BOLD, 22));
		customerPhone.setEnabled(false);
		customerPhone.setEditable(false);
		GroupLayout gl_pnlContact = new GroupLayout(pnlContact);
		gl_pnlContact
				.setHorizontalGroup(gl_pnlContact.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlContact.createSequentialGroup()
								.addGroup(gl_pnlContact.createParallelGroup(Alignment.LEADING)
										.addComponent(lblPhone, GroupLayout.PREFERRED_SIZE, 87,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblEmail))
						.addGap(154)
						.addGroup(gl_pnlContact.createParallelGroup(Alignment.TRAILING).addComponent(customerPhone)
								.addComponent(customerEmail, GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE))
				.addContainerGap()));
		gl_pnlContact
				.setVerticalGroup(gl_pnlContact.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlContact.createSequentialGroup().addContainerGap(39, Short.MAX_VALUE)
								.addComponent(lblPhone).addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(lblEmail).addGap(33))
						.addGroup(gl_pnlContact.createSequentialGroup().addGap(24)
								.addComponent(customerPhone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(customerEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addContainerGap(24, Short.MAX_VALUE)));
		pnlContact.setLayout(gl_pnlContact);
		lblMambership.setFont(new Font("SansSerif", Font.BOLD, 25));
		customerMembership = new JTextField(customer.getMembership().getName());
		customerMembership.setFont(new Font("SansSerif", Font.BOLD, 22));
		customerMembership.setEnabled(false);
		customerMembership.setEditable(false);
		customerAmount.setFont(new Font("SansSerif", Font.BOLD, 22));
		customerAmount.setEnabled(false);
		customerAmount.setEditable(false);
		lblAmountToPay.setFont(new Font("SansSerif", Font.BOLD, 25));
		lblPayDay.setFont(new Font("SansSerif", Font.BOLD, 25));
		customerPayDay.setFont(new Font("SansSerif", Font.BOLD, 22));
		customerPayDay.setEnabled(false);
		customerPayDay.setEditable(false);
		GroupLayout gl_pnlMembership = new GroupLayout(pnlMembership);
		gl_pnlMembership.setHorizontalGroup(
			gl_pnlMembership.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlMembership.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pnlMembership.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlMembership.createSequentialGroup()
							.addGroup(gl_pnlMembership.createParallelGroup(Alignment.LEADING)
								.addComponent(lblMambership)
								.addComponent(lblAmountToPay)
								.addComponent(lblPayDay))
							.addPreferredGap(ComponentPlacement.RELATED, 78, Short.MAX_VALUE)
							.addGroup(gl_pnlMembership.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(customerAmount, Alignment.LEADING)
								.addComponent(customerPayDay, Alignment.LEADING)
								.addComponent(customerMembership, GroupLayout.PREFERRED_SIZE, 244, GroupLayout.PREFERRED_SIZE))
							.addGap(19))
						.addGroup(gl_pnlMembership.createSequentialGroup()
							.addComponent(txtPayCheck, GroupLayout.DEFAULT_SIZE, 507, Short.MAX_VALUE)
							.addGap(6)))
					.addGap(0))
		);
		gl_pnlMembership.setVerticalGroup(
			gl_pnlMembership.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlMembership.createSequentialGroup()
					.addGroup(gl_pnlMembership.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlMembership.createSequentialGroup()
							.addGap(16)
							.addComponent(lblMambership)
							.addGap(18)
							.addComponent(lblAmountToPay)
							.addGap(18)
							.addComponent(lblPayDay))
						.addGroup(gl_pnlMembership.createSequentialGroup()
							.addContainerGap()
							.addComponent(customerMembership, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(customerAmount, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(customerPayDay, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
					.addComponent(txtPayCheck, GroupLayout.PREFERRED_SIZE, 374, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		txtPayCheck.setFont(new Font("SansSerif", Font.BOLD, 22));
		pnlMembership.setLayout(gl_pnlMembership);
		JTextField customerCity = new JTextField(customer.getCity());
		customerCity.setFont(new Font("SansSerif", Font.BOLD, 22));
		customerCity.setEnabled(false);
		customerCity.setEditable(false);
		lblCity.setFont(new Font("SansSerif", Font.BOLD, 25));
		JTextField customerZip = new JTextField(customer.getZip());
		customerZip.setFont(new Font("SansSerif", Font.BOLD, 22));
		customerZip.setEnabled(false);
		customerZip.setEditable(false);
		lblZip.setFont(new Font("SansSerif", Font.BOLD, 25));
		JTextField customerAddress = new JTextField(customer.getAddress());
		customerAddress.setFont(new Font("SansSerif", Font.BOLD, 22));
		customerAddress.setEnabled(false);
		customerAddress.setEditable(false);
		lblAddress.setFont(new Font("SansSerif", Font.BOLD, 25));
		GroupLayout gl_pnlAddress = new GroupLayout(pnlAddress);
		gl_pnlAddress.setHorizontalGroup(gl_pnlAddress.createParallelGroup(Alignment.TRAILING).addGroup(gl_pnlAddress
				.createSequentialGroup()
				.addGroup(gl_pnlAddress.createParallelGroup(Alignment.LEADING).addComponent(lblAddress)
						.addGroup(gl_pnlAddress.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(lblZip, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
										GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblCity, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 47,
										Short.MAX_VALUE)))
				.addGap(139)
				.addGroup(gl_pnlAddress.createParallelGroup(Alignment.LEADING)
						.addComponent(customerZip, GroupLayout.DEFAULT_SIZE, 295, Short.MAX_VALUE)
						.addComponent(customerAddress, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 295,
								Short.MAX_VALUE)
						.addComponent(customerCity, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 304,
								GroupLayout.PREFERRED_SIZE))
				.addContainerGap()));
		gl_pnlAddress.setVerticalGroup(gl_pnlAddress.createParallelGroup(Alignment.TRAILING).addGroup(gl_pnlAddress
				.createSequentialGroup()
				.addGroup(gl_pnlAddress.createParallelGroup(Alignment.BASELINE).addComponent(lblAddress).addComponent(
						customerAddress, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_pnlAddress.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlAddress.createSequentialGroup()
								.addComponent(customerZip, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(customerCity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_pnlAddress.createSequentialGroup().addComponent(lblZip).addGap(18)
								.addComponent(lblCity)))
				.addContainerGap()));
		pnlAddress.setLayout(gl_pnlAddress);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(pnlBtns, GroupLayout.DEFAULT_SIZE, 1105, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(24)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(pnlAddress, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(pnlContact, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(pnlName, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(pnlMembership, GroupLayout.PREFERRED_SIZE, 519, Short.MAX_VALUE)
							.addGap(14))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap(858, Short.MAX_VALUE)
							.addComponent(lblDate)))
					.addGap(2))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblDate)
					.addGap(46)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(pnlName, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(pnlContact, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(pnlAddress, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE))
						.addComponent(pnlMembership, GroupLayout.PREFERRED_SIZE, 556, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(pnlBtns, GroupLayout.PREFERRED_SIZE, 104, Short.MAX_VALUE)
					.addContainerGap())
		);
		setLayout(groupLayout);

		txtPayCheck.setWrapStyleWord(true);
		txtPayCheck.setLineWrap(true);
		txtPayCheck.setEnabled(false);
		txtPayCheck.setEditable(false);
	}

	private void createEvents() {
		changeMembership.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ChangeMembershipDialogPanel(customer);
				customerMembership.setText(customer.getMembership().getName());
			}
		});

		ChangePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ChangePasswordDialogPanel(customer);
			}
		});
	}

	@SuppressWarnings("deprecation")
	private void initializeText() {
		Timestamp payDay = customer.getPayDay();
		String payDayString = "No pay day";
		if (payDay != null) {
			payDayString = new java.text.SimpleDateFormat("d MMMMM yyyy", Locale.ENGLISH).format(payDay);
		}
		customerAmount.setText(String.valueOf(customer.getAmountToPay()));
		customerPayDay.setText(String.valueOf(payDayString));

		if (customer.isPayCheck()) {
			txtPayCheck.setText(
					"You have payed the membership fee for this month.\nThank you and enjoy your time!\n\n- Alma Spa team");
		} else {
			if (customer.getPayDay() != null) {
				Timestamp now = Timestamp.valueOf(LocalDateTime.now());
				int days = customer.getPayDay().getDay() - now.getDay();
				txtPayCheck.setText(
						"You have not payed the membership fee this month.\nPlease make the payment at the reception.\nYou have "
								+ days
								+ " day(s) left or your account will be disabled.\nHave a nice day!\n\n- Alma Spa team");
			} else {
				txtPayCheck.setText(
						"Thank you for using Alma Spa's facilites!\nConsider becoming a member and get great discounts on your future bookings.\nHave a nice day!\n\n- Alma Spa team");
			}
		}
	}
}
