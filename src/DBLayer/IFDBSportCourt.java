package DBLayer;

import java.util.ArrayList;
import java.util.List;

import ModelLayer.SportCourt;

/**
 * @author Eugen Bobolea, Dalibor Branny, Adrian Cucolas, Catalin Rat
 *
 */
public interface IFDBSportCourt {
	
	public ArrayList<SportCourt> getAllSportCourts();
	
	public ArrayList<SportCourt> getAllSportCourts(String wClause, String var);

	public SportCourt findSportCourt(int courtId);

	public int insertSportCourt(SportCourt sc) throws Exception;

	public int updateSportCourt(SportCourt sc);
	
	public List<String> getCourtTypes();

}
