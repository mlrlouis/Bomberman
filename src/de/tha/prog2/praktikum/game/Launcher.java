package de.tha.prog2.praktikum.game;

import de.tha.prog2.praktikum.game.core.Board;
import de.tha.prog2.praktikum.game.core.GameImpl;
import de.tha.prog2.praktikum.game.core.GameState;
import de.tha.prog2.praktikum.game.exceptions.NoSuchLevelGeneratorException;
import de.tha.prog2.praktikum.game.level.LevelGenerator;
import de.tha.prog2.praktikum.game.level.LevelGeneratorLoader;
import de.tha.prog2.praktikum.game.ui.ConsoleUI;
import de.tha.prog2.praktikum.game.util.Ranking;

public class Launcher {
	public static void main(String[] args) throws ClassNotFoundException {
		try {
			LevelGenerator generator = LevelGeneratorLoader.createGenerator("de.tha.prog2.praktikum.game.level.BasicGenerator");
			
			//UI erstellen
			ConsoleUI consoleUI = new ConsoleUI();
			
			//Spielzustand erstellen
			GameState state = new GameState(new Board(generator));
			GameImpl game = new GameImpl(state, consoleUI);
			
			//Spiel starten
			game.run();
		} catch (NoSuchLevelGeneratorException nslge) {
			nslge.printStackTrace();			
			Ranking ranking = new Ranking();
			ranking.updateRanking();
			System.out.println(ranking.toString());
		}
			/*
			Alter Code, vor blatt5:
			
			//Basic-Generator erstellen
			BasicGenerator generator = new BasicGenerator();
			System.out.println(generator.getClass().toString());
			
			//UI erstellen
			ConsoleUI consoleUI = new ConsoleUI();


			//Spielzustand erstellen
			GameState state = new GameState(new Board(generator));
			GameImpl game = new GameImpl(state, consoleUI);
			
			//Spiel starten
			game.run();
			*/

	}
}
