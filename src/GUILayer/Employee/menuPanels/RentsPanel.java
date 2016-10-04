package GUILayer.Employee.menuPanels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import ControlLayer.RentCtr;
import GUILayer.Customer.MainMenuCustomer;
import GUILayer.Customer.menuPanels.TimeClass;
import GUILayer.Customer.menuPanels.dialogPanels.BookingDialogPanel;
import GUILayer.Employee.menuPanels.dialogPanels.RentTablePopupMenu;
import ModelLayer.Rent;

@SuppressWarnings("serial")
public class RentsPanel extends JPanel {

	private DefaultTableModel modelSc;
	private DefaultTableModel modelEq;
	private RentCtr rentCtr = new RentCtr();
	private MouseListener mL;

	private final JLabel lblDate = new JLabel(TimeClass.getDate());
	private final JScrollPane scrollPane = new JScrollPane();
	private final JScrollPane scrollPane_1 = new JScrollPane();
	private final JTable tblSc = new JTable();
	private final JTable tblEq = new JTable();
	private final JComboBox<String> cbShow = new JComboBox<String>();
	private final JDateChooser dcPer1 = new JDateChooser();
	private final JDateChooser dcPer2 = new JDateChooser();
	private final JButton btnAll = new JButton("Search All");
	private final JButton btnCustomer = new JButton("Search Customer's");
	private final JTextField txtCus = new JTextField();
	private final JLabel lblSportCourts = new JLabel("Sport Courts");
	private final JLabel lblEquipment = new JLabel("Equipment");

	/**
	 * Create the panel.
	 */
	public RentsPanel() {
		initialize();
		createEvents();
		btnAll.doClick();
	}

	private void initialize() {
		setSize(new Dimension(1120, 765));
		lblDate.setFont(new Font("SansSerif", Font.BOLD, 20));

		cbShow.setModel(new DefaultComboBoxModel<String>(
				new String[] { "Show Ongoing Rents", "Show All Rents", "Choose Date" }));
		cbShow.setFont(new Font("SansSerif", Font.PLAIN, 22));
		dcPer1.setFont(new Font("SansSerif", Font.PLAIN, 15));
		dcPer1.setVisible(false);
		dcPer2.setFont(new Font("SansSerif", Font.PLAIN, 15));
		dcPer2.setVisible(false);

		lblEquipment.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblSportCourts.setFont(new Font("SansSerif", Font.BOLD, 20));

		btnAll.setFont(new Font("SansSerif", Font.BOLD, 17));
		btnCustomer.setFont(new Font("SansSerif", Font.BOLD, 17));
		txtCus.setFont(new Font("SansSerif", Font.BOLD, 20));
		txtCus.setColumns(10);

		modelSc = new DefaultTableModel(new Object[][] {},
				new String[] { "Booking Date", "Start Date", "End Date", "Cost", "id" }) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] { false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};

