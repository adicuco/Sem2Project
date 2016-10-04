package GUILayer.Customer.menuPanels.dialogPanels.bookingProcessPanels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JSpinner.DefaultEditor;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;

import ControlLayer.EquipmentCtr;
import GUILayer.Customer.MainMenuCustomer;
import ModelLayer.Equipment;
import ModelLayer.SportCourt;
import javax.swing.JSpinner;
import javax.swing.JButton;
import javax.swing.SpinnerNumberModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class AddEquipmentPanel extends JPanel {

	private DefaultTableModel modelAvailable;
	private DefaultTableModel modelSelected;
	private SpinnerNumberModel spinnerModel;
	private static ArrayList<Equipment> selectedEqs;
	private ArrayList<Equipment> availableEqs;

	private final JScrollPane scrollPane = new JScrollPane();
	private final JScrollPane scrollPane_1 = new JScrollPane();
	private final JLabel lblNewLabel = new JLabel("Select from the available equipment :");
	private final JLabel lblSelectedEquipment = new JLabel("Selected equipment :");
	private final JTable tblAvailable = new JTable();
	private final JTable tblSelected = new JTable();
	private final JSpinner spinner = new JSpinner();
	private final JButton btnAdd = new JButton("Add");
	private final JButton btnRemove = new JButton("Remove");

	/**
	 * Create the panel.
	 */
	public AddEquipmentPanel(Timestamp startDate, Timestamp endDate, SportCourt sportCourt) {
		selectedEqs = new ArrayList<Equipment>();
		EquipmentCtr eqCtr = new EquipmentCtr();
		availableEqs = eqCtr.getAvailableEquipment(startDate, endDate, sportCourt.getEqType().getEqTypeId(),
				MainMenuCustomer.getCustomerId());
		initialize();
		fillAvailableTable();
		createEvents();
	}

	private void initialize() {
		setVisible(true);
		setBounds(100, 100, 697, 415);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane_1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 685, Short.MAX_VALUE)
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(lblSelectedEquipment, GroupLayout.PREFERRED_SIZE, 261, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
							.addComponent(spinner, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
							.addGap(12)
							.addComponent(btnAdd)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnRemove)
							.addGap(10))
						.addComponent(scrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 685, Short.MAX_VALUE)
						.addComponent(lblNewLabel))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(7)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
							.addComponent(btnAdd, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnRemove, GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
								.addComponent(spinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addComponent(lblSelectedEquipment, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
					.addGap(2))
		);

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
		tblSelected.setRowHeight(70);

		tblSelected.setShowVerticalLines(true);
		tblSelected.setShowHorizontalLines(true);
		tblSelected.setFont(new Font("SansSerif", Font.PLAIN, 25));
		tblSelected.setModel(modelSelected);
		scrollPane_1.setViewportView(tblSelected);
		tblAvailable.setRowHeight(70);
		tblAvailable.setFont(new Font("SansSerif", Font.PLAIN, 25));
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
				fillAvailableTable();
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
				int i = tblAvailable.getSelectedRow();
				int id = (int) modelAvailable.getValueAt(i, 3);
				EquipmentCtr eqCtr = new EquipmentCtr();
				Equipment eqq = eqCtr.findEquipment(id);
				Equipment eq = availableEqs.get(i);
				eq.setQuantity(eq.getQuantity() - (int) spinner.getValue());
				eqq.setQuantity((int) spinner.getValue());
				eqq.setPrice(calculatePrice(eqq));
				selectedEqs.add(eqq);
				modelSelected.addRow(new Object[] { eqq.getName(), eqq.getQuantity(), eqq.getPrice(), eqq.getEqId() });
				fillAvailableTable();
				spinner.setValue(0);
			}
		});
	}

	private void fillAvailableTable() {
		modelAvailable.setRowCount(0);
		if (!availableEqs.isEmpty()) {
			for (Equipment eq : availableEqs) {
				modelAvailable.addRow(new Object[] { eq.getName(), eq.getQuantity(), eq.getPrice(), eq.getEqId() });
			}
		}
	}

	public static ArrayList<Equipment> getEq() {
		return selectedEqs;
	}

	@SuppressWarnings("deprecation")
	private double calculatePrice(Equipment eq) {
		Timestamp startDate = BookingSportCourtPanel.getStartDate();
		Timestamp endDate = BookingSportCourtPanel.getEndDate();

		int days = endDate.getHours() - startDate.getHours() + 1;
		double price = eq.getQuantity() * eq.getPrice() * days;

		return price;
	}
}
