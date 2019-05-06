package Entities;

import java.util.Random;

import core.EntityContext;
import core.XY;

public abstract class Entity 
{
	private final int id;
	private int energy;
	private XY pos;
	private XY previousPos;
	
	
	public Entity(int id, int energy,XY pos)
	{
		this.id = id;
		this.energy = energy;
		this.pos = pos;
		XY pos2 = new XY(0,0);
		this.previousPos = pos2;
	}
	
	public void updateEnergy(int wert)
	{
		if((energy + wert)< 0)
		{
			this.energy = 0;
		}
		else
		{
			this.energy += wert;
		}
	}
	
	public void nextStep(EntityContext entityContext)
	{
		// Individuell in den Unterklassen definiert.
	}
	
	public int getID()
	{
		return id;
	}
	
	public int getEnergy()
	{
		return energy;
	}
	
	public void move(int x, int y)
	{
		this.pos = this.getPos().move(x, y);
	}
	
	public void move(XY pos)
	{
		this.pos = this.getPos().move(pos);
	}
	
	public void moveRandom()
	{
		Random rand = new Random();
		
		int newX = rand.nextInt(3) - 1;
		int newY = rand.nextInt(3) - 1;
		this.move(newX, newY);
	}
	
	public void setPos(XY pos)
	{
		this.pos = this.getPos().move(pos);
	}
	
	public XY getPos()
	{
		return pos;
	}
	
	public String toString()
	{
		String s = "";
		s = "ID: " + this.getID() +  " Energy: " + this.getEnergy() + "  Position x/y: " + this.getPos().getX() + "/" + this.getPos().getY();
		return s;
	}
	
	public boolean equals(Entity bla)
	{
		return bla.getClass() == this.getClass();
	}
	
	public void setPreviousPos(XY pos)
	{
		this.previousPos = new XY(pos.getX(),pos.getY());	
	}
	
	public XY getPreviousPos()
	{
		return this.previousPos;
	}
	
}
