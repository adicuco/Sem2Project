package GUILayer.Customer;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class KeyPad extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JButton btn1 = new JButton("1");
	private final JButton btn2 = new JButton("2");
	private final JButton btn3 = new JButton("3");
	private final JButton btn4 = new JButton("4");
	private final JButton btn5 = new JButton("5");
	private final JButton btn6 = new JButton("6");
	private final JButton btn7 = new JButton("7");
	private final JButton btn8 = new JButton("8");
	private final JButton btn9 = new JButton("9");
	private final JButton btnDelete = new JButton("");
	private final JButton btn0 = new JButton("0");
	private final JButton btnClear = new JButton("Clear");

	private final JTextField txtId;
	private final JPasswordField txtPw;

	/**
	 * Create the panel.
	 */
	public KeyPad(JTextField txtId, JPasswordField txtPw) {
		this.txtId = txtId;
		this.txtPw = txtPw;
		initialize();
		createEvents();
	}

	private void initialize() {
		setBounds(new Rectangle(0, 0, 310, 221));
		setLayout(new GridLayout(4, 3));

		btn1.setFont(new Font("SansSerif", Font.BOLD, 25));
		add(btn1);
		btn2.setFont(new Font("SansSerif", Font.BOLD, 25));
		add(btn2);
		btn3.setFont(new Font("SansSerif", Font.BOLD, 25));
		add(btn3);
		btn4.setFont(new Font("SansSerif", Font.BOLD, 25));
		add(btn4);
		btn5.setFont(new Font("SansSerif", Font.BOLD, 25));
		add(btn5);
		btn6.setFont(new Font("SansSerif", Font.BOLD, 25));
		add(btn6);
		btn7.setFont(new Font("SansSerif", Font.BOLD, 25));
		add(btn7);
		btn8.setFont(new Font("SansSerif", Font.BOLD, 25));
		add(btn8);
		btn9.setFont(new Font("SansSerif", Font.BOLD, 25));
		add(btn9);
		btnDelete.setIcon(new ImageIcon(KeyPad.class.getResource("/resources/arrows (3).png")));
		add(btnDelete);
		btn0.setFont(new Font("SansSerif", Font.BOLD, 25));
		add(btn0);
		btnClear.setFont(new Font("SansSerif", Font.BOLD, 25));
		add(btnClear);
	}

	private void createEvents() {

		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (txtId.isFocusable()) {
					txtId.setText(txtId.getText() + "1");
				}
				if (txtPw.isFocusable()) {
					txtPw.setText(txtPw.getText() + "1");
				}
			}
		});

		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (txtId.isFocusable()) {
					txtId.setText(txtId.getText() + "2");
				}
				if (txtPw.isFocusable()) {
					txtPw.setText(txtPw.getText() + "2");
				}
			}
		});

		btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (txtId.isFocusable()) {
					txtId.setText(txtId.getText() + "3");
				}
				if (txtPw.isFocusable()) {
					txtPw.setText(txtPw.getText() + "3");
				}
			}
		});

		btn4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (txtId.isFocusable()) {
					txtId.setText(txtId.getText() + "4");
				}
				if (txtPw.isFocusable()) {
					txtPw.setText(txtPw.getText() + "4");
				}
			}
		});

		btn5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (txtId.isFocusable()) {
					txtId.setText(txtId.getText() + "5");
				}
				if (txtPw.isFocusable()) {
					txtPw.setText(txtPw.getText() + "5");
				}
			}
		});

		btn6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (txtId.isFocusable()) {
					txtId.setText(txtId.getText() + "6");
				}
				if (txtPw.isFocusable()) {
					txtPw.setText(txtPw.getText() + "6");
				}
			}
		});

		btn7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (txtId.isFocusable()) {
					txtId.setText(txtId.getText() + "7");
				}
				if (txtPw.isFocusable()) {
					txtPw.setText(txtPw.getText() + "7");
				}
			}
		});

		btn8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (txtId.isFocusable()) {
					txtId.setText(txtId.getText() + "8");
				}
				if (txtPw.isFocusable()) {
					txtPw.setText(txtPw.getText() + "8");
				}
			}
		});

		btn9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (txtId.isFocusable()) {
					txtId.setText(txtId.getText() + "9");
				}
				if (txtPw.isFocusable()) {
					txtPw.setText(txtPw.getText() + "9");
				}
			}
		});

		btn0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (txtId.isFocusable()) {
					txtId.setText(txtId.getText() + "0");
				}
				if (txtPw.isFocusable()) {
					txtPw.setText(txtPw.getText() + "0");
				}
			}
		});

		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (txtId.isFocusable()) {
					String str = txtId.getText();
					if (str != null && str.length() > 0) {
						str = str.substring(0, str.length() - 1);
					}
					txtId.setText(str);
				}
				if (txtPw.isFocusable()) {
					String str = txtPw.getText();
					if (str != null && str.length() > 0) {
						str = str.substring(0, str.length() - 1);
					}
					txtPw.setText(str);
				}
			}
		});

		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtId.setText("");
				txtPw.setText("");
				txtId.setFocusable(true);
				txtPw.setFocusable(false);
				txtPw.setBackground(null);
				txtId.setBackground(Color.LIGHT_GRAY);
			}
		});
	}
}
