package de.tha.prog2.praktikum.game.exceptions;

public class ContainerExceptions {

	    public static class ElementAlreadyExistsException extends RuntimeException {
			
			private static final long serialVersionUID = -123456789012345L;

			public ElementAlreadyExistsException(String message) {
	            super(message);
	        }
	    }

	    public static class ElementNotFoundException extends RuntimeException {

			private static final long serialVersionUID = -859203187349021L;

			public ElementNotFoundException(String message) {
	            super(message);
	        }
	    }

	    public static class InvalidIndexException extends RuntimeException {
			
			private static final long serialVersionUID = 69L;

			public InvalidIndexException(String message) {
	            super(message);
	        }
	    }

}
	
	/* Wieso sind unchecked Exceptions hier deutlich sinvoller?
	 * Das liegt daran, dass es sich hier um Programmierfehler handelt, die bei korrekter Nutzung der API/des Programms eigentlich
	 * nicht auftreten sollten. 
	 * Man erwartet bspw., dass man prüft, ob ein Element schon vorhanden ist, bevor man es hinzufügt oder entfernt.
	 * 
	 * Checked Exceptions wären hier
	 * 1. übertrieben und
	 * 2. sie würden den Code unnötig vergrößern
	 */

