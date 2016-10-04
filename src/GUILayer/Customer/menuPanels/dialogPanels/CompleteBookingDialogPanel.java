package GUILayer.Customer.menuPanels.dialogPanels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JSpinner.DefaultEditor;
import javax.swing.JTextPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import ControlLayer.RentCtr;
import GUILayer.Customer.MainMenuCustomer;
import GUILayer.Customer.menuPanels.EquipmentPanel;
import GUILayer.Customer.menuPanels.dialogPanels.rentTypePanels.EquipmentTypePanel;
import GUILayer.Customer.menuPanels.dialogPanels.rentTypePanels.SportCourtTypePanel;
import ModelLayer.Equipment;
import ModelLayer.RentType;
import ModelLayer.Rentable;
import ModelLayer.SportCourt;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class CompleteBookingDialogPanel extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private RentType rentType;
	private Timestamp startDate;
	private Timestamp endDate;
	private Rentable rentable;
	private SpinnerNumberModel spinnerModel;

	private final JLabel lblInfo = new JLabel("Complete Booking");
	private final JLabel lblStartDate = new JLabel("Start Date");
	private final JLabel lblEndDate = new JLabel("End Date");
	private final JLabel lblTotalPrice = new JLabel("Total Price");
	private final JButton btnBack = new JButton("Back");
	private final JButton btnConfirmBooking = new JButton("Confirm Booking");
	private final JTextPane txtStartDate = new JTextPane();
	private final JTextPane txtEndDate = new JTextPane();
	private final JTextPane txtPrice = new JTextPane();
	private final JLabel lblSelectQuantity = new JLabel("Select quantity");
	private final JSlider slider = new JSlider();
	private final JSpinner spinner = new JSpinner();
	private JPanel infoPanel = new JPanel();

	/**
	 * Create the dialog.
	 */
	public CompleteBookingDialogPanel(RentType rentType, Timestamp startDate, Timestamp endDate, Rentable rentable) {
		setSize(new Dimension(782, 458));
		this.rentable = rentable;
		this.rentType = rentType;
		this.startDate = startDate;
		this.endDate = endDate;
		initializeInfoPanel();
		initialize();
		initializeText();
		createEvents();

		getRootPane().setBorder(BorderFactory.createEtchedBorder(Color.BLACK, Color.GRAY));
		setUndecorated(true);
		// pack();
		setLocationRelativeTo(null);
		setModal(true);
		setVisible(true);
	}

	private void initialize() {
		lblInfo.setForeground(Color.DARK_GRAY);
		lblInfo.setFont(new Font("SansSerif", Font.BOLD, 30));
		lblStartDate.setFont(new Font("SansSerif", Font.BOLD, 25));
		lblEndDate.setFont(new Font("SansSerif", Font.BOLD, 25));
		lblTotalPrice.setFont(new Font("SansSerif", Font.BOLD, 25));
		infoPanel.setBounds(306, 75, 255, 318);

		getContentPane().add(infoPanel);
		btnBack.setFont(new Font("SansSerif", Font.BOLD, 25));
		btnConfirmBooking.setFont(new Font("SansSerif", Font.BOLD, 25));
		txtStartDate.setFont(new Font("SansSerif", Font.BOLD, 25));
		txtStartDate.setEnabled(false);
		txtStartDate.setEditable(false);
		txtEndDate.setFont(new Font("SansSerif", Font.BOLD, 25));
		txtEndDate.setEnabled(false);
		txtEndDate.setEditable(false);
		txtPrice.setFont(new Font("SansSerif", Font.BOLD, 25));
		txtPrice.setEnabled(false);
		txtPrice.setEditable(false);
		lblSelectQuantity.setFont(new Font("SansSerif", Font.BOLD, 25));
		slider.setFont(new Font("SansSerif", Font.PLAIN, 25));
		slider.setValue(0);
		slider.setMaximum(0);
		slider.setValueIsAdjusting(true);
		slider.setPaintTicks(true);
		spinnerModel = new SpinnerNumberModel(0, 0, 0, 1);
		spinner.setModel(spinnerModel);
		spinner.setFont(new Font("SansSerif", Font.PLAIN, 25));
		spinner.getComponent(0).setPreferredSize(new Dimension(70, 50));
		spinner.getComponent(1).setPreferredSize(new Dimension(70, 50));
		spinner.getComponent(2).setPreferredSize(new Dimension(40, 50));
		((DefaultEditor) spinner.getEditor()).getTextField().setEditable(false);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addGap(169).addComponent(lblInfo,
						GroupLayout.PREFERRED_SIZE, 287, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup().addGap(6)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addGroup(groupLayout.createSequentialGroup().addGap(25)
										.addComponent(lblStartDate, GroupLayout.PREFERRED_SIZE, 126,
												GroupLayout.PREFERRED_SIZE)
										.addGap(12).addComponent(txtStartDate, GroupLayout.PREFERRED_SIZE, 269,
												GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup().addGap(25).addComponent(lblEndDate)
										.addGap(30).addComponent(txtEndDate, GroupLayout.PREFERRED_SIZE, 269,
												GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup().addGap(21).addComponent(lblSelectQuantity)
								.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(spinner, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup().addGap(21).addComponent(slider,
								GroupLayout.PREFERRED_SIZE, 287, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup().addGap(25)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup().addGap(141).addComponent(txtPrice,
												GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE))
								.addComponent(lblTotalPrice, GroupLayout.PREFERRED_SIZE, 142,
										GroupLayout.PREFERRED_SIZE)))
						.addGroup(
								groupLayout.createSequentialGroup()
										.addComponent(btnBack, GroupLayout.PREFERRED_SIZE, 132,
												GroupLayout.PREFERRED_SIZE)
										.addGap(12).addComponent(btnConfirmBooking, GroupLayout.PREFERRED_SIZE, 245,
												GroupLayout.PREFERRED_SIZE)))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(infoPanel, GroupLayout.PREFERRED_SIZE, 319, GroupLayout.PREFERRED_SIZE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addGap(6)
						.addComponent(lblInfo, GroupLayout.PREFERRED_SIZE, 58,
								GroupLayout.PREFERRED_SIZE)
				.addGap(30)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
						.createSequentialGroup()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup().addGap(12).addComponent(lblStartDate,
										GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
								.addGroup(
										groupLayout.createSequentialGroup().addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(txtStartDate, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGap(12)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblEndDate, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup().addGap(1).addComponent(txtEndDate,
										GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)))
						.addGap(9)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
										.addComponent(lblSelectQuantity, GroupLayout.PREFERRED_SIZE, 26,
												GroupLayout.PREFERRED_SIZE)
										.addGap(25))
								.addGroup(groupLayout.createSequentialGroup()
										.addComponent(spinner, GroupLayout.PREFERRED_SIZE, 52,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)))
						.addComponent(slider, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE).addGap(12)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(txtPrice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup().addGap(15).addComponent(lblTotalPrice,
										GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)))
						.addGap(25).addGroup(
								groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(btnBack, GroupLayout.PREFERRED_SIZE, 58,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(btnConfirmBooking, GroupLayout.PREFERRED_SIZE, 58,
												GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup().addGap(4).addComponent(infoPanel,
								GroupLayout.PREFERRED_SIZE, 334, GroupLayout.PREFERRED_SIZE)))));
		getContentPane().setLayout(groupLayout);
	}

	private void createEvents() {

		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		btnConfirmBooking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int ok = JOptionPane.showConfirmDialog(null, "Are you sure you want to complete the booking?",
						"Confirm booking.", JOptionPane.YES_NO_OPTION);
				if (ok == JOptionPane.YES_OPTION) {
					RentCtr rentCtr = new RentCtr();
					String notes = "Succes!";
					switch (rentType) {
					case EQUIPMENT:
						notes = String.valueOf(slider.getValue());
						break;
					case SPORTCOURT:
						break;
					case ROOM:
						break;
					}
					try {
						rentCtr.insertNewRent(startDate, endDate, rentType.toString(), calculatePrice(), notes,
								MainMenuCustomer.getCustomerId(), rentable.getId());
						EquipmentPanel.update();
						dispose();
					} catch (Exception e1) {
						System.out.println(e1);
					}
				}
			}
		});

		spinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				slider.setValue((int) spinner.getValue());
				txtPrice.setText(String.valueOf(calculatePrice()));
			}
		});
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				spinner.setValue(slider.getValue());
				txtPrice.setText(String.valueOf(calculatePrice()));
			}
		});
	}

	private void initializeText() {
		String startDateString = new java.text.SimpleDateFormat("d MMMMM yyyy HH:mm", Locale.ENGLISH).format(startDate);
		String endDateString = new java.text.SimpleDateFormat("d MMMMM yyyy HH:mm", Locale.ENGLISH).format(endDate);
		txtStartDate.setText(startDateString);
		txtEndDate.setText(endDateString);
		switch (rentType) {
		case SPORTCOURT:
			slider.setVisible(false);
			spinner.setVisible(false);
			btnBack.setVisible(false);
			btnConfirmBooking.setVisible(false);
			break;
		case EQUIPMENT:
			slider.setMaximum(((Equipment) rentable).getQuantity());
			slider.setMajorTickSpacing(slider.getMaximum() / 5);
			slider.setMinorTickSpacing(slider.getMaximum() / 10);
			spinnerModel.setMaximum(slider.getMaximum());
			break;
		case ROOM:
			break;
		default:
			break;
		}
	}

	private void initializeInfoPanel() {
		switch (rentType) {
		case SPORTCOURT:
			infoPanel = new SportCourtTypePanel((SportCourt) rentable);
			break;
		case EQUIPMENT:
			infoPanel = new EquipmentTypePanel((Equipment) rentable);
			break;
		case ROOM:
			break;
		default:
			break;
		}
	}

	private double calculatePrice() {
		double price = 0;
		switch (rentType) {
		case EQUIPMENT:
			@SuppressWarnings("deprecation")
			int hours = endDate.getHours() - startDate.getHours() + 1;
			price = ((Equipment) rentable).getPrice() * slider.getValue() * hours;
			break;
		case ROOM:
			break;
		case SPORTCOURT:
			break;
		default:
			break;
		}
		return price;
	}
}
