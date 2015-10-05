package model.rooms;

import model.items.Item;

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
