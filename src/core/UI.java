package core;


public interface UI 
{
	void setCommand();
	Command getCommand();	
	void render(BoardView view);
}