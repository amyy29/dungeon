package model.rooms;

import model.characters.Monster;

public class NormalRoom extends Room {

	public NormalRoom(int id) {
		super(id);
		this.name = "NormalRoom";
	}
	
	public NormalRoom(int id, Room previousRoom) {
		super(id, previousRoom);
		this.name = "NormalRoom";
	}

	public NormalRoom(int id, Room previousRoom, Monster m) {
		super(id, previousRoom, m);
		this.name = "NormalRoom";
	}

}
