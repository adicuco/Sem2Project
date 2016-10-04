package GUILayer.Employee.menuPanels.dialogPanels;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import ControlLayer.EventCtr;
import GUILayer.Customer.menuPanels.TimeClass;
import ModelLayer.Event;

public class EditEventDialogPanel extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel pnlButtons = new JPanel();
	private final JButton btnCancel = new JButton("Cancel");
	private final JButton btnSave = new JButton("Save");
	private final JLabel lblName = new JLabel("Name");
	private final JLabel lblStart = new JLabel("Start Date");
	private final JLabel lblDesc = new JLabel("Description");
	private final JLabel lblMin = new JLabel("Min. Participants");
	private final JLabel lblParts = new JLabel("Participants no.");
	private final JLabel lblPrice = new JLabel("Price");
	private final JLabel lblStartDate = new JLabel("Registration Start Date");
	private final JLabel lblEmp = new JLabel("Organizer / Instructor");
	private final JTextField txtName = new JTextField();
	private final JButton btnEdit = new JButton("Edit");
	private final JLabel lblEmpId = new JLabel("Employee ID");
	private final JLabel lblMax = new JLabel("Max. Participants");
	private final JLabel lblRegEnd = new JLabel("Registration End Date");
	private final JLabel lblEnd = new JLabel("End Date");
	private final JTextField txtEmp = new JTextField();
	private final JTextField txtEmpId = new JTextField();
	private final JTextField txtRegStart = new JTextField();
	private final JTextField txtRegEnd = new JTextField();
	private final JTextField txtStart = new JTextField();
	private final JTextField txtEnd = new JTextField();
	private final JTextField txtMin = new JTextField();
	private final JTextField txtMax = new JTextField();
	private final JTextField txtParts = new JTextField();
	private final JTextField txtPrice = new JTextField();
	private final JScrollPane scrollPane = new JScrollPane();
	private final JTextArea txtDesc = new JTextArea();

	private Event event;

	public EditEventDialogPanel(Event event) {
		this.event = event;
		initialize();
		initializeText();
		createEvents();
		getRootPane().setBorder(BorderFactory.createEtchedBorder(Color.BLACK, Color.GRAY));
		setUndecorated(true);
		pack();
		setLocationRelativeTo(null);
		setModal(true);
		setVisible(true);
	}

	private void initialize() {
		txtName.setEnabled(false);
		txtName.setEditable(false);
		txtName.setColumns(10);
		txtName.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblName.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblStart.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblMin.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblParts.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblPrice.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblDesc.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblStartDate.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblEmp.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblEmpId.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblEmpId.setVisible(false);
		btnSave.setFont(new Font("SansSerif", Font.BOLD, 20));
		btnSave.setVisible(false);

		btnCancel.setFont(new Font("SansSerif", Font.BOLD, 20));
		btnEdit.setFont(new Font("SansSerif", Font.BOLD, 20));
		GroupLayout gl_pnlButtons = new GroupLayout(pnlButtons);
		gl_pnlButtons
				.setHorizontalGroup(gl_pnlButtons.createParallelGroup(Alignment.TRAILING).addGroup(Alignment.LEADING,
						gl_pnlButtons.createSequentialGroup().addGap(48).addComponent(btnCancel).addGap(262)
								.addComponent(btnEdit).addPreferredGap(ComponentPlacement.RELATED, 285, Short.MAX_VALUE)
								.addComponent(btnSave).addGap(91)));
		gl_pnlButtons.setVerticalGroup(gl_pnlButtons.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_pnlButtons.createSequentialGroup().addContainerGap(39, Short.MAX_VALUE)
						.addGroup(gl_pnlButtons.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_pnlButtons.createSequentialGroup()
										.addGroup(gl_pnlButtons.createParallelGroup(Alignment.BASELINE)
												.addComponent(btnCancel).addComponent(btnEdit))
										.addContainerGap())
						.addGroup(Alignment.TRAILING,
								gl_pnlButtons.createSequentialGroup().addComponent(btnSave).addGap(14)))));
		pnlButtons.setLayout(gl_pnlButtons);
		lblMax.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblRegEnd.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblEnd.setFont(new Font("SansSerif", Font.BOLD, 20));
		txtEmp.setText((String) null);
		txtEmp.setFont(new Font("SansSerif", Font.BOLD, 15));
		txtEmp.setEnabled(false);
		txtEmp.setEditable(false);
		txtEmp.setColumns(10);
		txtEmpId.setText((String) null);
		txtEmpId.setFont(new Font("SansSerif", Font.BOLD, 15));
		txtEmpId.setEnabled(false);
		txtEmpId.setEditable(false);
		txtEmpId.setVisible(false);
		txtEmpId.setColumns(10);
		txtRegStart.setText((String) null);
		txtRegStart.setFont(new Font("SansSerif", Font.BOLD, 15));
		txtRegStart.setEnabled(false);
		txtRegStart.setEditable(false);
		txtRegStart.setColumns(10);
		txtRegEnd.setText((String) null);
		txtRegEnd.setFont(new Font("SansSerif", Font.BOLD, 15));
		txtRegEnd.setEnabled(false);
		txtRegEnd.setEditable(false);
		txtRegEnd.setColumns(10);
		txtStart.setText((String) null);
		txtStart.setFont(new Font("SansSerif", Font.BOLD, 15));
		txtStart.setEnabled(false);
		txtStart.setEditable(false);
		txtStart.setColumns(10);
		txtEnd.setText((String) null);
		txtEnd.setFont(new Font("SansSerif", Font.BOLD, 15));
		txtEnd.setEnabled(false);
		txtEnd.setEditable(false);
		txtEnd.setColumns(10);
		txtMin.setText((String) null);
		txtMin.setFont(new Font("SansSerif", Font.BOLD, 15));
		txtMin.setEnabled(false);
		txtMin.setEditable(false);
		txtMin.setColumns(10);
		txtMax.setText((String) null);
		txtMax.setFont(new Font("SansSerif", Font.BOLD, 15));
		txtMax.setEnabled(false);
		txtMax.setEditable(false);
		txtMax.setColumns(10);
		txtParts.setText((String) null);
		txtParts.setFont(new Font("SansSerif", Font.BOLD, 15));
		txtParts.setEnabled(false);
		txtParts.setEditable(false);
		txtParts.setColumns(10);
		txtPrice.setText((String) null);
		txtPrice.setFont(new Font("SansSerif", Font.BOLD, 15));
		txtPrice.setEnabled(false);
		txtPrice.setEditable(false);
		txtPrice.setColumns(10);
		txtDesc.setLineWrap(true);
		txtDesc.setWrapStyleWord(true);
		txtDesc.setEnabled(false);
		txtDesc.setEditable(false);
		txtDesc.setFont(new Font("SansSerif", Font.BOLD, 15));

		scrollPane.setViewportView(txtDesc);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
				groupLayout.createSequentialGroup().addContainerGap().addGroup(groupLayout
						.createParallelGroup(Alignment.TRAILING)
						.addComponent(pnlButtons, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 842, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
								.addComponent(lblName, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
								.addGap(117).addComponent(txtName, GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
								.addGap(12)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(lblMin,
										GroupLayout.PREFERRED_SIZE, 210, GroupLayout.PREFERRED_SIZE)
										.addGroup(groupLayout.createSequentialGroup().addGap(169).addComponent(txtMin,
												GroupLayout.PREFERRED_SIZE, 221, GroupLayout.PREFERRED_SIZE))))
						.addGroup(groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblEmp, GroupLayout.PREFERRED_SIZE, 210,
												GroupLayout.PREFERRED_SIZE)
								.addComponent(lblEmpId, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE))
								.addGap(9)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(txtEmp, GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
										.addComponent(txtEmpId, GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE))
								.addGap(12)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblMax, GroupLayout.PREFERRED_SIZE, 210,
												GroupLayout.PREFERRED_SIZE)
										.addGroup(groupLayout.createSequentialGroup().addGap(169).addComponent(txtMax,
												GroupLayout.PREFERRED_SIZE, 221, GroupLayout.PREFERRED_SIZE))
										.addGroup(groupLayout.createSequentialGroup()
												.addComponent(lblParts, GroupLayout.PREFERRED_SIZE, 154,
														GroupLayout.PREFERRED_SIZE)
												.addGap(15).addComponent(txtParts, GroupLayout.PREFERRED_SIZE, 221,
														GroupLayout.PREFERRED_SIZE))))
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup().addGroup(groupLayout
								.createParallelGroup(Alignment.LEADING)
								.addComponent(lblStartDate, GroupLayout.PREFERRED_SIZE, 227, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup().addGap(219).addComponent(txtRegStart,
										GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE))
								.addGroup(groupLayout.createSequentialGroup()
										.addComponent(lblRegEnd, GroupLayout.PREFERRED_SIZE, 210,
												GroupLayout.PREFERRED_SIZE)
										.addGap(9)
										.addComponent(txtRegEnd, GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE))
								.addGroup(groupLayout.createSequentialGroup()
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(lblStart, GroupLayout.PREFERRED_SIZE, 100,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(lblEnd, GroupLayout.PREFERRED_SIZE, 100,
														GroupLayout.PREFERRED_SIZE))
										.addGap(119)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(txtEnd, GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
												.addComponent(txtStart, GroupLayout.DEFAULT_SIZE, 221,
														Short.MAX_VALUE))))
								.addGap(12)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(Alignment.TRAILING,
												groupLayout.createSequentialGroup()
														.addComponent(lblPrice, GroupLayout.PREFERRED_SIZE, 60,
																GroupLayout.PREFERRED_SIZE)
														.addGap(109).addComponent(txtPrice, GroupLayout.PREFERRED_SIZE,
																221, GroupLayout.PREFERRED_SIZE))
										.addComponent(lblDesc, GroupLayout.PREFERRED_SIZE, 116,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(scrollPane))))
						.addGap(9)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addGap(28)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup().addGap(1).addComponent(lblName))
								.addComponent(txtName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup().addGap(1).addComponent(lblMin))
						.addComponent(txtMin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addGap(7)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup().addGap(17).addComponent(lblEmp).addGap(5)
								.addComponent(lblEmpId))
						.addGroup(groupLayout.createSequentialGroup().addGap(16)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(txtEmp, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup().addGap(31).addComponent(txtEmpId,
										GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))))
						.addGroup(groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(lblMax)
										.addGroup(groupLayout.createSequentialGroup().addGap(2).addComponent(txtMax,
												GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)))
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(lblParts)
										.addGroup(groupLayout.createSequentialGroup().addGap(2).addComponent(txtParts,
												GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)))))
				.addGap(7)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup().addGap(27)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup().addGap(1)
												.addComponent(lblStartDate))
								.addComponent(txtRegStart, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGap(5)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(lblRegEnd)
								.addGroup(groupLayout.createSequentialGroup().addGap(2).addComponent(txtRegEnd,
										GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)))
						.addGap(23)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup().addComponent(lblStart).addGap(5)
										.addComponent(lblEnd))
								.addGroup(groupLayout.createSequentialGroup().addGap(2)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addGroup(groupLayout.createSequentialGroup().addGap(31).addComponent(
														txtEnd, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE))
												.addComponent(txtStart, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))))
						.addGroup(groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(lblPrice)
										.addGroup(groupLayout.createSequentialGroup().addGap(2).addComponent(txtPrice,
												GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)))
								.addGap(9).addComponent(lblDesc).addGap(9)
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(pnlButtons, GroupLayout.PREFERRED_SIZE, 45, Short.MAX_VALUE).addGap(16)));
		getContentPane().setLayout(groupLayout);
	}

	private void createEvents() {

		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtName.setEnabled(true);
				txtName.setEditable(true);
				txtMin.setEnabled(true);
				txtMin.setEditable(true);
				txtEmpId.setEnabled(true);
				txtEmpId.setEditable(true);
				txtEmpId.setVisible(true);
				txtMax.setEnabled(true);
				txtMax.setEditable(true);
				txtPrice.setEnabled(true);
				txtPrice.setEditable(true);
				txtDesc.setEnabled(true);
				txtDesc.setEditable(true);
				btnSave.setVisible(true);
				btnEdit.setVisible(false);

			}
		});

		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventCtr eventCtr = new EventCtr();
				try {
					eventCtr.updateEvent(event.getEventId(), txtName.getText(), txtDesc.getText(),
							Integer.valueOf(txtMax.getText()), Integer.valueOf(txtMin.getText()),
							event.getRegStartDate(), event.getRegEndDate(), event.getStartDate(), event.getEndDate(),
							event.getActive().toString(), event.getStatus().toString(),
							Double.valueOf(txtPrice.getText()), Integer.valueOf(txtEmpId.getText()));
					JOptionPane.showMessageDialog(null, "Event successfully updated.", "Succes!",
							JOptionPane.INFORMATION_MESSAGE);
					dispose();
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Event not updated.", "Error!", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}

	private void initializeText() {
		txtName.setText(event.getName());
		txtEmp.setText(event.getEmployee().getfName() + " " + event.getEmployee().getlName());
		txtEmpId.setText(String.valueOf(event.getEmployee().getEmployeeId()));
		txtRegStart.setText(TimeClass.txtDate(event.getRegStartDate()));
		txtRegEnd.setText(TimeClass.txtDate(event.getRegEndDate()));
		txtStart.setText(TimeClass.txtDate(event.getStartDate()));
		txtEnd.setText(TimeClass.txtDate(event.getEndDate()));
		txtMin.setText(String.valueOf(event.getMinParticipants()));
		txtMax.setText(String.valueOf(event.getMaxParticipants()));
		txtParts.setText(String.valueOf(event.getParticipantsNr()));
		txtPrice.setText(String.valueOf(event.getPrice()));
		txtDesc.setText(event.getDescription());
	}
}
