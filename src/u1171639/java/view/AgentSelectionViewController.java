package u1171639.java.view;

import org.springframework.stereotype.Component;

@Component
public class AgentSelectionViewController extends ViewController {

	public AgentSelectionViewController() {
		
	}

	@Override
	public String getViewName() {
		return "agent_selection.fxml";
	}

}
