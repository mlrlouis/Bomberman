package de.tha.prog2.praktikum.game.ui;

import java.util.Scanner;

import de.tha.prog2.praktikum.game.core.BoardView;
import de.tha.prog2.praktikum.game.gameobjects.Blast;
import de.tha.prog2.praktikum.game.gameobjects.Bomb;
import de.tha.prog2.praktikum.game.gameobjects.Bomberman;
import de.tha.prog2.praktikum.game.gameobjects.DestructibleWall;
import de.tha.prog2.praktikum.game.gameobjects.ExtendBombStrength;
import de.tha.prog2.praktikum.game.gameobjects.ExtraPoints;
import de.tha.prog2.praktikum.game.gameobjects.GameObject;
import de.tha.prog2.praktikum.game.gameobjects.Monster;
import de.tha.prog2.praktikum.game.gameobjects.MoreBombs;
import de.tha.prog2.praktikum.game.gameobjects.Wall;
import de.tha.prog2.praktikum.game.util.MoveCommand;

public class ConsoleUI implements UI {

	@Override
	public MoveCommand getCommand() {
		String input = new Scanner(System.in).nextLine().toLowerCase();

		if (input.length() == 0) {
			return null;
		}

		switch (input.charAt(0)) {
		case 'w':
			return MoveCommand.UP;
		case 'a':
			return MoveCommand.LEFT;
		case 's':
			return MoveCommand.DOWN;
		case 'd':
			return MoveCommand.RIGHT;
		case 'b':
			return MoveCommand.BOMB;
		}

		return null;
	}

	@Override
	public void render(BoardView view) {
		GameObject[][] arr = view.getGameBoard();
		int ySize = view.getSize().getY();
		int xSize = view.getSize().getX();
		char[][] board = new char[xSize][ySize];

		for (int y = 0; y < ySize; y++) {
			for (int x = 0; x < xSize; x++) {
				GameObject o = arr[x][y];

				if (o instanceof Bomberman) {
					board[x][y] = 'B';
					System.out.println("HandOperatedBomberman-Punkte: " + o.getPoints());
				} else if (o instanceof Monster) {
					board[x][y] = 'm';
				} else if (o instanceof Wall) {
					board[x][y] = 'W';
				} else if (o instanceof DestructibleWall) {
					board[x][y] = 'X';
				} else if (o instanceof Bomb) {
					board[x][y] = 'o';
				} else if (o instanceof Blast) {
					board[x][y] = '+';
				} else if (o instanceof MoreBombs) {
					board[x][y] = '2';
				} else if (o instanceof ExtraPoints) {
					board[x][y] = '3';
				} else if (o instanceof ExtendBombStrength) {
					board[x][y] = '1';
				} else {
					board[x][y] = '.';
				}
			}
		}

		StringBuilder sb = new StringBuilder();

		for (int y = 0; y < ySize; y++) {
			for (int x = 0; x < xSize; x++) {
				sb.append(board[x][y]).append(" ");
			}
			sb.append(System.lineSeparator());
		}
		System.out.print(sb.toString());
	}
}
