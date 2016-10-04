package ModelLayer;

/**
 * 
 * @author Eugen Bobolea, Dalibor Branny, Adrian Cucolas, Catalin Rat
 *
 */

public class EquipmentType {

	private int eqTypeId;
	private String name;
	
	public EquipmentType(){
		
	}
	
	public EquipmentType(String name) {
		this.name = name;
	}

	public int getEqTypeId() {
		return eqTypeId;
	}

	public void setEqTypeId(int eqTypeId) {
		this.eqTypeId = eqTypeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String toString(){
		return name;
	}
	
}
