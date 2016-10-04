package GUILayer.Customer;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import ControlLayer.CustomerCtr;
import ModelLayer.LoginErrors;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class Login extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel pnlLogin;
	private final JTextField txtId = new JTextField();
	private final JPasswordField txtPw = new JPasswordField();
	private final JLabel lblId = new JLabel("ID :");
	private final JLabel lblPw = new JLabel("Password :");
	private final JButton btnLogin = new JButton("Login");
	private final JButton btnExit = new JButton("Exit");
	private final JPanel pnlKeyPad = new KeyPad(txtId, txtPw);
	private final JLabel lblNewLabel = new JLabel("WELCOME TO ALMA SPA");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new Login();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		initialize();
		createEvents();
	}

	private void initialize() {
		setBounds(100, 100, 1280, 800);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setUndecorated(true);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pnlLogin = new JPanel();
		pnlLogin.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(pnlLogin);
		txtId.setFont(new Font("SansSerif", Font.BOLD, 27));
		txtId.setEditable(false);
		txtPw.setFont(new Font("SansSerif", Font.BOLD, 35));
		txtPw.setEditable(false);
		btnExit.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnExit.setFont(new Font("SansSerif", Font.BOLD, 13));
		btnLogin.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnLogin.setMinimumSize(new Dimension(47, 28));
		btnLogin.setMaximumSize(new Dimension(47, 28));
		btnLogin.setFont(new Font("SansSerif", Font.BOLD, 25));
		lblPw.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblPw.setFont(new Font("SansSerif", Font.BOLD, 25));
		lblId.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblId.setFont(new Font("SansSerif", Font.BOLD, 25));
		txtId.setColumns(4);
		txtId.setBackground(Color.LIGHT_GRAY);
		txtPw.setColumns(4);
		txtPw.setFocusable(false);
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 50));
		GroupLayout gl_pnlLogin = new GroupLayout(pnlLogin);
		gl_pnlLogin.setHorizontalGroup(
			gl_pnlLogin.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlLogin.createSequentialGroup()
					.addGap(1)
					.addComponent(btnExit, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE)
					.addGap(219)
					.addGroup(gl_pnlLogin.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 637, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_pnlLogin.createSequentialGroup()
							.addGap(94)
							.addComponent(pnlKeyPad, GroupLayout.PREFERRED_SIZE, 444, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_pnlLogin.createSequentialGroup()
							.addGap(224)
							.addComponent(btnLogin, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_pnlLogin.createSequentialGroup()
							.addGap(206)
							.addGroup(gl_pnlLogin.createParallelGroup(Alignment.TRAILING, false)
								.addGroup(gl_pnlLogin.createSequentialGroup()
									.addComponent(lblId)
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(txtId, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE))
								.addGroup(Alignment.LEADING, gl_pnlLogin.createSequentialGroup()
									.addComponent(lblPw)
									.addGap(12)
									.addComponent(txtPw, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)))))
					.addGap(271))
		);
		gl_pnlLogin.setVerticalGroup(
			gl_pnlLogin.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlLogin.createSequentialGroup()
					.addGroup(gl_pnlLogin.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlLogin.createSequentialGroup()
							.addGap(691)
							.addComponent(btnExit, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_pnlLogin.createSequentialGroup()
							.addGap(62)
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
							.addGap(76)
							.addGroup(gl_pnlLogin.createParallelGroup(Alignment.TRAILING)
								.addComponent(txtId, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblId))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_pnlLogin.createParallelGroup(Alignment.LEADING)
								.addComponent(lblPw, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_pnlLogin.createSequentialGroup()
									.addGap(4)
									.addComponent(txtPw, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)))
							.addGap(12)
							.addComponent(pnlKeyPad, GroupLayout.PREFERRED_SIZE, 276, GroupLayout.PREFERRED_SIZE)
							.addGap(39)
							.addComponent(btnLogin, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(40, Short.MAX_VALUE))
		);
		pnlLogin.setLayout(gl_pnlLogin);
		txtId.setText(null);
		txtPw.setText(null);
	}

	private void createEvents() {
		txtId.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				txtId.setFocusable(true);
				txtPw.setFocusable(false);
				txtPw.setBackground(null);
				txtId.setBackground(Color.LIGHT_GRAY);
			}
		});

		txtPw.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				txtPw.setFocusable(true);
				txtId.setFocusable(false);
				txtId.setBackground(null);
				txtPw.setBackground(Color.LIGHT_GRAY);

			}
		});

		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		btnLogin.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				CustomerCtr cusCtr = new CustomerCtr();
				if (txtId.getText().isEmpty() == false && txtPw.getText().isEmpty() == false) {
					int customerId = Integer.parseInt(txtId.getText().trim());
					LoginErrors login = cusCtr.login(customerId, txtPw.getText());

					switch (login) {
					case SUCCESS:
						new MainMenuCustomer(customerId);
						dispose();
						break;
					case PASSWORD:
						JOptionPane.showMessageDialog(null, "Check your ID, then type your password again.",
								"The ID or password is incorrect!", JOptionPane.ERROR_MESSAGE);
						txtPw.setText("");
						break;
					case INACTIVE:
						JOptionPane.showMessageDialog(null, "Please contact someone at the reception.",
								"Your account is inactive!", JOptionPane.WARNING_MESSAGE);
						txtPw.setText("");
						txtId.setText("");
						txtId.setFocusable(true);
						txtPw.setFocusable(false);
						txtPw.setBackground(null);
						txtId.setBackground(Color.LIGHT_GRAY);
						break;
					case LOGGEDIN:
						JOptionPane.showMessageDialog(null,
								"Logout from the other device or please contact someone at the reception.",
								"Your account is already logged in!", JOptionPane.WARNING_MESSAGE);
						txtPw.setText("");
						txtId.setText("");
						txtId.setFocusable(true);
						txtPw.setFocusable(false);
						txtPw.setBackground(null);
						txtId.setBackground(Color.LIGHT_GRAY);
						break;
					default:
						cusCtr.logout(customerId);
						break;
					}
				} else {
					JOptionPane.showMessageDialog(null, "You didn't enter your id or password.", "",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}
}
