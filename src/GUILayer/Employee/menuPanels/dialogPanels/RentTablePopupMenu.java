package GUILayer.Employee.menuPanels.dialogPanels;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import ControlLayer.RentCtr;
import ModelLayer.Rent;

@SuppressWarnings("serial")
public class RentTablePopupMenu extends JPopupMenu {

	private JTable table;
	private JMenuItem cancel = new JMenuItem("Cancel rent");

	public RentTablePopupMenu(JTable table) {
		this.table = table;
		initialize();
		createEvents();
	}

	private void initialize() {

		cancel.setFont(new Font("SansSerif", Font.BOLD, 15));
		add(cancel);
		setVisible(true);
	}

	private void createEvents() {

		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int rentId = 0;
				rentId = (int) table.getModel().getValueAt(table.getSelectedRow(), 4);
				RentCtr rentCtr = new RentCtr();
				Rent rent = rentCtr.findRent(rentId);
				int i = rentCtr.deleteRent(rentId, rent.getCustomer().getCustomerId());
				if (i <= 0) {
					JOptionPane.showMessageDialog(null, "Rent not canceled.", "Error.", JOptionPane.ERROR_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "Rent canceled.", "Canceled.", JOptionPane.WARNING_MESSAGE);
					((DefaultTableModel) table.getModel()).removeRow(table.getSelectedRow());
				}

			}
		});
	}
}
