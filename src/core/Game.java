package core;


public abstract class Game 
{
	protected State state;
	protected UI ui;
	protected final int fps;
	
	public Game(State state, UI ui, int fps)
	{
		this.state = state;
		this.ui = ui;
		this.fps = fps;
	}
	
	public void run() 
	{
		while(true)
		{
			render();
			processInput();
			update();
			
			try {
				Thread.sleep(1000/this.fps);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	protected void render()
	{
		BoardView boardView = state.flattenBoard();
		//System.out.println(boardView.toString());
		ui.render(boardView);
	}
	
	protected void processInput()
	{
		/*Entity[] entities = this.state.getBoard().getEntitySet().getEntities();
		XY next = ui.getCommand();
		System.out.println(next.toString());
		for(int i = 0; i < entities.length; i++)
		{
			if(entities[i] instanceof HandOperatedMasterSquirrel)
			{
				((HandOperatedMasterSquirrel)entities[i]).setNextMove(next);
			}
		}*/
		
	}
	
	protected void update()
	{	
		state.update();
	}
	
	protected int getFps()
	{
		return this.fps;
	}
}
