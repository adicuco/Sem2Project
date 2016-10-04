package GUILayer.Customer.menuPanels;

import java.awt.Color;
import java.awt.Component;
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
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import ControlLayer.SportCourtCtr;
import GUILayer.Customer.MainMenuCustomer;
import GUILayer.Customer.menuPanels.dialogPanels.SportCourtBookingDialogPanel;
import GUILayer.Employee.menuPanels.dialogPanels.SportCourtTablePopupMenu;
import ModelLayer.SportCourt;
import ModelLayer.Status;

public class SportCourtsPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Timestamp> days;
	private final JLabel lblDate = new JLabel(TimeClass.getDate());
	private JLabel lblSelectDate = new JLabel("Select Date");
	private JLabel lblSelectCourt = new JLabel("Select Court Type");
	private JComboBox<String> cbDate = new JComboBox<String>();
	private JComboBox<String> cbCourt = new JComboBox<String>();
	private final JScrollPane scrollPane = new JScrollPane();
	private final static JButton btnSearch = new JButton("Search");
	private JTable tblCourts;

	protected Timestamp day;
	private DefaultTableModel model;
	private ArrayList<Timestamp> period;
	private ArrayList<SportCourt> sportCourts;
	private ArrayList<Timestamp> dates;
	private final JButton btnCus = new JButton("Set ID");
	private final JTextField txtId = new JTextField();

	/**
	 * Create the panel.
	 */
	public SportCourtsPanel() {
		initialize();
		fillComboBoxes();
		createEvents();
	}

	@SuppressWarnings("serial")
	private void initialize() {
		setSize(new Dimension(1117, 765));
		lblDate.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblSelectCourt.setFont(new Font("SansSerif", Font.BOLD, 25));
		lblSelectDate.setFont(new Font("SansSerif", Font.BOLD, 25));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(16)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblSelectCourt, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblSelectDate, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addGap(76)))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(cbCourt, 0, 311, Short.MAX_VALUE)
								.addComponent(cbDate, 0, 311, Short.MAX_VALUE))
							.addGap(177)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(txtId, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnCus)
									.addGap(38)
									.addComponent(btnSearch, GroupLayout.PREFERRED_SIZE, 176, GroupLayout.PREFERRED_SIZE))
								.addComponent(lblDate)))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 1097, Short.MAX_VALUE)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addGap(10)
							.addGap(28)
							.addComponent(lblSelectDate)
							.addGap(18)
							.addComponent(lblSelectCourt))
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addGap(19)
							.addGap(19)
							.addComponent(cbDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(cbCourt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblDate, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
							.addGap(55)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnCus, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnSearch, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE))
							.addGap(0, 0, Short.MAX_VALUE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 611, Short.MAX_VALUE)
					.addGap(15))
		);
		cbCourt.setFont(new Font("SansSerif", Font.PLAIN, 25));
		cbDate.setFont(new Font("SansSerif", Font.PLAIN, 25));
		model = new DefaultTableModel(new Object[][] {},
				new String[] { "Period", "Court #1", "Court #2", "Court #3", "Court #4" }) {
			boolean[] columnEditables = new boolean[] { false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		tblCourts = new JTable() {
			@Override
			public Component prepareRenderer(TableCellRenderer renderer, int row, int col) {
				Component comp = super.prepareRenderer(renderer, row, col);
				Object value = getModel().getValueAt(row, col);
				if (value.equals(Status.UNAVAILABLE)) {
					comp.setBackground(Color.red);
				} else if (value.equals(Status.AVAILABLE)) {
					comp.setBackground(Color.green);
				} else {
					comp.setBackground(Color.WHITE);
				}
				return comp;
			}
		};
		tblCourts.setShowVerticalLines(true);
		tblCourts.setShowHorizontalLines(true);
		tblCourts.setModel(model);
		tblCourts.getColumnModel().getColumn(0).setResizable(false);
		tblCourts.getColumnModel().getColumn(0).setPreferredWidth(50);
		tblCourts.getColumnModel().getColumn(1).setResizable(false);
		tblCourts.getColumnModel().getColumn(2).setResizable(false);
		tblCourts.getColumnModel().getColumn(3).setResizable(false);
		tblCourts.getColumnModel().getColumn(4).setResizable(false);
		tblCourts.setRowSelectionAllowed(false);
		tblCourts.setRowHeight(70);
		tblCourts.setFont(new Font("SansSerif", Font.PLAIN, 25));
		tblCourts.setCellSelectionEnabled(true);
		tblCourts.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 20));
		tblCourts.getTableHeader().setForeground(Color.DARK_GRAY);

		scrollPane.setViewportView(tblCourts);
		setLayout(groupLayout);
		btnSearch.setFont(new Font("SansSerif", Font.BOLD, 25));
		txtId.setFont(new Font("SansSerif", Font.BOLD, 25));
		txtId.setColumns(10);
		btnCus.setFont(new Font("SansSerif", Font.BOLD, 25));
		btnCus.setVisible(false);
		txtId.setVisible(false);
		if (MainMenuCustomer.getCustomerId() == 0) {
			btnCus.setVisible(true);
			txtId.setVisible(true);
		}
	}

	private void createEvents() {

		TimeClass.getTime(lblDate);

		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				day = days.get(cbDate.getSelectedIndex());
				fillTimeTable(day);
			}
		});

		tblCourts.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("deprecation")
			public void mouseClicked(MouseEvent arg0) {
				if (SwingUtilities.isRightMouseButton(arg0)) {
					int c = tblCourts.columnAtPoint(arg0.getPoint());
					if (c >= 0 && c < tblCourts.getColumnCount()) {
						new SportCourtTablePopupMenu(tblCourts, sportCourts).show(arg0.getComponent(), arg0.getX(),
								arg0.getY());
						tblCourts.setColumnSelectionInterval(c, c);
					} else {
						tblCourts.clearSelection();
					}
				} else {
					int i = tblCourts.getSelectedRow();
					int j = tblCourts.getSelectedColumn();
					if (model.getValueAt(i, j).equals(Status.AVAILABLE)) {
						SportCourt sp = sportCourts.get(j - 1);
						Timestamp ts = period.get(i);
						dates = new ArrayList<Timestamp>();

						int h = 0;
						while (model.getValueAt(i, j).equals(Status.AVAILABLE)) {
							Timestamp d = (Timestamp) ts.clone();
							d.setHours(d.getHours() + h);
							d.setMinutes(50);
							dates.add(d);
							i++;
							h++;
							if (i == model.getRowCount()) {
								break;
							}
						}

						new SportCourtBookingDialogPanel(ts, dates, sp);
					}
				}
				day = days.get(cbDate.getSelectedIndex());
				fillTimeTable(day);
			}
		});

		btnCus.addActionListener(new ActionListener() {
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
	}

	private void fillComboBoxes() {
		SportCourtCtr spCtr = new SportCourtCtr();
		for (String s : spCtr.getSportTypes()) {
			cbCourt.addItem(s);
		}

		days = new ArrayList<Timestamp>();

		LocalDateTime now = LocalDateTime.now();
		Timestamp day = Timestamp.valueOf(now);
		fillTimeTable((Timestamp) day.clone());

		for (int i = 1; i <= 7; i++) {
			day = Timestamp.valueOf(now);
			days.add(day);
			cbDate.addItem(TimeClass.comboBoxDate(day));
			now = LocalDateTime.now().plusDays(i).withHour(8).withMinute(0);
		}
	}

	@SuppressWarnings("deprecation")
	private void fillTimeTable(Timestamp day) {
		model.setRowCount(0);
		Timestamp time = (Timestamp) day.clone();
		Timestamp time1 = (Timestamp) day.clone();
		period = new ArrayList<Timestamp>();

		LocalTime hours = LocalTime.of(day.getHours(), day.getMinutes());
		if (hours.getMinute() >= 50) {
			hours.plusHours(1);
		}
		if (hours.getHour() < 8) {
			hours = LocalTime.of(8, 0);
		}

		SportCourtCtr spCtr = new SportCourtCtr();

		for (int hour = hours.getHour(); hour <= 23; hour++) {
			time = (Timestamp) day.clone();
			time1 = (Timestamp) day.clone();
			time.setHours(hour);
			time1.setHours(hour);
			time.setMinutes(0);
			period.add(time);
			time1.setMinutes(50);
			String period = TimeClass.comboBoxPeriod(time) + " - " + TimeClass.comboBoxPeriod(time1);
			sportCourts = spCtr.getAvailableSportCourts(time, (String) cbCourt.getSelectedItem(),
					MainMenuCustomer.getCustomerId());

			if (sportCourts.size() == 0) {
				model.addRow(new Object[] { "PLEASE", "TRY", "TO", "SEARCH", "AGAIN" });
				break;
			} else {
				model.addRow(new Object[] { period, sportCourts.get(0).getStatus(), sportCourts.get(1).getStatus(),
						sportCourts.get(2).getStatus(), sportCourts.get(3).getStatus() });
			}
		}
	}

	public static void updateTable() {
		btnSearch.doClick();
	}
}
