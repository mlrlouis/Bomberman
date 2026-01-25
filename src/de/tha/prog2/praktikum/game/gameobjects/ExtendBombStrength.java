package de.tha.prog2.praktikum.game.gameobjects;

import de.tha.prog2.praktikum.game.util.XY;

public class ExtendBombStrength extends PowerUp {
	private int extendBombStrength = 1;

	public ExtendBombStrength(XY location) {
		super(location);
		System.out.println("ExtendedBombStrength-PowerUp-Konstruktor (XY location)! id: " + this.getID() + "; location: " + this.getLocation().toString());
	}

	@Override
	public void applyPowerUp(Bomberman bomberman) { // PowerUp konsumieren, damit man mehr Kreaturen nuken kann
		bomberman.updateBombStrength(extendBombStrength);
		System.out.println("PowerUp extendedBombStrength ist aktiv! Neue Stärke " + bomberman.nukeStrength +" ownerID " + bomberman.id );
	}
}