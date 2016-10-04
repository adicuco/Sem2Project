package GUILayer.Employee.menuPanels.dialogPanels.createEventProcess;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JSpinner.DefaultEditor;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import ControlLayer.EquipmentCtr;
import GUILayer.Customer.menuPanels.TimeClass;
import ModelLayer.Equipment;
import ModelLayer.PreRentEquipment;
import ModelLayer.Rent;
import ModelLayer.SportCourt;

@SuppressWarnings("serial")
public class CreateEventEquipmentPanel extends JPanel {

	private DefaultTableModel modelAvailable;
	private DefaultTableModel modelSelected;
	private SpinnerNumberModel spinnerModel;
	private static ArrayList<Equipment> selectedEqs;
	private ArrayList<Equipment> availableEqs;
	private PreRentEquipment preRentEq;
	private static ArrayList<PreRentEquipment> arrayPreRentEq;
	private ArrayList<Rent> selectedCourts;
	private int day;
	private int i;
	private Rent rent;

	private final JScrollPane scrollPane = new JScrollPane();
	private final JScrollPane scrollPane_1 = new JScrollPane();
	private final JLabel lblNewLabel = new JLabel("Select from the available equipment :");
	private final JLabel lblSelectedEquipment = new JLabel("Selected equipment :");
	private final JTable tblAvailable = new JTable();
	private final JTable tblSelected = new JTable();
	private final JSpinner spinner = new JSpinner();
	private final JButton btnAdd = new JButton("Add");
	private final JButton btnRemove = new JButton("Remove");
	private final JPanel panel = new JPanel();
	private final JButton btnPreviousDay = new JButton("Previous Day");
	private final JButton btnNextDay = new JButton("Next Day");
	private final JLabel lblDate = new JLabel((String) null);

	/**
	 * Create the panel.
	 */
	public CreateEventEquipmentPanel(ArrayList<Rent> selectedCourts) {
		this.selectedCourts = selectedCourts;
		arrayPreRentEq = new ArrayList<PreRentEquipment>();
		selectedEqs = new ArrayList<Equipment>();
		i = selectedCourts.size();
		day = 0;
		rent = selectedCourts.get(day);

		EquipmentCtr eqCtr = new EquipmentCtr();
		SportCourt sportCourt = (SportCourt) rent.getRentable();
		availableEqs = eqCtr.getAvailableEquipment(rent.getStartDate(), rent.getEndDate(),
				sportCourt.getEqType().getEqTypeId(), 0);

		initialize();
		fillAvailableTable();
		initializeDays();
		createEvents();
	}

