package model.global;

import model.rooms.*;
import java.util.*;

public class DungeonGenerator {
	protected int idMax;
	protected boolean hasExit;
	private Map<Integer, Room> dungeonMap = new HashMap<Integer,Room>();
	private Map<Integer, Room> tmpName = new HashMap<Integer, Room>();

	public DungeonGenerator() {
		this.idMax = 1;
		this.createMap();
	}

	public Map<Integer, Room> getMap() {
		return dungeonMap;
	}

	public void copyTmpMapIntoDungeonMap() {
		for (Map.Entry<Integer, Room> e : this.tmpName.entrySet()) {
			if (e.getKey() != null && e.getValue() != null)
				this.dungeonMap.put(e.getKey(), e.getValue());
		}
		tmpName.clear();
		return;
	}

	public Room randomRoom(int id, Room oldRoom) {
		/*
		int randomInt = new Random().nextInt(11);
		if (randomInt % 2 != 0)
			m.chooseName();		
		else
			m = null;
		if (randomInt == 10 && this.hasExit == false)
		{
			this.hasExit = true;
			return (new Exit(id, oldRoom, m));
		}
		if (r == 2 || r == 6)
			return (new Trap(id, oldRoom, m));

		if (r == 7 || r == 4)
			return (new TreasureRoom(id, oldRoom, m));*/
		return (new NormalRoom(id, oldRoom));
	}

	public void createLinkedRoom(Room room) {
		int randomInt;
		if (this.idMax == 1) {
			randomInt = 4;
		}
		else {
			randomInt = new Random().nextInt(3);
		}
		if (randomInt == 0) {
			return;
		}

		int i = 0;
		if (!room.isRoomExist()) {
			while (i != randomInt) {
				Room newRoom = randomRoom(this.idMax, room);
				room.getDoors().put(this.idMax, newRoom);
				tmpName.put(this.idMax, newRoom);
				this.idMax++;
				i++;
			}
		}
	}

	public void	createExit() {
		for (Map.Entry<Integer, Room> e : dungeonMap.entrySet()) {
			int i = e.getKey();
			if (i == this.idMax - 1) {
				Room r = e.getValue();
				this.idMax++;
				Room exit = new ExitRoom(this.idMax, r);
				tmpName.put(this.idMax, exit);
				r.getDoors().put(this.idMax, exit);
			}
		}
		copyTmpMapIntoDungeonMap();
	}

	public void createMap() {
		Room entrance = new EntranceRoom(0);
		this.dungeonMap.put(entrance.getId(), entrance);
		for (int i=0; i<2; i++) {
			Iterator<Map.Entry<Integer, Room>> it = dungeonMap.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry<Integer, Room> elem = it.next();
				Room r = elem.getValue();
				this.createLinkedRoom(r);
				r.setRoomExist(true);
			}
			copyTmpMapIntoDungeonMap();
		}
		if (!hasExit) {
			createExit();
		}
	}
	
	public int getMaxId() {
		return idMax;
	}
}