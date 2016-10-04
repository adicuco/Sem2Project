package GUILayer.Employee.menuPanels.dialogPanels.createEventProcess;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;

import ControlLayer.EventCtr;
import GUILayer.Employee.menuPanels.EventsPanelEmployee;

@SuppressWarnings("serial")
public class CreateEventDialogPanel extends JDialog {

	private final JPanel pnlEventProcess = new JPanel();
	private CardLayout cardLayout = new CardLayout();
	private JPanel pnlInfo;
	private JPanel pnlSportCourt;
	private JPanel pnlEquipment;
	private JPanel pnlConfirm;
	private int position;

	private final JPanel pnlButtons = new JPanel();
	private final JButton btnCancel = new JButton("Cancel");
	private final JButton btnNext = new JButton("Select SportCourt");
	private final JButton btnBack = new JButton("Create Event");

	/**
	 * Create the dialog.
	 */
	public CreateEventDialogPanel() {
		position = 1;
		initialize();
		createEvents();

		getRootPane().setBorder(BorderFactory.createEtchedBorder(Color.BLACK, Color.GRAY));
		setUndecorated(true);
		// pack();
		setLocationRelativeTo(null);
		setModal(true);
		setVisible(true);
	}

	private void initialize() {
		setBounds(100, 100, 872, 660);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout
				.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(pnlButtons, GroupLayout.PREFERRED_SIZE, 870,
												GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup().addGap(6).addComponent(pnlEventProcess,
										GroupLayout.PREFERRED_SIZE, 860, Short.MAX_VALUE)))
								.addContainerGap()));
		groupLayout
				.setVerticalGroup(
						groupLayout.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
								groupLayout.createSequentialGroup().addContainerGap()
										.addComponent(pnlEventProcess, GroupLayout.DEFAULT_SIZE, 595, Short.MAX_VALUE)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(pnlButtons,
												GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
						.addContainerGap()));

		pnlEventProcess.setLayout(cardLayout);
		pnlInfo = new CreateEventInformationPanel();
		pnlEventProcess.add(pnlInfo, "Info");
		cardLayout.show(pnlEventProcess, "Info");

		GroupLayout gl_pnlButtons = new GroupLayout(pnlButtons);
		gl_pnlButtons
				.setHorizontalGroup(
						gl_pnlButtons.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
								gl_pnlButtons.createSequentialGroup().addContainerGap()
										.addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 110,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED, 307, Short.MAX_VALUE)
										.addComponent(btnBack).addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(btnNext).addContainerGap()));
		gl_pnlButtons.setVerticalGroup(gl_pnlButtons.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlButtons.createSequentialGroup()
						.addComponent(btnCancel, GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE).addGap(9))
				.addGroup(gl_pnlButtons.createSequentialGroup()
						.addComponent(btnBack, GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE).addContainerGap())
				.addGroup(Alignment.TRAILING, gl_pnlButtons.createSequentialGroup()
						.addComponent(btnNext, GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE).addContainerGap()));
		pnlButtons.setLayout(gl_pnlButtons);
		getContentPane().setLayout(groupLayout);

		btnBack.setFont(new Font("SansSerif", Font.BOLD, 25));
		btnCancel.setFont(new Font("SansSerif", Font.BOLD, 25));
		btnNext.setFont(new Font("SansSerif", Font.BOLD, 25));
	}

	private void createEvents() {

		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});

		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				position++;
				switch (position) {
				case 1:
					btnBack.setVisible(true);
					btnNext.doClick();
					break;
				case 2:
					btnBack.setVisible(true);
					pnlSportCourt = new CreateEventSportCourtPanel();
					pnlEventProcess.add(pnlSportCourt, "SportCourt");
					cardLayout.show(pnlEventProcess, "SportCourt");
					btnNext.setText("Next");
					btnBack.setText("Back");
					break;
				case 3:
					btnBack.setVisible(true);
					pnlEquipment = new CreateEventEquipmentPanel(CreateEventSportCourtPanel.getSelectedCourts());
					pnlEventProcess.add(pnlEquipment, "Equipment");
					cardLayout.show(pnlEventProcess, "Equipment");
					break;
				case 4:
					btnBack.setVisible(true);
					pnlConfirm = new CreateEventConfirmPanel();
					pnlEventProcess.add(pnlConfirm, "Confirm");
					cardLayout.show(pnlEventProcess, "Confirm");
					btnNext.setText("Create");
					btnBack.setText("Back");
					break;
				case 5:
					int ok = JOptionPane.showConfirmDialog(null, "Are you sure you want to create the event?",
							"Confirm.", JOptionPane.YES_NO_OPTION);
					if (ok == JOptionPane.YES_OPTION) {
						EventCtr eventCtr = new EventCtr();
						try {
							eventCtr.insertEvent(CreateEventInformationPanel.geName(),
									CreateEventInformationPanel.getDesc(), CreateEventInformationPanel.getMax(),
									CreateEventInformationPanel.getMin(), CreateEventInformationPanel.getRegStartDate(),
									CreateEventInformationPanel.getRegEndDate(),
									CreateEventConfirmPanel.getFinalPrice(), CreateEventInformationPanel.getEmp(),
									CreateEventInformationPanel.getRegStartDate(),
									CreateEventInformationPanel.getEndDate(), CreateEventEquipmentPanel.getPreRentEq(),
									CreateEventSportCourtPanel.getSelectedCourts());
							EventsPanelEmployee.update();
							dispose();
						} catch (Exception e1) {
							System.out.println(e1);
						}
					}
					break;
				}
			}
		});

		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				position--;
				switch (position) {
				case 0:
					int ok = JOptionPane.showConfirmDialog(null, "Are you sure you want to create the event?",
							"Confirm.", JOptionPane.YES_NO_OPTION);
					if (ok == JOptionPane.YES_OPTION) {
						EventCtr eventCtr = new EventCtr();
						try {
							eventCtr.insertNew(CreateEventInformationPanel.geName(),
									CreateEventInformationPanel.getDesc(), CreateEventInformationPanel.getMax(),
									CreateEventInformationPanel.getMin(), CreateEventInformationPanel.getRegStartDate(),
									CreateEventInformationPanel.getEndDate(),
									CreateEventInformationPanel.getStartDate(),
									CreateEventInformationPanel.getEndDate(),
									CreateEventInformationPanel.getPriceCalculated(),
									CreateEventInformationPanel.getEmp());
							EventsPanelEmployee.update();
							dispose();
						} catch (Exception e1) {
							System.out.println(e1);
						}
					}
					break;
				case 1:
					cardLayout.show(pnlEventProcess, "Info");
					btnBack.setVisible(false);
					btnNext.setText("Next");
					break;
				case 2:
					cardLayout.show(pnlEventProcess, "SportCourt");
					btnBack.setVisible(true);
					btnNext.setText("Next");
					btnBack.setText("Back");
					break;
				case 3:
					pnlEquipment = new CreateEventEquipmentPanel(CreateEventSportCourtPanel.getSelectedCourts());
					cardLayout.show(pnlEventProcess, "Equipment");
					btnBack.setVisible(true);
					btnNext.setText("Next");
					btnBack.setText("Back");
				}
			}
		});
	}

}
