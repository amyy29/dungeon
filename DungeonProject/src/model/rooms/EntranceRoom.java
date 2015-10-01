package model.rooms;

import model.items.Item;

public class EntranceRoom extends Room {
	
	Item surpriseItem;
	
	public EntranceRoom(int id) {
		super(id);
		this.name = "Entrance";
		this.surpriseItem = null;
	}
	
	public EntranceRoom(int id, Item surpriseItem) {
		super(id);
		this.name = "Entrance";
		this.surpriseItem = surpriseItem;
	}
}
