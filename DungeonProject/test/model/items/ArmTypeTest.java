package model.items;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ArmTypeTest {

	Arm weapon;
	Arm laser;
	Arm bomb;

	@Before
	public void init() {
		weapon = new Arm(ArmType.WEAPON);
		laser = new Arm(ArmType.LASER);
		bomb = new Arm(ArmType.BOMB);
	}

	@Test
	public void verifyCorrectName() {
		assertEquals("Weapon", weapon.getName());
		assertEquals("Laser", laser.getName());
		assertEquals("Bomb", bomb.getName());
	}
	
	@Test
	public void verifyDescription() {
		assertEquals("Little arm attacks with 10 points per hit", weapon.getDescription());
		assertEquals("Destroy with 20 points per hit" , laser.getDescription());
		assertEquals("Destroy all with 50 points per hit" , bomb.getDescription());
	}
	
	@Test
	public void verifyCorrectAttackPoints() {
		assertEquals(10, weapon.getAttackPoints());
		assertEquals(20, laser.getAttackPoints());
		assertEquals(50, bomb.getAttackPoints());
	}

}
