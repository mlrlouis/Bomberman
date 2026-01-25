package de.tha.prog2.praktikum.game.container;

public abstract class AbstractContainer<T> implements Container<T> {

	@Override
	public boolean equals(Object container) {
		Container<T> containerT = (Container<T>) container;

		if (this.size() != containerT.size()) return false;

		for (int i = 0; i < this.size(); i++) { // vergleichen jedes Element an der gleichen Position
			if (!get(i).equals(containerT.get(i))) return false;
		}

		return true;
	}

	@Override
	public String toString() {
		String output = "Container content:\n";

		for (int i = 0; i < this.size(); i++) {
			output += "Element " + i + ": " + get(i).toString() + "\n";
		}

		return output;
	}
}
