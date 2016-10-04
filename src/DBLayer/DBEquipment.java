package DBLayer;

/**
 * 
 * @author Eugen Bobolea, Dalibor Branny, Adrian Cucolas, Catalin Rat
 *
 */

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ModelLayer.Active;
import ModelLayer.Equipment;
import ModelLayer.EquipmentType;

public class DBEquipment implements IFDBEquipment {

	private Connection con;

	public DBEquipment() {
		con = DBConnection.getInstance().getDBcon();
	}

	public ArrayList<Equipment> getAllEquipment() {
		return miscWhere("");
	}

	public ArrayList<Equipment> getAllEquipment(String name) {
		return miscWhere("name = '" + name + "'");
	}

	public ArrayList<Equipment> getAllEquipment(String wClause, String var) {
		return miscWhere(wClause + " ='" + var + "'");
	}

	public Equipment findEquipment(int eqId) {
		String wClause = " eqId = " + eqId;
		return singleWhere(wClause);
	}

	public Equipment findEquipment(String name) {
		String wClause = "name = '" + name + "'";
		return singleWhere(wClause);
	}

	public int insertEquipment(Equipment eq) throws Exception {
		int nextID = GetMax.getMaxId("Select max(eqId) from Equipment");
		nextID++;

		int rc = -1;
		String query = "INSERT INTO Equipment(eqId, eqTypeId, name, description, quantity, price, active) VALUES("
				+ nextID + "," + eq.getEqType().getEqTypeId() + ", '" + eq.getName() + "','" + eq.getDescription()
				+ "', " + eq.getQuantity() + ", " + eq.getPrice() + ", '" + eq.getActive().toString() + "')";
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);
			stmt.close();
		} catch (SQLException e) {
			rc = -2;
			System.out.println(e);
			System.out.println("Equipment not inserted.");
			throw new Exception("Equipment is not inserted correct");
		}
		return rc;
	}

	public int updateEquipment(Equipment eq) {
		int rc = -1;

		String query = "UPDATE Equipment SET " + "eqTypeId = '" + eq.getEqType().getEqTypeId() + "', " + "name = '"
				+ eq.getName() + "', " + "description = '" + eq.getDescription() + "', " + "price = " + eq.getPrice()
				+ ", active = '" + eq.getActive().toString() + "' " + " WHERE eqId = " + eq.getEqId();

		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);

			stmt.close();
		} catch (Exception e) {
			System.out.println("Update exception in Equipment db: " + e);
			rc = -2;
		}
		return (rc);
	}

	private String buildQuery(String wClause) {
		String query = "SELECT eqId, eqTypeId, name, description, quantity, price, active  FROM Equipment";
		if (wClause.length() > 0)
			query = query + " WHERE " + wClause;
		return query;
	}

	private ArrayList<Equipment> miscWhere(String wClause) {
		ResultSet results;
		ArrayList<Equipment> list = new ArrayList<Equipment>();
		String query = buildQuery(wClause);

		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);

			while (results.next()) {
				Equipment eq = new Equipment();
				eq = buildEquipment(results);
				list.add(eq);
			}
			stmt.close();
		} catch (Exception e) {
			System.out.println("Query exception - select: " + e);
		}
		return list;
	}

	private Equipment singleWhere(String wClause) {
		ResultSet results;
		Equipment eq = new Equipment();
		String query = buildQuery(wClause);

		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);

			if (results.next()) {
				eq = buildEquipment(results);
				stmt.close();
			} else {
				eq = null;
			}
		} catch (Exception e) {
			System.out.println("Query exception: " + e);
		}
		return eq;
	}

	private Equipment buildEquipment(ResultSet results) {
		Equipment eq = new Equipment();
		try {
			eq.setEqId(results.getInt("eqId"));
			eq.setName(results.getString("name"));
			eq.setDescription(results.getString("description"));
			eq.setQuantity(results.getInt("quantity"));
			eq.setPrice(results.getDouble("price"));
			eq.setActive(Active.valueOf(results.getString("active")));
			IFDBEquipmentType dbEt = new DBEquipmentType();
			EquipmentType eT = dbEt.findEquipmentType(results.getInt("eqTypeId"));
			eq.setEqType(eT);

		} catch (Exception e) {
			System.out.println("Error in building the Equipment object");
		}
		return eq;
	}

}
