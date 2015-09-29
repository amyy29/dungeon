package controllers;

import model.characters.Player;
import java.util.Scanner;

public class CommandPrompt {
	
	protected Scanner sc;
	protected String command;
	
	public void interpretCommand(Player player) {
		
		System.out.println("What do you want to do?");	
		System.out.print("> ");
		this.sc = new Scanner(System.in);
		this.command = sc.nextLine();			
		System.out.println("----------------------\n");	
		
		if (this.command.length() == 0) {
			System.out.println("You can put 'go *number of the door' or 'search'");
			return;
		}
		String[] cmd = this.command.split(" ");
		switch (cmd[0]){
			case "go":
				player.changeRoom(cmd);
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
