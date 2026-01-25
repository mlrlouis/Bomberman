package de.tha.prog2.praktikum.game.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import de.tha.prog2.praktikum.game.gameobjects.Bomberman;

public class Ranking {
	private List<Bomberman> bombermen;
	
	public Ranking() {
		this.bombermen = new ArrayList<>();
	}
	
	public void addBomberman(Bomberman bm) {
		if (bm != null) bombermen.add(bm);
	}
	
	public int getRanking(Bomberman bm) {
		if (!bombermen.contains(bm)) return - 1;
	
		List<Bomberman> sorted = new ArrayList<>(bombermen);
	    Collections.sort(sorted);

	    int index = sorted.indexOf(bm);
	    if(index == -1) return -1;
	    
	    int rank = 1;

	    // Ermitteln des tatsächlichen Rangs durch Zählen von unterschiedlichen Bombermen davor
	    for(int i = 0; i < index; i++) {
	    	Bomberman current = sorted.get(i);
	    	Bomberman next = sorted.get(i+1);
	    	
	        // Wenn der aktuelle und der nächste Bomberman unterschiedlich sind, erhöhe den Rang
	    	if(current.compareTo(next) != 0) rank++;
	    }
	    return rank;
	}
		
	
	public void updateRanking() {
		Collections.sort(bombermen);
	}
	

	public String toString() {
		 StringBuilder s = new StringBuilder("Platz Spielername Punkte Monster DestructibleWalls\n");

		 List<Bomberman> sortedBm = new ArrayList<>(bombermen);
		 Collections.sort(sortedBm);

		 int currentRank = 1;

		 for (int i = 0; i < sortedBm.size(); i++) {
			 Bomberman current = sortedBm.get(i);

			 boolean sameAsPrevious = (i > 0) && (sortedBm.get(i - 1).compareTo(current) == 0);

			 if (!sameAsPrevious) {
				 s.append(String.format("%-5d", currentRank)); // Platz anzeigen
			 } else {
				 s.append("     "); // Leer lassen bei Gleichstand
			 }

			 s.append(String.format(" %-12s %-7d %-8d %d\n",
					 current.getPlayerName(),
					 current.getPoints(),
					 current.getDestroyedMonsters(),
					 current.getDestroyedDestructibleWalls()));

		        if (!sameAsPrevious) currentRank++;
		    }
		    return s.toString();
	}
}
