package model.global;
import static org.junit.Assert.*;

import java.util.Map;

import model.characters.MonsterType;
import model.items.Key;
import model.items.Potion;
import model.rooms.EntranceRoom;
import model.rooms.ExitRoom;
import model.rooms.NormalRoom;
import model.rooms.Room;

import org.junit.Before;
import org.junit.Test;

public class DungeonGeneratorTest {
	DungeonGenerator randomGen;
	DungeonGenerator classicGen;
	
	@Before
	public void init() {
		randomGen = new DungeonGenerator();
		classicGen = new DungeonGenerator();
	}
	
	@Test
	public void verifyClassicMapRoomNamesLevel1() {
		classicGen.createClassicMap(1);
		assertEquals("Entrance", classicGen.getDungeonMap().get(0).getName());
		assertEquals("Normal", classicGen.getDungeonMap().get(1).getName());
		assertEquals("Normal", classicGen.getDungeonMap().get(2).getName());
		assertEquals("Trap", classicGen.getDungeonMap().get(3).getName());
		assertEquals("Trap", classicGen.getDungeonMap().get(4).getName());
		assertEquals("Normal", classicGen.getDungeonMap().get(5).getName());
		assertEquals("Normal", classicGen.getDungeonMap().get(6).getName());
		assertEquals("Exit", classicGen.getDungeonMap().get(7).getName());
	}
	
	@Test
	public void verifyClassicMapItemsCharactersLevel1() {
		classicGen.createClassicMap(1);
		assertEquals(MonsterType.SQUELETON, classicGen.getDungeonMap().get(1).getMonster().getType());
		assertEquals("Gun", classicGen.getDungeonMap().get(2).getSurpriseItem().getName());
		assertEquals(MonsterType.ZOMBIE, classicGen.getDungeonMap().get(5).getMonster().getType());
	}
	
	@Test
	public void verifyCorrectRoomToOpenLevel1() {
		classicGen.createClassicMap(1);
		assertTrue(classicGen.getDungeonMap().get(2).isLocked());
		assertEquals("Key", classicGen.getDungeonMap().get(0).getSurpriseItem().getName());
		Key keyForRoom2 = (Key) classicGen.getDungeonMap().get(0).getSurpriseItem();
		assertEquals(classicGen.getDungeonMap().get(2), keyForRoom2.getRoomToOpen());
	}
	
	@Test
	public void verifyClassicMapRoomNamesLevel2() {
		classicGen.createClassicMap(2);
		assertEquals("Entrance", classicGen.getDungeonMap().get(0).getName());
		assertEquals("Normal", classicGen.getDungeonMap().get(1).getName());
		assertEquals("Normal", classicGen.getDungeonMap().get(2).getName());
		assertEquals("Normal", classicGen.getDungeonMap().get(3).getName());
		assertEquals("Normal", classicGen.getDungeonMap().get(4).getName());
		assertEquals("Normal", classicGen.getDungeonMap().get(5).getName());
		assertEquals("Normal", classicGen.getDungeonMap().get(6).getName());
		assertEquals("Normal", classicGen.getDungeonMap().get(7).getName());
		assertEquals("Trap", classicGen.getDungeonMap().get(8).getName());
		assertEquals("Normal", classicGen.getDungeonMap().get(9).getName());
		assertEquals("Normal", classicGen.getDungeonMap().get(10).getName());
		assertEquals("Normal", classicGen.getDungeonMap().get(11).getName());
		assertEquals("Exit", classicGen.getDungeonMap().get(12).getName());
		assertEquals("Normal", classicGen.getDungeonMap().get(13).getName());
	}
	
	@Test
	public void verifyClassicMapItemsCharactersLevel2() {
		classicGen.createClassicMap(2);
		Potion potion = (Potion) classicGen.getDungeonMap().get(0).getSurpriseItem();
		assertEquals("Potion", potion.getName());
		assertEquals(100, potion.getMoreLifePoints());
		assertEquals("Laser", classicGen.getDungeonMap().get(2).getSurpriseItem().getName());
		assertEquals(MonsterType.SQUELETON, classicGen.getDungeonMap().get(6).getMonster().getType());
		assertEquals(MonsterType.DEMON, classicGen.getDungeonMap().get(7).getMonster().getType());
		assertEquals("Potion", classicGen.getDungeonMap().get(0).getSurpriseItem().getName());
	}
	
	@Test
	public void verifyCorrectRoomToOpenLevel2() {
		classicGen.createClassicMap(2);
		assertTrue(classicGen.getDungeonMap().get(12).isLocked());
		assertEquals("Key", classicGen.getDungeonMap().get(11).getSurpriseItem().getName());
		Key keyForRoom12 = (Key) classicGen.getDungeonMap().get(11).getSurpriseItem();
		assertEquals(classicGen.getDungeonMap().get(12), keyForRoom12.getRoomToOpen());
	}

	@Test
	public void createClassicTest() {
		Room room0 = new EntranceRoom(0);
		Room room1 = new NormalRoom(1);
		Room room2 = new ExitRoom(2);
		
		this.classicGen.getDungeonMap().put(0, room0);
		this.classicGen.getDungeonMap().put(1, room1);
		this.classicGen.getDungeonMap().put(2, room2);
		
		Room r = this.classicGen.getDungeonMap().get(0);
		Room r1 = this.classicGen.getDungeonMap().get(1);
		Room r2 = this.classicGen.getDungeonMap().get(2);
		
		assertEquals(r2.getId(), 2);
		assertEquals(r.getId(), 0);
		assertEquals(r1.getId(), 1);

		r.getDoors().put(r1.getId(), r1);
		r1.getDoors().put(r2.getId(), r2);
		
		Room neightbour = r.getDoors().get(1);
		Room neightbour1 = r1.getDoors().get(2);
		
		assertEquals(neightbour1.getId(), r2.getId());
		assertEquals(neightbour.getId(), r1.getId());
		
	}
	
	@Test
	public void randomizedMapCreateExitTest() {
		this.randomGen.createRandomMap();
		
		Room roomtest = null;
		ExitRoom exit = new ExitRoom(10);
		
		for (Map.Entry<Integer, Room> e : this.randomGen.getMap().entrySet()) {
			roomtest = e.getValue();
			if (roomtest.getName().equals("Exit")) {
				assertEquals(roomtest.getName(), exit.getName());
				break;				
			}
		}
	}
	
	@Test
	public void randomizedMapCreateEntranceTest() {
		this.randomGen.createRandomMap();
		
		Room room = this.randomGen.getMap().get(0);
		assertEquals(room.getName(), "Entrance");
	}

}
