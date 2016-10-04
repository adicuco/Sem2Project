package GUILayer.Customer.menuPanels.dialogPanels;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;

import ControlLayer.RentCtr;
import GUILayer.Customer.MainMenuCustomer;
import GUILayer.Customer.menuPanels.dialogPanels.bookingProcessPanels.AddEquipmentPanel;
import GUILayer.Customer.menuPanels.dialogPanels.bookingProcessPanels.BookingSportCourtPanel;
import GUILayer.Customer.menuPanels.dialogPanels.bookingProcessPanels.ConfirmBookingPanel;
import ModelLayer.SportCourt;

@SuppressWarnings("serial")
public class SportCourtBookingDialogPanel extends JDialog {

	private final JPanel pnlBookingProcess = new JPanel();
	private CardLayout cardLayout = new CardLayout();
	private JPanel pnlSportCourt;
	private JPanel pnlAddEquipment;
	private JPanel pnlConfirm;
	private Timestamp startDate;
	private ArrayList<Timestamp> endDate;
	private SportCourt sportCourt;
	private int position;

	private final JPanel pnlButtons = new JPanel();
	private final JButton btnCancel = new JButton("Cancel");
	private final JButton btnNext = new JButton("Add Equipment");
	private final JButton btnBack = new JButton("Confirm Booking");

	/**
	 * Create the dialog.
	 */
	public SportCourtBookingDialogPanel(Timestamp startDate, ArrayList<Timestamp> endDate, SportCourt sportCourt) {
		this.startDate = startDate;
		this.endDate = endDate;
		this.sportCourt = sportCourt;
		position = 1;
		initialize();
		createEvents();

		getRootPane().setBorder(BorderFactory.createEtchedBorder(Color.BLACK, Color.GRAY));
		setUndecorated(true);
		// pack();
		setLocationRelativeTo(null);
		setModal(true);
		setVisible(true);
	}

	private void initialize() {
		setBounds(100, 100, 872, 660);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(pnlButtons, GroupLayout.DEFAULT_SIZE, 584, Short.MAX_VALUE)
								.addGroup(groupLayout.createSequentialGroup().addGap(6)
										.addComponent(pnlBookingProcess, GroupLayout.DEFAULT_SIZE, 572, Short.MAX_VALUE)
										.addGap(6)))
						.addGap(0)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addComponent(pnlBookingProcess, GroupLayout.DEFAULT_SIZE, 308, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(pnlButtons, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
						.addGap(0)));

		pnlBookingProcess.setLayout(cardLayout);
		pnlSportCourt = new BookingSportCourtPanel(startDate, endDate, sportCourt);
		pnlBookingProcess.add(pnlSportCourt, "SportCourt");
		cardLayout.show(pnlBookingProcess, "SportCourt");

		GroupLayout gl_pnlButtons = new GroupLayout(pnlButtons);
		gl_pnlButtons
				.setHorizontalGroup(
						gl_pnlButtons.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
								gl_pnlButtons.createSequentialGroup().addContainerGap()
										.addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 110,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED, 307, Short.MAX_VALUE)
										.addComponent(btnBack).addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(btnNext).addContainerGap()));
		gl_pnlButtons.setVerticalGroup(gl_pnlButtons.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlButtons.createSequentialGroup()
						.addComponent(btnCancel, GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE).addGap(9))
				.addGroup(gl_pnlButtons.createSequentialGroup()
						.addComponent(btnBack, GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE).addContainerGap())
				.addGroup(Alignment.TRAILING, gl_pnlButtons.createSequentialGroup()
						.addComponent(btnNext, GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE).addContainerGap()));
		pnlButtons.setLayout(gl_pnlButtons);
		getContentPane().setLayout(groupLayout);

		btnBack.setFont(new Font("SansSerif", Font.BOLD, 25));
		btnCancel.setFont(new Font("SansSerif", Font.BOLD, 25));
		btnNext.setFont(new Font("SansSerif", Font.BOLD, 25));
	}

	private void createEvents() {

		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});

		btnNext.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				position++;
				switch (position) {
				case 1:
					btnBack.setVisible(true);
					btnNext.doClick();
					break;
				case 2:
					btnBack.setVisible(true);
					pnlAddEquipment = new AddEquipmentPanel(startDate, BookingSportCourtPanel.getEndDate(), sportCourt);
					pnlBookingProcess.add(pnlAddEquipment, "AddEquipment");
					cardLayout.show(pnlBookingProcess, "AddEquipment");
					btnNext.setText("Next");
					btnBack.setText("Back");
					break;
				case 3:
					btnBack.setVisible(true);
					pnlConfirm = new ConfirmBookingPanel(sportCourt);
					pnlBookingProcess.add(pnlConfirm, "Confirm");
					cardLayout.show(pnlBookingProcess, "Confirm");
					btnNext.setText("Confirm Booking");
					btnBack.setText("Back");
					break;
				case 4:
					int ok = JOptionPane.showConfirmDialog(null, "Are you sure you want to complete the booking?",
							"Confirm booking.", JOptionPane.YES_NO_OPTION);
					if (ok == JOptionPane.YES_OPTION) {
						RentCtr rentCtr = new RentCtr();
						String notes = "+" + AddEquipmentPanel.getEq().size() + " pcs. of equipment";
						try {
							Timestamp end = BookingSportCourtPanel.getEndDate();
							startDate.setMinutes(0);
							end.setMinutes(50);
							rentCtr.insertSportCourtWithEquipment(startDate, end,
									BookingSportCourtPanel.getCourtPrice(), notes, MainMenuCustomer.getCustomerId(),
									sportCourt.getCourtId(), AddEquipmentPanel.getEq());
							dispose();
						} catch (Exception e1) {
							System.out.println(e1);
						}
					}
					break;
				}
			}
		});

		btnBack.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				position--;
				switch (position) {
				case 0:
					int ok = JOptionPane.showConfirmDialog(null,
							"You will only book the sport court. Are you sure you want to complete the booking?",
							"Confirm booking.", JOptionPane.YES_NO_OPTION);
					if (ok == JOptionPane.YES_OPTION) {
						RentCtr rentCtr = new RentCtr();
						String notes = "No equipment.";
						try {
							Timestamp end = BookingSportCourtPanel.getEndDate();
							startDate.setMinutes(0);
							end.setMinutes(50);
							rentCtr.insertNewRent(startDate, end, "SPORTCOURT", BookingSportCourtPanel.getCourtPrice(),
									notes, MainMenuCustomer.getCustomerId(), sportCourt.getCourtId());
							dispose();
						} catch (Exception e1) {
							System.out.println(e1);
						}
					}
					break;
				case 1:
					cardLayout.show(pnlBookingProcess, "SportCourt");
					btnBack.setVisible(false);
					btnNext.setText("Next");
					break;
				case 2:
					cardLayout.show(pnlBookingProcess, "AddEquipment");
					btnBack.setVisible(true);
					btnNext.setText("Next");
					btnBack.setText("Back");
				}
			}
		});
	}

}
