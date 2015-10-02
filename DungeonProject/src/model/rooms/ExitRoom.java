package model.rooms;

import model.characters.Monster;

public class ExitRoom extends Room implements IRoom {
	public ExitRoom(int id) {
		super(id);
		this.name = "Exit";
	}
	
	public ExitRoom(int id, Room previousRoom) {
		super(id, previousRoom);
		this.name = "Exit";
	}

	@Override
	public void enter() {
		
		
	}
}
