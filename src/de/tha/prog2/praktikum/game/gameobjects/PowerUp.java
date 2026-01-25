package de.tha.prog2.praktikum.game.gameobjects;

import de.tha.prog2.praktikum.game.util.XY;

public class PowerUp extends AbstractGameObject {

	public PowerUp(XY location) {
		super(location);
	}

	public void applyPowerUp(Bomberman bomberman) {
	}

	@Override
	public String toString() {
		return "PowerUp: id " + id + " auf " + location + " mit " + points + " Punkten";
	}
}

