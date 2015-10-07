package model.items;

/**
 * ArmType is the enum to list all arms available in this game
 * 
 * @author Aylin G., Amelie M., Sofian C., Laurent T.
 * 
 */
public enum ArmType {
	GUN("Gun", "Little arm attacks with 10 points per hit", 10),
	LASER("Laser", "Destroy with 20 points per hit", 20),
	BOMB("Bomb", "Destroy all with 50 points per hit", 50);
	
	private String name;
	private String description;
	private int attackPoints;
	
	ArmType(String name, String description, int attackPoints) {
		this.name = name;
		this.description = description;
		this.attackPoints = attackPoints;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getAttackPoints() {
		return attackPoints;
	}

	public void setAttackPoints(int attackPoints) {
		this.attackPoints = attackPoints;
	}
	
	
}
