package model.rooms;

public class NormalRoom extends Room {

	public NormalRoom(int id) {
		super(id);
		this.name = "NormalRoom";
	}

	public NormalRoom(int id, Room previousRoom) {
		super(id, previousRoom);
		this.name = "NormalRoom";
	}

}
