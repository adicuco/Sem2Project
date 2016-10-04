package GUILayer.Customer.menuPanels.dialogPanels;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JToggleButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

import ControlLayer.EventCtr;
import GUILayer.Customer.menuPanels.TimeClass;
import GUILayer.Employee.menuPanels.EventsPanelEmployee;
import ModelLayer.Customer;
import ModelLayer.Event;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class EventDialogPanel extends JDialog {

	private Event event;
	private Customer customer;
	private EventCtr eventCtr = new EventCtr();

	private final JLabel lblName = new JLabel("Name");
	private final JLabel lblDescription = new JLabel("Description");
	private final JLabel lblMinParticipants = new JLabel("Min. Participants");
	private final JLabel lblMaxParticipants = new JLabel("Max. Participants");
	private final JLabel lblRegistrationStartDate = new JLabel("Registration Start Date");
	private final JLabel lblRegistrationEndDate = new JLabel("Registration End Date");
	private final JLabel lblStartDate = new JLabel("Start Date");
	private final JLabel lblEndDate = new JLabel("End Date");
	private final JLabel lblParticipantsNumber = new JLabel("Participants number");
	private final JLabel lblOrganiserInstructor = new JLabel("Organizer / Instructor");
	private final JTextPane txtEmployee = new JTextPane();
	private final JTextPane txtRegStartDate = new JTextPane();
	private final JTextPane txtRegEndDate = new JTextPane();
	private final JTextPane txtStartDate = new JTextPane();
	private final JTextPane txtEndDate = new JTextPane();
	private final JTextPane txtMax = new JTextPane();
	private final JTextPane txtMin = new JTextPane();
	private final JTextPane txtParticipants = new JTextPane();
	private final JTextArea txtDescription = new JTextArea();
	private final JToggleButton tglJoin = new JToggleButton("Join Event");
	private final JButton btnBack = new JButton("Back");
	private final JScrollPane scrollPane = new JScrollPane();
	private final JLabel lblPrice = new JLabel("Price");
	private final JTextPane txtPrice = new JTextPane();
	private final JPanel pnlLeft = new JPanel();
	private final JPanel pnlRight = new JPanel();

	/**
	 * Create the dialog.
	 */
	public EventDialogPanel(Event event, Customer customer) {
		this.event = event;
		this.customer = customer;
		initialize();
		initializeEvent();
		createEvents();

		getRootPane().setBorder(BorderFactory.createEtchedBorder(Color.BLACK, Color.GRAY));
		setUndecorated(true);
		pack();
		setLocationRelativeTo(null);
		setModal(true);
		setVisible(true);
	}

	private void initialize() {
		setTitle("Event information.");
		setSize(new Dimension(933, 482));
		lblName.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblName.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.GRAY, Color.DARK_GRAY));
		lblName.setForeground(Color.DARK_GRAY);
		lblName.setFont(new Font("SansSerif", Font.BOLD, 30));
		lblOrganiserInstructor.setFont(new Font("SansSerif", Font.BOLD, 25));
		txtEmployee.setFont(new Font("SansSerif", Font.BOLD, 25));
		txtEmployee.setEnabled(false);
		txtEmployee.setEditable(false);
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setVerticalAlignment(SwingConstants.CENTER);
		lblRegistrationStartDate.setFont(new Font("SansSerif", Font.BOLD, 25));
		txtRegStartDate.setFont(new Font("SansSerif", Font.BOLD, 23));
		txtRegStartDate.setEnabled(false);
		txtRegStartDate.setEditable(false);
		lblRegistrationEndDate.setFont(new Font("SansSerif", Font.BOLD, 25));
		txtRegEndDate.setFont(new Font("SansSerif", Font.BOLD, 23));
		txtRegEndDate.setEnabled(false);
		txtRegEndDate.setEditable(false);
		lblDescription.setFont(new Font("SansSerif", Font.BOLD, 25));
		lblPrice.setFont(new Font("SansSerif", Font.BOLD, 25));
		txtPrice.setFont(new Font("SansSerif", Font.BOLD, 25));
		txtPrice.setEnabled(false);
		txtPrice.setEditable(false);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		txtDescription.setLineWrap(true);
		txtDescription.setWrapStyleWord(true);
		scrollPane.setViewportView(txtDescription);
		txtDescription.setText((String) null);
		txtDescription.setFont(new Font("SansSerif", Font.BOLD, 25));
		txtDescription.setEnabled(false);
		txtDescription.setEditable(false);
		GroupLayout gl_pnlLeft = new GroupLayout(pnlLeft);
		gl_pnlLeft.setHorizontalGroup(gl_pnlLeft.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlLeft.createSequentialGroup()
						.addComponent(lblRegistrationStartDate, GroupLayout.PREFERRED_SIZE, 280,
								GroupLayout.PREFERRED_SIZE)
						.addGap(18)
						.addComponent(txtRegStartDate, GroupLayout.PREFERRED_SIZE, 216, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_pnlLeft.createSequentialGroup()
						.addComponent(lblPrice, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE).addGap(12)
						.addComponent(txtPrice, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_pnlLeft.createSequentialGroup().addGap(6).addComponent(lblDescription,
						GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE))
				.addGroup(
						gl_pnlLeft.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_pnlLeft.createSequentialGroup().addGap(2).addComponent(scrollPane))
								.addGroup(Alignment.LEADING, gl_pnlLeft.createSequentialGroup()
										.addComponent(lblRegistrationEndDate, GroupLayout.PREFERRED_SIZE, 273,
												GroupLayout.PREFERRED_SIZE)
										.addGap(25).addComponent(txtRegEndDate, GroupLayout.PREFERRED_SIZE, 216,
												GroupLayout.PREFERRED_SIZE))));
		gl_pnlLeft.setVerticalGroup(gl_pnlLeft.createParallelGroup(Alignment.LEADING).addGroup(gl_pnlLeft
				.createSequentialGroup().addGap(6)
				.addGroup(gl_pnlLeft.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlLeft.createSequentialGroup().addGap(9).addComponent(lblRegistrationStartDate,
								GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
						.addComponent(txtRegStartDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addGap(6)
				.addGroup(gl_pnlLeft.createParallelGroup(Alignment.LEADING)
						.addComponent(lblRegistrationEndDate, GroupLayout.PREFERRED_SIZE, 36,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(txtRegEndDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addGap(14)
				.addGroup(gl_pnlLeft.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlLeft.createSequentialGroup().addGap(5).addComponent(lblPrice))
						.addComponent(txtPrice, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
				.addGap(51).addComponent(lblDescription, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
				.addGap(20).addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
				.addContainerGap()));
		pnlLeft.setLayout(gl_pnlLeft);
		btnBack.setFont(new Font("SansSerif", Font.BOLD, 25));

		tglJoin.setFont(new Font("SansSerif", Font.BOLD, 35));
		tglJoin.setSelected(false);
		tglJoin.setText("Join Event");
		tglJoin.setBackground(Color.GREEN);
		lblParticipantsNumber.setFont(new Font("SansSerif", Font.BOLD, 25));
		txtParticipants.setFont(new Font("SansSerif", Font.BOLD, 25));
		txtParticipants.setEnabled(false);
		txtParticipants.setEditable(false);
		txtMax.setFont(new Font("SansSerif", Font.BOLD, 25));
		txtMax.setEnabled(false);
		txtMax.setEditable(false);
		lblMaxParticipants.setFont(new Font("SansSerif", Font.BOLD, 25));
		lblMinParticipants.setFont(new Font("SansSerif", Font.BOLD, 25));
		txtMin.setFont(new Font("SansSerif", Font.BOLD, 25));
		txtMin.setEnabled(false);
		txtMin.setEditable(false);
		txtEndDate.setFont(new Font("SansSerif", Font.BOLD, 23));
		txtEndDate.setEnabled(false);
		txtEndDate.setEditable(false);
		txtStartDate.setFont(new Font("SansSerif", Font.BOLD, 23));
		txtStartDate.setEnabled(false);
		txtStartDate.setEditable(false);
		lblStartDate.setFont(new Font("SansSerif", Font.BOLD, 25));
		lblEndDate.setFont(new Font("SansSerif", Font.BOLD, 25));
		GroupLayout gl_pnlRight = new GroupLayout(pnlRight);
		gl_pnlRight.setHorizontalGroup(gl_pnlRight.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
				gl_pnlRight.createSequentialGroup().addGap(6).addComponent(lblMinParticipants).addGap(100)
						.addComponent(txtMin, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE).addGap(6))
				.addGroup(Alignment.TRAILING,
						gl_pnlRight.createSequentialGroup().addGap(6).addComponent(lblMaxParticipants).addGap(94)
								.addComponent(txtMax, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
								.addGap(6))
				.addGroup(Alignment.TRAILING,
						gl_pnlRight.createSequentialGroup().addGap(6)
								.addComponent(lblParticipantsNumber, GroupLayout.PREFERRED_SIZE, 241,
										GroupLayout.PREFERRED_SIZE)
								.addGap(59)
								.addComponent(txtParticipants, GroupLayout.PREFERRED_SIZE, 80,
										GroupLayout.PREFERRED_SIZE)
								.addGap(6))
				.addGroup(gl_pnlRight.createSequentialGroup().addGap(57)
						.addComponent(tglJoin, GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE).addGap(93))
				.addGroup(gl_pnlRight.createSequentialGroup().addGap(133).addComponent(btnBack,
						GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE))
				.addGroup(Alignment.TRAILING,
						gl_pnlRight.createSequentialGroup()
								.addGroup(
										gl_pnlRight.createParallelGroup(Alignment.LEADING)
												.addGroup(Alignment.TRAILING,
														gl_pnlRight.createSequentialGroup().addGap(6)
																.addComponent(lblStartDate, GroupLayout.PREFERRED_SIZE,
																		121, GroupLayout.PREFERRED_SIZE)
																.addGap(58))
										.addGroup(gl_pnlRight.createSequentialGroup().addGap(6)
												.addComponent(lblEndDate, GroupLayout.PREFERRED_SIZE, 117,
														GroupLayout.PREFERRED_SIZE)
												.addGap(62)))
								.addGroup(gl_pnlRight.createParallelGroup(Alignment.LEADING, false)
										.addComponent(txtEndDate, Alignment.TRAILING).addComponent(txtStartDate,
												Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE))
						.addContainerGap()));
		gl_pnlRight.setVerticalGroup(gl_pnlRight.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlRight.createSequentialGroup()
						.addGroup(gl_pnlRight.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_pnlRight.createSequentialGroup().addGap(6).addComponent(lblStartDate,
										GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
						.addComponent(txtStartDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_pnlRight.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlRight.createSequentialGroup().addGap(13).addComponent(lblEndDate,
								GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_pnlRight.createSequentialGroup().addGap(7).addComponent(txtEndDate,
								GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
				.addGap(8)
				.addGroup(gl_pnlRight.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlRight.createSequentialGroup().addGap(14).addComponent(lblMinParticipants,
								GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
						.addComponent(txtMin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addGap(1)
				.addGroup(gl_pnlRight.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlRight.createSequentialGroup().addGap(17).addComponent(lblMaxParticipants,
								GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
						.addComponent(txtMax, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addGap(11)
				.addGroup(gl_pnlRight.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlRight.createSequentialGroup().addGap(17).addComponent(lblParticipantsNumber,
								GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
						.addComponent(txtParticipants, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
						.addGap(13).addComponent(tglJoin, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnBack, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)));
		pnlRight.setLayout(gl_pnlRight);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.TRAILING).addGroup(groupLayout
				.createSequentialGroup().addGap(24)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup().addGap(3).addComponent(lblOrganiserInstructor,
								GroupLayout.PREFERRED_SIZE, 259, GroupLayout.PREFERRED_SIZE))
						.addComponent(txtEmployee, GroupLayout.PREFERRED_SIZE, 249, GroupLayout.PREFERRED_SIZE))
				.addGap(169).addComponent(lblName, GroupLayout.PREFERRED_SIZE, 341, GroupLayout.PREFERRED_SIZE)
				.addGap(151))
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addComponent(pnlLeft, GroupLayout.DEFAULT_SIZE, 517, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(pnlRight, GroupLayout.DEFAULT_SIZE, 392, Short.MAX_VALUE).addGap(20)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addGap(21)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
								.addComponent(lblOrganiserInstructor, GroupLayout.PREFERRED_SIZE, 30,
										GroupLayout.PREFERRED_SIZE)
								.addGap(6)
								.addComponent(txtEmployee, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblName, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE))
				.addGap(3)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(pnlRight, GroupLayout.PREFERRED_SIZE, 375, GroupLayout.PREFERRED_SIZE)
						.addComponent(pnlLeft, GroupLayout.PREFERRED_SIZE, 375, GroupLayout.PREFERRED_SIZE))
				.addContainerGap()));
		getContentPane().setLayout(groupLayout);
	}

	private void createEvents() {
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventsPanelEmployee.update();
				dispose();
			}
		});

		tglJoin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!tglJoin.isSelected()) {
					eventCtr.deleteParticipant(event.getEventId(), customer.getCustomerId());
					tglJoin.setSelected(false);
					tglJoin.setText("Join Event");
					tglJoin.setBackground(Color.GREEN);
					event.setParticipantsNr(event.getParticipantsNr() - 1);
					String participants = event.getParticipantsNr() + "/" + event.getMaxParticipants();
					txtParticipants.setText(participants);
				} else {
					eventCtr.addParticipant(event.getEventId(), customer.getCustomerId());
					tglJoin.setSelected(true);
					tglJoin.setText("Leave Event");
					tglJoin.setBackground(Color.RED);
					event.setParticipantsNr(event.getParticipantsNr() + 1);
					String participants = event.getParticipantsNr() + "/" + event.getMaxParticipants();
					txtParticipants.setText(participants);
				}
			}
		});
	}

	private void initializeEvent() {
		String startDate = TimeClass.comboBoxDate(event.getStartDate());
		String endDate = TimeClass.comboBoxDate(event.getEndDate());
		String regStartDate = TimeClass.comboBoxDate(event.getRegStartDate());
		String regEndDate = TimeClass.comboBoxDate(event.getRegEndDate());
		String participants = event.getParticipantsNr() + "/" + event.getMaxParticipants();

		lblName.setText(event.getName());
		txtEmployee.setText(event.getEmployee().getfName() + " " + event.getEmployee().getlName());
		txtRegStartDate.setText(regStartDate);
		txtRegEndDate.setText(regEndDate);
		txtStartDate.setText(startDate);
		txtEndDate.setText(endDate);
		txtMax.setText(String.valueOf(event.getMaxParticipants()));
		txtMin.setText(String.valueOf(event.getMinParticipants()));
		txtParticipants.setText(participants);
		txtDescription.setText(event.getDescription());
		txtPrice.setText(String.valueOf(event.getPrice()));

		if (Timestamp.valueOf(LocalDateTime.now()).before(event.getRegStartDate())) {
			tglJoin.setSelected(true);
			tglJoin.setEnabled(false);
			tglJoin.setText("Upcoming.");
			tglJoin.setBackground(Color.GRAY);
		} else {
			if (Timestamp.valueOf(LocalDateTime.now()).before(event.getRegEndDate())) {
				for (Customer cust : event.getParticipants()) {
					if (customer.getCustomerId() == cust.getCustomerId()) {
						tglJoin.setSelected(true);
						tglJoin.setText("Leave Event");
						tglJoin.setBackground(Color.RED);
						break;
					}
				}
			} else {
				if (Timestamp.valueOf(LocalDateTime.now()).after(event.getEndDate())) {
					tglJoin.setSelected(true);
					tglJoin.setEnabled(false);
					tglJoin.setText("Event finished.");
					tglJoin.setBackground(Color.GRAY);
				} else {
					tglJoin.setSelected(true);
					tglJoin.setEnabled(false);
					tglJoin.setText("Registered.");
					tglJoin.setBackground(Color.GRAY);
				}
			}
		}
	}
}
