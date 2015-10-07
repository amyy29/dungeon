package model.global;

import model.characters.Monster;
import model.characters.MonsterType;
import model.items.Arm;
import model.items.ArmType;
import model.items.Key;
import model.items.Potion;
import model.rooms.*;

import java.util.*;

/**
 * DungeonGenerator is the class used to generate a dungeonMap
 * 
 * @author Aylin G., Amelie M., Sofian C., Laurent T.
 * 
 */
public class DungeonGenerator {
	protected int idMax;
	protected boolean hasExit;
	private Map<Integer, Room> dungeonMap = new HashMap<Integer,Room>();
	private Map<Integer, Room> tmpMap = new HashMap<Integer, Room>();

	/**
	 * This function copy the tmpMap into the dungeonMap
	 */
	public void copyTmpMapIntoDungeonMap() {
		for (Map.Entry<Integer, Room> e : this.tmpMap.entrySet()) {
			if (e.getKey() != null && e.getValue() != null)
				this.dungeonMap.put(e.getKey(), e.getValue());
		}
		tmpMap.clear();
		return;
	}

	/**
	 * This function is used to generate a randomRoom
	 * It could be a TrapRoom, NormalRoom, TreasureRoom or ExitRoom with Monster or a surprise Item
	 * 
	 * @param id
	 * @param oldRoom
	 * @return the Room created
	 */
	public Room randomRoom(int id, Room oldRoom) {
		Monster m;
		int randomInt = new Random().nextInt(11);
		int randomInt2 = new Random().nextInt(11);

		if (randomInt < 3) {
			if (randomInt2 < 3) {
				m = new Monster(MonsterType.SQUELETON);
			} else if (randomInt2 >= 3 && randomInt2 < 6) {
				m = new Monster(MonsterType.ZOMBIE);
			} else {
				m = new Monster(MonsterType.DEMON);
			}
		} else {
			m = null;
		}

		if (randomInt == 2  || randomInt == 4) {
			return new TrapRoom(id, oldRoom, m);
		}

		if (randomInt == 3 || randomInt == 6) {
			int randomLifePoints = (new Random().nextInt(6)+1)*10;
			Potion potion =  new Potion(randomLifePoints);
			return new NormalRoom(id, oldRoom, potion);
		}

		if (randomInt == 5 || randomInt == 7) {
			if (randomInt2 < 3) {
				return new NormalRoom(id, oldRoom, new Arm(ArmType.WEAPON));
			} else if (randomInt2 >= 3 && randomInt2 < 6) {
				return new NormalRoom(id, oldRoom, new Arm(ArmType.LASER));
			} else {
				return new NormalRoom(id, oldRoom, new Arm(ArmType.BOMB));
			}
		}

		if (randomInt == 8 || randomInt == 9) {
			return new TreasureRoom(id, oldRoom);
		}

		if (randomInt == 10 && this.hasExit == false) {
			this.hasExit = true;
			return new ExitRoom(id, oldRoom, m);
		}

		return new NormalRoom(id, oldRoom, m);
	}

	/**
	 * This function is used to create a linked room
	 * 
	 * @param room
	 */
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

	/**
	 * This function is called when the program needs to create an ExitRoom
	 */
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

	/**
	 * This function is used to create the randomMap
	 */
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

