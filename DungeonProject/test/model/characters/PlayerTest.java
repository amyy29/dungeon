package model.characters;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import model.rooms.ExitRoom;
import model.rooms.NormalRoom;
import model.rooms.Room;

public class PlayerTest {
	
	Player player;
	Room currentRoom;
	Room exit;
	Monster zombie = new Monster(MonsterType.ZOMBIE);

	@Before
	public void init() {
		zombie = new Monster(MonsterType.ZOMBIE);
		currentRoom = new NormalRoom(0, zombie);
		exit = new ExitRoom(1);
		currentRoom.getDoors().put(1, exit);
		player = new Player("TestName", currentRoom);
	}

	@Test
	public void verifyAttack() {
		assertEquals(40, zombie.getLifePoints());
		player.attack(zombie);
		assertEquals(30, zombie.getLifePoints());
	}
	
	@Test
	public void verifyThePlayerChangesRoom() {
		String [] direction = {"go","1"};
		assertEquals(currentRoom, player.getCurrentRoom());
		player.changeRoom(direction);
		assertEquals(exit, player.getCurrentRoom());
	}
	
	@Test
	public void verifyThePlayerDoesntChangeRoom() {
		String [] direction = {"go","3"};
		assertEquals(currentRoom, player.getCurrentRoom());
		player.changeRoom(direction);
		assertEquals(currentRoom, player.getCurrentRoom());
	}
}
