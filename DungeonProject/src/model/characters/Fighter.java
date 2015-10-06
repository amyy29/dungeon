package model.characters;

/**
 * Fighter is the interface to represent a Character who is able to fight
 * 
 * @author Aylin G., Amelie M., Sofian C., Laurent T.
 * 
 */
public interface Fighter {
	/**
	 * attack is the function to describe the attack's behavior of a Fighter
	 * 
	 * @param c the Character to attack
	 */
	public void attack(Character c);
}
