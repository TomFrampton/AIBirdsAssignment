package u1171639.java;

public class ComponentsNotDefinedException extends Exception {
	
	public ComponentsNotDefinedException() {
		this("Components Not Defined");
	}
	
	public ComponentsNotDefinedException(String exception) {
		super(exception);
	}
}
