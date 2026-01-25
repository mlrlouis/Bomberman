package de.tha.prog2.praktikum.game.core;

import java.util.Iterator;

import de.tha.prog2.praktikum.game.container.DoubleLinkedList;
import de.tha.prog2.praktikum.game.gameobjects.GameObject;


public class GameObjectSet {
	private DoubleLinkedList<GameObject> gameObjects;


	// Erstellt ein neues GameObjectSet mit einer DoubleLinkedList als Container
	public GameObjectSet() {
		this.gameObjects = new DoubleLinkedList<GameObject>();
	}

	public boolean contains(GameObject o) {
		return gameObjects.contains(o);
	}

	public GameObject getObject(int i) {
		return gameObjects.get(i);
	}


	// fügt ein Spielobjekt hinzu
	public void addObject(GameObject o) {
		gameObjects.add(o);


	}

	// entfernt ein Spielobjekt
	public void removeObject(GameObject o) {
		gameObjects.remove(o);
	}

	public int size() {
		return gameObjects.size();
	}

	public void nextStep(GameObjectContext cont) {
		DoubleLinkedList<GameObject> copiedList = new DoubleLinkedList<GameObject>();
		Iterator<GameObject> Iter = gameObjects.iterator();


		while (Iter.hasNext()) {
			copiedList.add(Iter.next());
		}

		Iterator<GameObject> randomIter = copiedList.iteratorRandom();

		while (randomIter.hasNext()) {
			GameObject gameObject = randomIter.next();
			gameObject.nextStep(cont);
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		int i = 0;
		int size = gameObjects.size();

		while (i < size) {
			GameObject gameObject = gameObjects.get(i);
			sb.append(gameObject.toString() + "\n");
			i++;
		}

		return sb.toString();
	}

	public GameObject[] toArray() {
		Object[] tempArr = gameObjects.toArray();
		GameObject[] GameObjectArr = new GameObject[tempArr.length];

		for (int i = 0; i < tempArr.length; i++) {
			GameObjectArr[i] = (GameObject) tempArr[i];
		}

		return GameObjectArr;
	}
}
