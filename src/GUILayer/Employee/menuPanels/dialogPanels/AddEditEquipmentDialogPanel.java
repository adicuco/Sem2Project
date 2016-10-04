package GUILayer.Employee.menuPanels.dialogPanels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import ControlLayer.EquipmentCtr;
import ControlLayer.EquipmentTypeCtr;
import GUILayer.Customer.menuPanels.EquipmentPanel;
import ModelLayer.Active;
import ModelLayer.Equipment;
import ModelLayer.EquipmentType;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

@SuppressWarnings("serial")
public class AddEditEquipmentDialogPanel extends JDialog {

	private Equipment eq;
	private final JLabel lblId = new JLabel("ID");
	private final JLabel lblName = new JLabel("Name");
	private final JLabel lblDescription = new JLabel("Description");
	private final JLabel lblQuantity = new JLabel("Quantity");
	private final JLabel lblPrice = new JLabel("Price");
	private final JLabel lblStatus = new JLabel("Status");
	private final JLabel lblEquipmentType = new JLabel("Equipment Type");
	private final JLabel lblTitle = new JLabel("Add new equipment");
	private final JButton btnCancel = new JButton("Cancel");
	private final JButton btnEdit = new JButton("Clear");
	private final JButton btnConfirm = new JButton("Confirm");
	private final JTextField txtQuantity = new JTextField();
	private final JTextField txtPrice = new JTextField();
	private final JTextField txtName = new JTextField();
	private final JTextField txtId = new JTextField();
	private final JScrollPane scrollPane = new JScrollPane();
	private final JTextArea txtDescription = new JTextArea();
	private final JComboBox<String> cbEqType = new JComboBox<String>();
	private final JComboBox<Active> cbActive = new JComboBox<Active>();

	public AddEditEquipmentDialogPanel(Equipment eq) {
		this.eq = eq;
		initialize();
		fillComboBoxes();
		if (eq != null) {
			initializeText();
		}
		createEvents();

		getRootPane().setBorder(BorderFactory.createEtchedBorder(Color.BLACK, Color.GRAY));
		setUndecorated(true);
		pack();
		setLocationRelativeTo(null);
		setModal(true);
		setVisible(true);
	}

