package model.items;

/**
 * Arm is a subclass of an Item used to define an Arm in the game
 * 
 * @author Aylin G., Amelie M., Sofian C., Laurent T.
 * 
 */
public class Arm extends Item {

	protected ArmType type;
	protected int attackPoints;

	public Arm(ArmType type) {
		super(type.getName(), type.getDescription());
		this.attackPoints = type.getAttackPoints();
	}

	public int getAttackPoints() {
		return attackPoints;
	}

}
