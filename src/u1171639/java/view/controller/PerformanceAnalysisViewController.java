package u1171639.java.view.controller;

import java.util.List;

import u1171639.java.agent.Agent;

public class PerformanceAnalysisViewController extends ViewController {
	
	private List<Agent> agents;
	
	@Override
	public String getViewName() {
		return "agent-performance.fxml";
	}

	@Override
	public void initialise() {
		// TODO Auto-generated method stub
		
	}
	
	public void runPerformanceAnalysis(List<Agent> agents) {
		
	}

}
