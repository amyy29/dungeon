package model.items;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ArmTypeTest {

	Arm gun;
	Arm laser;
	Arm bomb;

	@Before
	public void init() {
		gun = new Arm(ArmType.GUN);
		laser = new Arm(ArmType.LASER);
		bomb = new Arm(ArmType.BOMB);
	}

	@Test
	public void verifyCorrectName() {
		assertEquals("Gun", gun.getName());
		assertEquals("Laser", laser.getName());
		assertEquals("Bomb", bomb.getName());
	}
	
	@Test
	public void verifyDescription() {
		assertEquals("Little arm attacks with 10 points per hit", gun.getDescription());
		assertEquals("Destroy with 20 points per hit" , laser.getDescription());
		assertEquals("Destroy all with 50 points per hit" , bomb.getDescription());
	}
	
	@Test
	public void verifyCorrectAttackPoints() {
		assertEquals(10, gun.getAttackPoints());
		assertEquals(20, laser.getAttackPoints());
		assertEquals(50, bomb.getAttackPoints());
	}

}
