package GUILayer.Customer.menuPanels.dialogPanels.bookingProcessPanels;

import java.awt.Font;
import java.sql.Timestamp;
import java.util.Locale;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

import ModelLayer.Equipment;
import ModelLayer.SportCourt;
import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

@SuppressWarnings("serial")
public class ConfirmBookingPanel extends JPanel {

	private SportCourt sportCourt;
	private DefaultListModel<String> model = new DefaultListModel<String>();

	private final JLabel lblConfirmBooking = new JLabel("Confirm Booking");
	private final JLabel label = new JLabel("Start Date");
	private final JLabel label_1 = new JLabel("End Date");
	private final JTextPane txtStartDate = new JTextPane();
	private final JTextPane txtEndDate = new JTextPane();
	private final JLabel label_2 = new JLabel("Total Price");
	private final JTextPane txtTotalPrice = new JTextPane();
	private final JLabel lblEquipmentPrice = new JLabel("Equipment Price");
	private final JTextPane txtEqPrice = new JTextPane();
	private final JLabel lblCourtPrice = new JLabel("Court price");
	private final JTextPane txtCourtPrice = new JTextPane();
	private final JScrollPane scrollPane = new JScrollPane();
	private final JLabel lblSportCourt = new JLabel("Sport court");
	private final JTextPane txtSportCourt = new JTextPane();
	private final JLabel lblEquipment = new JLabel("Equipment");
	private final JList<String> list = new JList<String>(model);

	/**
	 * Create the panel.
	 */
	public ConfirmBookingPanel(SportCourt sportCourt) {
		this.sportCourt = sportCourt;
		initialize();
		initializeText();
	}

