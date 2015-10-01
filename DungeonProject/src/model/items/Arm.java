package model.items;

public abstract class Arm extends Item {
	
	protected int attackPoints;

	public Arm(String name, String description, int attackPoints) {
		super(name, description);
	}
	
}
