package BotAPI;

public interface BotControllerFactory 
{
	public BotController CreateMasterBotController();
	public BotController CreateMiniBotController();
	
}
