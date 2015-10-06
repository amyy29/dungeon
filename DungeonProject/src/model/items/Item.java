package model.items;

/**
 * Item is class used to describe an Item n this game
 * 
 * @author Aylin G., Amelie M., Sofian C., Laurent T.
 * 
 */
public abstract class Item {
	protected String name;
	protected String description;
	
	public Item(String name, String description) {
		this.name = name;
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
