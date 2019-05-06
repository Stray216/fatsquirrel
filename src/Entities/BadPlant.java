package Entities;

import core.XY;

public class BadPlant extends Plant
{
	private static final int energy = -100;
	
	public BadPlant(int id, XY pos)
	{
		super(id, energy, pos);
	}
	public BadPlant(int id, int x, int y) 
	{
		super(id, energy, new XY(x,y));
	}
	
	public String toString()
	{
		String s = "BadPlant: ";
		s += super.toString();
		return s;
	}
}
