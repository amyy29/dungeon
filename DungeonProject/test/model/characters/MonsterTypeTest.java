package model.characters;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MonsterTypeTest {
	
	Monster squeleton;
	Monster zombie;
	Monster demon;
	
	@Before
	public void init() {
		squeleton = new Monster(MonsterType.SQUELETON);
		zombie = new Monster(MonsterType.ZOMBIE);
		demon = new Monster(MonsterType.DEMON);
	}

	@Test
	public void verifyCorrectLifeAndAttackPoints() {
		assertEquals(50, squeleton.getLifePoints());
		assertEquals(40, zombie.getLifePoints());
		assertEquals(60, demon.getLifePoints());
		assertEquals(5, squeleton.getAttackPoints());
		assertEquals(8, zombie.getAttackPoints());
		assertEquals(10, demon.getAttackPoints());
	}
	
	@Test
	public void verifyToString() {
		assertEquals("Squeleton", squeleton.getType().toString());
		assertEquals("Zombie" , zombie.getType().toString());
		assertEquals("Demon" , demon.getType().toString());
	}

}