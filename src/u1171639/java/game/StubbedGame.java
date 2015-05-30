package u1171639.java.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StubbedGame implements Game {
	private Random random = new Random(System.currentTimeMillis());
	
	/**
	 * Have we completed all levels in the game?
	 * @return All levels complete?
	 */
	public boolean isCompleted() {
		// Stubbed game never ends
		return false;
	}
	
	/**
	 * Generate the environment for a level which includes the positions and
	 * dimensions of blocks and positions of pigs
	 */
	@Override
	public Level getNextLevelInfo() {
		// How many blocks to use in the level (5 to 25)
		int blockCount = this.random.nextInt(21) + 5;
		// How many pigs (1 to 5)
		int pigCount = this.random.nextInt(5) + 1;
		
		List<Block> blocks = new ArrayList<Block>();
		List<Pig> pigs = new ArrayList<Pig>();
		
		// For each block
		for(int i = 0; i < blockCount; ++i) {
			// Generate the position of the block between 0 and max level dimensions
			double xPos = this.random.nextDouble() * Level.LEVEL_X;
			double yPos = this.random.nextDouble() * Level.LEVEL_Y;
			
			// Generate dimensions between 1 and 20
			double width = (this.random.nextDouble() * 19) + 1;
			double height = (this.random.nextDouble() * 19) + 1;
			
			// Create a new block using the generated values
			blocks.add(new Block(new Position(xPos, yPos), new Dimensions(width, height)));
		}
		
		// For each pig
		for(int i = 0; i < pigCount; ++i) {
			// Generate the position of the pig between 0 and max level dimensions
			double xPos = this.random.nextDouble() * Level.LEVEL_X;
			double yPos = this.random.nextDouble() * Level.LEVEL_Y;
			
			// Create a pig using the generated values
			pigs.add(new Pig(new Position(xPos, yPos)));
		}
		
		return new Level(pigs, blocks);
	}

}
