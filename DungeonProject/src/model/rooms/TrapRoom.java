package model.rooms;

import model.characters.Monster;

/**
 * TrapRoom is a subclass of an Room used that represent a trap
 * 
 * @author Aylin G., Amelie M., Sofian C., Laurent T.
 * 
 */
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

