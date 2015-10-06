package model.items;

import model.rooms.Room;

/**
 * Key is a subclass of an Item used to open a room locked
 * 
 * @author Aylin G., Amelie M., Sofian C., Laurent T.
 * 
 */
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
