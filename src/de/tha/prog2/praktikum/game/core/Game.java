package de.tha.prog2.praktikum.game.core;

public abstract class Game {
	private GameState state;
	private boolean continueGame = true;

	public Game(GameState state) {
		this.state = state;
	}

	public void run() {
		while (continueGame) {
			render();
			processInput();
			update();
		}
	}

	protected abstract void processInput();

	protected abstract void render();

	protected void update() {
		state.update();
	}

	public GameState getState() {
		return state;
	}
}
