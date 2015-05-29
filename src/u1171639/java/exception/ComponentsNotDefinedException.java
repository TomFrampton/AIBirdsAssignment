package u1171639.java.exception;

public class ComponentsNotDefinedException extends Exception {
	
	public ComponentsNotDefinedException() {
		this("Components Not Defined");
	}
	
	public ComponentsNotDefinedException(String exception) {
		super(exception);
	}
}
