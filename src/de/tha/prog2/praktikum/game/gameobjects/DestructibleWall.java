package de.tha.prog2.praktikum.game.gameobjects;

import de.tha.prog2.praktikum.game.util.XY;

public class DestructibleWall extends AbstractGameObject {

	public DestructibleWall(XY location) {
		super(location);
		this.points = 100;
		System.out.println("DestructibleWall-Konstruktor (XY location)! id: " + this.getID() + "; location: " + this.getLocation().toString());
	}

	@Override
	public String toString() {
		return "DestructibleWall: id " + id + " auf " + location + " mit " + points + " Punkten";
	}
}
