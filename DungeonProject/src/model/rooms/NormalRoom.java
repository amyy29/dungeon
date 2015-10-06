package model.rooms;

import model.characters.Monster;
import model.items.Item;

/**
 * NormalRoom is a subclass of an Room used that represent an normal room
 * 
 * @author Aylin G., Amelie M., Sofian C., Laurent T.
 * 
 */
public class NormalRoom extends Room {

	public NormalRoom(int id) {
		super(id);
		this.name = "Normal";
	}
	
	public NormalRoom(int id, Monster m) {
		super(id, m);
		this.name = "Normal";
		this.surpriseItem = null;
	}
	
	public NormalRoom(int id, Item surpriseItem) {
		super(id);
		this.name = "Normal";
		this.surpriseItem = surpriseItem;
	}
	
	public NormalRoom(int id, Room previousRoom, Monster m) {
		super(id, previousRoom, m);
		this.name = "Normal";
		this.surpriseItem = null;
	}
	
	public NormalRoom(int id, Room previousRoom, Item surpriseItem) {
		super(id, previousRoom, null);
		this.name = "Normal";
		this.surpriseItem = surpriseItem;
	}

}
