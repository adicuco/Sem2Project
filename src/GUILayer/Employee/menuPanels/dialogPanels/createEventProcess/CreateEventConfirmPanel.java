package GUILayer.Employee.menuPanels.dialogPanels.createEventProcess;

import java.awt.Color;
import java.awt.Font;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

import GUILayer.Customer.menuPanels.TimeClass;
import ModelLayer.Equipment;
import ModelLayer.PreRentEquipment;
import ModelLayer.Rent;
import ModelLayer.SportCourt;

@SuppressWarnings("serial")
public class CreateEventConfirmPanel extends JPanel {
	private DefaultListModel<String> modelC = new DefaultListModel<String>();
	private DefaultListModel<String> modelEq = new DefaultListModel<String>();
	private final JLabel lblConfirmEvent = new JLabel("Create Event");
	private final JLabel label = new JLabel("Equipment");
	private final JScrollPane scrollPane = new JScrollPane();
	private final JScrollPane scrollPane_1 = new JScrollPane();
	private final JLabel lblSportCourts = new JLabel("Sport Courts");
	private final JList<String> listCourts = new JList<String>(modelC);
	private final JList<String> listEq = new JList<String>(modelEq);
	private final JLabel label_1 = new JLabel("Court price");
	private final JLabel label_2 = new JLabel("Equipment Price");
	private final JLabel label_3 = new JLabel("Total Price");
	private final JLabel lblPriceparticipant = new JLabel("Event fee");
	private final JLabel lblEventPrice = new JLabel("Event price");
	private final JTextPane txtEvent = new JTextPane();
	private final JTextPane txtCourt = new JTextPane();
	private final JTextPane txtEq = new JTextPane();
	private final JTextPane txtTotal = new JTextPane();
	private final JTextPane txtFee = new JTextPane();
	private final JLabel label_4 = new JLabel("Start Date");
	private final JLabel label_5 = new JLabel("End Date");
	private final JTextPane txtStart = new JTextPane();
	private final JTextPane txtEnd = new JTextPane();
	private final JLabel lblName = new JLabel("Name");
	private final JTextPane txtName = new JTextPane();

	private static double courtPrice = 0;
	private static double eqPrice = 0;
	private static double eventFee = 0;
	private double total = 0;

	/**
	 * Create the panel.
	 */
	public CreateEventConfirmPanel() {
		initialize();
		fillLists();
		calculatePrice();
		initializeTxt();
	}

