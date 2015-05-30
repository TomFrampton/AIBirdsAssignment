package u1171639.java.game;

public class Block extends Component {
	private Dimensions dimensions;
	
	public Block(Coordinate2D position, Dimensions dimensions) {
		super(position);
		this.dimensions = dimensions;
	}

}
