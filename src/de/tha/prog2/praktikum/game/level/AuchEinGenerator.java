package de.tha.prog2.praktikum.game.level;

import de.tha.prog2.praktikum.game.core.Board;
import de.tha.prog2.praktikum.game.core.GameObjectSet;
import de.tha.prog2.praktikum.game.util.XY;

public class AuchEinGenerator implements LevelGenerator {
	private XY size;
	
		public AuchEinGenerator() {
			this.size = new XY(15, 15);
		}
		@Override
		public XY getSize() {
			return size;
		}
		
		@Override
		public GameObjectSet generate() {
			GameObjectSet set = new GameObjectSet();
		
			return set;
		}
		@Override
		public Board createBoard() {
			// TODO Auto-generated method stub
			return null;
		}

}
