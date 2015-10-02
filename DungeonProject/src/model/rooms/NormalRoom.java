package model.rooms;

import model.characters.Monster;
import model.items.Item;

public class NormalRoom extends Room {
	
	Item surpriseItem;
	
	public NormalRoom(int id) {
		super(id);
		this.name = "Normal";
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
