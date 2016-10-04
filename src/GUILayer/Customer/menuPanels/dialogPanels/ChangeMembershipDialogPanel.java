package GUILayer.Customer.menuPanels.dialogPanels;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import ControlLayer.CustomerCtr;
import ControlLayer.MembershipCtr;
import GUILayer.Customer.MainMenuCustomer;
import ModelLayer.Customer;
import ModelLayer.Membership;

public class ChangeMembershipDialogPanel extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Customer customer;
	private JLabel lblYourActualMembership = new JLabel("Your actual membership status is:");
	private JLabel lblPleaseSelectThe = new JLabel("Please select the new type of membership");
	private JButton btnCancel = new JButton("Cancel");
	private JButton btnUpdate = new JButton("Update");
	private ArrayList<Membership> ms;

	private JComboBox<String> comboBox = new JComboBox<String>();
	private final JLabel lblPrice = new JLabel("Price:");
	private final JLabel lblDiscount = new JLabel("Discount:");
	private final JLabel label = new JLabel("Price:");
	private final JLabel label_1 = new JLabel("Discount:");
	private final JTextField txtActualPrice = new JTextField((String) null);
	private final JTextField txtActualDiscount = new JTextField((String) null);
	private final JTextField txtNewPrice = new JTextField((String) null);
	private final JTextField txtNewDiscount = new JTextField((String) null);

	public ChangeMembershipDialogPanel(Customer customer) {
		this.customer = customer;
		initialize();
		fillCombo();
		createEvents();

		getRootPane().setBorder(BorderFactory.createEtchedBorder(Color.BLACK, Color.GRAY));
		setUndecorated(true);
		pack();
		setLocationRelativeTo(null);
		setModal(true);
		setVisible(true);

	}

	private void initialize() {
		setSize(new Dimension(696, 466));
		lblYourActualMembership.setFont(new Font("SansSerif", Font.BOLD, 25));
		lblPleaseSelectThe.setFont(new Font("SansSerif", Font.BOLD, 25));
		JTextField txtActualMembership = new JTextField(customer.getMembership().getName());
		txtActualMembership.setEnabled(false);
		txtActualMembership.setEditable(false);
		txtActualMembership.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 25));

		btnCancel.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnCancel.setFont(new Font("SansSerif", Font.BOLD, 25));

		btnUpdate.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnUpdate.setFont(new Font("SansSerif", Font.BOLD, 25));
		comboBox.setFont(new Font("SansSerif", Font.BOLD, 25));

		txtNewDiscount.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 25));
		txtNewDiscount.setEnabled(false);
		txtNewDiscount.setEditable(false);
		txtNewPrice.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 25));
		txtNewPrice.setEnabled(false);
		txtNewPrice.setEditable(false);
		txtActualDiscount.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 25));
		txtActualDiscount.setEnabled(false);
		txtActualDiscount.setEditable(false);
		txtActualPrice.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 25));
		txtActualPrice.setEnabled(false);
		txtActualPrice.setEditable(false);
		label_1.setFont(new Font("SansSerif", Font.BOLD, 25));
		label.setFont(new Font("SansSerif", Font.BOLD, 25));
		lblDiscount.setFont(new Font("SansSerif", Font.BOLD, 25));
		lblPrice.setFont(new Font("SansSerif", Font.BOLD, 25));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.TRAILING).addGroup(groupLayout
				.createSequentialGroup()
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup().addGap(166).addComponent(lblPrice).addGap(18)
								.addComponent(txtActualPrice, GroupLayout.PREFERRED_SIZE, 101,
										GroupLayout.PREFERRED_SIZE)
								.addGap(18).addComponent(lblDiscount).addGap(18).addComponent(txtActualDiscount,
										GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.TRAILING,
								groupLayout.createSequentialGroup().addContainerGap()
										.addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 147,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED, 578, Short.MAX_VALUE)
										.addComponent(btnUpdate, GroupLayout.PREFERRED_SIZE, 147,
												GroupLayout.PREFERRED_SIZE)))
				.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup().addGap(163).addComponent(label).addGap(18)
										.addComponent(txtNewPrice, GroupLayout.PREFERRED_SIZE, 101,
												GroupLayout.PREFERRED_SIZE)
										.addGap(18)
										.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 123,
												GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(txtNewDiscount,
										GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
								.addContainerGap(126, Short.MAX_VALUE)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblYourActualMembership).addComponent(lblPleaseSelectThe))))
						.addGap(8)
						.addGroup(
								groupLayout.createParallelGroup(Alignment.TRAILING)
										.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 139,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(txtActualMembership, GroupLayout.PREFERRED_SIZE, 175,
												GroupLayout.PREFERRED_SIZE))
						.addGap(78)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addContainerGap(76, Short.MAX_VALUE)
				.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING).addComponent(lblYourActualMembership)
						.addComponent(txtActualMembership, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addGap(37)
				.addGroup(
						groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtActualDiscount, GroupLayout.PREFERRED_SIZE, 36,
										GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDiscount, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtActualPrice, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPrice, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
				.addGap(38)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblPleaseSelectThe)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addGap(15)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtNewPrice, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtNewDiscount, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
						.addComponent(label, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
				.addGap(81)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnUpdate, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE))
				.addContainerGap()));
		getContentPane().setLayout(groupLayout);

	}

	private void createEvents() {

		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtNewPrice.setText(String.valueOf(ms.get(comboBox.getSelectedIndex()).getPrice()));
				txtNewDiscount.setText(String.valueOf(ms.get(comboBox.getSelectedIndex()).getDiscount()) + "%");
			}
		});

		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CustomerCtr cusCtr = new CustomerCtr();
				int ok = JOptionPane.showConfirmDialog(null,
						"Are you sure you want to change your membership? (you will have to login again so the changes apply)",
						"Change Membership.", JOptionPane.YES_NO_OPTION);
				if (ok == JOptionPane.YES_OPTION) {
					try {
						cusCtr.updateCustomer("msName", comboBox.getSelectedItem().toString(),
								customer.getCustomerId());
						MainMenuCustomer.close();
						dispose();

					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
	}

	private void fillCombo() {
		txtActualPrice.setText(String.valueOf(customer.getMembership().getPrice()));
		txtActualDiscount.setText(String.valueOf(customer.getMembership().getDiscount()) + "%");
		comboBox.setModel(new DefaultComboBoxModel<String>());
		MembershipCtr ctr = new MembershipCtr();
		ms = ctr.findAllMemberships();
		for (Membership m : ms) {
			comboBox.addItem(m.getName());
		}
		txtNewPrice.setText(String.valueOf(ms.get(comboBox.getSelectedIndex()).getPrice()));
		txtNewDiscount.setText(String.valueOf(ms.get(comboBox.getSelectedIndex()).getDiscount()) + "%");
	}
}
