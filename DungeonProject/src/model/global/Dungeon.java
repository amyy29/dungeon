package model.global;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import model.characters.Player;
import model.rooms.ExitRoom;
import model.rooms.Room;

public class Dungeon {
	
	private static Dungeon dungeon;
	
	private boolean hasExit;
	private final Scanner scanner;
	private Map<Integer, Room> dungeonMap;
	private Player player;
	private DungeonGenerator dungeonGenerator;
	private int totalRooms;
	private GameState gameState;
	
	public static Dungeon getInstance() {
		if (Dungeon.dungeon == null) {
			Dungeon.dungeon = new Dungeon();
			return Dungeon.dungeon;
		}
		return Dungeon.dungeon;
	}
	
	public Dungeon() {
		this.scanner = new Scanner(System.in);
		this.dungeonMap = new HashMap<Integer, Room>();
		this.dungeonGenerator = new DungeonGenerator();
		this.dungeonMap = this.dungeonGenerator.getDungeonMap();
		this.totalRooms = this.dungeonGenerator.getIdMax();
		this.gameState = GameState.BEGINNING;
	}
	
	public boolean gameIsFinished() {
		return gameIsLost() || gameIsWon();
	}

	public boolean gameIsLost() {
		if (player.getLifePoints() <= 0) {
			System.out.println("You are dead! GAME OVER");
			return true;
		}
		
		if (player.getCurrentRoom().getName().equals("TrapRoom")) {
			System.out.println("You are in a Trap. GAME OVER");
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
		}			
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public boolean isHasExit() {
		return hasExit;
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

	public GameState getGameState() {
		return gameState;
	}

	public void setGameState(GameState gameState) {
		this.gameState = gameState;
	}
	
	

}
