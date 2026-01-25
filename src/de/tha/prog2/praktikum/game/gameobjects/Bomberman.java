package de.tha.prog2.praktikum.game.gameobjects;

import de.tha.prog2.praktikum.game.core.GameObjectContext;
import de.tha.prog2.praktikum.game.util.XY;

// Damit eine Klasse eine natürliche Ordnung hat, muss sie das Interface Comparable<T> 
// implementieren und die Methode compareTo(T other) überschreiben.
public class Bomberman extends AbstractGameObject implements Comparable<Bomberman>{
	protected int nukeStrength = 1;
	protected int allowedNukes = 1;
	protected int nukeCount = 0;
	protected int destroyedMonsters = 0;
	protected int destroyedDestructibleWalls = 0;
	protected String playerName;
	


	public Bomberman(XY location) {
		super(location);
		this.playerName = "Spieler";
		System.out.println("Bomberman-Konstruktor (XY location)! bombermanID: " + this.id + "; location: " + this.getLocation().toString());
	}
	
	// Konstruktor aus der Angabe von Blatt6
	public Bomberman(XY location, String playerName, int points, int destroyedMonsters, int destroyedDestructibleWalls) {
		super(location);
		this.playerName = playerName;
		this.points = points;
		this.destroyedMonsters = destroyedMonsters;
		this.destroyedDestructibleWalls = destroyedDestructibleWalls;
		System.out.println("Bomberman-Konstruktor mit den folgenden stats - BombermanID: " + this.id +
				            "; Name: " + this.playerName +
				            "; Punkte: " + this.points +
				            "; getötete Monster: " + this.destroyedMonsters + 
				            "; zerstörte destructible walls: " + this.destroyedDestructibleWalls);
	}

	@Override
	public void nextStep(GameObjectContext cont) {
		// Logik kann später noch angepasst werden, falls das verlangt wird.
	}

	@Override
	public String toString() {
		return "Bomberman: id " + id + " auf " + location + " mit " + points + " Punkten";
	}

	@Override
	public void updatePoints(int points) {
		this.points += points;
	}

	public void updateAllowedBombs(int allowedBombs) {
		System.out.print("Erlaubte Nukes davor: " + this.allowedNukes + " - Erlaubte Nukes danach: ");
		this.allowedNukes += allowedBombs;
		System.out.println(this.allowedNukes);
	}

	public void updateBombStrength(int nukeStrength) {
		this.nukeStrength += nukeStrength;
	}

	public void addToBombCount(int value) {
		this.nukeCount += value;
	}
	
	//Methode für das erhöhen des zerstörten monster (blatt6)
	public void incrementDestroyedMonsters() {
		this.destroyedMonsters++;
	}
	//Methode für das erhöhen des zerstörten destructible walls (blatt6)	
	public void incrementDestroyedDestructibleWalls() {
		this.destroyedDestructibleWalls++;
	}
	
	@Override
	public int compareTo(Bomberman other) {
		// als erstes soll nach Punkten verglichen werden (höhere Punkte als erstes)
		if(this.points != other.points) return other.points - this.points;
		
		// wenn erstes gleich, dann vergleichen der getöteten Monster (höhere Monsterkills als erstes)
		if(this.destroyedMonsters != other.destroyedMonsters) return other.destroyedMonsters - this.destroyedMonsters;

		// wenn zweiteres gleich, dann vergleichen der zerstörten destructibleWalls (höhere destructibleWalls zerstörungen als erstes)		
		return other.destroyedDestructibleWalls - this.destroyedDestructibleWalls;
	}

	public int getBombCount() {
		return nukeCount;
	}

	public int getBombStrength() {
		return nukeStrength;

	}

	public int getAllowedBombs() {
		return allowedNukes;
	}
	
	public int getDestroyedMonsters() {
		return destroyedMonsters;
	}
	
	public int getDestroyedDestructibleWalls() {
		return destroyedDestructibleWalls;
	}
	

	public String getPlayerName() {
		return playerName;
	}
}