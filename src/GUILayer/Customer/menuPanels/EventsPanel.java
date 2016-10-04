package GUILayer.Customer.menuPanels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;

import ControlLayer.EventCtr;
import GUILayer.Customer.MainMenuCustomer;
import GUILayer.Customer.menuPanels.dialogPanels.EventDialogPanel;
import ModelLayer.Customer;
import ModelLayer.Event;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EventsPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Customer customer;
	private DefaultTableModel model;
	private EventCtr eventCtr = new EventCtr();
	private Timestamp now = Timestamp.valueOf(LocalDateTime.now());

	private final JLabel lblDate = new JLabel(TimeClass.getDate());
	private final JScrollPane scrollPane = new JScrollPane();
	private final JTable tblEvents = new JTable();
	private final JRadioButton rdbtnMyCurrentEvents = new JRadioButton("Show My Current Events");
	private final JRadioButton rdbtnAvailable = new JRadioButton("Show All Available Events");
	private final JRadioButton rdbtnShowAllMy = new JRadioButton("Show All My Events");
	private final ButtonGroup btngShowEvent = new ButtonGroup();
	private final JLabel lblShow = new JLabel("My Current Events:");

	/**
	 * Create the panel.
	 */
	public EventsPanel(Customer customer) {
		this.customer = customer;
		initialize();
		createEvents();
		showMyCurrentEvents();
	}

	@SuppressWarnings("serial")
	private void initialize() {
		setSize(new Dimension(1117, 765));
		lblDate.setFont(new Font("SansSerif", Font.BOLD, 20));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.TRAILING).addGroup(groupLayout
				.createSequentialGroup().addContainerGap()
				.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 1105, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup().addComponent(lblShow)
								.addPreferredGap(ComponentPlacement.RELATED, 632, Short.MAX_VALUE)
								.addComponent(lblDate))
						.addGroup(Alignment.LEADING,
								groupLayout.createSequentialGroup()
										.addComponent(rdbtnMyCurrentEvents, GroupLayout.DEFAULT_SIZE, 362,
												Short.MAX_VALUE)
										.addGap(31)
										.addComponent(rdbtnAvailable, GroupLayout.DEFAULT_SIZE, 379, Short.MAX_VALUE)
										.addGap(30)
										.addComponent(rdbtnShowAllMy, GroupLayout.DEFAULT_SIZE, 303, Short.MAX_VALUE)))
				.addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addGap(6)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
										.addComponent(lblDate, GroupLayout.PREFERRED_SIZE, 23,
												GroupLayout.PREFERRED_SIZE)
										.addGap(29))
						.addGroup(groupLayout.createSequentialGroup().addComponent(lblShow)
								.addPreferredGap(ComponentPlacement.RELATED)))
				.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 629, GroupLayout.PREFERRED_SIZE).addGap(18)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(rdbtnMyCurrentEvents, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)
						.addComponent(rdbtnShowAllMy, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)
						.addComponent(rdbtnAvailable, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE))
						.addGap(27)));
		scrollPane.setFont(new Font("SansSerif", Font.PLAIN, 25));
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
		tblEvents.getColumnModel().getColumn(3).setResizable(false);
		tblEvents.getColumnModel().getColumn(3).setPreferredWidth(20);
		tblEvents.getColumnModel().getColumn(4).setPreferredWidth(30);
		tblEvents.getColumnModel().getColumn(4).setResizable(false);
		tblEvents.setRowHeight(70);
		tblEvents.setShowVerticalLines(true);
		tblEvents.setShowHorizontalLines(true);
		tblEvents.setFont(new Font("SansSerif", Font.PLAIN, 25));
		tblEvents.removeColumn(tblEvents.getColumnModel().getColumn(5));
		tblEvents.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 20));
		tblEvents.getTableHeader().setForeground(Color.DARK_GRAY);

		scrollPane.setViewportView(tblEvents);
		setLayout(groupLayout);

		btngShowEvent.add(rdbtnShowAllMy);
		rdbtnShowAllMy.setFont(new Font("SansSerif", Font.BOLD, 25));
		btngShowEvent.add(rdbtnAvailable);
		rdbtnAvailable.setFont(new Font("SansSerif", Font.BOLD, 25));
		btngShowEvent.add(rdbtnMyCurrentEvents);
		rdbtnMyCurrentEvents.setFont(new Font("SansSerif", Font.BOLD, 25));
		rdbtnMyCurrentEvents.setSelected(true);
		lblShow.setFont(new Font("SansSerif", Font.BOLD, 25));
	}

	private void createEvents() {

		TimeClass.getTime(lblDate);

		rdbtnMyCurrentEvents.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showMyCurrentEvents();
				lblShow.setText("My Current Events:");
			}
		});

		rdbtnAvailable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showAllAvailableEvents();
				lblShow.setText("Available Events:");
			}
		});

		rdbtnShowAllMy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showAllMyEvents();
				lblShow.setText("My Events:");
			}
		});

		tblEvents.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				int i = tblEvents.getSelectedRow();
				int id = (int) model.getValueAt(i, 5);
				Event event = eventCtr.findEvent(id, customer.getCustomerId());
				new EventDialogPanel(event, customer);
				if (rdbtnMyCurrentEvents.isSelected()) {
					showMyCurrentEvents();
				} else if (rdbtnAvailable.isSelected()) {
					showAllAvailableEvents();
				} else {
					showAllMyEvents();
				}
			}
		});
	}

	private void showMyCurrentEvents() {
		model.setRowCount(0);
		ArrayList<Event> events = eventCtr.findCustomerCurrentEvents(now, customer.getCustomerId());
		for (Event event : events) {
			String startDate = TimeClass.comboBoxDate(event.getStartDate());
			String endDate = TimeClass.comboBoxDate(event.getEndDate());
			String participants = event.getParticipantsNr() + "/" + event.getMaxParticipants();
			model.addRow(new Object[] { event.getName(), startDate, endDate, event.getPrice(), participants,
					event.getEventId() });
		}
	}

	private void showAllAvailableEvents() {
		model.setRowCount(0);
		ArrayList<Event> events = eventCtr.findCustomerAvailableEvents(now, MainMenuCustomer.getCustomerId());
		for (Event event : events) {
			String startDate = TimeClass.comboBoxDate(event.getStartDate());
			String endDate = TimeClass.comboBoxDate(event.getEndDate());
			String participants = event.getParticipantsNr() + "/" + event.getMaxParticipants();
			model.addRow(new Object[] { event.getName(), startDate, endDate, event.getPrice(), participants,
					event.getEventId() });
		}
	}

	private void showAllMyEvents() {
		model.setRowCount(0);
		ArrayList<Event> events = eventCtr.findAllEvents(customer.getCustomerId());
		for (Event event : events) {
			String startDate = TimeClass.comboBoxDate(event.getStartDate());
			String endDate = TimeClass.comboBoxDate(event.getEndDate());
			String participants = event.getParticipantsNr() + "/" + event.getMaxParticipants();
			model.addRow(new Object[] { event.getName(), startDate, endDate, event.getPrice(), participants,
					event.getEventId() });
		}
	}
}