	private void initialize() {
		setSize(new Dimension(694, 494));
		lblId.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblName.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblDescription.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblQuantity.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblPrice.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblStatus.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblEquipmentType.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblTitle.setForeground(Color.DARK_GRAY);
		lblTitle.setFont(new Font("SansSerif", Font.BOLD, 30));

		btnCancel.setFont(new Font("SansSerif", Font.BOLD, 20));

		btnEdit.setFont(new Font("SansSerif", Font.BOLD, 20));

		btnConfirm.setFont(new Font("SansSerif", Font.BOLD, 20));
		txtQuantity.setFont(new Font("SansSerif", Font.BOLD, 20));
		txtQuantity.setColumns(10);
		txtPrice.setFont(new Font("SansSerif", Font.BOLD, 20));
		txtPrice.setColumns(10);
		txtName.setFont(new Font("SansSerif", Font.BOLD, 20));
		txtName.setColumns(10);
		txtId.setFont(new Font("SansSerif", Font.BOLD, 20));
		txtId.setColumns(10);
		txtDescription.setWrapStyleWord(true);
		txtDescription.setLineWrap(true);
		txtDescription.setFont(new Font("SansSerif", Font.BOLD, 20));

		scrollPane.setViewportView(txtDescription);
		cbEqType.setFont(new Font("SansSerif", Font.PLAIN, 20));
		cbActive.setFont(new Font("SansSerif", Font.PLAIN, 20));
		txtId.setVisible(false);
		cbActive.setVisible(false);
		txtId.setEnabled(false);
		lblId.setVisible(false);
		lblStatus.setVisible(false);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout
				.setHorizontalGroup(
						groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup().addGap(192).addComponent(lblTitle,
										GroupLayout.PREFERRED_SIZE, 288, GroupLayout.PREFERRED_SIZE))
								.addGroup(Alignment.TRAILING,
										groupLayout.createSequentialGroup().addGap(39)
												.addComponent(lblId, GroupLayout.PREFERRED_SIZE, 55,
														GroupLayout.PREFERRED_SIZE)
												.addGap(118)
												.addComponent(txtId, GroupLayout.PREFERRED_SIZE, 68,
														GroupLayout.PREFERRED_SIZE)
												.addGap(115).addComponent(lblStatus).addGap(27)
												.addComponent(cbActive, GroupLayout.PREFERRED_SIZE, 161,
														GroupLayout.PREFERRED_SIZE)
								.addGap(24)).addGroup(
										Alignment.TRAILING,
										groupLayout.createSequentialGroup().addGap(39)
												.addComponent(lblName, GroupLayout.PREFERRED_SIZE, 55,
														GroupLayout.PREFERRED_SIZE)
												.addGap(118)
												.addComponent(txtName, GroupLayout.PREFERRED_SIZE, 161,
														GroupLayout.PREFERRED_SIZE)
												.addGap(22).addComponent(lblDescription).addGap(165))
								.addGroup(groupLayout.createSequentialGroup().addGap(39)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(lblEquipmentType)
												.addComponent(lblPrice, GroupLayout.PREFERRED_SIZE, 55,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(lblQuantity))
						.addGap(19)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(cbEqType, GroupLayout.PREFERRED_SIZE, 161, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtPrice, GroupLayout.PREFERRED_SIZE, 161, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtQuantity, GroupLayout.PREFERRED_SIZE, 161, GroupLayout.PREFERRED_SIZE))
						.addGap(20).addComponent(scrollPane).addContainerGap()).addGroup(
								groupLayout.createSequentialGroup().addContainerGap()
										.addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 108,
												GroupLayout.PREFERRED_SIZE)
										.addGap(162)
										.addComponent(btnEdit, GroupLayout.PREFERRED_SIZE, 108,
												GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED, 170, Short.MAX_VALUE)
						.addComponent(btnConfirm, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)
						.addContainerGap()));
		groupLayout
				.setVerticalGroup(
						groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup().addGap(35)
										.addComponent(lblTitle, GroupLayout.PREFERRED_SIZE, 42,
												GroupLayout.PREFERRED_SIZE)
								.addGap(33)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup().addGap(11).addComponent(lblId,
												GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))
								.addComponent(txtId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup().addGap(11).addComponent(lblStatus,
										GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))
								.addComponent(cbActive, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
				.addGap(9)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup().addGap(17).addComponent(lblName,
								GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))
						.addComponent(txtName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup().addGap(12).addComponent(lblDescription)))
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup().addGap(4)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup().addGap(3)
												.addComponent(lblEquipmentType).addGap(45)
												.addComponent(lblPrice, GroupLayout.PREFERRED_SIZE, 16,
														GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(lblQuantity))
								.addGroup(groupLayout.createSequentialGroup()
										.addComponent(cbEqType, GroupLayout.PREFERRED_SIZE, 38,
												GroupLayout.PREFERRED_SIZE)
										.addGap(19)
										.addComponent(txtPrice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addGap(9).addComponent(txtQuantity, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
						.addGroup(groupLayout.createSequentialGroup().addGap(12).addComponent(scrollPane,
								GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)))
				.addGap(74)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnEdit, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnConfirm, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE))
				.addContainerGap()));
		getContentPane().setLayout(groupLayout);
	}

	private void fillComboBoxes() {
		EquipmentTypeCtr eqTypeCtr = new EquipmentTypeCtr();
		for (EquipmentType eqType : eqTypeCtr.findAllEquipmentTypes()) {
			cbEqType.addItem(eqType.getName());
		}

		cbActive.setModel(new DefaultComboBoxModel<>(Active.values()));

	}

	private void initializeText() {
		lblTitle.setText("Edit equipment");
		btnEdit.setText("Edit");
		txtId.setText(String.valueOf(eq.getEqId()));
		txtName.setText(eq.getName());
		txtPrice.setText(String.valueOf(eq.getPrice()));
		txtQuantity.setText(String.valueOf(eq.getQuantity()));
		txtDescription.setText(eq.getDescription());
		cbEqType.setSelectedItem(eq.getEqType().getName());
		txtId.setVisible(true);
		cbActive.setVisible(true);
		lblId.setVisible(true);
		lblStatus.setVisible(true);
		enableTxt(false);
	}

	private void enableTxt(boolean b) {
		txtName.setEnabled(b);
		txtPrice.setEnabled(b);
		txtDescription.setEnabled(b);
		txtQuantity.setEnabled(b);
		cbActive.setEnabled(b);
		cbEqType.setEnabled(b);
	}

	private void createEvents() {

		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});

		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (eq != null) {
					enableTxt(true);
				} else {
					txtName.setText("");
					txtPrice.setText("");
					txtQuantity.setText("");
					txtDescription.setText("");
				}
			}
		});

		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EquipmentCtr eqCtr = new EquipmentCtr();
				EquipmentTypeCtr eqTypeCtr = new EquipmentTypeCtr();
				if (eq != null) {
					int rc = eqCtr.updateEquipment(eq.getEqId(),
							eqTypeCtr.findEquipmentType((String) cbEqType.getSelectedItem()).getEqTypeId(),
							txtName.getText(), txtDescription.getText(), Integer.valueOf(txtQuantity.getText()),
							Double.valueOf(txtPrice.getText()), cbActive.getSelectedItem().toString());
					if (rc == 1) {
						JOptionPane.showMessageDialog(null, "Equipment successfully updated.", "Succes!",
								JOptionPane.INFORMATION_MESSAGE);
						EquipmentPanel.updateAll();
						dispose();
					} else {
						JOptionPane.showMessageDialog(null, "Equipment not updated.", "Error!",
								JOptionPane.ERROR_MESSAGE);
					}

				} else {
					try {
						eqCtr.insertNew(eqTypeCtr.findEquipmentType((String) cbEqType.getSelectedItem()).getEqTypeId(),
								txtName.getText(), txtDescription.getText(), Integer.valueOf(txtQuantity.getText()),
								Double.valueOf(txtPrice.getText()));
						JOptionPane.showMessageDialog(null, "Equipment successfully added.", "Succes!",
								JOptionPane.INFORMATION_MESSAGE);
						EquipmentPanel.updateAll();
						dispose();
					} catch (NumberFormatException e1) {
						e1.printStackTrace();
						JOptionPane.showMessageDialog(null, "Equipment not added.", "Error!",
								JOptionPane.ERROR_MESSAGE);
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "Equipment not added.", "Error!",
								JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					}
				}
			}
		});

	}
}
