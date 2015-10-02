package model.rooms;

import model.characters.Monster;
import model.global.Dungeon;
import model.global.GameState;

public class FightRoom extends Room implements IRoom {
	
	protected Monster monster;
	
	public FightRoom(int id, Room previousRoom, Monster monster) {
		super(id, previousRoom);
		this.monster=monster;
	}

	@Override
	public void enter() {
		Dungeon.getInstance().setGameState(GameState.FIGHT);
		
	}
	
	



}
