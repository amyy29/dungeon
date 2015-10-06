package controllers;

import model.characters.*;
import model.items.Arm;
import model.items.Item;
import model.rooms.Room;

import java.util.List;
import java.util.Scanner;

/**
 * CommandPrompt is used for all interactions between the user and the program.
 * 
 * @author Aylin G., Amelie M., Sofian C., Laurent T.
 * 
 */
public class CommandPrompt {
	
	protected Scanner sc;
	
	public CommandPrompt() {
		this.sc = new Scanner(System.in);
	}
	
	/**
	 * This function is called when the user chose the game.
	 * 
	 * @return the gameChoice chosen by the player
	 */
	public GameChoice chooseGame() {
		String answer;
		do {
			System.out.println("----------------------\n");
			System.out.println("Which sort of dungeon do you want ?");
			System.out.println("1. The random dungeon");
			System.out.println("2. The classic dungeon");
			System.out.print("> ");
			answer = sc.nextLine();
			switch(answer) {
			case "1":
				return GameChoice.RANDOM;
			case "2":
				return GameChoice.CLASSIC;
			default:
				System.out.println("\nI don't know what you mean\n");
				break;
			}
		} while(!answer.equals("1") && !answer.equals("2"));
		
		return null;
	}
	
	/**
	 * This function is called when the user chose an arm before a fight
	 * 
	 * @param bag the item's list of the player
	 * @return answer 
	 */
	public String chooseArm(List<Item> bag) {
		System.out.println("----------------------\n");
		System.out.println("Which arm do you want for this fight ?\n");
		int compteur = 0;
		System.out.println(compteur + " -> Fist (attackPoints: 10)");
		compteur++;
		for(Item i: bag) {
			if (i.getName().equals("Weapon") || i.getName().equals("Laser") || i.getName().equals("Bomb")) {
				Arm arm = (Arm) i;
				System.out.println(compteur + " -> " + arm.getName() + " (attackPoints: " + arm.getAttackPoints() + ")");
			}
			compteur++;
		}
		System.out.print("> ");
		String answer = sc.nextLine();
		return answer;
	}
	
	/**
	 * This function is called when the program create a player for a new game
	 * 
	 * @param currentRoom
	 * @return a new Player
	 */
	public Player createPlayer(Room currentRoom) {
		System.out.println("----------------------\n");
		System.out.println("What is your name ?");
		System.out.print("> ");
		String name = sc.nextLine();
		return new Player(name, currentRoom);
	}
	
	
	/**
	 * This function is used to interpret all the commands of the user
	 * 
	 * @param player
	 * @throws InterruptedException
	 */
	public void interpretCommand(Player player) throws InterruptedException {
		System.out.println("What do you want to do ?");	
		System.out.print("> ");
		String command = sc.nextLine();			
		System.out.println("----------------------\n");	
		
		if (command.length() == 0) {
			System.out.println("You can put 'go *number of the door' or 'search'");
			return;
		}
		
		String[] commandSplitted = command.split(" ");
		
		switch (commandSplitted[0]){
			case "go":
				if (player.getCurrentRoom().getMonster() != null && player.getCurrentRoom().getMonster().isAlive()) {
					System.out.println("There is a monster in the room, You can't leave this room");
				} else {
					player.changeRoom(commandSplitted);
				}
				break;
			case "describe":
				player.describeCurrentRoom();
				break;
			case "help":
				player.showHelpMenu();
				break;
			case "hit" :
				player.hit();
				break;
			case "search":
				player.searchInRoom();
				break;
			case "situation":
				System.out.println(player);
				break;
			case "take":
				player.takeItem();
				break;
			case "remove":
				player.removeItem(Integer.parseInt(commandSplitted[1]));
				break;
			case "drink":
				player.drink(Integer.parseInt(commandSplitted[1]));
				break;
			case "use":
				player.useKey(Integer.parseInt(commandSplitted[1]));
				break;
			case "quit":
				System.out.println("You quit the game");
				System.exit(0);
			default:
			System.out.println("I don’t know what you mean\n");
			break;
		}
	}
}
