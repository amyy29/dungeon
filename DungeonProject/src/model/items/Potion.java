package model.items;

/**
 * Potion is a subclass of an Item used to get more life points
 * 
 * @author Aylin G., Amelie M., Sofian C., Laurent T.
 * 
 */
public class Potion extends Item {
	
	protected int moreLifePoints;

	public Potion(int moreLifePoints) {
		super("Potion", "This potion can give you " + moreLifePoints + " life points.");
		this.moreLifePoints = moreLifePoints;
	}

	public int getMoreLifePoints() {
		return moreLifePoints;
	}
	
	
	
}
