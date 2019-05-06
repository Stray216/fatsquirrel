package core;

import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Launcher extends Application
{
	public static void main(String[] args)
	{
		//game1();
		game2();
		//Application.launch(args);
	}
	
	@Override
	public void start(Stage primaryStage)
	{
		
		final Game game = new GameImpl3();
		FxUI fxUI = FxUI.createInstance(game.state.getBoard().getSize());
         
        primaryStage.setScene(fxUI);
        primaryStage.setTitle("Diligent Squirrel");
        fxUI.getWindow().setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent evt) {
                System.exit(-1);     
            }
        });
        primaryStage.show();   
        
        startGame(1000, game);
	}
	
	
	
	public static void game1()
	{
		GameImpl1 game = new GameImpl1();
		game.run();
	}
	
	public static void game2()
	{
		GameImpl2 game = new GameImpl2();
		startGame(1000, game);
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			
			@Override
			public void run()
			{
				game.ui.setCommand();
			}
		}, 0, game.getFps());
	}
	
	
	
	public static void startGame(long time, Game game)
	{
		try 
		{
			Thread.sleep(time);
			
		} catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			
			@Override
			public void run()
			{
				game.run();
			}
		}, 0);
	}
}
