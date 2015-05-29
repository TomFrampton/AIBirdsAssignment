package u1171639.java.agent;

import u1171639.java.game.Game;

public interface Agent extends Runnable {

	public String getName();
	public String getDescription();
	
	public void setGame(Game game);
}
