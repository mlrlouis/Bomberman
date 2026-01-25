package de.tha.prog2.praktikum.game.exceptions;

public class NoMoreBombsAllowed extends RuntimeException {

	private static final long serialVersionUID = 2L;

		public NoMoreBombsAllowed(String message) {
			super(message);
		}
}
