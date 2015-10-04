package model.characters;

public abstract class Character {
	protected String name;
	protected int lifePoints;
	protected int attackPoints;
	
	public Character(String name, int lifePoints, int attackPoints) {
		super();
		this.name = name;
		this.lifePoints = lifePoints;
		this.attackPoints = attackPoints;
	}

	public boolean isAlive(){
		return this.lifePoints > 0;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLifePoints() {
		return lifePoints;
	}

	public void setLifePoints(int lifePoints) {
		this.lifePoints = lifePoints;
	}

	public int getAttackPoints() {
		return attackPoints;
	}

	public void setAttackPoints(int attackPoints) {
		this.attackPoints = attackPoints;
	}
	
	
}
