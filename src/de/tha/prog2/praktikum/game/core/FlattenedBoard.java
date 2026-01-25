package de.tha.prog2.praktikum.game.core;

import de.tha.prog2.praktikum.game.exceptions.IllegalMoveException;
import de.tha.prog2.praktikum.game.exceptions.NoMoreBombsAllowed;
import de.tha.prog2.praktikum.game.gameobjects.Blast;
import de.tha.prog2.praktikum.game.gameobjects.Bomb;
import de.tha.prog2.praktikum.game.gameobjects.Bomberman;
import de.tha.prog2.praktikum.game.gameobjects.DestructibleWall;
import de.tha.prog2.praktikum.game.gameobjects.GameObject;
import de.tha.prog2.praktikum.game.gameobjects.Monster;
import de.tha.prog2.praktikum.game.gameobjects.PowerUp;
import de.tha.prog2.praktikum.game.gameobjects.Wall;
import de.tha.prog2.praktikum.game.util.XY;

public class FlattenedBoard implements BoardView, GameObjectContext {
	private GameObject[][] board;
	private GameObjectSet gameObjectSet;
	private XY size;

	// size = die Größe des Spielfelds
	// gameObjects = die Liste der Spielobjekte
	public FlattenedBoard(XY size, GameObjectSet gameObjects) {
		this.size = size;
		this.gameObjectSet = gameObjects;

		GameObject[] gameObjectsArray = gameObjects.toArray();
		this.board = new GameObject[size.getX()][size.getY()];

		//Schleife geht jedes Objekt im gameObjectsArray durch, bestimmt die Koordinaten und platziert dann das Objekt an der Stelle

		for (GameObject elem : gameObjectsArray) {
			int x = elem.getLocation().getX();
			int y = elem.getLocation().getY();

			board[x][y] = elem;
		}
		
		System.out.println("Verlassen des FlattenedBoard-Konstruktors");
	}

	// alle Bewegungsregeln der Objekte...
	@Override

	public void move(GameObject gameObject, XY direction) {
		if (gameObject instanceof Bomb && !(gameObjectSet.contains(gameObject))) {
			Bomb bomb = (Bomb) gameObject;

			if (bomb.getOwner() == null) {
				gameObjectSet.addObject(bomb);
				board[bomb.getLocation().getX()][bomb.getLocation().getY()] = bomb;
				return;
			}

			// check nuke-count
			if (bomb.getOwner().getBombCount() >= bomb.getOwner().getAllowedBombs()) {
				System.out.println(bomb.getOwner().getAllowedBombs() + "Erlaubte Nukes");
				throw new NoMoreBombsAllowed("Alle Nukes wurde schon gezündet!");
			}

			// nuke wird geplaced
			gameObjectSet.addObject(bomb);
			board[bomb.getLocation().getX()][bomb.getLocation().getY()] = bomb;
			bomb.getOwner().addToBombCount(1);
			return;
		}

		if (gameObject instanceof Blast && !gameObjectSet.contains(gameObject)) {
			gameObjectSet.addObject(gameObject);
			return;
		}

		System.out.println("GameObject-location: " + gameObject.getLocation().toString());
		System.out.println(" move-direction: " + direction.toString());
		XY location = gameObject.getLocation().move(direction);
		System.out.println(" Versuch zur neuen location zu kommen: " + location.toString());

		int currX = gameObject.getLocation().getX();
		int currY = gameObject.getLocation().getY();
		int newX = location.getX();
		int newY = location.getY();

		if (newX < 0 || newX > board.length - 1 || newY < 0 || newY > board[0].length - 1) {
			System.out.println("Versucht ausserhalb des Spielfelds sich hinzubewegen!");
			return;
		}

		if (gameObject instanceof Bomberman || gameObject instanceof Monster) {
			if (Math.abs(currX - newX) + Math.abs(currY - newY) > 1) {
				System.out.println("Exception moveMoreThanOneBlock! GameObject: " + gameObject.toString() + "; location: " + location.toString());
				throw new IllegalMoveException("Illegale Bewegung: Versuch, mehr als einen Block zu gehen!");
			}

			if (board[newX][newY] == null) {
				System.out.println("Bewegung auf null location!");
				System.out.println(gameObject.getLocation().toString() + " x-length: " + board.length + " y-length: " + board[0].length);
				// Update alte posi
				board[currX][currY] = null;
				// move auf neue posi
				board[newX][newY] = gameObject;
				gameObject.setLocation(location);
				return;
			}

			if (board[newX][newY] instanceof Wall) {
				System.out.println("Versucht auf eine Wall zu steppen!");
				return;
			}

		} else {
			System.out.println("Exception moveStaticObjectOneBlock! GameObject: " + gameObject.toString() + "; location " + location.toString());
			throw new IllegalMoveException("Illegale Bewegung: Versuch, ein stationäres Objekt zu bewegen!");
		}

		if (gameObject instanceof Bomberman) {
			if (board[newX][newY] instanceof PowerUp) {
				System.out.println("Auf PowerUp bewegt: " + board[newX][newY]);

				board[currX][currY] = null;
				((PowerUp) board[newX][newY]).applyPowerUp((Bomberman) gameObject);
				gameObjectSet.removeObject(board[newX][newY]);
				gameObject.setLocation(location);

				return;
			}

			if (board[newX][newY] instanceof DestructibleWall) {
				System.out.println("Versucht auf eine DestructibleWall zu steppen!");
				gameObject.updatePoints(board[newX][newY].getPoints() * -1);
				return;
			}

			if (board[newX][newY] instanceof Blast) {
				System.out.println("Bewegt auf einen Blast!");
				board[currX][currY] = null;

				System.out.println(board[newX][newY].toString() + " Owner: " + ((Blast) board[newX][newY]).getOwner() + " walked on by " + gameObject);

				((Bomberman) gameObject).updatePoints(-1 * board[newX][newY].getPoints());

				if (!gameObject.equals(((Blast) board[newX][newY]).getOwner()) && ((Blast) board[newX][newY]).getOwner() != null)
					((Blast) board[newX][newY]).getOwner().updatePoints(board[newX][newY].getPoints());
				

				System.out.println("Owner: " + ((Blast) board[newX][newY]).getOwner() + " vorbeigegangen an " + gameObject.toString());

				if (gameObjectSet.contains(board[newX][newY])) gameObjectSet.removeObject(board[newX][newY]);
				

				board[newX][newY] = gameObject;
				gameObject.setLocation(location);
				return;
			}
		}

		if (gameObject instanceof Monster && board[newX][newY] instanceof Blast) {
			if (((Blast) board[newX][newY]).getOwner() != null) ((Blast) board[newX][newY]).getOwner().updatePoints(gameObject.getPoints());
			

			gameObjectSet.removeObject(gameObject);
			return;
		}

		System.out.println("Bomberman oder Monster bewegen sich nicht: GameBoard content an neuer location: " + board[newX][newY].toString());
	}

