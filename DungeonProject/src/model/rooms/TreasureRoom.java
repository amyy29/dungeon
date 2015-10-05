package model.rooms;

import java.util.Random;

import model.characters.Monster;

public class TreasureRoom extends Room {
	
	protected int gold;

	public TreasureRoom(int id) {
		super(id);
		this.name = "Treasure";
		this.initGold();
	}

	public TreasureRoom(int id, Monster monster) {
		super(id, monster);
		this.name = "Treasure";
		this.initGold();
	}

	public TreasureRoom(int id, Room previousRoom, Monster monster) {
		super(id, previousRoom, monster);
		this.name = "Treasure";
		this.initGold();
	}

	public TreasureRoom(int id, Room previousRoom) {
		super(id, previousRoom);
		this.name = "Treasure";
		this.initGold();
	}
	
	public void initGold () {
		this.gold = new Random().nextInt(11) * 10;
	}
	
}
