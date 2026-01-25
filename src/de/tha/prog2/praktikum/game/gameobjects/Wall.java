package de.tha.prog2.praktikum.game.gameobjects;

import de.tha.prog2.praktikum.game.util.XY;

public class Wall extends AbstractGameObject {

	public Wall(XY location) {
		super(location);
		System.out.println("Wall-Konstruktor (XY location)! id: " + this.getID() + "; location: " + this.getLocation().toString());
	}

	@Override
	public String toString() {
		return "Wall: id " + id + " auf " + location + " mit " + points + " Punkten";
	}
}
