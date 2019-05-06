package core;

import Entities.BadBeast;
import Entities.Entity;
import Entities.GoodBeast;
import Entities.MasterSquirrel;
import Entities.MiniSquirrel;

public interface EntityContext 
{
	XY getSize();
	void tryMove(MiniSquirrel miniSquirrel, XY direction);
	void tryMove(GoodBeast goodBeast, XY direction);
	void tryMove(BadBeast badBeast, XY direction);
	void tryMove(MasterSquirrel masterSquirrel, XY direction);
	Entity nearestPlayerEntity(XY pos);
	void kill(Entity entity);
	void killAndReplace(Entity entity);
	Entity getEntityType(XY pos);
}
