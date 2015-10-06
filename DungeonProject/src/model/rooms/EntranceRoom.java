package model.rooms;

import model.items.Item;

/**
 * EntranceRoom is a subclass of an Room used that represent an entrance
 * 
 * @author Aylin G., Amelie M., Sofian C., Laurent T.
 * 
 */
public class EntranceRoom extends Room {

	public EntranceRoom(int id) {
		super(id);
		this.name = "Entrance";
	}
	
	public EntranceRoom(int id, Item surpriseItem) {
		super(id);
		this.name = "Entrance";
		this.surpriseItem = surpriseItem;
	}
}
