package model.characters;

public class Monster extends Character {

	public Monster(String name, int lifePoints, int attackPoints) {
		super(name, lifePoints, attackPoints);
	}

	public void attack(Player p){
		System.out.println("------------------\n");
		System.out.println("The monster " + this.name + " attack the player " + p.getName() + " with " + this.attackPoints + " points");
		p.setLifePoints(p.getLifePoints() - this.attackPoints);
		System.out.println("Now, the player has " + p.getLifePoints() + " lifepoints.");
		System.out.println();
	}

}
