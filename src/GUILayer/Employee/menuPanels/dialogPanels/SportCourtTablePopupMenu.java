package GUILayer.Employee.menuPanels.dialogPanels;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JSeparator;
import javax.swing.JTable;

import ControlLayer.SportCourtCtr;
import GUILayer.Customer.menuPanels.SportCourtsPanel;
import ModelLayer.Active;
import ModelLayer.SportCourt;

@SuppressWarnings("serial")
public class SportCourtTablePopupMenu extends JPopupMenu {

	private JTable table;
	private ArrayList<SportCourt> sportCourts;
	private JMenuItem price = new JMenuItem("Edit price");
	private JMenuItem close = new JMenuItem("Close court");
	private JMenuItem open = new JMenuItem("Open court");

	public SportCourtTablePopupMenu(JTable table, ArrayList<SportCourt> sportCourts) {
		this.table = table;
		this.sportCourts = sportCourts;
		initialize();
		createEvents();
	}

	private void initialize() {
		price.setFont(new Font("SansSerif", Font.BOLD, 15));
		close.setFont(new Font("SansSerif", Font.BOLD, 15));
		open.setFont(new Font("SansSerif", Font.BOLD, 15));
		add(price);
		add(new JSeparator());
		add(open);
		add(new JSeparator());
		add(close);
		setVisible(true);
	}

	private void createEvents() {

		price.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int j = table.getSelectedColumn();
				SportCourt sp = sportCourts.get(j - 1);
				double newPrice = 0;
				try {
					newPrice = Double.valueOf(
							JOptionPane.showInputDialog("Current price is:" + sp.getPrice() + "\nEnter the new price"));
				} catch (Exception ex) {
				}
				if (newPrice > 0) {
					sp.setPrice(newPrice);
					SportCourtCtr spCtr = new SportCourtCtr();
					spCtr.updateSportCourt(sp.getCourtId(), newPrice);
				}
			}
		});

		open.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int j = table.getSelectedColumn();
				SportCourt sp = sportCourts.get(j - 1);
				sp.setActive(Active.ACTIVE);
				SportCourtCtr spCtr = new SportCourtCtr();
				spCtr.openSportCourt(sp.getCourtId());
				SportCourtsPanel.updateTable();
			}
		});

		close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int j = table.getSelectedColumn();
				SportCourt sp = sportCourts.get(j - 1);
				sp.setActive(Active.INACTIVE);
				SportCourtCtr spCtr = new SportCourtCtr();
				spCtr.closeSportCourt(sp.getCourtId());
				SportCourtsPanel.updateTable();
			}
		});
	}
}
