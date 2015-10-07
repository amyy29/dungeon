package view;

import java.util.Random;

import model.characters.Player;
import model.global.Dungeon;
import model.global.DungeonGenerator;
import controllers.CommandPrompt;
import controllers.GameChoice;

/**
 * ConsoleView is the Main Class of the program to launch the game in a console
 * 
 * @author Aylin G., Amelie M., Sofian C., Laurent T.
 * 
 */
public class ConsoleView {
	/**
	 * This is the main function to launch the game
	 * @param args
	 */
	public static void main (String [] args) {
		try {			
			DungeonGenerator generator;
			CommandPrompt commandPrompt = new CommandPrompt();
			Dungeon dungeon = new Dungeon();
			GameChoice gameChoice;
			int nbRandomLevels = 1;
			int classicLevel = 1;
			final int NB_CLASSIC_LEVELS = 2;
			
			System.out.println("\n\n\nWelcome to the Dungeon ! This first level has " + dungeon.getTotalRooms() + " rooms to discover!");
			System.out.println("Be careful of trap rooms and don't hesitate to explore every room, you might get a surprise !");

			gameChoice = commandPrompt.chooseGame();
			generator = new DungeonGenerator();

			if (gameChoice == GameChoice.RANDOM) {
				nbRandomLevels = new Random().nextInt(3) + 1;
				generator.createRandomMap();
			} else {
				generator.createClassicMap(classicLevel);
			}

			dungeon = new Dungeon(generator.getMap(), generator.getIdMax());		
			dungeon.setPlayer(commandPrompt.createPlayer(dungeon.getDungeonMap().get(0)));
			dungeon.showMap();
			if (gameChoice == GameChoice.RANDOM) {
				System.out.println("Number of levels : " + nbRandomLevels + "\n");
			} else {
				System.out.println("Number of levels : " + NB_CLASSIC_LEVELS + "\n");
			}
			
			do {
				dungeon.getPlayer().enterInRoom();
				commandPrompt.interpretCommand(dungeon.getPlayer());
				
				if (dungeon.gameIsWon()) {
					
					if (classicLevel < NB_CLASSIC_LEVELS && gameChoice.equals(GameChoice.CLASSIC)) {
						System.out.println("\nYou found the exit to the next level !\n");
						classicLevel++;
						Player savePlayer = dungeon.getPlayer();
						generator = new DungeonGenerator();
						generator.createClassicMap(classicLevel);
						dungeon = new Dungeon(generator.getMap(), generator.getIdMax());
						savePlayer.setCurrentRoom(dungeon.getDungeonMap().get(0));
						dungeon.setPlayer(savePlayer);
						dungeon.showMap();
					}
					
					if (nbRandomLevels != 0 && gameChoice.equals(GameChoice.RANDOM)) {
						System.out.println("\nYou found the exit to the next level !\n");
						nbRandomLevels--;
						Player savePlayer = dungeon.getPlayer();
						generator = new DungeonGenerator();
						generator.createRandomMap();
						dungeon = new Dungeon(generator.getMap(), generator.getIdMax());
						savePlayer.setCurrentRoom(dungeon.getDungeonMap().get(0));
						
						dungeon.setPlayer(savePlayer);
						dungeon.showMap();
					}
				}
				
			}
			while (nbRandomLevels != 0 && !dungeon.gameIsFinished());
			
			if (dungeon.gameIsWon()) {
				System.out.println("You found the exit ! You win!");
			}

		} catch (Exception e) {
			System.out.println("Program stopped");
			e.printStackTrace(System.out);
		}
	}
}
