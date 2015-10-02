package model.global;

import model.characters.Monster;
import model.items.Potion;
import model.items.Weapon;
import model.rooms.*;

import java.util.*;

public class DungeonGenerator {
	protected int idMax;
	protected boolean hasExit;
	private Map<Integer, Room> dungeonMap = new HashMap<Integer,Room>();
	private Map<Integer, Room> tmpMap = new HashMap<Integer, Room>();

	public DungeonGenerator() {
		this.idMax = 0;
		this.createMap();
	}

	public Map<Integer, Room> getMap() {
		return dungeonMap;
	}

	public void copyTmpMapIntoDungeonMap() {
		for (Map.Entry<Integer, Room> e : this.tmpMap.entrySet()) {
			if (e.getKey() != null && e.getValue() != null)
				this.dungeonMap.put(e.getKey(), e.getValue());
		}
		tmpMap.clear();
		return;
	}

	public Room randomRoom(int id, Room oldRoom) {
		Monster m;
		int randomInt = new Random().nextInt(11);
		
		if (randomInt < 3) {
			m = new Monster("Gnan gnan",50,5);
		} else {
			m = null;
		}

		if (randomInt == 2  || randomInt == 5) {
			return new TrapRoom(id, oldRoom, m);
		}
		
		if (randomInt == 4) {
			int randomLifePoints = new Random().nextInt(6)*10;
			Potion potion =  new Potion(randomLifePoints);
			return new NormalRoom(id, oldRoom, potion);
		}	
		
		if (randomInt == 7) {
			Weapon weapon = new Weapon();
			return new NormalRoom(id, oldRoom, weapon);
		}
		
		if (randomInt == 10 && this.hasExit == false) {
			this.hasExit = true;
			return new ExitRoom(id, oldRoom);
		}
		
		return new NormalRoom(id, oldRoom);
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
				tmpMap.put(this.idMax, newRoom);
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
				Room exit = new ExitRoom(this.idMax, r);
				tmpMap.put(this.idMax, exit);
				r.getDoors().put(this.idMax, exit);
			}
		}
		copyTmpMapIntoDungeonMap();
	}

	public void createMap() {
		Room entrance = new EntranceRoom(0);
		this.dungeonMap.put(entrance.getId(), entrance);
		this.idMax = 1;
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

	public int getIdMax() {
		return idMax;
	}

	public Map<Integer, Room> getDungeonMap() {
		return dungeonMap;
	}

	public void setDungeonMap(Map<Integer, Room> dungeonMap) {
		this.dungeonMap = dungeonMap;
	}
	
	
}