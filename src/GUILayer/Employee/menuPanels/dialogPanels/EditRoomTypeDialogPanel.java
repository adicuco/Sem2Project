package GUILayer.Employee.menuPanels.dialogPanels;

import javax.swing.JDialog;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import ControlLayer.RoomTypeCtr;

import javax.swing.JButton;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;

public class EditRoomTypeDialogPanel extends JDialog{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String typeName;
	private String price;
	private final JLabel lblEditRoomType = new JLabel("Edit Room Type");
	private final JLabel lblName = new JLabel("Name");
	private final JLabel lblPrice = new JLabel("Price");
	private final JTextField txtName = new JTextField();
	private final JTextField txtPrice = new JTextField();
	private final JButton btnCancel = new JButton("Cancel");
	private final JButton btnSave = new JButton("Save");
	
	public EditRoomTypeDialogPanel(String typeName, String price) {
		this.typeName = typeName;
		this.price = price;
		initialize();
		createEvents();
		getRootPane().setBorder(BorderFactory.createEtchedBorder(Color.BLACK, Color.GRAY));
		setUndecorated(true);
		pack();
		setLocationRelativeTo(null);
		setModal(true);
		setVisible(true);
	}
	
	
	private void initialize() {
		
		lblEditRoomType.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblName.setFont(new Font("SansSerif", Font.BOLD, 20));
		
		btnSave.setFont(new Font("SansSerif", Font.BOLD, 20));	
		btnCancel.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblPrice.setFont(new Font("SansSerif", Font.BOLD, 20));
		txtPrice.setFont(new Font("SansSerif", Font.BOLD, 20));
		txtPrice.setColumns(10);
		txtPrice.setTransferHandler(null);
		txtName.setFont(new Font("SansSerif", Font.BOLD, 20));
		txtName.setColumns(10);
		txtName.setEnabled(false);
		txtName.setEditable(false);
		txtName.setText(typeName);
		txtPrice.setText(price);
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(50)
					.addComponent(btnCancel)
					.addGap(156)
					.addComponent(btnSave, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
					.addGap(37))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(110)
					.addComponent(lblEditRoomType, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(87))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(85, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblName)
						.addComponent(lblPrice))
					.addGap(57)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(txtPrice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(63))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblEditRoomType)
					.addPreferredGap(ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblName)
						.addComponent(txtName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(33)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(txtPrice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPrice))
					.addGap(34)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSave)
						.addComponent(btnCancel))
					.addContainerGap())
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
				RoomTypeCtr rCtr = new RoomTypeCtr();
				rCtr.updateRoomType(txtName.getText(), Double.parseDouble(txtPrice.getText()));
				JOptionPane.showMessageDialog(null, "Type Updated");
				dispose();
				new ManageRoomsDialogPanel();
			}
		});
		
		txtPrice.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(txtPrice.getText().equals("") && e.getKeyChar() =='0'){
					e.consume();
				}
				if(!Character.isDigit(c) && e.getKeyChar()!='.'){
					e.consume();
				}
				if(e.getKeyChar()=='.' && txtPrice.getText().contains(".")){
					e.consume();
				}
				if(txtPrice.getText().contains(".") && ((txtPrice.getText().substring(txtPrice.getText().lastIndexOf(".")+1)).length()>1)){
					e.consume();
				}
			}
		});
		
		
	}
	


}
