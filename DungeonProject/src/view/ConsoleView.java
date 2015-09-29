package view;

import java.util.NoSuchElementException;

import model.global.Dungeon;
import controllers.CommandPrompt;

public class ConsoleView {
	public static void main (String [] args) {
		Dungeon dungeon = new Dungeon();
		CommandPrompt commandPrompt = new CommandPrompt();

		System.out.println("\n\n\nWelcome to the Dungeon ! This first level has " + dungeon.getTotalRooms() + " rooms to discover!");
		System.out.println("Be careful of trap rooms and don't hesitaggte to explore every room, you might get a surprise !");
		
		try {
			dungeon.setPlayer(commandPrompt.createPlayer(dungeon.getDungeonMap().get(0)));
			dungeon.showMap();
			
			do {			
				dungeon.getPlayer().showCurrentRoomInfos();
				commandPrompt.interpretCommand(dungeon.getPlayer());
			}
			while (!dungeon.isGameIsFinished());
			
			if (dungeon.gameIsWon()) {
				System.out.println("You found the exit ! You win!");
			}
			
		} catch (NoSuchElementException e) {
			System.out.println("Program stopped");			
		}
	}
}
