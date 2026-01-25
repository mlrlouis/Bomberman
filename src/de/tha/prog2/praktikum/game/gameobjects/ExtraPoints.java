package de.tha.prog2.praktikum.game.gameobjects;

import de.tha.prog2.praktikum.game.util.XY;

public class ExtraPoints extends PowerUp {
	private int points = 0;

	public ExtraPoints(XY location) {
		super(location);
		System.out.println("ExtraPoints-PowerUp-Konstrukter (location)! id: " + this.getID() + "; location: " + this.getLocation().toString());
	}

	@Override
	public void applyPowerUp(Bomberman bomberman) { // PowerUp konsumieren und die K/D des Bomberman wird erhöht
		bomberman.updatePoints(points);
		System.out.println("PowerUp extraPoints ist aktiv!");
	}
}