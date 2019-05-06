package core;

import Entities.Entity;

public interface BoardView 
{
	public Entity getEntityType(int x, int y);
	public XY getSize();
	public String toString();
	
}