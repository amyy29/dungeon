package model.items;
import static org.junit.Assert.*;
import model.rooms.NormalRoom;

import org.junit.Test;
import org.junit.Before;

public class ItemsTest {
	Arm arm;
	Potion potion;
	Key key;
	NormalRoom room;
	
	@Before
	public void initTestProperties() {
		this.room = new NormalRoom(5);
		this.arm = new Arm(ArmType.BOMB);
		this.key= new Key(room);
		this.potion=new Potion(40);
	}
	
	@Test
	public void testUnLock(){
		this.room.setLocked(true);
		assertTrue(this.room.isLocked());	
		this.key.unLock();
		assertFalse(this.room.isLocked());
	}
	
	@Test
	public void testGetAttackPoints(){
		assertEquals(this.arm.getAttackPoints(),50);
	}
	
	@Test
	public void testgetMoreLifePoints(){
		assertEquals(this.potion.getMoreLifePoints(),40);
	}
	

}
