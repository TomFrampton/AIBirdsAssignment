package u1171639.java.view.controller;

import java.net.URL;
import java.util.Hashtable;
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
		return "agent_selection.fxml";
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
			final Agent agent = this.agents.get(selection);
			
			// We cannot run the main application execution in the UI thread as
			// it will lock the UI thread and not allow user interaction. Start
			// the agent running in a service thread which runs in parallel
			// with the UI thread
			Service<Void> service = new Service<Void>() {
		        @Override
		        protected Task<Void> createTask() {
		            return new Task<Void>() {           
		                @Override
		                protected Void call() throws Exception {
		                   agent.run();
		                   return null;
		                }
		            };
		        }
		    };
		    
		    service.start();
		}
	}
}
