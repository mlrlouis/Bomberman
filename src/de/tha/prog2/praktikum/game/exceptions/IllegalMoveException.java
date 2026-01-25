package de.tha.prog2.praktikum.game.exceptions;

public class IllegalMoveException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public IllegalMoveException(String message) {
		super(message);
	}

}
