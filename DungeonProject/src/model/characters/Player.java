package model.characters;

import model.rooms.Room;

public class Player {
	protected String name;
	protected int lifePoints;
	protected Room currentRoom;
	// PAS OUBLIER armes, or
	
	
	public Player(String name, Room currentRoom) {
		super();
		this.name = name;
		this.currentRoom = currentRoom;
		this.lifePoints = 100;
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
	
	public void WhereIsPlayer() {
		System.out.println("I am in the room"+this.currentRoom.getId());
		this.currentRoom.showNeighbour();
	}

	

}
