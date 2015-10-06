package model.characters;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import controllers.CommandPrompt;

import model.global.Fight;
import model.rooms.Room;
import model.items.Arm;
import model.items.Item;
import model.items.Key;
import model.items.Potion;

/**
 * Player is the class to describe the properties and behaviors of a Player in the Dungeon
 * 
 * @author Aylin G., Amelie M., Sofian C., Laurent T.
 * 
 */
public class Player extends Character implements Fighter {
	protected String name;
	protected Room currentRoom;
	protected List<Item> bag;

	public Player(String name, Room currentRoom) {
		super(100, 10);
		this.name = name;
		this.currentRoom = currentRoom;
		this.bag = new ArrayList<Item>();
	}

	/**
	 * This function is called when the player enters in a room
	 * @throws InterruptedException
	 */
	public void enterInRoom() throws InterruptedException {
		System.out.println("I am currently in the Room " + this.currentRoom.getId());
		if (this.currentRoom.getMonster()!= null && this.currentRoom.getMonster().isAlive()) {
			System.out.println("Oh ! There is a monster in the room !");
		}
		if (this.currentRoom.getName()=="Trap"){
			this.lifePoints -= 35 ;
			System.out.println("You fell into a trap, you lost 35 lifePoints");
		}
		System.out.println("The room where you are is a " + this.currentRoom.getName() + " room");
		this.currentRoom.showNeighbours();
	}

	/**
	 * This function is called when the player wants to change room and verify if the door is not locked
	 * @param direction
	 */
	public void	changeRoom(String[] direction) {
		if (direction.length != 2) {
			System.out.println("You cannot go there !");
			System.out.println("----------------------\n");	
			return;
		}
		try {
			for (Map.Entry<Integer, Room> e : this.currentRoom.getDoors().entrySet()) {
				if (Integer.parseInt(direction[1]) == e.getKey())
				{
					if(!e.getValue().isLocked()) {
						this.currentRoom = e.getValue();
						//this.updateSituation();
						return;
					} else {
						System.out.println("The door is locked");
					}
				}
			}
			
		}
		catch (NumberFormatException e) {
			System.out.println("You must put a number!");
		}
		System.out.println("You cannot go there !");
		System.out.println("----------------------\n");	
	}

	/**
	 * This function is called when the player wants to describe his current room
	 */
	public void describeCurrentRoom() {
		System.out.println("Description of the current room :");
		System.out.println(this.currentRoom.getDescription());
		System.out.println();
	}

	/**
	 * This function is called when the user wants to show the help menu of the game
	 */
	public void showHelpMenu() {
		System.out.println("describe : To show the description of the current room.");
		System.out.println("drink <idPotion> : To drink a potion and get more life points.");
		System.out.println("go <idRoom> : To navigate in another room.");
		System.out.println("help : Here you are.");
		System.out.println("hit : To hit the monster in the current room.");
		System.out.println("quit : To quit the game.");
		System.out.println("remove <idItem> : To remove an item in your bag.");
		System.out.println("situation : To show the life points, the bag and the gold of the player.");
		System.out.println("search : To search through the room to possibly find a surprise item.");
		System.out.println("take : To take the surprise item of the current room.");
		System.out.println("use <idKey> : To use a key to open a room.");
		System.out.println();
	}

	/**
	 * 
	 * @throws InterruptedException
	 */
	public void fightMonster() throws InterruptedException{
		if (this.currentRoom.getMonster()!= null) {
			new Fight(this.currentRoom.getMonster(),this).goFight();
		}
	}

	/**
	 * This function is called when the player wants to search through his current room to see if surpriseItem is present or not
	 */
	public void searchInRoom() {		
		if(!this.currentRoom.isSearched()) {
			System.out.println("You decide to search throught the room...Heres' what you found:\n");
			this.currentRoom.setSearched(true);
			Item surpriseItem = this.currentRoom.getSurpriseItem();
			if (surpriseItem != null) {
				System.out.println("You have a " + surpriseItem.getName() + " in the room !\n");
			}
			if (this.currentRoom.getGold() != 0) {
				System.out.println("You found " + currentRoom.getGold() + " gold\n");
				this.gold += this.currentRoom.getGold();
			}
			if (surpriseItem == null && this.currentRoom.getGold() == 0) {
				System.out.println("Nothing\n");
			}
		} else {
			System.out.println("Sorry, you have already searched this room !\n");
		}
	}

