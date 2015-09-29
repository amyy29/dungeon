package view;

import java.util.NoSuchElementException;

import model.global.Dungeon;

public class ConsoleView {
	public static void main (String [] args) {
		Dungeon dungeon = new Dungeon();

		System.out.println("\n\n\nWelcome to the Dungeon ! This first level has " + dungeon.getTotalRooms() + " rooms to discover!");
		System.out.println("Be careful of trap rooms and don't hesitaggte to explore every room, you might get a surprise !");
		try {
			dungeon.createPlayer();
			dungeon.showMap();
			dungeon.start();
		} catch (NoSuchElementException e) {
			System.out.println("Program stopped");			
		}
	}
}
