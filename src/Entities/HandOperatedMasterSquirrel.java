package Entities;

import core.EntityContext;
import core.XY;

public class HandOperatedMasterSquirrel extends MasterSquirrel 
{
	private int stun = 0;
	private XY nextMove;
	
	public HandOperatedMasterSquirrel(int id, XY pos)
	{
		super(id, pos);
	}
	public HandOperatedMasterSquirrel(int id, int x, int y) 
	{
		super(id, new XY(x,y));
	}
	
	public String toString()
	{
		String s = "HandOperated";
		s += super.toString();
		return s;
	}
	
	public void setNextMove(XY pos)
	{
		this.nextMove = pos;
	}
	
	public void nextStep(EntityContext entityContext)
	{
		XY pos = new XY(0,0);
		if(this.nextMove != null)
		{
			pos = this.nextMove;
		}
		System.out.println(pos.toString());
		
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
		
		this.nextMove = new XY(0,0);
	}
}

