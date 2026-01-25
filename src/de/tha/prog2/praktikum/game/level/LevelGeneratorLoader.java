package de.tha.prog2.praktikum.game.level;

import java.lang.reflect.InvocationTargetException;

import de.tha.prog2.praktikum.game.exceptions.NoSuchLevelGeneratorException;

public class LevelGeneratorLoader {
	
	public LevelGeneratorLoader() {	
	}
	
	public static LevelGenerator createGenerator(String name) throws NoSuchLevelGeneratorException, ClassNotFoundException {
		try {
			Class<?> clazz = Class.forName(name);
			
			if (!LevelGenerator.class.isAssignableFrom(clazz)) {
				 throw new NoSuchLevelGeneratorException("Die Klasse " + name + " implementiert nicht das Interface!");
			 }
			 
			 return (LevelGenerator) clazz.getDeclaredConstructor().newInstance();
			 
		} catch (NoSuchLevelGeneratorException e) {
			throw new NoSuchLevelGeneratorException("Die Klasse " + name + " nicht gefunden!");
		} catch (NoSuchMethodException e) {
			throw new NoSuchLevelGeneratorException("Die Klasse " + name + " hat kein Default!");
		} catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
			throw new NoSuchLevelGeneratorException("Instanzfehler von " + name + e.getMessage());
		} catch (Exception e) {
			throw new NoSuchLevelGeneratorException("Fehler biem Laden des folgesnden LevelGen. " + name);
		}
	}

	
}
