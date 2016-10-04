package GUILayer.Employee.menuPanels;

import javax.swing.JPanel;

import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;

import ControlLayer.CustomerCtr;
import ControlLayer.PaymentCtr;
import GUILayer.Customer.menuPanels.TimeClass;
import GUILayer.Employee.menuPanels.dialogPanels.RegisterPaymentDilogPanel;
import ModelLayer.Customer;
import ModelLayer.Payment;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PaymentPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JLabel lblPaymentMenu = new JLabel("PAYMENT   MENU");
	private final JLabel lblDate = new JLabel(TimeClass.getDate());
	private final JPanel pnlRegisterPayment = new JPanel();
	private final JPanel pnlUpcomingPayments = new JPanel();
	private final JPanel pnlGetCustomer = new JPanel();
	private final JLabel lblCustomerId = new JLabel("Customer ID");
	private final JLabel lblCustomerCpr = new JLabel("Customer CPR");
	private final JLabel lblCustomerName = new JLabel("Customer Name");
	private final JButton btnSearch = new JButton("Search");
	private final JButton btnClear = new JButton("Clear");
	private final JTextField txtCustomerId = new JTextField();
	private final JTextField txtCustomerName = new JTextField();
	private final JTextField txtCustomerCpr = new JTextField();
	private final JScrollPane scrollPane = new JScrollPane();
	private final JTable tblGetCus = new JTable();
	private DefaultTableModel model;
	private DefaultTableModel modelPay;
	/**
	 * @wbp.nonvisual location=365,559
	 */
	private final JButton btnOverdue = new JButton("Overdue");
	private final JDateChooser dateChooserFrom = new JDateChooser();
	private final JDateChooser dateChooserTill = new JDateChooser();
	private final JButton btnRegisterPayment = new JButton("Register Payment");
	private final JScrollPane pnlPayments = new JScrollPane();
	private final JTable tblPayments = new JTable();
	private final JLabel lblFrom = new JLabel("From");
	private final JLabel lblTill = new JLabel("Till");
	private final JLabel lblCustomerIdPayment = new JLabel("Customer ID");
	private final JTextField txtCustomerIDPayment = new JTextField();
	private final JButton btnClearPayments = new JButton("Clear");
	private final JButton btnFindPayments = new JButton("Find Payments");
	private final JLabel lblSelectDateRange = new JLabel("Select date range");
	private final JLabel lblPaymentID = new JLabel("Payment ID");
	private final JTextField txtPaymentID = new JTextField();
	private final JPanel panel = new JPanel();
	private final JPanel panel_1 = new JPanel();

	public PaymentPanel() {

		initialize();
		createEvents();
	}

	private void initialize() {

		setVisible(true);
		BorderFactory.createEmptyBorder(1, 1, 1, 1);

		txtCustomerCpr.setColumns(10);
		txtCustomerName.setColumns(10);
		txtCustomerId.setColumns(10);
		txtCustomerCpr.setFont(new Font("SansSerif", Font.BOLD, 20));
		txtCustomerId.setFont(new Font("SansSerif", Font.BOLD, 20));
		txtCustomerName.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblCustomerCpr.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblCustomerId.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblCustomerName.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblDate.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblPaymentMenu.setFont(new Font("SansSerif", Font.BOLD, 30));
		btnClear.setFont(new Font("SansSerif", Font.BOLD, 20));
		btnSearch.setFont(new Font("SansSerif", Font.BOLD, 20));
		btnRegisterPayment.setFont(new Font("SansSerif", Font.BOLD, 20));
		btnRegisterPayment.setVisible(false);
		btnOverdue.setFont(new Font("SansSerif", Font.BOLD, 20));
		model = new DefaultTableModel(new Object[][] {},
				new String[] { "Id", "First Name", "Last Name", "CPR", "Pay Day", "Balance" });

		tblGetCus.setModel(model);
		tblGetCus.setFont(new Font("SansSerif", Font.PLAIN, 15));
		tblGetCus.setRowHeight(35);

		modelPay = new DefaultTableModel(new Object[][] {},
				new String[] { "Id", "Customer ID", "Customer Name", "Amount", "Date" });
		tblPayments.setModel(modelPay);
		tblPayments.setFont(new Font("SansSerif", Font.PLAIN, 15));
		tblPayments.setRowHeight(35);

		txtCustomerCpr.setTransferHandler(null);
		txtCustomerId.setTransferHandler(null);
		txtCustomerName.setTransferHandler(null);

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(346)
					.addComponent(lblPaymentMenu, GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
					.addGap(128)
					.addComponent(lblDate, GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
					.addGap(17))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(pnlUpcomingPayments, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 988, Short.MAX_VALUE)
						.addComponent(pnlRegisterPayment, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
					.addGap(2))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(21)
							.addComponent(lblPaymentMenu))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblDate)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(pnlRegisterPayment, GroupLayout.PREFERRED_SIZE, 395, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(pnlUpcomingPayments, GroupLayout.PREFERRED_SIZE, 274, GroupLayout.PREFERRED_SIZE)
					.addGap(27))
		);
		pnlRegisterPayment.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		pnlUpcomingPayments.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));

		pnlPayments.setViewportView(tblPayments);
		lblCustomerIdPayment.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblPaymentID.setFont(new Font("SansSerif", Font.BOLD, 20));
		txtCustomerIDPayment.setFont(new Font("SansSerif", Font.BOLD, 20));
		txtCustomerIDPayment.setColumns(10);
		txtCustomerIDPayment.setTransferHandler(null);
		txtPaymentID.setFont(new Font("SansSerif", Font.BOLD, 20));
		txtPaymentID.setColumns(10);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap(90, Short.MAX_VALUE)
							.addComponent(lblCustomerIdPayment)
							.addGap(18))
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap(99, Short.MAX_VALUE)
							.addComponent(lblPaymentID)
							.addGap(19)))
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(txtPaymentID, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtCustomerIDPayment, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE))
					.addGap(22))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(11)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtCustomerIDPayment, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCustomerIdPayment))
					.addGap(6)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtPaymentID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPaymentID)))
		);
		panel.setLayout(gl_panel);
		lblSelectDateRange.setFont(new Font("SansSerif", Font.BOLD, 20));
		dateChooserFrom.setFont(new Font("SansSerif", Font.BOLD, 15));
		dateChooserFrom.setDateFormatString("yyyy-MM-dd");
		dateChooserFrom.setTransferHandler(null);
		dateChooserTill.setFont(new Font("SansSerif", Font.BOLD, 15));
		dateChooserTill.setDateFormatString("yyyy-MM-dd");
		dateChooserTill.setTransferHandler(null);
		btnClearPayments.setFont(new Font("SansSerif", Font.BOLD, 20));
		
				btnFindPayments.setFont(new Font("SansSerif", Font.BOLD, 20));
				lblTill.setFont(new Font("SansSerif", Font.BOLD, 20));
				lblFrom.setFont(new Font("SansSerif", Font.BOLD, 20));
				GroupLayout gl_panel_1 = new GroupLayout(panel_1);
				gl_panel_1.setHorizontalGroup(
					gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
							.addGap(59)
							.addComponent(lblFrom, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
							.addGap(38)
							.addComponent(dateChooserFrom, GroupLayout.PREFERRED_SIZE, 227, GroupLayout.PREFERRED_SIZE)
							.addGap(18))
						.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
							.addGap(59)
							.addComponent(lblTill, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
							.addGap(38)
							.addComponent(dateChooserTill, GroupLayout.PREFERRED_SIZE, 227, GroupLayout.PREFERRED_SIZE)
							.addGap(18))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(34)
							.addComponent(btnClearPayments, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
							.addGap(34)
							.addComponent(btnFindPayments, GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
							.addGap(18))
						.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
							.addContainerGap(146, Short.MAX_VALUE)
							.addComponent(lblSelectDateRange, GroupLayout.PREFERRED_SIZE, 188, GroupLayout.PREFERRED_SIZE)
							.addGap(87))
				);
				gl_panel_1.setVerticalGroup(
					gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblSelectDateRange, GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(lblFrom)
								.addComponent(dateChooserFrom, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
							.addGap(7)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(lblTill)
								.addComponent(dateChooserTill, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
							.addGap(21)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(btnClearPayments)
								.addComponent(btnFindPayments))
							.addGap(12))
				);
				panel_1.setLayout(gl_panel_1);
				GroupLayout gl_pnlUpcomingPayments = new GroupLayout(pnlUpcomingPayments);
				gl_pnlUpcomingPayments.setHorizontalGroup(
					gl_pnlUpcomingPayments.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlUpcomingPayments.createSequentialGroup()
							.addGap(10)
							.addComponent(pnlPayments, GroupLayout.DEFAULT_SIZE, 506, Short.MAX_VALUE)
							.addGap(39)
							.addGroup(gl_pnlUpcomingPayments.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_pnlUpcomingPayments.createSequentialGroup()
									.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 421, Short.MAX_VALUE)
									.addGap(1))
								.addComponent(panel, GroupLayout.DEFAULT_SIZE, 422, Short.MAX_VALUE))
							.addGap(9))
				);
				gl_pnlUpcomingPayments.setVerticalGroup(
					gl_pnlUpcomingPayments.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlUpcomingPayments.createSequentialGroup()
							.addGap(12)
							.addComponent(pnlPayments, GroupLayout.PREFERRED_SIZE, 251, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_pnlUpcomingPayments.createSequentialGroup()
							.addComponent(panel, GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
							.addGap(181))
						.addGroup(gl_pnlUpcomingPayments.createSequentialGroup()
							.addGap(91)
							.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 170, Short.MAX_VALUE)
							.addContainerGap())
				);
				pnlUpcomingPayments.setLayout(gl_pnlUpcomingPayments);
		GroupLayout gl_pnlRegisterPayment = new GroupLayout(pnlRegisterPayment);
		gl_pnlRegisterPayment.setHorizontalGroup(
			gl_pnlRegisterPayment.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlRegisterPayment.createSequentialGroup()
					.addGroup(gl_pnlRegisterPayment.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_pnlRegisterPayment.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnOverdue, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 624, Short.MAX_VALUE)
							.addComponent(btnRegisterPayment))
						.addComponent(pnlGetCustomer, GroupLayout.DEFAULT_SIZE, 978, Short.MAX_VALUE)
						.addGroup(gl_pnlRegisterPayment.createSequentialGroup()
							.addContainerGap()
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 968, Short.MAX_VALUE)))
					.addGap(8))
		);
		gl_pnlRegisterPayment.setVerticalGroup(
			gl_pnlRegisterPayment.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlRegisterPayment.createSequentialGroup()
					.addComponent(pnlGetCustomer, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_pnlRegisterPayment.createParallelGroup(Alignment.LEADING)
						.addComponent(btnRegisterPayment)
						.addComponent(btnOverdue))
					.addGap(5))
		);

		scrollPane.setViewportView(tblGetCus);
		GroupLayout gl_pnlGetCustomer = new GroupLayout(pnlGetCustomer);
		gl_pnlGetCustomer.setHorizontalGroup(
			gl_pnlGetCustomer.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlGetCustomer.createSequentialGroup()
					.addGap(59)
					.addGroup(gl_pnlGetCustomer.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(gl_pnlGetCustomer.createSequentialGroup()
							.addComponent(lblCustomerName)
							.addGap(18))
						.addGroup(gl_pnlGetCustomer.createSequentialGroup()
							.addComponent(lblCustomerId)
							.addGap(35)))
					.addGroup(gl_pnlGetCustomer.createParallelGroup(Alignment.LEADING)
						.addComponent(txtCustomerId)
						.addComponent(txtCustomerName))
					.addGap(161)
					.addGroup(gl_pnlGetCustomer.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_pnlGetCustomer.createSequentialGroup()
							.addComponent(lblCustomerCpr)
							.addGap(18)
							.addComponent(txtCustomerCpr))
						.addGroup(gl_pnlGetCustomer.createSequentialGroup()
							.addGap(66)
							.addComponent(btnSearch, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(71)
							.addComponent(btnClear, GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)))
					.addGap(80))
		);
		gl_pnlGetCustomer.setVerticalGroup(
			gl_pnlGetCustomer.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlGetCustomer.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pnlGetCustomer.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlGetCustomer.createSequentialGroup()
							.addGroup(gl_pnlGetCustomer.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtCustomerCpr, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblCustomerCpr))
							.addGap(18)
							.addGroup(gl_pnlGetCustomer.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnClear)
								.addComponent(btnSearch)))
						.addGroup(gl_pnlGetCustomer.createSequentialGroup()
							.addGroup(gl_pnlGetCustomer.createParallelGroup(Alignment.LEADING)
								.addComponent(txtCustomerId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblCustomerId))
							.addGap(18)
							.addGroup(gl_pnlGetCustomer.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtCustomerName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblCustomerName))))
					.addContainerGap(22, Short.MAX_VALUE))
		);
		pnlGetCustomer.setLayout(gl_pnlGetCustomer);
		pnlRegisterPayment.setLayout(gl_pnlRegisterPayment);
		setLayout(groupLayout);
	}

	private void createEvents() {

		tblGetCus.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				payDay(Integer.parseInt(tblGetCus.getValueAt(tblGetCus.getSelectedRow(), 0).toString()));
			}
		});

		txtCustomerId.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if ((!Character.isDigit(c)) && !(c == KeyEvent.VK_BACK_SPACE)) {
					e.consume();
				}
			}
		});

		txtCustomerIDPayment.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if ((!Character.isDigit(c)) && !(c == KeyEvent.VK_BACK_SPACE)) {
					e.consume();
				}
			}
		});

		txtPaymentID.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if ((!Character.isDigit(c)) && !(c == KeyEvent.VK_BACK_SPACE)) {
					e.consume();
				}
			}
		});

		txtCustomerCpr.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();

				if ((txtCustomerCpr.getText().length() == 6) && !(c == KeyEvent.VK_BACK_SPACE)) {
					txtCustomerCpr.setText(txtCustomerCpr.getText() + "-");
				}
				if (!Character.isDigit(c)) {
					e.consume();
				}
				if (txtCustomerCpr.getText().length() > 10) {
					e.consume();
				}
				if (!(Character.isDigit(c)) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE)) {
					e.consume();
				}
			}
		});

		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model = new DefaultTableModel(new Object[][] {},
						new String[] { "Id", "First Name", "Last Name", "CPR", "Pay Day", "Balance" });
				tblGetCus.setModel(model);
				model.setRowCount(0);
				CustomerCtr cusCtr = new CustomerCtr();
				if (txtCustomerId.getText().equals("")) {
					if (txtCustomerCpr.getText().equals("")) {
						ArrayList<Customer> cus = cusCtr.findAllCustomers(txtCustomerName.getText());
						if (!cus.isEmpty()) {
							for (Customer c : cus) {
								model.addRow(new Object[] { c.getCustomerId(), c.getfName(), c.getlName(), c.getCpr(),
										c.getPayDay(), c.getAmountToPay() });
							}
						}

					} else {
						ArrayList<Customer> cus = new ArrayList<Customer>();
						try {
							cus = cusCtr.findAllCustomers("cpr", txtCustomerCpr.getText());

						} catch (NullPointerException e1) {

							e1.printStackTrace();
						}
						if (!cus.isEmpty()) {
							for (Customer c : cus) {
								model.addRow(new Object[] { c.getCustomerId(), c.getfName(), c.getlName(), c.getCpr(),
										c.getPayDay(), c.getAmountToPay() });
							}
						}
					}

				} else {

					Customer c = new Customer();
					try {
						c = cusCtr.findCustomer(Integer.parseInt(txtCustomerId.getText()));

					} catch (NullPointerException e1) {
						e1.printStackTrace();
					}
					model.addRow(new Object[] { c.getCustomerId(), c.getfName(), c.getlName(), c.getCpr(),
							c.getPayDay(), c.getAmountToPay() });
				}

			}
		});

		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnRegisterPayment.setVisible(false);
				txtCustomerName.setText("");
				txtCustomerCpr.setText("");
				txtCustomerId.setText("");
				tblGetCus.setModel(new DefaultTableModel(new Object[][] {},
						new String[] { "Id", "First Name", "Last Name", "CPR", "Pay Day", "Balance" }));
			}
		});

		btnOverdue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model = new DefaultTableModel(new Object[][] {}, new String[] { "Id", "First Name", "Last Name", "CPR",
						"Pay Day", "Balance", "Email", "Phone" });
				tblGetCus.setModel(model);
				model.setRowCount(0);
				CustomerCtr cusCtr = new CustomerCtr();
				ArrayList<Customer> cus = cusCtr.getOverDue();
				for (Customer c : cus) {
					model.addRow(new Object[] { c.getCustomerId(), c.getfName(), c.getlName(), c.getCpr(),
							c.getPayDay(), c.getAmountToPay(), c.getEmail(), c.getPhone() });
				}
			}
		});

		btnRegisterPayment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!(tblGetCus.getSelectedRow() == -1)) {
					new RegisterPaymentDilogPanel(
							Integer.parseInt(tblGetCus.getValueAt(tblGetCus.getSelectedRow(), 0).toString()));
				}
			}
		});

		btnClearPayments.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtCustomerIDPayment.setText("");
				txtPaymentID.setText("");
				tblPayments.setModel(new DefaultTableModel(new Object[][] {},
						new String[] { "Id", "Customer ID", "Customer Name", "Amount", "Date" }));
				dateChooserFrom.setDate(null);
				dateChooserTill.setDate(null);

			}
		});

		btnFindPayments.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PaymentCtr payCtr = new PaymentCtr();

				modelPay = new DefaultTableModel(new Object[][] {},
						new String[] { "Id", "Customer ID", "Customer Name", "Amount", "Date" });
				tblPayments.setModel(modelPay);
				modelPay.setRowCount(0);
				if (txtCustomerIDPayment.getText().equals("")) {
					if (txtPaymentID.getText().equals("")) {
						if ((dateChooserFrom.getDate() == null) || (dateChooserTill.getDate() == null)) {
							ArrayList<Payment> pay = payCtr.findAllPayments();
							for (Payment p : pay) {
								modelPay.addRow(new Object[] { p.getPaymentId(), p.getCustomer().getCustomerId(),
										p.getCustomer().getfName() + " " + p.getCustomer().getlName(), p.getAmount(),
										p.getDate().toString() });
							}
						} else {
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
							Timestamp dateF = null;
							Timestamp dateT = null;
							dateF = Timestamp.valueOf(sdf.format(dateChooserFrom.getDate()) + " 00:00:00");
							dateT = Timestamp.valueOf(sdf.format(dateChooserTill.getDate()) + " 00:00:00");
							ArrayList<Payment> pay = payCtr.getBetween(dateF, dateT);
							for (Payment p : pay) {
								modelPay.addRow(new Object[] { p.getPaymentId(), p.getCustomer().getCustomerId(),
										p.getCustomer().getfName() + " " + p.getCustomer().getlName(), p.getAmount(),
										p.getDate().toString() });
							}
						}
					} else {

						Payment pay = payCtr.findPayment(Integer.parseInt(txtPaymentID.getText()));
						modelPay.addRow(new Object[] { pay.getPaymentId(), pay.getCustomer().getCustomerId(),
								pay.getCustomer().getfName() + " " + pay.getCustomer().getlName(), pay.getAmount(),
								pay.getDate() });
					}
				} else {
					Payment pay = payCtr.findAllPayments("customerId", txtCustomerIDPayment.getText()).get(0);
					modelPay.addRow(new Object[] { pay.getPaymentId(), pay.getCustomer().getCustomerId(),
							pay.getCustomer().getfName() + " " + pay.getCustomer().getlName(), pay.getAmount(),
							pay.getDate().toString() });
				}

			}
		});
	}

	private void payDay(int customerId) {
		CustomerCtr cusCtr = new CustomerCtr();
		Customer customer = cusCtr.findCustomer(customerId);

		if (customer.getPayDay() == null) {
			btnRegisterPayment.setVisible(true);
		} else {
			Date date = new Date();
			Timestamp payDay = customer.getPayDay();
			Timestamp today = new Timestamp(date.getTime());
			long x = (payDay.getTime() - today.getTime()) / 86400000;
			if (x <= 7) {
				btnRegisterPayment.setVisible(true);
			} else
				btnRegisterPayment.setVisible(false);
		}
	}
}
