package de.tha.prog2.praktikum.game.util;

public final class XY {
	private final int x;
	private final int y;

	public XY(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public XY move(XY direction) {
		System.out.println("XY-move() wurde aufgerufen Richtung: " + direction.toString() + " Objekt XY "+ this.getX() + this.getY());
		return new XY(this.x + direction.getX(), this.y + direction.getY());
	}

	@Override
	public String toString() {
		return "(" + x +", "+ y + ")";
	}
}
