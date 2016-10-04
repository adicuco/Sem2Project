package GUILayer.Employee.menuPanels.dialogPanels.createEventProcess;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import ControlLayer.SportCourtCtr;
import GUILayer.Customer.menuPanels.TimeClass;
import ModelLayer.Customer;
import ModelLayer.Rent;
import ModelLayer.RentType;
import ModelLayer.SportCourt;
import ModelLayer.Status;

@SuppressWarnings("serial")
public class CreateEventSportCourtPanel extends JPanel {

	private ArrayList<Timestamp> period1;
	private ArrayList<Timestamp> period2;
	private ArrayList<Timestamp> days = new ArrayList<Timestamp>();
	private DefaultTableModel model;
	private DefaultTableModel modelSelected;
	private ArrayList<SportCourt> sportCourts;
	private static ArrayList<Rent> selectedCourts;
	private static Timestamp startDate;
	private static Timestamp endDate;
	private ArrayList<Point> xy;

	private final JLabel lblSelectStartingHour = new JLabel("Select Starting hour");
	private final JLabel lblSelectEndingHour = new JLabel("Select Ending hour");
	private final JLabel lblSelectCourtType = new JLabel("Select Court type");
	private final JComboBox<String> cbStart = new JComboBox<String>();
	private final JComboBox<String> cbEnd = new JComboBox<String>();
	private final JComboBox<String> cbType = new JComboBox<String>();
	private final JButton btnSearch = new JButton("Search Available");
	private final JScrollPane scrollPane = new JScrollPane();
	private JTable tblCourts;
	private final JScrollPane scrollPane_1 = new JScrollPane();
	private final JTable tblSelected = new JTable();

	/**
	 * Create the panel.
	 */
	public CreateEventSportCourtPanel() {
		selectedCourts = new ArrayList<Rent>();
		xy = new ArrayList<Point>();
		initialize();
		fillComboBoxes();
		createEvents();
	}

	private void initialize() {
		setVisible(true);
		setBounds(100, 100, 856, 591);

		setLayout(null);
		lblSelectStartingHour.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblSelectStartingHour.setBounds(6, 6, 189, 26);

		add(lblSelectStartingHour);
		lblSelectEndingHour.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblSelectEndingHour.setBounds(6, 44, 189, 26);

		add(lblSelectEndingHour);
		lblSelectCourtType.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblSelectCourtType.setBounds(6, 82, 164, 26);

		add(lblSelectCourtType);

		cbStart.setFont(new Font("SansSerif", Font.PLAIN, 25));
		cbStart.setBounds(207, 6, 105, 37);

		add(cbStart);
		cbEnd.setFont(new Font("SansSerif", Font.PLAIN, 25));
		cbEnd.setBounds(207, 44, 105, 37);

		add(cbEnd);
		cbType.setFont(new Font("SansSerif", Font.PLAIN, 25));
		cbType.setBounds(207, 82, 156, 37);

		add(cbType);
		btnSearch.setFont(new Font("SansSerif", Font.BOLD, 25));
		btnSearch.setBounds(557, 71, 240, 48);

		add(btnSearch);
		scrollPane.setBounds(6, 120, 844, 274);

		add(scrollPane);
		model = new DefaultTableModel(new Object[][] {},
				new String[] { "Day", "Court #1", "Court #2", "Court #3", "Court #4" }) {
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
				} else if (value.equals(Status.SELECTED)) {
					comp.setBackground(Color.ORANGE);
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
		tblCourts.setRowHeight(50);
		tblCourts.setFont(new Font("SansSerif", Font.PLAIN, 23));
		tblCourts.setCellSelectionEnabled(true);
		tblCourts.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 20));
		tblCourts.getTableHeader().setForeground(Color.DARK_GRAY);

		scrollPane.setViewportView(tblCourts);
		scrollPane_1.setBounds(6, 406, 844, 179);

