package model.rooms;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.characters.Monster;
import model.characters.MonsterType;
import model.items.Arm;
import model.items.ArmType;
import model.items.Item;

public class RoomsTest {
	
	EntranceRoom entrance;
	ExitRoom exit;
	NormalRoom normal;
	TrapRoom trap;
	TreasureRoom treasure;
	Room previousRoom;
	Monster monster;
	Item surpriseItem;
	
	@Before
	public void initTestProperties() {
		this.previousRoom = new NormalRoom(2);
		this.monster = new Monster(MonsterType.ZOMBIE);
		this.surpriseItem = new Arm(ArmType.GUN);
	}

	@Test
	public void testOfEntranceConstructors() {
		this.entrance = new EntranceRoom(1);
		assertEquals(1, this.entrance.id);
		assertEquals("Entrance", this.entrance.name);
		assertNull(this.entrance.surpriseItem);
		
		this.entrance = new EntranceRoom(1, this.surpriseItem);
		assertNotNull(this.entrance.surpriseItem);
		assertEquals(this.surpriseItem, this.entrance.surpriseItem);
	}
	
	@Test
	public void testOfExitConstructors() {
		this.exit = new ExitRoom(4);
		assertEquals(4, this.exit.id);
		assertEquals("Exit", this.exit.name);
		assertEquals(0, this.exit.doors.size());
		
		this.exit = new ExitRoom(4, this.previousRoom);
		assertEquals(1, this.exit.doors.size());
		assertEquals(this.previousRoom, this.exit.doors.get(2));
	
		this.exit = new ExitRoom(4, this.previousRoom, this.monster);
		assertEquals(this.monster, this.exit.monster);
	}
	
	@Test
	public void testOfNormalConstructors() {
		this.normal = new NormalRoom(5);
		assertEquals(5, this.normal.id);
		assertEquals("Normal", this.normal.name);
		assertEquals(0, this.normal.doors.size());
		
		this.normal = new NormalRoom(5, this.previousRoom, this.monster);
		assertEquals(1, this.normal.doors.size());
		assertEquals(this.previousRoom, this.normal.doors.get(2));
		assertEquals(this.monster, this.normal.monster);
	
		this.normal = new NormalRoom(4, this.previousRoom, this.surpriseItem);
		assertEquals(this.surpriseItem, this.normal.surpriseItem);
	}
	
	@Test
	public void testOfTrapConstructor() {
		this.trap = new TrapRoom(6, this.previousRoom, this.monster);
		assertEquals(6, this.trap.id);
		assertEquals("Trap", this.trap.name);
		assertEquals(1, this.trap.doors.size());
		assertEquals(this.previousRoom, this.trap.doors.get(2));
		assertEquals(this.monster, this.trap.monster);
	}
	
	@Test
	public void testOfTreasureConstructor() {
		this.treasure = new TreasureRoom(7, this.previousRoom, this.monster);
		assertEquals(7, this.treasure.id);
		assertEquals("Treasure", this.treasure.name);
		assertEquals(1, this.treasure.doors.size());
		assertEquals(this.previousRoom, this.treasure.doors.get(2));
		assertEquals(this.monster, this.treasure.monster);
		assertNotEquals(0, this.treasure.gold);
	}

}