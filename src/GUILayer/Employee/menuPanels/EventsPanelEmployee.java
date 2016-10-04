package GUILayer.Employee.menuPanels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Timestamp;
import java.time.LocalDateTime;
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
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import ControlLayer.CustomerCtr;
import ControlLayer.EventCtr;
import GUILayer.Customer.menuPanels.TimeClass;
import GUILayer.Customer.menuPanels.dialogPanels.EventDialogPanel;
import GUILayer.Employee.menuPanels.dialogPanels.EventTablePopupMenu;
import GUILayer.Employee.menuPanels.dialogPanels.createEventProcess.CreateEventDialogPanel;
import ModelLayer.Customer;
import ModelLayer.Event;

public class EventsPanelEmployee extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DefaultTableModel model;
	private EventCtr eventCtr = new EventCtr();
	private Timestamp now = Timestamp.valueOf(LocalDateTime.now());

	private final JLabel lblDate = new JLabel(TimeClass.getDate());
	private final JComboBox<String> cbShow = new JComboBox<String>();
	private final JLabel lblShow = new JLabel("Show:");
	private final JTextField txtCustomer = new JTextField();
	private final JButton btnCustomer = new JButton("Find Customer's Events");
	private final JButton btnEmployee = new JButton("Find Employee's Events");
	private final JTextField txtEmployee = new JTextField();
	private final JButton btnCreateEvent = new JButton("Create new Event");
	private final JSeparator separator = new JSeparator();
	private final JScrollPane scrollPane = new JScrollPane();
	private final JTable tblEvents = new JTable();
	private final static JButton btnAll = new JButton("Find All Events");

	public EventsPanelEmployee() {
		initialize();
		createEvents();
	}

	@SuppressWarnings("serial")
	private void initialize() {
		setSize(new Dimension(1117, 765));
		lblDate.setFont(new Font("SansSerif", Font.BOLD, 20));
		cbShow.setModel(new DefaultComboBoxModel<String>(
				new String[] { "Ongoing Events", "Available Events", "Upcoming Events", "All Events" }));
		cbShow.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblShow.setFont(new Font("SansSerif", Font.BOLD, 25));
		btnCustomer.setFont(new Font("SansSerif", Font.BOLD, 20));
		btnEmployee.setFont(new Font("SansSerif", Font.BOLD, 20));
		txtEmployee.setFont(new Font("SansSerif", Font.BOLD, 20));
		txtEmployee.setColumns(10);

		btnCreateEvent.setFont(new Font("SansSerif", Font.BOLD, 20));
		separator.setFont(new Font("SansSerif", Font.PLAIN, 20));
		separator.setOrientation(SwingConstants.VERTICAL);

		txtCustomer.setFont(new Font("SansSerif", Font.BOLD, 20));
		txtCustomer.setColumns(10);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout
				.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup().addContainerGap().addComponent(
												scrollPane, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup().addGap(6)
								.addComponent(lblShow, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(cbShow, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(btnAll, GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(txtCustomer, GroupLayout.PREFERRED_SIZE, 65,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(txtEmployee, GroupLayout.PREFERRED_SIZE, 65,
												GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
										.addComponent(btnEmployee, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE,
												GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(btnCustomer, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE,
												GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addGap(189)
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING).addComponent(lblDate)
										.addGroup(groupLayout.createSequentialGroup().addComponent(btnCreateEvent)
												.addPreferredGap(ComponentPlacement.RELATED)))))
								.addGap(4)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addContainerGap()
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup().addGap(5)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(txtCustomer, GroupLayout.PREFERRED_SIZE, 36,
												GroupLayout.PREFERRED_SIZE)
								.addComponent(btnCustomer, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(txtEmployee, GroupLayout.PREFERRED_SIZE, 36,
												GroupLayout.PREFERRED_SIZE)
										.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
												.addComponent(btnEmployee, GroupLayout.PREFERRED_SIZE, 36,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(btnCreateEvent, GroupLayout.PREFERRED_SIZE, 36,
														GroupLayout.PREFERRED_SIZE)))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 670, Short.MAX_VALUE))
						.addComponent(lblDate)
						.addGroup(groupLayout.createSequentialGroup().addGap(5).addComponent(lblShow,
								GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
								.addComponent(cbShow, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(btnAll, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE))
						.addComponent(separator, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE))
				.addContainerGap()));

		model = new DefaultTableModel(new Object[][] {},
				new String[] { "Name", "Start Date", "End Date", "Price", "Participants", "id" }) {
			boolean[] columnEditables = new boolean[] { false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};

		tblEvents.setModel(model);
		tblEvents.getColumnModel().getColumn(0).setResizable(false);
		tblEvents.getColumnModel().getColumn(0).setPreferredWidth(80);
		tblEvents.getColumnModel().getColumn(1).setResizable(false);
		tblEvents.getColumnModel().getColumn(2).setResizable(false);
		tblEvents.getColumnModel().getColumn(3).setMinWidth(120);
		tblEvents.getColumnModel().getColumn(3).setMaxWidth(150);
		tblEvents.getColumnModel().getColumn(3).setResizable(false);
		tblEvents.getColumnModel().getColumn(4).setMinWidth(150);
		tblEvents.getColumnModel().getColumn(4).setMaxWidth(300);
		tblEvents.getColumnModel().getColumn(4).setResizable(false);
		tblEvents.setRowHeight(70);
		tblEvents.setShowVerticalLines(true);
		tblEvents.setShowHorizontalLines(true);
		tblEvents.setFont(new Font("SansSerif", Font.PLAIN, 25));
		tblEvents.removeColumn(tblEvents.getColumnModel().getColumn(5));
		tblEvents.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 20));
		tblEvents.getTableHeader().setForeground(Color.DARK_GRAY);

		scrollPane.setViewportView(tblEvents);
		btnAll.setFont(new Font("SansSerif", Font.BOLD, 20));
		setLayout(groupLayout);
	}

	private void createEvents() {

		TimeClass.getTime(lblDate);

		btnCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				now = Timestamp.valueOf(LocalDateTime.now());
				String nr = txtCustomer.getText();
				if (!nr.equals("")) {
					int id = Integer.valueOf(nr);
					if (id > 0) {
						whatToShow(cbShow.getSelectedItem().toString(), btnCustomer.getText(), id);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Customer not found", "Error!", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		btnEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				now = Timestamp.valueOf(LocalDateTime.now());
				String nr = txtEmployee.getText();
				if (!nr.equals("")) {
					int id = Integer.valueOf(nr);
					if (id > 0) {
						whatToShow(cbShow.getSelectedItem().toString(), btnEmployee.getText(), id);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Employee not found", "Error!", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		btnAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				now = Timestamp.valueOf(LocalDateTime.now());
				whatToShow(cbShow.getSelectedItem().toString(), btnAll.getText(), 0);
			}
		});

		btnCreateEvent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CreateEventDialogPanel();
			}
		});

		tblEvents.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (SwingUtilities.isRightMouseButton(e)) {
					int r = tblEvents.rowAtPoint(e.getPoint());
					if (r >= 0 && r < tblEvents.getRowCount()) {
						new EventTablePopupMenu(tblEvents).show(e.getComponent(), e.getX(), e.getY());
						tblEvents.setRowSelectionInterval(r, r);
					} else {
						tblEvents.clearSelection();
					}
				} else {
					int i = tblEvents.getSelectedRow();
					int id = (int) model.getValueAt(i, 5);
					CustomerCtr cusCtr = new CustomerCtr();
					String nr = txtCustomer.getText();
					int cusId = 0;
					if (!nr.equals("")) {
						cusId = Integer.valueOf(nr);
					}
					Customer customer = cusCtr.findCustomer(cusId);
					Event event = eventCtr.findEvent(id, cusId);
					new EventDialogPanel(event, customer);
				}
			}
		});
	}

	private void fillTable(ArrayList<Event> events) {
		model.setRowCount(0);
		for (Event event : events) {
			String startDate = TimeClass.comboBoxDate(event.getStartDate());
			String endDate = TimeClass.comboBoxDate(event.getEndDate());
			String participants = event.getParticipantsNr() + "/" + event.getMaxParticipants();
			model.addRow(new Object[] { event.getName(), startDate, endDate, event.getPrice(), participants,
					event.getEventId() });
		}
	}

	// All Events
	private void showCustomerAllEvents(int id) {
		ArrayList<Event> events = eventCtr.findAllEvents(id);
		fillTable(events);
	}

	private void showEmployeeAllEvents(int id) {
		ArrayList<Event> events = eventCtr.findAllEvents("employeeId", String.valueOf(id));
		fillTable(events);

	}

	private void showAllAllEvents() {
		ArrayList<Event> events = eventCtr.findAllEvents();
		fillTable(events);
	}

	// Ongoing Events
	private void showCustomerOngoingEvents(int id) {
		ArrayList<Event> events = eventCtr.findCustomerCurrentEvents(now, id);
		fillTable(events);
	}

	private void showEmployeeOngoingEvents(int id) {
		ArrayList<Event> events = eventCtr.findEmployeeOngoingEvents(now, id);
		fillTable(events);
	}

	private void showAllOngoingEvents() {
		ArrayList<Event> events = eventCtr.findAllOngoingEvents(now);
		fillTable(events);
	}

	// Available Events
	private void showCustomerAvailableEvents(int id) {
		ArrayList<Event> events = eventCtr.findCustomerAvailableEvents(now, id);
		fillTable(events);
	}

	private void showEmployeeAvailableEvents(int id) {
		ArrayList<Event> events = eventCtr.findEmployeeAvailableEvents(now, id);
		fillTable(events);
	}

	private void showAllAvailableEvents() {
		ArrayList<Event> events = eventCtr.findCustomerAvailableEvents(now, 0);
		fillTable(events);
	}

	// Upcoming Events
	private void showCustomerUpcomingEvents(int id) {
		ArrayList<Event> events = eventCtr.findCustomerUpcomingEvents(now, id);
		fillTable(events);
	}

	private void showEmployeeUpcomingEvents(int id) {
		ArrayList<Event> events = eventCtr.findEmployeeUpcomingEvents(now, id);
		fillTable(events);
	}

	private void showAllUpcomingEvents() {
		ArrayList<Event> events = eventCtr.findCustomerUpcomingEvents(now, 0);
		fillTable(events);
	}

	private void whatToShow(String cb, String btn, int id) {
		switch (cb) {
		case "All Events":
			whatToShowAll(btn, id);
			break;
		case "Ongoing Events":
			whatToShowOngoing(btn, id);
			break;
		case "Available Events":
			whatToShowAvailable(btn, id);
			break;
		case "Upcoming Events":
			whatToShowUpcoming(btn, id);
			break;
		}
	}

	private void whatToShowAll(String btn, int id) {
		switch (btn) {
		case "Find Customer's Events":
			showCustomerAllEvents(id);
			break;
		case "Find Employee's Events":
			showEmployeeAllEvents(id);
			break;
		case "Find All Events":
			showAllAllEvents();
			break;
		}
	}

	private void whatToShowOngoing(String btn, int id) {
		switch (btn) {
		case "Find Customer's Events":
			showCustomerOngoingEvents(id);
			break;
		case "Find Employee's Events":
			showEmployeeOngoingEvents(id);
			break;
		case "Find All Events":
			showAllOngoingEvents();
			break;
		}
	}

	private void whatToShowAvailable(String btn, int id) {
		switch (btn) {
		case "Find Customer's Events":
			showCustomerAvailableEvents(id);
			break;
		case "Find Employee's Events":
			showEmployeeAvailableEvents(id);
			break;
		case "Find All Events":
			showAllAvailableEvents();
			break;
		}
	}

	private void whatToShowUpcoming(String btn, int id) {
		switch (btn) {
		case "Find Customer's Events":
			showCustomerUpcomingEvents(id);
			break;
		case "Find Employee's Events":
			showEmployeeUpcomingEvents(id);
			break;
		case "Find All Events":
			showAllUpcomingEvents();
			break;
		}
	}

	public static void update() {
		btnAll.doClick();
	}
}
