package GUILayer.Customer.menuPanels.dialogPanels.rentTypePanels;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import ModelLayer.SportCourt;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class SportCourtTypePanel extends JPanel {
	private SportCourt sportCourt;
	private final JLabel lblId = new JLabel("Court ID");
	private final JLabel lblSport = new JLabel("Sport");
	private final JLabel lblPrice = new JLabel("Price");
	private final JTextField txtId = new JTextField();
	private final JTextField txtSport = new JTextField();
	private final JTextField txtPrice = new JTextField();

	/**
	 * Create the panel.
	 */
	public SportCourtTypePanel(SportCourt sportCourt) {
		this.sportCourt = sportCourt;
		initialize();
		initializeFields();
	}

	private void initialize() {
		setSize(new Dimension(255, 318));
		lblId.setFont(new Font("SansSerif", Font.BOLD, 25));
		lblSport.setFont(new Font("SansSerif", Font.BOLD, 25));
		lblPrice.setFont(new Font("SansSerif", Font.BOLD, 25));
		txtSport.setFont(new Font("SansSerif", Font.BOLD, 25));
		txtSport.setEnabled(false);
		txtSport.setEditable(false);
		txtSport.setColumns(10);
		txtPrice.setFont(new Font("SansSerif", Font.BOLD, 25));
		txtPrice.setEnabled(false);
		txtPrice.setEditable(false);
		txtPrice.setColumns(10);

		txtId.setFont(new Font("SansSerif", Font.BOLD, 25));
		txtId.setEnabled(false);
		txtId.setEditable(false);
		txtId.setColumns(10);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(6)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblId, GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
							.addGap(17)
							.addComponent(txtId, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED, 86, Short.MAX_VALUE)
							.addComponent(txtSport, GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblSport, GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
							.addGap(139))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblPrice, GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
							.addGap(17)
							.addComponent(txtPrice, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE)))
					.addGap(6))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(26)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(14)
							.addComponent(lblId, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 3, Short.MAX_VALUE))
						.addComponent(txtId, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE))
					.addGap(26)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(txtSport, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSport, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE))
					.addGap(88)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblPrice, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
						.addComponent(txtPrice, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE))
					.addGap(61))
		);
		setLayout(groupLayout);
	}

	private void initializeFields() {
		txtId.setText(String.valueOf(sportCourt.getCourtId()));
		txtSport.setText(sportCourt.getSport());
		txtPrice.setText(String.valueOf(sportCourt.getPrice()));
	}

}