	private void initialize() {
		setVisible(true);
		setBounds(100, 100, 856, 591);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(scrollPane_1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 844,
										Short.MAX_VALUE)
						.addComponent(scrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 844, Short.MAX_VALUE)
						.addGroup(Alignment.TRAILING,
								groupLayout.createSequentialGroup()
										.addComponent(lblSelectedEquipment, GroupLayout.PREFERRED_SIZE, 261,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED, 225, Short.MAX_VALUE)
										.addComponent(spinner, GroupLayout.PREFERRED_SIZE, 130,
												GroupLayout.PREFERRED_SIZE)
										.addGap(12).addComponent(btnAdd).addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(btnRemove).addGap(10))
						.addComponent(panel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 844, Short.MAX_VALUE)
						.addComponent(lblNewLabel)).addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addGap(7)
				.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblSelectedEquipment, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(btnAdd, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addComponent(btnRemove, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addComponent(spinner, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 207, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE).addGap(3)));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addContainerGap().addComponent(btnPreviousDay).addGap(34)
						.addComponent(lblDate, GroupLayout.PREFERRED_SIZE, 451, Short.MAX_VALUE).addGap(40)
						.addComponent(btnNextDay, GroupLayout.PREFERRED_SIZE, 154, GroupLayout.PREFERRED_SIZE)
						.addContainerGap()));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup().addContainerGap(11, Short.MAX_VALUE)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblDate, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
										.addComponent(btnNextDay, GroupLayout.PREFERRED_SIZE, 54,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(btnPreviousDay, GroupLayout.PREFERRED_SIZE, 54,
												GroupLayout.PREFERRED_SIZE)))));
		panel.setLayout(gl_panel);

		modelAvailable = new DefaultTableModel(new Object[][] {}, new String[] { "Name", "Available", "Price", "id" }) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] { false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};

		modelSelected = new DefaultTableModel(new Object[][] {}, new String[] { "Name", "Quantity", "Price", "id" }) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] { false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		tblSelected.setRowHeight(40);

		tblSelected.setShowVerticalLines(true);
		tblSelected.setShowHorizontalLines(true);
		tblSelected.setFont(new Font("SansSerif", Font.PLAIN, 20));
		tblSelected.setModel(modelSelected);
		scrollPane_1.setViewportView(tblSelected);
		tblAvailable.setRowHeight(40);
		tblAvailable.setFont(new Font("SansSerif", Font.PLAIN, 20));
		tblAvailable.setShowVerticalLines(true);
		tblAvailable.setShowHorizontalLines(true);
		tblAvailable.setModel(modelAvailable);
		scrollPane.setViewportView(tblAvailable);
		tblAvailable.removeColumn(tblAvailable.getColumnModel().getColumn(3));
		tblSelected.removeColumn(tblSelected.getColumnModel().getColumn(3));
		tblAvailable.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 20));
		tblAvailable.getTableHeader().setForeground(Color.DARK_GRAY);
		tblSelected.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 20));
		tblSelected.getTableHeader().setForeground(Color.DARK_GRAY);
		setLayout(groupLayout);

		lblSelectedEquipment.setFont(new Font("SansSerif", Font.BOLD, 25));
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 25));

		spinnerModel = new SpinnerNumberModel(0, 0, 0, 1);
		spinner.setModel(spinnerModel);
		spinner.setFont(new Font("SansSerif", Font.PLAIN, 25));
		spinner.getComponent(0).setPreferredSize(new Dimension(50, 50));
		spinner.getComponent(1).setPreferredSize(new Dimension(50, 50));
		spinner.getComponent(2).setPreferredSize(new Dimension(100, 60));
		((DefaultEditor) spinner.getEditor()).getTextField().setEditable(false);
		btnAdd.setFont(new Font("SansSerif", Font.BOLD, 25));
		btnRemove.setFont(new Font("SansSerif", Font.BOLD, 25));

		lblDate.setVerticalAlignment(SwingConstants.CENTER);
		lblDate.setHorizontalAlignment(SwingConstants.CENTER);
		lblDate.setForeground(Color.DARK_GRAY);
		lblDate.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblDate.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.GRAY, Color.DARK_GRAY));
		lblDate.setAlignmentX(0.5f);
		btnNextDay.setFont(new Font("SansSerif", Font.BOLD, 20));
		btnPreviousDay.setFont(new Font("SansSerif", Font.BOLD, 20));
		btnPreviousDay.setVisible(false);

	}

	private void createEvents() {

		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = tblSelected.getSelectedRow();
				int id = (int) modelSelected.getValueAt(i, 3);
				Equipment eq = selectedEqs.get(i);
				for (Equipment eqq : availableEqs) {
					if (eqq.getEqId() == id) {
						eqq.setQuantity(eq.getQuantity() + eqq.getQuantity());
						break;
					}
				}
				selectedEqs.remove(eq);
				modelSelected.removeRow(i);
				refreshAvailableTable();
				spinner.setValue(0);
			}
		});

		tblAvailable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				int i = tblAvailable.getSelectedRow();
				Equipment eq = availableEqs.get(i);
				spinnerModel.setMaximum(eq.getQuantity());
			}
		});

		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int i = tblAvailable.getSelectedRow();
					int id = (int) modelAvailable.getValueAt(i, 3);
					EquipmentCtr eqCtr = new EquipmentCtr();
					Equipment eqq = eqCtr.findEquipment(id);
					Equipment eq = availableEqs.get(i);
					eq.setQuantity(eq.getQuantity() - (int) spinner.getValue());
					eqq.setQuantity((int) spinner.getValue());
					eqq.setPrice(calculatePrice(eqq));
					selectedEqs.add(eqq);
					modelSelected
							.addRow(new Object[] { eqq.getName(), eqq.getQuantity(), eqq.getPrice(), eqq.getEqId() });
					refreshAvailableTable();
					spinner.setValue(0);
				} catch (Exception ex) {
					System.out.println("boule");
				}
			}
		});

		btnPreviousDay.addActionListener(new ActionListener() {
			@SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent arg0) {
				if (arrayPreRentEq.size() == day) {
					ArrayList<Equipment> se = (ArrayList<Equipment>) selectedEqs.clone();
					preRentEq = new PreRentEquipment(se, rent.getStartDate(), rent.getEndDate());
					arrayPreRentEq.add(preRentEq);
				}
				selectedEqs = arrayPreRentEq.get(day - 1).getEquipments();
				fillSelectedTable();
				previousDay();
			}
		});

		btnNextDay.addActionListener(new ActionListener() {
			@SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent arg0) {
				if (arrayPreRentEq.size() == selectedCourts.size() && btnNextDay.getText().equals("Confirm")) {
					btnNextDay.setEnabled(false);
					btnPreviousDay.setEnabled(false);
				} else {
					if (btnNextDay.getText().equals("Confirm")) {
						ArrayList<Equipment> se = (ArrayList<Equipment>) selectedEqs.clone();
						preRentEq = new PreRentEquipment(se, rent.getStartDate(), rent.getEndDate());
						arrayPreRentEq.add(preRentEq);
						selectedEqs = new ArrayList<Equipment>();
						btnNextDay.setEnabled(false);
						btnPreviousDay.setEnabled(false);
					} else {
						if (arrayPreRentEq.size() == day || arrayPreRentEq.size() == day + 1) {
							ArrayList<Equipment> se = (ArrayList<Equipment>) selectedEqs.clone();
							preRentEq = new PreRentEquipment(se, rent.getStartDate(), rent.getEndDate());
							arrayPreRentEq.add(preRentEq);
							selectedEqs = new ArrayList<Equipment>();
							modelSelected.setRowCount(0);
						} else {
							selectedEqs = arrayPreRentEq.get(day + 1).getEquipments();
							fillSelectedTable();
						}
						nextDay();
					}
				}
			}
		});

	}

	@SuppressWarnings("deprecation")
	private void fillAvailableTable() {
		if (day > 0) {
			if (selectedCourts.get(day - 1).getStartDate().getDate() != rent.getStartDate().getDate()) {
				EquipmentCtr eqCtr = new EquipmentCtr();
				SportCourt sportCourt = (SportCourt) rent.getRentable();
				availableEqs = eqCtr.getAvailableEquipment(rent.getStartDate(), rent.getEndDate(),
						sportCourt.getEqType().getEqTypeId(), 0);
			}
		}
		refreshAvailableTable();
	}

	private void refreshAvailableTable() {
		modelAvailable.setRowCount(0);
		if (!availableEqs.isEmpty()) {
			for (Equipment eq : availableEqs) {
				modelAvailable.addRow(new Object[] { eq.getName(), eq.getQuantity(), eq.getPrice(), eq.getEqId() });
			}
		}
	}

	private void fillSelectedTable() {
		modelSelected.setRowCount(0);
		for (Equipment eqq : selectedEqs) {
			modelSelected.addRow(new Object[] { eqq.getName(), eqq.getQuantity(), eqq.getPrice(), eqq.getEqId() });
		}
	}

	public static ArrayList<Equipment> getEq() {
		return selectedEqs;
	}

	@SuppressWarnings("deprecation")
	private double calculatePrice(Equipment eq) {
		Timestamp startDate = rent.getStartDate();
		Timestamp endDate = rent.getEndDate();

		int days = endDate.getHours() - startDate.getHours() + 1;
		double price = eq.getQuantity() * eq.getPrice() * days;

		return price;
	}

	private void initializeDays() {
		String date = ((SportCourt) rent.getRentable()).getSport() + " #"
				+ ((SportCourt) rent.getRentable()).getCourtId() + "   " + TimeClass.smallDate(rent.getStartDate())
				+ " " + TimeClass.comboBoxPeriod(rent.getStartDate()) + " - "
				+ TimeClass.comboBoxPeriod(rent.getEndDate());
		lblDate.setText(date);
		if (day >= selectedCourts.size() - 1) {
			btnNextDay.setText("Confirm");
		} else {
			btnNextDay.setText("Next Day");
		}
//		if (day <= 0) {
//			btnPreviousDay.setVisible(false);
//		} else {
//			btnPreviousDay.setVisible(true);
//		}
	}

	private void nextDay() {
		i--;
		day = (i - selectedCourts.size()) * -1;
		rent = selectedCourts.get(day);
		initializeDays();
		fillAvailableTable();
	}

	private void previousDay() {
		i++;
		day = (i - selectedCourts.size()) * -1;
		rent = selectedCourts.get(day);
		initializeDays();
		fillAvailableTable();
	}

	public static ArrayList<PreRentEquipment> getPreRentEq() {
		return arrayPreRentEq;
	}
}
