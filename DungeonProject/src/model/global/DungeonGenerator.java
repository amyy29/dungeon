package model.global;

import model.characters.Monster;
import model.items.Potion;
import model.rooms.*;

import java.util.*;

import controllers.GameChoiceEnum;

public class DungeonGenerator {
	protected int idMax;
	protected boolean hasExit;
	private Map<Integer, Room> dungeonMap = new HashMap<Integer,Room>();
	private Map<Integer, Room> tmpMap = new HashMap<Integer, Room>();

	public DungeonGenerator(GameChoiceEnum gameChoice) {
		if (gameChoice == GameChoiceEnum.RANDOM) {
			this.createRandomMap();
		}
		if (gameChoice == GameChoiceEnum.CLASSIC) {
			this.createClassicMap();
		}
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
		
	/*	if (randomInt == 7) {
			Weapon weapon = new Weapon();
			return new NormalRoom(id, oldRoom, weapon);
		}
		*/
		
		if (randomInt == 10 && this.hasExit == false) {
			this.hasExit = true;
			return new ExitRoom(id, oldRoom, m);
		}
		
		return new NormalRoom(id, oldRoom, m);
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

	public void createRandomMap() {
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
	
	public void createClassicMap() {
		
		Room room0 = new EntranceRoom(0);
		Room room1 = new NormalRoom(1);
		Room room2 = new TrapRoom(2);
		Room room3 = new NormalRoom(3);
		Room room4 = new NormalRoom(4);
		Room room5 = new TrapRoom(5);
		Room room6 = new NormalRoom(6);
		Room room7 = new ExitRoom(7);
		
		this.dungeonMap.put(0, room0);
		this.dungeonMap.put(1, room1);
		this.dungeonMap.put(2, room2);
		this.dungeonMap.put(3, room3);
		this.dungeonMap.put(4, room4);
		this.dungeonMap.put(5, room5);
		this.dungeonMap.put(6, room6);
		this.dungeonMap.put(7, room7);
		
		room0.getDoors().put(room1.getId(), room1);
		room0.getDoors().put(room2.getId(), room2);
		room0.getDoors().put(room3.getId(), room3);
		
		room1.getDoors().put(room0.getId(), room0);
		room1.getDoors().put(room5.getId(), room5);
		
		room2.getDoors().put(room0.getId(), room0);
		room2.getDoors().put(room4.getId(), room4);
		
		room3.getDoors().put(room0.getId(), room0);
		room3.getDoors().put(room4.getId(), room5);
		room3.getDoors().put(room5.getId(), room0);
		room3.getDoors().put(room6.getId(), room5);
		
		room4.getDoors().put(room2.getId(), room2);
		room4.getDoors().put(room3.getId(), room3);
		room4.getDoors().put(room7.getId(), room7);
		
		room5.getDoors().put(room1.getId(), room1);
		room5.getDoors().put(room3.getId(), room3);
		
		room6.getDoors().put(room3.getId(), room3);
		room6.getDoors().put(room7.getId(), room7);
		
		room7.getDoors().put(room4.getId(), room4);
		room4.getDoors().put(room6.getId(), room6);
		
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
	
	public Map<Integer, Room> getMap() {
		return dungeonMap;
	}
	
	
}