	/**
	 * This function is used to create the classicMap
	 * 
	 * @param level the level to create
	 */
	public void createClassicMap(int level) {

		Monster squeleton = new Monster(MonsterType.SQUELETON);
		Monster zombie = new Monster(MonsterType.ZOMBIE);
		Monster demon = new Monster(MonsterType.DEMON);
		Arm weapon = new Arm(ArmType.WEAPON);
		Arm laser = new Arm(ArmType.LASER);
		Potion potion = new Potion(20);
		Potion potion2 = new Potion(100);

		if (level == 1) {
			Room room0 = new EntranceRoom(0);
			Room room1 = new NormalRoom(1, squeleton);
			Room room2 = new NormalRoom(2, weapon);
			Room room3 = new TrapRoom(3);
			Room room4 = new TrapRoom(4);
			Room room5 = new NormalRoom(5, zombie);
			Room room6 = new NormalRoom(6);
			Room room7 = new ExitRoom(7);
			room2.setLocked(true);
			
			Key keyForRoom2 = new Key(room2);
			room0.setSurpriseItem(keyForRoom2);

			this.dungeonMap.put(0, room0);
			this.dungeonMap.put(1, room1);
			this.dungeonMap.put(2, room2);
			this.dungeonMap.put(3, room3);
			this.dungeonMap.put(4, room4);
			this.dungeonMap.put(5, room5);
			this.dungeonMap.put(6, room6);
			this.dungeonMap.put(7, room7);

			room0.getDoors().put(room1.getId(), room1);

			room1.getDoors().put(room0.getId(), room0);
			room1.getDoors().put(room2.getId(), room2);
			room1.getDoors().put(room3.getId(), room3);

			room2.getDoors().put(room1.getId(), room1);
			room2.getDoors().put(room4.getId(), room4);

			room3.getDoors().put(room1.getId(), room1);

			room4.getDoors().put(room2.getId(), room2);
			room4.getDoors().put(room5.getId(), room5);
			room4.getDoors().put(room7.getId(), room7);

			room5.getDoors().put(room1.getId(), room1);
			room5.getDoors().put(room4.getId(), room4);
			room5.getDoors().put(room5.getId(), room5);

			room6.getDoors().put(room5.getId(), room5);
			room6.getDoors().put(room7.getId(), room7);

			room7.getDoors().put(room4.getId(), room4);
			room7.getDoors().put(room6.getId(), room6);
		}
		if (level==2) {
			Room room0 = new EntranceRoom(0, potion2);
			Room room1 = new NormalRoom(1);
			Room room2 = new NormalRoom(2, laser);
			Room room3 = new NormalRoom(3);
			Room room4 = new NormalRoom(4);
			Room room5 = new NormalRoom(5);
			Room room6 = new NormalRoom(6,squeleton);
			Room room7 = new NormalRoom(7,demon);
			Room room8 = new TrapRoom(8);
			Room room9 = new NormalRoom(9);
			Room room10 = new NormalRoom(10);
			Room room12 = new ExitRoom(12);
			Key keyForRoom12 = new Key(room12);
			Room room11 = new NormalRoom(11,keyForRoom12);	
			Room room13 = new NormalRoom(13,potion);

			this.dungeonMap.put(0, room0);
			this.dungeonMap.put(1, room1);
			this.dungeonMap.put(2, room2);
			this.dungeonMap.put(3, room3);
			this.dungeonMap.put(4, room4);
			this.dungeonMap.put(5, room5);
			this.dungeonMap.put(6, room6);
			this.dungeonMap.put(7, room7);
			this.dungeonMap.put(8, room8);
			this.dungeonMap.put(9, room9);
			this.dungeonMap.put(10, room10);
			this.dungeonMap.put(11, room11);
			this.dungeonMap.put(12, room12);
			this.dungeonMap.put(13, room13);

			room0.getDoors().put(room1.getId(), room1);

			room1.getDoors().put(room0.getId(), room0);
			room1.getDoors().put(room2.getId(), room2);	

			room2.getDoors().put(room1.getId(), room1);
			room2.getDoors().put(room3.getId(), room3);

			room3.getDoors().put(room2.getId(), room2);
			room3.getDoors().put(room4.getId(), room4);

			room4.getDoors().put(room3.getId(), room3);
			room4.getDoors().put(room5.getId(), room5);
			room4.getDoors().put(room6.getId(), room6);

			room5.getDoors().put(room4.getId(), room4);
			room5.getDoors().put(room7.getId(), room7);

			room6.getDoors().put(room4.getId(), room4);
			room6.getDoors().put(room9.getId(), room9);

			room7.getDoors().put(room5.getId(), room5);
			room7.getDoors().put(room8.getId(), room8);

			room8.getDoors().put(room7.getId(), room7);
			room8.getDoors().put(room10.getId(), room10);

			room9.getDoors().put(room6.getId(), room6);
			room9.getDoors().put(room11.getId(), room11);

			room10.getDoors().put(room8.getId(), room8);
			room10.getDoors().put(room12.getId(), room12);

			room11.getDoors().put(room9.getId(), room9);
			room11.getDoors().put(room13.getId(), room13);

			room12.getDoors().put(room10.getId(), room10);
			room12.getDoors().put(room13.getId(), room13);
			room12.setLocked(true);

			room13.getDoors().put(room11.getId(), room11);
			room13.getDoors().put(room12.getId(), room12);
		}
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