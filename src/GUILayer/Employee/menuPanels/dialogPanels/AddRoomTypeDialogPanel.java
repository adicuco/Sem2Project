package GUILayer.Employee.menuPanels.dialogPanels;

import javax.swing.JDialog;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;

import ControlLayer.RoomTypeCtr;
import ModelLayer.RoomType;

import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;

public class AddRoomTypeDialogPanel extends JDialog{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JLabel lblNewRoomType = new JLabel("New Room Type");
	private final JLabel lblTypeName = new JLabel("Type Name");
	private final JLabel lblTypePrice = new JLabel("Type Price");
	private final JTextField txtName = new JTextField();
	private final JTextField txtPrice = new JTextField();
	private final JButton btnSave = new JButton("Save");
	private final JButton btnCancel = new JButton("Cancel");
	
	
	public AddRoomTypeDialogPanel() {
		
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
		btnCancel.setFont(new Font("SansSerif", Font.BOLD, 20));
		
		btnSave.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblNewRoomType.setFont(new Font("SansSerif", Font.BOLD, 30));
		lblTypeName.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblTypePrice.setFont(new Font("SansSerif", Font.BOLD, 20));
		txtPrice.setFont(new Font("SansSerif", Font.BOLD, 20));
		txtPrice.setColumns(10);
		txtName.setFont(new Font("SansSerif", Font.BOLD, 20));
		txtName.setColumns(10);
		txtPrice.setTransferHandler(null);
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnCancel)
					.addPreferredGap(ComponentPlacement.RELATED, 289, Short.MAX_VALUE)
					.addComponent(btnSave, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
					.addGap(28))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(148)
					.addComponent(lblNewRoomType, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(132))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(122, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblTypeName)
						.addComponent(lblTypePrice))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(txtPrice)
						.addComponent(txtName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(91))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewRoomType)
					.addGap(36)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(txtName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(32)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtPrice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblTypePrice)))
						.addComponent(lblTypeName))
					.addGap(44)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCancel)
						.addComponent(btnSave))
					.addGap(25))
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
				RoomTypeCtr rCtr  = new RoomTypeCtr();
				try {
					rCtr.insertNew(txtName.getText(), Double.parseDouble(txtPrice.getText()));
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}			
				JOptionPane.showMessageDialog(null, "Type Created");
				dispose();
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
