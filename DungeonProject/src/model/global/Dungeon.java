package model.global;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import controllers.CommandPrompt;

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
	private CommandPrompt commandPrompt;
	
	public Dungeon() {
		this.scanner = new Scanner(System.in);
		this.dungeonMap = new HashMap<Integer, Room>();
		this.dungeonGenerator = new DungeonGenerator();
		this.dungeonMap = this.dungeonGenerator.getDungeonMap();
		this.totalRooms = this.dungeonGenerator.getIdMax();
		this.commandPrompt = new CommandPrompt();
	}

	public void createPlayer() {
		System.out.println("----------------------\n");
		System.out.println("What is your name?");
		System.out.print("> ");
		String line = scanner.nextLine();
		this.player = new Player(line, dungeonMap.get(0));
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
			int i = e.getKey();
			Room r = e.getValue();
			System.out.println("Room :" + r.getId() + " : It's a " + r.getName() + " room! and is linked to " + r.getDoors());
			/*if (r.getMonster() != null)
			System.out.println(" And the room has a monster called " + r.getMonster().getName());*/
		}			
	}
	
	public void start() {
		do {			
			this.player.whereIsPlayer();
			this.commandPrompt.interpretCommand(this.player);
		}
		while (!gameIsFinished());
			if (gameIsWon()) {
				System.out.println("You found the exit ! You win!");
			}
			else
				return;
	}

	public int getTotalRooms() {
		return totalRooms;
	}

}
