package model.global;

import model.characters.Monster;
import model.characters.Player;

/**
 * Fight is the class used to simulate a fight between two characters
 * 
 * @author Aylin G., Amelie M., Sofian C., Laurent T.
 * 
 */
public class Fight {

	protected Monster monster;
	protected Player player;

	public Fight(Monster monster, Player player) {
		this.monster = monster;
		this.player = player;
	}

	/**
	 * goFight is the function called to start a fight between the Monster and the Player
	 * 
	 * @throws InterruptedException
	 */
	public void goFight() throws InterruptedException {
		while (this.monster.isAlive() && this.player.isAlive()) {
			Thread.sleep(400);
			this.player.attack(this.monster);
			if (this.monster.isAlive()) {
				Thread.sleep(400);
				this.monster.attack(this.player);
			}
		}
	}
}