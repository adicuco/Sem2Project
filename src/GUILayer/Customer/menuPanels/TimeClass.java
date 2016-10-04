package GUILayer.Customer.menuPanels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.swing.JLabel;
import javax.swing.Timer;

public class TimeClass {

	private static int delay = 1000; // milliseconds
	private static String date = new java.text.SimpleDateFormat("EEEEE, d MMMMM yyyy HH:mm", Locale.ENGLISH)
			.format(new java.util.Date());

	public static void getTime(JLabel lblDate) {
		ActionListener taskPerformer = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				lblDate.setText(date);
			}
		};
		new Timer(delay, taskPerformer).start();
	}

	public static String getDate() {
		return date;
	}

	public static String comboBoxDate(Timestamp timestamp) {
		String date = new java.text.SimpleDateFormat("d MMMMM yyyy", Locale.ENGLISH).format(timestamp);
		return date;
	}

	public static String smallDate(Timestamp timestamp) {
		String date = new java.text.SimpleDateFormat("d MMMMM", Locale.ENGLISH).format(timestamp);
		return date;
	}

	public static String comboBoxPeriod(Timestamp timestamp) {
		String date = new java.text.SimpleDateFormat("HH:mm", Locale.ENGLISH).format(timestamp);
		return date;
	}

	public static String txtDate(Timestamp timestamp) {
		String date = new java.text.SimpleDateFormat("d MMMMM yyyy HH:mm", Locale.ENGLISH).format(timestamp);
		return date;
	}

	public static Timestamp toTimestamp(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Timestamp ts = Timestamp.valueOf(sdf.format(date) + " 00:00:00");
		return ts;
	}
}
