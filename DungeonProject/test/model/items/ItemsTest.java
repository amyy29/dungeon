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
	public void testDescriptionOfAPotion() {
		Potion potion = new Potion(100);
		assertEquals("This potion can give you 100 life points.", potion.getDescription());
	}
	
	@Test
	public void testDescriptionOfAKey() {
		Key key = new Key(room);
		assertEquals("This key open the door 5.", key.getDescription());
	}
	
	@Test
	public void testgetMoreLifePoints(){
		assertEquals(this.potion.getMoreLifePoints(),40);
	}
	

}
