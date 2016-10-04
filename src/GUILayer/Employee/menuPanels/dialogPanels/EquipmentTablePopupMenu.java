package GUILayer.Employee.menuPanels.dialogPanels;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTable;

import ControlLayer.EquipmentCtr;
import ModelLayer.Equipment;

@SuppressWarnings("serial")
public class EquipmentTablePopupMenu extends JPopupMenu {

	private JTable table;
	private JMenuItem edit = new JMenuItem("Edit");

	public EquipmentTablePopupMenu(JTable table) {
		this.table = table;
		initialize();
		createEvents();
	}

	private void initialize() {
		edit.setFont(new Font("SansSerif", Font.BOLD, 15));
		add(edit);
		setVisible(true);
	}

	private void createEvents() {

		edit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EquipmentCtr eqCtr = new EquipmentCtr();
				Equipment eq = eqCtr.findEquipment(((int) table.getModel().getValueAt(table.getSelectedRow(), 3)));
				new AddEditEquipmentDialogPanel(eq);
			}
		});
	}
}
