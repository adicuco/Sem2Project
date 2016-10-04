package GUILayer.Employee;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import ModelLayer.LoginErrors;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.UIManager;

import ControlLayer.EmployeeCtr;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JLabel lblId = new JLabel("ID");
	private final JLabel lblPassword = new JLabel("Password");
	private final JTextField textId = new JTextField();
	private final JPasswordField textPassword = new JPasswordField();
	private final JButton btnLogin = new JButton("Login");

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

	public Login() {

		initialize();
		createEvents();
	}

	private void initialize() {
		setBounds(100, 100, 800, 600);
		setVisible(true);
		textPassword.setFont(new Font("SansSerif", Font.BOLD, 25));
		textPassword.setColumns(10);
		textId.setFont(new Font("SansSerif", Font.BOLD, 25));
		textId.setColumns(10);
		lblPassword.setFont(new Font("SansSerif", Font.BOLD, 30));
		lblId.setFont(new Font("SansSerif", Font.BOLD, 30));
		btnLogin.setFont(new Font("SansSerif", Font.BOLD, 30));

		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.TRAILING).addGroup(Alignment.LEADING,
				groupLayout.createSequentialGroup().addGap(166)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING).addComponent(lblPassword)
								.addComponent(lblId))
						.addGap(31)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(btnLogin)
								.addComponent(textId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(textPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addContainerGap(221, Short.MAX_VALUE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addGap(120)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblId).addComponent(textId,
						GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblPassword).addComponent(
						textPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(105).addComponent(btnLogin).addContainerGap(177, Short.MAX_VALUE)));
		getContentPane().setLayout(groupLayout);
	}

	private void createEvents() {

		textId.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				textId.setFocusable(true);
				textPassword.setFocusable(false);
				textPassword.setBackground(null);
				textId.setBackground(Color.LIGHT_GRAY);
			}
		});

		textPassword.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				textPassword.setFocusable(true);
				textId.setFocusable(false);
				textId.setBackground(null);
				textPassword.setBackground(Color.LIGHT_GRAY);

			}
		});

		btnLogin.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				if((textId.getText().toString().equals("")) || (textPassword.getText().toString().equals(""))){			
				} else {
				EmployeeCtr empCtr = new EmployeeCtr();
				int empId = Integer.parseInt(textId.getText());
				
				LoginErrors login = empCtr.login(empId, textPassword.getText());

				switch (login) {
				case SUCCESS:
					new MainMenu(empId);
					dispose();
					break;
				case PASSWORD:
					JOptionPane.showMessageDialog(null, "Check your ID, then type your password again.",
							"The ID or password is incorrect!", JOptionPane.ERROR_MESSAGE);
					textPassword.setText("");
					break;

				case LOGGEDIN:
					JOptionPane.showMessageDialog(null, "Please logout from the other device in order to login here.",
							"Your account is already logged in!", JOptionPane.WARNING_MESSAGE);
					textPassword.setText("");
					textId.setText("");
					textId.setFocusable(true);
					textPassword.setFocusable(false);
					textPassword.setBackground(null);
					textId.setBackground(Color.LIGHT_GRAY);
					break;
				default:
					empCtr.logout(empId);
					break;
				}
				}
			}
		});

	}

}
