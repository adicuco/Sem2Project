package GUILayer.Customer.menuPanels.dialogPanels.rentTypePanels;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import ModelLayer.Equipment;
import javax.swing.ScrollPaneConstants;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class EquipmentTypePanel extends JPanel {
	private Equipment eq;
	private final JLabel lblName = new JLabel("Name");
	private final JLabel lblType = new JLabel("Type");
	private final JLabel lblEquipmentPrice = new JLabel("Price");
	private final JLabel lblDescription = new JLabel("Description");
	private final JTextField txtName = new JTextField();
	private final JTextField txtType = new JTextField();
	private final JTextField txtPrice = new JTextField();
	private final JScrollPane scrollPane = new JScrollPane();
	private final JTextArea txtDescription = new JTextArea();

	/**
	 * Create the panel.
	 */
	public EquipmentTypePanel(Equipment eq) {
		this.eq = eq;
		initialize();
		initializeFields();
	}

	private void initialize() {
		setSize(new Dimension(303, 318));
		lblName.setFont(new Font("SansSerif", Font.BOLD, 25));
		lblType.setFont(new Font("SansSerif", Font.BOLD, 25));
		lblEquipmentPrice.setFont(new Font("SansSerif", Font.BOLD, 25));
		lblDescription.setFont(new Font("SansSerif", Font.BOLD, 25));
		txtType.setFont(new Font("SansSerif", Font.BOLD, 25));
		txtType.setEnabled(false);
		txtType.setEditable(false);
		txtType.setColumns(10);
		txtPrice.setFont(new Font("SansSerif", Font.BOLD, 25));
		txtPrice.setEnabled(false);
		txtPrice.setEditable(false);
		txtPrice.setColumns(10);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		txtDescription.setWrapStyleWord(true);
		txtDescription.setLineWrap(true);
		txtDescription.setFont(new Font("SansSerif", Font.BOLD, 25));
		txtDescription.setEnabled(false);
		txtDescription.setEditable(false);

		scrollPane.setViewportView(txtDescription);

		txtName.setFont(new Font("SansSerif", Font.BOLD, 25));
		txtName.setEnabled(false);
		txtName.setEditable(false);
		txtName.setColumns(10);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup()
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup().addContainerGap()
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblEquipmentPrice, GroupLayout.PREFERRED_SIZE, 72,
												GroupLayout.PREFERRED_SIZE)
								.addComponent(lblType, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblName, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE))
								.addGap(5)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(txtPrice, GroupLayout.PREFERRED_SIZE, 159,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(txtName, GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
										.addGroup(groupLayout.createSequentialGroup()
												.addPreferredGap(ComponentPlacement.RELATED).addComponent(txtType,
														GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE))))
						.addGroup(groupLayout.createSequentialGroup().addContainerGap().addComponent(lblDescription,
								GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE))
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE))
				.addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
										.addComponent(txtName, GroupLayout.PREFERRED_SIZE, 44,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(txtType, GroupLayout.PREFERRED_SIZE, 39,
												GroupLayout.PREFERRED_SIZE)
										.addGap(26))
						.addGroup(groupLayout.createSequentialGroup().addComponent(lblName).addGap(18)
								.addComponent(lblType).addGap(18)))
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEquipmentPrice, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtPrice, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(lblDescription)
				.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
				.addContainerGap()));
		setLayout(groupLayout);
	}

	private void initializeFields() {
		txtName.setText(eq.getName());
		txtType.setText(eq.getEqType().getName());
		txtPrice.setText(String.valueOf(eq.getPrice()));
		txtDescription.setText(eq.getDescription());
	}
}
