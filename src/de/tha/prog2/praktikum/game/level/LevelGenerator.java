package de.tha.prog2.praktikum.game.level;

import de.tha.prog2.praktikum.game.core.Board;
import de.tha.prog2.praktikum.game.core.GameObjectSet;
import de.tha.prog2.praktikum.game.gameobjects.GameObject;
import de.tha.prog2.praktikum.game.util.XY;

public interface LevelGenerator {
    
    @SuppressWarnings("unused")
	private GameObject gameObject() {
        return null;
    }
    
    public GameObjectSet generate();
    
    public Board createBoard();

	public XY getSize();

	
}
