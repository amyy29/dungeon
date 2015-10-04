package model.characters;

public class Monster extends Character implements Fighter {

	public Monster(String name, int lifePoints, int attackPoints) {
		super(name, lifePoints, attackPoints);
	}

	@Override
	public void attack(Character c) {
		System.out.println("------------------\n");
		System.out.println("The monster " + this.name + " attack the player " + c.getName() + " with " + this.attackPoints + " points");
		c.setLifePoints(c.getLifePoints() - this.attackPoints);
		System.out.println("Now, the player has " + c.getLifePoints() + " lifepoints.");
		System.out.println();
	}

}
