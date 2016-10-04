package DBLayer;
import java.util.ArrayList;

import ModelLayer.Equipment;


/**
 * 
 * @author Eugen Bobolea, Dalibor Branny, Adrian Cucolas, Catalin Rat
 *
 */

public interface IFDBEquipment {
	
	public ArrayList<Equipment> getAllEquipment();
	
	public ArrayList<Equipment> getAllEquipment(String name);
	
	public ArrayList<Equipment> getAllEquipment(String wClause, String var);
	
	public Equipment findEquipment (int eqId);
	
	public Equipment findEquipment (String name);
	
	public int insertEquipment(Equipment eq) throws Exception;
	
	public int updateEquipment(Equipment eq);
}
