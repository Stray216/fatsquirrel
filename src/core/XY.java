package core;


public final class XY 
{
	private final int x;
	private final int y;
	
	public XY(int x,int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public XY move(int x, int y)
	{
		XY a = new XY(this.x + x,this.y + y);
		return a;
	}
	
	public XY move(XY pos)
	{
		XY a = new XY(this.x + pos.getX(), this.y + pos.getY());
		return a;
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
	public boolean equals(XY pos)
	{
		if(this.getX() == pos.getX() && this.getY() == pos.getY())
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public String toString()
	{
		String s = this.getX() + "/" + this.getY();
		return s;
	}
}
