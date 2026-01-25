package de.tha.prog2.praktikum.game.core;

import de.tha.prog2.praktikum.game.level.LevelGenerator;
import de.tha.prog2.praktikum.game.util.XY;

public class Board {
	private GameObjectSet gameObjectSet;
	private XY size; // größe des Spielfelds

	// Konstruktor
	public Board(LevelGenerator levelGenerator) {
		this.gameObjectSet = levelGenerator.generate();
		this.size = levelGenerator.getSize();
	}

	public FlattenedBoard flatten() {
		return new FlattenedBoard(size, gameObjectSet);
	}

	public XY getSize() {
		return size;
	}

	public GameObjectSet getGameObjectSet() {
		return this.gameObjectSet;
	}
}