	/**
	 * This function is called when the player wants to take an item available in his current room
	 */
	public void takeItem() {
		if(this.currentRoom.getSurpriseItem() != null) {
			this.bag.add(this.currentRoom.getSurpriseItem());
			System.out.println("Ok ! You put a " + this.currentRoom.getSurpriseItem().getName() + " in your bag :)\n");
			this.currentRoom.setSurpriseItem(null);
		} else {
			System.out.println("There's no item in this room.\n");
		}
	}
	
	/**
	 * This function is called when the player wants to remove an Item of his bag
	 * 
	 * @param id the identifiant of the item in the bag
	 */
	public void removeItem(int id) {
		if(id < this.bag.size()) {
			Item item = this.bag.get(id);
			this.bag.remove(id);
			System.out.println("Ok ! You just remove a " + item.getName());
		} else {
			System.out.println("There's no item at the index " + id);
		}
	}
	
	/**
	 * The function is called when the player wants to hit a Monster in a Room
	 * 
	 * @throws InterruptedException
	 */
	public void hit() throws InterruptedException {
		if (this.currentRoom.getMonster() != null) {
			int idArm = Integer.parseInt(new CommandPrompt().chooseArm(bag));
			
			if(idArm == 0) {
				this.attackPoints = 10;
			} else if(idArm < this.bag.size()+1 && idArm > 0) {
				Arm arm = (Arm) this.bag.get(idArm-1);
				this.attackPoints = arm.getAttackPoints();
			} else {
				System.out.println();
				System.out.println("It's not possible to choose that !\n");
			}
			new Fight(this.currentRoom.getMonster(), this).goFight();
		} else {
			System.out.println("There's no monster in this room.");
		}
	}

	/**
	 * attack is the function to attack an other Character
	 * 
	 * @param c the Character to attack
	 */
	@Override
	public void attack(Character c) {
		System.out.println("------------------\n");
		System.out.println("The player " + this.name + " attack the monster with " + this.attackPoints + " points");
		c.setLifePoints(c.getLifePoints() - this.attackPoints);
		System.out.println("Now, the monster has " + c.getLifePoints() + " lifepoints.");
		System.out.println();
	}

	/**
	 * This function is redefined to describe a Player with his lifePoints, attackPoints and his bag
	 */
	public String toString() {
		String situation = "";
		situation += this.name + " has got " + this.lifePoints + " lifePoints and " + this.attackPoints + " attackPoints.\n";
		situation += "He has in his bag :\n\n";
		int idItem = 0;
		for (Item i: this.bag) {
			if (i.getName().equals("Potion")) {
				Potion potion = (Potion) i;
				situation += idItem + " -> " +  potion.getName() + " (LifePoints: " + potion.getMoreLifePoints() + ")\n";
				idItem++;
			} else if (i.getName().equals("Key")) {
				Key key = (Key) i;
				situation += idItem + " -> " +  key.getName() + " (Room to open: " + key.getRoomToOpen().getId() + ")\n";
				idItem++;
			} else {
				Arm arm = (Arm) i;
				situation += idItem + " -> " +  arm.getName() + " (AttackPoints: " + arm.getAttackPoints() + ")\n";
				idItem++;
			}
		}
		situation += "\n-> " + this.gold + " gold.\n"; 
		return situation;
	}
	
	/**
	 * This function is called when the player wants to drink a potion
	 * 
	 * @param idItem the id of the potion in the bag
	 */
	public void drink(int idItem){
		if (this.bag.get(idItem).getName().equals("Potion")){
			Potion potion = (Potion) this.bag.get(idItem) ;
			this.lifePoints += potion.getMoreLifePoints();
			this.bag.remove(idItem);
		}
		else {
			System.out.println("You can't drink that ! \n");
		}
	}
	
	/**
	 * This function is called when the player wants to use a key
	 */
	public void useKey(int id) {
		if (this.bag.get(id).getName().equals("Key")) {
			Key key = (Key) this.bag.get(id);
			key.unLock();
			System.out.println("You unlock the room " + key.getRoomToOpen().getId() + " !\n");
			this.bag.remove(id);
		} else {
			System.out.println("You only can use keys !\n");
		}
	}

	public Room getCurrentRoom() {
		return currentRoom;
	}

	public void setCurrentRoom(Room currentRoom) {
		this.currentRoom = currentRoom;
	}

	public List<Item> getBag() {
		return bag;
	}

	public void setBag(List<Item> bag) {
		this.bag = bag;
	}
}
