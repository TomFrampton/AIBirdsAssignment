package u1171639.java.agent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import u1171639.java.game.Game;
import u1171639.java.game.Level;

public class RegressionAgent implements Agent {
	private Game game;
	
	private List<Instance> trainingExamples = new ArrayList<Instance>();
	
	// TODO
	private Random random = new Random(System.currentTimeMillis());
	
	// Keep statistics for performance analysis
	private int totalShotsTaken = 0;
	private int shotsTakenOnCurrentLevel = 0;
	private int levelsCompleted = 0;
	
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
	public void setGame(Game game) {
		this.game = game;
	}
	
	/**
	 * Represents a instance of data used by this learning algorithm
	 */
	private class Instance {
		// Attributes
		private double meanBlockPosition;
		private double meanPigPosition;
		// Class attribute
		private double targetPosition;
	}

	@Override
	public void runContinuously() {
		// Keep going until we can't get anymore level info
		while(!game.isCompleted()) {
			this.runOneLevel();
		}
	}

	@Override
	public void runOneLevel() {
		// Reset level statistics
		this.shotsTakenOnCurrentLevel = 0;
		
		// Get the level environment
		Level currentLevel = this.game.getNextLevelInfo();
		
		// While there are still pigs to kill
		while(!currentLevel.isCompleted()) {
			// If we have a trained classifier to help up

			// Generate random trajectory
			double releaseAngle = this.random.nextDouble() * 90;
			
			currentLevel.takeShot(releaseAngle);
			
			// Increment statistics
			this.totalShotsTaken++;
			this.shotsTakenOnCurrentLevel++;
		}
		
		this.levelsCompleted++;
	}

	@Override
	public int shotsTakenForCurrentLevel() {
		return this.shotsTakenOnCurrentLevel;
	}

	@Override
	public int totalShotsTaken() {
		return this.totalShotsTaken;
	}

	@Override
	public int levelsCompleted() {
		return this.levelsCompleted;
	}

	@Override
	public double averageShotsPerLevel() {
		if(this.levelsCompleted > 0) {
			return this.totalShotsTaken / this.levelsCompleted;
		} else {
			return Integer.MAX_VALUE;
		}
	}
}
