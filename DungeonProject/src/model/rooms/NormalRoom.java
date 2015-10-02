package model.rooms;

import model.items.Item;

public class NormalRoom extends Room {
	
	Item surpriseItem;
	
	public NormalRoom(int id) {
		super(id);
		this.name = "Normal";
	}
	
	
	
	public NormalRoom(int id, Room previousRoom) {
		super(id, previousRoom);

	}



	public NormalRoom(int id, Room previousRoom, Item surpriseItem) {
		super(id, previousRoom);
		this.name = "Normal";
		this.surpriseItem = surpriseItem;
	}

}
