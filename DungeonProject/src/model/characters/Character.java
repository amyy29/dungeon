package model.characters;

/**
 * Character is the abstract class that represents a Character into the Game.
 * 
 * @author Aylin G., Amelie M., Sofian C., Laurent T.
 * 
 */
public abstract class Character {
	protected int lifePoints;
	protected int attackPoints;
	protected int gold;
	
	public Character(int lifePoints, int attackPoints) {
		this.lifePoints = lifePoints;
		this.attackPoints = attackPoints;
		this.gold = 0;
	}
	
	public Character(int lifePoints, int attackPoints, int gold) {
		this.lifePoints = lifePoints;
		this.attackPoints = attackPoints;
		this.gold = gold;
	}

	public boolean isAlive(){
		return this.lifePoints > 0;
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
