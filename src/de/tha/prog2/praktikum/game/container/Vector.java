package de.tha.prog2.praktikum.game.container;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;


import de.tha.prog2.praktikum.game.exceptions.ContainerExceptions;

public class Vector<T> extends AbstractContainer<T> implements Container<T> {

	private int size;
	private T[] data;
	private int modificationCount = 0; // zählt Änderungen im Contoi

	public Vector() {
		size = 0;
		data = (T[]) new Object[0];
	}

	@Override
	public boolean add(T o) {
		if (contains(o)) throw new ContainerExceptions.ElementAlreadyExistsException("Das folgende element: " + o.toString() + " ist bereits im Container");

		modificationCount++; //modificationCount erhöhen, damit Exception geworfen werden kann

		if (size == data.length) {
			T[] newArr = (T[]) new Object[data.length + 1];

			System.arraycopy(data, 0, newArr, 0, size);
			newArr[newArr.length - 1] = o;
			data = newArr;
		}
		
		data[size++] = o;

		return true;
	}

	@Override
	public T get(int i) {
		if (i < 0 || i > size) throw new ContainerExceptions.InvalidIndexException("Index: " + i + " ist kein korrekter/vorhandener Index");

		return data[i];
	}


	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean remove(T o) {
		if (!contains(o)) throw new ContainerExceptions.ElementNotFoundException("Das folgende Element: " + o.toString() + " ist nicht mehr im Container enthalten!");

		modificationCount++;


		for (int i = 0; i < size; i++) {
			if (data[i].equals(o)) {
				for (int j = i; j < size - 1; j++) {
					data[j] = data[j + 1];
				}
				data[--size] = null;
				return true;
			}
		}
		return false;

	}

	@Override
	public Iterator<T> iterator() {

		return new IteratorImpl<T>();
	}

	private class IteratorImpl<T> implements Iterator<T> {
		private int modificationState;
		private int index = 0;

		public IteratorImpl() {
			this.modificationState = modificationCount;
		}


		@Override
		public T next() {
			// Überprüfung des modificationCounts muss auch in der next()-Methode stehen, nicht nur in der hasNext()-Methode
			if (modificationState != modificationCount) throw new ConcurrentModificationException("Der Zustand des Containers hat sich geändert.");

			if (data.length <= index) throw new NoSuchElementException();

			return (T) data[index++];
		}


		@Override
		public boolean hasNext() {

			if (modificationState != modificationCount) throw new ConcurrentModificationException("Der Zustand des Containers hat sich geändert.");

			return index < size;
		}
	}

	@Override
	public Iterator<T> iteratorRandom() {
		Iterator<T> iterator = new Iterator<T>() {

			int modificationState = modificationCount;
			Random random = new Random();
			List<T> list = new LinkedList<T>(Arrays.asList(data));

			@Override
			public T next() {
				// Überprüfung des modificationCounts in next()
				if (modificationState != modificationCount) throw new ConcurrentModificationException("Der Zustand des Containers hat sich geändert.");

				if (list.size() == 0) throw new NoSuchElementException();

				int randomIndex = random.nextInt(list.size());
				return list.remove(randomIndex);
			}

			@Override
			public boolean hasNext() {

				if (modificationState != modificationCount) throw new ConcurrentModificationException("Der Zustand des Containers hat sich geändert.");

				return list.size() > 0;
			}
		};

		return iterator;
	}
}