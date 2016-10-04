package DBLayer;

/**
 * 
 * @author Eugen Bobolea, Dalibor Branny, Adrian Cucolas, Catalin Rat
 *
 */

import java.util.ArrayList;

import ModelLayer.EquipmentType;

public interface IFDBEquipmentType {

	public ArrayList <EquipmentType> getAllEquipmentType();
	
	public EquipmentType findEquipmentType(String equipmentTypeName);
	
	public EquipmentType findEquipmentType(int equipmentTypeId);
	
	public int insertEquipmentType(EquipmentType eqT) throws Exception;
	
	public int updateEquipmentType(EquipmentType eqT);
	
}
