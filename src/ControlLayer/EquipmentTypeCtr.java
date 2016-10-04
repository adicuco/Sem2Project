package ControlLayer;

/**
 * 
 * @author Eugen Bobolea, Dalibor Branny, Adrian Cucolas, Catalin Rat
 *
 */

import java.util.ArrayList;

import DBLayer.DBConnection;
import DBLayer.DBEquipmentType;
import DBLayer.IFDBEquipmentType;
import ModelLayer.EquipmentType;

public class EquipmentTypeCtr {

	public EquipmentTypeCtr (){
		
	}
	
	public ArrayList<EquipmentType> findAllEquipmentTypes(){
		IFDBEquipmentType dbEt = new DBEquipmentType();
		ArrayList<EquipmentType> allEt = new ArrayList<EquipmentType>();
		allEt = dbEt.getAllEquipmentType();
		return allEt;
	}
	
	
	public EquipmentType findEquipmentType(String name) {
		IFDBEquipmentType dbEt = new DBEquipmentType();
		return dbEt.findEquipmentType(name);
	}
	
	
	public EquipmentType findEquipmentType(int eqTypeId){
		IFDBEquipmentType dbEt = new DBEquipmentType();
		return dbEt.findEquipmentType(eqTypeId);
	}
	
	
	public int updateEquipmentType(int eqTypeId, String name){
		IFDBEquipmentType dbEt = new DBEquipmentType();
		EquipmentType eT = new EquipmentType();
		eT.setEqTypeId(eqTypeId);
		eT.setName(name);
		return dbEt.updateEquipmentType(eT);
	}
	
	
	public void insertNew(String name) throws Exception {
		EquipmentType eqT = new EquipmentType( name);
		try {
			IFDBEquipmentType dbEt = new DBEquipmentType();
			DBConnection.startTransaction();
			dbEt.insertEquipmentType(eqT);
			DBConnection.commitTransaction();
		} catch (Exception e) {
			DBConnection.rollbackTransaction();
			throw new Exception ("EquipmentType not inserted");
		}
	}
	
}
