package GUILayer.Employee.menuPanels.dialogPanels;

import javax.swing.JDialog;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;

import ControlLayer.EmployeeCtr;
import ModelLayer.Person;
import ModelLayer.Person.AccessLvl;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;

public class AddEmployeeDialogPanel extends JDialog{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel pnlButtons = new JPanel();
	private final JButton btnCancel = new JButton("Cancel");
	private final JButton btnAddEmployee = new JButton("Add Employee");
	private final JLabel lblFirstName = new JLabel("First Name");
	private final JLabel lblLastName = new JLabel("Last Name");
	private final JLabel lblCpr = new JLabel("CPR");
	private final JLabel lblAddress = new JLabel("Address");
	private final JLabel lblCity = new JLabel("City");
	private final JLabel lblZip = new JLabel("Zip");
	private final JLabel lblEmail = new JLabel("Email");
	private final JLabel lblPhone = new JLabel("Phone");
	private final JLabel lblAcceslvl = new JLabel("Access level");
	private final JTextField txtFName = new JTextField();
	private final JTextField txtCPR = new JTextField();
	private final JTextField txtAddress = new JTextField();
	private final JTextField txtEmail = new JTextField();
	private final JTextField txtLName = new JTextField();
	private final JTextField txtCity = new JTextField();
	private final JTextField txtZip = new JTextField();
	private final JTextField txtPhone = new JTextField();
	private final JComboBox comboAccessLvl = new JComboBox();
	private final JButton btnClear = new JButton("Clear");
	
		
	public AddEmployeeDialogPanel() {
		
		initialize();
		createEvents();
		fillCombo();
		getRootPane().setBorder(BorderFactory.createEtchedBorder(Color.BLACK, Color.GRAY));
		setUndecorated(true);
		pack();
		setLocationRelativeTo(null);
		setModal(true);
		setVisible(true);
	}
	
	
	private void initialize() {
		
		txtPhone.setColumns(10);
		txtZip.setColumns(10);
		txtCity.setColumns(10);
		txtLName.setColumns(10);
		txtEmail.setColumns(10);
		txtAddress.setColumns(10);
		txtCPR.setColumns(10);
		txtFName.setColumns(10);
		txtFName.setFont(new Font("SansSerif", Font.BOLD, 15));
		txtLName.setFont(new Font("SansSerif", Font.BOLD, 15));
		txtAddress.setFont(new Font("SansSerif", Font.BOLD, 15));
		txtCity.setFont(new Font("SansSerif", Font.BOLD, 15));
		txtZip.setFont(new Font("SansSerif", Font.BOLD, 15));
		txtCPR.setFont(new Font("SansSerif", Font.BOLD, 15));
		txtPhone.setFont(new Font("SansSerif", Font.BOLD, 15));
		txtEmail.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblFirstName.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblLastName.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblAddress.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblCity.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblZip.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblCpr.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblEmail.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblPhone.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblAcceslvl.setFont(new Font("SansSerif", Font.BOLD, 20));
		comboAccessLvl.setFont(new Font("SansSerif", Font.BOLD, 15));
		
		btnAddEmployee.setFont(new Font("SansSerif", Font.BOLD, 20));
		btnCancel.setFont(new Font("SansSerif", Font.BOLD, 20));
		btnClear.setFont(new Font("SansSerif", Font.BOLD, 20));
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(27)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblAcceslvl)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblAddress)
										.addComponent(lblEmail)
										.addComponent(lblCpr))
									.addGap(18)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(txtAddress, GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
										.addGroup(groupLayout.createSequentialGroup()
											.addGap(56)
											.addComponent(comboAccessLvl, 0, 175, Short.MAX_VALUE))
										.addComponent(txtCPR, GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
										.addComponent(txtEmail, GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblFirstName)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtFName, GroupLayout.PREFERRED_SIZE, 221, GroupLayout.PREFERRED_SIZE)))
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(104)
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(lblLastName)
											.addPreferredGap(ComponentPlacement.UNRELATED))
										.addComponent(lblPhone))
									.addGap(50))
								.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED, 150, Short.MAX_VALUE)
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addComponent(lblCity)
										.addComponent(lblZip))
									.addGap(67)))
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(txtPhone)
								.addComponent(txtCity, GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
								.addComponent(txtLName, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
								.addComponent(txtZip, Alignment.TRAILING))))
					.addGap(121))
				.addComponent(pnlButtons, GroupLayout.DEFAULT_SIZE, 915, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(30)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblFirstName)
						.addComponent(txtFName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtLName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblLastName))
					.addGap(38)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCpr)
						.addComponent(txtCPR, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtCity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCity))
					.addGap(34)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAddress)
						.addComponent(txtZip, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtAddress, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblZip))
					.addPreferredGap(ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEmail)
						.addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtPhone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPhone))
					.addGap(28)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAcceslvl)
						.addComponent(comboAccessLvl, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(65)
					.addComponent(pnlButtons, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE))
		);
		GroupLayout gl_pnlButtons = new GroupLayout(pnlButtons);
		gl_pnlButtons.setHorizontalGroup(
			gl_pnlButtons.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlButtons.createSequentialGroup()
					.addGap(48)
					.addComponent(btnAddEmployee)
					.addGap(180)
					.addComponent(btnClear, GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
					.addGap(152)
					.addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE)
					.addGap(49))
		);
		gl_pnlButtons.setVerticalGroup(
			gl_pnlButtons.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_pnlButtons.createSequentialGroup()
					.addContainerGap(24, Short.MAX_VALUE)
					.addGroup(gl_pnlButtons.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAddEmployee)
						.addComponent(btnCancel)
						.addComponent(btnClear))
					.addGap(32))
		);
		pnlButtons.setLayout(gl_pnlButtons);
		getContentPane().setLayout(groupLayout);
	}

	private void createEvents(){
		
		txtZip.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e){
				char c = e.getKeyChar();
				if((!Character.isDigit(c)) && !(c==KeyEvent.VK_BACK_SPACE)){
					e.consume();
				}
			}
		});
		
		txtPhone.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e){
				char c = e.getKeyChar();
				if((!Character.isDigit(c)) && !(c==KeyEvent.VK_BACK_SPACE)){
					e.consume();
				}
			}
		});
		
		txtCPR.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e){
				char c = e.getKeyChar();
				
				if((txtCPR.getText().length() == 6) && !(c==KeyEvent.VK_BACK_SPACE)){
				txtCPR.setText(txtCPR.getText()+"-");	
				}
				if(!Character.isDigit(c)){
					e.consume();
				}
				if(txtCPR.getText().length() > 10){
					e.consume();
				}
				if(!(Character.isDigit(c)) || (c==KeyEvent.VK_BACK_SPACE) || (c==KeyEvent.VK_DELETE)){
					e.consume();
				}
			}
		});
		
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtFName.setText("");
				txtLName.setText("");
				txtAddress.setText("");
				txtCity.setText("");
				txtZip.setText("");
				txtCPR.setText("");
				txtEmail.setText("");
				txtPhone.setText("");
			}
		});
		
		btnAddEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(validateEmail(txtEmail.getText())){
				EmployeeCtr empCtr = new EmployeeCtr();
				try {
					empCtr.insertNew(txtFName.getText(), txtLName.getText(), txtAddress.getText(), txtCity.getText(), txtZip.getText(), txtPhone.getText(), txtEmail.getText(), txtCPR.getText(), (AccessLvl) comboAccessLvl.getSelectedItem());
				
				} catch (Exception e1) {
					System.out.println(e1);
					e1.printStackTrace();
				}
				
				new AddEmployeeConfirmationDialogPanel(txtCPR.getText());
				btnClear.doClick();				
			} else {
				JOptionPane.showMessageDialog(null, "Invalid email format \nPlease correct the email address");
			}
			}});
			}
	
	private void fillCombo() {
		
		Person.AccessLvl acc = null;		
		comboAccessLvl.setModel(new DefaultComboBoxModel<>(acc.values()));
		
	}
	
	private boolean validateEmail(String email){
		boolean valid = false;
		String emailPatern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		Pattern pattern = Pattern.compile(emailPatern);
		Matcher matcher = pattern.matcher(email);
		if(matcher.matches()){
			valid = true;
		} else {
			valid = false;
		}
		return valid;
	}
	
}
