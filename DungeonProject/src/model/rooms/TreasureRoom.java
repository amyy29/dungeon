package model.rooms;

import model.items.Item;

public class TreasureRoom extends Room {
	protected Item item;

	public TreasureRoom (int id) {
		super(id);
		this.name = "TreasureRoom ";
	}
	
	public TreasureRoom (int id, Room previousRoom, Item item) {
		super(id, previousRoom);
		this.name = "TreasureRoom ";
		this.item = item;
	}

	public Item getItem() {
		return item;
	}



}
