package GUILayer.Employee;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EtchedBorder;

import ControlLayer.EmployeeCtr;
import GUILayer.Customer.MainMenuCustomer;
import GUILayer.Customer.menuPanels.EquipmentPanel;
import GUILayer.Customer.menuPanels.SportCourtsPanel;
import GUILayer.Employee.menuPanels.BookingsPanel;
import GUILayer.Employee.menuPanels.CustomerPanel;
import GUILayer.Employee.menuPanels.EmployeePanel;
import GUILayer.Employee.menuPanels.EventsPanelEmployee;
import GUILayer.Employee.menuPanels.PaymentPanel;
import GUILayer.Employee.menuPanels.ProfilePanel;
import GUILayer.Employee.menuPanels.RentsPanel;
import ModelLayer.Employee;

public class MainMenu extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Employee employee;
	private static EmployeeCtr empCtr = new EmployeeCtr();
	private final JPanel panelButtons = new JPanel();
	private final JButton btnProfile = new JButton("Profile");
	private final JButton btnCustomer = new JButton("Customer");
	private final JButton btnBookings = new JButton("Bookings");
	private final JButton btnEvents = new JButton("Events");
	private final JButton btnPayment = new JButton("Payment");
	private final JButton btnEmployee = new JButton("Employee");
	private final JButton btnEquipment = new JButton("Equipment");
	private final static JButton btnLogout = new JButton("Logout");
	private final JPanel pnlMenu = new JPanel();
	private JPanel pnlProfile;
	private JPanel pnlCustomer;
	private JPanel pnlBookings;
	private JPanel pnlEvents;
	private JPanel pnlPayment;
	private JPanel pnlEmployee;
	private JPanel pnlEquipment;
	private JPanel pnlSportCourts;
	private JPanel pnlRents;
	private CardLayout cardLayout = new CardLayout();
	private final JButton btnSportCourts = new JButton("Sport Courts");
	private final JButton btnRents = new JButton("Rents");

	public MainMenu(int employeeId) {
		employee = empCtr.findEmployee(employeeId);
		createEvents();
		initialize();
		initializeCardLayout();
	}

	private void initialize() {
		if(employee.getAccessLvl().toString().equals("EMPLOYEE")){
			
			btnEmployee.setVisible(false);
		}
		setSize(new Dimension(1200, 800));
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setUndecorated(true);
		setVisible(true);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout
				.setHorizontalGroup(
						groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
										.addComponent(panelButtons, GroupLayout.PREFERRED_SIZE, 193,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(pnlMenu, GroupLayout.PREFERRED_SIZE, 985, Short.MAX_VALUE)));
		panelButtons.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.GRAY, Color.LIGHT_GRAY));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(panelButtons, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 750,
										Short.MAX_VALUE)
						.addComponent(pnlMenu, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addContainerGap()));
		GroupLayout gl_pnlMenu = new GroupLayout(pnlMenu);
		gl_pnlMenu
				.setHorizontalGroup(gl_pnlMenu.createParallelGroup(Alignment.LEADING).addGap(0, 985, Short.MAX_VALUE));
		gl_pnlMenu.setVerticalGroup(gl_pnlMenu.createParallelGroup(Alignment.LEADING).addGap(0, 658, Short.MAX_VALUE));

		gl_pnlMenu.setVerticalGroup(gl_pnlMenu.createParallelGroup(Alignment.LEADING).addGap(0, 658, Short.MAX_VALUE));
		pnlMenu.setLayout(gl_pnlMenu);
		getContentPane().setLayout(groupLayout);

		btnLogout.setFont(new Font("SansSerif", Font.BOLD, 15));
		panelButtons.setLayout(new GridLayout(0, 1, 0, 0));
		btnProfile.setFont(new Font("SansSerif", Font.BOLD, 15));
		panelButtons.add(btnProfile);
		btnCustomer.setFont(new Font("SansSerif", Font.BOLD, 15));
		panelButtons.add(btnCustomer);
		btnBookings.setFont(new Font("SansSerif", Font.BOLD, 15));
		panelButtons.add(btnBookings);

		btnRents.setFont(new Font("SansSerif", Font.BOLD, 15));
		panelButtons.add(btnRents);
		btnEvents.setFont(new Font("SansSerif", Font.BOLD, 15));
		panelButtons.add(btnEvents);
		btnSportCourts.setFont(new Font("SansSerif", Font.BOLD, 15));

		panelButtons.add(btnSportCourts);
		btnEquipment.setFont(new Font("SansSerif", Font.BOLD, 15));
		panelButtons.add(btnEquipment);
		btnPayment.setFont(new Font("SansSerif", Font.BOLD, 15));
		panelButtons.add(btnPayment);
		btnEmployee.setFont(new Font("SansSerif", Font.BOLD, 15));
		panelButtons.add(btnEmployee);
		panelButtons.add(btnLogout);

		pnlMenu.setLayout(cardLayout);
	}

	private void createEvents() {

		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				empCtr.logout(employee.getEmployeeId());
				System.exit(0);
			}
		});

		btnProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(pnlMenu, "Profile");
			}
		});

		btnCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainMenuCustomer.setCustomerId(0);
				cardLayout.show(pnlMenu, "Customer");
			}
		});

		btnBookings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainMenuCustomer.setCustomerId(0);
				cardLayout.show(pnlMenu, "Bookings");
			}
		});

		btnEvents.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainMenuCustomer.setCustomerId(0);
				cardLayout.show(pnlMenu, "Events");
			}
		});

		btnEquipment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainMenuCustomer.setCustomerId(0);
				cardLayout.show(pnlMenu, "Equipment");
			}
		});

		btnPayment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainMenuCustomer.setCustomerId(0);
				cardLayout.show(pnlMenu, "Payment");
			}
		});

		btnEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(pnlMenu, "Employee");
			}
		});

		btnSportCourts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainMenuCustomer.setCustomerId(0);
				cardLayout.show(pnlMenu, "SportCourts");
			}
		});

		btnRents.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainMenuCustomer.setCustomerId(0);
				cardLayout.show(pnlMenu, "Rents");
			}
		});

	}

	private void initializeCardLayout() {
		MainMenuCustomer.setCustomerId(0);
		pnlProfile = new ProfilePanel(employee);
		pnlCustomer = new CustomerPanel();
		pnlBookings = new BookingsPanel();
		pnlEvents = new EventsPanelEmployee();
		pnlPayment = new PaymentPanel();
		pnlEquipment = new EquipmentPanel();
		pnlEmployee = new EmployeePanel();
		pnlSportCourts = new SportCourtsPanel();
		pnlRents = new RentsPanel();

		pnlMenu.add(pnlProfile, "Profile");
		pnlMenu.add(pnlCustomer, "Customer");
		pnlMenu.add(pnlBookings, "Bookings");
		pnlMenu.add(pnlEvents, "Events");
		pnlMenu.add(pnlPayment, "Payment");
		pnlMenu.add(pnlEquipment, "Equipment");
		pnlMenu.add(pnlEmployee, "Employee");
		pnlMenu.add(pnlSportCourts, "SportCourts");
		pnlMenu.add(pnlRents, "Rents");

	}

	public static int EmployeeId() {
		return employee.getEmployeeId();
	}

	public static void close() {
		btnLogout.doClick();
	}
}