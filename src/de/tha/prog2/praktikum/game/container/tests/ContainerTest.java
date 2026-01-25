package de.tha.prog2.praktikum.game.container.tests;

import de.tha.prog2.praktikum.game.container.Container;
import de.tha.prog2.praktikum.game.container.Vector;

public class ContainerTest {
	public static void main(String[] args) {
		ContainerTest test = new ContainerTest();

		//DummyContainer dummyCont = new DummyContainer();

		Vector vect = new Vector();
		test.containerTest(vect);
	}

	public void containerTest(Container cont) {
		System.out.println("Test isEmpty():\nerwartet: true\nerhalten: " + cont.isEmpty()); // schaut ob leer
		
		cont.add(69.96);
		cont.add("6ix9ine");
		cont.add(69);
		
		System.out.println("\nTest add():\nerwartet: [69.96, \"6ix9ine\", 69] im Container\nerhalten: " + cont.toString()); // schaut ob was hinzugefügt werden kann
		System.out.println("\nTest size():\nerwartet: 3 Elemente im Container\nerhalten: " + cont.size()); // gibt größe zurück
		System.out.println("Test isEmpty():\nexpected: true\nreceived: " + cont.isEmpty());// schaut ob leer
		
		System.out.println("\nTest toArray():\nerwartet: Container mit 3 Elementen\nerhalten: " + cont.toString());

		System.out.println();

		System.out.println("\nTest get():\nerwartet: 69\nerhalten: " + cont.get(2)); // schaut ob an stelle x ein element ist und nennt dieses Element
		System.out.println("\nTest get():\nerwartet: false (nicht valider index): " + cont.get(900)); //schaut ob es Element bei 900 gibt

		System.out.println();

		System.out.println("\nTest contains():\nerwartet: true\nerhalten: " + cont.contains(69)); // schaut ob element drinnen ist
		System.out.println("\nTest contains():\nerwartet: false\nerhalten: " + cont.contains(404)); // schau ob element drinnen ist


		System.out.println();

		DummyContainer cont2 = new DummyContainer();
		
		cont2.add(69.96);
		cont2.add("6ix9ine");
		
		System.out.println("\nTest equals():\nerwartet: false\nerhalten: " + cont.equals(cont2)); // schaut ob inhalt gleich

		cont.remove(69);
		System.out.println("\nTest remove():\nerwartet: 2 Elemente im Container nach dem Löschen 1\nerhalten: " + cont.size()); //schau ob entfernent funktioniert


		System.out.println();
		System.out.println("Container1 size: " + cont.size() + " " + cont.toString());
		System.out.println("Container2 size: " + cont2.size() + " " + cont2.toString());
		System.out.println("\nTest equals():\nerwartet: true\nrerhalten: " + cont.equals(cont2));

		System.out.println();

		System.out.println("\nTest isEmpty():\nerwartet: false\nerhalten: " + cont.isEmpty());

		cont.clear();
		System.out.println("\nTest clear():\nerwartet: true(container isEmpty => leer)\nerhalten: " + cont.isEmpty()); // löscht alle elemente raus

		System.out.println("Container size: " + cont.size() + " - Zeige den Container nun nochmal an: " + cont.toString());
	}
}

