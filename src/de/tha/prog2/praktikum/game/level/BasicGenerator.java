package de.tha.prog2.praktikum.game.level;

import de.tha.prog2.praktikum.game.core.Board;
import de.tha.prog2.praktikum.game.core.GameObjectSet;
import de.tha.prog2.praktikum.game.gameobjects.DestructibleWall;
import de.tha.prog2.praktikum.game.gameobjects.ExtendBombStrength;
import de.tha.prog2.praktikum.game.gameobjects.ExtraPoints;
import de.tha.prog2.praktikum.game.gameobjects.HandOperatedBomberman;
import de.tha.prog2.praktikum.game.gameobjects.Monster;
import de.tha.prog2.praktikum.game.gameobjects.MoreBombs;
import de.tha.prog2.praktikum.game.gameobjects.PowerUp;
import de.tha.prog2.praktikum.game.gameobjects.Wall;
import de.tha.prog2.praktikum.game.util.XY;

public class BasicGenerator implements LevelGenerator {
	private XY size;

	public BasicGenerator() {
		this.size = new XY(20, 20); // (20,20) entspricht (width, height) des Felds
	}

	@Override
	public XY getSize() {
		return size;
	}

	@Override
	public GameObjectSet generate() {
		System.out.println("BasicGenerator generate() benutzt");
		int xSize = size.getX();
		int ySize = size.getY();
		GameObjectSet gameObjectSet = new GameObjectSet();

		HandOperatedBomberman bomber = new HandOperatedBomberman(new XY(1, 1));
		gameObjectSet.addObject(bomber);

		gameObjectSet.addObject(new Monster(new XY(13, 1)));
		gameObjectSet.addObject(new Monster(new XY(1, 13)));
		gameObjectSet.addObject(new Monster(new XY(13, 13)));
		gameObjectSet.addObject(new PowerUp(new XY(2, 2)));

		gameObjectSet.addObject(new ExtendBombStrength(new XY(3, 3)));
		gameObjectSet.addObject(new MoreBombs(new XY(3, 5)));
		gameObjectSet.addObject(new ExtraPoints(new XY(3, 7)));

		for (int y = 0; y < ySize; y++) {
			for (int x = 0; x < xSize; x++) {
				if (y == 0 || y == ySize - 1 || x == 0 || x == xSize - 1) {
					gameObjectSet.addObject(new Wall(new XY(x, y)));
					continue;
				}

				boolean inCornerSpace = (x == 1 && (y == 1 || y == 2 || y == ySize - 2 || y == ySize - 3))
						|| (x == 2 && (y == 1 || y == ySize - 2)) || (x == xSize - 3 && (y == 1 || y == ySize - 2))
						|| (x == xSize - 2 && ((y == 1 || y == 2 || y == ySize - 2 || y == ySize - 3)));

				if (Math.random() > 0.7 && !inCornerSpace) {
					gameObjectSet.addObject(new DestructibleWall(new XY(x, y)));
				}
			}
		}

		return gameObjectSet;
	}

	@Override
	public Board createBoard() {
		// TODO Auto-generated method stub
		return null;
	}
}
