package de.tha.prog2.praktikum.game.container.tests;

import java.util.ArrayList;
import java.util.Iterator;

import de.tha.prog2.praktikum.game.container.AbstractContainer;

public class DummyContainer extends AbstractContainer {
	private java.util.List<Object> arrayList = new ArrayList<>();

	@Override
	public boolean add(Object o) { // hinzufügen von Objekten
		return arrayList.add(o);
	}

	@Override
	public Object get(int i) { // objekte holen
		return arrayList.get(i);
	}

	@Override
	public int size() {	// größe des arrays nennen
		return arrayList.size();
	}

	@Override
	public boolean remove(Object o) { // element aus array entfernen
		return arrayList.remove(o);
	}
	
	@Override
	public Iterator<Object> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<Object> iteratorRandom() {
		// TODO Auto-generated method stub
		return null;
	}
}
