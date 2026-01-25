package de.tha.prog2.praktikum.game.gameobjects;

import de.tha.prog2.praktikum.game.core.GameObjectContext;
import de.tha.prog2.praktikum.game.util.XY;

public class Blast extends AbstractGameObject {
	private int ownerID = -1;
	private int rounds = 2;
	private Bomberman owner;

	public Blast(XY location) {
		super(location);
		this.points = 300;
		System.out.println("Blast constructor 1 (XY location)  blastID: " + this.getID() + "; location: " + this.getLocation().toString());
	}

	public Blast(XY location, Bomberman bomberman) {
		super(location);
		this.points = 300;
		owner = bomberman;
		System.out.println("Blast constructor 2 (XY location, Bomberman bomberman)! ownerID: " + this.ownerID + "; blastID: " + this.getID() + this.getLocation().toString());
	}

	@Override
	public String toString() {
		return "Blast: id " + id + " auf " + location + " mit " + points + " Punkten";
	}

	@Override
	public void nextStep(GameObjectContext cont) {
		
		rounds--;
		if (rounds == 0) cont.removeBlast(this);
	}

	public int getOwnerID() {
		return this.ownerID;
	}

	public Bomberman getOwner() {
		return owner;
	}
}
