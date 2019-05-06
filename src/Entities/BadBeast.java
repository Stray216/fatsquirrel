package Entities;

import java.util.Random;

import core.EntityContext;
import core.XY;

public class BadBeast extends Beast
{
	private static final int energy = -150;
	private int stun = 0;
	private int bites = 7;
	
	public BadBeast(int id, XY pos)
	{
		super(id, energy, pos);
	}
	public BadBeast(int id, int x, int y) 
	{
		super(id, energy, new XY(x,y));
	}
	
	public String toString()
	{
		String s = "BadBeast: ";
		s += super.toString();
		return s;
	}
	
	public void updateBites()
	{
		bites = bites - 1;
	}
	
	public void nextStep(EntityContext entityContext)
	{
		Entity ent = entityContext.nearestPlayerEntity(this.getPos());
		
		if(ent != null)
		{
			
			int i = ent.getPos().getX() - this.getPos().getX();
			i = (i < 0) ? i*(-1) : i;
			int j = ent.getPos().getY() - this.getPos().getY();
			j = (j < 0) ? j*(-1) : j;
			
			if(this.stun == 0)
			{
				if((i + j) <= 6)
				{
					i = ent.getPos().getX() - this.getPos().getX();
					j = ent.getPos().getY() - this.getPos().getY();
					
					if(i == 0)
					{
						if(j == 0)
						{
							//Doesn't happen
						}
						else if(j < 0)
						{	
							XY a = new XY(this.getPos().getX(), this.getPos().getY() - 1);
							if(entityContext.getEntityType(a) instanceof MasterSquirrel || entityContext.getEntityType(a) instanceof HandOperatedMasterSquirrel || entityContext.getEntityType(a) instanceof MiniSquirrel)
							{
								this.updateBites();
								entityContext.getEntityType(a).updateEnergy(this.getEnergy());
							}
							else
							{
								entityContext.tryMove(this, new XY(0, -1));
							}
						}
						else if(j > 0)
						{
							XY a = new XY(this.getPos().getX(), this.getPos().getY() + 1);
							if(entityContext.getEntityType(a) instanceof MasterSquirrel || entityContext.getEntityType(a) instanceof HandOperatedMasterSquirrel || entityContext.getEntityType(a) instanceof MiniSquirrel)
							{
								this.updateBites();
								entityContext.getEntityType(a).updateEnergy(this.getEnergy());
							}
							else
							{
								entityContext.tryMove(this, new XY(0, 1));
							}
						}
					}
					else if(i < 0)
					{
						if(j == 0)
						{
							XY a = new XY(this.getPos().getX() - 1, this.getPos().getY());
							if(entityContext.getEntityType(a) instanceof MasterSquirrel || entityContext.getEntityType(a) instanceof HandOperatedMasterSquirrel || entityContext.getEntityType(a) instanceof MiniSquirrel)
							{
								this.updateBites();
								entityContext.getEntityType(a).updateEnergy(this.getEnergy());
							}
							else
							{
								entityContext.tryMove(this, new XY(-1, 0));
							}
						}
						else if(j < 0)
						{
							XY a = new XY(this.getPos().getX() - 1, this.getPos().getY() - 1);
							if(entityContext.getEntityType(a) instanceof MasterSquirrel || entityContext.getEntityType(a) instanceof HandOperatedMasterSquirrel || entityContext.getEntityType(a) instanceof MiniSquirrel)
							{
								this.updateBites();
								entityContext.getEntityType(a).updateEnergy(this.getEnergy());
							}
							else
							{
								entityContext.tryMove(this, new XY(-1, -1));
							}
						}
						else if(j > 0)
						{
							XY a = new XY(this.getPos().getX() - 1, this.getPos().getY() + 1);
							if(entityContext.getEntityType(a) instanceof MasterSquirrel || entityContext.getEntityType(a) instanceof HandOperatedMasterSquirrel || entityContext.getEntityType(a) instanceof MiniSquirrel)
							{
								this.updateBites();
								entityContext.getEntityType(a).updateEnergy(this.getEnergy());
							}
							else
							{
								entityContext.tryMove(this, new XY(-1, 1));
							}
						}
					}
					else if(i > 0)
					{
						if(j == 0)
						{
							XY a = new XY(this.getPos().getX() + 1, this.getPos().getY());
							if(entityContext.getEntityType(a) instanceof MasterSquirrel || entityContext.getEntityType(a) instanceof HandOperatedMasterSquirrel || entityContext.getEntityType(a) instanceof MiniSquirrel)
							{
								this.updateBites();
								entityContext.getEntityType(a).updateEnergy(this.getEnergy());
							}
							else
							{
								entityContext.tryMove(this, new XY(1, 0));
							}
						}
						else if(j < 0)
						{
							XY a = new XY(this.getPos().getX() + 1, this.getPos().getY() - 1);
							if(entityContext.getEntityType(a) instanceof MasterSquirrel || entityContext.getEntityType(a) instanceof HandOperatedMasterSquirrel || entityContext.getEntityType(a) instanceof MiniSquirrel)
							{
								this.updateBites();
								entityContext.getEntityType(a).updateEnergy(this.getEnergy());
							}
							else
							{
								entityContext.tryMove(this, new XY(1, -1));
							}
						}
						else if(j > 0)
						{
							XY a = new XY(this.getPos().getX() + 1, this.getPos().getY() + 1);
							if(entityContext.getEntityType(a) instanceof MasterSquirrel || entityContext.getEntityType(a) instanceof HandOperatedMasterSquirrel || entityContext.getEntityType(a) instanceof MiniSquirrel)
							{
								this.updateBites();
								entityContext.getEntityType(a).updateEnergy(this.getEnergy());
							}
							else
							{
								entityContext.tryMove(this, new XY(1, 1));
							}
						}
					}
				}
				else
				{
					Random rand = new Random();
					
					int newX = rand.nextInt(3) - 1;
					int newY = rand.nextInt(3) - 1;
					XY a = new XY(newX, newY);
					
					entityContext.tryMove(this, a);
				}
				
				this.stun += 4;
			}
		}
		else if(this.stun == 0)
		{
			Random rand = new Random();
			
			int newX = rand.nextInt(3) - 1;
			int newY = rand.nextInt(3) - 1;
			XY a = new XY(newX, newY);
			
			entityContext.tryMove(this, a);
			this.stun += 4;
		}
		
		if(stun > 0)
		{
			this.stun -= 1;
		}
		
		if(this.bites <= 0)
		{
			entityContext.killAndReplace(this);
		}
	}
}
