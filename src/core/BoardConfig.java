package core;


public class BoardConfig 
{
	private XY size;
	private int wallCount;
	private int plantCount;
	private int beastCount;
	
	public BoardConfig(XY size, int wallCount, int plantCount, int beastCount)
	{
		this.size = size;
		this.wallCount = wallCount;
		this.plantCount = plantCount;
		this.beastCount = beastCount;
	}
	
	//Default Konstruktor
	public BoardConfig()
	{
		this.size = new XY(20, 20);
		this.wallCount = 0;
		this.plantCount = 3;
		this.beastCount = 4;
	}
	
	public XY getSize()
	{
		return size;
	}
	
	public int getWallCount()
	{
		return wallCount;
	}
	
	public int getPlantCount()
	{
		return plantCount;
	}
	
	public int getBeastCount()
	{
		return beastCount;
	}
}
