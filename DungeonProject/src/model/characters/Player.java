package model.characters;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import model.global.Fight;
import model.rooms.Room;
import model.items.Item;

public class Player extends Character implements Fighter {
	protected String name;
	protected Room currentRoom;
	protected List<Item> bag;

	public Player(String name, Room currentRoom) {
		super(100, 10);
		this.name = name;
		this.currentRoom = currentRoom;
		this.bag = new ArrayList<Item>();
	}

	public void enterInRoom() throws InterruptedException {
		System.out.println("----------------------\n");	
		System.out.println("I am currently in the Room " + this.currentRoom.getId());
		this.fightMonster();
		System.out.println("The room where you are is a " + this.currentRoom.getName() + " room");
		this.currentRoom.showNeighbours();
	}

	public void	changeRoom(String[] direction) {
		if (direction.length != 2) {
			System.out.println("You cannot go there !");
			System.out.println("----------------------\n");	
			return;
		}
		try {
			for (Map.Entry<Integer, Room> e : this.currentRoom.getDoors().entrySet()) {
				if (Integer.parseInt(direction[1]) == e.getKey())
				{
					this.currentRoom = e.getValue();
					//this.updateSituation();
					return;
				}
			}
		}
		catch (NumberFormatException e)
		{
			System.out.println("You must put a number!");
		}
		System.out.println("You cannot go there !");
		System.out.println("----------------------\n");	
	}
	
	public void describeCurrentRoom() {
		System.out.println("Description of the current room :");
		System.out.println(this.currentRoom.getDescription());
		System.out.println();
	}
	
	public void showHelpMenu() {
		System.out.println("describe : To show the description of the current room.");
		System.out.println("go <idRoom> : To navigate in another room.");
		System.out.println("help : Here you are !");
		System.out.println("quit : To quit the game.");
		System.out.println();
	}
	
	public void fightMonster() throws InterruptedException{
		if (this.currentRoom.getMonster()!= null) {
			new Fight(this.currentRoom.getMonster(),this).goFight();
		}
	}
	
	@Override
	public void attack(Character c) {
		System.out.println("------------------\n");
		System.out.println("The player " + this.name + " attack the monster with " + this.attackPoints + " points");
		c.setLifePoints(c.getLifePoints() - this.attackPoints);
		System.out.println("Now, the monster has " + c.getLifePoints() + " lifepoints.");
		System.out.println();
	}
	
	public Room getCurrentRoom() {
		return currentRoom;
	}

	public void setCurrentRoom(Room currentRoom) {
		this.currentRoom = currentRoom;
	}

	public List<Item> getBag() {
		return bag;
	}

	public void setBag(List<Item> bag) {
		this.bag = bag;
	}
	
}
