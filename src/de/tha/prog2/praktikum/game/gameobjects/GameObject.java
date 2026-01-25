package de.tha.prog2.praktikum.game.gameobjects;

import de.tha.prog2.praktikum.game.core.GameObjectContext;
import de.tha.prog2.praktikum.game.util.XY;

public interface GameObject {

	public XY getLocation();

	public void setLocation(XY location);

	public void updatePoints(int points);

	public int getID();

	public int getPoints();

	@Override
	public boolean equals(Object gameObject);

	public void nextStep(GameObjectContext context);

	@Override
	public String toString();
}