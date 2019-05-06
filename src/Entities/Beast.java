package Entities;

import core.XY;

public abstract class Beast extends Entity
{
	
	public Beast(int id, int energy, XY pos)
	{
		super(id, energy, pos);
	}

	public void nextStep(int x, int y)
	{
		getPos().move(x, y);
	}
	
}
