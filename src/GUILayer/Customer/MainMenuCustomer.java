package GUILayer.Customer;

import java.awt.CardLayout;
import java.awt.Color;
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

import ControlLayer.CustomerCtr;
import GUILayer.Customer.menuPanels.BookingsPanel;
import GUILayer.Customer.menuPanels.EquipmentPanel;
import GUILayer.Customer.menuPanels.EventsPanel;
import GUILayer.Customer.menuPanels.ProfilePanel;
import GUILayer.Customer.menuPanels.SportCourtsPanel;
import ModelLayer.Customer;
import javax.swing.ImageIcon;

public class MainMenuCustomer extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Customer customer;
	private static CustomerCtr cusCtr = new CustomerCtr();

	private JPanel pnlMenu = new JPanel();
	// private JPanel pnlFirst;
	private JPanel pnlProfile;
	private JPanel pnlBookings;
	private JPanel pnlEvents;
	private JPanel pnlSportCourts;
	private JPanel pnlEquipment;
	private CardLayout cardLayout = new CardLayout();

	public static JPanel pnlMainMenu;
	private final JPanel pnlButtons = new JPanel();
	private final JButton btnProfile = new JButton("         Profile");
	private final JButton btnBookings = new JButton("My Bookings");
	private final JButton btnEvents = new JButton("         Events");
	private final JButton btnSportCourts = new JButton("Sport Courts");
	private final JButton btnEquipment = new JButton("   Equipment");
	private final static JButton btnLogout = new JButton("       Logout");

	/**
	 * Create the frame.
	 */
	public MainMenuCustomer(int customerId) {
		customer = cusCtr.findCustomer(customerId);
		initialize();
		initializeCardLayout();
		createEvents();
		// pack();
	}

	private void initialize() {
		setBounds(100, 100, 1280, 800);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setResizable(false);
		setUndecorated(true);
		setVisible(true);
		pnlMainMenu = new JPanel();
		setContentPane(pnlMainMenu);
		GroupLayout gl_pnlMainMenu = new GroupLayout(pnlMainMenu);
		gl_pnlMainMenu.setHorizontalGroup(gl_pnlMainMenu.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlMainMenu.createSequentialGroup()
						.addComponent(pnlButtons, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(pnlMenu, GroupLayout.PREFERRED_SIZE, 1125, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(18, Short.MAX_VALUE)));
		gl_pnlMainMenu.setVerticalGroup(gl_pnlMainMenu.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_pnlMainMenu.createSequentialGroup()
						.addGroup(gl_pnlMainMenu.createParallelGroup(Alignment.TRAILING)
								.addComponent(pnlMenu, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 797,
										Short.MAX_VALUE)
						.addComponent(pnlButtons, GroupLayout.DEFAULT_SIZE, 797, Short.MAX_VALUE)).addGap(3)));
		pnlMenu.setLayout(cardLayout);
		cardLayout.show(pnlMenu, "Profile");
		pnlButtons
				.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(128, 128, 128), new Color(192, 192, 192)));
		pnlButtons.setLayout(new GridLayout(6, 1));
		btnProfile.setIcon(new ImageIcon(MainMenuCustomer.class.getResource("/resources/png/user.png")));

		btnProfile.setFont(new Font("SansSerif", Font.BOLD, 15));
		pnlButtons.add(btnProfile);
		btnBookings.setIcon(new ImageIcon(MainMenuCustomer.class.getResource("/resources/png/notebook.png")));

		btnBookings.setFont(new Font("SansSerif", Font.BOLD, 15));
		pnlButtons.add(btnBookings);
		btnEvents.setIcon(new ImageIcon(MainMenuCustomer.class.getResource("/resources/png/calendar.png")));

		btnEvents.setFont(new Font("SansSerif", Font.BOLD, 15));
		pnlButtons.add(btnEvents);
		btnSportCourts
				.setIcon(new ImageIcon(MainMenuCustomer.class.getResource("/resources/png/basketball-court.png")));

		btnSportCourts.setFont(new Font("SansSerif", Font.BOLD, 15));
		pnlButtons.add(btnSportCourts);
		btnEquipment.setIcon(new ImageIcon(MainMenuCustomer.class.getResource("/resources/png/soccer.png")));

		btnEquipment.setFont(new Font("SansSerif", Font.BOLD, 15));
		pnlButtons.add(btnEquipment);
		btnLogout.setIcon(new ImageIcon(MainMenuCustomer.class.getResource("/resources/png/logout.png")));

		btnLogout.setFont(new Font("SansSerif", Font.BOLD, 15));
		pnlButtons.add(btnLogout);

		pnlMainMenu.setLayout(gl_pnlMainMenu);
	}

	private void createEvents() {

		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cusCtr.logout(customer.getCustomerId());
				new Login();
				dispose();
			}
		});

		btnProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(pnlMenu, "Profile");
			}
		});

		btnBookings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlBookings = new BookingsPanel(customer);
				pnlMenu.add(pnlBookings, "My Bookings");
				cardLayout.show(pnlMenu, "My Bookings");
			}
		});

		btnEvents.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(pnlMenu, "Events");
			}
		});

		btnSportCourts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(pnlMenu, "Sport Courts");
			}
		});

		btnEquipment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(pnlMenu, "Equipment");
			}
		});
	}

	private void initializeCardLayout() {
		// pnlFirst = new FirstPanel(customer);
		pnlProfile = new ProfilePanel(customer);
		pnlEvents = new EventsPanel(customer);
		pnlSportCourts = new SportCourtsPanel();
		pnlEquipment = new EquipmentPanel();

		// pnlMenu.add(pnlFirst, "First");
		pnlMenu.add(pnlProfile, "Profile");
		pnlMenu.add(pnlEvents, "Events");
		pnlMenu.add(pnlSportCourts, "Sport Courts");
		pnlMenu.add(pnlEquipment, "Equipment");
	}

	public static int getCustomerId() {
		return customer.getCustomerId();
	}

	public static boolean setCustomerId(int customerId) {
		boolean ok = true;

		CustomerCtr cusCtr = new CustomerCtr();
		customer = cusCtr.findCustomer(customerId);
		if (customer == null) {
			ok = false;
		}
		return ok;
	}

	public static void close() {
		btnLogout.doClick();
	}
}
