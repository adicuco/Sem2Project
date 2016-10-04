package DBLayer;

/**
 * 
 * @author Eugen Bobolea, Dalibor Branny, Adrian Cucolas, Catalin Rat
 *
 */

import java.sql.Connection;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ModelLayer.EquipmentType;

public class DBEquipmentType implements IFDBEquipmentType{

    private Connection con;
	
	public DBEquipmentType (){
		con  = DBConnection.getInstance().getDBcon();
	}
	
	
	public ArrayList<EquipmentType> getAllEquipmentType() {
		return miscWhere("");
	}

	
	public EquipmentType findEquipmentType(String name) {
		String wClause = "name ='" + name + "'";
		return singleWhere(wClause);
	}

	
	public EquipmentType findEquipmentType(int id) {
		String wClause = "eqTypeId ='" + id + "'";
		return singleWhere(wClause);
	}

	
	public int insertEquipmentType(EquipmentType eqT) throws Exception {
		int nextID = GetMax.getMaxId("Select max(eqTypeId) from EquipmentType");
		nextID++;
		int rc = -1;
		String query = "INSERT INTO EquipmentType(eqTypeId, name) VALUES('" + nextID + "','"
				+ eqT.getName() + " ')";

		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);
			stmt.close();
		} catch (SQLException e) {
			rc = -2;
			System.out.println(e);
			System.out.println("EquipmentType not inserted.");
			throw new Exception("EquipmentType is not inserted correct");
		}
		return rc;
	}
	

	
	public int updateEquipmentType(EquipmentType eqT) {
		EquipmentType eT = eqT;
		int rc = -1;

		String query = "UPDATE EquipmentType SET " + "name ='" + eT.getName() + "'" + " WHERE eqTypeId = "
				+ eT.getEqTypeId();

		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);

			stmt.close();
		} catch (Exception e) {
			System.out.println("Update exception in EquipmentType db: " + e);
			rc = -2;
		}
		return (rc);
	}

	private String buildQuery(String wClause) {
		String query = "SELECT eqTypeId, name FROM EquipmentType";

		if (wClause.length() > 0)
			query = query + " WHERE " + wClause;

		return query;
	}
	
	private ArrayList<EquipmentType> miscWhere(String wClause) {
		ResultSet results;
		ArrayList<EquipmentType> list = new ArrayList<EquipmentType>();
		String query = buildQuery(wClause);

		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);

			while (results.next()) {
				EquipmentType eT = new EquipmentType();
				eT = buildEquipmentType(results);
				list.add(eT);
			}
			stmt.close();
		} catch (Exception e) {
			System.out.println("Query exception - select: " + e);
		}
		return list;
	}
	
	private EquipmentType singleWhere(String wClause) {
		ResultSet results;
		EquipmentType eT = new EquipmentType();
		String query = buildQuery(wClause);

		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);

			if (results.next()) {
				eT = buildEquipmentType(results);
				stmt.close();
			} else {
				eT = null;
			}
		} catch (Exception e) {
			System.out.println("Query exception: " + e);
		}
		return eT;
	}
	
	private EquipmentType buildEquipmentType(ResultSet results) {
		EquipmentType eT = new EquipmentType();
		try {
			eT.setName(results.getString("name"));
			eT.setEqTypeId(results.getInt("eqTypeId"));
		} catch (Exception e) {
			System.out.println("Error in building the EquipmentType object");
		}
		return eT;
	}
	
}
