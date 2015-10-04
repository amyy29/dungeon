package model.characters;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MonsterTest {
	
	Monster squeleton;
	Player player;
	
	@Before
	public void init() {
		squeleton = new Monster(MonsterType.SQUELETON);
		player = new Player("TestName", null);
	}
	
	@Test
	public void verifyAttack() {
		assertEquals(100, player.getLifePoints());
		squeleton.attack(player);
		assertEquals(95, player.getLifePoints());
	}

}
