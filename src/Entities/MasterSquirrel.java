package Entities;

import core.EntityContext;
import core.XY;

public class MasterSquirrel extends Squirrel
{
	private static int DefaultEnergy =  1000;
	private boolean miniRequest = false;
	private int miniEnergy;
	private XY miniDirection;
	private int stun = 0;
	
	public MasterSquirrel(int id, XY pos)
	{
		super(id, DefaultEnergy, pos);
	}
	public MasterSquirrel(int id, int x, int y) 
	{
		super(id, DefaultEnergy, new XY(x,y));
	}
	
	public void makeMini(int eng, XY direction)
	{
		if(this.getEnergy() < eng)
		{
			System.out.println("Not Enough ENERGY!");
		}
		else
		{
			setMiniRequest(true);
			miniEnergy = eng;
			this.miniDirection = direction;
		}
	}
	
	public void makeMini(int eng)
	{
		if(this.getEnergy() < eng)
		{
			System.out.println("Not Enough ENERGY!");
		}
		else
		{
			setMiniRequest(true);
			miniEnergy = eng;
			this.miniDirection = new XY(0,1);
		}
	}
	
	public boolean getMiniRequest()
	{
		return miniRequest;
	}
	
	public void setMiniRequest(boolean req)
	{
		this.miniRequest = req;
	}
	
	public int getMiniEnergy()
	{
		return miniEnergy;
	}
	
	public XY getMiniDirection()
	{
		return this.miniDirection;
	}
	
	public String toString()
	{
		String s = "MasterSquirrel: ";
		s += super.toString();
		return s;
	}
	
	public void nextStep(EntityContext entityContext, XY pos)
	{
		if(stun == 0)
		{
			XY a = new XY(this.getPos().getX() + pos.getX(), this.getPos().getY() + pos.getY());
			Entity temp = entityContext.getEntityType(a);
			if(temp == null)
			{
				entityContext.tryMove(this, pos);
			}
			else if(temp instanceof MiniSquirrel)
			{
				if(((MiniSquirrel)temp).getMaster() == this.getID())
				{
					this.updateEnergy(temp.getEnergy());
					entityContext.kill(temp);
					entityContext.tryMove(this, pos);
				}
				else
				{
					this.updateEnergy(150);
					entityContext.kill(temp);
					entityContext.tryMove(this, pos);
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
		}
		
		if(stun > 0)
		{
			stun --;
		}
	}
}
