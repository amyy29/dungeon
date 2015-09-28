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
	private DungeonGenerator randomMap;
	
	public Dungeon() {
		this.scanner = new Scanner(System.in);
		this.dungeonMap = new HashMap<Integer, Room>();
		this.randomMap = new DungeonGenerator();
	}

	public DungeonGenerator getRandomMap() {
		return randomMap;
	}

	public void createPlayer()
	{
			System.out.println("----------------------\n");	
			System.out.println("What is your name?");	
			System.out.print("> ");
			String line = scanner.nextLine();
			for (Map.Entry<Integer, Room> e : dungeonMap.entrySet())
				{
					int i = e.getKey();
					if (i == 0)
					{
						Room r = e.getValue();
						this.player = new Player(line, r);
					}
				}
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

	public boolean gameIsWon()
	{
		Object obj = this.player.getCurrentRoom();
		if (obj instanceof ExitRoom)
		{
			System.out.println("It's a " + player.getCurrentRoom().getName() + " room!");
			return true;
		}
		return false;
	}

}
