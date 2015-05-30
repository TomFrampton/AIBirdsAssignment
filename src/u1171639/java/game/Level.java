package u1171639.java.game;

import java.util.ArrayList;
import java.util.List;

import u1171639.java.exception.ComponentsNotDefinedException;

public class Level {
	private List<Pig> pigs;
	private List<Block> blocks;
	
	// Dimensions of world
	public static final double LEVEL_X = 100;
	public static final double LEVEL_Y = 50;
	
	public Level(List<Pig> pigs, List<Block> blocks) {
		this.pigs = pigs;
		this.blocks = blocks;
	}
	
	public List<Pig> getPigs() {
		return pigs;
	}
	
	public void setPigs(List<Pig> pigs) {
		this.pigs = pigs;
	}
	
	public List<Block> getBlocks() {
		return blocks;
	}
	
	public void setBlocks(List<Block> blocks) {
		this.blocks = blocks;
	}
	// TODO
	public Coordinate2D meanPigPosition() { // throws ComponentsNotDefinedException {
		// If no pigs have been defined for this level yet we cannot calculate
		// their average position. Throw an exception.
		if(this.pigs == null || this.pigs.isEmpty()) {
			//throw new ComponentsNotDefinedException();
		}
		
		double totalX = 0;
		double totalY = 0;
		
		for(Pig pig : this.pigs) {
			totalX += pig.getPosition().getX();
			totalY += pig.getPosition().getY();
		}
		
		double meanX = totalX / this.pigs.size();
		double meanY = totalY / this.pigs.size();
		
		return new Coordinate2D(meanX, meanY);
	}
	
//	public Position meanBlockPosition() throws ComponentsNotDefinedException {
//		// If no blocks have been defined for this level yet we cannot calculate
//		// their average position. Throw an exception.
//		if(this.blocks == null || this.blocks.isEmpty()) {
//			throw new ComponentsNotDefinedException();
//		}
//		
//		double totalX = 0;
//		double totalY = 0;
//		
//		for(Block block : this.blocks) {
//			totalX += block.getPosition().getX();
//			totalY += block.getPosition().getY();
//		}
//		
//		double meanX = totalX / this.blocks.size();
//		double meanY = totalY / this.blocks.size();
//		
//		return new Position(meanX, meanY);
//	}
	
	public Coordinate2D pigVariance() {
		Coordinate2D meanPigPosition = this.meanPigPosition();
		// Calculate variance in x and y values
		double meanX = meanPigPosition.getX();
		double tempX = 0;
		
		double meanY = meanPigPosition.getY();
		double tempY = 0;
		
		for(Pig pig : this.pigs) {
			tempX += (meanX - pig.getPosition().getX()) *  (meanX - pig.getPosition().getX());
			tempY += (meanY - pig.getPosition().getY()) *  (meanY - pig.getPosition().getY());
		}
		
		double varianceX = tempX / this.pigs.size();
		double varianceY = tempY / this.pigs.size();
		
		return new Coordinate2D(varianceX, varianceY);
	}
	
	public Coordinate2D pigStandardDeviation() {
		Coordinate2D variance = this.pigVariance();
		variance.setX(Math.sqrt(variance.getX()));
		variance.setY(Math.sqrt(variance.getY()));
		return variance;
	}
	
	public boolean takeShot(double releaseAngle) {
		// Stores pigs that we have killed - will process at the end of method
		List<Pig> killedPigs = new ArrayList<Pig>();
		
		// Simulate a straight trajectory
		// Does the bird pass near any pigs along its straight trajectory?
		// Check at every point along the x axis
		for(double x = 0; x < LEVEL_X; ++x) {
			// Get y value for given x using mx + c (c = 0)
			double y =  releaseAngle * x;
			
			//System.out.println(x + " " + y);
			
			// To simulate whether we will kill a pig or not just assume that
			// if the shot is close to a pig that the pig dies			
			for(Pig pig : this.pigs) {
				double pigX = pig.getPosition().getX();
				double pigY = pig.getPosition().getY();
				
				// If the shot landed close to the pig
				if(Math.abs(x - pigX) < 5 && Math.abs(y - pigY) < 5) {
					// This pig is dead
					if(!killedPigs.contains(pig)) {
						killedPigs.add(pig);
						System.out.println("Killed Pig: " + pig.getPosition().getX() + ", " + pig.getPosition().getY());
					}
					
				}
			}
		}
		
		// Remove all the killed pigs
		this.pigs.removeAll(killedPigs);
		
		// Return true if some pigs were killed
		return !killedPigs.isEmpty();
	}
	
	/**
	 * Is the level complete - that is have all the pigs been killed?
	 * @return Is the level complete
	 */
	public boolean isCompleted() {
		return this.pigs.isEmpty();
	}
	
	
}
