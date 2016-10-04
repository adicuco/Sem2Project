package GUILayer.Customer.menuPanels.dialogPanels.rentTypePanels;

import java.awt.Dimension;

import javax.swing.JPanel;

import ModelLayer.Room;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JCheckBox;
import javax.swing.ScrollPaneConstants;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

@SuppressWarnings("serial")
public class RoomTypePanel extends JPanel {

	private Room room;
	private final JLabel lblRoomNumber = new JLabel("Room Number");
	private final JLabel lblRoomType = new JLabel("Room Type");
	private final JLabel lblRoomPrice = new JLabel("Price");
	private final JLabel lblExtraBed = new JLabel("Extra bed");
	private final JLabel lblDescription = new JLabel("Description");
	private final JTextField txtNumber = new JTextField();
	private final JTextField txtType = new JTextField();
	private final JTextField txtPrice = new JTextField();
	private final JScrollPane scrollPane = new JScrollPane();
	private final JTextArea txtNotes = new JTextArea();
	private final JCheckBox cExtra = new JCheckBox("");

	/**
	 * Create the panel.
	 */
	public RoomTypePanel(Room room) {
		this.room = room;
		initialize();
		initializeFields();
	}

	private void initialize() {
		setSize(new Dimension(303, 318));
		lblRoomNumber.setFont(new Font("SansSerif", Font.BOLD, 25));
		lblRoomType.setFont(new Font("SansSerif", Font.BOLD, 25));
		lblRoomPrice.setFont(new Font("SansSerif", Font.BOLD, 25));
		lblExtraBed.setFont(new Font("SansSerif", Font.BOLD, 25));
		lblDescription.setFont(new Font("SansSerif", Font.BOLD, 25));
		txtType.setFont(new Font("SansSerif", Font.BOLD, 25));
		txtType.setEnabled(false);
		txtType.setEditable(false);
		txtType.setColumns(10);
		txtPrice.setFont(new Font("SansSerif", Font.BOLD, 25));
		txtPrice.setEnabled(false);
		txtPrice.setEditable(false);
		txtPrice.setColumns(10);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		txtNotes.setWrapStyleWord(true);
		txtNotes.setLineWrap(true);
		txtNotes.setFont(new Font("SansSerif", Font.BOLD, 25));
		txtNotes.setEnabled(false);
		txtNotes.setEditable(false);

		scrollPane.setViewportView(txtNotes);

		txtNumber.setFont(new Font("SansSerif", Font.BOLD, 25));
		txtNumber.setEnabled(false);
		txtNumber.setEditable(false);
		txtNumber.setColumns(10);
		cExtra.setFont(new Font("SansSerif", Font.PLAIN, 25));
		cExtra.setEnabled(false);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(6)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(scrollPane)
							.addContainerGap())
						.addGroup(Alignment.TRAILING, groupLayout.createParallelGroup(Alignment.LEADING)
							.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
								.addComponent(lblRoomNumber, GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
								.addGap(41)
								.addComponent(txtNumber, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
								.addGap(6))
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(lblRoomType, GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
								.addGap(6)
								.addComponent(txtType, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)
								.addGap(6))
							.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
								.addComponent(lblRoomPrice, GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
								.addGap(60)
								.addComponent(txtPrice, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
								.addGap(6))
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(lblExtraBed, GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
								.addGap(93)
								.addComponent(cExtra, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
								.addGap(23))
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(lblDescription, GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
								.addGap(152)))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(19)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblRoomNumber, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtNumber, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblRoomType, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
							.addGap(9))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(txtType, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
							.addGap(6)))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(3)
							.addComponent(lblRoomPrice, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
						.addComponent(txtPrice, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
					.addGap(13)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblExtraBed, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
						.addComponent(cExtra, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
					.addGap(12)
					.addComponent(lblDescription, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
					.addContainerGap())
		);
		setLayout(groupLayout);
	}

	private void initializeFields() {
		txtNumber.setText(String.valueOf(room.getRoomId()));
		txtType.setText(room.getRoomType().getRoomTypeName());
		txtPrice.setText(String.valueOf(room.getRoomPrice()));
		txtNotes.setText(room.getNotes());
		if (room.isExtraBed()) {
			cExtra.setSelected(true);
		}
	}
}
