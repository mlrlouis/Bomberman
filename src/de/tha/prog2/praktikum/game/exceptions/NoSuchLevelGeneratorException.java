package de.tha.prog2.praktikum.game.exceptions;

@SuppressWarnings("serial")
public class NoSuchLevelGeneratorException extends Exception {

	public NoSuchLevelGeneratorException() {
		super();		
	}
	
	public NoSuchLevelGeneratorException(String msg) {
		super(msg);		
	}
	
	public NoSuchLevelGeneratorException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
	public NoSuchLevelGeneratorException(Throwable cause) {
		super(cause);
	}
}
