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
		this.arm = new Arm("arm","this arm attack to 30 point", 30,5);
		this.key= new Key(room);
		this.potion=new Potion(40);
		}
	
	@Test
	public void testGetUtilisation(){
		assertEquals(this.arm.getUtilisation(),5);
	}
	
	@Test
	public void testSetUtilisation(){
		this.arm.setUtilisation();
		assertEquals(this.arm.getUtilisation(),4);	
	}
	
	@Test
	public void testGetAttackPoints(){
		assertEquals(this.arm.getAttackPoints(),30);
	}
	
	@Test
	public void testgetMoreLifePoints(){
		assertEquals(this.potion.getMoreLifePoints(),40);
	}
	
	@Test
	public void testUnLock(){
		this.room.setLocked(true);
		this.key.unLock();
		assertFalse(this.room.isLocked());	
	}
	

}
