package model.characters;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import model.global.Fight;
import model.rooms.Room;
import model.items.Item;

public class Player extends Character {
	protected Room currentRoom;
	protected List<Item> bag;

	public Player(String name, Room currentRoom) {
		super(name, 100, 10);
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
	
	public void fightMonster() throws InterruptedException{
		if (this.currentRoom.getMonster()!= null) {
			new Fight(this.currentRoom.getMonster(),this).goFight();
		}
	}

	public void attack(Monster m){
		System.out.println("------------------\n");
		System.out.println("The player " + this.name + " attack the monster " + m.getName() + " with " + this.attackPoints + " points");
		m.setLifePoints(m.getLifePoints() - this.attackPoints);
		System.out.println("Now, the monster has " + m.getLifePoints() + " lifepoints.");
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
