package Entities;

import core.EntityContext;
import core.XY;

public class MiniSquirrel extends Squirrel
{
	private int masterID;
	private int stun = 0;
	
	public MiniSquirrel(int id, int energy, XY pos, int masterID)
	{
		super(id, energy, pos);
		this.masterID = masterID;
	}
	public MiniSquirrel(int id, int energy, int x, int y, int masterID) 
	{
		super(id, energy, new XY(x,y));
		this.masterID = masterID;
	}
	
	public int getMaster()
	{
		return masterID;
	}
	
	public String toString()
	{
		String s = "MiniSquirrel: ";
		s += super.toString();
		return s;
	}
	
	public void nextStep(EntityContext entityContext, XY pos)
	{
		if(this.getEnergy() > 0)
		{
			if(stun == 0)
			{
				XY a = new XY(this.getPos().getX() + pos.getX(), this.getPos().getY() + pos.getY());
				Entity temp = entityContext.getEntityType(a);
				if(temp == null)
				{
					entityContext.tryMove(this, pos);
				}
				else if(temp instanceof MasterSquirrel)
				{
					if(this.masterID == temp.getID())
					{
						temp.updateEnergy(this.getEnergy());
						entityContext.kill(this);
					}
					else
					{
						entityContext.kill(this);
					}
				}
				else if(temp instanceof MiniSquirrel)
				{
					if(this.masterID != ((MiniSquirrel)temp).getMaster())
					{
						entityContext.kill(this);
						entityContext.kill(temp);
					}
				}
				else if(temp instanceof Wall)
				{
					this.stun += 3;
				}
				else if(temp instanceof GoodPlant || temp instanceof BadPlant || temp instanceof GoodBeast)
				{
					this.updateEnergy(temp.getEnergy());
					entityContext.killAndReplace(temp);
					entityContext.tryMove(this, pos);
				}
				else if(temp instanceof BadBeast)
				{
					this.updateEnergy(temp.getEnergy());
					((BadBeast)temp).updateBites();
				}
				else
				{
					entityContext.tryMove(this, pos);
				}
				
				this.updateEnergy(-1);
			}
			
			if(stun > 0)
			{
				stun --;
			}
		}
		else
		{
			entityContext.kill(this);
		}
	}
}
