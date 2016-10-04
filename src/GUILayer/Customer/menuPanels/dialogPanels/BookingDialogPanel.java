package GUILayer.Customer.menuPanels.dialogPanels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

import ControlLayer.RentCtr;
import GUILayer.Customer.MainMenuCustomer;
import GUILayer.Customer.menuPanels.dialogPanels.rentTypePanels.EquipmentTypePanel;
import GUILayer.Customer.menuPanels.dialogPanels.rentTypePanels.RoomTypePanel;
import GUILayer.Customer.menuPanels.dialogPanels.rentTypePanels.SportCourtTypePanel;
import ModelLayer.Equipment;
import ModelLayer.Rent;
import ModelLayer.Room;
import ModelLayer.SportCourt;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class BookingDialogPanel extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Rent rent;

	private final JLabel lblInfo = new JLabel("Booking information");
	private final JLabel lblBookingDate = new JLabel("Booking Date");
	private final JLabel lblStartDate = new JLabel("Start Date");
	private final JLabel lblEndDate = new JLabel("End Date");
	private final JLabel lblTotalPrice = new JLabel("Total Price");
	private final JLabel lblAdditionalInfo = new JLabel("Additional Info");
	private final JButton btnBack = new JButton("Back");
	private final JButton btnCancelBooking = new JButton("Cancel Booking");
	private final JTextPane txtBookingDate = new JTextPane();
	private final JTextPane txtStartDate = new JTextPane();
	private final JTextPane txtEndDate = new JTextPane();
	private final JTextPane txtPrice = new JTextPane();
	private final JScrollPane scrollPane = new JScrollPane();
	private final JTextArea txtNotes = new JTextArea();
	private JPanel infoPanel = new JPanel();
	private final JPanel panel = new JPanel();

	/**
	 * Create the dialog.
	 */
	public BookingDialogPanel(Rent rent) {
		this.rent = rent;
		initializeInfoPanel();
		initialize();
		initializeRent();
		createEvents();

		getRootPane().setBorder(BorderFactory.createEtchedBorder(Color.BLACK, Color.GRAY));
		setUndecorated(true);
		//pack();
		setLocationRelativeTo(null);
		setModal(true);
		setVisible(true);
	}

	private void initialize() {
		setSize(new Dimension(838, 576));
		lblInfo.setForeground(Color.DARK_GRAY);
		lblInfo.setFont(new Font("SansSerif", Font.BOLD, 30));
		btnBack.setFont(new Font("SansSerif", Font.BOLD, 25));
		btnCancelBooking.setFont(new Font("SansSerif", Font.BOLD, 25));
		if (rent.getStartDate().before(Timestamp.valueOf(LocalDateTime.now()))) {
			btnCancelBooking.setVisible(false);
		}
		lblAdditionalInfo.setFont(new Font("SansSerif", Font.BOLD, 25));
		txtNotes.setFont(new Font("SansSerif", Font.BOLD, 25));
		txtNotes.setEnabled(false);
		txtNotes.setEditable(false);

		scrollPane.setViewportView(txtNotes);
		lblEndDate.setFont(new Font("SansSerif", Font.BOLD, 25));
		lblBookingDate.setFont(new Font("SansSerif", Font.BOLD, 25));
		lblStartDate.setFont(new Font("SansSerif", Font.BOLD, 25));
		lblTotalPrice.setFont(new Font("SansSerif", Font.BOLD, 25));
		txtPrice.setFont(new Font("SansSerif", Font.BOLD, 25));
		txtPrice.setEnabled(false);
		txtPrice.setEditable(false);
		txtEndDate.setFont(new Font("SansSerif", Font.BOLD, 25));
		txtEndDate.setEnabled(false);
		txtEndDate.setEditable(false);
		txtStartDate.setFont(new Font("SansSerif", Font.BOLD, 25));
		txtStartDate.setEnabled(false);
		txtStartDate.setEditable(false);
		txtBookingDate.setFont(new Font("SansSerif", Font.BOLD, 25));
		txtBookingDate.setEnabled(false);
		txtBookingDate.setEditable(false);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(6)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblBookingDate)
							.addGap(6)
							.addComponent(txtBookingDate, GroupLayout.PREFERRED_SIZE, 247, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblStartDate)
							.addGap(48)
							.addComponent(txtStartDate, GroupLayout.PREFERRED_SIZE, 247, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblEndDate)
							.addGap(58)
							.addComponent(txtEndDate, GroupLayout.PREFERRED_SIZE, 247, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblTotalPrice)
							.addGap(38)
							.addComponent(txtPrice, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblAdditionalInfo)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 413, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(12, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(6)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(8)
							.addComponent(lblBookingDate))
						.addComponent(txtBookingDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(12)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(8)
							.addComponent(lblStartDate))
						.addComponent(txtStartDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(8)
							.addComponent(lblEndDate))
						.addComponent(txtEndDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(35)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(8)
							.addComponent(lblTotalPrice))
						.addComponent(txtPrice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(28)
					.addComponent(lblAdditionalInfo)
					.addGap(6)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(15, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(280)
					.addComponent(lblInfo, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(361))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(8)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnBack, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)
							.addGap(56)
							.addComponent(btnCancelBooking))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 425, GroupLayout.PREFERRED_SIZE)
							.addGap(6)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(infoPanel, GroupLayout.PREFERRED_SIZE, 387, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(18, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblInfo, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 429, GroupLayout.PREFERRED_SIZE)
							.addGap(12)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(btnBack, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnCancelBooking, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE))
							.addGap(2))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(infoPanel, GroupLayout.DEFAULT_SIZE, 494, Short.MAX_VALUE)
							.addContainerGap())))
		);
		getContentPane().setLayout(groupLayout);
	}

	private void createEvents() {

		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		btnCancelBooking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int ok = JOptionPane.showConfirmDialog(null, "Are you sure you want to cancel the booking?",
						"Cancel booking.", JOptionPane.YES_NO_OPTION);
				if (ok == JOptionPane.YES_OPTION) {
					RentCtr rentCtr = new RentCtr();
					rentCtr.deleteRent(rent.getRentId(), MainMenuCustomer.getCustomerId());
					dispose();
				}
			}
		});
	}

	private void initializeRent() {
		String dateRentMade = new java.text.SimpleDateFormat("d MMMMM yyyy HH:mm", Locale.ENGLISH)
				.format(rent.getDateRentMade());
		String startDate = new java.text.SimpleDateFormat("d MMMMM yyyy HH:mm", Locale.ENGLISH)
				.format(rent.getStartDate());
		String endDate = new java.text.SimpleDateFormat("d MMMMM yyyy HH:mm", Locale.ENGLISH).format(rent.getEndDate());

		txtBookingDate.setText(dateRentMade);
		txtStartDate.setText(startDate);
		txtEndDate.setText(endDate);
		txtPrice.setText(String.valueOf(rent.gettAmount()));
		txtNotes.setText(rent.getNotes());

	}

	private void initializeInfoPanel() {
		switch (rent.getRentType()) {
		case ROOM:
			infoPanel = new RoomTypePanel((Room) rent.getRentable());
			break;
		case SPORTCOURT:
			infoPanel = new SportCourtTypePanel((SportCourt) rent.getRentable());
			break;
		case EQUIPMENT:
			infoPanel = new EquipmentTypePanel((Equipment) rent.getRentable());
			break;
		}
	}
}
