package model.rooms;

import model.characters.Monster;

public class TrapRoom extends Room {
	
	public TrapRoom (int id) {
		super(id);
		this.name = "Trap";
	}
	
	public TrapRoom(int id, Monster m) {
		super(id, m);
		this.name = "Trap";
	}

	public TrapRoom(int id, Room previousRoom, Monster m) {
		super(id, previousRoom, m);
		this.name = "Trap";
	}

}

