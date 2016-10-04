package DBLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 
 * @author Eugen Bobolea, Dalibor Branny, Adrian Cucolas, Catalin Rat
 *
 */

public class DBConnection {

	private static final String driver = "jdbc:sqlserver://kraka.ucn.dk";
	private static final String databaseName = ";databaseName=dmai0915_2Sem_1";
	private static String userName = ";user=dmaI0915_2Sem_1";
	private static String password = ";password=IsAllowed";

	private static Connection con;
	private static DBConnection instance = null;

	private DBConnection() {
		String url = driver + databaseName + userName + password;
		try {
			con = DriverManager.getConnection(url);
			con.setAutoCommit(true);
			con.getMetaData();
			System.out.println("Connected!");
		} catch (SQLException e) {
			System.out.println("Problems with the connection to the database");
			System.out.println(e.getMessage());
			System.out.println(url);
		}
	}

	public Connection getDBcon() {
		return con;
	}

	public static void closeConnection() {
		try {
			con.close();
			System.out.println("The connection is closed.");
		} catch (SQLException e) {
			System.out.println("Error trying to close the database " + e.getMessage());
		}
	}

	public static DBConnection getInstance() {
		if (instance == null) {
			instance = new DBConnection();
		}
		return instance;
	}

	public static void startTransaction() {
		try {
			System.out.println("con: " + con);
			con.setAutoCommit(false);

		} catch (SQLException e) {
			System.out.println("Failed to start transaction.");
			System.out.println(e.getMessage());
		}
	}

	public static void commitTransaction() {
		try {
			con.setAutoCommit(true);
		} catch (SQLException e) {
			System.out.println("Failed to commit transaction");
			System.out.println(e.getMessage());
		}
	}

	public static void rollbackTransaction() {
		try {
			con.rollback();
			con.setAutoCommit(true);
		} catch (SQLException e) {
			System.out.println("Failed to rollback transaction");
			System.out.println(e.getMessage());
		}
	}
}
