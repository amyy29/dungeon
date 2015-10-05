package model.characters;

public enum MonsterType {
	SQUELETON("Squeleton", 50, 5, 40),
	ZOMBIE("Zombie", 40, 8, 50),
	DEMON("Demon", 60, 10, 60);
	
	private String type;
	private int lifePoints;
	private int attackPoints;
	private int gold;
	
	MonsterType(String type, int lifePoints, int attackPoints, int gold) {
		this.type = type;
		this.lifePoints = lifePoints;
		this.attackPoints = attackPoints;
		this.gold = gold;
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
	
	public int getGold() {
		return gold;
	}

}
