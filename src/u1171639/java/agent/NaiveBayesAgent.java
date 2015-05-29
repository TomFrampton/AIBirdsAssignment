package u1171639.java.agent;

import u1171639.java.game.Game;

public class NaiveBayesAgent implements Agent {
	private Game game;
	
	@Override
	public String getName() {
		return "Naive Bayes Agent";
	}

	@Override
	public String getDescription() {
		return "Uses Naive Bayes to learn.";
	}

	@Override
	public void run() {
		System.out.println(this.getName() + " running!");
	}

	@Override
	public void setGame(Game game) {
		this.game = game;
	}

}
