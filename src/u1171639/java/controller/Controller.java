package u1171639.java.controller;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import u1171639.java.view.View;

public class Controller {

	private View view;
	
	public Controller() {
		
	}
	
	public Controller(View view) {
		this.view = view;
	}
	
	public static void main(String[] args) {
		try (ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("u1171639/application-context.xml")) {
			Controller controller = context.getBean(Controller.class);
			
			controller.start();
		}
	}
	
	public void start() {
		this.view.start();
	}
}
