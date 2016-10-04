package GUILayer.Employee.menuPanels;

import javax.swing.JPanel;

import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import GUILayer.Customer.menuPanels.TimeClass;
import GUILayer.Employee.menuPanels.dialogPanels.AddEmployeeDialogPanel;
import GUILayer.Employee.menuPanels.dialogPanels.EmployeeTablePopupMenu;
import ModelLayer.Employee;

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

import ControlLayer.EmployeeCtr;

import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EmployeePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JLabel lblDate = new JLabel(TimeClass.getDate());
	private final JLabel lblEmployeeMenu = new JLabel("EMPLOYEE  MENU");
	private final JPanel pnlFindEmp = new JPanel();
	private final JLabel lblEmployeeId = new JLabel("Employee ID");
	private final JLabel lblEmployeeName = new JLabel("Employee Name");
	private final JLabel lblEmployeeCPR = new JLabel("Employee CPR");
	private final JButton btnFind = new JButton("Search");
	private final JTextField txtId = new JTextField();
	private final JTextField txtName = new JTextField();
	private final JTextField txtCpr = new JTextField();
	private final JSeparator separator = new JSeparator();
	private final JPanel pnlTable = new JPanel();
	private final JTable employeeList = new JTable();
	private final JScrollPane scrollPane = new JScrollPane();
	private DefaultTableModel model;
	private final JButton btnAddNewEmployee = new JButton("Add New Employee");
	private final JButton btnClear = new JButton("Clear");
	private EmployeeTablePopupMenu popMenu;

	public EmployeePanel() {

		createEvents();
		initialize();
	}

	private void initialize() {

		setVisible(true);
		BorderFactory.createEmptyBorder(1, 1, 1, 1);

		// setSize(new Dimension(1050, 750));

		lblDate.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblEmployeeMenu.setFont(new Font("SansSerif", Font.BOLD, 30));
		lblEmployeeId.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblEmployeeCPR.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblEmployeeName.setFont(new Font("SansSerif", Font.BOLD, 20));
		txtCpr.setColumns(10);
		txtCpr.setFont(new Font("SansSerif", Font.BOLD, 20));
		txtName.setColumns(10);
		txtName.setFont(new Font("SansSerif", Font.BOLD, 20));
		txtId.setColumns(10);
		txtId.setFont(new Font("SansSerif", Font.BOLD, 20));
		btnFind.setFont(new Font("SansSerif", Font.BOLD, 20));
		btnClear.setFont(new Font("SansSerif", Font.BOLD, 20));
		btnAddNewEmployee.setFont(new Font("SansSerif", Font.BOLD, 20));

		model = new DefaultTableModel(new Object[][] {},
				new String[] { "Id", "First Name", "Last Name", "CPR", "Phone", "Email" });
		employeeList.setModel(model);
		employeeList.setFont(new Font("SansSerif", Font.PLAIN, 25));
		employeeList.setRowHeight(35);
		employeeList.setComponentPopupMenu(popMenu);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(141)
					.addComponent(btnClear, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 254, Short.MAX_VALUE)
					.addComponent(btnAddNewEmployee)
					.addGap(208))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(321)
					.addComponent(lblEmployeeMenu, GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
					.addGap(152)
					.addComponent(lblDate)
					.addGap(26))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(3)
					.addComponent(pnlTable, GroupLayout.PREFERRED_SIZE, 937, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addGap(28)
					.addComponent(separator, GroupLayout.DEFAULT_SIZE, 910, Short.MAX_VALUE)
					.addGap(25))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(3)
					.addComponent(pnlFindEmp, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblDate)
						.addComponent(lblEmployeeMenu))
					.addGap(17)
					.addComponent(pnlFindEmp, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addComponent(pnlTable, GroupLayout.PREFERRED_SIZE, 400, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAddNewEmployee)
						.addComponent(btnClear))
					.addContainerGap())
		);
		GroupLayout gl_pnlTable = new GroupLayout(pnlTable);
		gl_pnlTable.setHorizontalGroup(
			gl_pnlTable.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlTable.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 931, Short.MAX_VALUE)
					.addGap(6))
		);
		gl_pnlTable.setVerticalGroup(
			gl_pnlTable.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlTable.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 389, Short.MAX_VALUE))
		);
		scrollPane.setViewportView(employeeList);
		pnlTable.setLayout(gl_pnlTable);
		GroupLayout gl_pnlFindCus = new GroupLayout(pnlFindEmp);
		gl_pnlFindCus.setHorizontalGroup(
			gl_pnlFindCus.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlFindCus.createSequentialGroup()
					.addGap(19)
					.addGroup(gl_pnlFindCus.createParallelGroup(Alignment.LEADING)
						.addComponent(lblEmployeeName)
						.addComponent(lblEmployeeId))
					.addGap(29)
					.addGroup(gl_pnlFindCus.createParallelGroup(Alignment.LEADING)
						.addComponent(txtId, GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
						.addComponent(txtName, GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE))
					.addGroup(gl_pnlFindCus.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_pnlFindCus.createSequentialGroup()
							.addGap(114)
							.addComponent(lblEmployeeCPR)
							.addGap(45)
							.addComponent(txtCpr, GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE))
						.addGroup(gl_pnlFindCus.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnFind, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)))
					.addGap(45))
		);
		gl_pnlFindCus.setVerticalGroup(
			gl_pnlFindCus.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlFindCus.createSequentialGroup()
					.addGap(21)
					.addGroup(gl_pnlFindCus.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlFindCus.createParallelGroup(Alignment.BASELINE)
							.addComponent(txtCpr, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblEmployeeCPR))
						.addGroup(gl_pnlFindCus.createSequentialGroup()
							.addGroup(gl_pnlFindCus.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblEmployeeId)
								.addComponent(txtId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(45)
							.addGroup(gl_pnlFindCus.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblEmployeeName, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnFind))))
					.addContainerGap(26, Short.MAX_VALUE))
		);
		pnlFindEmp.setLayout(gl_pnlFindCus);
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
				employeeList.setModel(model);
				model.setRowCount(0);
				EmployeeCtr empCtr = new EmployeeCtr();
				if (txtId.getText().equals("")) {
					if (txtCpr.getText().equals("")) {
						ArrayList<Employee> emp = empCtr.findAllEmployees(txtName.getText());
						if (!emp.isEmpty()) {
							for (Employee employee : emp) {
								model.addRow(new Object[] { employee.getEmployeeId(), employee.getfName(),
										employee.getlName(), employee.getCpr(), employee.getPhone(),
										employee.getEmail() });
							}
						}

					} else {
						ArrayList<Employee> emp = new ArrayList<Employee>();
						try {
							emp = empCtr.findAllEmployees("cpr", txtCpr.getText());

						} catch (NullPointerException e1) {

							e1.printStackTrace();
						}
						if (!emp.isEmpty()) {
							for (Employee employee : emp) {
								model.addRow(new Object[] { employee.getEmployeeId(), employee.getfName(),
										employee.getlName(), employee.getCpr(), employee.getPhone(),
										employee.getEmail() });
							}
						}
					}

				} else {

					Employee employee = new Employee();
					try {
						employee = empCtr.findEmployee(Integer.parseInt(txtId.getText()));

					} catch (NullPointerException e1) {
						e1.printStackTrace();
					}
					model.addRow(new Object[] { employee.getEmployeeId(), employee.getfName(), employee.getlName(),
							employee.getCpr(), employee.getPhone(), employee.getEmail() });
				}

			}
		});

		btnAddNewEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AddEmployeeDialogPanel();
			}
		});

		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtName.setText("");
				txtCpr.setText("");
				txtId.setText("");
				employeeList.setModel(new DefaultTableModel(new Object[][] {},
						new String[] { "Id", "First Name", "Last Name", "CPR", "Phone", "Email" }));
			}
		});

		employeeList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (SwingUtilities.isRightMouseButton(e)) {
					int r = employeeList.rowAtPoint(e.getPoint());
					if (r >= 0 && r < employeeList.getRowCount()) {
						new EmployeeTablePopupMenu(employeeList).show(e.getComponent(), e.getX(), e.getY());
						employeeList.setRowSelectionInterval(r, r);
					} else {
						employeeList.clearSelection();
					}
				}

			}
		});

	}
}
