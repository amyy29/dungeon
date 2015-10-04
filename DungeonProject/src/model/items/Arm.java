package model.items;


public class Arm extends Item {

	protected int attackPoints;

	public Arm(String name, String description, int attackPoints) {
		super(name, description);
		this.attackPoints = attackPoints;
	}

	public int getAttackPoints() {
		return attackPoints;
	}

}
