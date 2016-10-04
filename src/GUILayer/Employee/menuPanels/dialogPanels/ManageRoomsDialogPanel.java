package GUILayer.Employee.menuPanels.dialogPanels;

import javax.swing.JDialog;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import ControlLayer.RoomCtr;
import ControlLayer.RoomTypeCtr;
import ModelLayer.Room;
import ModelLayer.RoomType;

import java.awt.Color;
import java.awt.Font;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ManageRoomsDialogPanel extends JDialog{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel pnlType = new JPanel();
	private final JPanel pnlRoom = new JPanel();
	private final JLabel lblRoomsManagement = new JLabel("ROOMS  MANAGEMENT");
	private final JButton btnEditT = new JButton("Edit");
	private final JButton btnAddT = new JButton("ADD");
	private final JButton btnDeleteT = new JButton("Delete");
	private final JButton btnEditR = new JButton("Edit");
	private final JButton btnCancel = new JButton("Cancel");
	private final JScrollPane scrollPane = new JScrollPane();
	private final JTable typeList = new JTable();
	private final JScrollPane scrollPane_1 = new JScrollPane();
	private final JTable roomList = new JTable();
	private DefaultTableModel modelT = new DefaultTableModel();
	private DefaultTableModel modelR = new DefaultTableModel();
	private final JLabel lblRoomTypesList = new JLabel("Room Types List");
	private final JLabel lblRoomsList = new JLabel("Rooms List");

	public ManageRoomsDialogPanel() {
		
		
		initialize();
		createEvents();
		populateRoomList();
		populateTypeLis();
		
		getRootPane().setBorder(BorderFactory.createEtchedBorder(Color.BLACK, Color.GRAY));
		setUndecorated(true);
		pack();
		setLocationRelativeTo(null);
		setModal(true);
		setVisible(true);
	}
	private void initialize() {
		
		pnlRoom.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnlType.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblRoomsManagement.setFont(new Font("SansSerif", Font.BOLD, 30));
		lblRoomTypesList.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblRoomsList.setFont(new Font("SansSerif", Font.BOLD, 20));
		btnAddT.setFont(new Font("SansSerif", Font.BOLD, 20));
		btnCancel.setFont(new Font("SansSerif", Font.BOLD, 20));
		
		btnEditR.setFont(new Font("SansSerif", Font.BOLD, 20));
		btnEditT.setFont(new Font("SansSerif", Font.BOLD, 20));
		btnDeleteT.setFont(new Font("SansSerif", Font.BOLD, 20));
		btnDeleteT.setVisible(false);
		modelT = new DefaultTableModel(new Object[][] {}, new String[] { "Name", "Room Price" });
		
		typeList.setModel(modelT);
		typeList.setFont(new Font("SansSerif", Font.PLAIN, 15));
		typeList.setRowHeight(35);
		modelR = new DefaultTableModel(new Object[][] {},
				new String[] { "ID", "Room Type", "Extra Bed", "Active", "Notes" });
		roomList.setModel(modelR);
		roomList.setFont(new Font("SansSerif", Font.PLAIN, 15));
		roomList.setRowHeight(35);
		
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(598, Short.MAX_VALUE)
					.addComponent(btnCancel)
					.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(194)
					.addComponent(lblRoomsManagement, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(165))
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(pnlType, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(pnlRoom, GroupLayout.DEFAULT_SIZE, 703, Short.MAX_VALUE))
					.addGap(2))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblRoomsManagement)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(pnlType, GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(pnlRoom, GroupLayout.PREFERRED_SIZE, 239, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnCancel)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		GroupLayout gl_pnlRoom = new GroupLayout(pnlRoom);
		gl_pnlRoom.setHorizontalGroup(
			gl_pnlRoom.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlRoom.createSequentialGroup()
					.addGroup(gl_pnlRoom.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_pnlRoom.createSequentialGroup()
							.addGap(30)
							.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 550, Short.MAX_VALUE)
							.addGap(30)
							.addComponent(btnEditR, GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE))
						.addGroup(gl_pnlRoom.createSequentialGroup()
							.addGap(270)
							.addComponent(lblRoomsList)))
					.addContainerGap())
		);
		gl_pnlRoom.setVerticalGroup(
			gl_pnlRoom.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlRoom.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblRoomsList)
					.addGroup(gl_pnlRoom.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlRoom.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 189, GroupLayout.PREFERRED_SIZE)
							.addGap(5))
						.addGroup(gl_pnlRoom.createSequentialGroup()
							.addGap(33)
							.addComponent(btnEditR)
							.addContainerGap())))
		);
		
		scrollPane_1.setViewportView(roomList);
		pnlRoom.setLayout(gl_pnlRoom);
		GroupLayout gl_pnlType = new GroupLayout(pnlType);
		gl_pnlType.setHorizontalGroup(
			gl_pnlType.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_pnlType.createSequentialGroup()
					.addGroup(gl_pnlType.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_pnlType.createSequentialGroup()
							.addGap(245)
							.addComponent(lblRoomTypesList, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(195))
						.addGroup(gl_pnlType.createSequentialGroup()
							.addContainerGap()
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 279, GroupLayout.PREFERRED_SIZE)
							.addGap(133)))
					.addGroup(gl_pnlType.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnDeleteT, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(gl_pnlType.createSequentialGroup()
							.addComponent(btnEditT, GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
							.addGap(4))
						.addComponent(btnAddT, GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_pnlType.setVerticalGroup(
			gl_pnlType.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlType.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pnlType.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlType.createSequentialGroup()
							.addComponent(lblRoomTypesList, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
							.addGap(5))
						.addGroup(gl_pnlType.createSequentialGroup()
							.addComponent(btnEditT)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnAddT)
							.addGap(13)
							.addComponent(btnDeleteT)))
					.addGap(0))
		);
		
		scrollPane.setViewportView(typeList);
		pnlType.setLayout(gl_pnlType);
		getContentPane().setLayout(groupLayout);
	}
	
	private void createEvents(){
		
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		btnAddT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AddRoomTypeDialogPanel();
			}
		});
		
		btnEditT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!(typeList.getSelectedRow() == -1)){
					
				new EditRoomTypeDialogPanel(typeList.getValueAt(typeList.getSelectedRow(), 0).toString(), typeList.getValueAt(typeList.getSelectedRow(), 1).toString());
				dispose();
			}
			}
		});
		
		typeList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				RoomTypeCtr ctr = new RoomTypeCtr();
				int i=0;
				i= ctr.checkIfAssigned(typeList.getValueAt(typeList.getSelectedRow(), 0).toString());
				if(i==1){
					btnDeleteT.setVisible(false);
				}else{
					btnDeleteT.setVisible(true);
				}
				
			}
		});
		
		btnEditR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!(roomList.getSelectedRow() == -1)){
				new EditRoomDialogPanel(Integer.parseInt(roomList.getValueAt(roomList.getSelectedRow(), 0).toString()));
				dispose();
			}
			}
		});			
	}
	
	private void populateTypeLis(){
		modelT = new DefaultTableModel(new Object[][] {},new String[] { "Name", "Room Price"});
		typeList.setModel(modelT);
		modelT.setRowCount(0);
		RoomTypeCtr rCtr = new RoomTypeCtr();
		ArrayList<RoomType> rTypes = new ArrayList<RoomType>();
		rTypes = rCtr.findAllRoomTypes();
		for(RoomType r : rTypes){
			modelT.addRow(new Object[] { r.getRoomTypeName(), r.getRoomPrice()});
		}
	}
	
	private void populateRoomList(){
		modelR = new DefaultTableModel(new Object[][] {},
				new String[] { "ID", "Room Type", "Extra Bed", "Active", "Notes" });
		roomList.setModel(modelR);
		modelR.setRowCount(0);
		RoomCtr roomCtr = new RoomCtr();
		ArrayList<Room> rooms = new ArrayList<Room>();
		rooms = roomCtr.findAllRooms();
		for(Room rm : rooms){
			modelR.addRow(new Object[] { rm.getId(), rm.getRoomType().getRoomTypeName(), rm.isExtraBed(), rm.getActive().toString(), rm.getNotes()});
		}
		
	}
	
	

}
