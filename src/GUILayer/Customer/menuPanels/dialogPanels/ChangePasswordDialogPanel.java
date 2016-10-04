package GUILayer.Customer.menuPanels.dialogPanels;

import javax.swing.JDialog;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import ModelLayer.Customer;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import GUILayer.Customer.KeyPad;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;

import ControlLayer.CustomerCtr;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class ChangePasswordDialogPanel extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Customer customer;
	private JLabel lblNewPassword = new JLabel("Old Password");
	private JLabel lblConfirmPassword = new JLabel("New Password");
	private JTextField txtNew = new JTextField();
	private JPasswordField txtOld = new JPasswordField();
	private JButton cancel = new JButton("Cancel");
	private JButton btnNewButton_1 = new JButton("Save");

	public ChangePasswordDialogPanel(Customer customer) {
		this.customer = customer;
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
		setSize(new Dimension(830, 498));
		lblNewPassword.setFont(new Font("SansSerif", Font.BOLD, 25));
		lblConfirmPassword.setFont(new Font("SansSerif", Font.BOLD, 25));
		KeyPad keyPad_1 = new KeyPad(txtNew, txtOld);
		txtNew.setFont(new Font("SansSerif", Font.BOLD, 30));
		txtNew.setEditable(false);
		txtNew.setBounds(431, 45, 80, 43);
		txtNew.setColumns(4);
		txtOld.setBackground(Color.LIGHT_GRAY);
		txtOld.setFont(new Font("SansSerif", Font.BOLD, 35));
		txtOld.setEditable(false);
		txtOld.setBounds(431, 98, 80, 44);
		txtOld.setColumns(4);
		txtNew.setFocusable(false);

		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.TRAILING).addGroup(groupLayout
				.createSequentialGroup().addContainerGap()
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
						.createSequentialGroup().addGap(231)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(lblConfirmPassword)
								.addGroup(groupLayout.createSequentialGroup()
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(lblNewPassword)))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(txtNew, 0, 0, Short.MAX_VALUE)
								.addComponent(txtOld, GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE))
						.addPreferredGap(ComponentPlacement.RELATED, 34, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
								.addComponent(cancel, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED, 104, Short.MAX_VALUE)
								.addComponent(keyPad_1, GroupLayout.PREFERRED_SIZE, 305, GroupLayout.PREFERRED_SIZE)))
				.addGap(133).addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 164, GroupLayout.PREFERRED_SIZE)
				.addContainerGap()));
		groupLayout
				.setVerticalGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addGroup(groupLayout.createSequentialGroup().addContainerGap().addComponent(
												cancel, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE))
						.addGroup(
								groupLayout.createSequentialGroup().addGap(55)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(lblNewPassword, GroupLayout.PREFERRED_SIZE, 47,
														Short.MAX_VALUE)
												.addComponent(txtOld, GroupLayout.PREFERRED_SIZE, 47,
														GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(lblConfirmPassword).addComponent(txtNew,
														GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE))
										.addGap(30).addComponent(keyPad_1, GroupLayout.PREFERRED_SIZE, 267,
												GroupLayout.PREFERRED_SIZE))
						.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE))
				.addGap(16)));
		btnNewButton_1.setFont(new Font("SansSerif", Font.BOLD, 25));
		cancel.setFont(new Font("SansSerif", Font.BOLD, 25));

		getContentPane().setLayout(groupLayout);
	}

	private void createEvents() {
		txtNew.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				txtNew.setFocusable(true);
				txtOld.setFocusable(false);
				txtOld.setBackground(null);
				txtNew.setBackground(Color.LIGHT_GRAY);
			}
		});

		txtOld.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				txtOld.setFocusable(true);
				txtNew.setFocusable(false);
				txtNew.setBackground(null);
				txtOld.setBackground(Color.LIGHT_GRAY);
			}
		});

		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		btnNewButton_1.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				CustomerCtr cusCtr = new CustomerCtr();

				if (txtOld.getText().equals(customer.getPassword())) {
					try {
						cusCtr.updateCustomer("password", txtNew.getText(), customer.getCustomerId());
						JOptionPane.showMessageDialog(null, "Password successfully updated");
						dispose();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(null, "The password does not mtach");

				}
			}
		});
	}

}
