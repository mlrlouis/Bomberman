package de.tha.prog2.praktikum.game.tests;

import java.util.Iterator;

import org.junit.jupiter.api.Test;

import de.tha.prog2.praktikum.game.container.Vector;

public class ContainerTest {
	@Test
	public void testVector() {
		Vector vect = new Vector();

		vect.add(1);
		vect.add(2);
		vect.add(3);
		vect.add(4);
		vect.add(5);
		vect.add(6);

		Iterator it = vect.iterator();

		while (it.hasNext()) {
			System.out.println(it.next());
		}
	}
}
