package GUILayer.Employee.menuPanels.dialogPanels.createEventProcess;

import java.awt.Font;
import java.sql.Timestamp;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import GUILayer.Customer.menuPanels.TimeClass;

@SuppressWarnings("serial")
public class CreateEventInformationPanel extends JPanel {

	private static Timestamp startDate;
	private static Timestamp endDate;

	private final JLabel label = new JLabel("Registration Start Date");
	private final JLabel label_1 = new JLabel("Registration End Date");
	private final JLabel lblEndDate = new JLabel("End Date");
	private final JLabel lblStartDate = new JLabel("Start Date");
	private final JLabel label_4 = new JLabel("Name");
	private final static JTextField txtName = new JTextField();
	private final JLabel lblEmployeeId = new JLabel("Employee ID");
	private final static JTextField txtEmp = new JTextField();
	private final JLabel label_5 = new JLabel("Min. Participants");
	private final static JTextField txtMin = new JTextField();
	private final JLabel label_6 = new JLabel("Max. Participants");
	private final static JTextField txtMax = new JTextField();
	private final JLabel label_7 = new JLabel("Price");
	private final static JTextField txtPrice = new JTextField();
	private final JLabel label_8 = new JLabel("Description");
	private final JScrollPane scrollPane = new JScrollPane();
	private final static JTextArea txtDesc = new JTextArea();
	private final static JDateChooser dcRegStart = new JDateChooser();
	private final static JDateChooser dcRegEnd = new JDateChooser();
	private final static JDateChooser dcStart = new JDateChooser();
	private final static JDateChooser dcEnd = new JDateChooser();

	/**
	 * Create the panel.
	 */
	public CreateEventInformationPanel() {
		initialize();
	}

