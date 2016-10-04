package DBLayer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import ModelLayer.Membership;

public class DBMembership implements IFDBMembership {

	private Connection con;

	public DBMembership() {
		con = DBConnection.getInstance().getDBcon();
	}

	public ArrayList<Membership> getAllMemberships() {
		return miscWhere("");
	}

	public Membership findMembership(String name) {
		String wClause = "name ='" + name + "'";
		return singleWhere(wClause);
	}

	public int insertMembership(Membership ms) throws Exception {
		int rc = -1;
		String query = "INSERT INTO Membership(name, discount, price) VALUES ('" + ms.getName() + "', "
				+ ms.getDiscount() + ", " + ms.getPrice() + ")";
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);
			stmt.close();
		} catch (SQLException e) {
			System.out.println(e);
			System.out.println("Membership not inserted.");
			throw new Exception("Membership is not inserted correct");
		}
		return rc;
	}

	public int updateMembership(Membership ms) {
		int rc = -1;

		String query = "UPDATE Membership SET " + "discount =" + ms.getDiscount()
				+ ", price =" + ms.getPrice() + " WHERE name ='" + ms.getName() + "'";
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);

			stmt.close();
		} catch (Exception e) {
			System.out.println("Update exception in Membership db: " + e);
		}
		return (rc);
	}

	private String buildQuery(String wClause) {
		String query = "SELECT name, discount, price FROM Membership";

		if (wClause.length() > 0)
			query = query + " WHERE " + wClause;

		return query;
	}

	private Membership singleWhere(String wClause) {
		ResultSet results;
		Membership ms = new Membership();
		String query = buildQuery(wClause);

		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);

			if (results.next()) {
				ms = buildMembership(results);
				stmt.close();
			} else {
				ms = null;
			}
		} catch (Exception e) {
			System.out.println("Query exception: " + e);
		}
		return ms;
	}

	private ArrayList<Membership> miscWhere(String wClause) {
		ResultSet results;
		ArrayList<Membership> list = new ArrayList<Membership>();
		String query = buildQuery(wClause);

		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);

			while (results.next()) {
				Membership ms = new Membership();
				ms = buildMembership(results);
				list.add(ms);
			}
			stmt.close();
		} catch (Exception e) {
			System.out.println("Query exception - select: " + e);
		}
		return list;
	}

	private Membership buildMembership(ResultSet results) {
		Membership ms = new Membership();
		try {
			ms.setName(results.getString("name"));
			ms.setDiscount(results.getInt("discount"));
			ms.setPrice(results.getDouble("price"));

		} catch (Exception e) {
			System.out.println("Error in building the Membership object");
		}
		return ms;
	}
}
