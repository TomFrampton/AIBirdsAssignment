package u1171639.java.controller;

import java.util.List;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import u1171639.java.agent.Agent;
import u1171639.java.game.Game;
import u1171639.java.view.View;

public class Controller {
	private View view;
	private Game game;
	private List<Agent> agents;
	
	public Controller() {
		
	}
	
	public Controller(View view, Game game, List<Agent> agents) {
		this.view = view;
		this.game = game;
		this.agents = agents;
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
