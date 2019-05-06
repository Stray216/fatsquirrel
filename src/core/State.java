package core;


public class State 
{
	private int highScore = 1000;
	private Board board;
	
	public State(Board board)
	{
		this.board = board;
	}
	
	public FlattenedBoard flattenBoard()
	{
		return this.board.flatten();
	}
	
	public void update()
	{
		board.update();
	}
	
	public Board getBoard()
	{
		return this.board;
	}
}
