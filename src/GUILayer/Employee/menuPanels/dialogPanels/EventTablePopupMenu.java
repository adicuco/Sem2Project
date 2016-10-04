package GUILayer.Employee.menuPanels.dialogPanels;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JSeparator;
import javax.swing.JTable;

import ControlLayer.EventCtr;
import GUILayer.Employee.menuPanels.EventsPanelEmployee;

@SuppressWarnings("serial")
public class EventTablePopupMenu extends JPopupMenu {

	private JTable table;
	private JMenuItem edit = new JMenuItem("Edit");
	private JMenuItem cancel = new JMenuItem("Cancel event");

	public EventTablePopupMenu(JTable table) {
		this.table = table;
		initialize();
		createEvents();
	}

	private void initialize() {
		edit.setFont(new Font("SansSerif", Font.BOLD, 15));
		cancel.setFont(new Font("SansSerif", Font.BOLD, 15));

		add(edit);
		add(new JSeparator());
		add(cancel);
		setVisible(true);
	}

	private void createEvents() {

		edit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int eventId = (int) table.getModel().getValueAt(table.getSelectedRow(), 5);
				EventCtr eventCtr = new EventCtr();
				new EditEventDialogPanel(eventCtr.findEvent(eventId));
			}
		});

		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int eventId = (int) table.getModel().getValueAt(table.getSelectedRow(), 5);
					EventCtr eventCtr = new EventCtr();
					int ok = eventCtr.removeEvent(eventId);
					if (ok != 1) {
						JOptionPane.showMessageDialog(null, "Event not canceled.", "Error!", JOptionPane.ERROR_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, "Event successfully canceled.", "Succes!",
								JOptionPane.INFORMATION_MESSAGE);
						EventsPanelEmployee.update();
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Event not canceled.", "Error!", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}
}
