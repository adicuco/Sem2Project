package GUILayer.Customer.menuPanels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

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
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import ControlLayer.EquipmentCtr;
import ControlLayer.EquipmentTypeCtr;
import GUILayer.Customer.MainMenuCustomer;
import GUILayer.Customer.menuPanels.dialogPanels.CompleteBookingDialogPanel;
import GUILayer.Employee.menuPanels.dialogPanels.AddEditEquipmentDialogPanel;
import GUILayer.Employee.menuPanels.dialogPanels.EquipmentTablePopupMenu;
import ModelLayer.Equipment;
import ModelLayer.EquipmentType;
import ModelLayer.RentType;
import javax.swing.JSeparator;
import javax.swing.LayoutStyle.ComponentPlacement;

public class EquipmentPanel extends JPanel {

	private ArrayList<Timestamp> days;
	private ArrayList<Timestamp> period1;
	private ArrayList<Timestamp> period2;
	private DefaultTableModel model;
	private DefaultTableModel model1;
	private Timestamp day;
	private Timestamp per1;
	private Timestamp per2;
	private ActionListener actionFillPeriodCB;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JLabel lblDate = new JLabel(TimeClass.getDate());
	private final JComboBox<String> cbDate = new JComboBox<String>();
	private final JLabel lblDate_1 = new JLabel("Date");
	private final JLabel lblPeriod = new JLabel("Period");
	private final JLabel lblEquipmentType = new JLabel("Equipment Type");
	private final JComboBox<String> cbPer1 = new JComboBox<String>();
	private final JComboBox<EquipmentType> cbEqType = new JComboBox<EquipmentType>();
	private final JLabel label = new JLabel("-");
	private final JComboBox<String> cbPer2 = new JComboBox<String>();
	private final JScrollPane scrollPane = new JScrollPane();
	private final JTable tblEq = new JTable();
	private final static JButton btnSearch = new JButton("Search");
	private final static JButton btnSearchAll = new JButton("Search All");
	private final JButton btnAddEquipment = new JButton("Add Equipment");
	private final JButton btnSetId = new JButton("Set ID");
	private final JTextField txtId = new JTextField();
	private final JPanel pnlLeft = new JPanel();
	private final JPanel pnlRight = new JPanel();
	private final JSeparator separator = new JSeparator();

	/**
	 * Create the panel.
	 */
	public EquipmentPanel() {
		separator.setFont(new Font("SansSerif", Font.BOLD, 15));
		initialize();
		fillComboBoxes();
		createEvents();
	}

