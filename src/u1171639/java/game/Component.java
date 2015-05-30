package u1171639.java.game;

public class Component {
	private Coordinate2D position;
	
	public Component(Coordinate2D position) {
		this.position = position;
	}

	public Coordinate2D getPosition() {
		return position;
	}

	public void setPosition(Coordinate2D position) {
		this.position = position;
	}
}
