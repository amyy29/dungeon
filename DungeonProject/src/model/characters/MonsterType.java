package model.characters;

public enum MonsterType {
	SQUELETON("Squeleton", 50, 5),
	ZOMBIE("Zombie", 40, 8),
	DEMON("Demon", 60, 10);
	
	private String type;
	private int lifePoints;
	private int attackPoints;
	
	MonsterType(String type, int lifePoints, int attackPoints) {
		this.type = type;
		this.lifePoints = lifePoints;
		this.attackPoints = attackPoints;
	}
	
	public String toString() {
		return type;
	}

	public String getType() {
		return type;
	}

	public int getLifePoints() {
		return lifePoints;
	}

	public int getAttackPoints() {
		return attackPoints;
	}

}