	private void initialize() {
		setVisible(true);
		setBounds(100, 100, 728, 434);
		lblConfirmBooking.setForeground(Color.DARK_GRAY);
		list.setFont(new Font("SansSerif", Font.PLAIN, 25));

		scrollPane.setViewportView(list);
		lblSportCourt.setFont(new Font("SansSerif", Font.BOLD, 25));
		txtSportCourt.setText("<dynamic>");
		txtSportCourt.setFont(new Font("SansSerif", Font.BOLD, 25));
		txtSportCourt.setEnabled(false);
		txtSportCourt.setEditable(false);
		lblEquipment.setFont(new Font("SansSerif", Font.BOLD, 25));
		txtCourtPrice.setText("0.0");
		txtCourtPrice.setFont(new Font("SansSerif", Font.BOLD, 25));
		txtCourtPrice.setEnabled(false);
		txtCourtPrice.setEditable(false);
		lblCourtPrice.setFont(new Font("SansSerif", Font.BOLD, 25));
		txtEqPrice.setText("0.0");
		txtEqPrice.setFont(new Font("SansSerif", Font.BOLD, 25));
		txtEqPrice.setEnabled(false);
		txtEqPrice.setEditable(false);
		lblEquipmentPrice.setFont(new Font("SansSerif", Font.BOLD, 25));
		txtTotalPrice.setText("0.0");
		txtTotalPrice.setFont(new Font("SansSerif", Font.BOLD, 25));
		txtTotalPrice.setEnabled(false);
		txtTotalPrice.setEditable(false);
		label_2.setFont(new Font("SansSerif", Font.BOLD, 25));
		txtEndDate.setText("<dynamic>");
		txtEndDate.setFont(new Font("SansSerif", Font.BOLD, 25));
		txtEndDate.setEnabled(false);
		txtEndDate.setEditable(false);
		txtStartDate.setText("<dynamic>");
		txtStartDate.setFont(new Font("SansSerif", Font.BOLD, 25));
		txtStartDate.setEnabled(false);
		txtStartDate.setEditable(false);
		label_1.setFont(new Font("SansSerif", Font.BOLD, 25));
		label.setFont(new Font("SansSerif", Font.BOLD, 25));
		lblConfirmBooking.setFont(new Font("SansSerif", Font.BOLD, 30));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addGap(16)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addGroup(groupLayout.createSequentialGroup().addGap(163)
														.addComponent(lblConfirmBooking, GroupLayout.DEFAULT_SIZE, 262,
																Short.MAX_VALUE)
														.addGap(18))
								.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup().addGroup(groupLayout
										.createParallelGroup(Alignment.TRAILING)
										.addGroup(groupLayout.createSequentialGroup()
												.addComponent(lblSportCourt, GroupLayout.PREFERRED_SIZE, 173,
														GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED, 152, Short.MAX_VALUE))
										.addGroup(groupLayout.createSequentialGroup()
												.addComponent(txtSportCourt, GroupLayout.DEFAULT_SIZE, 184,
														Short.MAX_VALUE)
												.addGap(141)))
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
												.addComponent(label_1).addComponent(label))))
								.addGap(19)
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(txtStartDate)
										.addComponent(txtEndDate, GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)))
						.addGroup(groupLayout.createSequentialGroup().addGap(12)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(lblEquipment)
										.addGroup(groupLayout.createSequentialGroup()
												.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 294,
														Short.MAX_VALUE)
												.addGap(43)
												.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
														.addGroup(groupLayout.createSequentialGroup()
																.addComponent(lblCourtPrice, GroupLayout.PREFERRED_SIZE,
																		139, GroupLayout.PREFERRED_SIZE)
																.addGap(57))
														.addComponent(lblEquipmentPrice)
														.addGroup(groupLayout.createSequentialGroup()
																.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 139,
																		GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(ComponentPlacement.RELATED, 57,
																		Short.MAX_VALUE)))
										.addGap(67)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(txtCourtPrice, GroupLayout.PREFERRED_SIZE, 86,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(txtEqPrice, GroupLayout.PREFERRED_SIZE, 86,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(txtTotalPrice, GroupLayout.PREFERRED_SIZE, 86,
														GroupLayout.PREFERRED_SIZE))))))
						.addGap(14)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addGap(6)
				.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING).addGroup(groupLayout
						.createSequentialGroup()
						.addComponent(lblConfirmBooking, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
						.addGap(6)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblSportCourt, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup().addGap(11).addComponent(label,
										GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))))
						.addComponent(txtStartDate, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup().addGap(12).addComponent(txtSportCourt,
								GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup().addGap(18)
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addComponent(txtEndDate, GroupLayout.PREFERRED_SIZE, 40,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(label_1))))
				.addGap(18).addComponent(lblEquipment, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(
						groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
								.addGroup(groupLayout.createSequentialGroup().addGap(9)
										.addComponent(lblCourtPrice, GroupLayout.PREFERRED_SIZE, 32,
												GroupLayout.PREFERRED_SIZE)
								.addGap(10)
								.addComponent(lblEquipmentPrice, GroupLayout.PREFERRED_SIZE, 32,
										GroupLayout.PREFERRED_SIZE).addGap(12)
								.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
								.addGap(47))
						.addGroup(
								groupLayout.createSequentialGroup().addGap(1)
										.addComponent(txtCourtPrice, GroupLayout.PREFERRED_SIZE, 40,
												GroupLayout.PREFERRED_SIZE)
										.addGap(2)
										.addComponent(txtEqPrice, GroupLayout.PREFERRED_SIZE, 40,
												GroupLayout.PREFERRED_SIZE)
										.addGap(4).addComponent(txtTotalPrice, GroupLayout.PREFERRED_SIZE, 40,
												GroupLayout.PREFERRED_SIZE)
										.addGap(47)))
				.addGap(31)));
		setLayout(groupLayout);
	}

	private void initializeText() {
		Timestamp startDate = BookingSportCourtPanel.getStartDate();
		Timestamp endDate = BookingSportCourtPanel.getEndDate();
		String sp = sportCourt.getSport() + " #" + sportCourt.getCourtId();
		txtSportCourt.setText(sp);
		String startDateString = new java.text.SimpleDateFormat("d MMMMM yyyy HH:mm", Locale.ENGLISH).format(startDate);
		String endDateString = new java.text.SimpleDateFormat("d MMMMM yyyy HH:mm", Locale.ENGLISH).format(endDate);
		txtStartDate.setText(startDateString);
		txtEndDate.setText(endDateString);
		txtCourtPrice.setText(String.valueOf(BookingSportCourtPanel.getCourtPrice()));
		double price = 0;
		for (Equipment eq : AddEquipmentPanel.getEq()) {
			model.addElement(eq.getQuantity() + " x " + eq.getName() + " " + eq.getPrice());
			price += eq.getPrice();
		}
		txtEqPrice.setText(String.valueOf(price));
		txtTotalPrice.setText(String.valueOf(price + BookingSportCourtPanel.getCourtPrice()));
	}
}
