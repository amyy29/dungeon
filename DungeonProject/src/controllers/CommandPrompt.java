package controllers;

import model.characters.Player;
import model.rooms.Room;

import java.util.Scanner;

public class CommandPrompt {
	
	protected Scanner sc;
	
	public CommandPrompt() {
		this.sc = new Scanner(System.in);
	}
	
	public Player createPlayer(Room currentRoom) {
		System.out.println("----------------------\n");
		System.out.println("What is your name?");
		System.out.print("> ");
		String name = sc.nextLine();
		return new Player(name, currentRoom);
	}
	
	public void interpretCommand(Player player) {
		System.out.println("What do you want to do?");	
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
				player.changeRoom(commandSplitted);
				break;
			/*case "search":
				player.searchRoom();				
				break;
			case "situation":
				player.situation();
				break;
			case "take":
				player.takeItem(cmd);
				break;
			case "remove":
				player.removeItem(cmd);
				break;
			case "equip":
				player.equipItem(cmd);
				break;
			case "use":
				player.use(cmd);
				break;*/
			case "quit":
				System.out.println("You quit the game");
				System.exit(0);
			default:
			System.out.println("I don’t know what you mean");
			break;
		}
	}
}
