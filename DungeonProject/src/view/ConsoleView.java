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
			int nbLevels = 1;
			int classicLevel = 1;
			final int nbLevelsClassic = 2;
			
			System.out.println("\n\n\nWelcome to the Dungeon ! This first level has " + dungeon.getTotalRooms() + " rooms to discover!");
			System.out.println("Be careful of trap rooms and don't hesitate to explore every room, you might get a surprise !");

			gameChoice = commandPrompt.chooseGame();
			generator = new DungeonGenerator(gameChoice);

			if (gameChoice == GameChoice.RANDOM) {
				nbLevels = new Random().nextInt(3) + 1;
			} else {
				generator.setLevel(classicLevel);
			}

			dungeon = new Dungeon(generator.getMap(), generator.getIdMax());		
			dungeon.setPlayer(commandPrompt.createPlayer(dungeon.getDungeonMap().get(0)));
			dungeon.showMap();			
			System.out.println("number of level : " + nbLevels);
			
			do {
				dungeon.getPlayer().enterInRoom();
				commandPrompt.interpretCommand(dungeon.getPlayer());
				
				if (dungeon.gameIsWon()) {
					
					if (classicLevel < nbLevelsClassic && gameChoice.equals(GameChoice.CLASSIC)) {
						System.out.println("\nYou found the exit to the next level !\n");
						classicLevel++;
						Player savePlayer = dungeon.getPlayer();
						generator = new DungeonGenerator(gameChoice);
						generator.setLevel(classicLevel);
						dungeon = new Dungeon(generator.getMap(), generator.getIdMax());
						savePlayer.setCurrentRoom(dungeon.getDungeonMap().get(0));
						dungeon.setPlayer(savePlayer);
						dungeon.showMap();
					}
					
					nbLevels--;
					
					if (nbLevels != 0 && gameChoice.equals(GameChoice.RANDOM)) {
						System.out.println("\nYou found the exit to the next level !\n");
						Player savePlayer = dungeon.getPlayer();
						generator = new DungeonGenerator(gameChoice);
						dungeon = new Dungeon(generator.getMap(), generator.getIdMax());
						savePlayer.setCurrentRoom(dungeon.getDungeonMap().get(0));
						
						dungeon.setPlayer(savePlayer);
						dungeon.showMap();
					}
				}
				
			}
			while (nbLevels != 0 && !dungeon.gameIsFinished());
			
			if (dungeon.gameIsWon()) {
				System.out.println("You found the exit ! You win!");
			}

		} catch (Exception e) {
			System.out.println("Program stopped");
			e.printStackTrace(System.out);
		}
	}
}
