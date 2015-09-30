package model.global;

import model.characters.Monster;
import model.characters.Player;

public class Fight {

	protected Monster monster;
	protected Player player;

	public Fight(Monster monster, Player player) {
		this.monster = monster;
		this.player = player;
	}

	public void goFight() throws InterruptedException{
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