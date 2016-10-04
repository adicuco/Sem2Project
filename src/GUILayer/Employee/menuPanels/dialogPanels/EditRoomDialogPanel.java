package GUILayer.Employee.menuPanels.dialogPanels;

import javax.swing.JDialog;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JComboBox;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

import org.omg.PortableInterceptor.ACTIVE;

import ControlLayer.CustomerCtr;
import ControlLayer.MembershipCtr;
import ControlLayer.RoomCtr;
import ControlLayer.RoomTypeCtr;
import ModelLayer.Active;
import ModelLayer.Membership;
import ModelLayer.RoomType;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class EditRoomDialogPanel extends JDialog{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JLabel lblEditRoom = new JLabel("Edit Room");
	private final JLabel lblRoomId = new JLabel("Room Id");
	private final JLabel lblRoomType = new JLabel("Room Type");
	private final JLabel lblActive = new JLabel("Active");
	private final JLabel lblNotes = new JLabel("Notes");
	private final JTextField txtId = new JTextField();
	private final JTextArea txtNotes = new JTextArea();
	private final JComboBox<String> comboType = new JComboBox();
	private final JComboBox comboActive = new JComboBox();
	private final JLabel lblExtraBed = new JLabel("Extra Bed");
	private final JComboBox<Boolean> comboBed = new JComboBox();
	private final JButton btnCancel = new JButton("Cancel");
	private final JButton btnSave = new JButton("Save");
	private int roomId;
	
	
	public EditRoomDialogPanel(int roomId) {
		this.roomId = roomId;
		initialize();
		createEvents();
		populateCombo();
		getRootPane().setBorder(BorderFactory.createEtchedBorder(Color.BLACK, Color.GRAY));
	//	setUndecorated(true);
		pack();
		setLocationRelativeTo(null);
		setModal(true);
		setVisible(true);
	}
	private void initialize() {
		RoomCtr roomCtr = new RoomCtr();
		txtId.setFont(new Font("SansSerif", Font.BOLD, 20));
		txtId.setColumns(10);
		txtId.setEditable(false);
		txtId.setEnabled(false);
		txtId.setText(String.valueOf(roomId));
		btnCancel.setFont(new Font("SansSerif", Font.BOLD, 20));
		
		btnSave.setFont(new Font("SansSerif", Font.BOLD, 20));
		comboBed.setFont(new Font("SansSerif", Font.BOLD, 20));
		comboActive.setFont(new Font("SansSerif", Font.BOLD, 20));
		comboType.setFont(new Font("SansSerif", Font.BOLD, 20));
		txtNotes.setFont(new Font("SansSerif", Font.PLAIN, 20));
		txtNotes.setText(roomCtr.findRoom(roomId).getNotes());
		lblRoomId.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblActive.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblRoomType.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblExtraBed.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblNotes.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblEditRoom.setFont(new Font("SansSerif", Font.BOLD, 30));
		
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(249, Short.MAX_VALUE)
					.addComponent(lblEditRoom, GroupLayout.PREFERRED_SIZE, 195, GroupLayout.PREFERRED_SIZE)
					.addGap(162))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addGap(28)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addComponent(lblNotes)
							.addGap(18)
							.addComponent(txtNotes, GroupLayout.DEFAULT_SIZE, 453, Short.MAX_VALUE))
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblRoomId)
								.addComponent(lblActive))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(txtId, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
								.addComponent(comboActive, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(27)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblExtraBed)
								.addComponent(lblRoomType))
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(36)
									.addComponent(comboBed, 0, 132, Short.MAX_VALUE))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(37)
									.addComponent(comboType, 0, 131, Short.MAX_VALUE)))))
					.addGap(52))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addGap(41)
					.addComponent(btnCancel)
					.addPreferredGap(ComponentPlacement.RELATED, 350, Short.MAX_VALUE)
					.addComponent(btnSave)
					.addGap(39))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblEditRoom)
					.addGap(30)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblRoomId)
								.addComponent(txtId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(comboType, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(46)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblActive)
								.addComponent(comboActive, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(comboBed, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblExtraBed)))
						.addComponent(lblRoomType))
					.addGap(28)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNotes)
						.addComponent(txtNotes, GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE))
					.addGap(28)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCancel)
						.addComponent(btnSave))
					.addGap(22))
		);
		
		getContentPane().setLayout(groupLayout);
	}

	private void createEvents(){
		
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RoomCtr rCtr = new RoomCtr();
				rCtr.updateRoom(roomId, comboType.getSelectedItem().toString(), (boolean) comboBed.getSelectedItem(), 
						rCtr.findRoom(roomId).getStatus().toString(), comboActive.getSelectedItem().toString(), txtNotes.getText());
			JOptionPane.showMessageDialog(null, "Room Updated");
		dispose();
		new ManageRoomsDialogPanel();
			}
		});
	}
	
	private void populateCombo(){
		comboType.setModel(new DefaultComboBoxModel<String>());
		RoomCtr roomCtr = new RoomCtr();
		RoomTypeCtr ctr = new RoomTypeCtr();
		ArrayList<RoomType> types = ctr.findAllRoomTypes();
		for (RoomType m : types) {
			comboType.addItem(m.getRoomTypeName());
		}
		comboType.setSelectedItem(roomCtr.findRoom(roomId).getRoomType().getRoomTypeName().toString());

		comboActive.setModel(new DefaultComboBoxModel<>(Active.values()));
		comboActive.setSelectedItem(roomCtr.findRoom(roomId).getActive());
		
		comboBed.addItem(true);
		comboBed.addItem(false);
		comboBed.setSelectedItem(roomCtr.findRoom(roomId).isExtraBed());
	}
	
	
	
}	
