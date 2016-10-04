package GUILayer.Employee.menuPanels;

import javax.swing.JPanel;

import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import GUILayer.Customer.menuPanels.TimeClass;
import GUILayer.Employee.menuPanels.dialogPanels.AddCustomerDialogPanel;
import GUILayer.Employee.menuPanels.dialogPanels.CustomerTablePopupMenu;
import ModelLayer.Customer;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import ControlLayer.CustomerCtr;

import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CustomerPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JLabel lblDate = new JLabel(TimeClass.getDate());
	private final JLabel lblCustomerMenu = new JLabel("CUSTOMER   MENU");
	private final JPanel pnlFindCus = new JPanel();
	private final JLabel lblCustomerId = new JLabel("Customer ID");
	private final JLabel lblCustomerName = new JLabel("Customer Name");
	private final JLabel lblCustomerCpr = new JLabel("Customer CPR");
	private final JButton btnFind = new JButton("Search");
	private final JTextField txtId = new JTextField();
	private final JTextField txtName = new JTextField();
	private final JTextField txtCpr = new JTextField();
	private final JSeparator separator = new JSeparator();
	private final JPanel pnlTable = new JPanel();
	private final JTable cusromerList = new JTable();
	private final JScrollPane scrollPane = new JScrollPane();
	private DefaultTableModel model;
	private final JButton btnAddNewCustomer = new JButton("Add New Customer");
	private final JButton btnClear = new JButton("Clear");
	private CustomerTablePopupMenu popMenu;

	public CustomerPanel() {

		createEvents();
		initialize();
	}

	private void initialize() {

		setVisible(true);
		BorderFactory.createEmptyBorder(1, 1, 1, 1);

		lblDate.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblCustomerMenu.setFont(new Font("SansSerif", Font.BOLD, 30));
		lblCustomerId.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblCustomerCpr.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblCustomerName.setFont(new Font("SansSerif", Font.BOLD, 20));
		txtCpr.setColumns(10);
		txtCpr.setFont(new Font("SansSerif", Font.BOLD, 20));
		txtName.setColumns(10);
		txtName.setFont(new Font("SansSerif", Font.BOLD, 20));
		txtId.setColumns(10);
		txtId.setFont(new Font("SansSerif", Font.BOLD, 20));
		txtId.setTransferHandler(null);
		txtCpr.setTransferHandler(null);
		btnFind.setFont(new Font("SansSerif", Font.BOLD, 20));
		btnClear.setFont(new Font("SansSerif", Font.BOLD, 20));
		btnAddNewCustomer.setFont(new Font("SansSerif", Font.BOLD, 20));
		model = new DefaultTableModel(new Object[][] {},
				new String[] { "Id", "First Name", "Last Name", "CPR", "Phone", "Email" });
		cusromerList.setModel(model);
		cusromerList.setFont(new Font("SansSerif", Font.PLAIN, 20));
		cusromerList.setRowHeight(35);
		cusromerList.setComponentPopupMenu(popMenu);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(346)
					.addComponent(lblCustomerMenu, GroupLayout.DEFAULT_SIZE, 355, Short.MAX_VALUE)
					.addGap(154)
					.addComponent(lblDate, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE)
					.addGap(20))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(1)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(pnlTable, 0, 0, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(2)
							.addComponent(pnlFindCus, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
					.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(141)
					.addComponent(btnClear, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 352, Short.MAX_VALUE)
					.addComponent(btnAddNewCustomer)
					.addGap(208))
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addGap(35)
					.addComponent(separator, GroupLayout.DEFAULT_SIZE, 946, Short.MAX_VALUE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(23)
							.addComponent(lblCustomerMenu))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblDate)))
					.addGap(18)
					.addComponent(pnlFindCus, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(pnlTable, GroupLayout.PREFERRED_SIZE, 400, GroupLayout.PREFERRED_SIZE)
					.addGap(41)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAddNewCustomer)
						.addComponent(btnClear))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		GroupLayout gl_pnlTable = new GroupLayout(pnlTable);
		gl_pnlTable.setHorizontalGroup(
			gl_pnlTable.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlTable.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 973, Short.MAX_VALUE)
					.addGap(8))
		);
		gl_pnlTable.setVerticalGroup(
			gl_pnlTable.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlTable.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 389, Short.MAX_VALUE))
		);
		scrollPane.setViewportView(cusromerList);
		pnlTable.setLayout(gl_pnlTable);
		GroupLayout gl_pnlFindCus = new GroupLayout(pnlFindCus);
		gl_pnlFindCus.setHorizontalGroup(
			gl_pnlFindCus.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlFindCus.createSequentialGroup()
					.addGap(19)
					.addGroup(gl_pnlFindCus.createParallelGroup(Alignment.LEADING)
						.addComponent(lblCustomerName, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(gl_pnlFindCus.createSequentialGroup()
							.addComponent(lblCustomerId, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(34)))
					.addGap(29)
					.addGroup(gl_pnlFindCus.createParallelGroup(Alignment.LEADING)
						.addComponent(txtId)
						.addComponent(txtName, GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE))
					.addGap(114)
					.addComponent(lblCustomerCpr, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE)
					.addGap(28)
					.addGroup(gl_pnlFindCus.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnFind, GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
						.addComponent(txtCpr, GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE))
					.addGap(95))
		);
		gl_pnlFindCus.setVerticalGroup(
			gl_pnlFindCus.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlFindCus.createSequentialGroup()
					.addGap(21)
					.addGroup(gl_pnlFindCus.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlFindCus.createSequentialGroup()
							.addGroup(gl_pnlFindCus.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblCustomerCpr)
								.addComponent(txtCpr, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(85))
						.addGroup(gl_pnlFindCus.createSequentialGroup()
							.addGroup(gl_pnlFindCus.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblCustomerId)
								.addComponent(txtId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(45)
							.addGroup(gl_pnlFindCus.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblCustomerName, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnFind))))
					.addContainerGap(21, Short.MAX_VALUE))
		);
		pnlFindCus.setLayout(gl_pnlFindCus);
		setLayout(groupLayout);
	}

	private void createEvents() {

		txtId.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if ((!Character.isDigit(c)) && !(c == KeyEvent.VK_BACK_SPACE)) {
					e.consume();
				}
			}
		});

		txtCpr.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();

				if ((txtCpr.getText().length() == 6) && !(c == KeyEvent.VK_BACK_SPACE)) {
					txtCpr.setText(txtCpr.getText() + "-");
				}
				if (!Character.isDigit(c)) {
					e.consume();
				}
				if (txtCpr.getText().length() > 10) {
					e.consume();
				}
				if (!(Character.isDigit(c)) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE)) {
					e.consume();
				}
			}
		});

		btnFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model = new DefaultTableModel(new Object[][] {},
						new String[] { "Id", "First Name", "Last Name", "CPR", "Phone", "Email" });
				cusromerList.setModel(model);
				model.setRowCount(0);
				CustomerCtr cusCtr = new CustomerCtr();
				if (txtId.getText().equals("")) {
					if (txtCpr.getText().equals("")) {
						ArrayList<Customer> cus = cusCtr.findAllCustomers(txtName.getText());
						if (!cus.isEmpty()) {
							for (Customer c : cus) {
								model.addRow(new Object[] { c.getCustomerId(), c.getfName(), c.getlName(), c.getCpr(),
										c.getPhone(), c.getEmail() });
							}
						}

					} else {
						ArrayList<Customer> cus = new ArrayList<Customer>();
						try {
							cus = cusCtr.findAllCustomers("cpr", txtCpr.getText());

						} catch (NullPointerException e1) {

							e1.printStackTrace();
						}
						if (!cus.isEmpty()) {
							for (Customer c : cus) {
								model.addRow(new Object[] { c.getCustomerId(), c.getfName(), c.getlName(), c.getCpr(),
										c.getPhone(), c.getEmail() });
							}
						}
					}

				} else {

					Customer c = new Customer();
					try {
						c = cusCtr.findCustomer(Integer.parseInt(txtId.getText()));

					} catch (NullPointerException e1) {
						e1.printStackTrace();
					}
					model.addRow(new Object[] { c.getCustomerId(), c.getfName(), c.getlName(), c.getCpr(), c.getPhone(),
							c.getEmail() });
				}

			}
		});

		btnAddNewCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AddCustomerDialogPanel();
			}
		});

		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtName.setText("");
				txtCpr.setText("");
				txtId.setText("");
				cusromerList.setModel(new DefaultTableModel(new Object[][] {},
						new String[] { "Id", "First Name", "Last Name", "CPR", "Phone", "Email" }));
			}
		});

		cusromerList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (SwingUtilities.isRightMouseButton(e)) {
					int r = cusromerList.rowAtPoint(e.getPoint());
					if (r >= 0 && r < cusromerList.getRowCount()) {
						new CustomerTablePopupMenu(cusromerList).show(e.getComponent(), e.getX(), e.getY());
						cusromerList.setRowSelectionInterval(r, r);
					} else {
						cusromerList.clearSelection();
					}
				}
			}
		});

	}
}
