package model.items;


public class Arm extends Item {

	protected int attackPoints;
	protected int utilisation;

	public Arm(String name, String description, int attackPoints, int utilisation) {
		super(name, description);
		this.attackPoints = attackPoints;
		this.utilisation = utilisation;
	}

	public int getUtilisation() {
		return utilisation;
	}

	public void setUtilisation() {
		this.utilisation = this.utilisation - 1;
	}

	public int getAttackPoints() {
		return attackPoints;
	}

}
