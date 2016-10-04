package GUILayer.Customer.menuPanels;

import java.awt.Dimension;
import java.awt.Font;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import ModelLayer.Customer;

public class FirstPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Customer customer;

	private final JLabel lblWelcome = new JLabel("");
	private final JLabel lblDate = new JLabel(TimeClass.getDate());

	/**
	 * Create the panel.
	 */
	public FirstPanel(Customer customer) {
		this.customer = customer;
		initialize();
		createEvents();
	}

	private void initialize() {
		setSize(new Dimension(583, 438));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap().addComponent(lblWelcome)
						.addPreferredGap(ComponentPlacement.RELATED, 356, Short.MAX_VALUE).addComponent(lblDate)
						.addContainerGap()));
		lblWelcome.setFont(new Font("SansSerif", Font.BOLD, 13));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblWelcome)
								.addComponent(lblDate, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
				.addContainerGap(407, Short.MAX_VALUE)));
		lblDate.setFont(new Font("SansSerif", Font.BOLD, 13));
		lblDate.setBounds(385, 6, 192, 23);
		setLayout(groupLayout);
		lblWelcome.setText("Welcome " + customer.getfName() + " " + customer.getlName());

	}

	private void createEvents() {

		TimeClass.getTime(lblDate);
	}
}
