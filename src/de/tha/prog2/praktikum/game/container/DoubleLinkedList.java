package de.tha.prog2.praktikum.game.container;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

import de.tha.prog2.praktikum.game.exceptions.ContainerExceptions;

public class DoubleLinkedList<T> extends AbstractContainer<T> {
	private int modificationCount = 0;
	private Entry<T> head = new Entry<T>(); // Kopf der Liste
	public DoubleLinkedList() {
	}


	@Override
	public boolean add(T o) {
		if (contains(o)) throw new ContainerExceptions.ElementAlreadyExistsException("Das folgende element: " + o.toString() + " ist bereits im Container!");
		

		Entry<T> node = head;

		// solange man ein nächstes Element hat geht man die Liste weiter
		while (node.getNext() != null) {
			node = node.getNext();
		}

		node.setNext(new Entry<T>(node, o));
		modificationCount++; // am Container ändert sich was, wenn man ein neues Element hinzufügt
		return true;
	}

	@Override
	public T get(int i) {
		if (i < 0 || i > size() - 1) throw new ContainerExceptions.InvalidIndexException("Index: " + i + " ist kein korrekter Index!");

		Entry<T> node = head; // starten beim ersten echten Element

		for (int j = 0; j <= i; j++) {
			node = node.getNext();

			if (node == null) return null;
		}

		return node.getData();
	}

	@Override
	public int size() {
		Entry<T> node = head;
		int size = 0;

		while (node.getNext() != null) {
			node = node.getNext();
			size++;
		}

		return size;
	}

	@Override
	public boolean remove(T o) {

		if (!contains(o)) throw new ContainerExceptions.ElementNotFoundException("Das folgende Element: " + o.toString() + " ist nicht im Container!");
		

		modificationCount++;

		Entry<T> node = head;

		while (node.getNext() != null) {
			node = node.getNext();

			if (node.getData().equals(o)) {
				node.getPrev().setNext(node.getNext());
				modificationCount++; // am Container ändert sich was, wenn man ein Element löscht

				if (node.getNext() != null) node.getNext().setPrev(node.getPrev());
				
				return true;
			}
		}
		return false;
	}

	@Override
	public void clear() {
		this.head.setNext(null);
		modificationCount++; // am Container ändert sich was, wenn man die Liste leert
	}

	@Override
	public boolean contains(T o) {
		Entry<T> node = head;

		while (node.getNext() != null) {
			node = node.getNext();

			if (node.getData().equals(o)) return true;
		}
		return false;
	}

	@Override
	public Object[] toArray() {
		// needed otherwise CLassCastException is thrown
		Object[] resultArr = new Object[size()]; // (T[]) Array.newInstance(entriesDataClass, size());

		int i = 0;
		Entry<T> node = head;

		while (i < resultArr.length) {
			node = node.getNext();
			resultArr[i] = node.getData();
			i++;
		}

		return resultArr;
	}

	@Override
	public boolean isEmpty() {
		return head.getNext() == null;
	}

	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			private Entry<T> node = head.getNext();
			private final int expectedModCount = modificationCount;

			@Override
			public boolean hasNext() {
				return node != null;
			}

			@Override
			public T next() {
				if (expectedModCount != modificationCount) throw new ConcurrentModificationException("Das Array wurde während der Itteration bearbeitet!");

				if (node == null) throw new NoSuchElementException();

				T o = node.getData();
				node = node.getNext();
				return o;
			}
		};
	}

	@Override
	public Iterator<T> iteratorRandom(DoubleLinkedList<T> this) {
		Iterator<T> iterator = new Iterator<T>() {

			int modificationState = modificationCount;
			Random random = new Random();
			@SuppressWarnings("unchecked")
			List<T> list = new LinkedList(Arrays.asList(toArray()));


			@Override
			public T next() { //ist da, um zufälligen Knoten zu finden, indem er eine zufällige Anzahl an Knoten überspringt
				if (modificationState != modificationCount) throw new ConcurrentModificationException("Das Array wurde während der Itteration bearbeitet!");

				if (list.size() == 0) throw new NoSuchElementException();

				int randomIndex = random.nextInt(list.size());
				return list.remove(randomIndex);
			}

			@Override

			public boolean hasNext() {

				if (modificationState != modificationCount) throw new ConcurrentModificationException("Das Array wurde während der Itteration bearbeitet!");

				return list.size() > 0;
			}
		};

		return iterator;
	}
}

class Entry<T> {
	private Entry<T> next;
	private Entry<T> prev;

	private T data;

	public Entry() {
	}

	public Entry(Entry<T> prev, T o) {
		this.prev = prev;
		this.data = o;
	}

	public void setNext(Entry<T> next) {
		this.next = next;

	}

	public void setPrev(Entry<T> prev) {
		this.prev = prev;
	}

	public Entry<T> getNext() {
		return this.next;
	}

	public Entry<T> getPrev() {
		return this.prev;
	}

	public T getData() {
		return this.data;
	}
}