	private void initialize() {
		setSize(new Dimension(1117, 765));
		lblDate.setFont(new Font("SansSerif", Font.BOLD, 20));
		model = new DefaultTableModel(new Object[][] {}, new String[] { "Name", "Available", "Price/hour", "id" }) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] { false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		model1 = new DefaultTableModel(new Object[][] {}, new String[] { "Name", "Total", "Price/hour", "id" }) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] { false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		tblEq.setModel(model);
		tblEq.getColumnModel().getColumn(0).setResizable(false);
		tblEq.getColumnModel().getColumn(1).setResizable(false);
		tblEq.getColumnModel().getColumn(2).setResizable(false);
		tblEq.setRowHeight(70);
		tblEq.setShowVerticalLines(true);
		tblEq.setShowHorizontalLines(true);
		tblEq.setFont(new Font("SansSerif", Font.PLAIN, 25));
		tblEq.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 20));
		tblEq.getTableHeader().setForeground(Color.DARK_GRAY);

		scrollPane.setViewportView(tblEq);
		lblDate_1.setFont(new Font("SansSerif", Font.BOLD, 25));
		lblPeriod.setFont(new Font("SansSerif", Font.BOLD, 25));
		lblEquipmentType.setFont(new Font("SansSerif", Font.BOLD, 25));
		cbDate.setFont(new Font("SansSerif", Font.PLAIN, 25));
		cbPer1.setFont(new Font("SansSerif", Font.PLAIN, 25));
		label.setFont(new Font("SansSerif", Font.BOLD, 25));
		cbPer2.setFont(new Font("SansSerif", Font.PLAIN, 25));
		cbEqType.setFont(new Font("SansSerif", Font.PLAIN, 25));
		GroupLayout gl_pnlLeft = new GroupLayout(pnlLeft);
		gl_pnlLeft.setHorizontalGroup(gl_pnlLeft.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
				gl_pnlLeft.createSequentialGroup().addContainerGap(13, Short.MAX_VALUE).addGroup(gl_pnlLeft
						.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlLeft.createSequentialGroup().addComponent(lblDate_1).addGap(206)
								.addComponent(cbDate, GroupLayout.PREFERRED_SIZE, 255, GroupLayout.PREFERRED_SIZE))
						.addGroup(
								gl_pnlLeft.createSequentialGroup().addComponent(lblPeriod).addGap(182)
										.addComponent(cbPer1, GroupLayout.PREFERRED_SIZE, 109,
												GroupLayout.PREFERRED_SIZE)
										.addGap(18)
										.addComponent(label, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
										.addGap(6).addComponent(cbPer2, GroupLayout.PREFERRED_SIZE, 102,
												GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_pnlLeft.createSequentialGroup()
								.addComponent(lblEquipmentType, GroupLayout.PREFERRED_SIZE, 207,
										GroupLayout.PREFERRED_SIZE)
								.addGap(53)
								.addComponent(cbEqType, GroupLayout.PREFERRED_SIZE, 255, GroupLayout.PREFERRED_SIZE)))
						.addContainerGap()));
		gl_pnlLeft
				.setVerticalGroup(
						gl_pnlLeft.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_pnlLeft.createSequentialGroup().addGap(19).addGap(6)
										.addGroup(gl_pnlLeft.createParallelGroup(Alignment.LEADING)
												.addGroup(gl_pnlLeft.createSequentialGroup().addGap(5)
														.addComponent(lblDate_1))
												.addComponent(cbDate, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(6)
				.addGroup(gl_pnlLeft.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlLeft.createSequentialGroup().addGap(5).addComponent(lblPeriod))
						.addComponent(cbPer1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_pnlLeft.createSequentialGroup().addGap(13).addComponent(label,
								GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))
						.addComponent(cbPer2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addGap(12)
				.addGroup(gl_pnlLeft.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlLeft.createSequentialGroup().addGap(5).addComponent(lblEquipmentType))
						.addComponent(cbEqType, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)).addContainerGap(26, Short.MAX_VALUE)));
		pnlLeft.setLayout(gl_pnlLeft);
		btnAddEquipment.setFont(new Font("SansSerif", Font.BOLD, 23));
		btnAddEquipment.setVisible(false);
		btnSearchAll.setFont(new Font("SansSerif", Font.BOLD, 25));
		btnSearchAll.setVisible(false);
		btnSearch.setFont(new Font("SansSerif", Font.BOLD, 25));
		btnSetId.setFont(new Font("SansSerif", Font.BOLD, 25));
		btnSetId.setVisible(false);
		txtId.setFont(new Font("SansSerif", Font.BOLD, 25));
		txtId.setColumns(10);
		txtId.setVisible(false);
		GroupLayout gl_pnlRight = new GroupLayout(pnlRight);
		gl_pnlRight.setHorizontalGroup(
			gl_pnlRight.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_pnlRight.createSequentialGroup()
					.addGap(23)
					.addGroup(gl_pnlRight.createParallelGroup(Alignment.LEADING, false)
						.addComponent(separator, GroupLayout.PREFERRED_SIZE, 425, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_pnlRight.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnAddEquipment, GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(btnSearchAll, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_pnlRight.createSequentialGroup()
							.addGap(26)
							.addComponent(txtId, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
							.addGap(13)
							.addComponent(btnSetId)
							.addGap(18)
							.addComponent(btnSearch, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_pnlRight.setVerticalGroup(
			gl_pnlRight.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlRight.createSequentialGroup()
					.addGroup(gl_pnlRight.createParallelGroup(Alignment.LEADING)
						.addComponent(btnAddEquipment, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSearchAll, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator, GroupLayout.DEFAULT_SIZE, 5, Short.MAX_VALUE)
					.addGap(3)
					.addGroup(gl_pnlRight.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlRight.createSequentialGroup()
							.addGap(2)
							.addComponent(txtId, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnSetId, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSearch, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE))
					.addGap(5))
		);
		pnlRight.setLayout(gl_pnlRight);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(6)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 1111, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(pnlLeft, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 107, Short.MAX_VALUE)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(pnlRight, GroupLayout.PREFERRED_SIZE, 452, GroupLayout.PREFERRED_SIZE)
									.addGap(14))
								.addComponent(lblDate))))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(6)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblDate, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
							.addGap(33)
							.addComponent(pnlRight, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE))
						.addComponent(pnlLeft, GroupLayout.PREFERRED_SIZE, 194, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 529, Short.MAX_VALUE)
					.addGap(30))
		);
		setLayout(groupLayout);
		if (MainMenuCustomer.getCustomerId() == 0) {
			txtId.setVisible(true);
			btnSetId.setVisible(true);
			btnSearchAll.setVisible(true);
			btnAddEquipment.setVisible(true);
		}
	}

	private void createEvents() {

		TimeClass.getTime(lblDate);

		actionFillPeriodCB = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cbPer2.setSelectedIndex(cbPer1.getSelectedIndex());
			}
		};
		cbPer1.addActionListener(actionFillPeriodCB);

		btnSearch.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				day = days.get(cbDate.getSelectedIndex());
				per1 = period1.get(cbPer1.getSelectedIndex());
				per2 = period2.get(cbPer2.getSelectedIndex());
				per1.setMonth(day.getMonth());
				per1.setDate(day.getDate());
				per2.setMonth(day.getMonth());
				per2.setDate(day.getDate());

				if (txtId.getText().isEmpty()) {
					MainMenuCustomer.setCustomerId(0);
				}
				int id = MainMenuCustomer.getCustomerId();

				EquipmentCtr eqCtr = new EquipmentCtr();
				ArrayList<Equipment> eqs = eqCtr.getAvailableEquipment(per1, per2,
						((EquipmentType) cbEqType.getSelectedItem()).getEqTypeId(), id);

				model.setRowCount(0);
				model1.setRowCount(0);
				tblEq.setModel(model);
				if (!eqs.isEmpty()) {
					for (Equipment eq : eqs) {
						model.addRow(new Object[] { eq.getName(), eq.getQuantity(), eq.getPrice(), eq.getEqId() });
					}
				}
				try {
					tblEq.removeColumn(tblEq.getColumnModel().getColumn(3));
				} catch (Exception e) {
				}
			}
		});

		tblEq.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (SwingUtilities.isRightMouseButton(e)) {
					int r = tblEq.rowAtPoint(e.getPoint());
					if (r >= 0 && r < tblEq.getRowCount()) {
						new EquipmentTablePopupMenu(tblEq).show(e.getComponent(), e.getX(), e.getY());
						tblEq.setRowSelectionInterval(r, r);
					} else {
						tblEq.clearSelection();
					}
				} else {
					int i = tblEq.getSelectedRow();
					int id = (int) tblEq.getModel().getValueAt(i, 3);
					EquipmentCtr eqCtr = new EquipmentCtr();
					Equipment eq = eqCtr.findEquipment(id);
					new CompleteBookingDialogPanel(RentType.EQUIPMENT, per1, per2, eq);
				}
			}
		});

		cbDate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Timestamp day = days.get(cbDate.getSelectedIndex());
				cbPer1.removeActionListener(actionFillPeriodCB);
				fillPeriodCB(day);
				cbPer1.addActionListener(actionFillPeriodCB);
			}
		});

		btnSetId.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (txtId.getText().isEmpty()) {
					txtId.setText("0");
				}
				boolean ok = MainMenuCustomer.setCustomerId(Integer.valueOf(txtId.getText()));
				if (!ok) {
					JOptionPane.showMessageDialog(null, "Customer not found.", "No match.", JOptionPane.ERROR_MESSAGE);
				}

			}
		});

		btnSearchAll.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				day = days.get(cbDate.getSelectedIndex());
				per1 = period1.get(cbPer1.getSelectedIndex());
				per2 = period2.get(cbPer2.getSelectedIndex());
				per1.setMonth(day.getMonth());
				per1.setDate(day.getDate());
				per2.setMonth(day.getMonth());
				per2.setDate(day.getDate());

				EquipmentCtr eqCtr = new EquipmentCtr();
				ArrayList<Equipment> eqs = eqCtr.findAllEquipment("eqTypeId",
						String.valueOf(((EquipmentType) cbEqType.getSelectedItem()).getEqTypeId()));
				model.setRowCount(0);
				model1.setRowCount(0);
				tblEq.setModel(model1);
				if (!eqs.isEmpty()) {
					for (Equipment eq : eqs) {
						model1.addRow(new Object[] { eq.getName(), eq.getQuantity(), eq.getPrice(), eq.getEqId() });
					}
				}
				try {
					tblEq.removeColumn(tblEq.getColumnModel().getColumn(3));
				} catch (Exception e) {
				}
			}
		});

		btnAddEquipment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AddEditEquipmentDialogPanel(null);
				updateAll();
			}
		});

	}

	private void fillComboBoxes() {
		days = new ArrayList<Timestamp>();

		LocalDateTime now = LocalDateTime.now();
		Timestamp day = Timestamp.valueOf(now);
		fillPeriodCB((Timestamp) day.clone());

		for (int i = 1; i <= 7; i++) {
			day = Timestamp.valueOf(now);
			days.add(day);
			cbDate.addItem(TimeClass.comboBoxDate(day));
			now = LocalDateTime.now().plusDays(i).withHour(8).withMinute(0);
		}

		EquipmentTypeCtr eqTypeCtr = new EquipmentTypeCtr();
		ArrayList<EquipmentType> eqTypes = eqTypeCtr.findAllEquipmentTypes();
		for (EquipmentType eqType : eqTypes) {
			cbEqType.addItem(eqType);
		}
	}

	@SuppressWarnings("deprecation")
	private void fillPeriodCB(Timestamp day) {
		cbPer1.removeAllItems();
		cbPer2.removeAllItems();
		Timestamp time = (Timestamp) day.clone();
		Timestamp time1 = (Timestamp) day.clone();
		period1 = new ArrayList<Timestamp>();
		period2 = new ArrayList<Timestamp>();

		LocalTime hours = LocalTime.of(day.getHours(), day.getMinutes());
		if (hours.getMinute() >= 50) {
			hours.plusHours(1);
		}
		if (hours.getHour() < 8) {
			hours = LocalTime.of(8, 0);
		}
		for (int hour = hours.getHour(); hour <= 23; hour++) {
			time = (Timestamp) day.clone();
			time1 = (Timestamp) day.clone();
			time.setHours(hour);
			time1.setHours(hour);
			time.setMinutes(0);
			period1.add(time);
			cbPer1.addItem(TimeClass.comboBoxPeriod(time));
			time1.setMinutes(50);
			period2.add(time1);
			cbPer2.addItem(TimeClass.comboBoxPeriod(time1));
		}
	}

	public static void updateAll() {
		btnSearchAll.doClick();
	}

	public static void update() {
		btnSearch.doClick();
	}
}
