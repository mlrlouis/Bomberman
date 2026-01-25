package de.tha.prog2.praktikum.game.gameobjects;

import de.tha.prog2.praktikum.game.core.GameObjectContext;
import de.tha.prog2.praktikum.game.util.XY;

public abstract class AbstractGameObject implements GameObject {

	private static int takenId = 1;
	protected int points = 0;
	protected final int id;
	protected XY location;

	public AbstractGameObject(XY location) {
		this.id = AbstractGameObject.takenId++;
		this.location = location;
	}

	@Override
	public void nextStep(GameObjectContext cont) {
	}

	@Override
	public void updatePoints(int points) {
	}

	@Override
	public XY getLocation() {
		return this.location;
	}

	@Override
	public void setLocation(XY location) {
		this.location = location;
	}

	@Override
	public int getID() {
		return this.id;
	}

	@Override
	public int getPoints() {
		return this.points;
	}

	@Override
	public boolean equals(Object o) {
		
		if (o == null) return false;
		return this.id == ((GameObject) o).getID();
	}
}
