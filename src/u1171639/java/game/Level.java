package u1171639.java.game;

import java.util.List;

import u1171639.java.exception.ComponentsNotDefinedException;

public class Level {
	private List<Pig> pigs;
	private List<Block> blocks;
	
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
	
	public Position meanPigPosition() throws ComponentsNotDefinedException {
		// If no pigs have been defined for this level yet we cannot calculate
		// their average position. Throw an exception.
		if(this.pigs == null || this.pigs.isEmpty()) {
			throw new ComponentsNotDefinedException();
		}
		
		double totalX = 0;
		double totalY = 0;
		
		for(Pig pig : this.pigs) {
			totalX += pig.getPosition().getX();
			totalY += pig.getPosition().getY();
		}
		
		double meanX = totalX / this.pigs.size();
		double meanY = totalY / this.pigs.size();
		
		return new Position(meanX, meanY);
	}
	
	public Position meanBlockPosition() throws ComponentsNotDefinedException {
		// If no blocks have been defined for this level yet we cannot calculate
		// their average position. Throw an exception.
		if(this.blocks == null || this.blocks.isEmpty()) {
			throw new ComponentsNotDefinedException();
		}
		
		double totalX = 0;
		double totalY = 0;
		
		for(Block block : this.blocks) {
			totalX += block.getPosition().getX();
			totalY += block.getPosition().getY();
		}
		
		double meanX = totalX / this.blocks.size();
		double meanY = totalY / this.blocks.size();
		
		return new Position(meanX, meanY);
	}
	
	
}
