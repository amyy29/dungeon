package model.items;


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