	private void initialize() {
		setVisible(true);
		setBounds(100, 100, 856, 591);
		label.setFont(new Font("SansSerif", Font.BOLD, 20));
		label_1.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblEndDate.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblStartDate.setFont(new Font("SansSerif", Font.BOLD, 20));
		label_4.setFont(new Font("SansSerif", Font.BOLD, 20));
		txtName.setText((String) null);
		txtName.setFont(new Font("SansSerif", Font.BOLD, 15));
		txtName.setColumns(10);
		lblEmployeeId.setFont(new Font("SansSerif", Font.BOLD, 20));
		txtEmp.setText((String) null);
		txtEmp.setFont(new Font("SansSerif", Font.BOLD, 15));
		txtEmp.setColumns(10);
		label_5.setFont(new Font("SansSerif", Font.BOLD, 20));
		txtMin.setText((String) null);
		txtMin.setFont(new Font("SansSerif", Font.BOLD, 15));
		txtMin.setColumns(10);
		label_6.setFont(new Font("SansSerif", Font.BOLD, 20));
		txtMax.setText((String) null);
		txtMax.setFont(new Font("SansSerif", Font.BOLD, 15));
		txtMax.setColumns(10);
		label_7.setFont(new Font("SansSerif", Font.BOLD, 20));
		txtPrice.setText((String) null);
		txtPrice.setFont(new Font("SansSerif", Font.BOLD, 15));
		txtPrice.setColumns(10);
		label_8.setFont(new Font("SansSerif", Font.BOLD, 20));
		txtDesc.setWrapStyleWord(true);
		txtDesc.setText((String) null);
		txtDesc.setLineWrap(true);
		txtDesc.setFont(new Font("SansSerif", Font.BOLD, 15));

		scrollPane.setViewportView(txtDesc);
		dcRegStart.setFont(new Font("SansSerif", Font.BOLD, 15));
		dcRegEnd.setFont(new Font("SansSerif", Font.BOLD, 15));
		dcStart.setFont(new Font("SansSerif", Font.BOLD, 15));
		dcEnd.setFont(new Font("SansSerif", Font.BOLD, 15));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout
				.setHorizontalGroup(groupLayout
						.createParallelGroup(
								Alignment.LEADING)
						.addGroup(
								groupLayout.createSequentialGroup().addGap(22)
										.addComponent(label_4, GroupLayout.PREFERRED_SIZE, 60,
												GroupLayout.PREFERRED_SIZE)
										.addGap(12)
										.addComponent(txtName, GroupLayout.PREFERRED_SIZE, 328,
												GroupLayout.PREFERRED_SIZE)
										.addGap(163).addComponent(lblEmployeeId).addGap(32).addComponent(txtEmp,
												GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup().addGap(106)
						.addComponent(label, GroupLayout.PREFERRED_SIZE, 227, GroupLayout.PREFERRED_SIZE).addGap(277)
						.addComponent(lblStartDate, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup().addGap(85)
						.addComponent(dcRegStart, GroupLayout.PREFERRED_SIZE, 248, GroupLayout.PREFERRED_SIZE)
						.addGap(199).addComponent(dcStart, GroupLayout.PREFERRED_SIZE, 249, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup().addGap(106)
						.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 210, GroupLayout.PREFERRED_SIZE).addGap(294)
						.addComponent(lblEndDate, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup().addGap(86)
						.addComponent(dcRegEnd, GroupLayout.PREFERRED_SIZE, 247, GroupLayout.PREFERRED_SIZE).addGap(199)
						.addComponent(dcEnd, GroupLayout.PREFERRED_SIZE, 248, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup().addGap(7)
										.addComponent(label_8, GroupLayout.PREFERRED_SIZE, 116,
												GroupLayout.PREFERRED_SIZE)
										.addGap(409))
						.addGroup(groupLayout.createSequentialGroup().addGap(6).addComponent(scrollPane)))
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup().addComponent(label_5).addGap(32))
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup().addGap(131).addComponent(label_7,
												GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE))
										.addComponent(label_6, GroupLayout.PREFERRED_SIZE, 169,
												GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(txtMax, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtMin, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtPrice, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))
						.addGap(55)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addGap(40)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup().addGap(1).addComponent(label_4))
								.addComponent(txtName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup().addGap(1).addComponent(lblEmployeeId))
						.addComponent(txtEmp, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addGap(39)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(label)
						.addComponent(lblStartDate))
				.addGap(12)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(dcRegStart, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(dcStart, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addGap(30)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(label_1)
						.addComponent(lblEndDate))
				.addGap(12)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(dcRegEnd, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(dcEnd, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addGap(87)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup().addGap(1).addComponent(label_8))
						.addGroup(groupLayout.createSequentialGroup().addGap(1).addComponent(label_5))
						.addComponent(txtMin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addGap(7)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup().addGap(6).addComponent(label_6).addGap(91)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(label_7)
										.addComponent(txtPrice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup().addGap(11).addComponent(txtMax,
								GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))));
		setLayout(groupLayout);
	}

	public static Timestamp getStartDate() {
		startDate = TimeClass.toTimestamp(dcStart.getDate());
		return startDate;
	}

	public static Timestamp getEndDate() {
		endDate = TimeClass.toTimestamp(dcEnd.getDate());
		return endDate;
	}

	public static Timestamp getRegStartDate() {
		return TimeClass.toTimestamp(dcRegStart.getDate());
	}

	public static Timestamp getRegEndDate() {
		return TimeClass.toTimestamp(dcRegEnd.getDate());
	}

	public static String geName() {
		return txtName.getText();
	}

	public static int getEmp() {
		return Integer.valueOf(txtEmp.getText());
	}

	public static int getMin() {
		return Integer.valueOf(txtMin.getText());
	}

	public static int getMax() {
		return Integer.valueOf(txtMax.getText());
	}

	public static double getPriceCalculated() {
		double price = Double.valueOf(txtPrice.getText());
		price = price / getMin();
		return price;
	}

	public static double getPrice() {
		return Double.valueOf(txtPrice.getText());
	}

	public static String getDesc() {
		return txtDesc.getText();
	}
}
