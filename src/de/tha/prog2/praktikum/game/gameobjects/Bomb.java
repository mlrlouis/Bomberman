package de.tha.prog2.praktikum.game.gameobjects;

import de.tha.prog2.praktikum.game.core.GameObjectContext;
import de.tha.prog2.praktikum.game.util.XY;

public class Bomb extends AbstractGameObject {
	private int ownerID = -1;
	private int rounds = 5;
	private Bomberman owner;
	private int nukeStrength = 1;

	public Bomb(XY location) {
		super(location);
		System.out.println("Erster Bomb-Konstruktor (XY location)! ownerID: " + this.ownerID + "; bombID: " + this.getID() + "; location: " + this.getLocation().toString());
	}

	public Bomb(XY location, Bomberman bomberman) {
		super(location);
		this.points = 300;

		if (bomberman != null) {
			this.owner = bomberman;
			this.ownerID = bomberman.getID();
			this.nukeStrength = owner.getBombStrength();
		}

		System.out.println("Zweiter Bomb-Konstruktor (XY location, Bomberman bomberman)! ownerID: " + this.ownerID + "; bombID: " + this.getID() + "; location: " + this.getLocation().toString());
	}

	@Override
	public String toString() {
		return "Bomb: id " + id + " auf " + location + " mit " + points + " Pukten";
	}

	public int getRounds() {
		return this.rounds;
	}

	public void setRounds(int rounds) {
		this.rounds = rounds;
	}

	public int getOwnerId() {
		return this.ownerID;
	}

	public Bomberman getOwner() {
		return owner;
	}

	@Override
	public void nextStep(GameObjectContext cont) {
		
		rounds--;
		if (rounds == 0) cont.explode(this);
	}
}
