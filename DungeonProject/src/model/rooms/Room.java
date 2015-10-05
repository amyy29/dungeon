package model.rooms;

import java.util.Map;
import java.util.HashMap;

import model.characters.Monster;
import model.items.Item;

public abstract class Room {
	protected String name;
	protected int id;
	protected String description;
	protected Map<Integer, Room> doors = new HashMap<Integer, Room>();
	protected boolean roomExist;
	protected boolean searched;
	protected Monster monster;
	protected boolean locked;
	protected Item surpriseItem;
	protected int gold;

	public Room(int id) {
		this.id = id;
		this.description = "This is the Room " + id;
	}

	public Room(int id, Room previousRoom) {
		this.id = id;
		doors.put(previousRoom.getId(), previousRoom);
		this.description = "This is the Room " + id;
	}
	
	public Room(int id, Monster monster) {
		this.id = id;
		this.monster = monster;
	}

	public Room(int id, Room previousRoom, Monster monster) {
		this.id = id;
		doors.put(previousRoom.getId(), previousRoom);
		this.description = "This is the Room " + id;
		this.monster = monster;
	}

	public void showNeighbours() {
		System.out.print("This door will lead to rooms : ");
		for (Map.Entry<Integer, Room> e : doors.entrySet())
			System.out.print(e.getKey() + ", ");
		System.out.println();
	}

	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public Map<Integer, Room> getDoors() {
		return doors;
	}

	public boolean isRoomExist() {
		return roomExist;
	}

	public void setRoomExist(boolean roomExist) {
		this.roomExist = roomExist;
	}
	
	public void setSearched(boolean searched) {
		this.searched = searched;
	}

	public Monster getMonster() {
		return monster;
	}

	public void setMonster(Monster monster) {
		this.monster = monster;
	}

	public boolean isSearched() {
		return searched;
	}

	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	public Item getSurpriseItem() {
		return surpriseItem;
	}

	public void setSurpriseItem(Item surpriseItem) {
		this.surpriseItem = surpriseItem;
	}

	public int getGold() {
		return gold;
	}

	public void setGold(int gold) {
		this.gold = gold;
	}
	
	

}