	private void initialize() {
		setVisible(true);
		setBounds(100, 100, 856, 591);
		setLayout(null);
		lblConfirmEvent.setForeground(Color.DARK_GRAY);
		lblConfirmEvent.setFont(new Font("SansSerif", Font.BOLD, 30));
		lblConfirmEvent.setBounds(294, 6, 209, 69);

		add(lblConfirmEvent);
		label.setFont(new Font("SansSerif", Font.BOLD, 25));
		label.setBounds(333, 93, 127, 33);

		add(label);
		scrollPane.setBounds(6, 129, 262, 456);

		add(scrollPane);
		listCourts.setFont(new Font("SansSerif", Font.PLAIN, 15));

		scrollPane.setViewportView(listCourts);
		scrollPane_1.setBounds(269, 129, 262, 456);

		add(scrollPane_1);
		listEq.setFont(new Font("SansSerif", Font.PLAIN, 15));

		scrollPane_1.setViewportView(listEq);
		lblSportCourts.setFont(new Font("SansSerif", Font.BOLD, 25));
		lblSportCourts.setBounds(58, 93, 152, 33);

		add(lblSportCourts);
		label_1.setFont(new Font("SansSerif", Font.BOLD, 25));
		label_1.setBounds(543, 389, 139, 32);

		add(label_1);
		label_2.setFont(new Font("SansSerif", Font.BOLD, 25));
		label_2.setBounds(543, 431, 196, 32);

		add(label_2);
		label_3.setFont(new Font("SansSerif", Font.BOLD, 25));
		label_3.setBounds(543, 475, 139, 32);

		add(label_3);
		lblPriceparticipant.setFont(new Font("SansSerif", Font.BOLD, 25));
		lblPriceparticipant.setBounds(543, 531, 112, 32);

		add(lblPriceparticipant);
		lblEventPrice.setFont(new Font("SansSerif", Font.BOLD, 25));
		lblEventPrice.setBounds(543, 345, 139, 32);

		add(lblEventPrice);
		txtEvent.setText("0.0");
		txtEvent.setFont(new Font("SansSerif", Font.BOLD, 20));
		txtEvent.setEnabled(false);
		txtEvent.setEditable(false);
		txtEvent.setBounds(764, 337, 86, 40);

		add(txtEvent);
		txtCourt.setText("0.0");
		txtCourt.setFont(new Font("SansSerif", Font.BOLD, 20));
		txtCourt.setEnabled(false);
		txtCourt.setEditable(false);
		txtCourt.setBounds(764, 379, 86, 40);

		add(txtCourt);
		txtEq.setText("0.0");
		txtEq.setFont(new Font("SansSerif", Font.BOLD, 20));
		txtEq.setEnabled(false);
		txtEq.setEditable(false);
		txtEq.setBounds(764, 423, 86, 40);

		add(txtEq);
		txtTotal.setText("0.0");
		txtTotal.setFont(new Font("SansSerif", Font.BOLD, 20));
		txtTotal.setEnabled(false);
		txtTotal.setEditable(false);
		txtTotal.setBounds(764, 467, 86, 40);

		add(txtTotal);
		txtFee.setText("0.0");
		txtFee.setFont(new Font("SansSerif", Font.BOLD, 20));
		txtFee.setEnabled(false);
		txtFee.setEditable(false);
		txtFee.setBounds(764, 523, 86, 40);

		add(txtFee);
		label_4.setFont(new Font("SansSerif", Font.BOLD, 25));
		label_4.setBounds(543, 187, 118, 29);

		add(label_4);
		label_5.setFont(new Font("SansSerif", Font.BOLD, 25));
		label_5.setBounds(543, 258, 108, 33);

		add(label_5);
		txtStart.setFont(new Font("SansSerif", Font.BOLD, 25));
		txtStart.setEnabled(false);
		txtStart.setEditable(false);
		txtStart.setBounds(543, 217, 236, 40);

		add(txtStart);
		txtEnd.setFont(new Font("SansSerif", Font.BOLD, 25));
		txtEnd.setEnabled(false);
		txtEnd.setEditable(false);
		txtEnd.setBounds(543, 287, 236, 40);

		add(txtEnd);
		lblName.setFont(new Font("SansSerif", Font.BOLD, 25));
		lblName.setBounds(672, 95, 67, 29);

		add(lblName);
		txtName.setFont(new Font("SansSerif", Font.BOLD, 25));
		txtName.setEnabled(false);
		txtName.setEditable(false);
		txtName.setBounds(543, 129, 307, 40);

		add(txtName);
	}

	private void fillLists() {
		for (Rent rent : CreateEventSportCourtPanel.getSelectedCourts()) {
			String sp = ((SportCourt) rent.getRentable()).getSport() + " #"
					+ ((SportCourt) rent.getRentable()).getCourtId();
			String add = sp + " | " + TimeClass.smallDate(rent.getStartDate()) + " "
					+ TimeClass.comboBoxPeriod(rent.getStartDate()) + " - "
					+ TimeClass.comboBoxPeriod(rent.getEndDate());
			modelC.addElement(add);
			courtPrice += rent.gettAmount();
		}

		for (PreRentEquipment preRentEq : CreateEventEquipmentPanel.getPreRentEq()) {
			for (Equipment eq : preRentEq.getEquipments()) {
				String eqq = eq.getQuantity() + " x " + eq.getName();
				String add = eqq + " | " + TimeClass.smallDate(preRentEq.getStartDate()) + " "
						+ TimeClass.comboBoxPeriod(preRentEq.getStartDate()) + " - "
						+ TimeClass.comboBoxPeriod(preRentEq.getEndDate());
				modelEq.addElement(add);
				eqPrice += eq.getPrice();
			}
		}
	}

	private void calculatePrice() {
		total = CreateEventInformationPanel.getPrice() + courtPrice + eqPrice;
		eventFee = total / CreateEventInformationPanel.getMin();
	}

	private void initializeTxt() {
		txtName.setText(CreateEventInformationPanel.geName());
		txtStart.setText(TimeClass.comboBoxDate(CreateEventInformationPanel.getStartDate()));
		txtEnd.setText(TimeClass.comboBoxDate(CreateEventInformationPanel.getEndDate()));
		txtEvent.setText(String.valueOf(CreateEventInformationPanel.getPrice()));
		txtCourt.setText(String.valueOf(courtPrice));
		txtEq.setText(String.valueOf(eqPrice));
		txtTotal.setText(String.valueOf(total));
		txtFee.setText(String.valueOf(eventFee));
	}

	public static double getFinalPrice() {
		return eventFee;
	}
}
