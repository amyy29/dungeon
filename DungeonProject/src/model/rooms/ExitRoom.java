package model.rooms;

public class ExitRoom extends Room {
	public ExitRoom(int id) {
		super(id);
		this.name = "Exit";
	}

	public ExitRoom(int id, Room previousRoom) {
		super(id, previousRoom);
		this.name = "Exit";
	}
}
