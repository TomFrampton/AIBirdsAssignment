package u1171639.java.game;

public class AIBirdsGame implements Game {

	@Override
	public Level getNextLevelInfo() {
		// Communicate with the AI Birds server to get the
		// data for the next level.
		return new Level(null, null);
	}

	@Override
	public boolean isCompleted() {
		// TODO Auto-generated method stub
		return false;
	}
}
