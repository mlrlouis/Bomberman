package de.tha.prog2.praktikum.game.gameobjects;

import de.tha.prog2.praktikum.game.util.XY;

public class MoreBombs extends PowerUp {
	private int moreBombs = 1;

	public MoreBombs(XY location) {
		super(location);
		System.out.println("MoreBombs-PowerUp-Konstruktor (XY location)! id: " + this.getID() + "; location: " + this.getLocation().toString());
	}

	@Override
	public void applyPowerUp(Bomberman bomberman) { // PowerUp wird dem Bomberman hinzugefügt um noch mehr Kriegsverbrechen zu begehen
		bomberman.updateAllowedBombs(moreBombs);
		System.out.println("PowerUp moreBombs ist aktiv! Nukes erlaubt: "+ bomberman.allowedNukes + "; ownerID: " + bomberman.id);
	}
}