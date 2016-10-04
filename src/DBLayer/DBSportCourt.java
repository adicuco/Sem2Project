
/**
 * 
 */
package DBLayer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ModelLayer.Active;
import ModelLayer.SportCourt;
import ModelLayer.Status;

/**
 * @author Eugen Bobolea, Dalibor Branny, Adrian Cucolas, Catalin Rat
 *
 */
public class DBSportCourt implements IFDBSportCourt {

	private Connection con;

	public DBSportCourt() {
		con = DBConnection.getInstance().getDBcon();
	}

	public ArrayList<SportCourt> getAllSportCourts() {
		return miscWhere("");

	}

	public ArrayList<SportCourt> getAllSportCourts(String wClause, String var) {
		return miscWhere(wClause + " ='" + var + "'");

	}

	public SportCourt findSportCourt(int courtId) {
		String wClause = " courtId = '" + courtId + "'";
		return singleWhere(wClause);
	}

	public int insertSportCourt(SportCourt sc) throws Exception {
		int nextID = GetMax.getMaxId("Select max(courtId) from SportCourt");
		nextID++;

		int re = -1;
		String query = "INSERT INTO SportCourt(courtId, eqTypeId, sport, price, active, status) VALUES('" + nextID
				+ "','" + sc.getEqType().getEqTypeId() + "','" + sc.getSport() + "','" + sc.getPrice() + "','"
				+ sc.getActive().toString() + "','" + sc.getStatus().toString() + "')";
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			re = stmt.executeUpdate(query);
			stmt.close();
		} catch (SQLException e) {
			System.out.println(e);
			System.out.println("SportCourt not inserted.");
			throw new Exception("SportCourt is not inserted correct");
		}
		return re;
	}

	public int updateSportCourt(SportCourt sportcourt) {
		SportCourt sc = sportcourt;
		int rc = -1;

		String query = "UPDATE SportCourt SET " + "price ='" + sc.getPrice() + "', " + "active ='"
				+ sc.getActive().toString() + "' WHERE courtId = " + sc.getCourtId();

		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);

			stmt.close();
		} catch (Exception e) {
			System.out.println("Update exception in SportCourt db: " + e);
		}
		return (rc);
	}

	private String buildQuery(String wClause) {
		String query = "SELECT courtId, eqTypeId, sport, price, active, status FROM SportCourt";

		if (wClause.length() > 0)
			query = query + " WHERE " + wClause;

		return query;
	}

	private ArrayList<SportCourt> miscWhere(String wClause) {

		ResultSet results;
		ArrayList<SportCourt> list = new ArrayList<SportCourt>();
		String query = buildQuery(wClause);

		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);

			while (results.next()) {
				SportCourt sc = new SportCourt();
				sc = buildSportCourt(results);
				list.add(sc);
			}
			stmt.close();
		} catch (Exception e) {
			System.out.println("Query exception - select: " + e);
		}
		return list;
	}

	private SportCourt singleWhere(String wClause) {
		ResultSet results;
		SportCourt sc = new SportCourt();
		String query = buildQuery(wClause);

		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);

			if (results.next()) {
				sc = buildSportCourt(results);
				stmt.close();
			} else {
				sc = null;
			}
		} catch (Exception e) {
			System.out.println("Query exception: " + e);
		}
		return sc;
	}

	private SportCourt buildSportCourt(ResultSet results) {
		SportCourt sc = new SportCourt();
		try {
			sc.setCourtId(results.getInt("courtId"));
			IFDBEquipmentType dbEqType = new DBEquipmentType();
			sc.setEqType(dbEqType.findEquipmentType(results.getInt("eqTypeId")));
			sc.setSport(results.getString("sport"));
			sc.setPrice(results.getDouble("price"));
			sc.setStatus(Status.valueOf(results.getString("status")));
			sc.setActive(Active.valueOf(results.getString("active")));

		} catch (Exception e) {
			System.out.println("Error in building the SportCourt object");
		}
		return sc;
	}

	public List<String> getCourtTypes() {
		ResultSet results;
		List<String> list = new ArrayList<String>();
		String query = "SELECT DISTINCT sport FROM SportCourt";

		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);

			while (results.next()) {
				int i = 1;
				list.add(results.getString(i++));
			}
			stmt.close();
		} catch (Exception e) {
			System.out.println("Query exception - select: " + e);
		}
		return list;
	}

}
