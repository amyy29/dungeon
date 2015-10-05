package model.items;

import model.rooms.Room;

public class Key extends Item {
	
	Room roomToOpen;
	public Key(Room roomToOpen) {
		super("Key", "This key open the door " + roomToOpen.getId());
		this.roomToOpen = roomToOpen ;
	}

	public void unLock(){
		this.roomToOpen.setLocked(false);
	}

	public Room getRoomToOpen() {
		return roomToOpen;
	}

	public void setRoomToOpen(Room roomToOpen) {
		this.roomToOpen = roomToOpen;
	}
	
	
}
