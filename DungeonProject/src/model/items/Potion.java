package model.items;

public class Potion extends Item {
	
	protected int moreLifePoints;

	public Potion(int moreLifePoints) {
		super("Potion", "This potion can give you " + moreLifePoints + " life points.");
	}
	
}
