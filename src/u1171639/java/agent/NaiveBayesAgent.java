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
	public void setGame(Game game) {
		this.game = game;
	}

	@Override
	public void runContinuously() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void runOneLevel() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int shotsTakenForCurrentLevel() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int totalShotsTaken() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int levelsCompleted() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double averageShotsPerLevel() {
		// TODO Auto-generated method stub
		return 0;
	}

}
