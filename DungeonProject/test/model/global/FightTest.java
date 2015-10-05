package model.global;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.characters.Monster;
import model.characters.MonsterType;
import model.characters.Player;

public class FightTest {
	
	Fight fight;
	Monster monster;
	Player player;
	
	@Before
	public void init() {
		monster = new Monster(MonsterType.SQUELETON);
		player = new Player("testName", null);
		fight = new Fight(monster, player);
	}

	@Test
	public void testIfTheMonsterIsNotAliveAfterTheFight() throws InterruptedException {
		assertTrue(monster.isAlive());
		assertTrue(player.isAlive());
		fight.goFight();
		assertFalse(monster.isAlive());
		assertTrue(player.isAlive());
	}
	
	@Test
	public void testCorrectLifePointsAfterFight() throws InterruptedException {
		assertEquals(50, monster.getLifePoints());
		assertEquals(100, player.getLifePoints());
		fight.goFight();
		assertEquals(0, monster.getLifePoints());
		assertEquals(80, player.getLifePoints());
	}

}
