package model.global;
import static org.junit.Assert.*;

import java.util.Map;

import model.rooms.EntranceRoom;
import model.rooms.ExitRoom;
import model.rooms.NormalRoom;
import model.rooms.Room;

import org.junit.Test;

public class DungeonGeneratorTest {
	DungeonGenerator randomGen = new DungeonGenerator();
	DungeonGenerator classicGen = new DungeonGenerator();

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
		
		for (Map.Entry<Integer, Room> e : this.randomGen.getMap().entrySet())
		{
			roomtest = e.getValue();
			if (roomtest.getName().equals("Exit"))
			{
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
