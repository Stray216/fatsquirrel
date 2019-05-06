package core;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import Entities.BadBeast;
import Entities.BadPlant;
import Entities.Entity;
import Entities.GoodBeast;
import Entities.GoodPlant;
import Entities.HandOperatedMasterSquirrel;
import Entities.MasterSquirrel;
import Entities.MiniSquirrel;
import Entities.Wall;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class FxUI extends Scene implements UI 
{

	private BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
	protected static Command lastCommand;
	private Canvas boardCanvas;
	private Label msgLabel;
	private static final double CELL_SIZE = 20;

    public FxUI(Parent parent, Canvas boardCanvas, Label msgLabel) {
        super(parent);
        this.boardCanvas = boardCanvas;
        this.msgLabel = msgLabel;
    }
    
    public static FxUI createInstance(XY boardSize) 
    {
        Canvas boardCanvas = new Canvas(boardSize.getX() * CELL_SIZE, boardSize.getY() * CELL_SIZE);
        Label statusLabel = new Label();
        VBox top = new VBox();
        top.getChildren().add(boardCanvas);
        top.getChildren().add(statusLabel);
        statusLabel.setText("Yeet");
        final FxUI fxUI = new FxUI(top, boardCanvas, statusLabel); 
        fxUI.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
                   /*@Override
                   public void handle(KeyEvent keyEvent) {
                      System.out.println("Es wurde folgende Taste gedrückt: " + keyEvent.getCode() + " bitte behandeln!");
                      // TODO handle event 
                   }*/
                	switch(event.getCode())
                	{
                		case UP: 
                		{
                			lastCommand = new Command(GameCommandType.UP);
                			break;
                		}
                		case LEFT:
                		{
                			lastCommand = new Command(GameCommandType.LEFT);
                			break;
                		}
                		case DOWN:
                		{
                			lastCommand = new Command(GameCommandType.DOWN);
                			break;
                		}
                		case RIGHT:
                		{
                			lastCommand = new Command(GameCommandType.RIGHT);
                			break;
                		}
                		case H:
                		{
                			lastCommand = new Command(GameCommandType.HELP);
                			break;
                		}
                		case A:
                		{
                			lastCommand = new Command(GameCommandType.ALL);
                			break;
                		}
                		case E:
                		{
                			lastCommand = new Command(GameCommandType.MASTER_ENERGY);
                			break;
                		}
                		case M:
                		{
                			Object[] obj = new Object[1];
                			obj[0] = new Integer(200);
                			lastCommand = new Command(GameCommandType.SPAWN_MINI, obj);
                			break;
                		}
                		default:
                		{
                			break;
                		}
                	}

                });
        return fxUI;
    }

    

    @Override
    public void render(final BoardView view) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                repaintBoardCanvas(view);            
            }      
        });  
    }
    
    private void repaintBoardCanvas(BoardView view) {
        GraphicsContext gc = boardCanvas.getGraphicsContext2D();
        gc.clearRect(0, 0, boardCanvas.getWidth(), boardCanvas.getHeight());
        XY viewSize = view.getSize();
        
        int x = viewSize.getX();
		int y = viewSize.getY();
		
		for(int i = 0; i < y; i++)
		{
			for(int j = 0; j < x; j++)
			{
				Entity temp = view.getEntityType(j, i);
				
				if(temp instanceof Wall){
					gc.setFill(Color.ORANGE);
			        gc.fillRect(i*CELL_SIZE, j*CELL_SIZE, CELL_SIZE, CELL_SIZE);
				}
				else if(temp instanceof GoodPlant){
					gc.setFill(Color.GREEN);
			        gc.fillRect(i*CELL_SIZE, j*CELL_SIZE, CELL_SIZE, CELL_SIZE);
				}
				else if(temp instanceof BadPlant){
					gc.setFill(Color.RED);
			        gc.fillRect(i*CELL_SIZE, j*CELL_SIZE, CELL_SIZE, CELL_SIZE);
				}
				else if(temp instanceof GoodBeast){
					gc.setFill(Color.GREEN);
			        gc.fillOval(i*CELL_SIZE, j*CELL_SIZE, CELL_SIZE, CELL_SIZE);
				}
				else if(temp instanceof BadBeast){
					gc.setFill(Color.RED);
			        gc.fillOval(i*CELL_SIZE, j*CELL_SIZE, CELL_SIZE, CELL_SIZE);
				}
				else if(temp instanceof MasterSquirrel || temp instanceof HandOperatedMasterSquirrel){
					gc.setFill(Color.BLUE);
			        gc.fillRect(i*CELL_SIZE, j*CELL_SIZE, CELL_SIZE, CELL_SIZE);
				}
				else if(temp instanceof MiniSquirrel){
					gc.setFill(Color.BLUE);
			        gc.fillOval(i*CELL_SIZE, j*CELL_SIZE, CELL_SIZE, CELL_SIZE);
				}
				else if(temp == null){
					gc.setFill(Color.TRANSPARENT);
			        gc.fillRect(i*CELL_SIZE, j*CELL_SIZE, CELL_SIZE, CELL_SIZE);
				}
			}
		}
    }
    
    public void message(final String msg) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                msgLabel.setText(msg);            
            }      
        });         
    }
    
    public Command getCommand()
	{
		Command command = lastCommand;
		lastCommand = null;
		return command;
	}
    
    public void setCommand()
	{
		CommandScanner commandScanner = new CommandScanner(GameCommandType.values(), inputReader);
		Command command = commandScanner.next();
		lastCommand = command;
	}
}