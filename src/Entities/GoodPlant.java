package Entities;

import core.XY;

public class GoodPlant extends Plant 
{
	private static final int energy = 100;
	
	public GoodPlant(int id, XY pos)
	{
		super(id, energy, pos);
	}
	public GoodPlant(int id, int x, int y) 
	{
		super(id, energy, new XY(x,y));
	}
	
	public String toString()
	{
		String s = "GoodPlant: ";
		s += super.toString();
		return s;
	}
}
