package test;

import static org.junit.Assert.*;
import game.Game;

import org.junit.Test;

public class GameTest {

	@Test
	public void test() {
		Game gm = new Game();
		try {
			gm.startFile("resources/example.txt");
			assertTrue(true);
		} catch (Exception e) {
			fail();
		}
	}

}
