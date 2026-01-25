package de.tha.prog2.praktikum.game.gameobjects;

import de.tha.prog2.praktikum.game.core.GameObjectContext;
import de.tha.prog2.praktikum.game.util.XY;

public class Monster extends AbstractGameObject {
	private int round;

	public Monster(XY location) {
		super(location);

		this.points = 300;
		this.round = 0;
		System.out.println("Monster-Konstruktor (XY location)! id: " + this.getID() + "; location: " + this.getLocation().toString());
	}

	@Override
	public void nextStep(GameObjectContext context) {
		if (round % 2 == 0) {
			int dir = (int) (Math.random() * 4); // random bewegungen

			switch (dir) {
			case 0:
				context.move(this, new XY(-1, 0));
				break;
			case 1:
				context.move(this, new XY(0, -1));
				break;
			case 2:
				context.move(this, new XY(1, 0));
				break;
			case 3:
				context.move(this, new XY(0, 1));
				break;
			}
		}
		round++;
	}

	@Override
	public String toString() {
		return "Monster: id " + id + " auf " + location + " mit " + points + " Punkten";
	}

	@Override
	public int getPoints() {
		return points;
	}
}
