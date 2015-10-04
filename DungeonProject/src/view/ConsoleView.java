package view;

import model.global.Dungeon;
import model.global.DungeonGenerator;
import controllers.CommandPrompt;

public class ConsoleView {
	public static void main (String [] args) {
		DungeonGenerator generator;
		CommandPrompt commandPrompt = new CommandPrompt();
		Dungeon dungeon = new Dungeon();
		
		System.out.println("\n\n\nWelcome to the Dungeon ! This first level has " + dungeon.getTotalRooms() + " rooms to discover!");
		System.out.println("Be careful of trap rooms and don't hesitate to explore every room, you might get a surprise !");
		
		generator = new DungeonGenerator(commandPrompt.chooseGame());
		dungeon = new Dungeon(generator.getMap(), generator.getIdMax());
		
		try {
			dungeon.setPlayer(commandPrompt.createPlayer(dungeon.getDungeonMap().get(0)));
			dungeon.showMap();
			
			do {
				dungeon.getPlayer().enterInRoom();
				commandPrompt.interpretCommand(dungeon.getPlayer());
			}
			while (!dungeon.gameIsFinished());
			
			if (dungeon.gameIsWon()) {
				System.out.println("You found the exit ! You win!");
			}
			
		} catch (Exception e) {
			System.out.println("Program stopped");
			 e.printStackTrace(System.out);
		}
	}
}
