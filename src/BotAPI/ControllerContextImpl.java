package BotAPI;

import Entities.Entity;
import Entities.MasterSquirrel;
import core.EntityContext;
import core.XY;

public class ControllerContextImpl implements ControllerContext
{
	private EntityContext entityContext;
	private Entity entity;
	
	public ControllerContextImpl(EntityContext entityContext, Entity entity)
	{
		this.entityContext = entityContext;
		this.entity = entity;
	}
	
	public XY getViewLowerLeft()
	{
		XY pos = new XY(entity.getPos().getX()-3, entity.getPos().getY()+3);
		return pos;
	}
	
	public XY getViewUpperRight()
	{
		XY pos = new XY(entity.getPos().getX()+3, entity.getPos().getY()-3);
		return pos;
	}
	
	public EntityType getEntityAt(XY pos)
	{
		if(pos.getX() < this.getViewUpperRight().getX() && pos.getX() > this.getViewLowerLeft().getX() && pos.getY() < this.getViewLowerLeft().getY() && pos.getY() > this.getViewUpperRight().getY())
		{
			for(int i = 0; i < EntityType.values().length; i++)
			{
				if(entityContext.getEntityType(pos).getClass() == EntityType.values()[i].getParamType())
				{
					return EntityType.values()[i];
				}
			}
		}
		
		System.out.println("Position außer Sichtweite!");
		return null;
	}
	
	public void move(XY direction)
	{
		this.entity.move(direction);
	}
	
	public void spawnMiniBot(XY direction, int energy)
	{
		if(this.entity instanceof MasterSquirrel)
		{
			((MasterSquirrel) entity).makeMini(energy, direction);
		}
	}
	
	public int getEnergy()
	{
		return this.entity.getEnergy();
	}
}
