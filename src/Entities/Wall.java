package Entities;

import core.XY;

public class Wall extends Entity
{
	private static final int energy = -10;
	
	public Wall(int id, XY pos)
	{
		super(id, energy, pos);
	}
	public Wall(int id, int x, int y) 
	{
		super(id, energy, new XY(x,y));
	}
	
	public void nextStep()
	{
		// do nothing yet ...
	}
	
	public String toString()
	{
		String s = "Wall: ";
		s += super.toString();
		return s;
	}
	
}
