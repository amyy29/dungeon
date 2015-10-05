package model.global;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import model.characters.Player;
import model.rooms.EntranceRoom;
import model.rooms.ExitRoom;
import model.rooms.NormalRoom;
import model.rooms.Room;

public class DungeonTest {
	Dungeon dungeon;
	Map<Integer, Room> dungeonMap;
	Player player;
	Room entrance;
	Room normal;
	Room exit;
	
	@Before
	public void init() {
		entrance = new EntranceRoom(0);
		normal = new NormalRoom(1);
		exit = new ExitRoom(2);
		
		dungeonMap = new HashMap<Integer, Room>();
		
		dungeonMap.put(entrance.getId(), entrance);
		dungeonMap.put(normal.getId(), normal);
		dungeonMap.put(exit.getId(), exit);
		
		dungeon = new Dungeon(dungeonMap, 3);
		player = new Player("TestName", entrance);
		dungeon.setPlayer(player);
	}
	
	@Test
	public void testGameIsLost() {
		assertFalse(dungeon.gameIsLost());
		player.setLifePoints(0);
		assertTrue(dungeon.gameIsLost());
	}
	
	@Test
	public void testGameIsWon() {
		assertFalse(dungeon.gameIsWon());
		player.setCurrentRoom(exit);
		assertTrue(dungeon.gameIsWon());
	}
	
	@Test
	public void testGameIsFinished() {
		assertFalse(dungeon.gameIsFinished());
		player.setCurrentRoom(exit);
		assertTrue(dungeon.gameIsFinished());
	}
	
}
