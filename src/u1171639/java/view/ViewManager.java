package u1171639.java.view;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;

public class ViewManager {
	private Stage stage;
	private AnchorPane panel;
	
	// View Controllers - one for each view
	@Autowired private AgentSelectionViewController agentSelectionViewController;	

	public ViewManager() {
		
	}
	
	public void initStage(Stage stage, int width, int height) {
		this.panel = new AnchorPane();
		this.panel.setMinWidth(width);
		this.panel.setMinHeight(height);
		
		this.stage = stage;
		this.stage.setScene(new Scene(this.panel, width, height));
		this.stage.show();
	}
	
	public void showScreen(ViewController controller) {
		Parent screen = controller.getView();
		
		AnchorPane.setTopAnchor(screen, 0.0);
		AnchorPane.setBottomAnchor(screen, 0.0);
		AnchorPane.setLeftAnchor(screen, 0.0);
		AnchorPane.setRightAnchor(screen, 0.0);
		
		this.panel.getChildren().clear();
		this.panel.getChildren().add(screen);
	}
	
	public void setStage(Stage stage) {
		this.stage = stage;
	}
	
	public AgentSelectionViewController agentSelection() {
		return this.agentSelectionViewController;
	}
}
