package model.rooms;

import model.characters.Monster;

public class ExitRoom extends Room {
	public ExitRoom(int id) {
		super(id);
		this.name = "Exit";
	}
	
	public ExitRoom(int id, Room previousRoom) {
		super(id, previousRoom);
		this.name = "Exit";
	}

	public ExitRoom(int id, Room previousRoom, Monster m) {
		super(id, previousRoom, m);
		this.name = "Exit";
	}
}
