package de.tha.prog2.praktikum.game.gameobjects;

import de.tha.prog2.praktikum.game.core.GameObjectContext;
import de.tha.prog2.praktikum.game.exceptions.IllegalMoveException;
import de.tha.prog2.praktikum.game.util.MoveCommand;
import de.tha.prog2.praktikum.game.util.XY;

public class HandOperatedBomberman extends Bomberman {
	private MoveCommand move;
	private boolean placedBomb = false;
	private XY bombLoc; // da, um die Platzierung der Bombe darzustellen

	public HandOperatedBomberman(XY location) {
		super(location);

		System.out.println("HandOperatedBomberman-Konstruktor (XY location)! bombermanID: " + this.id + "; location: " + this.getLocation().toString());
	}
	
	// Konstruktor aus der Angabe von Blatt6
	public HandOperatedBomberman(XY location , String playerName, int points, int destroyedMonsters , int destroyedDestructibleWalls) {
		super(location, playerName, points, destroyedMonsters, destroyedDestructibleWalls);
		System.out.println("HandOperatedBomberman mit den folgenden stats - bombermanID: " + this.id);
	}
	

	@Override
	public void nextStep(GameObjectContext context) {
		System.out.println("HandOperatedBomberman " + this.getID() + " nextStep() wurde mit folgendem move benutzt: " + this.move);

		// Does not move if move is null
		if (move == null) return;

		// bomberman soll sich so bewegen, wie man zuletzt gedrückt hat => w, a, s, d
		switch (move) {
		case UP:
			context.move(this, new XY(0, -1));
			break;
		case LEFT:
			context.move(this, new XY(-1, 0));
			break;
		case DOWN:
			context.move(this, new XY(0, 1));
			break;
		case RIGHT:
			context.move(this, new XY(1, 0));
			break;
		case BOMB:
			if (placedBomb) throw new IllegalMoveException("Bomb MoveCommand wurde wiederholt verwendet!");


			placedBomb = true;
			bombLoc = this.getLocation();
			System.out.println("Nuke plazieren Kommando");
			return;
		}

		if (placedBomb) {
			context.move(new Bomb(bombLoc, this), new XY(0, 0));
			placedBomb = false;
		}

		this.move = null;
	}

	@Override
	public String toString() {
		return "HandOperatedBomberman: " + id + " auf " + location + " mit " + points + " Punkten";
	}

	public void setMoveCommand(MoveCommand move) {
		System.out.println("Move wurde festgelegt: " + move + " für HandOperatedBomberman " + this.getID());
		this.move = move;
	}
}
