package model.characters;

import java.util.Map;

import model.rooms.Room;

public class Player {
	protected String name;
	protected int lifePoints;
	protected Room currentRoom;
	private String command;
	
	public Player(String name, Room currentRoom) {
		super();
		this.name = name;
		this.currentRoom = currentRoom;
		this.lifePoints = 100;
	}
	
	public void showCurrentRoomInfos() {
		System.out.println("----------------------\n");	
		System.out.println("I am currently in the Room " + this.currentRoom.getId());
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

	public String getCommand() {
		return command;
	}

}