		modelEq = new DefaultTableModel(new Object[][] {},
				new String[] { "Booking Date", "Start Date", "End Date", "Cost", "id" }) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] { false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};

		tblSc.setRowHeight(30);
		tblSc.setFont(new Font("SansSerif", Font.PLAIN, 15));
		tblSc.setShowVerticalLines(true);
		tblSc.setShowHorizontalLines(true);
		tblSc.setModel(modelSc);
		tblSc.getColumnModel().getColumn(0).setResizable(false);
		tblSc.getColumnModel().getColumn(0).setPreferredWidth(50);
		tblSc.getColumnModel().getColumn(1).setResizable(false);
		tblSc.getColumnModel().getColumn(2).setResizable(false);
		tblSc.getColumnModel().getColumn(3).setResizable(false);
		tblSc.getColumnModel().getColumn(4).setResizable(false);
		tblSc.getColumnModel().getColumn(3).setMaxWidth(80);
		tblSc.removeColumn(tblSc.getColumnModel().getColumn(4));
		tblSc.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 20));
		tblSc.getTableHeader().setForeground(Color.DARK_GRAY);

		scrollPane.setViewportView(tblSc);

		tblEq.setRowHeight(30);
		tblEq.setFont(new Font("SansSerif", Font.PLAIN, 15));
		tblEq.setShowVerticalLines(true);
		tblEq.setShowHorizontalLines(true);
		tblEq.setModel(modelEq);
		tblEq.getColumnModel().getColumn(0).setResizable(false);
		tblEq.getColumnModel().getColumn(0).setPreferredWidth(50);
		tblEq.getColumnModel().getColumn(1).setResizable(false);
		tblEq.getColumnModel().getColumn(2).setResizable(false);
		tblEq.getColumnModel().getColumn(3).setResizable(false);
		tblEq.getColumnModel().getColumn(4).setResizable(false);
		tblEq.getColumnModel().getColumn(3).setMaxWidth(80);
		tblEq.removeColumn(tblEq.getColumnModel().getColumn(4));
		tblEq.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 20));
		tblEq.getTableHeader().setForeground(Color.DARK_GRAY);

		scrollPane_1.setViewportView(tblEq);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(42)
					.addComponent(dcPer1, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(dcPer2, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
					.addGap(563)
					.addComponent(lblDate)
					.addGap(9))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addGap(57)
					.addComponent(cbShow, GroupLayout.PREFERRED_SIZE, 234, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 380, Short.MAX_VALUE)
					.addComponent(txtCus, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
					.addGap(21)
					.addComponent(btnCustomer, GroupLayout.PREFERRED_SIZE, 194, GroupLayout.PREFERRED_SIZE)
					.addGap(35)
					.addComponent(btnAll, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
					.addGap(9))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(202)
					.addComponent(lblSportCourts, GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
					.addGap(472)
					.addComponent(lblEquipment, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(223))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(10)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 545, Short.MAX_VALUE)
					.addGap(7)
					.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 548, Short.MAX_VALUE)
					.addGap(10))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(6)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblDate)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(12)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(dcPer1, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
								.addComponent(dcPer2, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))))
					.addGap(31)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(cbShow, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtCus, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(1)
							.addComponent(btnCustomer, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(1)
							.addComponent(btnAll, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblSportCourts, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblEquipment, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 546, Short.MAX_VALUE)
						.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 546, Short.MAX_VALUE))
					.addGap(41))
		);
		setLayout(groupLayout);
	}

	private void createEvents() {

		TimeClass.getTime(lblDate);

		btnAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtCus.setText("");
				switch (cbShow.getSelectedItem().toString()) {
				case "Show Ongoing Rents":
					ArrayList<Rent> rentsSc = rentCtr.findOngoingRents("rentType", "SPORTCOURT");
					fillTable(modelSc, rentsSc);
					ArrayList<Rent> rentsEq = rentCtr.findOngoingRents("rentType", "EQUIPMENT");
					fillTable(modelEq, rentsEq);
					break;
				case "Show All Rents":
					ArrayList<Rent> rentsSc1 = rentCtr.findAllRents("rentType", "SPORTCOURT");
					fillTable(modelSc, rentsSc1);
					ArrayList<Rent> rentsEq1 = rentCtr.findAllRents("rentType", "EQUIPMENT");
					fillTable(modelEq, rentsEq1);
					break;
				case "Choose Date":
					if (dcPer1.toString().isEmpty() || dcPer2.toString().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Insert desired dates.", "No dates.",
								JOptionPane.WARNING_MESSAGE);
					} else {
						Timestamp per1 = TimeClass.toTimestamp(dcPer1.getDate());
						Timestamp per2 = TimeClass.toTimestamp(dcPer2.getDate());
						ArrayList<Rent> rentsSc2 = rentCtr.findAllRents("rentType",
								"SPORTCOURT' and dateRentMade BETWEEN '" + per1 + "' and '" + per2);
						fillTable(modelSc, rentsSc2);
						ArrayList<Rent> rentsEq2 = rentCtr.findAllRents("rentType",
								"EQUIPMENT' and dateRentMade BETWEEN '" + per1 + "' and '" + per2);
						fillTable(modelEq, rentsEq2);
					}
					break;
				}
			}
		});

		btnCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (txtCus.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Insert customer's ID.", "No ID.", JOptionPane.WARNING_MESSAGE);
				} else {
					boolean ok = MainMenuCustomer.setCustomerId(Integer.valueOf(txtCus.getText()));
					if (!ok) {
						JOptionPane.showMessageDialog(null, "Customer not found.", "No match.",
								JOptionPane.ERROR_MESSAGE);
					} else {
						switch (cbShow.getSelectedItem().toString()) {
						case "Show Ongoing Rents":
							ArrayList<Rent> rentsSc = rentCtr.findOngoingRents("customerId",
									String.valueOf(MainMenuCustomer.getCustomerId()) + "' and rentType = 'SPORTCOURT");
							fillTable(modelSc, rentsSc);
							ArrayList<Rent> rentsEq = rentCtr.findOngoingRents("customerId",
									String.valueOf(MainMenuCustomer.getCustomerId()) + "' and rentType = 'EQUIPMENT");
							fillTable(modelEq, rentsEq);
							break;
						case "Show All Rents":
							ArrayList<Rent> rentsSc1 = rentCtr.findAllRents("customerId",
									String.valueOf(MainMenuCustomer.getCustomerId()) + "' and rentType = 'SPORTCOURT");
							fillTable(modelSc, rentsSc1);
							ArrayList<Rent> rentsEq1 = rentCtr.findAllRents("customerId",
									String.valueOf(MainMenuCustomer.getCustomerId()) + "' and rentType = 'EQUIPMENT");
							fillTable(modelEq, rentsEq1);
							break;
						case "Choose Date":
							if (dcPer1.toString().isEmpty() || dcPer2.toString().isEmpty()) {
								JOptionPane.showMessageDialog(null, "Insert desired dates.", "No dates.",
										JOptionPane.WARNING_MESSAGE);
							} else {
								Timestamp per1 = TimeClass.toTimestamp(dcPer1.getDate());
								Timestamp per2 = TimeClass.toTimestamp(dcPer2.getDate());
								ArrayList<Rent> rentsSp2 = rentCtr.findAllRents("customerId",
										String.valueOf(MainMenuCustomer.getCustomerId())
												+ "' and rentType = 'SPORTCOURT' and dateRentMade BETWEEN '" + per1
												+ "' and '" + per2);
								fillTable(modelSc, rentsSp2);
								ArrayList<Rent> rentsEq2 = rentCtr.findAllRents("customerId",
										String.valueOf(MainMenuCustomer.getCustomerId())
												+ "' and rentType = 'EQUIPMENT' and dateRentMade BETWEEN '" + per1
												+ "' and '" + per2);
								fillTable(modelEq, rentsEq2);
							}
							break;
						}
					}
				}
			}
		});

		cbShow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switch (cbShow.getSelectedItem().toString()) {
				case "Show Ongoing Rents":
					modelSc.setRowCount(0);
					modelEq.setRowCount(0);
					dcPer1.setVisible(false);
					dcPer2.setVisible(false);
					break;
				case "Show All Rents":
					modelSc.setRowCount(0);
					modelEq.setRowCount(0);
					dcPer1.setVisible(false);
					dcPer2.setVisible(false);
					break;
				case "Choose Date":
					modelSc.setRowCount(0);
					modelEq.setRowCount(0);
					dcPer1.setVisible(true);
					dcPer2.setVisible(true);
					break;
				}
			}
		});

		mL = new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				JTable table = null;
				DefaultTableModel model = null;
				if (tblEq.hasFocus()) {
					table = tblEq;
					model = modelEq;
				} else {
					table = tblSc;
					model = modelSc;
				}
				if (SwingUtilities.isRightMouseButton(e)) {
					int r = table.rowAtPoint(e.getPoint());
					if (r >= 0 && r < table.getRowCount()) {
						new RentTablePopupMenu(table).show(e.getComponent(), e.getX(), e.getY());
						table.setRowSelectionInterval(r, r);
					} else {
						table.clearSelection();
					}
				} else {
					int i = table.getSelectedRow();
					int id = (int) model.getValueAt(i, 4);
					Rent rent = rentCtr.findRent(id);
					new BookingDialogPanel(rent);
					
					if (txtCus.getText().isEmpty()) {
						btnAll.doClick();
					} else {
						btnCustomer.doClick();
					}
				}
			}
		};
		tblEq.addMouseListener(mL);
		tblSc.addMouseListener(mL);
	}

	private void fillTable(DefaultTableModel model, ArrayList<Rent> rents) {
		model.setRowCount(0);
		if (!rents.isEmpty()) {
			for (Rent rent : rents) {
				String dateRentMade = TimeClass.txtDate(rent.getDateRentMade());
				String startDate = TimeClass.txtDate(rent.getStartDate());
				String endDate = TimeClass.txtDate(rent.getEndDate());
				model.addRow(new Object[] { dateRentMade, startDate, endDate, rent.gettAmount(), rent.getRentId() });
			}

		} else {
			model.addRow(new Object[] { "NO", "RENTS", "FOUND", "!", 0 });
		}
	}
}
