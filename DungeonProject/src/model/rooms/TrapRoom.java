package model.rooms;

import model.characters.Monster;

public class TrapRoom extends Room {

	public TrapRoom(int id, Room previousRoom, Monster m) {
		super(id, previousRoom);
		this.name = "Trap";
	}

}