		add(scrollPane_1);
		modelSelected = new DefaultTableModel(new Object[][] {},
				new String[] { "Day", "Period", "Court", "Court Price", "Total" }) {
			boolean[] columnEditables = new boolean[] { false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};

		tblSelected.setShowVerticalLines(true);
		tblSelected.setShowHorizontalLines(true);
		tblSelected.setModel(modelSelected);
		tblSelected.getColumnModel().getColumn(0).setResizable(false);
		tblSelected.getColumnModel().getColumn(0).setPreferredWidth(50);
		tblSelected.getColumnModel().getColumn(1).setResizable(false);
		tblSelected.getColumnModel().getColumn(2).setResizable(false);
		tblSelected.getColumnModel().getColumn(3).setResizable(false);
		tblSelected.getColumnModel().getColumn(4).setResizable(false);
		tblSelected.setRowSelectionAllowed(false);
		tblSelected.setRowHeight(50);
		tblSelected.setFont(new Font("SansSerif", Font.PLAIN, 23));
		tblSelected.setCellSelectionEnabled(true);
		tblSelected.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 20));
		tblSelected.getTableHeader().setForeground(Color.DARK_GRAY);
		scrollPane_1.setViewportView(tblSelected);
	}

	private void fillComboBoxes() {
		SportCourtCtr spCtr = new SportCourtCtr();
		for (String s : spCtr.getSportTypes()) {
			cbType.addItem(s);
		}
		fillPeriodCB(CreateEventInformationPanel.getStartDate());
	}

	@SuppressWarnings({ "deprecation" })
	private void fillPeriodCB(Timestamp day) {
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
			cbStart.addItem(TimeClass.comboBoxPeriod(time));
			time1.setMinutes(50);
			period2.add(time1);
			cbEnd.addItem(TimeClass.comboBoxPeriod(time1));
		}
	}

	private void createEvents() {

		btnSearch.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				model.setRowCount(0);
				startDate = CreateEventInformationPanel.getStartDate();
				startDate.setHours(period1.get(cbStart.getSelectedIndex()).getHours());
				endDate = CreateEventInformationPanel.getEndDate();
				endDate.setHours(period2.get(cbEnd.getSelectedIndex()).getHours());
				endDate.setMinutes(period2.get(cbEnd.getSelectedIndex()).getMinutes());
				Timestamp ts = (Timestamp) startDate.clone();
				while (ts.before(endDate)) {
					fillTimeTable(ts, endDate);
					days.add((Timestamp) ts.clone());
					ts.setDate(ts.getDate() + 1);
				}
			}
		});

		cbStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cbEnd.setSelectedIndex(cbStart.getSelectedIndex());
			}
		});

		tblCourts.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("deprecation")
			public void mouseClicked(MouseEvent arg0) {
				int i = tblCourts.getSelectedRow();
				int j = tblCourts.getSelectedColumn();
				if (model.getValueAt(i, j).equals(Status.AVAILABLE)) {
					model.setValueAt(Status.SELECTED, i, j);
					Point p = new Point(i, j);
					xy.add(p);
					SportCourt sp = sportCourts.get(j - 1);
					startDate.setDate(days.get(i).getDate());
					endDate.setDate(days.get(i).getDate());
					Rent preRent = new Rent(Timestamp.valueOf(LocalDateTime.now()), (Timestamp) startDate.clone(),
							(Timestamp) endDate.clone(), RentType.SPORTCOURT, calculatePrice(sp), "", new Customer(0),
							sp);
					selectedCourts.add(preRent);
					Timestamp ts = (Timestamp) startDate.clone();
					ts.setDate(ts.getDate() + i);
					String day = TimeClass.smallDate(startDate);
					String period = TimeClass.comboBoxPeriod(startDate) + " - " + TimeClass.comboBoxPeriod(endDate);
					String court = sp.getSport() + " #" + sp.getCourtId();
					modelSelected.addRow(new Object[] { day, period, court, sp.getPrice(), calculatePrice(sp) });
				} else if (model.getValueAt(i, j).equals(Status.SELECTED)) {
					int index = -1;
					for (int a = 0; a <= xy.size() - 1; a++) {
						if (xy.get(a).x == i && xy.get(a).y == j) {
							index = a;
							break;
						}
					}
					if (index != -1) {
						model.setValueAt(Status.AVAILABLE, i, j);
						xy.remove(index);
						modelSelected.removeRow(index);
						selectedCourts.remove(index);
					}
				}
			}
		});

		tblSelected.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				int i = tblSelected.getSelectedRow();
				Point p = xy.get(i);
				model.setValueAt(Status.AVAILABLE, ((int) p.getX()), ((int) p.getY()));
				xy.remove(i);
				modelSelected.removeRow(i);
				selectedCourts.remove(i);
			}
		});
	}

	@SuppressWarnings("deprecation")
	private void fillTimeTable(Timestamp startDate, Timestamp endDate) {
		Timestamp sd = (Timestamp) startDate.clone();
		Timestamp ed = (Timestamp) endDate.clone();
		ed.setDate(sd.getDate());
		SportCourtCtr spCtr = new SportCourtCtr();

		sportCourts = spCtr.getAvailableSportCourtsDay(sd, ed, (String) cbType.getSelectedItem());
		String day = TimeClass.smallDate(sd);
		if (sportCourts.size() == 0) {
			model.addRow(new Object[] { "PLEASE", "TRY", "TO", "SEARCH", "AGAIN" });
		} else {
			model.addRow(new Object[] { day, sportCourts.get(0).getStatus(), sportCourts.get(1).getStatus(),
					sportCourts.get(2).getStatus(), sportCourts.get(3).getStatus() });
		}
	}

	@SuppressWarnings("deprecation")
	private double calculatePrice(SportCourt sportCourt) {
		double price = 0;
		int hours = endDate.getHours() - startDate.getHours() + 1;
		price = sportCourt.getPrice() * hours;
		return price;
	}

	public static ArrayList<Rent> getSelectedCourts() {
		return selectedCourts;
	}
}
