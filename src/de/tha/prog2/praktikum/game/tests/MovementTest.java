package de.tha.prog2.praktikum.game.tests;



import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.tha.prog2.praktikum.game.core.FlattenedBoard;
import de.tha.prog2.praktikum.game.core.GameObjectSet;
import de.tha.prog2.praktikum.game.gameobjects.HandOperatedBomberman;
import de.tha.prog2.praktikum.game.util.MoveCommand;
import de.tha.prog2.praktikum.game.util.XY;

class MovementTest {
	private GameObjectSet gos;
	private FlattenedBoard fBoard;
	private HandOperatedBomberman bomber;

	@BeforeEach
	public void setUpBoard() {
		gos = new GameObjectSet();
		bomber = new HandOperatedBomberman(new XY(0, 0));

		gos.addObject(bomber);

		fBoard = new FlattenedBoard(new XY(5, 5), gos);
	}

	@Test
	public void moveBombermanAround() {
		assertTrue(bomber.getLocation().getX() == 0 && bomber.getLocation().getY() == 0, "start location: Bomberman sollte auf (0, 0) sein");

		bomber.setMoveCommand(MoveCommand.RIGHT);
		bomber.nextStep(fBoard);
		assertTrue(bomber.getLocation().getX() == 1 && bomber.getLocation().getY() == 0, "move right: Bomberman sollte auf (1, 0) sein");

		bomber.setMoveCommand(MoveCommand.DOWN);
		bomber.nextStep(fBoard);
		assertTrue(bomber.getLocation().getX() == 1 && bomber.getLocation().getY() == 1, "move down: Bomberman sollte auf (1, 1) sein");

		bomber.setMoveCommand(MoveCommand.LEFT);
		bomber.nextStep(fBoard);
		assertTrue(bomber.getLocation().getX() == 0 && bomber.getLocation().getY() == 1, "move left: Bomberman sollte auf (0, 1) sein");

		bomber.setMoveCommand(MoveCommand.UP);
		bomber.nextStep(fBoard);
		assertTrue(bomber.getLocation().getX() == 0 && bomber.getLocation().getY() == 0, "move up: Bomberman sollte auf (0, 0) sein");
	}

}
