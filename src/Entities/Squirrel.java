package Entities;

import core.XY;

public abstract class Squirrel extends Entity
{
	
	public Squirrel(int id, int energy, XY pos)
	{
		super(id, energy, pos);
	}
	
	public void nextStep(int x, int y)
	{
		this.getPos().move(x,y);
	}
	
	public void nextStep(XY pos)
	{
		this.getPos().move(pos);
	}
	
}
