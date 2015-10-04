package model.characters;

public class Monster {
	protected String name;
	protected int lifePoints;
	protected int attackPoints;

	public Monster(String name, int lifePoints, int attackPoints) {
		this.name = name;
		this.lifePoints = lifePoints;
		this.attackPoints = attackPoints;
	}

	public int getLifePoints() {
		return lifePoints;
	}

	public void setLifePoints(int lifePoints) {
		this.lifePoints = lifePoints;
	}

	public void attack(Player p){
		System.out.println("------------------\n");
		System.out.println("The monster " + this.name + " attack the player " + p.getName() + " with " + this.attackPoints + " points");
		p.setLifePoints(p.getLifePoints() - this.attackPoints);
		System.out.println("Now, the player has " + p.getLifePoints() + " lifepoints.");
		System.out.println();
	}

	public boolean isAlive(){
		return this.lifePoints>0;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	

}
