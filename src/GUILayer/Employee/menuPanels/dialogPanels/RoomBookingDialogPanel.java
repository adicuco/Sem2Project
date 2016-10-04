package GUILayer.Employee.menuPanels.dialogPanels;

import javax.swing.JDialog;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;

import ControlLayer.CustomerCtr;
import ControlLayer.RentCtr;
import ControlLayer.RoomCtr;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;

public class RoomBookingDialogPanel extends JDialog{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int cusId = 0;
	private int roomId = 0;
	private Timestamp from = null;
	private Timestamp till = null;
	private final JLabel lblBookingConfirmation = new JLabel("Booking Confirmation");
	private final JLabel lblCustomerName = new JLabel("Customer Name");
	private final JLabel lblNumberOfDays = new JLabel("Number Of Days");
	private final JLabel lblFrom = new JLabel("From");
	private final JLabel lblTill = new JLabel("Till");
	private final JLabel lblPricePerNight = new JLabel("Price Per Night");
	private final JLabel lblTotal = new JLabel("Total");
	private final JButton btnCancel = new JButton("Cancel");
	private final JButton btnConfirm = new JButton("Confirm reservation");
	private final JLabel lblAddNotes = new JLabel("Additional Notes");
	private final JTextField txtCusName = new JTextField();
	private final JTextField txtNrDays = new JTextField();
	private final JTextField txtFrom = new JTextField();
	private final JTextField txtTill = new JTextField();
	private final JTextField txtPPN = new JTextField();
	private final JTextField txtTotal = new JTextField();
	private final JTextArea notes = new JTextArea();
	private RoomCtr rCtr = new RoomCtr();
	private CustomerCtr cusCtr = new CustomerCtr();
    
	
	
	public RoomBookingDialogPanel(int cusId, int roomId, Timestamp from, Timestamp till) {
		this.cusId = cusId;
		this.roomId = roomId;
		this.from = from;
		this.till = till;
		
		initialize();
		createEvents();
		getRootPane().setBorder(BorderFactory.createEtchedBorder(Color.BLACK, Color.GRAY));
		setUndecorated(true);
		pack();
		setLocationRelativeTo(null);
		setModal(true);
		setVisible(true);
		
	}
	private void initialize() {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		int nrOfDays = (int) ((till.getTime() - from.getTime()) / 86400000);
		double amount = rCtr.findRoom(roomId).getRoomPrice() * nrOfDays;
		txtTotal.setColumns(10);
		txtPPN.setColumns(10);
		txtTill.setColumns(10);
		txtFrom.setColumns(10);
		txtNrDays.setColumns(10);
		txtCusName.setColumns(10);
		lblBookingConfirmation.setFont(new Font("SansSerif", Font.BOLD, 30));
		lblAddNotes.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblCustomerName.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblFrom.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblNumberOfDays.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblPricePerNight.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblTill.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblTotal.setFont(new Font("SansSerif", Font.BOLD, 15));
		txtCusName.setFont(new Font("SansSerif", Font.BOLD, 15));
		txtFrom.setFont(new Font("SansSerif", Font.BOLD, 15));
		txtNrDays.setFont(new Font("SansSerif", Font.BOLD, 15));
		txtPPN.setFont(new Font("SansSerif", Font.BOLD, 15));
		txtTill.setFont(new Font("SansSerif", Font.BOLD, 15));
		txtTotal.setFont(new Font("SansSerif", Font.BOLD, 15));
		txtCusName.setEnabled(false);
		txtCusName.setEditable(false);
		txtFrom.setEditable(false);
		txtFrom.setEnabled(false);
		txtNrDays.setEnabled(false);
		txtNrDays.setEditable(false);
		txtPPN.setEnabled(false);
		txtPPN.setEditable(false);
		txtTill.setEnabled(false);
		txtTill.setEditable(false);
		txtTotal.setEnabled(false);
		txtTotal.setEditable(false);
		btnCancel.setFont(new Font("SansSerif", Font.BOLD, 15));	
		btnConfirm.setFont(new Font("SansSerif", Font.BOLD, 15));
		notes.setFont(new Font("SansSerif", Font.BOLD, 15));
		
		txtCusName.setText(cusCtr.findCustomer(cusId).getfName() + " " + cusCtr.findCustomer(cusId).getlName());
		txtFrom.setText(sdf.format(from));
		//txtFrom.setText(from.toString());
		txtTill.setText(sdf.format(till));
	//	txtTill.setText(till.toString());
		txtNrDays.setText(String.valueOf(nrOfDays));
		txtPPN.setText(String.valueOf(rCtr.findRoom(roomId).getRoomPrice()));
		txtTotal.setText(String.valueOf(amount));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblCustomerName)
							.addGap(18)
							.addComponent(txtCusName, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNumberOfDays)
								.addComponent(lblPricePerNight))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(txtNrDays, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(lblFrom, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
								.addComponent(txtPPN, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(73)
									.addComponent(lblTotal)
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(txtTotal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
									.addGap(18)
									.addComponent(txtFrom, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
									.addComponent(lblTill)
									.addGap(33)))
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(18)
									.addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(2)
									.addComponent(txtTill, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE)))))
					.addGap(34))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(191)
					.addComponent(lblBookingConfirmation)
					.addContainerGap(204, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(28)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnConfirm)
						.addComponent(lblAddNotes))
					.addGap(28)
					.addComponent(notes, GroupLayout.PREFERRED_SIZE, 202, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(273, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblBookingConfirmation)
					.addGap(8)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCustomerName)
						.addComponent(txtCusName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(26)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNumberOfDays)
						.addComponent(txtNrDays, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtTill, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblFrom)
						.addComponent(txtFrom, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTill))
					.addGap(40)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPricePerNight)
						.addComponent(lblTotal)
						.addComponent(txtPPN, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtTotal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(42)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblAddNotes)
							.addPreferredGap(ComponentPlacement.RELATED, 99, Short.MAX_VALUE)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnConfirm)
								.addComponent(btnCancel))
							.addGap(19))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(notes, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())))
		);
		getContentPane().setLayout(groupLayout);
	}
	
	private void createEvents(){
		
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();	
			}
		});
		
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				RentCtr rentCtr = new RentCtr();
				int nrOfDays = (int) ((till.getTime() - from.getTime()) / 86400000);
				double amount = rCtr.findRoom(roomId).getRoomPrice() * nrOfDays;
				try {
					rentCtr.insertNew(from, till, "ROOM", amount, notes.getText(), cusId, roomId);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, "Reservation saved");
				dispose();
			}
		});
		
	}

	
}
