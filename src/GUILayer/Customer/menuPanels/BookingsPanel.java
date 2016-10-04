package GUILayer.Customer.menuPanels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Locale;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;

import ControlLayer.RentCtr;
import GUILayer.Customer.menuPanels.dialogPanels.BookingDialogPanel;
import ModelLayer.Customer;
import ModelLayer.Rent;
import java.awt.Rectangle;

public class BookingsPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private RentCtr rentCtr = new RentCtr();
	private Customer customer;
	private DefaultTableModel model;

	private final JLabel lblDate = new JLabel(TimeClass.getDate());
	private final JScrollPane scrollPane = new JScrollPane();
	private final JRadioButton rdbtnShowAll = new JRadioButton("Show All");
	private final JRadioButton rdbtnShowOngoing = new JRadioButton("Show Ongoing");
	private final JTable tblBookings = new JTable();
	private final ButtonGroup btngShowBooking = new ButtonGroup();
	private final JLabel lblShow = new JLabel("Ongoing Bookings:");

	/**
	 * Create the panel.
	 */
	public BookingsPanel(Customer customer) {
		this.customer = customer;
		initialize();
		createEvents();

		ArrayList<Rent> rents = rentCtr.findOngoingRents("customerId", String.valueOf(customer.getCustomerId()));
		if (!rents.isEmpty()) {
			showOngoingTable();
		} else {
			lblShow.setText("All Bookings:");
			rdbtnShowAll.setSelected(true);
			showFullTable();
		}
	}

	private void initialize() {
		setSize(new Dimension(1117, 765));
		lblDate.setBounds(new Rectangle(385, 6, 192, 23));
		lblDate.setFont(new Font("SansSerif", Font.BOLD, 20));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addGap(86).addComponent(rdbtnShowOngoing)
						.addPreferredGap(ComponentPlacement.RELATED, 602, Short.MAX_VALUE).addComponent(rdbtnShowAll)
						.addGap(107))
				.addGroup(
						groupLayout.createSequentialGroup().addContainerGap()
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 1105, Short.MAX_VALUE)
										.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
												.addComponent(lblShow, GroupLayout.PREFERRED_SIZE, 282,
														GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED, 578, Short.MAX_VALUE)
												.addComponent(lblDate)))
								.addContainerGap()));
		groupLayout
				.setVerticalGroup(
						groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addGroup(groupLayout.createSequentialGroup().addGap(18)
														.addComponent(lblShow))
								.addGroup(groupLayout.createSequentialGroup().addContainerGap().addComponent(lblDate)))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 645, Short.MAX_VALUE)
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(rdbtnShowOngoing)
						.addComponent(rdbtnShowAll)).addGap(18)));
		rdbtnShowOngoing.setSelected(true);
		btngShowBooking.add(rdbtnShowAll);
		btngShowBooking.add(rdbtnShowOngoing);
		model = new DefaultTableModel(new Object[][] {},
				new String[] { "Type", "Booking Date", "Start Date", "End Date", "Cost", "id" }) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] { false, false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		tblBookings.setRowHeight(70);
		tblBookings.setShowVerticalLines(true);
		tblBookings.setShowHorizontalLines(true);
		tblBookings.setModel(model);
		tblBookings.getColumnModel().getColumn(0).setResizable(false);
		tblBookings.getColumnModel().getColumn(0).setPreferredWidth(50);
		tblBookings.getColumnModel().getColumn(1).setResizable(false);
		tblBookings.getColumnModel().getColumn(2).setResizable(false);
		tblBookings.getColumnModel().getColumn(3).setResizable(false);
		tblBookings.getColumnModel().getColumn(4).setResizable(false);
		tblBookings.getColumnModel().getColumn(4).setPreferredWidth(30);
		tblBookings.setFont(new Font("SansSerif", Font.PLAIN, 25));
		tblBookings.removeColumn(tblBookings.getColumnModel().getColumn(5));
		tblBookings.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 20));
		tblBookings.getTableHeader().setForeground(Color.DARK_GRAY);

		scrollPane.setViewportView(tblBookings);
		setLayout(groupLayout);

		rdbtnShowOngoing.setFont(new Font("SansSerif", Font.BOLD, 25));
		rdbtnShowAll.setFont(new Font("SansSerif", Font.BOLD, 25));

		lblShow.setFont(new Font("SansSerif", Font.BOLD, 25));
	}

	private void createEvents() {

		TimeClass.getTime(lblDate);

		rdbtnShowAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showFullTable();
				lblShow.setText("All Bookings:");
			}
		});

		rdbtnShowOngoing.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showOngoingTable();
				lblShow.setText("Ongoing Bookings:");
			}
		});

		tblBookings.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				int i = tblBookings.getSelectedRow();
				int id = (int) model.getValueAt(i, 5);
				Rent rent = rentCtr.findRent(id);
				new BookingDialogPanel(rent);
				if (rdbtnShowAll.isSelected()) {
					showFullTable();
				} else {
					showOngoingTable();
				}
			}
		});
	}

	private void showFullTable() {
		model.setRowCount(0);
		ArrayList<Rent> rents = rentCtr.findAllRents("customerId", String.valueOf(customer.getCustomerId()));
		for (Rent rent : rents) {
			String dateRentMade = new java.text.SimpleDateFormat("d MMMMM yyyy HH:mm", Locale.ENGLISH)
					.format(rent.getDateRentMade());
			String startDate = new java.text.SimpleDateFormat("d MMMMM yyyy HH:mm", Locale.ENGLISH)
					.format(rent.getStartDate());
			String endDate = new java.text.SimpleDateFormat("d MMMMM yyyy HH:mm", Locale.ENGLISH)
					.format(rent.getEndDate());
			model.addRow(new Object[] { rent.getRentType(), dateRentMade, startDate, endDate, rent.gettAmount(),
					rent.getRentId() });
		}
	}

	private void showOngoingTable() {
		model.setRowCount(0);
		ArrayList<Rent> rents = rentCtr.findOngoingRents("customerId", String.valueOf(customer.getCustomerId()));
		if (!rents.isEmpty()) {
			for (Rent rent : rents) {
				String dateRentMade = new java.text.SimpleDateFormat("d MMMMM yyyy HH:mm", Locale.ENGLISH)
						.format(rent.getDateRentMade());
				String startDate = new java.text.SimpleDateFormat("d MMMMM yyyy HH:mm", Locale.ENGLISH)
						.format(rent.getStartDate());
				String endDate = new java.text.SimpleDateFormat("d MMMMM yyyy HH:mm", Locale.ENGLISH)
						.format(rent.getEndDate());
				model.addRow(new Object[] { rent.getRentType(), dateRentMade, startDate, endDate, rent.gettAmount(),
						rent.getRentId() });
			}
		} else {
			JOptionPane.showMessageDialog(this, "You don't have any ongoing bookings!");
			rdbtnShowAll.setSelected(true);
			showFullTable();
		}
	}
}
