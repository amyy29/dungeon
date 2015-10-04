package model.characters;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import model.global.Fight;
import model.rooms.Room;
import model.items.Item;

public class Player {
	protected String name;
	protected int lifePoints;
	protected Room currentRoom;
	protected int attackPoints;
	protected List<Item> bag;

	public Player(String name, Room currentRoom) {
		super();
		this.name = name;
		this.currentRoom = currentRoom;
		this.lifePoints = 100;
		this.attackPoints = 10;
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

	public int getLifePoints() {
		return lifePoints;
	}


	public void setLifePoints(int lifePoints) {
		this.lifePoints = lifePoints;
	}


	public Room getCurrentRoom() {
		return currentRoom;
	}


	public void setCurrentRoom(Room currentRoom) {
		this.currentRoom = currentRoom;
	}


	public String getName() {
		return name;
	}


	public void attack(Monster m){
		System.out.println("------------------\n");
		System.out.println("The player " + this.name + " attack the monster " + m.getName() + " with " + this.attackPoints + " points");
		m.setLifePoints(m.getLifePoints() - this.attackPoints);
		System.out.println("Now, the monster has " + m.getLifePoints() + " lifepoints.");
		System.out.println();
	}

	public boolean isAlive(){
		return this.lifePoints > 0;
	}

	public void fightMonster() throws InterruptedException{
		if (this.currentRoom.getMonster()!= null) {
			new Fight(this.currentRoom.getMonster(),this).goFight();
		}
	}
}