	/* Gibt das Spielobjekt an den angegebenen Koordinaten zurück.
	 * x = die x-Koordinate
	 * y = die y-Koordinate
	 */
	@Override
	public GameObject getGameObject(int x, int y) {
		return board[x][y];
	}

	@Override
	public XY getSize() {
		return size;
	}

	@Override
	public GameObject[][] getGameBoard() {
		return board;
	}

	// nimmt die Anweisungen aus createBlast und gibt der Bombe nach den 5 Runden dann die Blasts mit den richtigen Richtungen
	@Override
	public void explode(Bomb nuke) {
		System.out.println("Nuke explodiert! Die " + nuke + " gehört " + nuke.getOwner());
		createBlast(nuke, 0, 0);
		createBlast(nuke, 0, -1);
		createBlast(nuke, 1, 0);
		createBlast(nuke, 0, 1);
		createBlast(nuke, -1, 0);
	}

	// erstellt die Blastregeln, also die Regeln für Kontakte und dem Punktesystem

	private void createBlast(Bomb nuke, int xModifier, int yModifier) {
		int xOrigin = nuke.getLocation().getX();
		int yOrigin = nuke.getLocation().getY();

		int bombStrength = (nuke.getOwner() != null) ? nuke.getOwner().getBombStrength() : 1;

		if (xModifier == 0 && yModifier == 0) {
			if (nuke.getOwner() != null) {
				nuke.getOwner().addToBombCount(-1);
				System.out.println("Bomb wurde vom GameObjectSet entfernt " + nuke.getID() + " bombcount owner: " + nuke.getOwner().getBombCount());
			} else {
				System.out.println("Bomb wurde vom GameObjectSet entfernt " + "NoOwner");
			}

			gameObjectSet.removeObject(nuke);

			Blast blast = new Blast(new XY(xOrigin, yOrigin), nuke.getOwner());
			gameObjectSet.addObject(blast);
			board[xOrigin][yOrigin] = blast;
			return;
		}

		for (int i = 1; i <= bombStrength; i++) {
			int x = xOrigin + i * xModifier;
			int y = yOrigin + i * yModifier;

			if (x < 0 || x > board.length - 1 || y < 0 || y > board[0].length - 1) {
				System.out.println("Blast erreicht border!");
				return;
			}

			System.out.println("BombStrength vor der Blast erschaffung: " + bombStrength);
			GameObject gameObject = board[x][y];
			System.out.println("Auf blast-space: " + gameObject);
			Blast blast = new Blast(new XY(x, y), nuke.getOwner());

			if (gameObject instanceof Monster || gameObject instanceof DestructibleWall || gameObject instanceof PowerUp
					|| gameObject == null) {
				if (nuke.getOwner() != null && gameObject != null) {
					nuke.getOwner().updatePoints(gameObject.getPoints());
					
					// zähler wird für walls und destructobleWalls aktuallisiert
					if(gameObject instanceof Monster) {
						nuke.getOwner().incrementDestroyedMonsters();;
					} else if (gameObject instanceof DestructibleWall) {
						nuke.getOwner().incrementDestroyedDestructibleWalls();;
					}
					
					gameObjectSet.removeObject(gameObject);
				}

				gameObjectSet.addObject(blast);
				board[x][y] = blast;
			}

			if (gameObject instanceof Bomberman) {
				((Bomberman) gameObject).updatePoints(-1 * blast.getPoints());
				return;
			}

			if (gameObject instanceof Wall || gameObject instanceof Bomberman || gameObject instanceof DestructibleWall) return;
			
		}
	}

	// entfernt nach 2 runden dann die Blasts wieder vom board
	@Override
	public void removeBlast(Blast blast) {
		gameObjectSet.removeObject(blast);
	}
}
