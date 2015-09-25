package model.global;

import static org.junit.Assert.*;

import org.junit.Test;

public class DungeonTest {
	
	Dungeon dungeon;

	@Test
	public void dummyTest() {
		dungeon = new Dungeon("test");
		assertEquals("test", dungeon.getName());
	}

}
