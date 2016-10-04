package DBLayer;

import java.util.ArrayList;

import ModelLayer.Membership;

public interface IFDBMembership {
	
	public ArrayList<Membership> getAllMemberships();

	public Membership findMembership(String name);

	public int insertMembership(Membership ms) throws Exception;

	public int updateMembership(Membership ms);

}
