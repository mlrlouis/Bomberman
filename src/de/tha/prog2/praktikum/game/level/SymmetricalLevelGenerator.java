package de.tha.prog2.praktikum.game.level;

import java.util.Random;

import de.tha.prog2.praktikum.game.core.Board;
import de.tha.prog2.praktikum.game.core.GameObjectSet;
import de.tha.prog2.praktikum.game.gameobjects.DestructibleWall;
import de.tha.prog2.praktikum.game.gameobjects.ExtendBombStrength;
import de.tha.prog2.praktikum.game.gameobjects.ExtraPoints;
import de.tha.prog2.praktikum.game.gameobjects.HandOperatedBomberman;
import de.tha.prog2.praktikum.game.gameobjects.Monster;
import de.tha.prog2.praktikum.game.gameobjects.MoreBombs;
import de.tha.prog2.praktikum.game.gameobjects.Wall;
import de.tha.prog2.praktikum.game.util.XY;

public class SymmetricalLevelGenerator implements LevelGenerator{
	private final XY size;

	public SymmetricalLevelGenerator() {
		this.size = new XY(10, 10);
	}
	
	@Override
	public XY getSize() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override // look thru it again something is missing //
	public GameObjectSet generate() {
		// TODO Auto-generated method stub
		GameObjectSet set = new GameObjectSet();
		
		Random random = new Random();
		
		set.addObject(new HandOperatedBomberman(new XY(1, size.getY() / 2)));
		addWalls(set);
		addDestructibleWalls(set, random, 10);
		addMonsters(set, random, 3);
		addPowerUps(set, random);
		
		
		return set;
	}
	
	private void addWalls(GameObjectSet set) {
		for(int x = 0; x < size.getX(); x++) {
			XY topWallPosition = new XY(x, 0);
	        XY bottomWallPosition = new XY(x, size.getY() - 1);
			set.addObject(new Wall(topWallPosition));
			set.addObject(new Wall(bottomWallPosition));
		}
		
		for(int y = 0; y < size.getY(); y++) {
			XY leftWallPosition = new XY(0, y);
	        XY rightWallPosition = new XY(size.getX() - 1, y);
			set.addObject(new Wall(leftWallPosition));
			set.addObject(new Wall(rightWallPosition));
		}
	}

	private void addDestructibleWalls(GameObjectSet set, Random random, int count) {
		for (int i = 0; i < count / 2; i++) {
			int x = random.nextInt((size.getX() / 2) - 2) + 1;
			int y = random.nextInt(size.getY() - 2 ) + 1;
			
			XY leftPos = new XY(x, y);
			XY rightPos = new XY(size.getX() - 1 - x, y);
			
			if (!leftPos.equals(new XY(1, size.getY() / 2))) {
				set.addObject(new DestructibleWall(leftPos));
			}
			
			if (!rightPos.equals(new XY(1, size.getY() / 2))) {
				set.addObject(new DestructibleWall(rightPos));
			}
			
		}
	}
	
	private void addMonsters(GameObjectSet set, Random random, int count) {
		for (int i = 0; i < count; i++) {
			int x = random.nextInt((size.getX() / 2) - 2) + 1;
			int y = random.nextInt(size.getY() - 2 ) + 1;
			
			XY leftPos = new XY(x, y);
			XY rightPos = new XY(size.getX() - 1 - x, y);
			
			if (!leftPos.equals(new XY(1, size.getY() / 2))) {
				set.addObject(new Monster(leftPos));
			}
			
			if (!rightPos.equals(new XY(1, size.getY() / 2))) {
				set.addObject(new Monster(rightPos));
			}
		}
	}
	
	private void addPowerUps(GameObjectSet set, Random random) {
		int x1 = random.nextInt(size.getX() - 2) + 1;
		int y1 = random.nextInt(size.getY() - 2) + 1;
		
		int x2 = random.nextInt(size.getX() - 2) + 1;
		int y2 = random.nextInt(size.getY() - 2) + 1;
		
		int x3 = random.nextInt(size.getX() - 2) + 1;
		int y3 = random.nextInt(size.getY() - 2) + 1;
		
		set.addObject(new MoreBombs(new XY(x1, y1)));
		set.addObject(new ExtendBombStrength(new XY(x2, y2)));
		set.addObject(new ExtraPoints(new XY(x3, y3)));	
	}

	@Override
	public Board createBoard() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
