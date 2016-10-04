package GUILayer.Employee.menuPanels;

import javax.swing.JPanel;

import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;

import GUILayer.Customer.menuPanels.TimeClass;
import GUILayer.Employee.menuPanels.dialogPanels.AddCustomerDialogPanel;
import GUILayer.Employee.menuPanels.dialogPanels.ManageRoomsDialogPanel;
import GUILayer.Employee.menuPanels.dialogPanels.RoomBookingDialogPanel;
import ModelLayer.Customer;
import ModelLayer.Rent;
import ModelLayer.Room;

import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

import ControlLayer.CustomerCtr;
import ControlLayer.RentCtr;
import ControlLayer.RoomCtr;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import com.toedter.calendar.JDateChooser;

public class BookingsPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JLabel lblBookingsMenu = new JLabel("BOOKINGS   MENU");
	private final JPanel pnlFindCus = new JPanel();
	private final JLabel lblDate = new JLabel(TimeClass.getDate());
	private final JLabel lblCustomerId = new JLabel("Customer ID");
	private final JLabel lblCustomerName = new JLabel("Customer Name");
	private final JLabel lblCustomerCpr = new JLabel("Customer CPR");
	private final JTextField txtCusId = new JTextField();
	private final JTextField txtCusName = new JTextField();
	private final JTextField txtCusCpr = new JTextField();
	private final JScrollPane scrolPanCusList = new JScrollPane();
	private final JTable cusList = new JTable();
	private final JButton btnFindCustomer = new JButton("Find Customer");
	private final JButton btnClear = new JButton("Clear");
	private DefaultTableModel model;
	private DefaultTableModel modelR;
	private DefaultTableModel modelB;
	private final JButton btnAddNewCustomer = new JButton("Add New Customer");
	private final JPanel pnlFindRooms = new JPanel();
	private final JDateChooser roomFrom = new JDateChooser();
	private final JDateChooser roomTill = new JDateChooser();
	private final JButton btnFindRoom = new JButton("Find Room");
	private final JLabel lblCheckin = new JLabel("Check-in");
	private final JLabel lblCheckout = new JLabel("Check-out");
	private final JButton btnBookRoom = new JButton("Book Room");
	private final JButton btnClearRoom = new JButton("Clear");
	private final JButton btnManageRoom = new JButton("Manage Room");
	private final JScrollPane scrollPane = new JScrollPane();
	private final JTable roomList = new JTable();
	private final JPanel panel = new JPanel();
	private final JDateChooser dateIn = new JDateChooser();
	private final JDateChooser dateOut = new JDateChooser();
	private final JLabel lblReservationId = new JLabel("Reservation Id");
	private final JLabel lblCusCPR = new JLabel("Customer CPR");
	private final JLabel lblCheckinR = new JLabel("Check-in");
	private final JLabel lblCheckoutR = new JLabel("Check-out");
	private final JButton btnSearch = new JButton("Search");
	private final JButton btnClearReservation = new JButton("Clear");
	private final JTextField txtRId = new JTextField();
	private final JTextField txtCusCPR = new JTextField();
	private final JScrollPane scrollPane_1 = new JScrollPane();
	private final JTable resList = new JTable();
	
	
	public BookingsPanel(){
		txtCusCPR.setText("");
		txtCusCPR.setColumns(10);
		txtRId.setColumns(10);
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnlFindRooms.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		initialize();
		createEvents();
	}
	
	private void initialize() {
		
		setSize(new Dimension(950, 750));
		setVisible(true);
		BorderFactory.createEmptyBorder(1, 1, 1, 1);
		txtCusCpr.setColumns(10);
		txtCusName.setColumns(10);
		txtCusId.setColumns(10);
		
		lblBookingsMenu.setFont(new Font("SansSerif", Font.BOLD, 30));
		lblCustomerCpr.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblCustomerId.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblCustomerName.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblDate.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblCheckin.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblCheckout.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblCheckinR.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblCusCPR.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblCheckoutR.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblReservationId.setFont(new Font("SansSerif", Font.BOLD, 20));
		
		txtCusCpr.setFont(new Font("SansSerif", Font.BOLD, 20));
		txtCusId.setFont(new Font("SansSerif", Font.BOLD, 20));
		txtCusName.setFont(new Font("SansSerif", Font.BOLD, 20));
		txtCusCpr.setTransferHandler(null);
		txtCusId.setTransferHandler(null);
		txtCusCPR.setFont(new Font("SansSerif", Font.BOLD, 20));
		txtRId.setFont(new Font("SansSerif", Font.BOLD, 20));
		txtRId.setTransferHandler(null);
		
		btnAddNewCustomer.setFont(new Font("SansSerif", Font.BOLD, 18));
		btnFindCustomer.setFont(new Font("SansSerif", Font.BOLD, 18));	
		btnClear.setFont(new Font("SansSerif", Font.BOLD, 18));	
		btnFindRoom.setFont(new Font("SansSerif", Font.BOLD, 18));
		btnClearRoom.setFont(new Font("SansSerif", Font.BOLD, 18));	
		btnBookRoom.setFont(new Font("SansSerif", Font.BOLD, 18));	
		btnManageRoom.setFont(new Font("SansSerif", Font.BOLD, 15));
		
		btnSearch.setFont(new Font("SansSerif", Font.BOLD, 18));	
		
		btnClearReservation.setFont(new Font("SansSerif", Font.BOLD, 18));	
		roomFrom.setFont(new Font("SansSerif", Font.BOLD, 18));
		roomTill.setFont(new Font("SansSerif", Font.BOLD, 18));
		dateIn.setFont(new Font("SansSerif", Font.BOLD, 18));
		dateOut.setFont(new Font("SansSerif", Font.BOLD, 18));
		roomFrom.setDateFormatString("yyyy-MM-dd");
		roomTill.setDateFormatString("yyyy-MM-dd");
		dateIn.setDateFormatString("yyyy-MM-dd");
		dateOut.setDateFormatString("yyyy-MM-dd");
		
		model = new DefaultTableModel(new Object[][] {},
				new String[] { "Id", "First Name", "Last Name", "CPR", "Phone", "Email" });
		cusList.setModel(model);
		cusList.setFont(new Font("SansSerif", Font.PLAIN, 15));
		cusList.setRowHeight(35);
		
		modelB = new DefaultTableModel(new Object[][] {},
				new String[] { "Reservation Id", "Room Id", "Customer Name", "Chaeck-in", "Check-out" });
		resList.setModel(modelB);
		resList.setFont(new Font("SansSerif", Font.PLAIN, 15));
		resList.setRowHeight(35);
		
		modelR = new DefaultTableModel(new Object[][] {},
				new String[] { "Id", "Room Type", "Extra Bed", "Price", "Notes" });
		roomList.setModel(modelR);
		roomList.setFont(new Font("SansSerif", Font.PLAIN, 15));
		roomList.setRowHeight(35);
				
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(0)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(panel, GroupLayout.DEFAULT_SIZE, 950, Short.MAX_VALUE)
							.addGap(10))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(pnlFindRooms, 0, 0, Short.MAX_VALUE)
								.addComponent(pnlFindCus, GroupLayout.PREFERRED_SIZE, 950, Short.MAX_VALUE)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(315)
									.addComponent(lblBookingsMenu, GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE)
									.addGap(137)
									.addComponent(lblDate, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
									.addGap(13)))
							.addGap(5))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblDate)
						.addComponent(lblBookingsMenu))
					.addGap(18)
					.addComponent(pnlFindCus, GroupLayout.PREFERRED_SIZE, 203, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(pnlFindRooms, GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
					.addGap(10))
		);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(37)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblReservationId)
								.addComponent(lblCheckinR))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
								.addComponent(dateIn, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(txtRId))
							.addGap(63)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblCusCPR)
								.addComponent(lblCheckoutR))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(dateOut, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE)
								.addComponent(txtCusCPR, GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE))
							.addGap(45))
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 809, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnClearReservation, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
						.addComponent(btnSearch, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(25))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(11)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblReservationId)
								.addComponent(txtRId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblCusCPR)
								.addComponent(txtCusCPR, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addGap(20)
									.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
										.addComponent(lblCheckinR)
										.addComponent(dateIn, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
								.addGroup(gl_panel.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(lblCheckoutR))
								.addGroup(gl_panel.createSequentialGroup()
									.addGap(18)
									.addComponent(dateOut, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(32)
							.addComponent(btnSearch)))
					.addGap(8)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnClearReservation)
						.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE))
					.addContainerGap())
		);
		
		scrollPane_1.setViewportView(resList);
		panel.setLayout(gl_panel);
		GroupLayout gl_pnlFindRooms = new GroupLayout(pnlFindRooms);
		gl_pnlFindRooms.setHorizontalGroup(
			gl_pnlFindRooms.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlFindRooms.createSequentialGroup()
					.addGroup(gl_pnlFindRooms.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlFindRooms.createSequentialGroup()
							.addGap(21)
							.addComponent(lblCheckin, GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(roomFrom, GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
							.addGap(92)
							.addComponent(lblCheckout, GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(roomTill, GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
							.addGap(64))
						.addGroup(gl_pnlFindRooms.createSequentialGroup()
							.addContainerGap()
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 702, Short.MAX_VALUE)))
					.addGap(81)
					.addGroup(gl_pnlFindRooms.createParallelGroup(Alignment.LEADING)
						.addComponent(btnFindRoom, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
						.addComponent(btnBookRoom, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnManageRoom, GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
						.addComponent(btnClearRoom, GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE))
					.addGap(15))
		);
		gl_pnlFindRooms.setVerticalGroup(
			gl_pnlFindRooms.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlFindRooms.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pnlFindRooms.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlFindRooms.createSequentialGroup()
							.addComponent(btnFindRoom, GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
							.addGap(26)
							.addComponent(btnBookRoom, GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
							.addGap(26)
							.addComponent(btnClearRoom)
							.addGap(27)
							.addComponent(btnManageRoom, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(gl_pnlFindRooms.createSequentialGroup()
							.addGap(3)
							.addGroup(gl_pnlFindRooms.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblCheckin)
								.addComponent(lblCheckout)
								.addComponent(roomTill, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(roomFrom, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addGap(2))
		);
		
		scrollPane.setViewportView(roomList);
		pnlFindRooms.setLayout(gl_pnlFindRooms);
		pnlFindCus.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		GroupLayout gl_pnlFindCus = new GroupLayout(pnlFindCus);
		gl_pnlFindCus.setHorizontalGroup(
			gl_pnlFindCus.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlFindCus.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pnlFindCus.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlFindCus.createSequentialGroup()
							.addComponent(scrolPanCusList, GroupLayout.DEFAULT_SIZE, 928, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(gl_pnlFindCus.createSequentialGroup()
							.addGroup(gl_pnlFindCus.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_pnlFindCus.createSequentialGroup()
									.addComponent(lblCustomerId, GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
									.addGap(27)
									.addComponent(txtCusId))
								.addGroup(gl_pnlFindCus.createSequentialGroup()
									.addComponent(lblCustomerCpr, GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtCusCpr)
									.addGap(1)))
							.addGap(4)
							.addGroup(gl_pnlFindCus.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_pnlFindCus.createSequentialGroup()
									.addComponent(lblCustomerName, GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
									.addGap(18)
									.addComponent(txtCusName)
									.addGap(26))
								.addGroup(gl_pnlFindCus.createSequentialGroup()
									.addGap(159)
									.addComponent(btnAddNewCustomer, GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
									.addGap(9)))
							.addGroup(gl_pnlFindCus.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_pnlFindCus.createSequentialGroup()
									.addGap(15)
									.addComponent(btnFindCustomer, GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
									.addGap(62))
								.addGroup(gl_pnlFindCus.createSequentialGroup()
									.addGap(52)
									.addComponent(btnClear, GroupLayout.PREFERRED_SIZE, 100, Short.MAX_VALUE)
									.addGap(89))))))
		);
		gl_pnlFindCus.setVerticalGroup(
			gl_pnlFindCus.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlFindCus.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pnlFindCus.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlFindCus.createSequentialGroup()
							.addGroup(gl_pnlFindCus.createParallelGroup(Alignment.BASELINE, false)
								.addGroup(gl_pnlFindCus.createSequentialGroup()
									.addGap(6)
									.addComponent(lblCustomerId))
								.addComponent(txtCusId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtCusName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_pnlFindCus.createSequentialGroup()
									.addGap(6)
									.addComponent(lblCustomerName)))
							.addGap(1))
						.addComponent(btnFindCustomer, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_pnlFindCus.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlFindCus.createSequentialGroup()
							.addGap(1)
							.addGroup(gl_pnlFindCus.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblCustomerCpr)
								.addComponent(txtCusCpr, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_pnlFindCus.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnAddNewCustomer)
							.addComponent(btnClear)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrolPanCusList, GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		scrolPanCusList.setViewportView(cusList);
		pnlFindCus.setLayout(gl_pnlFindCus);
		setLayout(groupLayout);
	}
	
	private void createEvents(){
		
		txtCusId.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if ((!Character.isDigit(c)) && !(c == KeyEvent.VK_BACK_SPACE)) {
					e.consume();
				}
			}
		});
		
		txtRId.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if ((!Character.isDigit(c)) && !(c == KeyEvent.VK_BACK_SPACE)) {
					e.consume();
				}
			}
		});

		txtCusCpr.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();

				if ((txtCusCpr.getText().length() == 6) && !(c == KeyEvent.VK_BACK_SPACE)) {
					txtCusCpr.setText(txtCusCpr.getText() + "-");
				}
				if (!Character.isDigit(c)) {
					e.consume();
				}
				if (txtCusCpr.getText().length() > 10) {
					e.consume();
				}
				if (!(Character.isDigit(c)) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE)) {
					e.consume();
				}
			}
		});
		
		
		btnFindCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model = new DefaultTableModel(new Object[][] {},
						new String[] { "Id", "First Name", "Last Name", "CPR", "Phone", "Email" });
				cusList.setModel(model);
				model.setRowCount(0);
				CustomerCtr cusCtr = new CustomerCtr();
				if (txtCusId.getText().equals("")) {
					if (txtCusCpr.getText().equals("")) {
						ArrayList<Customer> cus = cusCtr.findAllCustomers(txtCusName.getText());
						if (!cus.isEmpty()) {
							for (Customer c : cus) {
								model.addRow(new Object[] { c.getCustomerId(), c.getfName(), c.getlName(), c.getCpr(),
										c.getPhone(), c.getEmail() });
							}
						}

					} else {
						ArrayList<Customer> cus = new ArrayList<Customer>();
						try {
							cus = cusCtr.findAllCustomers("cpr", txtCusCpr.getText());

						} catch (NullPointerException e1) {

							e1.printStackTrace();
						}
						if (!cus.isEmpty()) {
							for (Customer c : cus) {
								model.addRow(new Object[] { c.getCustomerId(), c.getfName(), c.getlName(), c.getCpr(),
										c.getPhone(), c.getEmail() });
							}
						}
					}

				} else {

					Customer c = new Customer();
					try {
						c = cusCtr.findCustomer(Integer.parseInt(txtCusId.getText()));

					} catch (NullPointerException e1) {
						e1.printStackTrace();
					}
					model.addRow(new Object[] { c.getCustomerId(), c.getfName(), c.getlName(), c.getCpr(), c.getPhone(),
							c.getEmail() });
				}			
			}
		});
		
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtCusName.setText("");
				txtCusCpr.setText("");
				txtCusId.setText("");
				cusList.setModel(new DefaultTableModel(new Object[][] {},
						new String[] { "Id", "First Name", "Last Name", "CPR", "Phone", "Email" }));		
			}
		});
		
		btnAddNewCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AddCustomerDialogPanel();
			}
		});
		
		btnClearRoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				roomFrom.setDate(null);
				roomTill.setDate(null);
				roomList.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Id", "Room Type", "Extra Bed", "Price", "Notes" }));
			}
		});
		
		btnFindRoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Buton apasat**********************************");
				RoomCtr rCtr = new RoomCtr();
				modelR = new DefaultTableModel(new Object[][] {},
						new String[] { "Id", "Room Type", "Extra Bed", "Price", "Notes" });
				roomList.setModel(modelR);
				modelR.setRowCount(0);
				if((roomFrom.getDate() == null) || (roomTill.getDate() == null)){	
					System.out.println("Cautare fara data++++++++++++++++++++++++++++++");
				}else {
					System.out.println("Cautare cu  data**********************************");
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					Timestamp checkIn = null;
					Timestamp checkOut = null;
					checkIn = Timestamp.valueOf(sdf.format(roomFrom.getDate()) + " 00:00:00");
					checkOut = Timestamp.valueOf(sdf.format(roomTill.getDate()) + " 00:00:00");		
					ArrayList<Room> rooms = rCtr.getAllAvailable(checkIn, checkOut);
					System.out.print(rooms.size()+ " this is the number of rooms available+++++++++++++++++++++++++++++");
					for (Room r : rooms){
						modelR.addRow(new Object[]{r.getId(), r.getRoomType().getRoomTypeName(), r.isExtraBed(), r.getRoomPrice(), r.getNotes()});
					}					
				}			
			}
		});
		
		btnBookRoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (!(cusList.getSelectedRow() == -1) && !(roomList.getSelectedRow() == -1)){
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					Timestamp checkIn = null;
					Timestamp checkOut = null;
					checkIn = Timestamp.valueOf(sdf.format(roomFrom.getDate()) + " 00:00:00");
					checkOut = Timestamp.valueOf(sdf.format(roomTill.getDate()) + " 00:00:00");
					new RoomBookingDialogPanel((int) cusList.getValueAt(cusList.getSelectedRow(), 0), (int) roomList.getValueAt(roomList.getSelectedRow(), 0), checkIn, checkOut);
					
				}
				
			}
		});
		
		btnManageRoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ManageRoomsDialogPanel();
			}
		});
		
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RentCtr rentCtr = new RentCtr();
				Rent r = new Rent();
				CustomerCtr cusCtr = new CustomerCtr();
				Customer cus = new Customer();
				ArrayList<Rent> rentList = new ArrayList<Rent>();
				modelB = new DefaultTableModel(new Object[][] {},
						new String[] { "Reservation Id", "Room Id", "Customer Name", "Chaeck-in", "Check-out"});
				resList.setModel(modelB);
				if(txtRId.getText().equals("")){
					if(txtCusCPR.getText().equals("")){
						if(dateIn.toString().isEmpty() || dateOut.toString().isEmpty()){							
						} else {
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
							Timestamp checkIn = null;
							Timestamp checkOut = null;
							checkIn = Timestamp.valueOf(sdf.format(dateIn.getDate()) + " 00:00:00");
							checkOut = Timestamp.valueOf(sdf.format(dateOut.getDate()) + " 00:00:00");
							rentList = rentCtr.getRoomReservation(checkIn, checkOut);
							for(Rent rent : rentList){
								if(rent.getRentType().toString().equals("ROOM")){
									modelB.addRow(new Object[] { rent.getRentId(), rent.getRentable().getId(), cus.getfName() + " " + cus.getlName(),  rent.getStartDate().toString().substring(0,10), rent.getEndDate().toString().substring(0,10)});
								}
							}	
						}
						
					}else{
						cus = cusCtr.findAllCustomers("cpr", txtCusCPR.getText()).get(0);
						rentList = rentCtr.findAllRents("customerId", String.valueOf(cus.getCustomerId()));
						for(Rent rent : rentList){
							if(rent.getRentType().toString().equals("ROOM")){
								modelB.addRow(new Object[] { rent.getRentId(), rent.getRentable().getId(), cus.getfName() + " " + cus.getlName(),  rent.getStartDate().toString().substring(0,10), rent.getEndDate().toString().substring(0,10)});
							}
						}		
					}
				}else{
					r = rentCtr.findRent(Integer.parseInt(txtRId.getText()));
					cus = r.getCustomer();
					if(r.getRentType().toString().equals("ROOM")){
						modelB.addRow(new Object[] { r.getRentId(), r.getRentable().getId(), cus.getfName() + " " + cus.getlName(),  r.getStartDate().toString().substring(0,10), r.getEndDate().toString().substring(0,10)});	
					}else {
						
					}			
				}		
			}
		});	
		
		btnClearReservation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dateIn.setDate(null);
				dateOut.setDate(null);
				txtCusCPR.setText("");
				txtRId.setText("");
				modelB = new DefaultTableModel(new Object[][] {},
						new String[] { "Reservation Id", "Room Id", "Customer Name", "Chaeck-in", "Check-out"});
				resList.setModel(modelB);
				
			}
		});
	}
}
