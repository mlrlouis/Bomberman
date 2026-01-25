package de.tha.prog2.praktikum.game.core;

import de.tha.prog2.praktikum.game.gameobjects.HandOperatedBomberman;
import de.tha.prog2.praktikum.game.ui.ConsoleUI;

public class GameImpl extends Game {
	private ConsoleUI consoleUI;
	private HandOperatedBomberman bomberman;

	public GameImpl(GameState gState, ConsoleUI ui) {
		super(gState);
		this.consoleUI = ui;
		this.bomberman = (HandOperatedBomberman) gState.getBoard().getGameObjectSet().getObject(0);
		System.out.println("Konstruktor GameImpl");
	}

	@Override
	protected void processInput() {
		System.out.println("processInput");
		bomberman.setMoveCommand(consoleUI.getCommand());
	}

	@Override
	protected void render() {
		FlattenedBoard board = getState().getBoard().flatten();
		consoleUI.render(board);
	}

}
