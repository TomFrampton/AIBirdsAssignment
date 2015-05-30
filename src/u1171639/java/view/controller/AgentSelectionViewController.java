package u1171639.java.view.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import org.springframework.stereotype.Component;

import u1171639.java.agent.Agent;

@Component
public class AgentSelectionViewController extends ViewController {
	
	private Hashtable<String, Agent> agents = new Hashtable<String, Agent>();
	@FXML private ComboBox<String> agentSelection;
	
	public AgentSelectionViewController() {
		
	}

	@Override
	public String getViewName() {
		return "peformance-analysis.fxml";
	}
	
	@Override
	public void initialise() {
		ObservableList<String> agentList = FXCollections.observableArrayList();
		
		for(Agent agent : this.getController().getAgents()) {
			agentList.add(agent.getName());
			this.agents.put(agent.getName(), agent);
		}
		
		this.agentSelection.setItems(agentList);
	}
	
	@FXML protected void handleRunAgentClicked(MouseEvent event) {
		String selection = this.agentSelection.getSelectionModel().getSelectedItem();
		
		if(selection != null) {
			startPerformanceAnalysis(this.agents.get(selection));
		}
	}
	
	private void startPerformanceAnalysis(List<Agent> selectedAgents) {
		// Get Performance Analysis View Controller
		PerformanceAnalysisViewController performanceAnalysis = this.getViewManager().performanceAnalysis();
		performanceAnalysis.runPerformanceAnalysis(selectedAgents);
		
	}
	
	private void startPerformanceAnalysis(Agent agent) {
		List<Agent> agents = new ArrayList<Agent>();
		agents.add(agent);
		this.startPerformanceAnalysis(agents);
	}
}
