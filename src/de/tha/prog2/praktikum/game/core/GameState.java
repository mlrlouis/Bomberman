package de.tha.prog2.praktikum.game.core;

public class GameState {
	private int highScore;
	private Board board;

	public GameState(Board board) {
		this.board = board;
		this.highScore = 0;
	}

	public void update() {
		this.board.getGameObjectSet().nextStep(board.flatten());
	}

	public FlattenedBoard flattenBoard() {
		return board.flatten();
	}

	public Board getBoard() {
		return board;
	}
}
