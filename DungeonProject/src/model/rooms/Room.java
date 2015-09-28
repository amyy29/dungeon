package model.rooms;

import java.util.Map;
import java.util.HashMap;

public class Room {
	protected String name;
	protected int id;
	protected String description;
	protected Map<Integer, Room> doors = new HashMap<Integer, Room>();
	protected boolean hasRoom;

	public Room(int id) {
		this.id = id;
		this.description = "This is the Room " + id;
	}

	public Room(int id, Room previousRoom) {
		this.id = id;
		doors.put(previousRoom.getId(), previousRoom);
		this.description = "This is the Room " + id;
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

	public boolean isHasRoom() {
		return hasRoom;
	}

	public void setHasRoom(boolean hasRoom) {
		this.hasRoom = hasRoom;
	}
	
	
	
}
