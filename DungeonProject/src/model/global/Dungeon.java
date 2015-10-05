package model.global;

import java.util.HashMap;
import java.util.Map;

import model.characters.Player;
import model.rooms.ExitRoom;
import model.rooms.Room;

public class Dungeon {
	private boolean hasExit;
	private Map<Integer, Room> dungeonMap;
	private Player player;
	private int totalRooms;
	
	public Dungeon() {
		this.dungeonMap = new HashMap<Integer, Room>();
	}
	
	public Dungeon(Map<Integer, Room> dungeonMap, int totalRooms) {
		this.dungeonMap = dungeonMap;
		this.totalRooms = totalRooms;
	}

	public boolean gameIsFinished()
	{
		return gameIsLost() || gameIsWon();
	}

	public boolean gameIsLost()
	{
		if (player.getLifePoints() <= 0) {
			System.out.println("You are dead! GAME OVER");
			return true;
		}
		
		if (player.getCurrentRoom().getName().equals("Trap")) {
			System.out.println("You are in a Trap. GAME OVER");
			return true;
		}
		return false;
	}

	public boolean gameIsWon() {
		Object obj = this.player.getCurrentRoom();
		if (obj instanceof ExitRoom) {
			return true;
		}
		return false;
	}
	
	public void showMap() {
		System.out.println();
		System.out.println(dungeonMap);
		for (Map.Entry<Integer, Room> e : dungeonMap.entrySet()) {
			Room r = e.getValue();
			System.out.println("Room :" + r.getId() + " : It's a " + r.getName() + " room! and is linked to " + r.getDoors());
			if (r.getMonster() != null) {
				System.out.println("And the room has a " + r.getMonster().getType());
			}
			if (r.getSurpriseItem() != null) {
				System.out.println("And the room has a " + r.getSurpriseItem().getName());
			}
		}
		System.out.println();
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public boolean isHasExit() {
		return hasExit;
	}

	public Map<Integer, Room> getDungeonMap() {
		return dungeonMap;
	}

	public Player getPlayer() {
		return player;
	}

	public int getTotalRooms() {
		return totalRooms;
	}

}
