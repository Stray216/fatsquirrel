package BotAPI;

import core.XY;

public interface ControllerContext 
{
	public XY getViewLowerLeft();
	public XY getViewUpperRight();
	public EntityType getEntityAt(XY pos);
	public void move(XY direction);
	public void spawnMiniBot(XY direction, int energy);
	public int getEnergy();
}
