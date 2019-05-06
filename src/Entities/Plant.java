package Entities;

import core.XY;

public abstract class Plant extends Entity
{
	public Plant(int id, int energy, XY pos)
	{
		super(id, energy, pos);
	}
	
	public void nextStep()
	{
		// Do nothing yet ...
	}
}
