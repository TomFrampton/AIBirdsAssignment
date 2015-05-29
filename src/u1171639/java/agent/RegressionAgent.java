package u1171639.java.agent;

import u1171639.java.game.Game;

public class RegressionAgent implements Agent {
	private Game game;
	
	public RegressionAgent() {
		
	}
	
	@Override
	public String getName() {
		return "Regression Agent";
	}

	@Override
	public String getDescription() {
		return "Learns using regression";
	}

	@Override
	public void run() {
		System.out.println(this.getName() + " running!");
	}
	
	public void setGame(Game game) {
		this.game = game;
	}

}
