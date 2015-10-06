package model.characters;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.items.Arm;
import model.items.ArmType;
import model.items.Key;
import model.items.Potion;
import model.rooms.ExitRoom;
import model.rooms.NormalRoom;
import model.rooms.Room;
import model.rooms.TrapRoom;
import model.rooms.TreasureRoom;

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
	public void verifyEnterInATrap() throws InterruptedException {
		Room trap = new TrapRoom(2);
		assertEquals(100, player.getLifePoints());
		player.setCurrentRoom(trap);
		player.enterInRoom();
		assertEquals(65, player.getLifePoints());
	}
	
	@Test
	public void verifyThePlayerChangesRoom() {
		String [] direction = {"go","1"};
		assertEquals(currentRoom, player.getCurrentRoom());
		player.changeRoom(direction);
		assertEquals(exit, player.getCurrentRoom());
	}
	
	@Test
	public void verifyThePlayerDoesntChangeRoomIfItsLocked() {
		exit.setLocked(true);
		String [] direction = {"go","1"};
		assertEquals(currentRoom, player.getCurrentRoom());
		player.changeRoom(direction);
		assertEquals(currentRoom, player.getCurrentRoom());
	}
	
	@Test
	public void verifyThePlayerDoesntChangeRoom() {
		String [] direction = {"go","2"};
		assertEquals(currentRoom, player.getCurrentRoom());
		player.changeRoom(direction);
		assertEquals(currentRoom, player.getCurrentRoom());
	}

	@Test
	public void verifySetSearchedToTrue() {
		assertFalse(currentRoom.isSearched());
		player.searchInRoom();
		assertTrue(currentRoom.isSearched());
	}
	
	@Test
	public void verifySearchInTreasureRoomWillGiveGold() {
		Room treasure = new TreasureRoom(2);
		int goldOfTreasureRoom = treasure.getGold();
		assertEquals(0, player.getGold());
		player.setCurrentRoom(treasure);
		assertEquals(0, player.getGold());
		player.searchInRoom();
		assertEquals(goldOfTreasureRoom, player.getGold());
	}
	
	@Test
	public void verifyTakeItem() {
		Arm bomb = new Arm(ArmType.BOMB);
		currentRoom.setSurpriseItem(bomb);
		assertEquals(0, player.getBag().size());
		player.takeItem();
		assertEquals(1, player.getBag().size());
		assertEquals(bomb, player.getBag().get(0));
	}
	
	@Test
	public void verifyRemoveItem() {
		Arm bomb = new Arm(ArmType.BOMB);
		Key key = new Key(exit);
		player.getBag().add(bomb);
		player.getBag().add(key);
		assertEquals(2, player.getBag().size());
		assertEquals(bomb, player.getBag().get(0));
		assertEquals(key, player.getBag().get(1));
		player.removeItem(1);
		assertEquals(1, player.getBag().size());
		assertEquals(bomb, player.getBag().get(0));
		player.removeItem(0);
		assertEquals(0, player.getBag().size());
	}
	
	@Test
	public void verifyDrink() {
		Potion potion = new Potion(40);
		assertEquals(100, player.getLifePoints());
		player.getBag().add(potion);
		player.drink(0);
		assertEquals(140, player.getLifePoints());
		player.getBag().add(new Arm(ArmType.BOMB));
		player.drink(0);
		assertEquals(140, player.getLifePoints());
	}
	
	@Test
	public void verifyUseKey() {
		Key key = new Key(exit);
		exit.setLocked(true);
		assertTrue(exit.isLocked());
		player.getBag().add(key);
		player.getBag().add(new Arm(ArmType.BOMB));
		player.useKey(1);
		assertTrue(exit.isLocked());
		player.useKey(0);
		assertFalse(exit.isLocked());
	}
	
	@Test
	public void verifyAttack() {
		assertEquals(40, zombie.getLifePoints());
		player.attack(zombie);
		assertEquals(30, zombie.getLifePoints());
	}
}
