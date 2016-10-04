package GUILayer.Customer.menuPanels.dialogPanels.bookingProcessPanels;

import java.awt.Font;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Locale;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;

import GUILayer.Customer.menuPanels.dialogPanels.rentTypePanels.SportCourtTypePanel;
import ModelLayer.SportCourt;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

@SuppressWarnings("serial")
public class BookingSportCourtPanel extends JPanel {
	private final JLabel label = new JLabel("Complete Booking");
	private final JLabel label_1 = new JLabel("Start Date");
	private final JLabel label_2 = new JLabel("End Date");
	private final JTextPane txtStartDate = new JTextPane();
	private final JLabel lblPrice = new JLabel("Total Price");
	private final static JTextPane txtPrice = new JTextPane();
	private SportCourt sportCourt;
	private static Timestamp startDate;
	private static ArrayList<Timestamp> endDate;
	private static JComboBox<String> cbEndDate = new JComboBox<String>();
	private JPanel pnlInfo;
	private ActionListener actionFillEndDateCB;

	/**
	 * Create the panel.
	 */
	public BookingSportCourtPanel(Timestamp startDate, ArrayList<Timestamp> endDate, SportCourt sportCourt) {
		this.sportCourt = sportCourt;
		this.startDate = startDate;
		this.endDate = endDate;
		initialize();
		createEvents();
		initializeText();
	}

	private void initialize() {
		setVisible(true);
		setBounds(100, 100, 806, 442);
		label.setFont(new Font("SansSerif", Font.BOLD, 30));
		label_1.setFont(new Font("SansSerif", Font.BOLD, 25));
		label_2.setFont(new Font("SansSerif", Font.BOLD, 25));
		txtStartDate.setText("<dynamic>");
		txtStartDate.setFont(new Font("SansSerif", Font.BOLD, 25));
		txtStartDate.setEnabled(false);
		txtStartDate.setEditable(false);
		lblPrice.setFont(new Font("SansSerif", Font.BOLD, 25));
		txtPrice.setFont(new Font("SansSerif", Font.BOLD, 25));
		txtPrice.setEnabled(false);
		txtPrice.setEditable(false);
		cbEndDate.setFont(new Font("SansSerif", Font.PLAIN, 25));

		pnlInfo = new SportCourtTypePanel(sportCourt);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addGap(18)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup().addGap(15).addComponent(label,
										GroupLayout.PREFERRED_SIZE, 269, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup().addGroup(groupLayout
								.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
										.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 124,
												GroupLayout.PREFERRED_SIZE)
										.addGap(47).addComponent(txtStartDate, GroupLayout.PREFERRED_SIZE, 242,
												GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup().addComponent(label_2).addGap(63)
										.addComponent(cbEndDate, GroupLayout.PREFERRED_SIZE, 248,
												GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
										.addComponent(lblPrice, GroupLayout.PREFERRED_SIZE, 139,
												GroupLayout.PREFERRED_SIZE)
										.addGap(45).addComponent(txtPrice, GroupLayout.PREFERRED_SIZE, 92,
												GroupLayout.PREFERRED_SIZE)))
								.addGap(12).addComponent(pnlInfo, GroupLayout.DEFAULT_SIZE, 351, Short.MAX_VALUE)))
				.addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup()
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
						.createSequentialGroup().addGap(27)
						.addComponent(label, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup().addGap(42).addComponent(label_1,
										GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup().addGap(29).addComponent(txtStartDate,
										GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup().addGap(23).addComponent(label_2,
										GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup().addGap(12).addComponent(cbEndDate,
										GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)))
						.addGap(122)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblPrice, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtPrice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup().addGap(101).addComponent(pnlInfo,
								GroupLayout.PREFERRED_SIZE, 335, GroupLayout.PREFERRED_SIZE)))
				.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		setLayout(groupLayout);
	}

	private void createEvents() {

		actionFillEndDateCB = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cbEndDate.getItemCount() > 0) {
					txtPrice.setText(String.valueOf(calculatePrice(endDate.get(cbEndDate.getSelectedIndex()))));
				}
			}
		};

	}

	private void initializeText() {
		cbEndDate.removeActionListener(actionFillEndDateCB);
		cbEndDate.removeAllItems();
		String startDateString = new java.text.SimpleDateFormat("d MMMMM yyyy HH:mm", Locale.ENGLISH).format(startDate);
		txtStartDate.setText(startDateString);
		for (Timestamp ts : endDate) {
			String endDateString = new java.text.SimpleDateFormat("d MMMMM yyyy HH:mm", Locale.ENGLISH).format(ts);
			cbEndDate.addItem(endDateString);
		}
		txtPrice.setText(String.valueOf(calculatePrice(endDate.get(cbEndDate.getSelectedIndex()))));
		cbEndDate.addActionListener(actionFillEndDateCB);

	}

	@SuppressWarnings("deprecation")
	private double calculatePrice(Timestamp ts) {
		double price = 0;
		int hours = ts.getHours() - startDate.getHours() + 1;
		price = sportCourt.getPrice() * hours;
		return price;
	}

	public static Timestamp getEndDate() {
		Timestamp ed = endDate.get(cbEndDate.getSelectedIndex());
		return ed;
	}

	public static Timestamp getStartDate() {
		return startDate;
	}

	public static double getCourtPrice() {
		double price = Double.valueOf(txtPrice.getText());
		return price;
	}
}
