package model.global;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import model.characters.Player;
import model.rooms.ExitRoom;
import model.rooms.Room;

public class Dungeon {
	private boolean hasExit;
	private boolean gameIsFinished;
	private final Scanner scanner;
	private Map<Integer, Room> dungeonMap;
	private Player player;
	private DungeonGenerator dungeonGenerator;
	private int totalRooms;
	
	public Dungeon() {
		this.scanner = new Scanner(System.in);
		this.dungeonMap = new HashMap<Integer, Room>();
		this.dungeonGenerator = new DungeonGenerator();
		this.dungeonMap = this.dungeonGenerator.getDungeonMap();
		this.totalRooms = this.dungeonGenerator.getIdMax();
	}
	
	public boolean gameIsFinished()
	{
		return gameIsLost() || gameIsWon();
	}

	public boolean gameIsLost()
	{
		if (player.getLifePoints() <= 0)
		{
			System.out.println("You are dead!");
			return true;
		}
		return false;
	}

	public boolean gameIsWon() {
		Object obj = this.player.getCurrentRoom();
		if (obj instanceof ExitRoom)
		{
			return true;
		}
		return false;
	}
	
	public void showMap() {
		System.out.println(dungeonMap);
		for (Map.Entry<Integer, Room> e : dungeonMap.entrySet()) {
			Room r = e.getValue();
			System.out.println("Room :" + r.getId() + " : It's a " + r.getName() + " room! and is linked to " + r.getDoors());
			if (r.getMonster() != null)
			System.out.println(" And the room has a monster called " + r.getMonster().getName());
		}			
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public boolean isHasExit() {
		return hasExit;
	}

	public boolean isGameIsFinished() {
		return gameIsFinished;
	}

	public Scanner getScanner() {
		return scanner;
	}

	public Map<Integer, Room> getDungeonMap() {
		return dungeonMap;
	}

	public Player getPlayer() {
		return player;
	}

	public DungeonGenerator getDungeonGenerator() {
		return dungeonGenerator;
	}

	public int getTotalRooms() {
		return totalRooms;
	}

}
