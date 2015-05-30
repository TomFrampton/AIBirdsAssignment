package u1171639.java.agent;

import u1171639.java.game.Game;

public interface Agent {

	public String getName();
	public String getDescription();
	
	public void setGame(Game game);
	
	public void runContinuously();
	public void runOneLevel();
	
}
